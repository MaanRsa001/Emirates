package com.maan.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class ExportTableController extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(ExportTableController.class);
	private static final long serialVersionUID = 1L;
	final static transient private String ENTER = "- Enter";
	final static transient private String SELECT = "Select";
	final static transient private String QUOTENOSS = "quotenoss";
	final static transient private String BNAMES = "BNames";
	final static transient private String QNOFROM = "qnoFrom";
	final static transient private String QNOTO = "qnoTo";
	final static transient private String ERRORDETAIL = "errorDetail";
	final static transient private String DATA1 = "data1";
	final static transient private String DATA2 = "data2";
	final static transient private String PID = "pid";
	final static transient private String DOBDAY = "dobDay";
	final static transient private String DOBMONTH = "dobMonth";
	final static transient private String DOBYEAR = "dobYear";
	final static transient private String BRANCH = "branch";
	final static transient private String DOBDAY1 = "dobDay1";
	final static transient private String DOBMONTH1 = "dobMonth1";
	final static transient private String DOBYEAR1 = "dobYear1";
	final static transient private String REQUESTFROM = "requestfrom";
	
	PrintWriter out = null;
	DataSelection dataSelect;
	Date dat = new Date();
	SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
	String deActivatedDate = "";

	public void init(final ServletConfig config) throws ServletException {
		dataSelect = new DataSelection();
	}

	public void doPost(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException{
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}
	
	public void doGet(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException{
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}

	public void processResult(final HttpServletRequest req,final  HttpServletResponse res)throws ServletException, IOException,BaseException
	{
		try
		{
			simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
			logger.info("Export Tables Controller transfer method()");
			logger.debug(ENTER);
			HttpSession session = null; session = req.getSession(true);
			String loginPersonId = ""; loginPersonId = (String) session.getAttribute("loginPersonId");
			RequestDispatcher dispatcher1 =  null; dispatcher1 = req.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
			if (dispatcher1 != null){
				dispatcher1.include(req, res);
			}
			if (loginPersonId == null) {
				loginPersonId = (String) session.getAttribute("user");
			}
			String pathh = ""; pathh = req.getContextPath();
			String usrModeSC = "";
			usrModeSC = (String) session.getAttribute("userLoginMode") == null ? "": (String) session.getAttribute("userLoginMode");
			if (usrModeSC.length() > 0){
				DBConnectionStatus.statusStatic = usrModeSC;
			}
			else {
				res.sendRedirect(pathh + "/login/error_messg.jsp");
				return;
			}
			
			// Start Modified By Rajesh R For Export table
			if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("Export_By_Date")) 
			{
				logger.info("In New begin");
				String error = "";
				RequestDispatcher disp = null;
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				expObj.setDobDay(req.getParameter(DOBDAY) == null ? "" :req.getParameter(DOBDAY));
				expObj.setDobMonth(req.getParameter(DOBMONTH) == null ? "" :req.getParameter(DOBMONTH));
				expObj.setDobYear(req.getParameter(DOBYEAR) == null ? "" :req.getParameter(DOBYEAR));
				expObj.setDobDay1(req.getParameter(DOBDAY1) == null ? "" :req.getParameter(DOBDAY1));
				expObj.setDobMonth1(req.getParameter(DOBMONTH1) == null ? "":req.getParameter(DOBMONTH1));
				expObj.setDobYear1(req.getParameter(DOBYEAR1) == null ? "" :req.getParameter(DOBYEAR1));
				expObj.setBranch(req.getParameter(BRANCH) == null ? "" : req.getParameter(BRANCH));
				expObj.setPid(req.getParameter(PID) == null ? "" : req.getParameter(PID));
				String data1 = ""; data1 = (req.getParameter(DOBDAY) + "-"+ req.getParameter(DOBMONTH) + "-" + req.getParameter(DOBYEAR));
				String data2 = ""; data2 = (req.getParameter(DOBDAY1) + "-"+ req.getParameter(DOBMONTH1) + "-" + req.getParameter(DOBYEAR1));
				expObj.setData1(data1);
				expObj.setData2(data2);

				error = expObj.validateDateFields();

				if (error.length() > 0) {
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					req.setAttribute(ERRORDETAIL, error);
					disp = req.getRequestDispatcher("../admin/Export_By_Dates.jsp");

				} else {
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					logger.info("Export_Result_By_Date");
					disp = req.getRequestDispatcher("../admin/Export_Result_By_Date.jsp");
				}

				if (disp != null){
					disp.forward(req, res);
				}
			} 
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("Export_By_Policy_Range")) 
			{
				String pno = "";
				String pno1 ="";
				String error = "";
				String bran=(String)session.getAttribute("LoginBranchCode");
				RequestDispatcher disp = null;
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				pno = req.getParameter(QNOFROM) == null ? "" : req.getParameter(QNOFROM);
				pno1 = req.getParameter(QNOTO) == null ? "" : req.getParameter(QNOTO);

				expObj.setQnoFrom(pno);
				expObj.setQnoTo(pno1);
				// cc.setBranch(req.getParameter(BRANCH)==null?"":req.getParameter(BRANCH));
				// cc.setPid(req.getParameter(PID)==null?"":req.getParameter(PID));
				error = expObj.validatePolicyFields(bran);

				if (error.length() > 0) {
					req.setAttribute("errorDetail", error);
					disp = req.getRequestDispatcher("../admin/Export_By_Policy_Range.jsp");
				} 
				else if(pno.indexOf("-")==-1 || pno1.indexOf("-")==-1 )
				{
					req.setAttribute("errorDetail","<br>*Invalid Policy Numbers");
					disp = req.getRequestDispatcher("../admin/Export_By_Policy_Range.jsp");
				}	
				else
				{
					
					String pid = "";
					String branch = "";
					if (pno.length() > 0) {
						pid = dataSelect.ProductId_Select(pno);
						StringTokenizer st = new StringTokenizer(pno, "-");
						if (st.hasMoreTokens()) {
							String token1 = st.nextToken();
							String token2 = st.nextToken();
							String token3 = st.nextToken();
							
						}
						req.setAttribute("branch", (String)session.getAttribute("BNames"));
						req.setAttribute("pid", pid);
					}
					disp = req.getRequestDispatcher("../admin/Export_Result_By_PolicyRange.jsp");
				}
				if (disp != null){
					disp.forward(req, res);
				}
			} 
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("Export_By_Policy_Number")) 
			{
				String error = "";
				String quotenoss = "";
				String bran=""; bran = (String)session.getAttribute("LoginBranchCode");
				RequestDispatcher disp = null;
				StringBuffer policyList = new StringBuffer();
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				quotenoss = req.getParameter(QUOTENOSS) == null ? "" : req.getParameter(QUOTENOSS);
				expObj.setQuotenoss(quotenoss);
				// expObj.setBranch(req.getParameter(BRANCH)==null?"":req.getParameter(BRANCH));
				// expObj.setPid(req.getParameter(PID)==null?"":req.getParameter(PID));
				expObj.setPolicyList(policyList);
				error = expObj.validatePolicyNos(bran);
				policyList = expObj.getPolicyList();
				
				if (error.length() > 0) 
				{
					req.setAttribute("errorDetail", error);
					disp = req.getRequestDispatcher("../admin/Export_By_Policy_Number.jsp");
				} 
				else if(policyList.indexOf("-")==-1)
				{
					req.setAttribute("errorDetail","<br>*Invalid Policy Numbers");
					disp = req.getRequestDispatcher("../admin/Export_By_Policy_Number.jsp");
				}	
				else 
				{
				
					String pid = "";
					String branch = "";
					String token1="";
					String token2="";
					String token3="";
					if (quotenoss.length() > 0) 
					{
						pid = dataSelect.ProductId_Select(policyList);
						StringTokenizer temp1 = new StringTokenizer(quotenoss,",");
						if (temp1.hasMoreTokens()) {
							String temp = temp1.nextToken();
							StringTokenizer st = new StringTokenizer(temp, "-");
							if (st.hasMoreTokens()) 
							{
								token1 = st.nextToken();
								token2 = st.nextToken();
								token3 = st.nextToken();
							}
						}
						req.setAttribute("PolicyList", policyList);
						req.setAttribute("branch", (String)session.getAttribute("BNames"));
						req.setAttribute("pid", pid);
					}
					disp = req.getRequestDispatcher("../admin/Export_Result_By_PolicyNumber.jsp");
				}
				if (disp != null){
					disp.forward(req, res);
				}
			}
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("Export_Report_By_Date")) 
			{
				logger.info("In New begin");
				String error = "";
				RequestDispatcher disp = null;
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				expObj.setDobDay(req.getParameter(DOBDAY) == null ? "" : req.getParameter(DOBDAY));
				expObj.setDobMonth(req.getParameter(DOBMONTH) == null ? "" : req.getParameter(DOBMONTH));
				expObj.setDobYear(req.getParameter(DOBYEAR) == null ? "" : req.getParameter(DOBYEAR));
				expObj.setDobDay1(req.getParameter(DOBDAY1) == null ? "" : req.getParameter(DOBDAY1));
				expObj.setDobMonth1(req.getParameter(DOBMONTH1) == null ? "": req.getParameter(DOBMONTH1));
				expObj.setDobYear1(req.getParameter(DOBYEAR1) == null ? "" :req.getParameter(DOBYEAR1));
				expObj.setBranch(req.getParameter(BRANCH) == null ? "" : req.getParameter(BRANCH));
				expObj.setPid(req.getParameter(PID) == null ? "" : req.getParameter(PID));
				String data1 = ""; data1 = (req.getParameter(DOBDAY) + "-"+ req.getParameter(DOBMONTH) + "-" + req.getParameter(DOBYEAR));
				String data2 = ""; data2 = (req.getParameter(DOBDAY1) + "-"+ req.getParameter(DOBMONTH1) + "-" + req.getParameter(DOBYEAR1));
				expObj.setData1(data1);
				expObj.setData2(data2);

				error = expObj.validateDateFields();

				if (error.length() > 0) {
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					req.setAttribute(ERRORDETAIL, error);
					disp = req.getRequestDispatcher("../admin/Export_Report_By_Date.jsp");
				} else {
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					logger.info("Export_Result_By_Date");
					disp = req.getRequestDispatcher("../admin/Export_Result_Report.jsp");
				}
				if (disp != null){
					disp.forward(req, res);
				}
	
			} else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("Close_Date")) {
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				RequestDispatcher disp = null;
				// ///////////////Closed Date///////////
				String closedDay = ""; closedDay = req.getParameter("closedDay") == null ? SELECT: req.getParameter("closedDay");
				String closedMonth = ""; closedMonth =  req.getParameter("closedMonth") == null ? SELECT: req.getParameter("closedMonth");
				String closedYear = ""; closedYear = req.getParameter("closedYear") == null ? SELECT: req.getParameter("closedYear");
				// ///////////////Open Date/////////
				String openDay = ""; openDay = req.getParameter("openDay") == null ? SELECT: req.getParameter("openDay");
				String openMonth = ""; openMonth =req.getParameter("openMonth") == null ? SELECT: req.getParameter("openMonth");
				String openYear = req.getParameter("openYear") == null ? SELECT: req.getParameter("openYear");
				// ///////////////Entry Date////////
				String entryDay = ""; entryDay = req.getParameter("entryDay") == null ? SELECT: req.getParameter("entryDay");
				String entryMonth = ""; entryMonth = req.getParameter("entryMonth") == null ? SELECT: req.getParameter("entryMonth");
				String entryYear = ""; entryYear = req.getParameter("entryYear") == null ? SELECT: req.getParameter("entryYear");
				// //////// Remarks /////////
				String ClosedRemarks = "";ClosedRemarks = req.getParameter("Closed_Remarks") == null ? "": req.getParameter("Closed_Remarks");
				String loginId = ""; loginId = req.getParameter("loginId") == null ? "" : req.getParameter("loginId");
				String productCode = ""; productCode = req.getParameter("productCode") == null ? "" : req.getParameter("productCode");
	
				String braCode = "";
				String cid = "";
				braCode = (String)session.getAttribute("adminBranch");
				cid = (String)session.getAttribute("AdminCountryId");
				expObj.setClosedDay(closedDay);
				expObj.setClosedMonth(closedMonth);
				expObj.setClosedYear(closedYear);
				expObj.setOpenDay(openDay);
				expObj.setOpenMonth(openMonth);
				expObj.setOpenYear(openYear);
				expObj.setEntryDay(entryDay);
				expObj.setEntryMonth(entryMonth);
				expObj.setEntryYear(entryYear);
				expObj.setClosedRemarks(ClosedRemarks);
				expObj.setLoginId(loginId);
				expObj.setCoreProductCode(productCode);
				// Validation //
				String trnerror = "";
				trnerror = expObj.validateTrnCloseFields();
	
				if (trnerror.length() > 0) {
					req.setAttribute("Trn_Error", trnerror);
					disp = req.getRequestDispatcher("../admin/Close_Date.jsp");
					if (disp != null){
						disp.forward(req, res);
					}
				} else {
					if(braCode.indexOf('\'')!=-1){
						braCode = braCode.replaceAll("'","");
					}
					String result = ""; result = expObj.insertCloseTRN(braCode,cid,productCode);
					
					if (result.equalsIgnoreCase("INSERRTED")|| result.equalsIgnoreCase("UPDATED")) {
						trnerror = "Record is Inserted Suceesfully";
						req.setAttribute("Trn_Error", trnerror);
						req.setAttribute("productCode", productCode);
						disp = req.getRequestDispatcher("../admin/Close_Date_Confirmation.jsp");
						
						if (disp != null)
						{
							disp.forward(req, res);
							if(true){return;}
						}
					} else {
						trnerror = "Record is not Inserted";
						req.setAttribute("Trn_Error", trnerror);
						disp = req.getRequestDispatcher("../admin/Close_Date.jsp");
						if (disp != null){
							disp.forward(req, res);
						}
					}
				}
			}// Modified By Rajesh For Close TRN End //
	
			// Modified Purpose here is to Show the Mississippi Customer Id
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("MississippiCutomerId")) 
			{
				logger.info("Export Table Controller Mississippi Customer Id Begin...");
				String error = "";
				RequestDispatcher disp = null;
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				expObj.setDobDay(req.getParameter(DOBDAY) == null ? "" : req.getParameter(DOBDAY));
				expObj.setDobMonth(req.getParameter(DOBMONTH) == null ? "" : req.getParameter(DOBMONTH));
				expObj.setDobYear(req.getParameter(DOBYEAR) == null ? "" : req.getParameter(DOBYEAR));
				expObj.setDobDay1(req.getParameter(DOBDAY1) == null ? "" : req.getParameter(DOBDAY1));
				expObj.setDobMonth1(req.getParameter(DOBMONTH1) == null ? "": req.getParameter(DOBMONTH1));
				expObj.setDobYear1(req.getParameter(DOBYEAR1) == null ? "" : req.getParameter(DOBYEAR1));
				expObj.setBranch(req.getParameter(BRANCH) == null ? "" : req.getParameter(BRANCH));
				expObj.setPid(req.getParameter(PID) == null ? "" : req.getParameter(PID));
				String data1 = ""; data1 = (req.getParameter(DOBDAY) + "-"+ req.getParameter(DOBMONTH) + "-" + req.getParameter(DOBYEAR));
				String data2 = ""; data2 = (req.getParameter(DOBDAY1) + "-"+ req.getParameter(DOBMONTH1) + "-" + req.getParameter(DOBYEAR1));
				expObj.setData1(data1);
				expObj.setData2(data2);

				error = expObj.validateDateFields();

				if (error.length() > 0) {
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					req.setAttribute(ERRORDETAIL, error);
					disp = req.getRequestDispatcher("../admin/Mississippi_CustomerId_Dates.jsp");
				} else {
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					logger.info("Mississippi_CustomerId_Result");
					disp = req.getRequestDispatcher("../admin/Mississippi_CustomerId_Result.jsp");
				}
				if (disp != null){
					disp.forward(req, res);
				}
			}
			else if(req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("MississippiCodeUpdates"))
			{
				logger.info("Mississippi Code Updates Begin...");
				String missipInfo[][] = new String[0][0];
				String error = "";
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				RequestDispatcher disp = null;
				missipInfo = (String [][])session.getAttribute("missipInfo");
				String missipCode[][] = new String[missipInfo.length][2];
				for(int i=0; i<missipInfo.length; i++)
				{
					missipCode[i][0] = missipInfo[i][0]!=null?missipInfo[i][0]:"";
					missipCode[i][1] = req.getParameter(missipInfo[i][0])==null ? "" : req.getParameter(missipInfo[i][0]);
				}
				error = expObj.updateMississippiCode(missipCode);
				if(error.length() >0)
				{
					req.setAttribute(ERRORDETAIL, error);
					disp = req.getRequestDispatcher("../admin/Mississippi_Code_updates.jsp");
				}
				if(disp != null){
					disp.forward(req,res);
				}
			}
			//Home & Travel Missippi Integration Start
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("TravelExportByDate")) 
			{
				String error = "";
				RequestDispatcher disp = null;
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				expObj.setDobDay(req.getParameter(DOBDAY) == null ? "" :req.getParameter(DOBDAY));
				expObj.setDobMonth(req.getParameter(DOBMONTH) == null ? "" :req.getParameter(DOBMONTH));
				expObj.setDobYear(req.getParameter(DOBYEAR) == null ? "" :req.getParameter(DOBYEAR));
				expObj.setDobDay1(req.getParameter(DOBDAY1) == null ? "" :req.getParameter(DOBDAY1));
				expObj.setDobMonth1(req.getParameter(DOBMONTH1) == null ? "":req.getParameter(DOBMONTH1));
				expObj.setDobYear1(req.getParameter(DOBYEAR1) == null ? "" :req.getParameter(DOBYEAR1));
				expObj.setBranch(req.getParameter(BRANCH) == null ? "" : req.getParameter(BRANCH));
				expObj.setPid(req.getParameter(PID) == null ? "" : req.getParameter(PID));
				String data1 = ""; data1 = (req.getParameter(DOBDAY) + "-"+ req.getParameter(DOBMONTH) + "-" + req.getParameter(DOBYEAR));
				String data2 = ""; data2 = (req.getParameter(DOBDAY1) + "-"+ req.getParameter(DOBMONTH1) + "-" + req.getParameter(DOBYEAR1));
				expObj.setData1(data1);
				expObj.setData2(data2);

				error = expObj.validateDateFields();

				if (error.length() > 0) {
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					req.setAttribute(ERRORDETAIL, error);
					disp = req.getRequestDispatcher("../TravelExport/Export_By_Dates.jsp");
				}
				else 
				{
					req.setAttribute(DATA1, data1);
					req.setAttribute(DATA2, data2);
					disp = req.getRequestDispatcher("../TravelExport/Export_Result_By_Date.jsp");
				}
				if (disp != null){
					disp.forward(req, res);
				}
			}
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("TravelExportByPolicyNumber")) 
			{
				String error = "";
				String quotenoss = "";
				String bran= ""; bran = (String)session.getAttribute("loginBranch");
				RequestDispatcher disp = null;
				StringBuffer policyList = null; policyList = new StringBuffer(1000);
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				quotenoss = req.getParameter(QUOTENOSS) == null ? "" : req.getParameter(QUOTENOSS);
				expObj.setQuotenoss(quotenoss);
				expObj.setPolicyList(policyList);
				error = expObj.validatePolicyNos(bran);
				policyList = expObj.getPolicyList();
				if (error.length() > 0) 
				{
					req.setAttribute(ERRORDETAIL, error);
					disp = req.getRequestDispatcher("../TravelExport/Export_By_Policy_Number.jsp");
				} 
				else if(policyList.indexOf("/")==-1)
				{
					req.setAttribute(ERRORDETAIL,"<br>*Invalid Policy Numbers");
					disp = req.getRequestDispatcher("../TravelExport/Export_By_Policy_Number.jsp");
				}	
				else 
				{
					String pid = "";
					/*String token1="";
					String token2="";
					String token3="";*/
					if (quotenoss.length() > 0) 
					{
						pid = dataSelect.htoProductIdSelect(policyList);
						StringTokenizer temp1 = null; temp1 = new StringTokenizer(quotenoss,",");
						if (temp1.hasMoreTokens()) 
						{
							String temp = ""; temp1.nextToken();
							StringTokenizer stoken = null; stoken = new StringTokenizer(temp, "/");
							if (stoken.hasMoreTokens()) 
							{
								/*token1 = st.nextToken();
								token2 = st.nextToken();
								token3 = st.nextToken();*/
								stoken.nextToken();
								stoken.nextToken();
								stoken.nextToken();
							}
						}
						req.setAttribute("PolicyList", policyList);
						req.setAttribute(BRANCH, (String)session.getAttribute(BNAMES));
						req.setAttribute(PID, pid);
					}
					disp = req.getRequestDispatcher("../TravelExport/Export_Result_By_PolicyNumber.jsp");
				}
				if (disp != null){
					disp.forward(req, res);
				}
			}
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("TravelExportByPolicyRange")) 
			{
				String pno = "";
				String pno1 ="";
				String error = "";
				String bran = ""; bran = (String)session.getAttribute("LoginBranchCode");
				
				RequestDispatcher disp = null;
				com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
				pno = req.getParameter(QNOFROM) == null ? "" : req.getParameter(QNOFROM);
				pno1 = req.getParameter(QNOTO) == null ? "" : req.getParameter(QNOTO);

				expObj.setQnoFrom(pno);
				expObj.setQnoTo(pno1);
				error = expObj.validatePolicyFields(bran);
				if (error.length() > 0) 
				{
					req.setAttribute(ERRORDETAIL, error);
					disp = req.getRequestDispatcher("../TravelExport/Export_By_Policy_Range.jsp");
				} 
				else if(pno.indexOf('/')==-1 || pno1.indexOf('/')==-1 )
				{
					req.setAttribute(ERRORDETAIL,"<br>*Invalid Policy Numbers");
					disp = req.getRequestDispatcher("../TravelExport/Export_By_Policy_Range.jsp");
				}	
				else
				{
					String pid = "";
					if (pno.length() > 0) {
						pid = dataSelect.htoProductIdSelect(pno);
						StringTokenizer stoken = null; stoken = new StringTokenizer(pno, "/");
						if (stoken.hasMoreTokens()) {
							/*String token1 = st.nextToken();
							String token2 = st.nextToken();
							String token3 = st.nextToken();*/
							stoken.nextToken();
							stoken.nextToken();
							stoken.nextToken();
						}
						req.setAttribute(BRANCH, (String)session.getAttribute(BNAMES));
						req.setAttribute(PID, pid);
					}
					disp = req.getRequestDispatcher("../TravelExport/Export_Result_By_PolicyRange.jsp");
				}
				if (disp != null){
					disp.forward(req, res);
				}
			}
			else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("TravelExportReport")) 
			{
					String error = "";
					RequestDispatcher disp = null;
					com.maan.admin.ExportBean expObj = null; expObj = new com.maan.admin.ExportBean();
					expObj.setDobDay(req.getParameter(DOBDAY) == null ? "" : req.getParameter(DOBDAY));
					expObj.setDobMonth(req.getParameter(DOBMONTH) == null ? "" : req.getParameter(DOBMONTH));
					expObj.setDobYear(req.getParameter(DOBYEAR) == null ? "" : req.getParameter(DOBYEAR));
					expObj.setDobDay1(req.getParameter(DOBDAY1) == null ? "" : req.getParameter(DOBDAY1));
					expObj.setDobMonth1(req.getParameter(DOBMONTH1) == null ? "": req.getParameter(DOBMONTH1));
					expObj.setDobYear1(req.getParameter(DOBYEAR1) == null ? "" :req.getParameter(DOBYEAR1));
					expObj.setBranch(req.getParameter(BRANCH) == null ? "" : req.getParameter(BRANCH));
					expObj.setPid(req.getParameter(PID) == null ? "" : req.getParameter(PID));
					String data1 = ""; data1 = (req.getParameter(DOBDAY) + "-"+ req.getParameter(DOBMONTH) + "-" + req.getParameter(DOBYEAR));
					String data2 = ""; data2 = (req.getParameter(DOBDAY1) + "-"+ req.getParameter(DOBMONTH1) + "-" + req.getParameter(DOBYEAR1));
					expObj.setData1(data1);
					expObj.setData2(data2);
	
					error = expObj.validateDateFields();
	
					if (error.length() > 0) {
						req.setAttribute(DATA1, data1);
						req.setAttribute(DATA2, data2);
						req.setAttribute(ERRORDETAIL, error);
						disp = req.getRequestDispatcher("../TravelExport/Export_Report_By_Date.jsp");
					} 
					else {
						req.setAttribute(DATA1, data1);
						req.setAttribute(DATA2, data2);
						disp = req.getRequestDispatcher("../TravelExport/Export_Result_Report.jsp");
					}
					if (disp != null){
						disp.forward(req, res);
					}
			}else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("ReceiptRegister")) 
			{
				System.out.println("FROM rr CONTROLLER.......");
				
					String[][] result = new String[0][0];
					String error = "";
					String values = null;
					String pid=(req.getParameter("pid") == null) ? "" : (String)req.getParameter("pid");
					System.out.println("pid is :"+pid);
					RequestDispatcher rd = null;
					com.maan.admin.ExportBean cc = new com.maan.admin.ExportBean();
					cc.setDobDay(req.getParameter("dobDay") == null ? "" :req.getParameter("dobDay"));
					cc.setDobMonth(req.getParameter("dobMonth") == null ? "" :req.getParameter("dobMonth"));
					cc.setDobYear(req.getParameter("dobYear") == null ? "" :req.getParameter("dobYear"));
					cc.setDobDay1(req.getParameter("dobDay1") == null ? "" :req.getParameter("dobDay1"));
					cc.setDobMonth1(req.getParameter("dobMonth1") == null ? "":req.getParameter("dobMonth1"));
					cc.setDobYear1(req.getParameter("dobYear1") == null ? "" :req.getParameter("dobYear1"));
					cc.setPid(req.getParameter("pid") == null ? "" : req.getParameter("pid"));
					String data1 = (req.getParameter("dobDay") + "-"+ req.getParameter("dobMonth") + "-" + req.getParameter("dobYear"));
					String data2 = (req.getParameter("dobDay1") + "-"+ req.getParameter("dobMonth1") + "-" + req.getParameter("dobYear1"));
					cc.setData1(data1);
					cc.setData2(data2);

					error = cc.validateRegisterReportFields();

					if (error.length() > 0) {
						req.setAttribute("data1", data1);
						req.setAttribute("data2", data2);
						req.setAttribute("errorDetail", error);
						rd = req.getRequestDispatcher("../TravelExport/ReceiptRegister.jsp");

					}
					else 
					{
						String branch_code=(String)session.getAttribute("LoginBranchCode");
						com.maan.admin.DataSelection dataSelect=new com.maan.admin.DataSelection();
						result=(String[][])dataSelect.getReceiptRegisterReportsForDate(data1, data2, pid, branch_code);
						session.setAttribute("data1", data1);
						session.setAttribute("data2", data2);
						session.setAttribute("pid", pid);
						session.setAttribute("ReceiptReport", result);
						rd = req.getRequestDispatcher("../TravelExport/ReceiptRegisterReport.jsp");
					}

					if (rd != null)
						rd.forward(req, res);
					
				

			
			}else if (req.getParameter(REQUESTFROM).trim().equalsIgnoreCase("CancelledPolicy")) 
			{
				System.out.println("FROM rr CONTROLLER.......");

				
					String[][] result = new String[0][0];
					String error = "";
					String values = null;
					String pid=(req.getParameter("pid") == null) ? "" : (String)req.getParameter("pid");
					System.out.println("pid is :"+pid);
					RequestDispatcher rd = null;
					com.maan.admin.ExportBean cc = new com.maan.admin.ExportBean();
					cc.setDobDay(req.getParameter("dobDay") == null ? "" :req.getParameter("dobDay"));
					cc.setDobMonth(req.getParameter("dobMonth") == null ? "" :req.getParameter("dobMonth"));
					cc.setDobYear(req.getParameter("dobYear") == null ? "" :req.getParameter("dobYear"));
					cc.setDobDay1(req.getParameter("dobDay1") == null ? "" :req.getParameter("dobDay1"));
					cc.setDobMonth1(req.getParameter("dobMonth1") == null ? "":req.getParameter("dobMonth1"));
					cc.setDobYear1(req.getParameter("dobYear1") == null ? "" :req.getParameter("dobYear1"));
					cc.setPid(req.getParameter("pid") == null ? "" : req.getParameter("pid"));
					String data1 = (req.getParameter("dobDay") + "-"+ req.getParameter("dobMonth") + "-" + req.getParameter("dobYear"));
					String data2 = (req.getParameter("dobDay1") + "-"+ req.getParameter("dobMonth1") + "-" + req.getParameter("dobYear1"));
					cc.setData1(data1);
					cc.setData2(data2);

					error = cc.validateRegisterReportFields();

					if (error.length() > 0) {
						req.setAttribute("data1", data1);
						req.setAttribute("data2", data2);
						req.setAttribute("errorDetail", error);
						rd = req.getRequestDispatcher("../TravelExport/CancelledPolicy.jsp");

					}
					else 
					{
						String branch_code=(String)session.getAttribute("LoginBranchCode");
						com.maan.admin.DataSelection dataSelect=new com.maan.admin.DataSelection();
						result=(String[][])dataSelect.getCancelledPolicyReportsForDate(data1, data2, pid, branch_code);
						session.setAttribute("data1", data1);
						session.setAttribute("data2", data2);
						session.setAttribute("pid", pid);
						session.setAttribute("CancelledPolicy", result);
						rd = req.getRequestDispatcher("../TravelExport/CancelledPolicyReport.jsp");
					}

					if (rd != null)
						rd.forward(req, res);
					
				

			
			} //Home & Travel Missippi Integration End
			
			
		}catch(BaseException e){
			throw new BaseException(e,"Error");
		}
	}// transfer
		
}// Class
