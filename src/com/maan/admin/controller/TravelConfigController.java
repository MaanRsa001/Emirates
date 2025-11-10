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

import com.maan.admin.DAO.TravelConfigDAO;
import com.maan.common.LogUtil;
import com.maan.services.util.ValidationFormat;

public class TravelConfigController extends HttpServlet {
	private static final Logger logger = LogUtil.getLogger(TravelConfigController.class);

	
	private static final long serialVersionUID = 1L;
	final private transient static String SCHEME_ID = "scheme_id";
	final private transient static String TDAO_ID="tdao";
	final private transient static String OPTION_ID = "option_id";
	final private transient static String COVERAGES_ID = "coverages_id";
	final private transient static String TEXT_BOX = "textBox";

	final private transient static String  RADIO =  "radio";
	final private transient static String  DISCOUNT_ID =  "discount_id";
	final private transient static String TRAVEL_PREMIUM=  "travel_premium_id";
	final private transient static String RATE_ID="rate_id";
	final private transient static String DISCOUNT_START="discount_start";
	final private transient static String DISCOUNT_END="discount_end";
	final private transient static String RATE_VALUE="rate_value";
	final private transient static String AGE_END="age_end";
	final private transient static String AGE_START="age_start";
	final private transient static String MODE="mode";
	final private transient static String STATUS="status";
	final private transient static String COREAPP_CODE="coreapp_code";
	final private transient static String ERROR="error";
	
	
	
	public void destroy() {
		
	}

	
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		
	 doPost(request,response);
		
	}

	
	public void doPost(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException {
		final StringBuffer stringbuf=new StringBuffer("");

		HttpSession session = request.getSession(true);
		logger.info((String)request.getParameter("requestFrom"));
		logger.info((String)request.getParameter("val"));
		final TravelConfigDAO tDAO = new TravelConfigDAO();
		tDAO.setSessionInfo(session);
		
		if(session.getAttribute("ses")==null)
		{
				response.sendRedirect("../login/error_messg.jsp");
				
		}
		
		session = request.getSession(true);
		final String  branchCode= (String)session.getAttribute("LoginBranchCode");
		final String requestFrom=request.getParameter("requestFrom")==null?"":(String)request.getParameter("requestFrom");
		logger.info("requestFrom: "+requestFrom);
		
		if("schemCoverList".equalsIgnoreCase(requestFrom))
			{
			logger.info("***************** schemeCoverLIst*************************");
			try{
				final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			 request.setAttribute("travelconfig",tDAO.getSchemeList(branchCode));
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelSchemeConfig.jsp");
			 dispatcher.forward(request, response);
		     
			}
			catch(Exception e){
	logger.fatal(e);
			}
		}
		else if("edittravelconfig".equalsIgnoreCase(requestFrom))
		{
		logger.info("***************enter to travel scheme***************");
	
	final String scheme_id=request.getParameter(SCHEME_ID)==null?"":(String)request.getParameter(SCHEME_ID);
    logger.info("scheme_id is:"+scheme_id);
	 final String[][] result=tDAO.getTravelEdit(branchCode,scheme_id);
	if(result.length>0)
	{
	tDAO.setScheme_id(result[0][0]);
	tDAO.setScheme_name(result[0][1]);
	tDAO.setStatus(result[0][2]);
	tDAO.setCoreapp_code(result[0][3]);
	}
	request.setAttribute(TDAO_ID,tDAO);
	logger.info("schemid"+tDAO.getScheme_id());
	final RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/TravelEdit.jsp");
	dispatcher.forward(request,response);
	logger.info("exit from editmakeconfig");
							
	}
	
		else if("updateTravelSchemeconfig".equalsIgnoreCase(requestFrom)){
		logger.info("*************updateconfig***************");
		final String mode=request.getParameter(MODE)==null?"":(String)request.getParameter(MODE);
		
		final String scheme_id=request.getParameter(SCHEME_ID)==null?"":(String)request.getParameter(SCHEME_ID);
		tDAO.setScheme_name(request.getParameter("scheme_name")==null?"":(String)request.getParameter("scheme_name"));
		tDAO.setProductId(request.getParameter("product_id")==null?"":(String)request.getParameter("product_id"));
		tDAO.setStatus(request.getParameter(STATUS)==null?"":(String)request.getParameter(STATUS));
		tDAO.setCoreapp_code(request.getParameter(COREAPP_CODE)==null?"":(String)request.getParameter(COREAPP_CODE));
		
		try{
		RequestDispatcher dispatcher=null;
	
		if(tDAO.getScheme_name().equalsIgnoreCase(""))
		{
			
			stringbuf.append("Enter the Scheme Name(English) <br>");
		}
		if(tDAO.getProductId().equalsIgnoreCase(""))
		{
			
			stringbuf.append("Please Choose Product <br>");
		}
		
		if(tDAO.getStatus().equalsIgnoreCase(""))
		{
			stringbuf.append("Enter the Status <br/>");
		}
		
		if(tDAO.getScheme_name()!=null && !tDAO.getScheme_name().equalsIgnoreCase("")){
			
			logger.info("*************it comes to getScheme_name*********************"+scheme_id+"-"+tDAO.getScheme_name()+"-"+mode);
			    if(tDAO.validate( scheme_id,tDAO.getScheme_name(),mode))
			    {
		
				stringbuf.append("Scheme Name Already Exists <br/>");
			    }
			}
		
		logger.info("the errors is:"+stringbuf);
		if(stringbuf.length()>0){
			
		request.setAttribute(ERROR, stringbuf);
		 dispatcher = request.getRequestDispatcher("../admin/TravelEdit.jsp?mode="+mode);
		}
		else{ 
			
			tDAO.insertTravelSchemeEdit(branchCode, scheme_id,mode);
			dispatcher = request.getRequestDispatcher("/servlet/TravelConfigController?requestFrom=schemCoverList");
			
		}
		dispatcher.forward(request, response);
		}catch(Exception e){
			logger.fatal(e);
		}		
		
	}
		
		
		else if("initTravelOption".equalsIgnoreCase(requestFrom)){
			
			final String option_id = request.getParameter(OPTION_ID )==null?"":(String)request.getParameter(OPTION_ID);
			logger.info("*****************initTraveloption*************************"+option_id);
			List travelOptiolList;
			try{
				logger.info("it comes to the try block");
				travelOptiolList=tDAO.getOptionList(branchCode);
				
				request.setAttribute("travelOptiolList", travelOptiolList);
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelOption.jsp");
			 dispatcher.forward(request, response);
	
			}
			catch(Exception e){
				logger.fatal(e);
			}
		}
	     else if("editTravelOption".equalsIgnoreCase(requestFrom)){
            logger.info("*********now  i am in edittraveloption block**********");
		
			final String option_id=request.getParameter(OPTION_ID)==null?"":(String)request.getParameter(OPTION_ID);
			
			final String[][] result=tDAO.getTravelEditOption(branchCode,option_id);
			if(result.length>0)
			{
			tDAO.setOption_id(result[0][0]);
			tDAO.setOption_name(result[0][1]);
			tDAO.setStatus(result[0][2]);
			tDAO.setCoreapp_code(result[0][3]);
			}
			request.setAttribute(TDAO_ID,tDAO);
		
			logger.info(OPTION_ID+tDAO.getOption_id());
			final RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/TravelOptionLimit.jsp");
			dispatcher.forward(request,response);
			logger.info("exit from edittraveloption");
							
		}
		 else if("updateTravelOption".equalsIgnoreCase(requestFrom)){
				logger.info("*************updateTraveloption block***************");
				final String mode=request.getParameter(MODE)==null?"":(String)request.getParameter(MODE);
				
				
				final String option_id=request.getParameter(OPTION_ID)==null?"":(String)request.getParameter(OPTION_ID);
				tDAO.setOption_name(request.getParameter("option_name")==null?"":(String)request.getParameter("option_name"));
				tDAO.setStatus(request.getParameter(STATUS)==null?"":(String)request.getParameter(STATUS));
				tDAO.setCoreapp_code(request.getParameter(COREAPP_CODE)==null?"":(String)request.getParameter(COREAPP_CODE));
		
				try{
				RequestDispatcher dispatcher=null;
			
				if(tDAO.getOption_name().equalsIgnoreCase(""))
				{
					
					stringbuf.append("Enter the Option Name(English) <br>");
				}
				
				if(tDAO.getStatus().equalsIgnoreCase(""))
				{
					stringbuf.append("Enter the Status <br>");
				}
				if(tDAO.getOption_name()!=null && !tDAO.getOption_name().equalsIgnoreCase("")){
					
					logger.info("*************it comes to getScheme_name*********************"+option_id+"-"+tDAO.getScheme_name()+"-"+mode);
					    if(tDAO.optionValidate( option_id,tDAO.getOption_name(),mode))
					    {
						logger.info("***********with in the  validateif condition***********");
						stringbuf.append("Option Name Already Exists <br>");
					    }
					}
				
				
				logger.info("the error is:"+stringbuf);
				if(stringbuf.length()>0){
					
				request.setAttribute(ERROR, stringbuf);
				 dispatcher = request.getRequestDispatcher("../admin/TravelOptionLimit.jsp?mode="+mode);
				}
				else{ 
					
					tDAO.insertTravelOptionEdit(branchCode, option_id,mode);
					dispatcher = request.getRequestDispatcher("/servlet/TravelConfigController?requestFrom=initTravelOption");
					
				}
				dispatcher.forward(request, response);
				}catch(Exception e){
					logger.fatal(e);
				}		
			
			}
		/* else if("initTravelCovers".equalsIgnoreCase(requestFrom)){
				logger.info("******************now i am in initTravelCovers***********************");
				final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/Travel Coverages List.jsp");
				dispatcher.forward(request, response);
			
		 }
		 else if("listTravelCoverages".equalsIgnoreCase(requestFrom)){
			 logger.info("*****************now i am in lisitTravelCoverages******************");
				final String scheme_id = request.getParameter(SCHEME_ID)==null?"":(String)request.getParameter(SCHEME_ID);
				final String option_id=request.getParameter(OPTION_ID)==null?"":(String)request.getParameter(OPTION_ID);
				logger.info("*****************listTravelCoverages*************************"+scheme_id);
				logger.info("*****************listTravelCoverages*************************"+option_id);
				List travelCoverList;
				try{
					logger.info("*****************now i comes to try block***************");
					logger.info("it comes to the try block");
					travelCoverList=tDAO.getTravelCoverages(scheme_id,option_id,branchCode);
					logger.info("*****************now i am goes to getTravelCoverages ***************"+travelCoverList);
					request.setAttribute("travelCoverList", travelCoverList);
					request.setAttribute("jspView", "TravelCoverList");
					final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/Travel Coverages List.jsp");
					dispatcher.forward(request, response);
				
				}
				catch(Exception e){
					logger.fatal(e);
				}
			}
		 else if("editTravelCoverages".equalsIgnoreCase(requestFrom)){
			 logger.info("************it is in the edittravelcoverages block*************");
			
			final String scheme_id=request.getParameter(SCHEME_ID)==null?"":(String)request.getParameter(SCHEME_ID);
		     final String option_id=request.getParameter(OPTION_ID)==null?"":(String)request.getParameter(OPTION_ID); 
             final String coverages_id=request.getParameter(COVERAGES_ID)==null?"":(String)request.getParameter(COVERAGES_ID); 
             logger.info("scheme__id of :"+scheme_id+"option_id of:"+option_id+"coverages_id"+coverages_id);
             final String[][] result=tDAO.getTravelEditCoverages(branchCode,coverages_id);
             if(result.length>0)
 			{
            tDAO.setCoverages_id(result[0][0]);       
 			tDAO.setCoverages_name(result[0][1]);
 			tDAO.setCoverages_value(result[0][2]);
 			tDAO.setStatus(result[0][3]);
 			tDAO.setCoreapp_code(result[0][4]);
 			}
             request.setAttribute(TDAO_ID,tDAO);
            final  RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/TravelCoverageEdit.jsp");
 			dispatcher.forward(request,response);
		 }*/
		 /*else if("updateTravelCovrages".equalsIgnoreCase(requestFrom)){
			 logger.info("***********it is in the updateTravelCoverages block**************");
			 final String mode=request.getParameter(MODE)==null?"":(String)request.getParameter(MODE);
			 final String scheme_id=request.getParameter(SCHEME_ID)==null?"":(String)request.getParameter(SCHEME_ID);
			 final String option_id=request.getParameter(OPTION_ID)==null?"":(String)request.getParameter(OPTION_ID);
			 final String coverages_id=request.getParameter(COVERAGES_ID)==null?"":(String)request.getParameter(COVERAGES_ID);
             tDAO.setStatus(request.getParameter(STATUS)==null?"":(String)request.getParameter(STATUS));
             tDAO.setCoreapp_code(request.getParameter(COREAPP_CODE)==null?"":(String)request.getParameter(COREAPP_CODE));
         
             try{
            		RequestDispatcher dispatcher=null;
            		if(tDAO.getCoverages_name().equalsIgnoreCase("")){
            			
            			stringbuf.append("Enter the Coverages Name(English) <br>");
            		}
            		if(tDAO.getCoverages_value().equalsIgnoreCase("")){
            			stringbuf.append("Enter the Coverages value(English) <br>");
            			
            		}
            		if(tDAO.getStatus().equalsIgnoreCase(""))
    				{
            			stringbuf.append("Enter the Status <br>");
    				}
            		logger.info("ther error is:"+stringbuf);
            		if(stringbuf.length()>0){
            			
            		request.setAttribute(ERROR, stringbuf);
            		 dispatcher = request.getRequestDispatcher("../admin/TravelCoverageEdit.jsp?mode="+mode);
            		}else{
            			
            			dispatcher = request.getRequestDispatcher("/servlet/TravelConfigController?requestFrom=listTravelCoverages");
            			
            		}
            		dispatcher.forward(request, response);       		         	
            		  	 
            }catch(Exception e){
            	logger.fatal(e);
            	 
             }
		 }*/
		 else if("coveragesmaster".equalsIgnoreCase(requestFrom)){
			 
			 try{
					
				 request.setAttribute("masterCoverages",tDAO.getCoveragesList(branchCode));
				 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelCoveragesMasterList.jsp");
				 dispatcher.forward(request, response);
				
				}catch(Exception e){
					logger.fatal(e);
				}
		 }
		 else if("editmastercoverages".equalsIgnoreCase(requestFrom))
			{
			logger.info("***************enter to travel coverages***************");
		
		final String coverages_id=request.getParameter(COVERAGES_ID)==null?"":(String)request.getParameter(COVERAGES_ID);
	    logger.info("coverages_id is:"+coverages_id);
		final String[][] result=tDAO.getTravelCoveragesMasterEdit(branchCode,coverages_id);
		if(result.length>0)
		{
		tDAO.setCoverages_id(result[0][0]);
		tDAO.setCoverages_name(result[0][1]);
		tDAO.setCoverages_value(result[0][2]);
		tDAO.setStatus(result[0][3]);
		tDAO.setCoreapp_code(result[0][4]);
		}
		request.setAttribute(TDAO_ID,tDAO);
		logger.info("coverages_id"+tDAO.getScheme_id());
		 final RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/TravelCoveragesMasterEdit.jsp");
		dispatcher.forward(request,response);
		logger.info("exit from editmastercoverages");
						
		}
		 else if("updatemastercoverages".equalsIgnoreCase(requestFrom)){
				logger.info("*************updatemastercoverages***************");
				final String mode=request.getParameter(MODE)==null?"":(String)request.getParameter(MODE);
			
				final String coverages_id=request.getParameter(COVERAGES_ID)==null?"":(String)request.getParameter(COVERAGES_ID);
			    tDAO.setCoverages_name(request.getParameter("coverages_name")==null?"":(String)request.getParameter("coverages_name"));
			    tDAO.setCoverages_value(request.getParameter("coverages_value")==null?"":(String)request.getParameter("coverages_value"));
			    tDAO.setStatus(request.getParameter(STATUS)==null?"":(String)request.getParameter(STATUS));
			    tDAO.setCoreapp_code(request.getParameter(COREAPP_CODE)==null?"":(String)request.getParameter(COREAPP_CODE));
			
				try{
				RequestDispatcher dispatcher=null;
			
				if(tDAO.getCoverages_name().equalsIgnoreCase(""))
				{
					
					stringbuf.append("Enter the Coverages Name(English) <br>");
				}

				if(tDAO.getCoverages_value().equalsIgnoreCase(""))
				{
					
					stringbuf.append("Enter the Coverages value(English) <br>");
				}
				if(tDAO.getStatus().equalsIgnoreCase(""))
				{
					stringbuf.append("Enter the Status <br>");
				}
				
				if(tDAO.getCoverages_value()!=null && !tDAO.getCoverages_value().equalsIgnoreCase("")){
					
					logger.info("*************it comes to coverages value*********************"+coverages_id+"-"+tDAO.getCoverages_value()+"-"+mode);
					    if(tDAO.coveragesValidate( coverages_id,tDAO.getCoverages_value(),mode))
					    {
						logger.info("***********with in the  validateif condition***********");
						stringbuf.append("Coverages Value Already Exists <br>");
					    }
					}
			if(tDAO.getCoverages_name()!=null && !tDAO.getCoverages_name().equalsIgnoreCase("")){
					
					logger.info("*************it comes to coverages value*********************"+coverages_id+"-"+tDAO.getCoverages_name()+"-"+mode);
					    if(tDAO.coveragesNameValidate( coverages_id,tDAO.getCoverages_name(),mode))
					    {
						logger.info("***********with in the  validateif condition***********");
						stringbuf.append("Coverages name Already Exists <br>");
					    }
					}
				
				
				logger.info("the error is :"+stringbuf);
				if(stringbuf.length()>0){
					
				request.setAttribute(ERROR, stringbuf);
				 dispatcher = request.getRequestDispatcher("../admin/TravelCoveragesMasterEdit.jsp?mode="+mode);
				}
				else{ 
					
					 tDAO.insertTravelMasterCoveragesEdit(branchCode, coverages_id,mode);
					dispatcher = request.getRequestDispatcher("/servlet/TravelConfigController?requestFrom=coveragesmaster");
					
				}
				dispatcher.forward(request, response);
				}catch(Exception e){
					logger.fatal(e);
				}		
			
			}
		 else if("premiumRate".equalsIgnoreCase(requestFrom)){
			 
			 	request.setAttribute("jspView", "TravelPremiumListProduct");
			 	final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelPremiumRate.jsp");
				dispatcher.forward(request, response);
			
			 
		 }else if("premiumRateProduct".equalsIgnoreCase(requestFrom)){
			 
			 logger.info("now i am in premium Rate block of TRAVEL PREMIUM RATE ***");
			 final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			 
			 request.setAttribute("jspView", "TravelPremiumList");
			 request.setAttribute("ratepremium",tDAO.getPremiumList(branchCode,productId));
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelPremiumRate.jsp");
			 dispatcher.forward(request, response);
}
		 else if("linkinitcoverages".equalsIgnoreCase(requestFrom)){
			logger.info("**********now i am in linkcoverages*************");
			
			request.setAttribute("jspView", "ProductSelection");
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelLinkCoveragesList.jsp");
			 dispatcher.forward(request, response);
			
		 }else if("linkinitcoveragesProduct".equalsIgnoreCase(requestFrom)){
			 
			 request.setAttribute("jspView", "TravelLinkCoverInit");
			 final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			 System.out.println("productId: "+productId);
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelLinkCoveragesList.jsp");
			 dispatcher.forward(request, response); 
		 }
		 else if("linkSubmitcoverages".equalsIgnoreCase(requestFrom)){
			 
			 logger.info("**********now i am in linkcoverages*************");
				
				final String schemeId=request.getParameter(SCHEME_ID)==null?"":(String)request.getParameter(SCHEME_ID);
				final String optionId=request.getParameter(OPTION_ID)==null?"":(String)request.getParameter(OPTION_ID);
				final String CoverId=request.getParameter("selectedCoverageId")==null?"":(String)request.getParameter("selectedCoverageId");
				final String checkId=request.getParameter("deselectedCoverageId")==null?"":(String)request.getParameter("deselectedCoverageId");
				final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
				String[] coveragesId;
				String rate="",rateType="";
				double rateValue=0;
			
				System.out.println("productId: "+productId);
				
				if(!"".equalsIgnoreCase(CoverId))
				{
				// Selected CoverageId Process
				coveragesId=CoverId.split(",");
				String rt[]=(request.getParameter(("rate"))==null?"":(String)request.getParameter(("rate"))).split("~");
				String type[]=(request.getParameter(("radio"))==null?"":(String)request.getParameter(("radio"))).split("~");
				String date[]=(request.getParameter(("effDate"))==null?"":(String)request.getParameter(("effDate"))).split("~");
				
				for(int i=0;i<coveragesId.length;i++)
				{
					/*rate=request.getParameter((TEXT_BOX +i))==null?"0.0":(String)request.getParameter((TEXT_BOX +i));
					rateType=request.getParameter( (RADIO+i))==null?"":(String)request.getParameter( (RADIO+i));
					String effDate=request.getParameter( ("effDate"+i))==null?"":(String)request.getParameter( ("effDate"+i));*/
					rate=rt[i];
					rateType=type[i];
					String effDate=date[i];
					
					try{
					rateValue=Double.parseDouble(rate);
					}catch(Exception e){logger.info("Exception In Selected CoverageId Process: "+e);rateValue=0;}
					if("D".equalsIgnoreCase(rateType))
					{
						rateValue=rateValue*(-1);
					}
					
					/*if((tDAO.getAlreadyExistCount(schemeId, optionId, coveragesId[i], branchCode,productId))>0)
					{
						tDAO.updateTravelLinkCoverages(coveragesId[i], schemeId, optionId, String.valueOf(rateValue), branchCode, "Y",productId);
					}
					else
					{*/
						tDAO.insertTravelLinkCoverages(coveragesId[i], schemeId, optionId, String.valueOf(rateValue), branchCode,productId,effDate);
					//}
				}
				}
				
				if(!"".equalsIgnoreCase(checkId))
				{
				
				//	Not Selected CoverageId Process
					coveragesId=checkId.split(",");
				
				for(int i=0;i<coveragesId.length;i++)
				{
				/*	rate=request.getParameter(TEXT_BOX +coveragesId[i])==null?"0.0":(String)request.getParameter(TEXT_BOX +coveragesId[i]);
					rateType=request.getParameter( RADIO+coveragesId[i])==null?"":(String)request.getParameter( RADIO+coveragesId[i]);
					try{
						rateValue=Double.parseDouble(rate);
						}catch(Exception e){logger.info("Exception In Not Selected CoverageId Process: "+e);rateValue=0;}
					if("D".equalsIgnoreCase(rateType))
					{
						rateValue=rateValue*(-1);
					}*/
					
					if((tDAO.getAlreadyExistCount(schemeId, optionId, coveragesId[i], branchCode,productId))>0)
					{
						tDAO.deleteTravelLinkCoverages(coveragesId[i], schemeId, optionId, branchCode,productId);
					}
				}
				}
				
				stringbuf.append("Records Updated Successfully");
				
				request.setAttribute("result", stringbuf);
				final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelLinkCoveragesList.jsp");
				dispatcher.forward(request, response);
			
		 }else if("ratediscountProduct".equalsIgnoreCase(requestFrom)){
			 logger.info("*************now iam in ratediscount************");
			 final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			 
			 try{
				 request.setAttribute("jspView", "TravelRateList");	
				 request.setAttribute("travelrate",tDAO.getRateList(branchCode,productId));
				 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelRate.jsp");
				 dispatcher.forward(request, response);
		
				}
				catch(Exception e){
					logger.fatal(e);
				} 
		 }else if("ratediscount".equalsIgnoreCase(requestFrom)){
			 logger.info("*************now iam in ratediscount************");
			 try{
					
				 request.setAttribute("jspView", "TravelRateProduct");
				 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelRate.jsp");
				 dispatcher.forward(request, response);
		
				}
				catch(Exception e){
					logger.fatal(e);
				} 
		 }else if("relationDiscountProduct".equalsIgnoreCase(requestFrom)){
			 
			 logger.info("*****************now i am in relationDiscount(Travel relation discount) block ");
			 try{
				 final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
				 System.out.println("productId: "+productId);
				 request.setAttribute("jspView", "TravelDiscountList");
				 request.setAttribute("rds",tDAO.getRelationDiscount(branchCode,productId));
				 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelDiscountRelation.jsp");
				 dispatcher.forward(request, response);
			 }catch(Exception e){
					logger.fatal(e);
			 }
			 
		 }
		 else if("relationDiscount".equalsIgnoreCase(requestFrom)){
			 request.setAttribute("jspView", "TravelDiscountProduct");
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelDiscountRelation.jsp");
			 dispatcher.forward(request, response);
		 }
		 else if("discountrelationedit".equalsIgnoreCase(requestFrom)){
			 logger.info("***************now i am in travel relation edit block of the controler***************");
			
			 String mode=request.getParameter("mode")==null?"":(String)request.getParameter("mode");
			
			 if("Edit".equalsIgnoreCase(mode))
			 {
			final String discount_id=request.getParameter(DISCOUNT_ID)==null?"":(String)request.getParameter(DISCOUNT_ID);			 
			logger.info("Discount id is:"+discount_id);
			final String[][] result=tDAO.getDiscountRelationEdit(branchCode,discount_id);
			final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			System.out.println("productId: "+productId);
			

				if(result.length>0)
				{
				tDAO.setDiscount_id(result[0][0]);
				tDAO.setDiscount_start(result[0][1]);
				tDAO.setDiscount_end(result[0][2]);
				tDAO.setRate_value(result[0][3]);
				tDAO.setRelation_type(result[0][4]);
				tDAO.setRemarks(result[0][5]);
				tDAO.setEffDate(result[0][6]);
				}
			 
				request.setAttribute(TDAO_ID,tDAO);
				logger.info("Discount id"+tDAO.getDiscount_id());
			  }
				final RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/TravelDiscountRelationEdit.jsp");
				dispatcher.forward(request,response);
				logger.info("exit from ratetraveledit");
						
		 }
		 else if("premiumeditrate".equalsIgnoreCase(requestFrom)){
			 
			 logger.info("now i am in premium travelrate edit block of travel premium rate table");
			 String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			 String mode=request.getParameter("mode")==null?"":(String)request.getParameter("mode");
				
			 if("Edit".equalsIgnoreCase(mode))
			 {
			 
			final String travel_premium_id=request.getParameter(TRAVEL_PREMIUM)==null?"":(String)request.getParameter(TRAVEL_PREMIUM);
			final String[][] result=tDAO.getTravelPremiumEdit(branchCode,travel_premium_id,productId);
				if(result.length>0)
				{
				tDAO.setTravel_premium_id(result[0][0]);
				tDAO.setNo_of_days(result[0][1]);
			
				tDAO.setScheme_id(result[0][2]);
				tDAO.setOption_id(result[0][3]);
				tDAO.setPremium(result[0][4]);
				tDAO.setEffDate(result[0][5]);
				}
				request.setAttribute(TDAO_ID,tDAO);
			 }
				
				final RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/TravelPremiumEdit.jsp");
					dispatcher.forward(request,response);
		 }
		 else if("ratetraveledit".equalsIgnoreCase(requestFrom)){
		logger.info("***************enter to travel rate***************");
		
		String mode=request.getParameter("mode")==null?"":(String)request.getParameter("mode");
		final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
		
		if("Edit".equalsIgnoreCase(mode))
		{
		final String rate_id=request.getParameter(RATE_ID)==null?"":(String)request.getParameter(RATE_ID);
	    logger.info("Rate id is:"+rate_id);
		
	    final String[][] result=tDAO.getTravelRateEdit(branchCode,rate_id,productId);
		if(result.length>0)
		{
		tDAO.setRate_id(result[0][0]);
		tDAO.setType(result[0][1]);
		tDAO.setAge_start(result[0][2]);
		tDAO.setAge_end(result[0][3]);
		tDAO.setRate_value(result[0][4]);
		tDAO.setEffDate(result[0][5]);
		
		}
		request.setAttribute(TDAO_ID,tDAO);
		
		
	    logger.info("Rate id"+tDAO.getRate_id());
		}
	    
		final RequestDispatcher dispatcher=request.getRequestDispatcher("../admin/TravelRateEdit.jsp");
		dispatcher.forward(request,response);
		logger.info("exit from ratetraveledit");
						
		}
		 else if("updateTravelPremiumRate".equalsIgnoreCase(requestFrom)){
			 logger.info("now i am in the updateTravelPremiumRate of trvel premium rate table of the updation");
			 final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			 final String mode=request.getParameter(MODE)==null?"":(String)request.getParameter(MODE);
			 final String travel_premium_id=request.getParameter(TRAVEL_PREMIUM)==null?"0":(String)request.getParameter(TRAVEL_PREMIUM);
			 final String premium=request.getParameter("premium")==null?"0":(String)request.getParameter("premium");
			 final String no_of_days=request.getParameter("no_of_days")==null?"":request.getParameter("no_of_days");
			 final String scheme_id=request.getParameter("scheme_id")==null?"":request.getParameter("scheme_id");
			 final String option_id=request.getParameter("option_id")==null?"":request.getParameter("option_id");
			 final String effDate=request.getParameter("effDate")==null?"":request.getParameter("effDate");
			 
			 tDAO.setPremium(premium);
			 tDAO.setNo_of_days(no_of_days);
			 tDAO.setScheme_id(scheme_id);
			 tDAO.setOption_id(option_id);
			 tDAO.setEffDate(effDate);
			 
			 try{
				 RequestDispatcher dispatcher=null;
					
				 	if("".equalsIgnoreCase(tDAO.getNo_of_days()))
				 	{
				 		stringbuf.append("Please Select No Of Days <br>");
				 	}
				 
				 	if("".equalsIgnoreCase(tDAO.getScheme_id()))
				 	{
				 		stringbuf.append("Please Select Scheme Name <br>");
				 	}
				 	
				 	if("".equalsIgnoreCase(tDAO.getOption_id()))
				 	{
				 		stringbuf.append("Please Select Option Name <br>");
				 	}
				 	
				 	if(tDAO.getPremium().equalsIgnoreCase(""))
					{
						stringbuf.append("Please Enter the premium value <br>");
					}
				 	if("".equalsIgnoreCase(tDAO.getEffDate()))
					{
						stringbuf.append("Please Select Effective Date <br>");
					}else if(new ValidationFormat().sysDateValidation(tDAO.getEffDate()))
					{
						stringbuf.append("Effective Date should be greater than or Equal to sysdate <br>");
					}
				 	
				/* 	
				 	if(tDAO.recordAlreadyExists(scheme_id, option_id, no_of_days, travel_premium_id, branchCode, productId)>0)
				 	{
				 		stringbuf.append("Record Already Exists For This Combination <br>");
				 	}*/
				 	
					if(stringbuf.length()>0){
						
						request.setAttribute(ERROR, stringbuf);
						 dispatcher = request.getRequestDispatcher("../admin/TravelPremiumEdit.jsp?mode="+mode);
						}
						else{ 
						tDAO.insertTravelPremiumRate(travel_premium_id,mode,branchCode,productId,effDate,branchCode);
						dispatcher = request.getRequestDispatcher("/servlet/TravelConfigController?requestFrom=premiumRateProduct");
						}
						dispatcher.forward(request, response);
		 }catch(Exception e){
				logger.fatal(e);
			 
		 }
			
			 
		 }
		 else if("updateTravelRelationDiscount".equalsIgnoreCase(requestFrom)){
			 logger.info("now i am in updateTravelRelationDiscount");
			 String val="";
			 
		 final String mode=request.getParameter(MODE)==null?"":(String)request.getParameter(MODE);
		 final String discount_id=request.getParameter(DISCOUNT_ID)==null?"":(String)request.getParameter(DISCOUNT_ID);
		 final String discountStart=request.getParameter(DISCOUNT_START)==null?"":(String)request.getParameter(DISCOUNT_START);
		 final String discountEnd=request.getParameter(DISCOUNT_END)==null?"":(String)request.getParameter(DISCOUNT_END);
		 final String rate_value=request.getParameter(RATE_VALUE)==null?"":(String)request.getParameter(RATE_VALUE);
		 final String remarks=request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
		 final String relation_type=request.getParameter("relation_type")==null?"":(String)request.getParameter("relation_type");
		 final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
		 final String effDate=request.getParameter("effDate")==null?"":request.getParameter("effDate");
		 tDAO.setDiscount_start(discountStart);
		 tDAO.setDiscount_end(discountEnd);
		 tDAO.setRate_value(rate_value);
		 tDAO.setRemarks(remarks);
		 tDAO.setDiscount_start(request.getParameter(DISCOUNT_START)==null?"":(String)request.getParameter(DISCOUNT_START));
		 tDAO.setDiscount_end(request.getParameter(DISCOUNT_END)==null?"":(String)request.getParameter(DISCOUNT_END));
		 tDAO.setRelation_type(relation_type);
		 tDAO.setEffDate(effDate);
		 
		 request.setAttribute("discountStart", discountStart);
		 request.setAttribute("discountEnd",discountEnd);

		 try{
				RequestDispatcher dispatcher=null;
				
				if(tDAO.getRelation_type().equalsIgnoreCase(""))
				{
					stringbuf.append("Select Relation Type <br>");
				}
				
				if(tDAO.getRate_value().equalsIgnoreCase(""))
				{
					
					stringbuf.append("Enter the Rate value <br>");
				}
				
/*				val=tDAO.relationValidate( discount_id,discountStart,branchCode,productId);

				 if(!"".equalsIgnoreCase(val))
				 {
					 stringbuf.append("Discount Start Is Already Existed In The Type "+val+" <br/>");
				 }
				 val=tDAO.relationValidate(discount_id, discountEnd, branchCode,productId);
				 
					if(!"".equalsIgnoreCase(val))		  
					{
						stringbuf.append("Discount  End Is Already Existed In The Type "+val+" <br/>");
					}
					
					if(tDAO.relationValidate1(discount_id, relation_type, branchCode, productId)>0)
					{
						stringbuf.append("Relation Type Already Exists <br/>");
					}*/
					if("".equalsIgnoreCase(tDAO.getEffDate()))
					{
						stringbuf.append("Please Select Effective Date <br>");
					}else if(new ValidationFormat().sysDateValidation(tDAO.getEffDate()))
					{
						stringbuf.append("Effective Date should be greater than or Equal to sysdate <br>");
					}
					
				//Anbu....
					int s1=Integer.parseInt(discountStart);
					int s2=Integer.parseInt(discountEnd);
					if(s1<=s2)
					{
						val=tDAO.relationValidate(discount_id, discountEnd, branchCode,productId);
					}
					else
					{
						stringbuf.append("Please Verify! DiscountEnd is less than  DiscountStart");
					}
				
					if(stringbuf.length()>0){
						
						request.setAttribute(ERROR, stringbuf);
						 dispatcher = request.getRequestDispatcher("../admin/TravelDiscountRelationEdit.jsp?mode="+mode);
						}
						else{ 
							
							tDAO.insertTravelDiscountRelation(discount_id,mode,branchCode,productId,effDate);
							dispatcher = request.getRequestDispatcher("/servlet/TravelConfigController?requestFrom=relationDiscountProduct");
							
						}
						dispatcher.forward(request, response);
		 }catch(Exception e){
			 logger.fatal(e);
			 
		 }
		 
		 }
		 else if("updateTravelRate".equalsIgnoreCase(requestFrom)){
				logger.info("*************updateconfig***************");
				 String valRes="";
				final String mode=request.getParameter(MODE)==null?"":(String)request.getParameter(MODE);
				
				final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id"); 
				final String type=request.getParameter("type")==null?"":request.getParameter("type");
				final String rate_id=request.getParameter(RATE_ID)==null?"":(String)request.getParameter(RATE_ID);
				logger.info("rate id value is:"+rate_id);
				final String ageStart=request.getParameter(AGE_START)==null?"":(String)request.getParameter(AGE_START);
				final String ageEnd=request.getParameter(AGE_END)==null?"":(String)request.getParameter(AGE_END);
				final String effDate=request.getParameter("effDate")==null?"":request.getParameter("effDate");
				tDAO.setAge_start(ageStart);
				tDAO.setAge_end(ageEnd);
				tDAO.setEffDate(effDate);
				logger.info("the dao age is "+ageStart+"the jsp age is "+tDAO.getAge_start());
				tDAO.setAge_end(request.getParameter(AGE_END)==null?"":(String)request.getParameter(AGE_END));
				tDAO.setRate_value(request.getParameter(RATE_VALUE)==null?"":(String)request.getParameter(RATE_VALUE));
				request.setAttribute("ageStart", ageStart);
				request.setAttribute("ageEnd",ageEnd);
				tDAO.setType(type);
				
				try{
				RequestDispatcher dispatcher=null;
			
				if("".equalsIgnoreCase(tDAO.getType()))
				stringbuf.append("Select Type <br>");
					
				if(tDAO.getRate_value().equalsIgnoreCase(""))
				{
					
					stringbuf.append("Enter the Rate value <br>");
				}
				
				/*valRes=tDAO.rateValidate(rate_id, ageStart, branchCode,productId);
				 if(!"".equalsIgnoreCase(valRes))
				 {
					 
					 stringbuf.append("Please Verify! AgeEnd is less than  AgeStart"+valRes+"<br/>");
				 }*/
				 
				/* valRes=tDAO.rateValidate(rate_id, ageEnd, branchCode,productId);
				 
				if(!"".equalsIgnoreCase(valRes))		  
				{
					stringbuf.append("Age End Is Already Existed In The Relation Type "+valRes+" <br>");
					
				}	 */
				
				//Anbu......
				int a=Integer.parseInt(ageStart);
				int b=Integer.parseInt(ageEnd);
				if(a<b)
				{
					valRes=tDAO.rateValidate(rate_id, ageStart, branchCode, productId);
				}
				else 
				{
					stringbuf.append("Please Verify! AgeEnd is less than  AgeStart");
				}
				
				if("".equalsIgnoreCase(tDAO.getEffDate()))
				{
					stringbuf.append("Please Select Effective Date <br>");
				}else if(new ValidationFormat().sysDateValidation(tDAO.getEffDate()))
				{
					stringbuf.append("Effective Date should be greater than or Equal to sysdate <br>");
				}
				
				/*if(!"Edit".equalsIgnoreCase(mode)&&tDAO.rateValidate1(rate_id, type, branchCode, productId)>0)
				{
					stringbuf.append("Type Already Exists <br>");
				}*/
					
				if(stringbuf.length()>0){
					
				request.setAttribute(ERROR, stringbuf);
				 dispatcher = request.getRequestDispatcher("../admin/TravelRateEdit.jsp?mode="+mode);
				}
				else{ 
					
					tDAO.insertTravelRateEdit(rate_id,mode,branchCode, productId,effDate);
					dispatcher = request.getRequestDispatcher("/servlet/TravelConfigController?requestFrom=ratediscountProduct");
					
				}
				dispatcher.forward(request, response);
				}catch(Exception e){
				logger.fatal(e);
				}		
				
			}
		 else if("firstlinkoption".equalsIgnoreCase(requestFrom)){
			 logger.info("now i am in travel link option****************");
			 request.setAttribute("jspView", "ProductSelection");
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelLinkOption.jsp");
				dispatcher.forward(request, response);
				
		 }else if("firstlinkoptionProduct".equalsIgnoreCase(requestFrom)){
			 
			 request.setAttribute("jspView", "TravelLinkOptionInit");
			 final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
			 System.out.println("productId: "+productId);
			 final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelLinkOption.jsp");
			 dispatcher.forward(request, response);
		 }
		
 else if("optionlink".equalsIgnoreCase(requestFrom)){
			 
			 logger.info("**********now i am in optionlink*************");
				final String schemeId=request.getParameter(SCHEME_ID)==null?"":(String)request.getParameter(SCHEME_ID);
				final String selectedOptionId=request.getParameter("selectedOptionId")==null?"":(String)request.getParameter("selectedOptionId");
				final String checkId=request.getParameter("deselectedOptionId")==null?"":(String)request.getParameter("deselectedOptionId");
				final String productId=request.getParameter("product_id")==null?"":request.getParameter("product_id");
				String[] OptionLinkId;
			
				
				logger.info("Product Id: "+productId+" schemeId:"+schemeId+" selectedOptionId: "+selectedOptionId+" notSelectedOptionId: "+checkId);
				
		
				
				try{
				
				if(!"".equalsIgnoreCase(selectedOptionId))
				{
				// Selected CoverageId Process
					OptionLinkId=selectedOptionId.split(",");
				
				for(int i=0;i<OptionLinkId.length;i++)
				{
					if((tDAO.getAlreadyOptionLinkCount(schemeId, OptionLinkId[i], branchCode,productId))>0)
					{
						tDAO.updateOptionLink(schemeId, OptionLinkId[i], branchCode,productId);
					}
					else
					{
						tDAO.insertOptionLink(schemeId, OptionLinkId[i] ,  branchCode,productId);
					}
				}
				}
				
				if(!"".equalsIgnoreCase(checkId))
				{
				
				//	Not Selected CoverageId Process
					OptionLinkId=checkId.split(",");
				
				for(int i=0;i<OptionLinkId.length;i++)
				{
					if((tDAO.getAlreadyOptionLinkCount(schemeId, OptionLinkId[i], branchCode,productId))>0)
					{
						tDAO.deleteOptionLink(schemeId, OptionLinkId[i], branchCode,productId);
					}
				}
				}
				
				stringbuf.append("Records Updated Successfully");
				}catch(Exception e){logger.info("Exception: "+e);}
				
				request.setAttribute("result", stringbuf);
			final RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/TravelLinkOption.jsp");
				dispatcher.forward(request, response);
				return;
		 }
		
		
		
		
	}
	public void init() throws ServletException {
	logger.info("now i am in init method");
	}

}
