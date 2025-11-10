package rsa.opencoverpdf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class PDFCreator extends HttpServlet
{
	private static final Logger logger = LogUtil.getLogger(PDFCreator.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -2486063562486142124L;
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String IMGSPACE = "/images/spacer.gif";
	final static transient private String PDF = ".pdf";
	final static transient private String CUSDEBITS = "/debitpdf/Customer_Debit_";
	
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException
	{
		try{
			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
	}
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException
	{
		try{
			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
	}
	public void processRequest(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException,BaseException
	{
		logger.info("opnecover PDFCreator for customerdebit processRequest method()");
		logger.debug(ENTER);
		try{
			response.setContentType("text/html");
			HttpSession session;
			session = request.getSession(false);
			finalprint print;
			print = new finalprint();
			String requestFrom;
			requestFrom = request.getParameter("requestFrom")==null?"":request.getParameter("requestFrom");
			String fontPath;
			fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
			
			//rajesh world work stated
			String currencyType = "";
			String brokerBra;
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			HashMap brokerDetails;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			int decimalDigit=0;
			if(!brokerDetails.isEmpty())
			{
				currencyType = (String)brokerDetails.get("CurrencyAbb");
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
			}
			//End
			PDFCreatorBean creatorBean;
			creatorBean = new PDFCreatorBean();
			String[][] placeCode;
			if(requestFrom.equalsIgnoreCase("")){
					String disPremium;
					disPremium = print.isNull(request.getParameter("disPremium"),"0");
					String quoteNumber;
					quoteNumber = print.isNull((String)session.getAttribute("quote_no"),"");
					String user;
					user = print.isNull((String)session.getAttribute("user"),"");
					String productId;
					productId = print.isNull((String)session.getAttribute("product_id"),"");
					String policyNumber;
					policyNumber = print.isNull(request.getParameter("PolicyNumber"),"");
	
					logger.info("MultipleDeclaration Policy Number  "+policyNumber);
	
					if(policyNumber == null||policyNumber.length()<=0){
						print.updateBrokerDebitNo(quoteNumber, user, productId, brokerBra, disPremium);
					}else{
						quoteNumber = print.updateBrokerDebitNoDeclaration(user, productId, brokerBra, disPremium,policyNumber);
					}
					logger.info("Multiple Declaration Quote No:  "+quoteNumber);
					creatorBean.setQuoteNo(quoteNumber);
					creatorBean.setLoginId(user);
					//creatorBean.setPid(productId);
					creatorBean.setBranch(brokerBra);
					creatorBean.setCurrencyType(currencyType);
					creatorBean.setDecimalDigit(decimalDigit);
					creatorBean.setDisPremium(disPremium.trim());
					creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/images/"));
					creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/images/"));
					creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+CUSDEBITS+quoteNumber+PDF));
					creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/images/"));
					creatorBean.setFontPath(fontPath);
					creatorBean.writePDF("Debit");
					response.sendRedirect(request.getContextPath()+CUSDEBITS+quoteNumber+PDF);
	        }
	        else if(requestFrom.equalsIgnoreCase("PortfolioDebit")){
	        	String quoteNumber,disPremium,mode,user;
	        	quoteNumber  = print.isNull(request.getParameter("QuoteNumber"),"");
	        	disPremium   = print.isNull(request.getParameter("disPremium"),"0");
	        	mode 	    = print.isNull(request.getParameter("mode"),"");
	        	user 	    = print.isNull((String)session.getAttribute("user"),"");
	
	        	String productId;
	        	productId = print.getQuoteProductId(quoteNumber);
	
	        	if(mode.equalsIgnoreCase("new")){
	        		print.updateBrokerDebitNo(quoteNumber, user, productId, brokerBra, disPremium);
	        	}
	        	else if(mode.equalsIgnoreCase("old")){
	        		String pdfFilePath;
	        		pdfFilePath = request.getSession().getServletContext().getRealPath(CUSDEBITS+quoteNumber+PDF);
					File checkPolicyFile;
					checkPolicyFile = new File(pdfFilePath);
					if(checkPolicyFile.exists()){
						response.sendRedirect(request.getContextPath()+CUSDEBITS+quoteNumber+PDF);
						return;
					}
	        	}
	        	creatorBean.setQuoteNo(quoteNumber);
				creatorBean.setLoginId(user);
				//creatorBean.setPid(productId);
				creatorBean.setBranch(brokerBra);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setDecimalDigit(decimalDigit);
				creatorBean.setDisPremium(disPremium);
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/images/"));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/images/"));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+CUSDEBITS+quoteNumber+PDF));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/images/"));
				creatorBean.writePDF("Debit");
				response.sendRedirect(request.getContextPath()+"\\debitpdf\\Customer_Debit_"+quoteNumber+PDF);
				return;
	        }
	        else if(requestFrom.equalsIgnoreCase("FromAdmin")){
	        	String policyNumber;
	        	policyNumber = print.isNull(request.getParameter("policyNumber"),"");
	
	        	String quoteNumber;
	        	quoteNumber = print.getQuoteNumber(policyNumber);
	
	        	String pdfFilePath;
	        	pdfFilePath = request.getSession().getServletContext().getRealPath(CUSDEBITS+quoteNumber+PDF);
				File checkPolicyFile;
				checkPolicyFile = new File(pdfFilePath);
				if(checkPolicyFile.exists()){
					response.sendRedirect(request.getContextPath()+"\\debitpdf\\Customer_Debit_"+quoteNumber+PDF);
					return;
				}
	        }
	        else if(requestFrom.equalsIgnoreCase("DeclarationPortFolioDebit")){
	        	String quoteNumber,policyNumber,disPremium,mode,user;
	
	        	policyNumber = print.isNull(request.getParameter("PolicyNumber"),"");
	        	disPremium   = print.isNull(request.getParameter("disPremium"),"0");
	        	mode 	    = print.isNull(request.getParameter("mode"),"");
	        	user 	    = print.isNull((String)session.getAttribute("user"),"");
	
	        	String productId;
	        	productId =  print.getPolicyProductId(policyNumber);
	
	        	if(mode.equalsIgnoreCase("new")){
	        		quoteNumber = print.updateBrokerDebitNoDeclaration(user, productId, brokerBra, disPremium,policyNumber);
	        	}
	        	else {
	        		quoteNumber = print.getMaxQuoteNum(policyNumber);
	        		logger.info("OLD Quote Number "+quoteNumber);
	        		String pdfFilePath;
	        		pdfFilePath = request.getSession().getServletContext().getRealPath(CUSDEBITS+quoteNumber+PDF);
					File checkPolicyFile;
					checkPolicyFile = new File(pdfFilePath);
					if(checkPolicyFile.exists()){
						response.sendRedirect(request.getContextPath()+CUSDEBITS+quoteNumber+PDF);
						return;
					}
	        	}
	        	creatorBean.setQuoteNo(quoteNumber);
				creatorBean.setLoginId(user);
				//creatorBean.setPid(productId);
				creatorBean.setBranch(brokerBra);
				creatorBean.setCurrencyType(currencyType);
				creatorBean.setDecimalDigit(decimalDigit);
				creatorBean.setDisPremium(disPremium.trim());
				creatorBean.setHeaderImagePath(request.getSession().getServletContext().getRealPath("/images"));
				creatorBean.setFooterImagePath(request.getSession().getServletContext().getRealPath("/images/"));
				creatorBean.setFilePath(request.getSession().getServletContext().getRealPath("/"+CUSDEBITS+quoteNumber+PDF));
				creatorBean.setSignedImagePath(request.getSession().getServletContext().getRealPath("/images/"));
				creatorBean.writePDF("Debit");
				response.sendRedirect(request.getContextPath()+"\\debitpdf\\Customer_Debit_"+quoteNumber+PDF);
				return;
	        }
		}catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		logger.debug(EXIT);
		
	}
}
