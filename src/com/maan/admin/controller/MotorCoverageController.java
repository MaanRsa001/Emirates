package com.maan.admin.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.admin.DAO.MotorCoverageDetailsDAO;
import com.maan.common.LogUtil;


public class MotorCoverageController extends HttpServlet {
	private static final Logger logger = LogUtil.getLogger(MotorCoverageController.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final MotorCoverageDetailsDAO mDAO = new MotorCoverageDetailsDAO();
	
	String coreappcode;
	String effDay;
	String effMon;
	String effYear;
	String expDay;
	String expMon;
	String expYear;
	String remarks;
	String rate;
	String groupId;
	String description;
	String status;
	String defaultselected;
	String addon;
	String deletable;
	
	
	public MotorCoverageController() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final HttpSession session=request.getSession(true);
		
		final String requestFrom=request.getParameter("requestFrom")==null?"":(String)request.getParameter("requestFrom");
		final String policyTypeId=request.getParameter("policyTypeId")==null?"":(String)request.getParameter("policyTypeId");
		final String policyTypeValue=request.getParameter("policyTypeValue")==null?"":(String)request.getParameter("policyTypeValue");
		final String branchCode=(String)session.getAttribute("LoginBranchCode");	
		final String typeOfBodyId=request.getParameter("typeOfBodyId")==null?"":(String)request.getParameter("typeOfBodyId");
		String typeOfBodyValue=request.getParameter("typeOfBodyValue")==null?"":(String)request.getParameter("typeOfBodyValue");
		final String opCoverId=request.getParameter("opCoverId")==null?"":(String)request.getParameter("opCoverId");
		
		mDAO.setBranchCode(branchCode);
		mDAO.setPolicyTypeId(policyTypeId);
		
		if("policyCoverList".equalsIgnoreCase(requestFrom)){
			final List result=mDAO.getPolicyCoverDetailList(policyTypeId,typeOfBodyId, branchCode);
			final String[][] bodyList = mDAO.getTypeOfBodyMaster(branchCode);
			if(bodyList!=null && bodyList.length>0){
				for(int i=0;i<bodyList.length;i++){
					if(typeOfBodyId.equalsIgnoreCase(bodyList[i][0]))
						typeOfBodyValue=bodyList[i][1];
				}
			}
			request.setAttribute("details",result);
			request.setAttribute("requestFrom", "policyCoverList");
		}else if("policyCoverEdit".equalsIgnoreCase(requestFrom) || "policyCoverAddmore".equalsIgnoreCase(requestFrom) ){
			final String policyTypeCoverId=request.getParameter("policyTypeCoverId")==null?"":(String)request.getParameter("policyTypeCoverId");
			request.setAttribute("requestFrom", "policyCoverDataScreen");
			if("policyCoverEdit".equalsIgnoreCase(requestFrom)){
				request.setAttribute("mode", "Edit");
				request.setAttribute("policyTypeCoverId", policyTypeCoverId);
			}else{
				request.setAttribute("opCoverId", opCoverId);	
				request.setAttribute("mode", "New");
			}
		}else if("policyCoverSubmit".equalsIgnoreCase(requestFrom)){
			final String mode=request.getParameter("mode")==null?"":(String)request.getParameter("mode");
			StringBuffer error=new StringBuffer();
			mDAO.setPolicyTypeCoverId(request.getParameter("policyTypeCoverId")==null?"":(String)request.getParameter("policyTypeCoverId"));
			mDAO.setCoreappcode(request.getParameter("coreappcode")==null?"":(String)request.getParameter("coreappcode"));
			mDAO.setEffDay(request.getParameter("effDay")==null?"":(String)request.getParameter("effDay"));
			mDAO.setEffMon(request.getParameter("effMon")==null?"":(String)request.getParameter("effMon"));
			mDAO.setEffYear(request.getParameter("effYear")==null?"":(String)request.getParameter("effYear"));
			mDAO.setExpDay(request.getParameter("expDay")==null?"":(String)request.getParameter("expDay"));
			mDAO.setExpMon(request.getParameter("expMon")==null?"":(String)request.getParameter("expMon"));
			mDAO.setExpYear(request.getParameter("expYear")==null?"":(String)request.getParameter("expYear"));
			mDAO.setRemarks(request.getParameter("remarks")==null?"":(String)request.getParameter("remarks"));
			mDAO.setRate(request.getParameter("rate")==null?"":(String)request.getParameter("rate"));
			mDAO.setGroupId(request.getParameter("groupId")==null?"":(String)request.getParameter("groupId"));
			mDAO.setDescription(request.getParameter("description")==null?"":(String)request.getParameter("description"));
			mDAO.setStatus(request.getParameter("status")==null?"":(String)request.getParameter("status"));
			mDAO.setDefaultselected(request.getParameter("defaultselected")==null?"":(String)request.getParameter("defaultselected"));
			mDAO.setAddon(request.getParameter("addon")==null?"":(String)request.getParameter("addon"));
			mDAO.setDeletable(request.getParameter("deletable")==null?"":(String)request.getParameter("deletable"));
			mDAO.setBranchCode(branchCode);
			mDAO.setOpcoverId(opCoverId);
			mDAO.setTypeOfBodyId(typeOfBodyId);
			mDAO.setPolicyTypeId(policyTypeId);
			mDAO.setCalcType(request.getParameter("calcType")==null?"":(String)request.getParameter("calcType"));
			
			logger.info("opCoverId: "+opCoverId+" policyTypeCoverId: "+mDAO.getPolicyTypeCoverId()+" mode: "+mode+" coreappcode: "+coreappcode+" effDay: "+effDay+" effMon: "+effMon+" effYear: "+effYear+" expDay: "+expDay+" expMon: "+expMon+" expYear: "+expYear+" remarks: "+remarks+" rate: "+rate+" groupId: "+groupId+" description: "+description+" status: "+status+" defaultselected: "+defaultselected+" addon: "+addon+" deletable: "+deletable);
			logger.info("policyTypeId: "+policyTypeId+" policyTypeValue: "+policyTypeValue+" typeOfBodyId: "+typeOfBodyId+" typeOfBodyValue: "+typeOfBodyValue);
			
			error=mDAO.validateCoverDetails(mDAO,error,mode);
			if(mDAO.getCalcType().equalsIgnoreCase("G")){
				//String result[][]=new CoverageBean().getGridDetails("65", policyTypeId, typeOfBodyId, opCoverId,"0");
				//if(result==null || result.length<=0)
				error.append("<br>Please Provide Base Rate Details for Grid Option");
			}
			if(error.length()>0){
				request.setAttribute("error", error);
				request.setAttribute("submitStatus", "Yes");
				request.setAttribute("mode", mode);
				request.setAttribute("policyTypeCoverId", mDAO.getPolicyTypeCoverId());
				request.setAttribute("requestFrom", "policyCoverDataScreen");
			}else{
				if(session.getAttribute("doProcess")!=null){
					mDAO.insertCoverageDetails(mDAO,mode);
					session.removeAttribute("doProcess");
				}
				List result=mDAO.getPolicyCoverDetailList(policyTypeId,typeOfBodyId, branchCode);
				if(result.size()>0){
					MotorCoverageDetailsDAO mv =(MotorCoverageDetailsDAO)result.get(0);
					typeOfBodyValue=mv.getTypeOfBody();
				}
				request.setAttribute("details",result);
				request.setAttribute("requestFrom", "policyCoverList");
			}
		}
		request.setAttribute("policyTypeId", policyTypeId);
		request.setAttribute("typeOfBodyId", typeOfBodyId);
		request.setAttribute("typeOfBodyValue", typeOfBodyValue);
		request.setAttribute("policyTypeValue", policyTypeValue);
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/MotorCoverageDetails.jsp");
		dispatcher.forward(request, response);
	}

	public void init() throws ServletException {
	}

}
