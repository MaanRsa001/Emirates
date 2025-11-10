package com.maan.admin.controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.admin.DAO.MotorConfigBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
public class MotorConfigController extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(MotorConfigController.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FORMPROCESS = "**********Here we populating the bean and displays the values in form*********";
	private static final String AREACOVERID = "areaCoverId";
	private static final String EFFECTIVEDATE="effDay";
	private static final String EFFECTIVEMONTH="effMonth";
	private static final String EFFECTIVEYEAR="effYear";
	private static final String STATUS="status";
	private static final String REMARKS ="remarks";
	private static final String VALIDCODE="Enter the Valid Code <br>";
	private static final String VALIDSTATUS="Select the Status <br>";
	private static final String DATEVALIDATION="Effective date should be greater or equal to previous effective date <br>";
	private static final String ERROR="error";
	private static final String BRANCHCODE="Branch Code::";
	private static final String POLICYTYPEID="policyTypeId";
	private static final String OPCOVERID="opCoverId";
	private static final String BANKID="bankId";
	private static final String BANKCODE="bankCOde";
	private static final String RESULTSIZE="Result size:";
	private static final String MOTORCONFIGBEAN="MotorConfigBean";
	private static final String CORECODE="coreAppCode";
	private static final String BANKENG="bankNameEng";
	private static final String BANKARABIC="bankNameArabic";
	private static final String COLORID="colorId";
	private static final String VTYPEID="vtypeId";
	private static final String TYPEOFBODYID="typeOfBodyId";
	private static final String CYLINDERID="cylId";
	private static final String FILEID="fileId";
	private static final String PRODUCTID="productId";
	private static final String GROUPID="groupId";
	private static final String VOLUNTARYID="voluntaryId";
	private static final String MODE="mode";
	
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException 
	{	
		try{
			processResult(request, response);
		}catch(BaseException e){
			logger.debug(e);
		}
	}
	
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException 
	{
		try{
			processResult(request, response);
		}catch(BaseException e){
			logger.debug(e);
		}
	}
	
	public void processResult(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException, BaseException 
	{
		try{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
		}catch(Exception e){
			logger.debug(e);
		}
		final HttpSession session = request.getSession(true);
		final MotorConfigBean bean=new MotorConfigBean();
		RequestDispatcher dispatcher;
		
		final String adminPid=session.getAttribute("AdminPid")==null?"":(String)session.getAttribute("AdminPid");
		final String branchCode= session.getAttribute("LoginBranchCode")==null?"":(String)session.getAttribute("LoginBranchCode");
		final String[][] pids=bean.getProductIds(adminPid, branchCode);
		final ValidationFormat validate=new ValidationFormat();
		final StringBuffer errorMessage=new StringBuffer("");
		final boolean flag;
		final String mode;
		final String areaCoverId;
		
		
		request.setAttribute("pids", pids);
		
		if(session.getAttribute("ses")==null)
		{
		response.sendRedirect("../login/error_messg.jsp");
		
		}
		
		final String requestFrom = request.getParameter("requestFrom")==null?"":request.getParameter("requestFrom");
	if("displayeconfig".equalsIgnoreCase(requestFrom))
	{
		logger.info("enter to make config");
		//final String branchCode= (String)session.getAttribute("LoginBranchCode");
		String productId=(String)session.getAttribute(PRODUCTID);
		if(productId==null)
		{
			productId="65";
		}
		final String[][] result=bean.getConfig(branchCode);
		
		logger.info(RESULTSIZE+result.length);
		ArrayList configList=null;
		try
		{
		 configList= bean.getConfigAreaList(result);
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is configList Size++++++++"+configList.size()); 
	    request.setAttribute("motorConfigList",configList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorAreaCoverage.jsp");
		dispatcher.forward(request,response);
				
	}
	
	else if("editMotorConfig".equalsIgnoreCase(requestFrom))
	{
		logger.info(FORMPROCESS);
		
		
		  areaCoverId=request.getParameter(AREACOVERID);
		logger.info("areaCoverId value"+areaCoverId);
	
		
		final String[][] result=bean.getAreaCongigById(branchCode,areaCoverId);
		
		if(result.length>0)
		{
		bean.setAreaCoverId(result[0][0]);
		bean.setAreaCoverCode(result[0][1]);
		bean.setAreaEnglish(result[0][2]);
		bean.setAreaArabic(result[0][3]);
		bean.setEffYear(result[0][4]);
		logger.info("Hear is Year++++="+bean.getEffYear());
		bean.setEffMonth(result[0][5]);
		bean.setEffDay(result[0][6]);
		bean.setStatus(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean AreaCoverId====>"+bean.getAreaCoverId());
		logger.info("In OCntroler date is====>"+bean.getEffDay());
		logger.info("In OCntroler mon is====>"+bean.getEffMonth());
		logger.info("In OCntroler year is====>"+bean.getEffYear());
		  dispatcher=request.getRequestDispatcher("../admin/MoterAreaEdit.jsp");
		
		dispatcher.forward(request,response);
		logger.info("------------exit from editAreaconfig--------");
	}
	
	
	else if("uplodeMotorConfig".equalsIgnoreCase(requestFrom))
	{

		  mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In areaCover Controller: "+mode);
		 
		
		  areaCoverId=request.getParameter(AREACOVERID)==null?"":request.getParameter(AREACOVERID);
		logger.info("In COntroler Request CID IS====>>"+areaCoverId);
	
		
		bean.setAreaCoverId(request.getParameter(AREACOVERID)==null?"":request.getParameter(AREACOVERID));
		bean.setAreaEnglish(request.getParameter("areaEnglish")==null?"":request.getParameter("areaEnglish"));
		bean.setAreaArabic(request.getParameter("areaArabic")==null?"":request.getParameter("areaArabic"));
		bean.setAreaCoverCode(request.getParameter("areaCoverCode")==null?"":request.getParameter("areaCoverCode"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		
		
		final String areaCoverId1=bean.getAreaCoverId();
		logger.info("In COntroler  Bean CID IS====>>"+areaCoverId1);
		
		
		if(bean.getAreaEnglish().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Area Name(English) <br>");
		}	
		
		if(bean.getAreaCoverCode().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Area Code <br>");
		}
		else
		{
			
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getAreaCoverCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		logger.info("-getEffDay----FDG------->"+bean.getEffDay());
		logger.info("-getEffMonth--DF--------->"+bean.getEffMonth());
		logger.info("-getEffYear---FG-------->"+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String areaCoverId2=bean.getAreaCoverId()==null?"":bean.getAreaCoverId();
			final String result=bean.isVAlidAreaDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),areaCoverId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In Area If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MoterAreaEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertAreaConfig(branchCode, areaCoverId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 
			
			 logger.info(BRANCHCODE+branchCode+"AreaID::"+ areaCoverId+"Mode Is=>"+mode);
			
			 logger.info("*****After Area Edit Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayeconfig");
	      }
		 
		 dispatcher.forward(request, response);
		

		 
	}
	
	
	else if("displaymotortype".equalsIgnoreCase(requestFrom))
	{

		logger.info("enter to Motor Type");
	
		
		final String[][] result=bean.getMotorPolicy(branchCode);
		ArrayList motorPolicyTypeList=null;
		try
		{
		 
		 motorPolicyTypeList= bean.getMotorPolicyTypeList(result);
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorPolicyTypeList Size++++++++"+motorPolicyTypeList.size()); 
	    request.setAttribute("motorPolicyTypeList",motorPolicyTypeList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorPolicyType.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	else if("editMotorPolicyType".equalsIgnoreCase(requestFrom))
	{
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String policyTypeId=request.getParameter(POLICYTYPEID);
		logger.info("policyTypeId VALUE==>"+policyTypeId);
	
		
		final String[][] result=bean.getMotorPolicyById(branchCode,policyTypeId);
		
		if(result.length>0)
		{
		bean.setPolicyTypeId(result[0][0]==null?"":result[0][0]);
		bean.setCoreAppCode(result[0][1]==null?"":result[0][1]);
		bean.setPolyTypeEng(result[0][2]==null?"":result[0][2]);
		bean.setPolyTypeArabic(result[0][3]==null?"":result[0][3]);
		bean.setStatus(result[0][4]==null?"":result[0][4]);
		bean.setEffYear(result[0][5]==null?"":result[0][5]);
		bean.setEffMonth(result[0][6]==null?"":result[0][6]);
		bean.setEffDay(result[0][7]==null?"":result[0][7]);
		bean.setRemarks(result[0][8]==null?"":result[0][8]);
		bean.setPolicyTerm(result[0][9]==null?"":result[0][9]);
		bean.setPolicytypeName(result[0][10]==null?"":result[0][10]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
    	logger.info("AND REMARKS IS==============================================>>>>>>>>>>>>>>>"+bean.getRemarks());
		logger.info("bean PolicyTypeId====>"+bean.getPolicyTypeId());
		dispatcher=request.getRequestDispatcher("../admin/MotorPolicyEdit.jsp");
		dispatcher.forward(request,response);
		logger.info("------------exit from editPolicyType--------");
	}
	
	else if("uplodePolicyType".equalsIgnoreCase(requestFrom))
	{

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In Policy Controller: "+mode);
		  dispatcher=null;
		
		final String policyTypeId=request.getParameter(POLICYTYPEID)==null?"":request.getParameter(POLICYTYPEID);
		logger.info("In COntroler Request Policy Id IS====>>"+policyTypeId);
	
		bean.setPolicytypeName(request.getParameter("policyTypeName")==null?"":request.getParameter("policyTypeName"));
		bean.setPolicyTypeId(request.getParameter(POLICYTYPEID)==null?"":request.getParameter(POLICYTYPEID));
		bean.setPolyTypeEng(request.getParameter("polyTypeEng")==null?"":request.getParameter("polyTypeEng"));
		bean.setPolyTypeArabic(request.getParameter("polyTypeArabic")==null?"":request.getParameter("polyTypeArabic"));
		bean.setCoreAppCode(request.getParameter(CORECODE)==null?"":request.getParameter(CORECODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		bean.setPolicyTerm(request.getParameter("policyTerm")==null?"":request.getParameter("policyTerm"));
		
		final String policyTypeId1=bean.getPolicyTypeId();
		logger.info("In COntroler  Bean CID IS====>>"+policyTypeId1);
		
		if(bean.getPolicytypeName().equalsIgnoreCase("")){
			errorMessage.append("Enter the Policy Type Name <br>");
		}	
		if(bean.getPolyTypeEng().equalsIgnoreCase("")){
			errorMessage.append("Enter the Policy Type Desc(English) <br>");
		}	
		
		if(bean.getCoreAppCode().equalsIgnoreCase("")){
			errorMessage.append("Enter the Core App Code <br>");
		}else{
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getCoreAppCode());
			if(!flag){
				errorMessage.append(VALIDCODE);
			}
		}
		
		if(bean.getStatus().equalsIgnoreCase("")){
			errorMessage.append(VALIDSTATUS);
		}
		logger.info("-getEffDay----GFH------->"+bean.getEffDay());
		logger.info("-getEffMonth--GH--------->"+bean.getEffMonth());
		logger.info("-getEffYear---GHD-------->"+bean.getEffYear());
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0){
			
			final String policyId=bean.getPolicyTypeId()==null?"":bean.getPolicyTypeId();
			final String result=bean.isVAlidEffDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),policyId,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		logger.info("&&&&&&&&&&&&&&&&&&&&Here Is Date&&&&&&&&&&&&&&&&"+errorMessage);
		 
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In policy If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorPolicyEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorPolicy(branchCode, policyTypeId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 
			 logger.info(BRANCHCODE+branchCode+"AreaID::"+ policyTypeId+"Mode Is===>"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute policyType Edit Query");
			
			 logger.info("*****After Policy Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displaymotortype");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	
	else if("displayopcover".equalsIgnoreCase(requestFrom))
	{

		logger.info("enter to OPCover");
		
		
		final String[][] result=bean.getOPCover(branchCode);
		logger.info(RESULTSIZE+result.length);
		ArrayList motorOPCoverList=null;
		try
		{
		 
		 motorOPCoverList= bean.getMotorOPCoverList(result);
		
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorOPCoverList Size++++++++"+motorOPCoverList.size()); 
	    request.setAttribute("motorOPCoverList",motorOPCoverList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorOPCover.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorOPCover".equalsIgnoreCase(requestFrom))
	{
		logger.info(FORMPROCESS);
		
		
		final String opCoverId=request.getParameter(OPCOVERID);
		logger.info("opCoverId VALUE==>"+opCoverId);
		 
		final String[][] result=bean.getMotorOPCoverById(branchCode,opCoverId);
		
		if(result.length>0)
		{
		bean.setOpCoverId(result[0][0]);
		bean.setCoreAppCode(result[0][1]);
		bean.setOpCoverEnglish(result[0][2]);
		bean.setOpCoverArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		bean.setProductId(pids[0][0]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean OPCoverID====>"+bean.getOpCoverId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorOPCoverEdit.jsp");
		 // dispatcher=request.getRequestDispatcher("../admin/MotorPolicyEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editOPCover--------");
	}
	
	
	else if("uplodeOPCover".equalsIgnoreCase(requestFrom))
	{

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In OPCover Controller: "+mode);
		  dispatcher=null;
		
		final String opCoverId=request.getParameter(OPCOVERID)==null?"":request.getParameter(OPCOVERID);
		logger.info("In COntroler Request Cover ID  IS====>>"+opCoverId);
	 
		
		bean.setOpCoverId(request.getParameter(OPCOVERID)==null?"":request.getParameter(OPCOVERID));
		bean.setOpCoverEnglish(request.getParameter("opCoverEnglish")==null?"":request.getParameter("opCoverEnglish"));
		bean.setOpCoverArabic(request.getParameter("opCoverArabic")==null?"":request.getParameter("opCoverArabic"));
		bean.setCoreAppCode(request.getParameter(CORECODE)==null?"":request.getParameter(CORECODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		
		
		final String opCoverId1=bean.getOpCoverId();
		logger.info("In COntroler  Bean CID IS====>>"+opCoverId1);
		
		
		if(bean.getOpCoverEnglish().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the OPCover Name(English) <br>");
		}	
		
		if(bean.getCoreAppCode().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Core App Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getCoreAppCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		logger.info("-getEffDay----HG------->"+bean.getEffDay());
		logger.info("-getEffMonth---FBGF-------->"+bean.getEffMonth());
		logger.info("-getEffYear----HFGH------->"+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String opCoverId2=bean.getOpCoverId()==null?"":bean.getOpCoverId();
			final String result=bean.isVAlidOPDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),opCoverId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		logger.info("&&&&&&&&&&&&&&&&&&&&Here Is Date&&&&&&&&&&&&&&&&"+errorMessage);
		 
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In OPCover If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorOPCoverEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertOPCover(branchCode, opCoverId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 logger.info(BRANCHCODE+branchCode+"OPCover ID::"+ opCoverId+"Mode >"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute opCover Edit Query");
			
			 logger.info("*****After Opcover Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayopcover");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	else if("displaybank".equalsIgnoreCase(requestFrom))
	{

		logger.info("enter to Bank");
	 
		
		final String[][] result=bean.getBank(branchCode);
		logger.info(RESULTSIZE+result.length);
		ArrayList motorBankList=null;
		try
		{
		 
		 motorBankList= bean.getMotorBankList(result);
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorBankList Size++++++++"+motorBankList.size()); 
	    request.setAttribute("motorBankList",motorBankList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorBank.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorBank".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String bankId=request.getParameter(BANKID);
		logger.info("ID VALUE==>"+bankId);
	 
		
		final String[][] result=bean.getMotorBankById(branchCode,bankId);
		
		if(result.length>0)
		{
		bean.setBankId(result[0][0]);
		bean.setBankCOde(result[0][1]);
		bean.setBankNameEng(result[0][2]);
		bean.setBankNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean bankId====>"+bean.getBankId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorBankEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editMotorBank-------");
	}
	
	
	else if("uplodeMotorBank".equalsIgnoreCase(requestFrom))
	{
		logger.info("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In Bank Controller: "+mode);
		  dispatcher=null;
		
		  final String bankId=request.getParameter(BANKID)==null?"":request.getParameter(BANKID);
		logger.info("In COntroler Request Bank Id IS====>>"+bankId);
	 
		
		bean.setBankId(request.getParameter(BANKID)==null?"":request.getParameter(BANKID));
		bean.setBankNameEng(request.getParameter(BANKENG)==null?"":request.getParameter(BANKENG));
		bean.setBankNameArabic(request.getParameter(BANKARABIC)==null?"":request.getParameter(BANKARABIC));
		bean.setBankCOde(request.getParameter(BANKCODE)==null?"":request.getParameter(BANKCODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		
		final String bankId1=bean.getBankId();
		logger.info("In COntroler  Bean Bank Id  IS====>>"+bankId1);
		logger.info("In COntroler  Bean Bank Cod4e IS====>>"+bean.getBankCOde());
		logger.info("In COntroler  Bean Bank Status  IS====>>"+bean.getStatus());
		logger.info("In COntroler  Bean Bank Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getBankNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Name(English) <br>");
		}	
		
		if(bean.getBankCOde().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getBankCOde());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		logger.info("-------day----->"+bean.getEffDay());
		logger.info("------month------>"+bean.getEffMonth());
		logger.info(""+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String bankId2=bean.getBankId()==null?"":bean.getBankId();
			final String result=bean.isVAlidBankDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),bankId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		//logger.info("&&&&&&&&&&&&&&&&&&&&Here Is Date&&&&&&&&&&&&&&&&"+errorMessage);
		 
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In Bank If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorBankEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 System.out.println("Hiiii Again1");
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 System.out.println("Hiiii Again2");
				 msg= bean.insertMotorBank(branchCode, bankId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 System.out.println("Hiiii Again3");
			 logger.info(BRANCHCODE+branchCode+"Bank ID::"+ bankId+"Mode Is==>>"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute ank Edit Query");
			
			 logger.info("*****After insertMotorBank Insertion*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displaybank");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	else if("displaymotorcolor".equalsIgnoreCase(requestFrom))
	{

		logger.info("enter to Motor Color");
	 
		
		final String[][] result=bean.getMotorColor(branchCode);
		logger.info(RESULTSIZE+result.length);
		ArrayList motorColorList=null;
		try
		{
		 
		 motorColorList= bean.getMotorColorList(result);
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorColorList Size++++++++"+motorColorList.size()); 
	    request.setAttribute("motorColorList",motorColorList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorColor.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorColorType".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String colorId=request.getParameter(COLORID);
		logger.info("ID VALUE==>"+colorId);
	 
		
		final String[][] result=bean.getMotorColorById(branchCode,colorId);
		
		if(result.length>0)
		{
		bean.setColorId(result[0][0]);
		bean.setColorCode(result[0][1]);
		bean.setColorNameEng(result[0][2]);
		bean.setColorNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean colorId====>"+bean.getColorId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorColorEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editColorType-------");
	}
	
	else if("uplodeColorType".equalsIgnoreCase(requestFrom))
	{
		logger.info("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In Color Controller: "+mode);
		  dispatcher=null;
		 String colorId=request.getParameter(COLORID)==null?"":request.getParameter(COLORID);
		logger.info("In COntroler Request Color Id IS====>>"+colorId);
		bean.setColorId(request.getParameter(COLORID)==null?"":request.getParameter(COLORID));
		bean.setColorNameEng(request.getParameter("colorNameEng")==null?"":request.getParameter("colorNameEng"));
		bean.setColorNameArabic(request.getParameter("colorNameArabic")==null?"":request.getParameter("colorNameArabic"));
		bean.setColorCode(request.getParameter("colorCode")==null?"":request.getParameter("colorCode"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		final String colorId1=bean.getColorId();
		logger.info("In COntroler  Bean colorId1 Id  IS====>>"+colorId1);
		logger.info("In COntroler  Bean colorId1 Cod4e IS====>>"+bean.getColorCode());
		logger.info("In COntroler  Bean colorId1 Status  IS====>>"+bean.getStatus());
		logger.info("In COntroler  Bean colorId1 Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getColorNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Color Name(English) <br>");
		}	
		
		if(bean.getColorCode().equalsIgnoreCase(""))
		{
			
			errorMessage.append("Enter the Color Code <br>");
		}
		else  if(session.getAttribute("doProcess")!=null)
		{
			colorId=(colorId==null||colorId==""||colorId.trim().length()<=0)?"0":colorId;
			final int count=bean.getColorCount(branchCode,colorId,bean.getColorCode());
			if (count>0)
			{
				errorMessage.append("Entered Color Code Is Already Exist <br>");
			}
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getColorCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
			 
		}
		
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0){
				final String colorId2=bean.getColorId()==null?"":bean.getColorId();
				final String result=bean.isVAlidColorDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),colorId2,branchCode);
				if(result.equalsIgnoreCase("no")){
					errorMessage.append(DATEVALIDATION);
				}
			}else if(message!=null){
				errorMessage.append(message);
			}
		}
		
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In color If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorColorEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 System.out.println("Hiiii Again1");
			 if(session.getAttribute("doProcess")!=null){
				 System.out.println("Hiiii Again2");
				 msg= bean.insertMotorColor(branchCode, colorId,bean,mode);
				 session.removeAttribute("doProcess");
				}
			 System.out.println("Hiiii Again3");
			 logger.info(BRANCHCODE+branchCode+"colorId ID::"+ colorId+"Mode Is=--=>"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute color Edit Query");
			 
			 logger.info("*****After insertMotorColor*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displaymotorcolor");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	else if("displayfinancebank".equalsIgnoreCase(requestFrom))
	{

		logger.info("enter to FinanceBank");
		 
		
		final String[][] result=bean.getFinanceBank(branchCode);
		logger.info(RESULTSIZE+result.length);
		ArrayList motorFinanceBankList=null;
		try
		{
		 
		 motorFinanceBankList= bean.getMotorFinanceBankList(result);
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorFinanceBankList Size++++++++"+motorFinanceBankList.size()); 
	    request.setAttribute("motorFinanceBankList",motorFinanceBankList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorFinanceBank.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorFinanceBank".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String bankId=request.getParameter(BANKID);
		logger.info("bankId VALUE==>"+bankId);
		final String[][] result=bean.getMotorFinanceBankById(branchCode,bankId);
		
		if(result.length>0)
		{
		bean.setBankId(result[0][0]);
		bean.setBankCOde(result[0][1]);
		bean.setBankNameEng(result[0][2]);
		bean.setBankNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean bankId====>"+bean.getBankId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorFinanceBankEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editMotorFinanceBank--------");
	}
	
	
	else if("uplodeMotorFinanceBank".equalsIgnoreCase(requestFrom))
	{
		 

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In FinanceBank Controller: "+mode);
		  dispatcher=null;
		
		  final String bankId=request.getParameter(BANKID)==null?"":request.getParameter(BANKID);
		logger.info("In COntroler Request Bank ID  IS====>>"+bankId);
	 
		
		bean.setBankId(request.getParameter(BANKID)==null?"":request.getParameter(BANKID));
		bean.setBankNameEng(request.getParameter(BANKENG)==null?"":request.getParameter(BANKENG));
		bean.setBankNameArabic(request.getParameter(BANKARABIC)==null?"":request.getParameter(BANKARABIC));
		bean.setBankCOde(request.getParameter(BANKCODE)==null?"":request.getParameter(BANKCODE));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		
		final String bankId1=bean.getBankId();
		logger.info("In COntroler  Bean Bank Id  IS====>>"+bankId1);
		logger.info("In COntroler  Bean Bank Cod4e IS====>>"+bean.getBankCOde());
		logger.info("In COntroler  Bean Bank Status  IS====>>"+bean.getStatus());
		logger.info("In COntroler  Bean Bank Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getBankNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Name(English) <br>");
		}	
		
		if(bean.getBankCOde().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Bank Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getBankCOde());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		logger.info("-getEffDay-----HGFH------>"+bean.getEffDay());
		logger.info("-getEffMonth----BVC------->"+bean.getEffMonth());
		logger.info("-getEffYear-----BC------>"+bean.getEffYear());
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String bankId2=bean.getBankId()==null?"":bean.getBankId();
			final String result=bean.isVAlidFinanceDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),bankId2,branchCode);
			if(result.equalsIgnoreCase("no")){
			
				errorMessage.append(DATEVALIDATION);
			 }


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		
		 
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In Bank If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorFinanceBankEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorFinanceBank(branchCode, bankId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 logger.info(BRANCHCODE+branchCode+"Bank ID::"+ bankId+"Mode >"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute Bank Edit Query");
			 logger.info("Finance Bank Arabic ==============>>>>>>>??"+bean.getBankNameArabic());
			
			 logger.info("*****After insertMotorFinanceBank*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayfinancebank");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	else if("displayMotorCylinder".equalsIgnoreCase(requestFrom))
	{
		logger.info("enter to displayMotorCylinder config");
		final String value=request.getParameter("val")==null?"":request.getParameter("val");
		logger.info("......Seeeeee Problem MAy here........");
		
		
		 
		if(value.equalsIgnoreCase("displayBodyList"))
		{
			final com.maan.admin.DAO.MotorBodyCreation bodybean = new com.maan.admin.DAO.MotorBodyCreation();
			logger.info("Inside displayBodyList--- displayMotorCylinder");
		 
			String productId=(String)session.getAttribute(PRODUCTID);
			if(productId==null)
			{
				productId="65";
			}
			final String[][] result=bodybean.getMotorBodyTypeId(branchCode,productId);
			request.setAttribute("bodyList",result);
			logger.info("t1");
			  dispatcher = request.getRequestDispatcher("../admin/MotorCylinder.jsp");
			logger.info("t1");
			dispatcher.forward(request,response);
			
		}
		else if(value.equalsIgnoreCase("displayCylinderList"))
		{
			logger.info(".............Hello Ram Now I am  In Model Config Part............");
			logger.info("Entry to display config");
		 
			logger.info("........Hai Ram Branch Code Is::"+branchCode);
			String typeOfBodyId=request.getParameter(TYPEOFBODYID);
			String cylTypeOfBodyName=bean.getCylinderTypeOfBody(branchCode, typeOfBodyId);
			request.setAttribute("cylTypeOfBodyName", cylTypeOfBodyName);
			
			String bodyName=request.getParameter("bodyId")==null?"":request.getParameter("bodyId");
			if(bodyName==null || bodyName=="")
			{
				bodyName=request.getParameter("bodyName");
				
			}
			if(typeOfBodyId==null || typeOfBodyId.equalsIgnoreCase(""))
			{
				typeOfBodyId=session.getAttribute(TYPEOFBODYID)==null?"":(String)session.getAttribute(TYPEOFBODYID);
			}
			logger.info(".......Hai Ram  Body Name Is ::::"+typeOfBodyId);
			request.setAttribute("typeOfBodyId",typeOfBodyId);
			final String [][]result=bean.getCylinderDisplay(branchCode, typeOfBodyId);
			
			ArrayList motorCylinderList=null;
			try{
			 logger.info("Model:1"); 
			 motorCylinderList=bean.getMotorCylinderList(result);
			 logger.info("Model:2");
			}  
			catch(Exception e)
			{
				logger.debug(e);
			}
			 
		    request.setAttribute("MotorCylinderList",motorCylinderList);
		    session.setAttribute(TYPEOFBODYID, typeOfBodyId);
		    logger.info("typeOfBodyId: "+typeOfBodyId);
		      dispatcher = request.getRequestDispatcher("../admin/MotorCylinder.jsp?display=cylinderList&body="+bodyName);
			dispatcher.forward(request,response);
			logger.info("-----------------Exit from display model config-----------");
			
		}
	}
	
	
	
	else if("editMotorCylinder".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String cylId=request.getParameter(CYLINDERID);
		logger.info("cylId VALUE==>"+cylId);
		String typeOfBodyId=request.getParameter(TYPEOFBODYID);
		String cylTypeOfBodyName=bean.getCylinderTypeOfBody(branchCode, typeOfBodyId);
		request.setAttribute("cylTypeOfBodyName", cylTypeOfBodyName);
	
		
		final String[][] result=bean.getMotorCylinderById(branchCode,cylId);
		
		if(result.length>0)
		{
		bean.setCylId(result[0][0]);
		bean.setCylCode(result[0][1]);
		bean.setCylNameEng(result[0][2]);
		bean.setCylNameArabic(result[0][3]);
		bean.setStatus(result[0][4]);
		bean.setEffYear(result[0][5]);
		bean.setEffMonth(result[0][6]);
		bean.setEffDay(result[0][7]);
		bean.setRemarks(result[0][8]);
		bean.setTypeOfBodyId(result[0][9]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean Cylinder Id====>"+bean.getCylId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorCylinderEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editCylinderType---");
	}
	
	
	else if("uplodeCylinderType".equalsIgnoreCase(requestFrom))
	{
		logger.info("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In Cylinder Controller: "+mode);
		  dispatcher=null;
		
		  final String cylId=request.getParameter(CYLINDERID)==null?"":request.getParameter(CYLINDERID);
		logger.info("In COntroler Request Cylinder Id  IS====>>"+cylId);
	 
		
		bean.setCylId(request.getParameter(CYLINDERID)==null?"":request.getParameter(CYLINDERID));
		bean.setCylNameEng(request.getParameter("cylNameEng")==null?"":request.getParameter("cylNameEng"));
		bean.setCylNameArabic(request.getParameter("cylNameArabic")==null?"":request.getParameter("cylNameArabic"));
		bean.setCylCode(request.getParameter("cylCode")==null?"":request.getParameter("cylCode"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		bean.setTypeOfBodyId(request.getParameter(TYPEOFBODYID)==null?(String)session.getAttribute(TYPEOFBODYID):request.getParameter(TYPEOFBODYID));
		final String typeOfBodyId=request.getParameter(TYPEOFBODYID);
		final String cylTypeOfBodyName=bean.getCylinderTypeOfBody(branchCode, typeOfBodyId);
		request.setAttribute("cylTypeOfBodyName", cylTypeOfBodyName);
	
		
		final String cylId1=bean.getCylId();
		logger.info("In COntroler  Bean Cylinder Id  IS====>>"+cylId1);
		logger.info("In COntroler  Bean Cylinder Cod4e IS====>>"+bean.getCylCode());
		logger.info("In COntroler  Bean Cylinder Status  IS====>>"+bean.getStatus());
		logger.info("In COntroler  Bean Cylinder Remarks  IS====>>"+bean.getRemarks());
		logger.info("In COntroler  Bean Cylinder body id  IS====>>"+bean.getTypeOfBodyId());
		if(bean.getCylNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Cylinder Name(English) <br>");
		}	
		
		if(bean.getCylCode().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Cylinder Code <br>");
		}
		else
		{
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getCylCode());
			if(!flag)
			{
				errorMessage.append(VALIDCODE);
			}
		}
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		logger.info("---------day--->"+bean.getEffDay());
		logger.info("-------mon----->"+bean.getEffMonth());
		logger.info("-------year----->"+bean.getEffYear());
		
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String cylId2=bean.getCylId()==null?"":bean.getCylId();
			final String result=bean.isVAlidCylinderDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),cylId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		
		 
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In cylinder If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorCylinderEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorCylinder(branchCode, cylId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 logger.info(BRANCHCODE+branchCode+"cylId ID::"+ cylId+"Mode Is====>"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute Cylinder Edit Query");
			 
			 logger.info("*****After insertMotorCylinder*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayMotorCylinder&val=displayCylinderList");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	else if("displayUplodeFile".equalsIgnoreCase(requestFrom))
	{

		logger.info("enter to Motor File");
	 
		
		final String[][] result=bean.getUplodeFIle(branchCode);
		logger.info(RESULTSIZE+result.length);
		ArrayList motorUplodeFileListt=null;
		try
		{
		 
		 motorUplodeFileListt= bean.getMotorUplodeFileList(result, pids);
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorUplodeFileListt Size++++++++"+motorUplodeFileListt.size()); 
	    request.setAttribute("motorUplodeFileListt",motorUplodeFileListt);
		  dispatcher = request.getRequestDispatcher("../admin/MotorUplodeFile.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	else if("editMotorUplodeFile".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String fileId=request.getParameter(FILEID);
		logger.info("fileId VALUE==>"+fileId);
	 
		if ("Edit".equalsIgnoreCase(mode)){
			final String[][] result=bean.getMotorFileById(branchCode,fileId);
			
			if(result.length>0)
			{
			bean.setFileId(result[0][0]);
			bean.setFileType(result[0][1]);
			bean.setProductId(result[0][2]);
			bean.setStatus(result[0][3]);
			bean.setRemarks(result[0][4]);
			}
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean fileId====>"+bean.getFileId());
		logger.info("bean Product Id====>"+bean.getProductId());
		logger.info("------------------------------------>>>>>"+request.getAttribute("pids"));
		  dispatcher=request.getRequestDispatcher("../admin/MotorUplodeFileEdit.jsp?mode="+mode);
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editMotorFile--------");
	}
	
	else if("uplodeMotorFile".equalsIgnoreCase(requestFrom))
	{
		 

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In File Controller: "+mode);
		  dispatcher=null;
		
		  final String fileId=request.getParameter(FILEID)==null?"":request.getParameter(FILEID);
		logger.info("In COntroler File Id IS====>>"+fileId);
	 
		
		bean.setFileId(request.getParameter(FILEID)==null?"":request.getParameter(FILEID));
		bean.setFileType(request.getParameter("fileType")==null?"":request.getParameter("fileType"));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		bean.setProductId(request.getParameter(PRODUCTID)==null?"":request.getParameter(PRODUCTID));
		final String productId=(String)session.getAttribute("product_id");
		logger.info("productId: ======="+productId);
		 
		
		final String bankId1=bean.getBankId();
		logger.info("In COntroler  Bean File Id  IS====>>"+bankId1);
		logger.info("Product Id IS==========>>"+session.getAttribute("getProductId"));
		logger.info("In COntroler  Bean File Status  IS====>>"+bean.getStatus());
		logger.info("In COntroler  Bean File Remarks  IS====>>"+bean.getRemarks());
		
		
		if(bean.getFileType().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the File Type  <br>");
		}
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In file If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorUplodeFileEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorFile(branchCode, fileId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 logger.info(BRANCHCODE+branchCode+"fileId ID::"+ fileId+"Mode"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute  file Edit Query");
			 
			 logger.info("*****insertMotorFile*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayUplodeFile");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
 
	
	else if("displayMotorGroup".equalsIgnoreCase(requestFrom))
	{

		logger.info("enter to Motor File");
	 
		
		final String[][] result=bean.getMotorGroup(branchCode);
		logger.info(RESULTSIZE+result.length);
		ArrayList motorGroupList=null;
		try
		{
		 
		 motorGroupList= bean.getMotorGroupList(result);
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorGroupList Size++++++++"+motorGroupList.size()); 
	    request.setAttribute("motorGroupList",motorGroupList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorGroup.jsp");
		dispatcher.forward(request,response);
		 					
		
	}
	
	
	else if("editMotorGroup".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String groupId=request.getParameter(GROUPID);
		logger.info("groupId VALUE==>"+groupId);
	 
		
		final String[][] result=bean.getMotorGroupById(branchCode,groupId);
		
		if(result.length>0)
		{
		bean.setGroupId(result[0][0]);
		bean.setGroupNameEng(result[0][1]);
		bean.setStatus(result[0][2]);
		bean.setEffYear(result[0][3]);
		bean.setEffMonth(result[0][4]);
		bean.setEffDay(result[0][5]);
		bean.setRemarks(result[0][6]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean getGroupId====>"+bean.getGroupId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorGroupEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editMotorGroup--------");
	}
	
	
	else if("uplodeMotorGroup".equalsIgnoreCase(requestFrom))
	{
		 

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In RequestGroup Controller: "+mode);
		  dispatcher=null;
		
		  final String groupId=request.getParameter(GROUPID)==null?"":request.getParameter(GROUPID);
		logger.info("In COntroler RequestGroup Id  IS====>>"+groupId);
	 
		bean.setGroupId(request.getParameter(GROUPID)==null?"":request.getParameter(GROUPID));
		bean.setGroupNameEng(request.getParameter("groupNameEng")==null?"":request.getParameter("groupNameEng"));
		bean.setEffDay(request.getParameter(EFFECTIVEDATE)==null?"":request.getParameter(EFFECTIVEDATE));
		bean.setEffMonth(request.getParameter(EFFECTIVEMONTH)==null?"":request.getParameter(EFFECTIVEMONTH));
		bean.setEffYear(request.getParameter(EFFECTIVEYEAR)==null?"":request.getParameter(EFFECTIVEYEAR));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		 
		
		final String groupId1=bean.getGroupId();
		logger.info("In COntroler  Bean Group Id  IS====>>"+groupId1);
		
		logger.info("In COntroler  Bean Group Status  IS====>>"+bean.getStatus());
		logger.info("In COntroler  Bean Group Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getGroupNameEng().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Group Name(English) <br>");
		}	
		
		
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		logger.info("-----dd------->"+bean.getEffDay());
		logger.info("-----mm------->"+bean.getEffMonth());
		logger.info("-----yy------->"+bean.getEffYear());
		
		
		
		if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0)
				{
			
			final String groupId2=bean.getGroupId()==null?"":bean.getGroupId();
			final String result=bean.isVAlidGroupDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),groupId2,branchCode);
			if(result.equalsIgnoreCase("no"))
			{
				errorMessage.append(DATEVALIDATION);
			}


				}
			else if(message!=null)
				{
				errorMessage.append(message);
				}
		}
		
		
		 
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In group If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorGroupEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorGroup(branchCode, groupId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 logger.info(BRANCHCODE+branchCode+"groupId ID::"+ groupId+"Mode ghIs==>"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute group Edit Query");
			 
			 
			 logger.info("*****insertMotorGroup*********"+msg);
			 dispatcher = request.getRequestDispatcher("../servlet/MotorConfigController?requestFrom=displayMotorGroup");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	
	else if("displayMotorVoluntary".equalsIgnoreCase(requestFrom))
	{
		logger.info("enter to displayMotorCylinder config");
		final String value=request.getParameter("val")==null?"":request.getParameter("val");
		logger.info("......Seeeeee Problem MAy here........");
		
		
		 
		if(value.equalsIgnoreCase("displayProductIdist"))
		{
			final com.maan.admin.DAO.MotorBodyCreation bodybean = new com.maan.admin.DAO.MotorBodyCreation();
			logger.info("Inside displayProductIdist--- displayProductIdist");
	
			String productId=(String)session.getAttribute(PRODUCTID);
			if(productId==null)
			{
				productId="65";
			}
			final String[][] result=bodybean.getMotorBodyTypeId(branchCode,productId);
			request.setAttribute("bodyList",result);
			logger.info("t1");
			  dispatcher = request.getRequestDispatcher("../admin/MotorCylinder.jsp");
			logger.info("t1");
			dispatcher.forward(request,response);
			
		}
		

		logger.info("enter to Motor File");
	
		
		final String[][] result=bean.getMotorVoluntary(branchCode);
		logger.info(RESULTSIZE+result.length);
		ArrayList motorVoluntaryList=null;
		try
		{
		 
		 motorVoluntaryList= bean.getmotorVoluntaryList(result,pids);
		 
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorVoluntaryList Size++++++++"+motorVoluntaryList.size()); 
	    request.setAttribute("motorVoluntaryList",motorVoluntaryList);
		  dispatcher = request.getRequestDispatcher("../admin/MotorVoluntary.jsp");
		dispatcher.forward(request,response);
	}
	
	else if("editVoluntary".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		final String voluntaryId=request.getParameter(VOLUNTARYID);
		logger.info("voluntaryId VALUE==>"+voluntaryId);
	 
		if ("Edit".equalsIgnoreCase(mode)){
			final String[][] result=bean.getVoluntaryById(branchCode,voluntaryId);
		
		if(result.length>0)
		{
		bean.setVoluntaryId(result[0][0]);
		bean.setVoluntaryNAme(result[0][1]);
		bean.setStatus(result[0][2]);
		bean.setRemarks(result[0][3]);
		bean.setPolicyTypeId(result[0][4]);
		bean.setVoluntaryValue(result[0][5]);
		bean.setCoreAppCode(result[0][6]);
		}
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean getVoluntaryId====>"+bean.getVoluntaryId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorVoluntaryEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editVoluntary--------");
	}
	
	
	else if("uplodeVoluntary".equalsIgnoreCase(requestFrom))
	{
		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In voluntary Controller: "+mode);
		final String voluntaryId=request.getParameter(VOLUNTARYID)==null?"":request.getParameter(VOLUNTARYID);
		logger.info("In COntroler voluntary Id  IS====>>"+voluntaryId);
		bean.setVoluntaryId(request.getParameter(VOLUNTARYID)==null?"":request.getParameter(VOLUNTARYID));
		bean.setVoluntaryNAme(request.getParameter("voluntaryNAme")==null?"":request.getParameter("voluntaryNAme"));
		bean.setVoluntaryValue(request.getParameter("voluntaryValue")==null?"":request.getParameter("voluntaryValue"));
		bean.setCoreAppCode(request.getParameter(CORECODE)==null?"":request.getParameter(CORECODE));
		bean.setProductId(request.getParameter(PRODUCTID)==null?"":request.getParameter(PRODUCTID));
		bean.setStatus(request.getParameter(STATUS)==null?"":request.getParameter(STATUS));
		bean.setRemarks(request.getParameter(REMARKS)==null?"":request.getParameter(REMARKS));
		final String voluntaryId1=bean.getGroupId();
		logger.info("In COntroler  Bean Group Id  IS====>>"+voluntaryId1);
		logger.info("In COntroler  Bean   Status  IS====>>"+bean.getStatus());
		logger.info("In COntroler  Bean   Remarks  IS====>>"+bean.getRemarks());
		
		if(bean.getVoluntaryNAme().trim().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Voluntary Name <br>");
		}	
		if(bean.getVoluntaryValue().trim().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Voluntary Value <br>");
		}
		else
		{
			 
			flag=validate.isNumberValue(bean.getVoluntaryValue());
			if (!flag) 
			{
				errorMessage.append("Enter the valid Value <br>");
			}
		}
		
			
		if(bean.getStatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		if(errorMessage.length()>0)
		 {
			logger.info("hi Ram I am In Voluntary If Part");
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorVoluntaryEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorVoluntary(branchCode, voluntaryId,bean,mode);
					session.removeAttribute("doProcess");
				}
			 logger.info(BRANCHCODE+branchCode+"voluntaryId ID::"+ voluntaryId+"Mode Ihjs==>"+mode);
			 logger.info("hi Ram I am In Edit Part ANd going to execute voluntary Edit Query");
			 
			 
			 logger.info("*****insertMotorVoluntary*********"+msg);
			 dispatcher = request.getRequestDispatcher("../servlet/MotorConfigController?requestFrom=displayMotorVoluntary");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	else if("displayvehicleusage".equalsIgnoreCase(requestFrom))
	{
		
        logger.info("enter to Vehicle Usage  File");
		final String[][] result=bean.getVehicleFile(branchCode);
		logger.info(RESULTSIZE+result.length);
		System.out.println("Result length----->"+result.length);
		ArrayList motorVehicleFileListt=null;
		try
		{
		 			motorVehicleFileListt= bean.getVehicleUsageFileList(result);
		}  
		catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("++++++++Here Is motorUplodeFileListt Size++++++++"+motorVehicleFileListt.size()); 
	    request.setAttribute("motorVehicleFileListt",motorVehicleFileListt);
		dispatcher = request.getRequestDispatcher("../admin/VehicleUsageFile.jsp");
		dispatcher.forward(request,response); 
		
		
	}
	
	else if("editMotorVehicleFile".equalsIgnoreCase(requestFrom))
	{
		
		logger.info(FORMPROCESS);
		mode=request.getParameter(MODE);
		
		 String strvtypeId=request.getParameter(VTYPEID);
		logger.info("ID VALUE==>"+strvtypeId);
	 
		
		final String[][] result=bean.getMotorVehicleById(branchCode,strvtypeId);
		
		System.out.println("result.length---->"+result.length);
		if(result.length>0)
		{
			bean.setVtypeId(result[0][0]);
			bean.setVehicleType(result[0][1]);
			bean.setVehicledesc(result[0][2]);
			bean.setVehiclearabic(result[0][3]);
			bean.setVestatus(result[0][4]);
		}
    	request.setAttribute(MOTORCONFIGBEAN,bean);
		logger.info("bean strvtypeId===>"+bean.getVtypeId());
		  dispatcher=request.getRequestDispatcher("../admin/MotorVehicleEdit.jsp");
		 
		dispatcher.forward(request,response);
		logger.info("------------exit from editVehiclerType-------");
	}
	
	else if("UploadMotorVehicleFile".equalsIgnoreCase(requestFrom))
	{
		logger.info("UploadMotorVehicleFile");

		mode=request.getParameter(MODE)==null?"":request.getParameter(MODE);
		logger.info("Mode In Vehicle Controller: "+mode);
		  dispatcher=null;
		String strVehicleId=request.getParameter(VTYPEID)==null?"":request.getParameter(VTYPEID);
		logger.info("In COntroler Request Color Id IS====>>"+strVehicleId);
		bean.setVtypeId(request.getParameter(VTYPEID)==null?"":request.getParameter(VTYPEID));
		bean.setVehicleType(request.getParameter("vehicleType")==null?"":request.getParameter("vehicleType"));
		bean.setVehicledesc(request.getParameter("vehicledesc")==null?"":request.getParameter("vehicledesc"));
		bean.setVehiclearabic(request.getParameter("vehiclearabic")==null?"":request.getParameter("vehiclearabic"));
		bean.setVestatus(request.getParameter("vestatus")==null?"":request.getParameter("vestatus"));
		
		String strvtypeid=bean.getVtypeId();
		if(bean.getVehicledesc().equalsIgnoreCase(""))
		{
			errorMessage.append("Enter the Vehicle Description <br>");
		}	
		
		if(bean.getVehicleType().equalsIgnoreCase(""))
		{
			
			errorMessage.append("Enter the  Core Application Code <br>");
		}
		
		else  if(session.getAttribute("doProcess")!=null)
		{
			System.out.println("bean.getVehicleType()-inside if -->"+bean.getVehicleType());
		 strvtypeid=(strvtypeid==null||strvtypeid==""||strvtypeid.trim().length()<=0)?"0":strvtypeid;
			final int count=bean.getVehicleCount(branchCode,strvtypeid,bean.getVehicleType());
			if (count>0)
			{
				errorMessage.append("Entered Core Application Code Is Already Exist <br>");
			}
			 
			flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getVtypeId());
			if(!flag)
			{
			errorMessage.append(VALIDCODE);
			}
			
		}
		if(bean.getVestatus().equalsIgnoreCase(""))
		{
			errorMessage.append(VALIDSTATUS);
		}
		
		/*if((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
		{
			final String message = bean.isValidDate(bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
			if(message!=null && message.trim().length()<=0){
				final String colorId2=bean.getColorId()==null?"":bean.getColorId();
				final String result=bean.isVAlidColorDate((bean.getEffDay()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),colorId2,branchCode);
				if(result.equalsIgnoreCase("no")){
					errorMessage.append(DATEVALIDATION);
				}
			}else if(message!=null){
				errorMessage.append(message);
			}
		}*/
		if(errorMessage.length()>0)
		 {
			request.setAttribute(ERROR, errorMessage);	 
			dispatcher = request.getRequestDispatcher("../admin/MotorVehicleEdit.jsp?mode="+mode);
		 }
		 else
		 { 
			 String msg="";
			 if(session.getAttribute("doProcess")!=null){
				 msg= bean.insertMotorVehicle(branchCode, strvtypeid,bean,mode);
				 session.removeAttribute("doProcess");
				}
			 logger.info(BRANCHCODE+branchCode+"vtype ID::"+ strvtypeid+"Mode Is=--=>"+mode);
			 
			 logger.info("*****After insertMotorVehicle*********"+msg);
			 dispatcher = request.getRequestDispatcher("/servlet/MotorConfigController?requestFrom=displayvehicleusage");
	      }
		 
		 dispatcher.forward(request, response);
		  
	
	}
	
	
	
	
 }
		
}
