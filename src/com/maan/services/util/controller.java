package com.maan.services.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import com.maan.premium.DAO.CommodityCountryRateDAO;
import com.maan.premium.DAO.ReportBean;
import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.services.customerInfo;

public class controller extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out = null;
	boolean format=false;
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			ProcessResult(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			ProcessResult(request,response);
	}
	
	public void ProcessResult(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String usrModeController="";
		HttpSession session=null;
		session=request.getSession(false);
		RequestDispatcher rd	= null;
		response.setContentType("text/html");
		if(session==null || session.getAttribute("ses")==null)
		{
			response.sendRedirect("login/error_messg.jsp");
			return;
		}
		try
		{
			usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;	
			
			if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController) )
			{
				response.sendRedirect("login/error_messg.jsp");
				return;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ID from session_master controller.java "+e.toString());
			e.printStackTrace();
		}


		System.out.println(" ****************** SESSION CHECKING   ****************");
		String[][] sessionMaster_id=new String[0][0];
		String[][] tracking_id=new String[0][0];
		
		String args[] = new String[0];
		String query = "";
		args = new String[1];
		args[0] = (String)session.getAttribute("ses");
		query = "select session_id,login_id from session_master where session_id=?";
		sessionMaster_id = runner.multipleSelection(query,args);
		query = "select session_id,login_id from tracking_master where session_id=?";
		tracking_id = runner.multipleSelection(query,args);

		String sescon = "";
		if(session.getAttribute("sessionCheckCondition")!=null)
			sescon = (String)session.getAttribute("sessionCheckCondition");
		if(!sescon.equalsIgnoreCase("Yes"))
		{
			try
			{
				if(sessionMaster_id.length>0)
				{
					if(!((String)session.getAttribute("user")).equalsIgnoreCase(sessionMaster_id[0][1]))
					{
						System.out.println("ID from session_master  "+sessionMaster_id[0][0]);
						System.out.println("USERNAME from session_master  "+sessionMaster_id[0][1]);
						response.sendRedirect("../login/error_messg.jsp");
						return;
					}
					if(tracking_id.length>0)
					{
						if(!((String)session.getAttribute("user")).equalsIgnoreCase(tracking_id[0][1]))
						{
							System.out.println("ID from tracking_master  "+tracking_id[0][0]);
							System.out.println("USERNAME from tracking_master  "+tracking_id[0][1]);
							response.sendRedirect("../login/error_messg.jsp");
							return;
						}
						if(!(sessionMaster_id[0][1]).equalsIgnoreCase(tracking_id[0][1]))
						{
							System.out.println("ID from session_master  "+sessionMaster_id[0][0]);
							System.out.println("USERNAME from session_master  "+sessionMaster_id[0][1]);
							System.out.println("ID from tracking_master  "+tracking_id[0][0]);
							System.out.println("USERNAME from tracking_master  "+tracking_id[0][1]);
							response.sendRedirect("../login/error_messg.jsp");
							return;
						}
					}
				}
				else
				{
					response.sendRedirect("login/error_messg.jsp");
					return;
				}
			}catch(Exception e)
			{
				System.out.println("ERROR NOT AVAILABLE SESSION ID\n "+e.toString());
			}
		}
		String commonAppNo = (String)session.getAttribute("applicationNo");
		if(commonAppNo==null)
		{
			if(session.getAttribute("application_no")!=null)
			{
				commonAppNo = (String)session.getAttribute("application_no");
				session.setAttribute("applicationNo",commonAppNo);
			}
		}
		else
		{
			session.setAttribute("application_no",commonAppNo);
		}
		System.out.println("Services/Util/Controller.java---> APPLICAITONNO.. "+commonAppNo);
		
		out=response.getWriter();
		String path=request.getRequestURI();
		System.out.println("Services/Util/Controller.java---> path.. "+path);
		try
		{
			path=path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
		}
		catch(Exception e)
		{
			path = "newquote";
		}
		
		String error="";
		
		String rsaissuer ="";
		rsaissuer = (String)session.getAttribute("RSAISSUER");
		rsaissuer = rsaissuer == null ? "" : rsaissuer;
		System.out.println("Pathhhhhhhhhhhhhhhhhhh"+path);
		if("newCustomer".equalsIgnoreCase(path))
		{
				com.maan.services.customerInfo cc=new com.maan.services.customerInfo();
				cc.setTitle(request.getParameter("title")==null?"":request.getParameter("title"));
				cc.setGender(request.getParameter("gender")==null?"":request.getParameter("gender"));
				cc.setFirstName(request.getParameter("firstName")==null?"":request.getParameter("firstName"));
				cc.setLastName(request.getParameter("lastName")==null?"":request.getParameter("lastName"));
				cc.setNationality(request.getParameter("nationality")==null?"":request.getParameter("nationality"));
				cc.setDobDay(request.getParameter("dobDay")==null?"":request.getParameter("dobDay"));
				cc.setDobMonth(request.getParameter("dobMonth")==null?"":request.getParameter("dobMonth"));
				cc.setDobYear(request.getParameter("dobYear")==null?"":request.getParameter("dobYear"));
				cc.setCity(request.getParameter("city")==null?"":request.getParameter("city"));
				cc.setCityStatus(request.getParameter("cityStatus")==null?"":request.getParameter("cityStatus"));
				cc.setTelephone(request.getParameter("telephone")==null?"":request.getParameter("telephone"));
				cc.setMobile(request.getParameter("mobile")==null?"":request.getParameter("mobile"));
				cc.setFax(request.getParameter("fax")==null?"":request.getParameter("fax"));
				cc.setEmail(request.getParameter("email")==null?"":request.getParameter("email"));
				cc.setAddress1(request.getParameter("address1")==null?"":request.getParameter("address1"));
				cc.setAddress2(request.getParameter("address2")==null?"":request.getParameter("address2"));
				cc.setOccupation(request.getParameter("occupation")==null?"":request.getParameter("occupation"));
				cc.setEmirate(request.getParameter("emirate")==null?"":request.getParameter("emirate"));
				cc.setCountry(request.getParameter("country")==null?"":request.getParameter("country"));
				cc.setPoBox(request.getParameter("poBox")==null?"":request.getParameter("poBox"));
				cc.setOrgName(request.getParameter("orgName")==null?"":request.getParameter("orgName"));
				cc.setCustomerCode(request.getParameter("customerCode")==null?"":request.getParameter("customerCode"));
				cc.setArNo(request.getParameter("arNo")==null?"":request.getParameter("arNo"));
				cc.setCustomerName(request.getParameter("customerName")==null?"":request.getParameter("customerName"));
				String branch = (String)session.getAttribute("LoginBranchCode");
				String pids = (String)session.getAttribute("product_id");
					
					cc.setBranch(branch);

					error=cc.validateFields(pids);
					if(error.length()>0)
					{
						request.setAttribute("errorDetail",error);
						rd = getServletContext().getRequestDispatcher("/Customer.jsp");
					}
					else
					{
						String cusIds = (String)session.getAttribute("customer_id");
						String appNos = (String)session.getAttribute("application_no");
						String loginIds = (String)session.getAttribute("user");
						String cids = (String)session.getAttribute("company_id");
						pids = (String)session.getAttribute("product_id");
						String brokerBra = (String)session.getAttribute("LoginBranchCode");
						String referal="";
						if(cc.checkingMissippiCode(cusIds, request.getParameter("brokerId")))
						{
							//if("11".equalsIgnoreCase(pids))
								error="<br>*Missippi Customer Code Already Exists";
						}

						if(error.length()>0)
						{
							request.setAttribute("errorDetail",error);
							rd = getServletContext().getRequestDispatcher("/Customer.jsp");
						}
						else
						{
							cusIds = cusIds!=null?cusIds:"0";
							cusIds = cusIds.equals("null")?"0":cusIds;
							
							String cusVal=cc.storedValues(cusIds,request.getParameter("brokerId"),(String)session.getAttribute("AdminBranchCode"),pids);
							
							if("DIDN'T INSERTED".equalsIgnoreCase(cusVal) || "DIDN'T UPDATE".equalsIgnoreCase(cusVal))
							{
								error=" Invalid Data";
								request.setAttribute("errorDetail",error);
								rd = getServletContext().getRequestDispatcher("/Customer.jsp");
							}
							else
							{
								session.setAttribute("customer_id",cusVal==null?"":cusVal);
								cusIds = (String)session.getAttribute("customer_id");
								
								if(cusIds==null)
								{
									response.sendRedirect("login/error_messg.jsp");
									return;
								}
								if("others.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
								{
									String qno = "";
									qno = getQuote(cusIds,appNos,pids,loginIds,cids,brokerBra,rsaissuer,(String)session.getAttribute("AdminBranchCode"));
									session.setAttribute("quote_no",qno);
									dataCollection datas=new dataCollection();
									referal=datas.getReferalStatus(qno);
									request.setAttribute("referalStatus",referal);
								
								} 
								else if("showQuote.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
								{
									String qno = "";
									qno = getQuote(cusIds,appNos,pids,loginIds,cids,brokerBra,rsaissuer,(String)session.getAttribute("AdminBranchCode"));
									session.setAttribute("quote_no",qno);
									dataCollection datas=new dataCollection();
									referal=datas.getReferalStatus(qno);
									request.setAttribute("referalStatus",referal);
								}
								
							if("11".equalsIgnoreCase(pids))
							{
								if("premium/QuotationOpen.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
								{
									session.setAttribute("identify_Id",cusIds);
								}
								else
								{
									if(session.getAttribute("identify_Id")!=null)
									session.removeAttribute("identify_Id");
								}

							}
							else
							{
								if("premium/Quotation.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
								{
									session.setAttribute("identify_Id",cusIds);
								}
								else
								{
									if(session.getAttribute("identify_Id")!=null)
									session.removeAttribute("identify_Id");
								}
							}
						//For Freight Forwarder
						String freightUser="";
						if(session.getAttribute("freight")!=null)
							freightUser = (String)session.getAttribute("freight");
						String statuss = "";
						String statussNew = "";
						String directRateOption = "";
						String quote_no=(String)session.getAttribute("quote_no");
						if(quote_no!=null)
						{
							args = new String[1];
							args[0] = quote_no;
							query = "select STATUS from FREIGHT_POSITION_MASTER  where  quote_no=?";
							statuss = runner.singleSelection(query,args);
						}
                        statuss = (statuss==null?"":statuss);
						if(quote_no!=null)
						{
							String qno = quote_no;
							query ="select nvl(PROVISION_FOR_PREMIUM,' ') from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?) and product_id='3'";
							args = new String[1];
							args[0] = qno;
							statussNew = runner.singleSelection(query,args);
							if(statussNew.equalsIgnoreCase("D"))
							{
								args = new String[1];
								args[0] = qno;
								query = "select nvl(FREIGHT_RATE_OPTION,' ') from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?)  and product_id='3'";
								directRateOption = runner.singleSelection(query,args);
							}
						}
						//if(freightUser.equalsIgnoreCase("freight")&&!statuss.equalsIgnoreCase("A")&&!statussNew.equalsIgnoreCase("D"))
						if(freightUser.equalsIgnoreCase("freight")&&!statuss.equalsIgnoreCase("A")&&!directRateOption.equalsIgnoreCase("Y"))
						{
							String  cusname="";
							if(quote_no!=null)
							{
								args = new String[1];
								args[0] = quote_no;
								String que = "select nvl(first_name,last_name),company_name from personal_info where customer_id=(select customer_id from position_master where quote_no =?) ";
								System.out.println("Customername    "+que);
								String name[][] = runner.multipleSelection(que,args);
								if(name != null && name.length >0)
								{
									 cusname = name[0][0];
									 cusname= cusname==null?name[0][1]:cusname;
								}
								String freightBroker = "";
								if(loginIds!=null)
								{
									args = new String[1];
									args[0] = loginIds;
									query = "select nvl(company_name,' ') from BROKER_COMPANY_MASTER where AGENCY_CODE=(select OA_CODE from LOGIN_MASTER where login_id=?)";
									freightBroker =  runner.singleSelection(query,args);
								}
								String Freightmsg =" <font color=\"red\">"+ cusname+"</font> <Br> Your Broker <font color=\"red\">"+freightBroker+"</font> for the  <font color=\"red\"> Quote Number "+quote_no+"</font> Will Get Back To You for Pricing the Same";
								request.setAttribute("FreightUserRestricted",Freightmsg);
								rd = getServletContext().getRequestDispatcher("/Freight_ShowQuote.jsp");
							}
							else
							{
								rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
							}
						}
						else if("referal".equalsIgnoreCase(referal))
						{
							System.out.println("admin_referral_status ...........11111");
							rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
						}
						else
						{
							
							rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
						}
					}
				}
			}
	}
	
	else if("existingCustomer".equalsIgnoreCase(path))
	{
		com.maan.premium.DAO.PremiumLogic logics  = new com.maan.premium.DAO.PremiumLogic();
		String freightUser = "";
		System.out.println("RoyalTest redirect path in Controller "+request.getParameter("actionPath"));
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		if(brokerDetails.size()>0)
		{
			cid = (String)brokerDetails.get("Orgination");
		}
		if(!"null".equals(request.getParameter("customers")) && (request.getParameter("customers").trim()).length()>0 && !"0".equals(request.getParameter("customers")))
		{
			if(request.getParameter("customers")!=null && !("").equalsIgnoreCase(request.getParameter("customers")))
			{
				String referal="";
				
				session.setAttribute("customer_id",request.getParameter("customers")==null?"":request.getParameter("customers"));
				String cusIds = (String)session.getAttribute("customer_id");
				String appNos = (String)session.getAttribute("application_no");
				String loginIds = (String)session.getAttribute("user");
				String cids = (String)session.getAttribute("company_id");
				String pids = (String)session.getAttribute("product_id");
				brokerBra = (String)session.getAttribute("LoginBranchCode");
				String actPath = request.getParameter("actionPath");
				actPath = actPath==null?"":actPath;
				if("others.jsp".equalsIgnoreCase(actPath) || "showQuote.jsp".equalsIgnoreCase(actPath) || "SendingQuote".equalsIgnoreCase(actPath))
				{
					String quote_no = "";
					
						quote_no = getQuote(cusIds,appNos,pids,loginIds,cids,brokerBra,rsaissuer,(String)session.getAttribute("AdminBranchCode"));
						session.setAttribute("quote_no",quote_no);
					

					if(session.getAttribute("freight")!=null)
						freightUser = (String)session.getAttribute("freight");
					/*if(freightUser.equalsIgnoreCase("freight"))
                          logics.updateFreightStatus((String)session.getAttribute("quote_no"));*/
                    dataCollection datas=null;
					datas=new dataCollection();
					referal=datas.getReferalStatus(quote_no);
					request.setAttribute("referalStatus",referal);
					
				}
				if("premium/Quotation.jsp".equalsIgnoreCase(actPath)||"premium/QuotationOpen.jsp".equalsIgnoreCase(actPath))
				{
					session.setAttribute("identify_Id",cusIds);
				}
				else
				{
					if(session.getAttribute("identify_Id")!=null)
						session.removeAttribute("identify_Id");
				}
				String statuss = "";
				String directRateOption = "";
				String qno = "";
				if(session.getAttribute("quote_no")!=null)
				{
					qno = (String)session.getAttribute("quote_no");
					args = new String[1];
					args[0] = qno;
					
					query = "select nvl(PROVISION_FOR_PREMIUM,' ') from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?) and product_id='3'";
					statuss = runner.singleSelection(query,args);
					if(statuss.equalsIgnoreCase("D"))
					{
						args = new String[1];
						args[0] = qno;
						query = "select nvl(FREIGHT_RATE_OPTION,' ') from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?) and product_id='3'";
						directRateOption = runner.singleSelection(query,args);
					}
				}
                statuss = (statuss==null?"":statuss);
				if(freightUser.equalsIgnoreCase("freight")&&!directRateOption.equalsIgnoreCase("Y"))
				{
					args = new String[1];
					args[0] = qno;
					String  cusname="";
					String que = "select nvl(first_name,last_name),company_name from personal_info where customer_id=(select customer_id from position_master where quote_no = ?) ";
					String name[][] = runner.multipleSelection(que,args);
                    if(name != null && name.length >0)
					{
						 cusname = name[0][0];
						 cusname= cusname==null?name[0][1]:cusname;
					}
					String freightBroker = "";
					if(loginIds!=null)
					{	
						args = new String[1];
						args[0] = loginIds;
						query = "select nvl(company_name,' ') from BROKER_COMPANY_MASTER where AGENCY_CODE=(select OA_CODE from LOGIN_MASTER where login_id=?)";
						freightBroker =  runner.singleSelection(query,args);
					}
					String Freightmsg =" <font color=\"red\">"+ cusname+"</font> <Br> Your Broker <font color=\"red\">"+freightBroker+" </font> for the  <font color=\"red\"> Quote Number "+qno+"</font> Will Get Back To You for Pricing the Same";
					request.setAttribute("FreightUserRestricted",Freightmsg);
					rd = getServletContext().getRequestDispatcher("/Freight_ShowQuote.jsp");
				}
				else if("referal".equalsIgnoreCase(referal)&&"Broker".equalsIgnoreCase((String)session.getAttribute("usertype")))
				{
					System.out.println("RoyalTest update freight status if referral");
					args = new String[1];
					args[0] = appNos;
					query = "update freight_position_master set STATUS='T' where application_id=(select application_no from position_master where application_no=? and FREIGHT_STATUS='F')";
					runner.multipleUpdation(query,args);
					System.out.println("admin_referral_status ...........222222");
					rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
				}
				else if("referal".equalsIgnoreCase(referal))
				{
					System.out.println("admin_referral_status ...........33333");
					rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
				}
				else
				{
					com.maan.premium.DAO.PremiumLogic PreLogics  = new com.maan.premium.DAO.PremiumLogic();
					//Rajesh World Work Started
									
					PreLogics.setLoginBra(brokerBra);
					PreLogics.setCid(cid);
					
					HashMap PremiumDetailsHash = new HashMap();
					PremiumDetailsHash = PreLogics.getPremiumDetails(appNos);
					request.setAttribute("fullDetails",PremiumDetailsHash);
					rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
					
				}
				//Block added by Chinna
				runner.multipleUpdation("UPDATE PERSONAL_INFO A SET A.CUST_AR_NO = (SELECT (CASE WHEN CUSTCODE IS NULL OR CUSTGRP IS NULL OR CUSTTYPE IS NULL OR CUSTCLAS IS NULL THEN '' ELSE ( CUSTCODE || '-' || CUSTGRP || '-' || CUSTTYPE || '-' || CUSTCLAS) END) ARACC FROM C_CUST@ECARGO_"+("Test".equalsIgnoreCase(usrModeController)?"DEV":"PROD")+" WHERE CIMSNO = A.MISSIPPI_CUSTOMER_CODE AND BRCODE=?) WHERE A.CUSTOMER_ID = ?", new String[]{brokerBra,cusIds});
				request.setAttribute("minPremium", request.getParameter("minPremium"));
				//End
				/*if(freightUser.equalsIgnoreCase("freight"))
				{
					rd = getServletContext().getRequestDispatcher("/Freight_ShowQuote.jsp");
				}
				else
				{
					if("referal".equalsIgnoreCase(referal))
						rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
					else
						rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
				}*/
			}
		}
		else
		{
			request.setAttribute("errorDetail",runner.getErrormsg("46"));
			rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
		}
	}
	else if("adminReferal".equalsIgnoreCase(path))
	{
			System.out.println("  ADMIN  REFERAL----------------------------");
			request.setAttribute("adminReferals","referal");
			com.maan.premium.DAO.PremiumLogic logics=null;
			logics=new com.maan.premium.DAO.PremiumLogic();
			if(session.getAttribute("applicationNo")!=null)
				logics.setApplicationNo((String)session.getAttribute("applicationNo"));
			else if(session.getAttribute("application_no")!=null)
				logics.setApplicationNo((String)session.getAttribute("application_no"));

			//logics.setCon(com.maan.DBCon.DBConnection.getDBConnection1());
			logics.updateMarineDatas("0","0","0",request.getParameter("admin"),"","0");
			logics.updateRemarks("Referal");

			rd = getServletContext().getRequestDispatcher("/selectCustomer.do");

	}
	else if("deleteInfo".equalsIgnoreCase(path))
	{
			com.maan.services.customerInfo cc=new com.maan.services.customerInfo();
			String from = request.getParameter("from");
			String quoteNo = request.getParameter("Quote_No");
			String lapsedStatus = request.getParameter("LapsedStatus");
			String loginId = (String)session.getAttribute("userName");
			if("select".equalsIgnoreCase(lapsedStatus))
			{
			   error="<br>*Please Select the Lapsed Quote Reason";
				request.setAttribute("errorDetail",error);
				rd = getServletContext().getRequestDispatcher("/LapsedQuote1.jsp");
			}
			else
			{
		
				String quote = cc.deleteTable(quoteNo,lapsedStatus,loginId);
				request.setAttribute("quote_no",""+quote);
				request.setAttribute("from",from);
				rd = getServletContext().getRequestDispatcher("/Quotedelete.jsp");
			}
	}
	else if("PolicyRejectd".equalsIgnoreCase(path))
	{
					com.maan.premium.DAO.PremiumLogic logic=new com.maan.premium.DAO.PremiumLogic();
					String qno = request.getParameter("Quote_No");
					String refStatus = request.getParameter("referal_status");
					qno = qno==null?"0":qno;
					String base = request.getContextPath();
					
					refStatus = refStatus==null?"R":refStatus;
					logic.rejectRejectedStatus(qno,refStatus);
					String freightUser = "";
					
						String Status = "";
					if(refStatus.equalsIgnoreCase("accept"))
						Status = "A";
					else if(refStatus.equalsIgnoreCase("none"))
						Status = "U";
					else if(refStatus.equalsIgnoreCase("reject"))
						Status = "R";
					logic.updateFreightStatusAdmin(qno,Status);
					
					if(refStatus.equalsIgnoreCase("accept"))
						rd = getServletContext().getRequestDispatcher("/admin/Approved_Policy.jsp");
					else if(refStatus.equalsIgnoreCase("reject"))
						rd = getServletContext().getRequestDispatcher("/admin/Rejected_Policy.jsp");
					else if(refStatus.equalsIgnoreCase("none"))
						rd = getServletContext().getRequestDispatcher("/admin/Pending_Policy.jsp");
					
	}
	
	else if("opencoverdeleteInfo".equalsIgnoreCase(path))
	{
				com.maan.services.customerInfo cc=new com.maan.services.customerInfo();
				String s = request.getParameter("Quote_No");
				String from = request.getParameter("from");
				String s1 = request.getParameter("LapsedStatus");
				String s2 = (String)session.getAttribute("userName");
				if("select".equalsIgnoreCase(s1))
				{
					 error="<br>*Please Select the Lapsed Quote Reason";
					 request.setAttribute("errorDetail",error);
					rd = getServletContext().getRequestDispatcher("/LapsedQuote1.jsp");
				}
			    else
			   {

					String quote = cc.deleteTable(s,s1,s2);
					request.setAttribute("quote_no",""+quote);
					request.setAttribute("from",from);
					rd = getServletContext().getRequestDispatcher("/Quotedelete.jsp");
				}
		}
		else if("referalPolicys".equalsIgnoreCase(path))
		{
			String freightUser = "";
			if(session.getAttribute("freight")!=null)
			{
				freightUser = (String)session.getAttribute("freight");
			}
			if(freightUser.equalsIgnoreCase("freight"))
			{
				com.maan.services.util.dataCollection collect = new com.maan.services.util.dataCollection();
				String qnos = request.getParameter("quoteIds")==null?"":request.getParameter("quoteIds");
				if(session.getAttribute("quote_no")==null)
				{
					session.setAttribute("quote_no",qnos);
				}
				else
				{
					session.removeAttribute("quote_no");
					session.setAttribute("quote_no",qnos);
				}
				String friAppNo = collect.getFriAppNo(qnos);
				if(session.getAttribute("application_no")==null)
				{
					session.setAttribute("application_no",friAppNo);
				}
				else
				{
					session.removeAttribute("application_no");
					session.setAttribute("application_no",friAppNo);
				}
				String cusIDs = request.getParameter("openCustomerId")==null?"":request.getParameter("openCustomerId");
				if(session.getAttribute("customer_id")==null)
				{
					session.setAttribute("customer_id",cusIDs);
				}
				else
				{
					session.removeAttribute("customer_id");
					session.setAttribute("customer_id",cusIDs);
				}
				request.setAttribute("from","approved");
				rd = getServletContext().getRequestDispatcher("/others.jsp");
			}
			else
			{
				
				if(request.getParameter("quote_no")!=null)
					session.setAttribute("quote_no",request.getParameter("quote_no"));
				else{
					out.println("<br>    Quote_no from request is null-->"+request.getParameter("quote_no"));
					if(true) return;
				}
				if(request.getParameter("application_no")!=null)
					session.setAttribute("application_no",request.getParameter("application_no"));
				else{
					out.println("<br>    Quote_no from request is null-->"+request.getParameter("application_no"));
					if(true) 	return;
				}
				request.setAttribute("mailmode","false");
				request.setAttribute("from","approved");
				rd = getServletContext().getRequestDispatcher("/others.jsp");
				//if(true)return;
			}
			String openCoverNo = (String)session.getAttribute("openCoverNo");
			
			session.setAttribute("openCoverNo",openCoverNo);
			if(rd!=null)
			{
				rd.forward(request,response);
				if(true)return;
			}
			else
			{
				out.println("redirect path in Controller "+request.getParameter("actionPath")!=null?request.getParameter("actionPath"):"");
			}
			
	}
	else if("policyInfo".equalsIgnoreCase(path))
	{
		String uploadOption = request.getParameter("uploadOption")!=null?request.getParameter("uploadOption"):"";
		String pqno = request.getParameter("pqno")==null ? "" : request.getParameter("pqno");
		
			String apNo=(String)session.getAttribute("applicationNo");
			String  freight="";
			String  freight_status=request.getParameter("freight")==null?"":request.getParameter("freight");
			String userType = (String)session.getAttribute("usertype");
			
			String cusIds = (String)session.getAttribute("customer_id");
			String loginIds = (String)session.getAttribute("user");
			String pids = (String)session.getAttribute("product_id");
			String quote_no=(String)session.getAttribute("quote_no");

			//For Freight Forwarder..
			String freghtPolicyStatus = "";
			freghtPolicyStatus = request.getParameter("freightPolicyPer")==null?"":request.getParameter("freightPolicyPer");
			
            if(apNo!=null && userType.equalsIgnoreCase("Broker"))
			{
				args = new String[1];
				args[0] = apNo;
				query = "select FREIGHT_STATUS from position_master where application_no=?"; 
				freight = runner.singleSelection(query,args);
                if(freight != null && (freight.trim().equalsIgnoreCase("F")))
				{
					if(freight_status!=null && freight_status.length() > 0 &&!freight_status.trim().equals("null") )
					{
						 if(freight_status.equalsIgnoreCase("Y"))
							 freight_status = "A";
						 else if(freight_status.equalsIgnoreCase("R"))
							 freight_status = "R";
						 
						 args = new String[3];
						 args[0] = freight_status;
						 args[1] = freghtPolicyStatus;
						 args[2] = apNo;
						 query = "update freight_position_master set status=?,ALLOW_TO_GENERATE_POLICY_STS=? where application_id=?";

						 runner.multipleUpdation(query,args);
						 
						if(freight_status.equalsIgnoreCase("R"))
						{
							 args = new String[2];
							 args[0] = freight_status;
							 args[1] = apNo;
							 query = "update POSITION_MASTER set status=? where application_no=?";
							 runner.multipleUpdation(query,args);
						}
					}
				}
				 args = new String[1];
				 args[0] = apNo;
				 query = "select status from freight_position_master where application_id=?";
                freight_status = runner.singleSelection(query,args);
			}
			
			String redirect=""; String Freightmsg = "";

			if((freight_status==null||freight_status.length()<=0)||(!freight_status.equalsIgnoreCase("R")&&!freight_status.equalsIgnoreCase("N")))
			{
				System.out.println("Inside Bloack:::");
				com.maan.services.policyInfo policys = new com.maan.services.policyInfo();
				policys.setBlNo(request.getParameter("blNo")==null?"":request.getParameter("blNo"));
				/*policys.setBlDay(request.getParameter("blDay")==null?"":request.getParameter("blDay"));
				policys.setBlMonth(request.getParameter("blMonth")==null?"":request.getParameter("blMonth"));
				policys.setBlYear(request.getParameter("blYear")==null?"":request.getParameter("blYear"));*/
				policys.setAgentId(request.getParameter("agentId")==null?"":request.getParameter("agentId"));
				policys.setAgentName(request.getParameter("agentName")==null?"":request.getParameter("agentName"));
				policys.setCarrieerName(request.getParameter("carrieerName")==null?"":request.getParameter("carrieerName"));
				policys.setLcNo(request.getParameter("lcNo")==null?"":request.getParameter("lcNo"));
				policys.setBankName(request.getParameter("bankName")==null?"":request.getParameter("bankName"));
				/*policys.setLcDay(request.getParameter("lcDay")==null?"":request.getParameter("lcDay"));
				policys.setLcMonth(request.getParameter("lcMonth")==null?"":request.getParameter("lcMonth"));
				policys.setLcYear(request.getParameter("lcYear")==null?"":request.getParameter("lcYear"));*/
				policys.setPartialAllowed(request.getParameter("partialAllowed")==null?"":request.getParameter("partialAllowed"));
				policys.setSdiscount(request.getParameter("sdiscount")==null?"":request.getParameter("sdiscount"));
				/*policys.setSailingDay(request.getParameter("sailingDay")==null?"":request.getParameter("sailingDay"));
				policys.setSailingMonth(request.getParameter("sailingMonth")==null?"":request.getParameter("sailingMonth"));
				policys.setSailingYear(request.getParameter("sailingYear")==null?"":request.getParameter("sailingYear"));*/	
				if("Y".equalsIgnoreCase(request.getParameter("brokerStatus")))
					policys.setBrokerRemarks(request.getParameter("brokerRemarks")==null?"":request.getParameter("brokerRemarks"));
				else
					policys.setBrokerRemarks("No");
				//Added by Chinna
				policys.setLcDate(request.getParameter("lcDate")==null?"":request.getParameter("lcDate"));
				policys.setBlDate(request.getParameter("blDate")==null?"":request.getParameter("blDate"));
				policys.setSailingDate(request.getParameter("sailingDate")==null?"":request.getParameter("sailingDate"));
				//End
				//Added by Hari
				policys.setPackType(request.getParameter("packType")==null?"":request.getParameter("packType"));
				policys.setContractNo(request.getParameter("contractNo")==null?"":request.getParameter("contractNo"));
				policys.setFmsCaseNo(request.getParameter("fmsCaseNo")==null?"":request.getParameter("fmsCaseNo"));
				policys.setReferenceNo(request.getParameter("referenceNo")==null?"":request.getParameter("referenceNo"));
				//End
				String from=request.getParameter("from")==null?"":request.getParameter("from");
				String brokerBra = (String)session.getAttribute("LoginBranchCode");
				String[][] commodityValue=policys.getCommodity(apNo,brokerBra);
				java.util.HashMap commodiyValidation=new java.util.HashMap();
				java.util.HashMap cV=new java.util.HashMap();
				
				for(int k=0;k<commodityValue.length;k++)
				{
					commodiyValidation.put("supplier"+(k+1),request.getParameter("supplier_"+commodityValue[k][0])==null?"":request.getParameter("supplier_"+commodityValue[k][0]));
					commodiyValidation.put("desc"+(k+1),request.getParameter("desc_"+commodityValue[k][0])==null?"":request.getParameter("desc_"+commodityValue[k][0]));
					commodiyValidation.put("invoiceno"+(k+1),request.getParameter("invoiceno_"+commodityValue[k][0])==null?"":request.getParameter("invoiceno_"+commodityValue[k][0]));
					cV.put("commodity"+(k+1),commodiyValidation);
				}
				
			//if(!"ViewQuote_B2B.jsp".equalsIgnoreCase(request.getParameter("actionPath"))&&!"RefferalDisplay_Approved.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
			{
					 policys.setCommodityValidation(cV);
					error=policys.validateFields(apNo,pids,request.getParameter("adminReferals"),quote_no,session.getAttribute("user").toString());
					
			}
			boolean freightQuote = false;
					if(session.getAttribute("quote_no")!=null)
						freightQuote = policys.getFreightQuoteStatus(quote_no);
					
					/*if((freightQuote&&"Broker".equals(session.getAttribute("usertype"))))
					{
						if(error.length()>0)
							error = "";
					}*/
					if(error.length()>0)
					{
						System.out.println("Errors::>"+error);
						request.setAttribute("from",from);
						request.setAttribute("errorDetail",error);
						rd = getServletContext().getRequestDispatcher("/others.jsp");
					}
					else
					{
						String flag = "";
						
						flag = policys.storeValues(quote_no,cusIds,request.getParameter("adminReferals"));
						
						if(flag.equalsIgnoreCase("DBEroor"))
						{
							response.sendRedirect("login/error_messg.jsp");
							return;
						}
						if("true".equalsIgnoreCase(flag))
						{
							String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
							String tempR = request.getParameter("adminReferalRemarks")!=null?request.getParameter("adminReferalRemarks"):"";
							args = new String[3];
							args[0] = temp;
							args[1] = tempR;
							args[2] = apNo;
							query = "update marine_data set admin_referral_status=?,remarks=? where application_no=?";
							runner.multipleUpdation(query,args);
							request.setAttribute("quote_no",quote_no);
							request.setAttribute("referalStatus","referal");
							System.out.println("admin_referral_status ...........44444444");
							rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
						}
						else
						{
							String PackageResult = ""; 
							for(int i=0;i<commodityValue.length;i++)
							{
								PackageResult = policys.storeCommodity(apNo,commodityValue[i][0],request.getParameter("supplier_"+commodityValue[i][0]),request.getParameter("desc_"+commodityValue[i][0]),request.getParameter("invoiceno_"+commodityValue[i][0]));
							}
							if(PackageResult.equalsIgnoreCase("DIDN'T UPDATE"))
							{
								response.sendRedirect("login/error_messg.jsp");
								return;
							}
							
				//New Modified on Aug14th - if("clausesEdit.jsp".equalsIgnoreCase(request.getParameter("actionPath")))				
				HashMap preDetails = policys.getResults(quote_no,cusIds,apNo,(String)session.getAttribute("company_id"),loginIds);
				//out.println("royal test.preDet.."+preDet.size());if(true)return;
				request.setAttribute("premiumresults",preDetails);
				
						String broker_referal = request.getParameter("brokerReferals");
						broker_referal = broker_referal==null?"":broker_referal;
						String broker_referal_remarks = request.getParameter("brokerReferalRemarks");
						broker_referal = broker_referal==null?"":broker_referal;
						broker_referal_remarks = broker_referal_remarks==null?"":broker_referal_remarks;
						if(broker_referal.equalsIgnoreCase("Y"))
						{
							args = new String[2];
							args[0] = broker_referal_remarks;
							args[1] = quote_no;
							query = "update position_master set FREIGHT_STATUS = 'F',FREIGHT_REMARKS =? where quote_no=?";
							runner.multipleUpdation(query,args);
                        }
						//Modified by Rajesh
						String statuss = "";
						args = new String[1];
						query = "select STATUS from FREIGHT_POSITION_MASTER  where  quote_no=?";
						args[0] = quote_no;
						statuss = runner.singleSelection(query,args);
                        // userType = (userType==null?"":userType);
                        statuss = (statuss==null?"":statuss);
						if(session.getAttribute("freight")!=null)
							userType = (String)session.getAttribute("freight");
						String statussNew = "";
						String directRateOption = "";
						if(session.getAttribute("quote_no")!=null)
						{
							String qno = (String)session.getAttribute("quote_no");
							args = new String[1];
							args[0] = qno;
							query = "select nvl(PROVISION_FOR_PREMIUM,' ') from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?) and product_id='3'";
							statussNew = runner.singleSelection(query,args);
							if(statussNew.equalsIgnoreCase("D"))
							{
								args = new String[1];
								args[0] = qno;
								query = "select nvl(FREIGHT_RATE_OPTION,' ') from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?) and product_id='3'";
								directRateOption = runner.singleSelection(query,args);
							}
						}
						//if(userType.equalsIgnoreCase("freight")&&!statuss.equalsIgnoreCase("A")&&!statussNew.equalsIgnoreCase("D"))
						
					if(userType.equalsIgnoreCase("freight")&&!statuss.equalsIgnoreCase("A")&&!directRateOption.equalsIgnoreCase("Y"))
					{
							 String  cusname="";
							 args = new String[1];
							 args[0] = quote_no;
							 String que = "select nvl(first_name,last_name),company_name from personal_info where customer_id=(select customer_id from position_master where quote_no =?)";
							 
							 String name[][] = runner.multipleSelection(que,args);

                            if(name != null && name.length >0)
							{
                                 cusname = name[0][0];
								 cusname= cusname==null?name[0][1]:cusname;
							}
							String freightBroker = "";
							if(session.getAttribute("user")!=null)
							{
								args = new String[1];
								args[0] = loginIds;
								query = "select nvl(company_name,' ') from BROKER_COMPANY_MASTER where AGENCY_CODE=(select OA_CODE from LOGIN_MASTER where login_id=?)";

								freightBroker =  runner.singleSelection(query,args);
							}
							if(broker_referal.equalsIgnoreCase("Y"))
							{
								  Freightmsg =  "<font color=\"red\">"+cusname+"</font> <Br> Your Quote <font color=\"red\"> Quote Number "+quote_no+"</font>has been sent for Broker Referal. Your Broker <font color=\"red\">"+freightBroker+"</font> Will Get Back To You";
								  redirect = "ShowQuote";
							}
							else
							{
									Freightmsg =" <font color=\"red\">"+ cusname+"</font> <Br> Your Broker <font color=\"red\">"+freightBroker+"</font> for the  <font color=\"red\"> Quote Number "+quote_no+"</font> Will Get Back To You for Pricing the Same";
									redirect = "FreightShowQuote";
							}
					}

					java.util.HashMap resultValues=null;
					resultValues=(java.util.HashMap)request.getAttribute("premiumresults");

					if("Y".equalsIgnoreCase(request.getParameter("adminReferals")))
					{
						com.maan.premium.DAO.PremiumLogic logics11=null;
						logics11=new com.maan.premium.DAO.PremiumLogic();
						logics11.setApplicationNo(apNo);
						String[][] existingStatus11=new String[0][0];
						existingStatus11=logics11.getAdminReferalStatus();
						/*String existingStatusAppend11="";
						if(existingStatus11.length<0)
						{

						}
						else
						{
							existingStatusAppend11=existingStatus11[0][1]==null?existingStatusAppend11:existingStatus11[0][1];
							if((existingStatusAppend11.trim()).length() >0 && !("Normal".equalsIgnoreCase(existingStatusAppend11)) )
							{
								existingStatusAppend11="~"+existingStatusAppend11;
							}
							else
							{
								existingStatusAppend11="";
							}
						}*/
						String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
						String tempR = request.getParameter("adminReferalRemarks")!=null?request.getParameter("adminReferalRemarks"):"";
						tempR = tempR.replaceAll("'","''");
						
//						runner.updation("update marine_data set admin_referral_status='"+temp+"',remarks='"+tempR+""+(existingStatusAppend11.replaceAll("'","''"))+"' where application_no='"+apNo+"'");
						runner.updation("update marine_data set admin_referral_status='"+temp+"',remarks='"+tempR+"' where application_no='"+apNo+"'");

						if(statuss != null && statuss.equalsIgnoreCase("B"))
						{
							args = new String[1];
							args[0] = quote_no;
							query = "update position_master set FREIGHT_STATUS = 'F' where quote_no=?";
							runner.multipleUpdation(query,args);
						}
					}
					else
					{
						String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";

						args = new String[2];
						args[0] = temp;
						args[1] = apNo;
						query = "update marine_data set admin_referral_status=? where application_no=?";
						runner.multipleUpdation(query,args);
					}
					
					boolean mrFlag = false; // Checking for Marine Result Table got entries or not..
					mrFlag = policys.checkingForMarineResult((String)session.getAttribute("quote_no"));
					if(mrFlag)
					{	
						if(redirect.equalsIgnoreCase("FreightShowQuote"))
						{
								request.setAttribute("FreightUserRestricted",Freightmsg);
								rd = getServletContext().getRequestDispatcher("/Freight_ShowQuote.jsp");
						}
						else if(redirect.equalsIgnoreCase("ShowQuote"))
						{
							request.setAttribute("FreightUserRestricted",Freightmsg);
							System.out.println("admin_referral_status ...........5555555");
							rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
						}
						else if("referal".equalsIgnoreCase(getReferalStatusPosition(quote_no)))
						{
								request.setAttribute("referalStatus","referal");
								System.out.println("admin_referral_status ...........666666");
								rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
						}
						else if (uploadOption.equalsIgnoreCase("Y")){
							request.setAttribute("pqno",pqno);
							request.setAttribute("scrnFrom","Settling");
							//rd = getServletContext().getRequestDispatcher("/marineUploadDownload.jsp");
							response.sendRedirect(request.getContextPath()+"/marineUploadDownload.jsp?pqno="+pqno+"&scrnFrom=Settling&from="+from);
							if(true)return;
						}
						else
						{
							String opt = request.getParameter("saveBtn") == null ? "" : request.getParameter("saveBtn"); 
							if(opt.equalsIgnoreCase("save"))
								rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
							else
								rd = getServletContext().getRequestDispatcher("/final1.jsp");
							//rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
						}
					}// Marine Result Checking 
				}
			}
					System.out.println("End Bloack::");
		}
		else
		{
			if(freight_status.equalsIgnoreCase("R"))
				request.setAttribute("freightRejected","yes");
			else if(freight_status.equalsIgnoreCase("N"))
				request.setAttribute("freightRejected","None");
			rd = getServletContext().getRequestDispatcher("/Freight_ShowQuote.jsp");
		}
	} //New Version Completed....
else if("dataTransfer".equalsIgnoreCase(path))
{
			try
			{
				String numbers="";
				String branch = (String)session.getAttribute("LoginBranchCode");
				HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
				
				numbers=request.getParameter("optionSearch");
				System.out.println("optionSearch  --->"+numbers);
				
				String quoteNos[][] = new String[0][0];
				String args1[] = new String[1];
				args1[0] = request.getParameter("quotes");
				if("policy".equalsIgnoreCase(numbers)){
					quoteNos = runner.multipleSelection("select quote_no from position_master where policy_no=? and status='P' order by quote_no",args1);
				}else{
					quoteNos = runner.multipleSelection("select quote_no from position_master where quote_no in(?) and status='P'",args1);
				}
				numbers = numbers == null?"DIDN'T SELECTED":numbers.trim();
				if(quoteNos.length<=0)
				{
					request.setAttribute("error","Invalid Number");
					rd = getServletContext().getRequestDispatcher("/missipi/DataTransfer.jsp");

				}
				else
				{
					com.maan.services.CoreApplicationIntegration engine=null;
					engine = new com.maan.services.CoreApplicationIntegration();
					
					String cid="";
					
					if(brokerDetails.size()>0)
					{
						cid = (String)brokerDetails.get("Orgination");
					}
					engine.setLoginBra(branch);
					engine.setBcid(cid);
					StringBuffer s=null;
					s=request.getRequestURL();
					boolean changeStatus=false;
					String concatQuotes = "";
					if(quoteNos.length>1)
					{
						/*engine.setProcess("Multiple");
						for(int i=0;i<quoteNos.length;i++)
						{
							if(i == 0)
								engine.setMultipleCheck(true);
							else
								engine.setMultipleCheck(false);
							changeStatus=engine.LoadingData(quoteNos[i][0]);
							concatQuotes = concatQuotes+quoteNos[i][0]+",";
						}*/
					}
					else
					{
						//engine.setMultipleCheck(true);
						changeStatus=engine.createMasterQry(quoteNos[0][0]);
						session.setAttribute("quote_no",quoteNos[0][0]);
					}
					/*if(concatQuotes.length()>1&&!changeStatus)
					{
						concatQuotes = concatQuotes.substring(0,concatQuotes.length()-1);
						engine.processDeclaration(concatQuotes);
						session.setAttribute("quote_no",concatQuotes);
					}*/
					
					if(!changeStatus){
						request.setAttribute("error","DataTransferFailed");
					}
					rd = getServletContext().getRequestDispatcher("/missipi/showTables.jsp");
				}
			}
			catch(Exception e)
			{
				System.out.println("DataTransefr in Controller.java"+e.toString());
				e.printStackTrace();
			}

	}
	else if("FreightBroker".equalsIgnoreCase(path))
	{		
		String applicationNo="";
		String err = "";
		boolean	flag=true;//sep08 Excess
		String loadDiscountOption = "";  
		String extra_premium = "" ;
		String total_premiums = "";
		String extra_premium_payable = ""; 
		String freightQuote =""; 
		String freightbroker = "";
		String minPremium = "";
		String freightLoadDis[][] = new String[0][0];
		double exPrem = 0;
		double totPrem = 0;
		double dobMinPre = 0;
		double exPremPay = 0;
		double temp=0;
		double freLoadDB = 0;
		double freDiscountDB = 0;
		dataCollection datas=new dataCollection();
		loadDiscountOption = request.getParameter("loadDiscountOption") == null ? "" : request.getParameter("loadDiscountOption");
		extra_premium = request.getParameter("extra_premium") == null ? "0" : request.getParameter("extra_premium");
		total_premiums =  request.getParameter("total_premiums1") == null ? "0" : request.getParameter("total_premiums1");
		extra_premium_payable = request.getParameter("extra_premium_payable") == null ? "0" : request.getParameter("extra_premium_payable");
		freightQuote = request.getParameter("freightQuote") == null ? "" : request.getParameter("freightQuote");
		freightbroker = request.getParameter("freightbroker") == null ? "" : request.getParameter("freightbroker");			
		minPremium = request.getParameter("minPremium") == null ? "" : request.getParameter("minPremium");			
		request.setAttribute("minPremium",minPremium);
		//RequestDispatcher dispatcher;
       	applicationNo=(String)session.getAttribute("applicationNo");
		com.maan.premium.DAO.PremiumLogic premiumLogic = new com.maan.premium.DAO.PremiumLogic();
		com.maan.premium.DAO.PremiumInputsBean premiumInputsBean = new com.maan.premium.DAO.PremiumInputsBean();
		//Rajesh World Work Started
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		if(brokerDetails.size()>0)
		{
			cid = (String)brokerDetails.get("Orgination");
		}
		premiumLogic.setLoginBra(brokerBra);
		premiumLogic.setCid(cid);
	//
		HashMap PremiumDetailsHash = premiumLogic.getPremiumDetails(applicationNo);
		request.setAttribute("fullDetails",PremiumDetailsHash);
		freightLoadDis = premiumInputsBean.getFreightLoadDiscount(applicationNo);
		args = new String[2];
		args[0] = total_premiums;
		args[1] = applicationNo;
		query = "update marine_data set premium=? where application_no=?";
		//runner.multipleUpdation(query,args); 
		//exPrem = Double.parseDouble(extra_premium);
		totPrem = Double.parseDouble(total_premiums);
		exPremPay = Double.parseDouble(extra_premium_payable);
		String freightAppOpt = request.getParameter("freight")==null?"":request.getParameter("freight");
		String adminReferals = "";
		adminReferals = request.getParameter("adminReferals");
		adminReferals = adminReferals == null ? "" : adminReferals;

		if(!"Y".equalsIgnoreCase(adminReferals))
		{
			if(freightAppOpt.length()<=0)
				err = err+"Please select anyone option, whether would you like to accept this Quote.<br/>";
		}
		
		if(freightLoadDis.length >0)
		{
			freightLoadDis[0][0] =  freightLoadDis[0][0] == null ? "" :freightLoadDis[0][0];
			freightLoadDis[0][1] =  freightLoadDis[0][1] == null ? "" :freightLoadDis[0][1];
			freightLoadDis[0][2] =  freightLoadDis[0][2] == null ? "" :freightLoadDis[0][2];
			freightLoadDis[0][3] =  freightLoadDis[0][3] == null ? "" :freightLoadDis[0][3];
		}
		
		freLoadDB = Double.parseDouble(freightLoadDis[0][2]);
		freDiscountDB = Double.parseDouble(freightLoadDis[0][3]);
		
		//extra_premium checking Sep-08

		flag = datas.validAmount(extra_premium);
		
		if(extra_premium.equalsIgnoreCase(""))
		{
			err = err + "Extra Premium value should not be empty";
			extra_premium="0";
		}
		else
		{
			if(!flag)
			{
				err = err + "Please Enter Valid Extra Premium Value";
				extra_premium="0";
			}
			else
			{	
				exPrem = Double.parseDouble(extra_premium);
				if(freightbroker.equalsIgnoreCase("freightBroker") && freightQuote.equalsIgnoreCase("true") )
				{			
					String[][] minPrem=new String[0][0];
					if(loadDiscountOption.equalsIgnoreCase("select"))
					{
						err = err + "Please select Excess Premium Option";
					}
					else
					{
						temp = ( exPrem / totPrem ) * 100; 
						try
						{
							minPrem=premiumInputsBean.getFreightMinPremQuoteNo((String)session.getAttribute("quote_no"));
						
							if(minPrem.length>0)
							{
								dobMinPre = Double.parseDouble((minPrem[0][1]!=null?minPrem[0][1]:"0"));
							}
						}
						catch(Exception e){}
						if(loadDiscountOption.equalsIgnoreCase("+"))
						{						
							if(temp >  freLoadDB)
								err = err + "Excess Premium Exceed the Maximum Loading Limit "+freightLoadDis[0][2]+"%";
						}
						else if(loadDiscountOption.equalsIgnoreCase("-"))
						{		
							if(dobMinPre==totPrem&&exPrem>0)
							{
								err = err + "Premium is Minimum Premium, Not possible to Give Discount Premium";
								extra_premium="0";
							}
							else
							{
								if(temp > freDiscountDB)
									err = err + "Excess Premium Exceed the Maximum Discount Limit "+freightLoadDis[0][3]+"%";
							}
						}
					}//else
				}
			}// Invalid Excess Premium
		}//empty String
		System.out.println("<<<temp>>"+temp);
		System.out.println("<<<<ERR>>"+err);	
		if( err.length() > 0 )
		{
			request.setAttribute("err",err);
			request.setAttribute("FBExtra_Premium",extra_premium);
			rd = getServletContext().getRequestDispatcher("/premium/Premium.jsp");
			//dispatcher = request.getRequestDispatcher(path+"/premium/Premium.jsp");
		}
		else
		{
			rd = getServletContext().getRequestDispatcher("/selectCustomer.do");
			//dispatcher = request.getRequestDispatcher(path+"/selectCustomer.do");
		}
		/*if(dispatcher != null)
			dispatcher.forward(request, response);*/
	}

	/**Branch Restriction for Loading Discount **/

	else if("LoadDiscountBroker".equalsIgnoreCase(path))
	{		
		String applicationNo="";
		String err = "";
		String loadDiscountOption = "";  
		String extra_premium = "" ;
		String total_premiums = "";
		String extra_premium_payable = ""; 
		String minPremium = "";
		String brokerLoadDis[][] = new String[0][0];
		boolean flag = true;
		double exPrem = 0;
		double totPrem = 0;
		double dobMinPre = 0;
		double exPremPay = 0;
		double temp=0;
		double broLoadDB = 0;
		double broDiscountDB = 0;
		double minPrem=0;
		String taxPremium="";
		String loginId="";
		String issuance = "";	
		String defaultIssuance = "";
		loginId = (String) session.getAttribute("user");
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		dataCollection datas=new dataCollection();
		loadDiscountOption = request.getParameter("loadDiscountOption") == null ? "" : request.getParameter("loadDiscountOption");
		extra_premium = request.getParameter("extra_premium") == null ? "0" : request.getParameter("extra_premium");
		//extra_premium checking senthil-08
		String totalMarineWar =  request.getParameter("total")==null?"0": request.getParameter("total");
		total_premiums =  request.getParameter("total_premiums1") == null ? "0" : request.getParameter("total_premiums1");
		issuance =  request.getParameter("issuance") == null ? "0" : request.getParameter("issuance");
		defaultIssuance = request.getParameter("issuanceDefault") == null ? "0" : request.getParameter("issuanceDefault");
		taxPremium =  request.getParameter("taxPremium") == null ? "0" : request.getParameter("taxPremium");
		extra_premium_payable = request.getParameter("extra_premium_payable") == null ? "0" : request.getParameter("extra_premium_payable");
		minPremium = request.getParameter("minPremium") == null ? "" : request.getParameter("minPremium");			
		request.setAttribute("minPremium",minPremium);
		System.out.println("minPremium:::"+minPremium);
		applicationNo=(String)session.getAttribute("applicationNo");
		com.maan.premium.DAO.PremiumLogic premiumLogic = new com.maan.premium.DAO.PremiumLogic();
		com.maan.premium.DAO.PremiumInputsBean premiumInputsBean = new com.maan.premium.DAO.PremiumInputsBean();
		//Rajesh World Work Started
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		if(brokerDetails.size()>0)
		{
			cid = (String)brokerDetails.get("Orgination");
		}
		premiumLogic.setLoginBra(brokerBra);
		premiumLogic.setCid(cid);
	//	
		System.out.println("applicationNo "+applicationNo);
		HashMap PremiumDetailsHash = premiumLogic.getPremiumDetails(applicationNo);
		request.setAttribute("fullDetails",PremiumDetailsHash);
		brokerLoadDis = premiumInputsBean.getBrokerLoadDiscount(loginId);
		 if((request.getParameter("linkFrom")==null && request.getParameter("isBack")==null)){
		
		if(loadDiscountOption.equalsIgnoreCase("+"))
		{	
			totPrem = Double.parseDouble(total_premiums);
			totPrem = totPrem-Double.parseDouble(taxPremium);
			exPrem = Double.parseDouble(extra_premium);
			temp = ( exPrem / totPrem ) * 100; 				
			if(temp < broLoadDB)
			{
				args = new String[3];
				System.out.println("Issuance:"+issuance);
				System.out.println("defaultIssuance:"+defaultIssuance);
			
				String total = request.getAttribute("total")==null?"0": request.getAttribute("total").toString();
				System.out.println("before"+total);
				System.out.println("CAlcu:"+(Float.parseFloat(total)+Float.parseFloat(issuance)));
				total_premiums = Float.toString(Float.parseFloat(total)+(Float.parseFloat(issuance)));
				
				System.out.println("Total premium after::"+total_premiums);
				//request.setAttribute("total_premium", request.getAttribute("total")==null?"0": request.getAttribute("total").toString());
				args[0] = total_premiums;
				args[1] = issuance;
				args[2] = applicationNo;
			query = "update marine_data set premium=? ,policy_fee=? where application_no=?";
			runner.multipleUpdation(query,args);
			}
		}
		}
		totPrem = Double.parseDouble(total_premiums);
		totPrem = totPrem-Double.parseDouble(taxPremium);
		exPremPay = Double.parseDouble(extra_premium_payable);
		String adminReferals = "", loadingPercent="0", discountPercent="0";
		adminReferals = request.getParameter("adminReferals");
		adminReferals = adminReferals == null ? "" : adminReferals;

		System.out.println("brokerLoadDis.length"+brokerLoadDis.length);
		if(brokerLoadDis.length >0)
		{
			brokerLoadDis[0][0] =  brokerLoadDis[0][0] == null ? "" :brokerLoadDis[0][0];
			brokerLoadDis[0][1] =  brokerLoadDis[0][1] == null ? "0" :brokerLoadDis[0][1];
			brokerLoadDis[0][2] =  brokerLoadDis[0][2] == null ? "0" :brokerLoadDis[0][2];
			brokerLoadDis[0][3] =  brokerLoadDis[0][3] == null ? "0" :brokerLoadDis[0][3];
			brokerLoadDis[0][4] =  brokerLoadDis[0][4] == null ? "0" :brokerLoadDis[0][4];
		}
		if(brokerLoadDis.length >0 && "3".equals((String)session.getAttribute("product_id"))){
			broLoadDB = Double.parseDouble(brokerLoadDis[0][2]);
			broDiscountDB = Double.parseDouble(brokerLoadDis[0][3]);
			minPrem = Double.parseDouble(brokerLoadDis[0][4]);
			loadingPercent=brokerLoadDis[0][2];
			discountPercent=brokerLoadDis[0][3];
		}
		if(extra_premium.equalsIgnoreCase(""))
		{
			err = err + "Extra Premium value should not be empty";
			extra_premium="0";
		}
		else
		{
			flag = datas.validAmount(extra_premium);
			if(!flag)
			{
				err = err + "Please Enter Valid Extra Premium Value";
				extra_premium="0";
			}
			else
			{	
				exPrem = Double.parseDouble(extra_premium);
				//need to be check with senthil
				//if(broLoadDB > 0 || broDiscountDB > 0 ) 
				{			
					if(loadDiscountOption.equalsIgnoreCase("select"))
					{
						err = err + "Please select Excess Premium Option";
					}
					else
					{
						temp = ( exPrem / totPrem ) * 100; 
						// As per emirates issuance fee should be excluded while checking for the below limit
						double temp2 = ( exPrem / (Float.parseFloat(totalMarineWar)) ) * 100; 
						//double temp2 = ( exPrem / (totPrem-(Double.parseDouble(issuance))) ) * 100; 
						
						try
						{
							if(minPrem > 0)
							{	
								dobMinPre = minPrem;
							}
						}
						catch(Exception e){}
						if(loadDiscountOption.equalsIgnoreCase("+"))
						{						
							// As per emirates issuance fee should be excluded while checking for the below limit
							if(temp2 >  broLoadDB)
								err = err + "Excess Premium Exceed the Maximum Loading Limit "+loadingPercent+"%";
						}
						else if(loadDiscountOption.equalsIgnoreCase("-"))
						{		
							if(dobMinPre==totPrem&&exPrem>0)
							{
								err = err + "Premium is Minimum Premium, Not possible to Give Discount Premium";
								extra_premium="0";
							}
							else
							{
								// As per emirates issuance fee should be excluded while checking for the below limit
								if(temp2 > broDiscountDB)
									err = err + "Excess Premium Exceed the Maximum Discount Limit "+discountPercent+"%";
							}
						}
					}//else
				}
				/*else
				{
					if(broLoadDB==0)
						err = err + "Loading limit is"+broLoadDB+"%";
					if(broDiscountDB==0)
						err = err + "Discount limit is"+broDiscountDB+"%";
				}*/
			}//Invalid Excess Premium
		}//empty String
		if("Y".equalsIgnoreCase(request.getParameter("adminReferals")) && (request.getParameter("adminReferalRemarks")==null || request.getParameter("adminReferalRemarks").equals("")))
			err+="Please Enter Referral Comments";
		
		if( err.length() > 0 )
		{
			request.setAttribute("err",err);
			request.setAttribute("FBExtra_Premium",extra_premium);
			rd = getServletContext().getRequestDispatcher("/premium/Premium.jsp?isBack=yes");
		}
		else
		{
			rd = getServletContext().getRequestDispatcher("/selectCustomer.do");
		}
	}

	/**Branch Restriction for Loading Discount **/
	else if("selectCustomer".equalsIgnoreCase(path))
	{
		   String apNo = (String)session.getAttribute("applicationNo");
		   System.out.println("***Start Updation Premium***");
		   if((request.getParameter("linkFrom")==null && request.getParameter("isBack")==null)){
		   String   issuance =  request.getParameter("issuance") == null ? "0" : request.getParameter("issuance");
		   String 	defaultIssuance = request.getParameter("issuanceDefault") == null ? "0" : request.getParameter("issuanceDefault");
			System.out.println("Issuance:"+issuance);
			System.out.println("defaultIssuance:"+defaultIssuance);
			String total =request.getParameter("total")==null ?"0" : request.getParameter("total"); 
			
			String total_premiums = Float.toString(Float.parseFloat(total)+Float.parseFloat(issuance));
			System.out.println("before"+total);
			System.out.println("Total premium after::"+total_premiums);
			//request.setAttribute("total_premium", request.getAttribute("total")==null?"0": request.getAttribute("total").toString());
			String ar[] = new String [3];
			ar[0] = total_premiums;
			ar[1] = issuance;
			ar[2] = apNo;
			
			query = "update marine_data set premium=? ,policy_fee=? where application_no=?";
			runner.multipleUpdation(query,ar);
			if(Double.parseDouble(total_premiums)==0){
				runner.multipleUpdation("UPDATE MARINE_DATA SET TOTAL_WAR_CHARGES=0 WHERE APPLICATION_NO=?", new String[]{apNo});
			}
			}
			System.out.println("***End Updation Premium***");
			
			String  freight="";
		    String userType = (String)session.getAttribute("usertype");
			String  freight_status=request.getParameter("freight")==null?"":request.getParameter("freight");
			String freghtPolicyStatus = "";
			freghtPolicyStatus = request.getParameter("freightPolicyPer")==null?"":request.getParameter("freightPolicyPer");
			
			if(apNo!=null && userType.equalsIgnoreCase("Broker"))
			{
				args = new String[1];
				args[0] = apNo;
				query = "select FREIGHT_STATUS from position_master  where application_no=?";
					freight = runner.singleSelection(query,args);
					if(freight!=null&&freight.trim().equalsIgnoreCase("F"))
					{
						if(freight_status!=null&&!freight_status.trim().equals("null"))
						{
							if(freight_status.equalsIgnoreCase("Y"))
								freight_status = "A";
							else if(freight_status.equalsIgnoreCase("R"))
								freight_status = "R";
							args = new String[3];
							args[0] = freight_status;
							args[1] = freghtPolicyStatus;
							args[2] = apNo;
							query = "update FREIGHT_POSITION_MASTER set status=?,ALLOW_TO_GENERATE_POLICY_STS=? where application_id=?";
							runner.multipleUpdation(query,args);
							if(freight_status.equalsIgnoreCase("R"))
							{
								args = new String[2];
								args[0] = freight_status;
								args[1] = apNo;
								query = "update POSITION_MASTER set status=? where application_no=?";
								runner.multipleUpdation(query,args);
							}
						}
					}
					args = new String[1];
					args[0] = apNo;
					query = "select status from FREIGHT_POSITION_MASTER  where application_id=?";
					freight_status = runner.singleSelection(query,args);
					
			}
			if(freight_status==null||(!freight_status.equalsIgnoreCase("R") &&!freight_status.equalsIgnoreCase("N"))||"".equalsIgnoreCase(freight_status))
			{
					
					String pids = (String)session.getAttribute("product_id");
					String actPath = request.getParameter("actionPath");
					actPath = actPath==null?"":actPath;
					String referralRemarks="";
					if("11".equalsIgnoreCase(pids))
					 {
						String cusIds = (String)session.getAttribute("customer_id");
						String appNos = (String)session.getAttribute("application_no");
						String loginIds = (String)session.getAttribute("user");
						String cids = (String)session.getAttribute("company_id");
						String brokerBra = (String)session.getAttribute("LoginBranchCode");
						
						if(appNos==null)
						{
							response.sendRedirect("login/error_messg.jsp");
							return;
						}
						else if(appNos!=null)
							session.setAttribute("application_no",appNos);
						
						if("Y".equalsIgnoreCase(request.getParameter("adminReferals")))
						{
								com.maan.premium.DAO.PremiumLogic logics=null;
								logics=new com.maan.premium.DAO.PremiumLogic();
								logics.setApplicationNo(appNos);
								/*String[][] existingStatus=new String[0][0];
								existingStatus=logics.getAdminReferalStatus();

								String existingStatusAppend="";
								if(existingStatus.length<0)
								{

								}
								else
								{
									existingStatusAppend=existingStatus[0][1]==null?existingStatusAppend:existingStatus[0][1];
									if((existingStatusAppend.trim()).length() >0 && !("Normal".equalsIgnoreCase(existingStatusAppend)))
									{
											existingStatusAppend="~"+existingStatusAppend;
									}
									else
									{
											existingStatusAppend="";
									}
										
									}*/
									referralRemarks = request.getParameter("adminReferalRemarks")==null?"":request.getParameter("adminReferalRemarks");

						
						if(!"".equalsIgnoreCase(referralRemarks))
						{
//							referralRemarks = referralRemarks.replaceAll("'","''")+""+existingStatusAppend.replaceAll("'","''");
							referralRemarks = referralRemarks.replaceAll("'","''");
						}
						else
						{
							//referralRemarks = (String)session.getAttribute("adminReferalRemarks")==null?"":(String)session.getAttribute("adminReferalRemarks");
							//out.println("Referral remarks"+referralRemarks);
						}
							String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
							args  = new String[3];
							args[0] = temp; 
							args[1] = referralRemarks;
							args[2] = appNos;
							query = "update marine_data set admin_referral_status=?, remarks=? where application_no=?";
							runner.multipleUpdation(query,args);
							logics.updateRemarks("Normal");
						}
						else
						{
							String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
							String tempR = request.getParameter("adminReferalRemarks")!=null?request.getParameter("adminReferalRemarks"):"";
							tempR = tempR.replaceAll("'","''");
							args = new String[2];
							args[0] = temp;
							args[1] = appNos;
							if("referal".equalsIgnoreCase(request.getParameter("referalStatus")))
								query = "update marine_data set admin_referral_status=? where application_no=?";
							else
								query = "update marine_data set admin_referral_status=?,remarks='Normal' where application_no=?";
							runner.multipleUpdation(query,args);
						}
					
						request.setAttribute("identifyCus",getCustomer(appNos==null?"":appNos));
						com.maan.premium.DAO.PremiumInputsBean excessPremium=null;
						excessPremium = new com.maan.premium.DAO.PremiumInputsBean();
					String minusOption = request.getParameter("loadDiscountOption")==null ?"" : request.getParameter("loadDiscountOption");
					String extraPrem="";
					extraPrem = request.getParameter("extra_premium")==null ?"" : request.getParameter("extra_premium");
					if(minusOption.equalsIgnoreCase("-"))
						extraPrem = "-" + extraPrem;
					if(request.getParameter("linkFrom")==null)
						excessPremium.setExtraPremium(appNos,extraPrem);
				
					
				if(session.getAttribute("identify_Id")!=null && !"edit".equalsIgnoreCase(request.getParameter("mode")))
				{
					session.setAttribute("identify_Id",request.getParameter("customers")==null?"":request.getParameter("customers"));
					session.setAttribute("identify_Id",request.getParameter("customers"));
					if("11".equalsIgnoreCase(pids))
					{
							request.setAttribute("identifyCus",cusIds);
					}
					else
					{
						request.setAttribute("identifyCus",(String)session.getAttribute("identify_Id"));
					}
					if("11".equalsIgnoreCase(pids))
					{
						String qno = "";
						//synchronized(this)
						{
							qno = getQuote(cusIds,appNos,pids,loginIds,cids,brokerBra,rsaissuer,(String)session.getAttribute("AdminBranchCode"));
							session.setAttribute("quote_no",qno);
						}
					}
					else
					{
						String qno = "";
						qno = getQuote((String)session.getAttribute("identify_Id"),appNos,pids,loginIds,cids,brokerBra,rsaissuer,(String)session.getAttribute("AdminBranchCode"));
						session.setAttribute("quote_no",qno);
					}
					String quote_no = (String)session.getAttribute("quote_no");
					quote_no = quote_no==null?"":quote_no;
					if("referal".equalsIgnoreCase(request.getParameter("referalStatus")))
					{
						args = new String[2];
						args[0] = appNos;
						args[1] = quote_no;
						query = "update position_master set remarks='Referal',excess_premium=(select excess_premium from marine_data where application_no=?) where quote_no=?";
						runner.multipleUpdation(query,args);
					}
					else
					{
						args = new String[1];
						args[0] = quote_no;
						query = "update position_master set remarks='Normal' where quote_no=?";
						runner.multipleUpdation(query,args);
					}
					if("save".equalsIgnoreCase(request.getParameter("identifypath")) || "referal".equalsIgnoreCase(request.getParameter("referalStatus")))
					{
						request.setAttribute("referalStatus",request.getParameter("referalStatus"));
						//rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
						//This commaned - this is for wihout eneter customer screen direcly goes show screen while referral open
						rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
					}
					else
					{
						if("11".equalsIgnoreCase(pids))
						{
							rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
						}
						else
						{
							rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
						}
					}
				}
				else
				{
						if("11".equalsIgnoreCase(pids) && "save".equalsIgnoreCase(request.getParameter("identifypath")))
						{
							if("Y".equalsIgnoreCase(request.getParameter("adminReferals")))
								request.setAttribute("referalStatus","referal");
							System.out.println("admin_referral_status ...........7777777");
							rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
						}
						else if("11".equalsIgnoreCase(pids) && "proceed".equalsIgnoreCase(request.getParameter("identifypath")))
						{
							String referal=null;
							dataCollection datas=new dataCollection();
							referal=datas.getReferalStatus((String)session.getAttribute("quote_no"));
											

							/*if(("Y").equalsIgnoreCase(request.getParameter("adminReferals")) || "referal".equalsIgnoreCase(referal))
							{
								request.setAttribute("referalStatus","referal");
								System.out.println("admin_referral_status ...........88888888");
								rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
							}
							else*/
								rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
						}
						else
						{
							System.out.println("Loads Here");
							rd = getServletContext().getRequestDispatcher("/others.jsp");
						}
					}
				}
				else
				{
					String cusIds = (String)session.getAttribute("customer_id");
					String appNos = (String)session.getAttribute("application_no");
					String loginIds = (String)session.getAttribute("user");
					String cids = (String)session.getAttribute("company_id");
					String brokerBra = (String)session.getAttribute("LoginBranchCode");
					String actionPath="";
					System.out.println("Roayl TEst..freight_status..inside.."+appNos);
					if(appNos==null)
					{
						response.sendRedirect("login/error_messg.jsp");
						return;
					}
					if(appNos!=null)
						session.setAttribute("application_no",appNos);
					if("Y".equalsIgnoreCase(request.getParameter("adminReferals")))
					{
						referralRemarks="";
						com.maan.premium.DAO.PremiumLogic logics=null;
						logics=new com.maan.premium.DAO.PremiumLogic();
						logics.setApplicationNo(appNos);
						/*String[][] existingStatus=new String[0][0];
						existingStatus=logics.getAdminReferalStatus();
						String existingStatusAppend="";
						if(existingStatus.length<0)
						{

						}
						else
						{
							existingStatusAppend=existingStatus[0][1]==null?existingStatusAppend:existingStatus[0][1];
							if((existingStatusAppend.trim()).length() >0 && !("Normal".equalsIgnoreCase(existingStatusAppend)) )
							{
								existingStatusAppend="~"+existingStatusAppend;
							}
							else
							{
								existingStatusAppend="";
							}
						}*/
						referralRemarks = request.getParameter("adminReferalRemarks")==null?"":request.getParameter("adminReferalRemarks");
				if(!"".equalsIgnoreCase(referralRemarks))
				{
//					referralRemarks = referralRemarks.replaceAll("'","''")+""+existingStatusAppend.replaceAll("'","''");
					referralRemarks = referralRemarks.replaceAll("'","''");
				}
				else
				{
					//referralRemarks = (String)session.getAttribute("adminReferalRemarks")==null?"":(String)session.getAttribute("adminReferalRemarks");
					//out.println("Referral remarks"+referralRemarks);
				}
					String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
					args = new String[3];
					args[0] = temp;
					args[1] = referralRemarks;
					args[2] = appNos;
					query = "update marine_data set admin_referral_status=?, remarks=? where application_no=?";
					runner.multipleUpdation(query,args);
					logics.updateRemarks("Normal");
				}
				else
				{
					String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
					String tempR = request.getParameter("adminReferalRemarks")!=null?request.getParameter("adminReferalRemarks"):"";
					tempR = tempR.replaceAll("'","''");
					args = new String[2];
					args[0] = temp;
					args[1] = appNos;
					if("referal".equalsIgnoreCase(request.getParameter("referalStatus")))
						query = "update marine_data set admin_referral_status=? where application_no=?";
					else
						query = "update marine_data set admin_referral_status=?, remarks='Normal' where application_no=?";
					runner.multipleUpdation(query,args);
				}
				request.setAttribute("identifyCus",getCustomer(appNos==null?" ":appNos));
				com.maan.premium.DAO.PremiumInputsBean excessPremium = null;
				excessPremium = new com.maan.premium.DAO.PremiumInputsBean();


					String minusOption = request.getParameter("loadDiscountOption")==null ?"" : request.getParameter("loadDiscountOption");
	
					String extraPrem="";
						extraPrem = request.getParameter("extra_premium")==null ?"" : request.getParameter("extra_premium");
					if(minusOption.equalsIgnoreCase("-"))
							extraPrem = "-" + extraPrem;
					if(request.getParameter("linkFrom")==null)
						excessPremium.setExtraPremium(appNos,extraPrem);
	
				
				if(session.getAttribute("identify_Id")!=null && !"edit".equalsIgnoreCase(request.getParameter("mode")))
				{
					
					if("11".equalsIgnoreCase(pids))
					{
						request.setAttribute("identifyCus",cusIds);
					}
					else
					{
						request.setAttribute("identifyCus",(String)session.getAttribute("identify_Id"));
					}
					if("11".equalsIgnoreCase(pids))
					{
						String qno = "";
						//synchronized(this)
						{
							qno = getQuote(cusIds,appNos,pids,loginIds,cids,brokerBra,rsaissuer,(String)session.getAttribute("AdminBranchCode"));
							session.setAttribute("quote_no",qno);
						}
					}
					else
					{
						String qno = "";
						//synchronized(this)
						{
							qno = getQuote((String)session.getAttribute("identify_Id"),appNos,pids,loginIds,cids,brokerBra,rsaissuer,(String)session.getAttribute("AdminBranchCode"));
							session.setAttribute("quote_no",qno);
						}
					}
					String quote_no= (String)session.getAttribute("quote_no");
					quote_no = quote_no==null?"":quote_no;
					if("referal".equalsIgnoreCase(request.getParameter("referalStatus")))
					{
						args = new String[2];
						args[0] = appNos;
						args[1] = quote_no;
						query = "update position_master set remarks='Referal',excess_premium=(select excess_premium from marine_data where application_no=?) where quote_no=?";
						runner.multipleUpdation(query,args);
					}
					else
					{
						args = new String[1];
						args[0] = quote_no;
						query = "update position_master set remarks='Normal' where quote_no=?";
						runner.multipleUpdation(query,args);
					}
					
					out.println("AFTER NEWQUOTE GENERATION AND INSERTION POSITION MASTER "+request.getParameter("identifypath"));
					out.println("AFTER NEWQUOTE GENERATION AND INSERTION POSITION MASTER "+request.getParameter("referalStatus"));
				//if(true)return;
					if("save".equalsIgnoreCase(request.getParameter("identifypath")) && "referal".equalsIgnoreCase(request.getParameter("referalStatus")))
					{
							request.setAttribute("referalStatus",request.getParameter("referalStatus"));
							System.out.println("admin_referral_status ...........9999999");
							rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
					}
					else
					{

						if("11".equalsIgnoreCase(pids))
						{
							rd = getServletContext().getRequestDispatcher("/others.jsp");
						}
						else
						{
							rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
						}
					}
				}
				else
				{
					if("11".equalsIgnoreCase(pids) && "save".equalsIgnoreCase(request.getParameter("identifypath")))
					{
						if("Y".equalsIgnoreCase(request.getParameter("adminReferals")))
						request.setAttribute("referalStatus","referal");
						System.out.println("admin_referral_status ...........0000000000");
						rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
					}
					else if("11".equalsIgnoreCase(pids) && "proceed".equalsIgnoreCase(request.getParameter("identifypath")))
					{
						String referal="";
						dataCollection datas=null;
									datas=new dataCollection();
						referal=datas.getReferalStatus((String)session.getAttribute("quote_no"));
						if(("Y").equalsIgnoreCase(request.getParameter("adminReferals")) || "referal".equalsIgnoreCase(referal))
						{
								request.setAttribute("referalStatus","referal");
								System.out.println("admin_referral_status ...........11,11,11");
							rd = getServletContext().getRequestDispatcher("/showQuote.jsp");
						}
						else
						{
							rd = getServletContext().getRequestDispatcher("/others.jsp");
						}
					}
					else
					{
						request.setAttribute("mailmode","true");
						rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
					}
				}
			 }
		}
		else
		{
				if(freight_status.equalsIgnoreCase("R"))
					request.setAttribute("freightRejected","yes");
				else if(freight_status.equalsIgnoreCase("N"))
					request.setAttribute("freightRejected","None");
				rd = getServletContext().getRequestDispatcher("/Freight_ShowQuote.jsp");
		}
	}
	else if("FreightBroker".equalsIgnoreCase(path))
	{
		String applicationNo="";
       	applicationNo=(String)session.getAttribute("applicationNo");
		com.maan.premium.DAO.PremiumLogic premiumLogic = new com.maan.premium.DAO.PremiumLogic();
		//Rajesh World Work Started
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		if(brokerDetails.size()>0)
		{
			cid = (String)brokerDetails.get("Orgination");
		}
		premiumLogic.setLoginBra(brokerBra);
		premiumLogic.setCid(cid);
		//
		HashMap PremiumDetailsHash = premiumLogic.getPremiumDetails(applicationNo);
		request.setAttribute("fullDetails",PremiumDetailsHash);

		RequestDispatcher dispatcher=request.getRequestDispatcher(path+"/selectCustomer.do");
		dispatcher.forward(request, response);if(true)return;
	}
	else if("CoreCustomer".equalsIgnoreCase(path))
	{
		String customerName=request.getParameter("customerName")==null?"":request.getParameter("customerName");
		request.setAttribute("customerList", customerInfo.getCoreCustomerInfo(customerName, (String)session.getAttribute("LoginBranchCode"),(String)session.getAttribute("userLoginMode")));
		request.setAttribute("display", "customerList");
		RequestDispatcher dispatcher=request.getRequestDispatcher("/CustomerInfo/CoreCustomerSearch.jsp");
		dispatcher.forward(request, response);if(true)return;
	}
	else if("ExchangeRateReport".equalsIgnoreCase(path))
	{
		String effectiveDate=request.getParameter("effectiveDate")==null?"":request.getParameter("effectiveDate");
		Map<String, String> brokerDetails = (HashMap<String, String>)session.getAttribute("BrokerDetails");
		List<Object> rateList=new ArrayList<Object>(); 
		Map<String, String> map=null; 
		String [][] rateInfo=new ReportBean().getCurrencyDetails((String)brokerDetails.get("Orgination"), effectiveDate);
		if(rateInfo!=null && rateInfo.length>0){
			for (int i = 0; i < rateInfo.length; i++) {
				map=new LinkedHashMap<String, String>(); 
				map.put("currencyName", StringUtils.defaultIfEmpty(rateInfo[i][1], ""));
				map.put("exchangeRate", StringUtils.defaultIfEmpty(rateInfo[i][0], ""));
				map.put("effectiveDate", StringUtils.defaultIfEmpty(rateInfo[i][3], ""));
				rateList.add(map);
			}
		}
		request.setAttribute("rateList", rateList);
		rd=request.getRequestDispatcher("/admin/ExchangeRateReport.jsp");
	}
	else if("DepositOpencoverList".equalsIgnoreCase(path))
	{
		List<Object> depositList=new ArrayList<Object>(); 
		Map<String, String> map=null; 
		String [][] rateInfo=new ReportBean().getOpencoverList();
		if(rateInfo!=null && rateInfo.length>0){
			for (int i = 0; i < rateInfo.length; i++) {
				map=new LinkedHashMap<String, String>(); 
				map.put("opencoverNo", StringUtils.defaultIfEmpty(rateInfo[i][1], ""));
				map.put("startDate", StringUtils.defaultIfEmpty(rateInfo[i][2], ""));
				map.put("CustomerName", StringUtils.defaultIfEmpty(rateInfo[i][3], ""));
				depositList.add(map);
			}
		}
		request.setAttribute("depositList", depositList);
		request.setAttribute("display", "depositList");
		rd=request.getRequestDispatcher("/admin/DepositOpencoverList.jsp");
	}
	else if("getOpencover".equalsIgnoreCase(path))
	{
		String opencoverNo=request.getParameter("opencoverNo")==null?"":request.getParameter("opencoverNo");
		request.setAttribute("opencoverNo", opencoverNo);
		List<Object> getOpencover=new ArrayList<Object>(); 
		Map<String, String> map=null; 
		String [][] rateInfo=new ReportBean().getOpencover(opencoverNo);
		if(rateInfo!=null && rateInfo.length>0){
			for (int i = 0; i < rateInfo.length; i++) {
				map=new LinkedHashMap<String, String>(); 
				map.put("startDate", StringUtils.defaultIfEmpty(rateInfo[i][1], ""));
				map.put("endDate", StringUtils.defaultIfEmpty(rateInfo[i][2], ""));
				getOpencover.add(map);
			}
		}
		request.setAttribute("getOpencover", getOpencover);
		request.setAttribute("display", "getOpencover");
		rd=request.getRequestDispatcher("/admin/DepositOpencoverList.jsp");
	}
	else if("getDepositOpencover".equalsIgnoreCase(path))
	{
		String opencoverNo=request.getParameter("opencoverNo")==null?"": request.getParameter("opencoverNo").toString();
		String startDate=request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate=request.getParameter("endDate")==null?"":request.getParameter("endDate");
		List<Object> getDepositOpencover=new ArrayList<Object>(); 
		Map<String, String> map=null; 
		String [][] rateInfo=new ReportBean().getDepositOpencover(opencoverNo,startDate,endDate);
		if(rateInfo!=null && rateInfo.length>0){
			for (int i = 0; i < rateInfo.length; i++) {
				map=new LinkedHashMap<String, String>(); 
				map.put("policyNo", StringUtils.defaultIfEmpty(rateInfo[i][0], ""));
				map.put("inceptionDate", StringUtils.defaultIfEmpty(rateInfo[i][1], ""));
				map.put("premium", StringUtils.defaultIfEmpty(rateInfo[i][2], ""));
				map.put("issuanceFee", StringUtils.defaultIfEmpty(rateInfo[i][3], ""));
				map.put("totalPremium", StringUtils.defaultIfEmpty(rateInfo[i][4], ""));
				getDepositOpencover.add(map);
			}
		}
		request.setAttribute("getDepositOpencover", getDepositOpencover);
		request.setAttribute("display", "getDepositOpencover");
		rd=request.getRequestDispatcher("/admin/DepositOpencoverList.jsp");
	}
	else if("Branchreports".equalsIgnoreCase(path))
	{
		String dobDay=request.getParameter("dobDay")==null?"":request.getParameter("dobDay");
		String dobMonth=request.getParameter("dobMonth")==null?"":request.getParameter("dobMonth");
		String dobYear=request.getParameter("dobYear")==null?"":request.getParameter("dobYear");
		String dobDay1=request.getParameter("dobDay1")==null?"":request.getParameter("dobDay1");
		String dobMonth1=request.getParameter("dobMonth1")==null?"":request.getParameter("dobMonth1");
		String dobYear1=request.getParameter("dobYear1")==null?"":request.getParameter("dobYear1");
		String startDate=dobDay+"-"+dobMonth+"-"+dobYear;
		String endDate=dobDay1+"-"+dobMonth1+"-"+dobYear1;
		String productId=request.getParameter("pid")==null?"":request.getParameter("pid");
		String type=request.getParameter("pending")==null?"":request.getParameter("pending");
		List list = new ReportBean().getBranchList(startDate,endDate,type,productId);
		request.setAttribute("reportList", list);
		rd=request.getRequestDispatcher("/admin/BranchReport.jsp");
	}else if("VesselSearch".equalsIgnoreCase(path))
	{
		String vesselName=request.getParameter("vesselName")==null?"":request.getParameter("vesselName");
		request.setAttribute("vesselList", new PremiumInputsBean().getVesselList(vesselName));
		request.setAttribute("display", "vesselList");
		request.setAttribute("vesselName", vesselName);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/premium/VesselList.jsp");
		dispatcher.forward(request, response);
		return;
	}else
	{
		com.maan.services.policyInfo policys=new com.maan.services.policyInfo();
			if("clausesEdit.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
			{
				HashMap preDetails = policys.getResults((String)session.getAttribute("quote_no"),(String)session.getAttribute("customer_id"),(String)session.getAttribute("application_no"),(String)session.getAttribute("company_id"),(String)session.getAttribute("user"));
				request.setAttribute("premiumresults",preDetails);
			if("Y".equalsIgnoreCase(request.getParameter("adminReferals")))
			{
				com.maan.premium.DAO.PremiumLogic logics1=null;
				logics1=new com.maan.premium.DAO.PremiumLogic();
				logics1.setApplicationNo((String)session.getAttribute("application_no"));
				//logics1.setCon(com.maan.DBCon.DBConnection.getDBConnection1());

				String[][] existingStatus1=new String[0][0];
				existingStatus1=logics1.getAdminReferalStatus();
				String existingStatusAppend1="";
				if(existingStatus1.length<0)
				{
				}else
				{
					out.println("<br>the BEOFRE existingStatusAppend1 is "+existingStatusAppend1);
					existingStatusAppend1=existingStatus1[0][1]==null?existingStatusAppend1:existingStatus1[0][1];
					out.println("<br>the after existingStatusAppend is1 "+existingStatusAppend1);
					if((existingStatusAppend1.trim()).length() >0 && !("Normal".equalsIgnoreCase(existingStatusAppend1)) )
					{
						existingStatusAppend1="~"+existingStatusAppend1;
					}
					else
					{
						existingStatusAppend1="";
					}
					out.println("<br>the after ~ SYSMBOL existingStatusAppend1 is "+existingStatusAppend1);
				}
				String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
				String tempR = request.getParameter("adminReferalRemarks")!=null?request.getParameter("adminReferalRemarks"):"";
				tempR = tempR.replaceAll("'","''");
				
			com.maan.services.util.runner.updation("update marine_data set admin_referral_status='"+temp+"', remarks='"+tempR+""+(existingStatusAppend1.replaceAll("'","''"))+"' where application_no='"+(String)session.getAttribute("application_no")+"'");
			}
			else
			{
				String temp = request.getParameter("adminReferals")!=null?request.getParameter("adminReferals"):"N";
				args = new String[2];
				args[0] = temp;
				args[1] = (String)session.getAttribute("application_no");
				query = "update marine_data set admin_referral_status=? where application_no=?";
				runner.multipleUpdation(query,args);
			}
		}
			boolean mrFlag = false; // Checking For Marine Result Details got inserted or not.....
			mrFlag = policys.checkingForMarineResult((String)session.getAttribute("quote_no"));
			if(mrFlag) 
			{ 
				rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
			}
		//rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
	}
	if(rd==null)
	{
		rd = getServletContext().getRequestDispatcher("/others.jsp");
	}
	rd.forward(request, response);
}


public String getCustomer(String appId)
{
	String args[] = new String[1];
	String sql = "";
	String custId = "";
	try
	{
		args[0] = appId;
		sql	= "select customer_id from position_master where application_no=?";
		custId = runner.singleSelection(sql,args);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return custId;
}

public synchronized String getQuote(String customersId,String app_no,String productId,String loginId,String companyId,String loginBra,String rsaissuer,String branch)
{
		String QuoteNo=null;
		String args[] = new String[0];
		String sql = "";
		String excessPremium1="";
		try
		{
			args = new String[1];
			args[0] =app_no;
			sql = "select quote_no from position_master where application_no=?";

			QuoteNo = runner.singleSelection(sql,args);
			//For Freight Forwarder - Rajesh
			String userType = "";
			String userStatus = "";
			String freightStatus = "";
			String freStatus = "";
			String disrctFreStatus = "";
			
			String userTypes[][] = new String[0][0];
			args[0] = loginId;
			sql = "select log.usertype,lu.PROVISION_FOR_PREMIUM,lu.FREIGHT_RATE_OPTION from login_master log,LOGIN_USER_DETAILS lu where log.login_id=lu.login_id " +
					"and log.login_id=? and lu.product_id='3'";

			userTypes = runner.multipleSelection(sql,args);

			if(userTypes.length>0)
			{
				userType = userTypes[0][0]!=null?userTypes[0][0]:"";
				userStatus = userTypes[0][1]!=null?userTypes[0][1]:"";
				disrctFreStatus = userTypes[0][2]!=null?userTypes[0][2]:"";
			}
			if(userType.equalsIgnoreCase("freight"))
				freightStatus = "F";
			else
				freightStatus = "";
			if(userStatus.equalsIgnoreCase("D"))
			{
				if(disrctFreStatus.equalsIgnoreCase("Y"))
					freStatus = "A";
				else if(disrctFreStatus.equalsIgnoreCase("N"))
					freStatus = "U";
			}
			else
				freStatus = "U";
			if(QuoteNo.length()==0 || ("").equals(QuoteNo) || "DIDN'T SELECTED".equalsIgnoreCase("QuoteNo"))
			{
				//Changed to sequence
				QuoteNo=com.maan.services.util.dataCollection.getMaxQuote(productId,branch);
				//QuoteNo=com.maan.services.util.dataCollection.getMaxQuote(productId);
				String businessOpen[][] = new String[0][0];
				String businessType = "";
				String openCOverNo = "";
				String missiOpenNo = "0";
				com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
				if(productId.equalsIgnoreCase("11"))
				{
					businessOpen = data.getBusinessOpenNo(app_no);
					 if(businessOpen.length>0)
					{
						openCOverNo = businessOpen[0][0]!=null?businessOpen[0][0]:"";
						businessType = businessOpen[0][1]!=null?businessOpen[0][1]:"";
						missiOpenNo = businessOpen[0][2]!=null?businessOpen[0][2]:"";
					}
					else
					{
						openCOverNo = "0";
						businessType = "1";//New Business - 1.. Existing Business - 0.
					}
				}
				else
				{
					openCOverNo = "0";
					businessType = "1";
				}
				//RSA Issuer
				String appID = "1";
				if(rsaissuer == null || rsaissuer.equalsIgnoreCase("null") || rsaissuer.equals(""))
					appID ="1";
				else
					appID = rsaissuer;
				
				args = new String[1];
				args[0] = app_no;
				sql = "select excess_premium from marine_data where application_no=?";
				excessPremium1 = runner.singleSelection(sql,args);
				
				/** Get Hours To Add Sysdate**/
				String hourSQL = "";
				String hour = "";
				args = new String[4];
				args[0] = "62";
				args[1] = "1";
				args[2] = "Y";
				args[3] = loginId;
				hourSQL = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=(select branch_code from broker_company_master where agency_code=(select oa_code from login_master where login_id=?))";
				 hour= runner.singleSelection(hourSQL,args);
				if(hour.length() > 0)
					hour = "sysdate" + "+" + hour;
				else
					hour = "sysdate";
				/** Get Hours To Add Sysdate**/

				args = new String[14];
				args[0] = productId;
				args[1] = loginId;
				args[2] = companyId;
				args[3] = app_no;
				args[4] = "0";
				args[5] = QuoteNo;
				args[6] = customersId;
				args[7] = appID;
				args[8] = "Y";
				args[9] = excessPremium1;
				args[10] = freightStatus;
				args[11] = businessType;
				args[12] = openCOverNo;
				args[13] = missiOpenNo;
				//sql="insert into position_master (product_id,login_id,company_id,application_no,amend_id,quote_no,customer_id,application_id,status,entry_date,effective_date,excess_premium,FREIGHT_STATUS,BUSINESS_TYPE,OPEN_COVER_NO) values(?,?,?,?,?,?,?,?,?,(select sysdate+8/24 from dual),(select add_months(sysdate,1)-1 from dual),?,?,?,?)";
				sql="insert into position_master (product_id,login_id,company_id,application_no,amend_id,quote_no,customer_id,application_id,status,entry_date,effective_date,excess_premium,FREIGHT_STATUS,BUSINESS_TYPE,OPEN_COVER_NO,MISSIPPI_OPENCOVER_NO) values(?,?,?,?,?,?,?,?,?,(select "+hour+" from dual),(select add_months("+hour+",1)-1 from dual),?,?,?,?,?)";
				
				System.out.println("Position Master loginId "+loginId);
				System.out.println("Position Master customersId "+customersId);
				System.out.println("Position openCOverNo"+openCOverNo);
				System.out.println("Position QuoteNo"+QuoteNo);
				System.out.println("Position productId"+productId);
				System.out.println("Position application_id"+appID);

				runner.multipleInsertion(sql,args);
				
				//For Freight Forwarder
				if(userType.equalsIgnoreCase("freight"))
				{
					String freightSql = "";
					args = new String[1];
					args[0] = app_no;
					sql = "select excess_premium from marine_data where application_no=?";
					excessPremium1 = runner.singleSelection(sql,args);

					args = new String[9];
					args[0] = productId;
					args[1] = loginId;
					args[2] = companyId;
					args[3] = app_no;
					args[4] = "1";
					args[5] = QuoteNo;
					args[6] = customersId;
					args[7] = freStatus;
					args[8] = excessPremium1;

					//freightSql = "insert into FREIGHT_POSITION_MASTER (product_id,login_id,company_id,APPLICATION_ID,amend_id,quote_no,customer_id,status,entry_date,effective_date,excess_premium) values(?,?,?,?,?,?,?,?,(select sysdate+8/24 from dual),(select add_months(sysdate,1)-1 from dual),?)";
					freightSql = "insert into FREIGHT_POSITION_MASTER (product_id,login_id,company_id,APPLICATION_ID,amend_id,quote_no,customer_id,status,entry_date,effective_date,excess_premium) values(?,?,?,?,?,?,?,?,(select "+hour+" from dual),(select add_months(sysdate,1)-1 from dual),?)";
					runner.multipleInsertion(freightSql,args);
				}
			}
			else
			{
				ValidationFormat validationFormat	= new ValidationFormat();
				if(validationFormat.IsDigitValidationFormat(customersId)==false)
				{
					customersId = getCustomerIdForName(customersId);
				}

				boolean flag;
				flag = getExistingCustomerId(app_no,QuoteNo,customersId);
				if(flag)
				{ 
					args = new String[1];
					args[0] = app_no;
					sql = "select excess_premium from marine_data where application_no=?";
					excessPremium1 = runner.singleSelection(sql,args);

					args = new String[4];
					args[0] = customersId;
					args[1] = excessPremium1;
					args[2] = app_no;
					args[3] = QuoteNo;
					sql="update position_master set customer_id=?,excess_premium=? where application_no=? and quote_no=?";
					runner.multipleUpdation(sql,args);
					
					args = new String[3];
					args[0] = customersId;
					args[1] = app_no;
					args[2] = QuoteNo;
					sql="update marine_result set customer_id=? where application_no=? and quote_no=?";
					runner.multipleUpdation(sql,args);
					
					args = new String[2];
					args[0] = customersId;
					args[1] = QuoteNo;
					sql="update marine_policy_details set customer_id=? where quote_no=?";
					runner.multipleUpdation(sql,args);
					if(userType.equalsIgnoreCase("freight"))
					{
						args = new String[1];
						args[0] = app_no;
						sql = "select excess_premium from marine_data where application_no=?";
						excessPremium1 = runner.singleSelection(sql,args);
						
						args = new String[4];
						args[0] = customersId;
						args[1] = excessPremium1;
						args[2] = app_no;
						args[3] = QuoteNo;
						String freightUpdateSql = "";
						freightUpdateSql = "update FREIGHT_POSITION_MASTER set customer_id=?,excess_premium=? where APPLICATION_ID=? and quote_no=?";
						runner.multipleUpdation(freightUpdateSql,args);
					}
				}
				else
				{
					System.out.println("Invalid app_no  in controller.java"+app_no);
					System.out.println("Invalid QuoteNo in controller.java"+QuoteNo);
					System.out.println("Invalid Customer Id in controller.java"+customersId);
				} 
			}
			new CommodityCountryRateDAO().updateWarrantyClauses(loginId, productId, app_no);//Added for Commodity Country Rate by Chinna
		}
		catch(Exception e)
		{
			System.out.println("ERROR in Quote No Generation----<br>"+e.toString());
			QuoteNo="Quote Not Generated";
		}
		return QuoteNo==null?" ":QuoteNo;
	}


	public String getReferalStatusPosition(String quoteNo)
	{
		String args[] = new String[1];
		String sql = "";
		String refStatus = "";
		
		try
		{
			args[0] = quoteNo;
			sql = "select remarks from position_master where quote_no=?";
			refStatus = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return refStatus;
	}

	public String getCustomerIdForName(String cusId)
	{
		String args[] = new String[3];
		String custName = "";
		String sql = "";
		System.out.println("getCustomerIdForName "+ cusId);
		try
		{
			args[0] = cusId;
			args[1] = cusId;
			args[2] = cusId;
			sql = "select customer_id from personal_info where first_name=? or last_name=? or company_name=?";
			custName = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return custName;
	}

	/** Customer Id Checking **/

	public boolean getExistingCustomerId(String appNo,String Quote,String CustId)
	{
		boolean flag = false;
		String[][] ss=new String[0][0];
		String[][] valuess=new String[0][0];
		String loginAllIds="";
		String args[] = new String[0];
		String loginIds ="";
		String sql = "";
		try
		{
			args = new String[2];
			args[0] = appNo;
			args[1] = Quote;
			sql = "select login_id from position_master where application_no=? and quote_no=?";
			loginIds = runner.singleSelection(sql,args);
			String utype = getUserType(loginIds);
			if("Freight".equalsIgnoreCase(utype))
			{
				sql = "select login_id from personal_info where login_id in('"+loginIds+"') and login_id!='NONE' and application_id in ('5')";
				valuess = runner.multipleSelection(sql);
			}
			else
			{
				args = new String[1];
				args[0] = loginIds;
				sql = "select distinct login_id from personal_info where login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?)))) and login_id!='NONE' and application_id in ('1','2','3')";
				valuess = runner.multipleSelection(sql,args);
			}
			
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));

			sql="select customer_id,first_name,last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME from personal_info where login_id in ("+loginAllIds+") and application_id=1 order by customer_id desc";
			ss = runner.multipleSelection(sql);

			for(int c=0;c<ss.length;c++)
			{
				if(ss[c][0].equalsIgnoreCase(CustId))
				{
					flag = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getExistingCustomerss  "+e.toString());
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	public String getUserType(String loginIds)
	{
		String sql = "";
		String res="";
		String args[] = new String[1];
		try
		{
			args[0] = loginIds;
			sql = "select usertype from login_master where login_id=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getExistingCustomerss Controller.java "+e.toString());
			e.printStackTrace();
		}
		return res;
	}
	/** Customer Id Checking **/



}// Class