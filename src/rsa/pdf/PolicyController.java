package rsa.pdf;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.policyInfo;


public class PolicyController extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(PolicyController.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8278735705246378313L;

	final static transient private String NOTHING = "NOTHING";
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		try{
			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
	}

	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		try{
			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
	}
	public String isNull(final String content,final String value)throws BaseException{
		StringBuffer contents;
		contents = new StringBuffer();
		if(content==null||content.length()<=0){
			contents.append(value);
		}
		else if(content.equalsIgnoreCase("select")){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	protected void processRequest(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException, BaseException {
		try{
			response.setContentType("text/html");
			HttpSession session;
			session = request.getSession(false);
			if (session.getAttribute("ses") == null) {
				response.sendRedirect("login/error_messg.jsp");
				return;
			}
			DBConnectionStatus.statusStatic = isNull((String)session.getAttribute("userLoginMode"),"");
		
			String extraDesc;
			String claDesc;
			String warDesc;
			String exDesc;
			String loginId;
			String quoteNo;
			int claCount;
			int extraCount;
			int exCount;
			int warCount;
			String exCover;
			String extraCover;
			String transId;
			quoteNo = isNull((String) session.getAttribute("quote_no"),"");
			loginId = isNull((String) session.getAttribute("user"),"");
			claCount = Integer.parseInt(isNull(request.getParameter("clausesCount"),"0"));
			extraCount = Integer.parseInt(isNull(request.getParameter("exClausesCount"),"0"));
			exCount = Integer.parseInt(isNull(request.getParameter("exCount"),"0"));
			warCount = Integer.parseInt(isNull(request.getParameter("warCount"),"0"));
			exCover = isNull(request.getParameter("exisCoverId"),"0");
			extraCover = isNull(request.getParameter("exisExCoverId"),"0");
			transId = isNull(request.getParameter("exisTransportId"),"0");
			
			String claIds[];
			String exClaIds[];
			String warIds[];
			String exIds[];
		
			claIds = request.getParameterValues("clausesIds") == null ? new String[0]:request.getParameterValues("clausesIds");
			exClaIds = request.getParameterValues("exClausesIds") == null ? new String[0]:request.getParameterValues("exClausesIds");
			warIds = request.getParameterValues("warIds") == null ? new String[0]:request.getParameterValues("warIds");
			exIds = request.getParameterValues("exIds") == null ? new String[0]:request.getParameterValues("exIds");
			
			String addClauses = request.getParameter("addClauses") == null ? "":request.getParameter("addClauses");
			String addWarClauses = request.getParameter("addWarClauses") == null ? "":request.getParameter("addWarClauses");
			String addExclusions = request.getParameter("addExclusions") == null ? "":request.getParameter("addExclusions");
			String addWarranties = request.getParameter("addWarranties") == null ? "":request.getParameter("addWarranties");
		
		StringBuffer clauses = new StringBuffer();
		StringBuffer clausesEdit = new StringBuffer();
		String extra = "";
		String wars = "";
		String excess = "";
		
		policyInfo policyinfo;
		policyinfo = new policyInfo();
		
		for (int i1 = 0; i1 < claCount; i1++) {
			claDesc = isNull(request.getParameter("clausesDesc" + claIds[i1]),"");
			clauses.append(claIds[i1]); 
			clauses.append('~');
			clauses.append(claDesc);
			clauses.append('#');
		}
		if (clauses.length()<=0) {
			if(addClauses.length()<=0){
				clausesEdit.append(NOTHING);
			}
		} else {
			clausesEdit.append(clauses.toString().substring(0, clauses.toString().length() - 1));
		}
		
		for (int j1 = 0; j1 < extraCount; j1++) {
			extraDesc = isNull(request.getParameter("extraClausesDesc" + exClaIds[j1]),"");
			extra = extra + exClaIds[j1] + "~" + extraDesc + "#";
		}
		if ("".equals(extra)) {
			if("".equals(addWarClauses)){
				extra = NOTHING;
			}
		} else {
			extra = extra.substring(0, extra.length() - 1);
		}
		for (int k1 = 0; k1 < warCount; k1++) {
			warDesc = isNull(request.getParameter("warDesc" + warIds[k1]),"");
			wars = wars + warIds[k1] + "~" + warDesc + "#";
		}
		if ("".equals(wars)) {
			if("".equals(addWarranties)){
				wars = NOTHING;
			}
		} else {
			wars = wars.substring(0, wars.length() - 1);
		}
		for (int l1 = 0; l1 < exCount; l1++) {
			exDesc = isNull(request.getParameter("exDesc" + exIds[l1]),"");
			excess = excess + exIds[l1] + "~" + exDesc + "#";
		}
		if ("".equals(excess)) {
			if("".equals(addExclusions)){
				excess = NOTHING;
			}
		} else {
			excess = excess.substring(0, excess.length() - 1);
		}
		RequestDispatcher dispatcher;
		String policysStatus;
		String policyBackDesc;
		policysStatus = request.getParameter("policysStatus");
		if (policysStatus  == null) 
		{
			policysStatus = "0";
			policyBackDesc = "";
		}
		else{
			policyBackDesc = isNull(request.getParameter("policyBackDesc"),"");
			policysStatus = "1000";
		}

		policyinfo.updatePDFWarrantiesStatus(quoteNo, loginId, clausesEdit.toString()+addClauses, wars+addWarranties, excess+addExclusions, extra+addWarClauses,"draft", exCover, transId, extraCover, policysStatus, policyBackDesc);
		Map preDetails;
		preDetails = policyinfo.getResults((String) session.getAttribute("quote_no"),(String)session.getAttribute("customer_id"),(String)session.getAttribute("application_no"),(String)session.getAttribute("company_id"),(String)session.getAttribute("user"));
		request.setAttribute("premiumresults", preDetails);
		if ("admin".equalsIgnoreCase((String) session.getAttribute("user1"))){
			dispatcher = request.getRequestDispatcher("/premium/showQuote.jsp");
		}else{
			dispatcher = request.getRequestDispatcher("final1.jsp");
		}
			dispatcher.forward(request, response);
		}
		catch(BaseException e){
			 throw new BaseException(e,"Error");
		}
	}
		
}
