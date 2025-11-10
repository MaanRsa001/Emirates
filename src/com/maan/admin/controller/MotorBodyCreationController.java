package com.maan.admin.controller;
import java.io.IOException;

import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.maan.admin.DAO.MakeBean;
import com.maan.admin.DAO.MotorBodyCreation;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.services.util.ValidationFormat;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;


public class MotorBodyCreationController extends HttpServlet {
	private static final Logger logger = LogUtil.getLogger(MotorBodyCreationController.class);

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try{
				processResult(request, response);
			} catch(BaseException e) {
				logger.debug(e);
			}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}
	public void processResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BaseException 
	{
		
		HttpSession session = request.getSession(true);
		
		final String branchCode= session.getAttribute("LoginBranchCode")==null?"":(String)session.getAttribute("LoginBranchCode");
		MakeBean bean=new MakeBean();
		logger.info((String)request.getParameter("requestFrom"));
		logger.info((String)request.getParameter("val"));
		if(session.getAttribute("ses")==null)
		{
				response.sendRedirect("../login/error_messg.jsp");
		}
		session = request.getSession(true);
		
		try{
			request.setCharacterEncoding("Cp1256");
			response.setContentType("text/html; charset=windows-1256");
		}catch(Exception e){
			e.printStackTrace();
		}
			
		MotorBodyCreation motorBody= new MotorBodyCreation();
		String msg="";
		motorBody.setTypeName(request.getParameter("typevalues")==null?"":request.getParameter("typevalues"));	
		
		if("motorthird".equalsIgnoreCase((String)request.getParameter("motorthird"))){ 
			try{
				logger.info((String)request.getParameter("typeID"));
				session.removeAttribute("typeid");
				session.setAttribute("typeid",(String)request.getParameter("typeID"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/MotorThirdParty.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();  
			}
		}
		
		if("makeconfig".equalsIgnoreCase((String)request.getParameter("requestFrom"))){
			logger.info("enter to make config");
			 
			String productId=(String)session.getAttribute("productid");
			if(productId==null){
				productId="65";
			}
			String[][] result=motorBody.getMotorMake(branchCode);
			logger.info("Result:"+ result);
			logger.info("Result size:"+result.length);
			ArrayList makeList=null;
			try{
				 logger.info("Result:1"); 
				 makeList= motorBody.getMakeList(result);
				 logger.info("Result:2");
			}catch(Exception e){
				e.printStackTrace();
			}
		    request.setAttribute("makeList",makeList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/MakeConfig.jsp");
			dispatcher.forward(request,response);
		}
		
		if("editmakeconfig".equalsIgnoreCase((String)request.getParameter("requestFrom"))){
			logger.info("entry to editmakeconfig");
			String mode=request.getParameter("mode");
			
			String makeId=request.getParameter("makeid");
			logger.info("makeId value"+makeId);
			 
			String[][] result=motorBody.getMakeEdit(branchCode,makeId);			
			if(result!=null && result.length>0){
				bean.setMakeId(result[0][0]==null?"":result[0][0]);
				bean.setMakeName(result[0][1]==null?"":result[0][1]);
				bean.setMakeArabic(result[0][2]==null?"":result[0][2]);
				bean.setCoreCode(result[0][3]==null?"":result[0][3]);
				bean.setStatus(result[0][4]==null?"":result[0][4]);
				bean.setEffYear(result[0][5]==null?"":result[0][5]);
		    	bean.setEffMonth(result[0][6]==null?"":result[0][6]);
		    	bean.setEffDate(result[0][7]==null?"":result[0][7]);
		    	bean.setRemarks(result[0][8]==null?"":result[0][8]);
		    	bean.setReferalStatus(result[0][9]==null?"":result[0][9]);
			}
	    	 
			request.setAttribute("makeBean",bean);
			logger.info("bean makeid"+bean.getMakeId());
			RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/MakeEdit.jsp");
			dispatcher.forward(request,response);
			logger.info("exit from editmakeconfig");
		}
		
		if("updateconfig".equalsIgnoreCase((String)request.getParameter("requestFrom"))){
			String mode=request.getParameter("mode")==null?"":request.getParameter("mode");
			logger.info("Mode In Controller: "+mode);
			RequestDispatcher dispatcher=null;
			
			String makeId=request.getParameter("makeID")==null?"":request.getParameter("makeID");
			 
			bean.setMakeName(request.getParameter("makename_eng")==null?"":request.getParameter("makename_eng"));
			bean.setMakeArabic(request.getParameter("makename_arab")==null?"":request.getParameter("makename_arab"));
			bean.setCoreCode(request.getParameter("corecode")==null?"":request.getParameter("corecode"));
			bean.setEffDate(request.getParameter("effDay")==null?"":request.getParameter("effDay"));
			bean.setEffMonth(request.getParameter("effMon")==null?"":request.getParameter("effMon"));
			bean.setEffYear(request.getParameter("effYear")==null?"":request.getParameter("effYear"));
			bean.setStatus(request.getParameter("status")==null?"":request.getParameter("status"));
			bean.setReferalStatus(request.getParameter("refstatus")==null?"":request.getParameter("refstatus"));
			bean.setRemarks(request.getParameter("remarks")==null?"":request.getParameter("remarks"));
			StringBuffer sb=new StringBuffer("");
			if(bean.getMakeName().equalsIgnoreCase("")){
				sb.append("Enter the Make Name(English) <br>");
			}	
			
			if(bean.getCoreCode().equalsIgnoreCase("")){
				sb.append("Enter the Core Code <br>");
				logger.info("eeeeeeeeeeeeee");
			}else{
				logger.info("tttttttt");
				ValidationFormat validate=new ValidationFormat();
				boolean flag=validate.IsLetterOrDigitWithSpaceValidationFormat(bean.getCoreCode());
				logger.info("Core Code::::::"+bean.getCoreCode());
				if(!flag){
					sb.append("Enter the Valid Code <br>");
				}
			}
			
			if(bean.getStatus().equalsIgnoreCase("")){
				sb.append("Select the Status <br>");
			}
			
			if(bean.getReferalStatus().equalsIgnoreCase("")){
				sb.append("Select the Referal Status <br>");
			}
			
			if((bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
			{
				final String message =motorBody.isValidDate(bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
				if(message!=null && message.trim().length()<=0){
					String result=motorBody.isVAlidMakeDate((bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),makeId,branchCode);
					if(result.equalsIgnoreCase("no")){
						sb.append("Effective date should be greater or equal to previous effective date <br>");
					}
				}else if(message!=null){
					sb.append(message);
				}
			}
			
			logger.info(""+sb);
			if(sb.length()>0){
				logger.info("Make ID:"+makeId);
				request.setAttribute("error", sb);	 
				dispatcher = request.getRequestDispatcher("../admin/MakeEdit.jsp?mode="+mode);
			}else{ 
				logger.info("Branch Code::"+branchCode+"Make ID::"+ makeId);
				if(session.getAttribute("doProcess")!=null){
					msg=motorBody.insertMakeEdit(branchCode, makeId,bean,mode);
					session.removeAttribute("doProcess");
				}				  
				dispatcher = request.getRequestDispatcher("/servlet/MotorBodyController?requestFrom=makeconfig");
		    }
			 
			if(dispatcher!=null) 
			dispatcher.forward(request, response);
		}
		
		if("modelconfig".equalsIgnoreCase((String)request.getParameter("requestFrom"))){
			logger.info("enter to model config");
			String value=request.getParameter("val")==null?"":request.getParameter("val");
			if(value.equalsIgnoreCase("config")){			
				String productId=(String)session.getAttribute("productid");
				if(productId==null){
					productId="65";
				}
				String[][] result=motorBody.getMotorMake(branchCode);
				request.setAttribute("make",result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/ModelConfig.jsp");
				dispatcher.forward(request,response);
			}else if(value.equalsIgnoreCase("display")){
				logger.info("Entry to display config");
				
				String makeId=request.getParameter("makeID");
				String [][]modelEdit=motorBody.getModelDisplay(branchCode, makeId);
				int f =(int) modelEdit.length;
				logger.info("Array size is==="+f);
				
				ArrayList modelEditList=null;
				try{
					modelEditList=motorBody.getModelEditList(modelEdit);
					logger.info("In Controler List Size==="+modelEditList.size());
					final String[][] makeList = motorBody.getMakeEdit(branchCode, makeId); 
					final String makeType=motorBody.getTypeOfMake(branchCode, makeId);
					request.setAttribute("MakeType", makeType);
					request.setAttribute("makeId", makeId);
					
					if(makeList!=null && makeList.length>0){
						request.setAttribute("makeName1", makeList[0][1]);
						logger.info("Make nameklshfs: "+ makeList[0][1]);
					}					
				}catch(Exception e){
					e.printStackTrace();
				}
				logger.info(bean.getModelId());
			    request.setAttribute("modelEditList",modelEditList);
			    request.setAttribute("makeId", makeId);
			    logger.info("makeId: "+makeId);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/ModelConfigDisplay.jsp");
				dispatcher.forward(request,response);
				logger.info("-----------------Exit from display model config-----------");				
			}
		}		
					
		if("editmodelconfig".equalsIgnoreCase((String)request.getParameter("requestFrom"))){
			String makeId=request.getParameter("makeID");
			String modelId =request.getParameter("modelId");
			String mode = request.getParameter("mode");
			logger.info("entry to editmodelconfig with mode=>"+mode);
			
			String bodyId[][]=motorBody.getBodyId(branchCode);
			if("Edit".equalsIgnoreCase(mode)){
				
				final String makeId3=request.getParameter("makeID");
				 String makeType=motorBody.getTypeOfMake(branchCode, makeId3);
				if(makeType.equals(null)||makeType.equals("")){
					makeType=request.getParameter("makeType");
				}
				logger.info("77777777777777"+makeType);
				request.setAttribute("MakeType", makeType);
				
				String[][] result=motorBody.getModelEdit(branchCode,makeId,modelId);  
				if(result.length>0){
					bean.setModelId(result[0][0]==null?"":result[0][0]);
					bean.setMakeId(result[0][1]==null?"":result[0][1]);
					
					bean.setModelName(result[0][2]==null?"":result[0][2]);
					bean.setModelArabic(result[0][3]==null?"":result[0][3]);
					bean.setCoreCode(result[0][4]==null?"":result[0][4]);
					bean.setEffYear(result[0][5]==null?"":result[0][5]);
					bean.setEffMonth(result[0][6]==null?"":result[0][6]);
					bean.setEffDate(result[0][7]==null?"":result[0][7]);
					bean.setStatus(result[0][8]==null?"":result[0][8]);
					bean.setBodyId(result[0][9]==null?"":result[0][9]);
					bean.setReferalStatus(result[0][10]==null?"":result[0][10]);
					bean.setCategoryId(result[0][11]==null?"":result[0][11]);
					bean.setRemarks(result[0][12]==null?"":result[0][12]);
				}
				logger.info("bodyId ..."+bodyId);
				request.setAttribute("makeBean",bean);
			}	
			logger.info("makeID: "+makeId);
			request.setAttribute("bodyId",bodyId);
			RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/ModelConfigEdit.jsp?mode="+mode+"&makeID="+makeId);
			dispatcher.forward(request,response);
			logger.info("exit from editModelconfig");
		}
		
		if ("submitModelconfig".equalsIgnoreCase((String)request.getParameter("requestFrom"))){
				String mode=request.getParameter("mode")==null?"":request.getParameter("mode");
				RequestDispatcher dispatcher=null;
				
				bean.setMakeName(request.getParameter("makename_eng")==null?"":request.getParameter("makename_eng"));
				bean.setModelName(request.getParameter("modelname_eng")==null?"":request.getParameter("modelname_eng"));
				bean.setModelArabic(request.getParameter("modelname_arabic")==null?"":request.getParameter("modelname_arabic"));
				bean.setMakeArabic(request.getParameter("makename_arab")==null?"":request.getParameter("makename_arab"));
				bean.setCoreCode(request.getParameter("corecode")==null?"":request.getParameter("corecode"));
				bean.setEffDate(request.getParameter("effDay")==null?"":request.getParameter("effDay"));
				bean.setEffMonth(request.getParameter("effMon")==null?"":request.getParameter("effMon"));
				bean.setEffYear(request.getParameter("effYear")==null?"":request.getParameter("effYear"));
				bean.setStatus(request.getParameter("status")==null?"":request.getParameter("status"));
				bean.setReferalStatus(request.getParameter("rstatus")==null?"":request.getParameter("rstatus"));
				bean.setRemarks(request.getParameter("remarks")==null?"":request.getParameter("remarks"));
				bean.setCategoryId(request.getParameter("categoryId")==null?"":request.getParameter("categoryId"));
				bean.setBodyId(request.getParameter("bodyId")==null?"":request.getParameter("bodyId"));
				bean.setMakeId(request.getParameter("makeID")==null?"":request.getParameter("makeID"));
				bean.setModelId(request.getParameter("modelId")==null?"":request.getParameter("modelId"));
				final String makeId4=request.getParameter("makeID");
				logger.info("Make ID==>"+makeId4);
				final String makeType=motorBody.getTypeOfMake(branchCode, makeId4);
				logger.info("Make Name==>"+makeType);
				request.setAttribute("MakeType", makeType);
				
				StringBuffer sb=new StringBuffer("");
				if(bean.getModelName().equalsIgnoreCase("")){
					sb.append("Enter the Model Name(English) <br>");
				}	
				
				if(bean.getCoreCode().equalsIgnoreCase("")){
					sb.append("Enter the Core Code <br>");
				}
				
				if(bean.getStatus().equalsIgnoreCase("")){
					sb.append("Select the Status <br>");
				}
				if(bean.getCategoryId().equalsIgnoreCase("")){
					sb.append("Enter Category Id <br>");
				}else{
					ValidationFormat validate=new ValidationFormat();
					boolean flag=validate.IsDigitValidationFormat(bean.getCategoryId());
					if(!flag){
						logger.info("uuuuuuuuuuuuuuuuuuu");
						sb.append("Enter the Valid Id <br>");
					}
				}
				if(bean.getReferalStatus().equalsIgnoreCase("")){
					sb.append("Select Refral Status <br>");
				}
				if(bean.getRemarks().equalsIgnoreCase("")){
					sb.append("Enter Remarks <br>");
				}
				
				
				if((bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!=null ||(bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear())!="" )
				{
					final String message =motorBody.isValidDate(bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear());
					if(message!=null && message.trim().length()<=0){
						String modelId=bean.getModelId();
						String result=motorBody.isVAlidModelDate((bean.getEffDate()+"-"+bean.getEffMonth()+"-"+bean.getEffYear()),modelId,branchCode);
						if(result.equalsIgnoreCase("no")){
							sb.append("Effective date should be greater or equal to previous effective date <br>");
						}
					}else if(message!=null){
						sb.append(message);
					}
					
				}
				
				logger.info("sTRING BUFFER....."+sb);
				if(sb.length()>0){					
					String bodyId[][]=motorBody.getBodyId(branchCode);
					logger.info("MOde Is=====>>"+mode);
					
					request.setAttribute("bodyId",bodyId);
					request.setAttribute("error", sb);	 
					dispatcher = request.getRequestDispatcher("../admin/ModelConfigEdit.jsp?mode="+mode);
				}else{  
					if(session.getAttribute("doProcess")!=null){
						msg=motorBody.insertModelEdit( branchCode, mode ,bean);
						session.removeAttribute("doProcess");
					}
					
					dispatcher = request.getRequestDispatcher("/servlet/MotorBodyController?requestFrom=modelconfig&val=display&makeID="+bean.getMakeId());
				}
				dispatcher.forward(request, response);
		}
		
		if("insert".equalsIgnoreCase(request.getParameter("motorbodyform"))){ 
			RequestDispatcher dispatcher=null;
			try{
				String mode="";
				if(request.getParameter("mode")!=null && request.getParameter("mode").length()!=0 ){
					mode="edit";
				}else{
					mode="insert";
				}
				
				logger.info("Type: "+motorBody.getTypeId());
					
				motorBody.setTypeId(request.getParameter("typeid")==null?"":request.getParameter("typeid"));
				if(motorBody.getTypeId()==null || ("".equalsIgnoreCase(motorBody.getTypeId()))){
					motorBody.setTypeId(request.getParameter("bodyId")==null?"":request.getParameter("bodyId"));
				}
			
				logger.info("Type New: "+motorBody.getTypeId());
			
				motorBody.setProductId(request.getParameter("productId")==null?"":request.getParameter("productId"));
				motorBody.setBranchCode(request.getParameter("branchCode")==null?"":request.getParameter("branchCode"));
				motorBody.setAppCode(request.getParameter("appcode")==null?"":request.getParameter("appcode"));
				motorBody.setBody_name_eng(request.getParameter("body_name_eng")==null?"":request.getParameter("body_name_eng"));
				motorBody.setBody_name_arab(request.getParameter("body_name_arab")==null?"":request.getParameter("body_name_arab"));
				motorBody.setYears(request.getParameter("years")==null?"":request.getParameter("years"));
				motorBody.setLiability(request.getParameter("liability")==null?"":request.getParameter("liability"));
				motorBody.setStatus(request.getParameter("status")==null?"":request.getParameter("status"));
				motorBody.setEffDay(request.getParameter("effDay")==null?"":request.getParameter("effDay"));
				motorBody.setEffMon(request.getParameter("effMon")==null?"":request.getParameter("effMon"));
				motorBody.setEffYear(request.getParameter("effYear")==null?"":request.getParameter("effYear"));
				request.setAttribute("motorBody", motorBody);
				StringBuffer sb=new StringBuffer("");
		
				if(motorBody.getAppCode().equalsIgnoreCase("")){
					sb.append("Enter the Application Code<br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getAppCode()))
						sb.append("Enter the Valid Number for Application Code <br>");
				}
			
				if(motorBody.getBody_name_eng().equalsIgnoreCase("")){
					sb.append("Enter the Body Name(English) <br>");
				}
				if(motorBody.getYears().equalsIgnoreCase("")){
					sb.append("Enter the No. Of Years Allowed <br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getYears()))
						sb.append("Enter the Valid Number for No. Of Years <br>");
				}
				if(motorBody.getLiability().equalsIgnoreCase("")){
					sb.append("Enter the Third Party Liability <br>");
				}else{
					if(motorBody.containsOnlyDecimalNumbers(motorBody.getLiability()))
						sb.append("Enter the Valid Number for ThirdParty Liability <br>");
				}
				motorBody.setReqFrom("motorbodyform");
				sb.append(motorBody.isValidDate(motorBody.getEffDay()+"-"+motorBody.getEffMon()+"-"+motorBody.getEffYear()));
				logger.info(""+sb);
				if(sb.length()>0){
					request.setAttribute("error", sb);	 
				 	dispatcher = request.getRequestDispatcher("../admin/MotorBody.jsp?mode=edit");
				}
				else{ 
					if(session.getAttribute("doProcess")!=null){
						msg=motorBody.isertOrUpdateBodyFormData(mode);
						session.removeAttribute("doProcess");
					}
				    dispatcher = request.getRequestDispatcher("../admin/MotorBody.jsp?mode=display");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
		}
		
		if("motormodel".equalsIgnoreCase((String)request.getParameter("motormodel"))){
			try{
				logger.info("Model Id"+(String)request.getParameter("ModelID"));
				session.setAttribute("modelid",(String)request.getParameter("ModelID"));
				motorBody.setModelid(request.getParameter("ModelID"));
				logger.info("bean value"+motorBody.getModelid());
				session.setAttribute("typeNames", motorBody.getTypeName());
				RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/ModelConfig.jsp?mode=edit");
			    dispatcher.forward(request, response);
			    return;
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		}
		
		if("motorbody".equalsIgnoreCase((String)request.getParameter("motorbody")))
		{
			try
		    {
				logger.info((String)request.getParameter("typeID"));
				session.removeAttribute("typeid");
				session.setAttribute("typeid",(String)request.getParameter("typeID"));
				session.setAttribute("typeNames", motorBody.getTypeName());
				RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/MotorBodyRate.jsp?mode=edit");
			    dispatcher.forward(request, response);
			    return;
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		}
		
	
		else if("insert".equalsIgnoreCase(request.getParameter("motorthirdpartyform"))){  
			logger.info("inside the if block************");
			try{
				String mode="";
			
				if(request.getParameter("modes")!=null && request.getParameter("modes").length()!=0 ){
					mode="edit";
					request.setAttribute("modes", "Edit");
				}else{
					mode="insert";
				}
			
				logger.info("inside the try block"+mode);
				motorBody.setProductId(request.getParameter("productId")==null?"":request.getParameter("productId"));
				motorBody.setBranchCode(request.getParameter("branchCode")==null?"":request.getParameter("branchCode"));
				motorBody.setTypeId(request.getParameter("typeID")==null?"":request.getParameter("typeID"));
				if(motorBody.getTypeId()==null || "".equalsIgnoreCase(motorBody.getTypeId())){	
					motorBody.setTypeId(request.getParameter("typeofbodyid")==null?"":request.getParameter("typeofbodyid"));
				}
				motorBody.setThirdCoreAppCode(request.getParameter("thirdCoreAppCode")==null?"":request.getParameter("thirdCoreAppCode"));
				motorBody.setSeatingCyll(request.getParameter("seatingCyll")==null?"":request.getParameter("seatingCyll"));
				motorBody.setThirdRate(request.getParameter("thirdRate")==null?"":request.getParameter("thirdRate"));
				motorBody.setStatus(request.getParameter("status")==null?"":request.getParameter("status"));
				motorBody.setEffDay(request.getParameter("effDay")==null?"":request.getParameter("effDay"));
				motorBody.setEffMon(request.getParameter("effMon")==null?"":request.getParameter("effMon"));
				motorBody.setEffYear(request.getParameter("effYear")==null?"":request.getParameter("effYear"));
				String thirdpartyid=request.getParameter("thirdpartyid")==null?"":request.getParameter("thirdpartyid");
				request.setAttribute("motorBody", motorBody);
				StringBuffer sb=new StringBuffer("");
				/*if(motorBody.getThirdCoreAppCode().equalsIgnoreCase("")){
					sb.append("Enter the Application Code<br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getThirdCoreAppCode()))
						sb.append("Enter the Valid Number for Application Code <br>");
				}*/
			
				if(motorBody.getSeatingCyll().equalsIgnoreCase("")){
					sb.append("Enter the No. Of Seating Cyllanders <br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getSeatingCyll()))
						sb.append("Enter the Valid Number for Seating Cyllanders <br>");
				}
				if(motorBody.getThirdRate().equalsIgnoreCase("")){
					sb.append("Enter the Third PartyRate <br>");
				}else{
					if(motorBody.containsOnlyDecimalNumbers(motorBody.getThirdRate()))
						sb.append("Enter the Valid Number for Third Party Rate <br>");
				}
				sb.append(motorBody.isValidDate(motorBody.getEffDay()+"-"+motorBody.getEffMon()+"-"+motorBody.getEffYear()));
				sb.append(motorBody.checkThirdPartyExistence());
				if(sb.length()==0){
					if(session.getAttribute("doProcess")!=null){
						motorBody.isertOrUpdateThirdPartyFormData(mode,thirdpartyid);
						session.removeAttribute("doProcess");
					}
					logger.info("after insertion result is "+msg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/MotorThirdParty.jsp?mode=view&typeid="+motorBody.getTypeId()+"");
					dispatcher.forward(request, response);
			    }else{
					logger.info("motorBody.getTypeId(): "+motorBody.getTypeId());
					request.setAttribute("1typeid", motorBody.getTypeId());
					request.setAttribute("error", sb);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/MotorThirdParty.jsp?mode=new");
					dispatcher.forward(request, response);
			    }
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		
		else if("insert".equalsIgnoreCase(request.getParameter("motorbodylimit")))
		{   
			
			try
		    {
				String mode="";
				if(request.getParameter("mode")!=null && request.getParameter("mode").length()!=0 ){
					mode="edit";
				}else{
					mode="insert";
				}
				logger.info("inside the try block"+mode);
				String bodyid=(String)request.getParameter("bodyid")==null?"":(String)request.getParameter("bodyid");
				
				motorBody.setBrokerId(request.getParameter("brokerId")==null?"":request.getParameter("brokerId"));
				
				motorBody.setProductId(request.getParameter("productId")==null?"65":request.getParameter("productId"));
				motorBody.setBranchCode(request.getParameter("branchCode")==null?"":request.getParameter("branchCode"));
				motorBody.setTypeId(request.getParameter("typeID")==null?"":request.getParameter("typeID"));
				motorBody.setStartage(request.getParameter("startage")==null?"":request.getParameter("startage"));
				motorBody.setEndage(request.getParameter("endage")==null?"":request.getParameter("endage"));
				motorBody.setLicperiod(request.getParameter("licperiod")==null?"":request.getParameter("licperiod"));
				motorBody.setLicendperiod(request.getParameter("licendperiod")==null?"":request.getParameter("licendperiod"));
				motorBody.setComboName(request.getParameter("comboName")==null?"":request.getParameter("comboName"));
				motorBody.setMake(request.getParameter("make")==null?"":request.getParameter("make"));
				motorBody.setModelname(request.getParameter("modelname")==null?"":request.getParameter("modelname"));
				motorBody.setModelid(request.getParameter("modelid")==null?"Nil":request.getParameter("modelid"));
				motorBody.setStatus(request.getParameter("status")==null?"":request.getParameter("status"));
				motorBody.setEffDay(request.getParameter("effDay")==null?"":request.getParameter("effDay"));
				motorBody.setEffMon(request.getParameter("effMon")==null?"":request.getParameter("effMon"));
				motorBody.setEffYear(request.getParameter("effYear")==null?"":request.getParameter("effYear"));
				motorBody.setVehicleId(request.getParameter("vehicleId")==null?"":request.getParameter("vehicleId"));
				logger.info("type id motorbodylimit: "+request.getParameter("typeID"));
				logger.info("typeid: "+request.getParameter("typeid"));
				request.setAttribute("motorBody", motorBody);
				StringBuffer sb=new StringBuffer("");
				logger.info("modelid is in controller"+motorBody.getModelid());
				if(motorBody.getStartage().equalsIgnoreCase(""))
				{
					sb.append("Enter the Start Age<br>");
				}
				else
				{
					if(motorBody.containsOnlyNumbers(motorBody.getStartage()))
						sb.append("Enter the Valid Number for Start Age<br>");
				}
				if(motorBody.getEndage().equalsIgnoreCase(""))
				{
					sb.append("Enter the End Age <br>");
				}
				else
				{
					if(motorBody.containsOnlyNumbers(motorBody.getEndage()))
						sb.append("Enter the Valid Number for End Age<br>");
				}
				if(motorBody.getLicperiod().equalsIgnoreCase(""))
				{
					sb.append("Enter the Start License Period <br>");
				}
				else
				{
					if(motorBody.containsOnlyNumbers(motorBody.getLicperiod()))
						sb.append("Enter the Valid Number for Start License Period <br>");
				}
				if(motorBody.getLicendperiod().equalsIgnoreCase(""))
				{
					sb.append("Enter the End License Period <br>");
				}
				else
				{
					if(motorBody.containsOnlyNumbers(motorBody.getLicendperiod()))
						sb.append("Enter the Valid Number for End License Period <br>");
				}
				sb.append(motorBody.isValidDate(motorBody.getEffDay()+"-"+motorBody.getEffMon()+"-"+motorBody.getEffYear()));
				logger.info("sblength"+sb.length());
				if(sb.length()==0)
				{
				if(Integer.parseInt(motorBody.getLicperiod())>Integer.parseInt(motorBody.getLicendperiod()))
				{
					sb.append("Start Period Must Less Than End Period<br>");
				}
				if(Integer.parseInt(motorBody.getStartage())>Integer.parseInt(motorBody.getEndage()))
				{
					sb.append("Driver Start Age Must Less Than End Age<br>");
				}
				sb.append(motorBody.checkMotorBodyLimtExistence(mode));
				}
				if(sb.length()>0){
					logger.info("hello"+sb);
					logger.info("hellomode"+request.getAttribute("mode"));
					request.setAttribute("error", sb);	
					request.removeAttribute("mode");
					logger.info("hellomode"+request.getAttribute("mode"));
					RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/MotorBodyLimit.jsp");
					dispatcher.forward(request, response);					
				}else{
					logger.info("helloelse"+sb);
					if(session.getAttribute("doProcess")!=null){
						motorBody.isertOrUpdateMotorBodyLimit(mode, bodyid);
						session.removeAttribute("doProcess");
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/MotorBodyRate.jsp?mode=edit");
					dispatcher.forward(request, response);
				}
			 }catch(Exception e){
				e.printStackTrace();
			 }	
		}
		
		else if("insert".equalsIgnoreCase(request.getParameter("motorbodyrate")))
		{   
			
			try
			{
				String mode="";
				if(request.getParameter("mode")!=null && request.getParameter("mode").length()!=0 ){
					mode="edit";
				}else{
					mode="insert";
				}
				logger.info("---> Type Id: "+request.getParameter("typeID"));
				logger.info("Type Body Id: "+request.getParameter("typeofbodyid"));
				
				String motortyperate=request.getParameter("typerate");
				logger.info("inside the try blockfsdgfdgdfg"+mode);
				motorBody.setProductId(request.getParameter("productId")==null?"":request.getParameter("productId"));
				motorBody.setBranchCode(request.getParameter("branchCode")==null?"":request.getParameter("branchCode"));
				motorBody.setTypeId(request.getParameter("typeID")==null?"":request.getParameter("typeID"));
				if(motorBody.getTypeId()==null || "".equalsIgnoreCase(motorBody.getTypeId())){	
					motorBody.setTypeId(request.getParameter("typeofbodyid")==null?"":request.getParameter("typeofbodyid"));
				}
				
				motorBody.setPolicyTypeId(request.getParameter("policyTypeId")==null?"":request.getParameter("policyTypeId"));
				motorBody.setVehicleId(request.getParameter("vehicleId")==null?"":request.getParameter("vehicleId"));
				motorBody.setAgencyRepairYear(request.getParameter("agencyRepairYear")==null?"":request.getParameter("agencyRepairYear"));
				motorBody.setRate(request.getParameter("rate")==null?"":request.getParameter("rate"));
				motorBody.setStartsum(request.getParameter("startsum")==null?"":request.getParameter("startsum"));
				motorBody.setEndsum(request.getParameter("endsum")==null?"":request.getParameter("endsum"));
				motorBody.setSeatingstart(request.getParameter("seatingstart")==null?"":request.getParameter("seatingstart"));
				motorBody.setSeatingend(request.getParameter("seatingend")==null?"":request.getParameter("seatingend"));
				motorBody.setMinpremium(request.getParameter("minpremium")==null?"":request.getParameter("minpremium"));
				motorBody.setStatus(request.getParameter("status")==null?"":request.getParameter("status"));
				motorBody.setEffDay(request.getParameter("effDay")==null?"":request.getParameter("effDay"));
				motorBody.setEffMon(request.getParameter("effMon")==null?"":request.getParameter("effMon"));
				motorBody.setEffYear(request.getParameter("effYear")==null?"":request.getParameter("effYear"));
				request.setAttribute("motorBody", motorBody);
				StringBuffer sb=new StringBuffer("");
				if(motorBody.getAgencyRepairYear().equalsIgnoreCase("")){
					sb.append("Enter the Agency Repair<br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getAgencyRepairYear()))
						sb.append("Enter the Valid Number for Agency Repair Year <br>");
				}
				
				if(motorBody.getRate().equalsIgnoreCase("")){
					sb.append("Enter the Rate <br>");
				}else{
					if(motorBody.containsOnlyDecimalNumbers(motorBody.getRate()))
						sb.append("Enter the Valid Number for Rate<br>");
				}
				if(motorBody.getStartsum().equalsIgnoreCase("")){
					sb.append("Enter the Start Sum <br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getStartsum()))
						sb.append("Enter the Valid Number for Start Sum <br>");
				}
				if(motorBody.getEndsum().equalsIgnoreCase("")){
					sb.append("Enter the End Sum <br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getEndsum()))
						sb.append("Enter the Valid Number for End Sum<br>");
				}
				if(motorBody.getMinpremium().equalsIgnoreCase("")){
					sb.append("Enter the Minimum Premium<br>");
				}else{
					if(motorBody.containsOnlyNumbers(motorBody.getMinpremium()))
						sb.append("Enter the Valid Number for Minimum Premium <br>");
				}
			
				sb.append(motorBody.isValidDate(motorBody.getEffDay()+"-"+motorBody.getEffMon()+"-"+motorBody.getEffYear()));
				sb.append(motorBody.checkMotorBodyRateExistence());
				if(sb.length()>0){
					request.setAttribute("error", sb);	
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/MotorBodyRateNew.jsp?mode=new");
					dispatcher.forward(request, response);
				}else{
					if(session.getAttribute("doProcess")!=null){
						motorBody.isertOrUpdateMotorBodyRate(mode,motortyperate);
						session.removeAttribute("doProcess");
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/MotorBodyRateNew.jsp?typeid="+motorBody.getTypeId()+"");
					dispatcher.forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
}		
}

