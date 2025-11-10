package com.maan.webservice;
import com.maan.common.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.maan.services.util.runner;
import com.maan.webservice.dao.QuotationLoadDAO;
import com.maan.webservice.service.QuoteGeneration;

public class QuotationLoadTest extends HttpServlet{
	private static final Logger logger = LogUtil.getLogger(QuotationLoadTest.class);
	
	private static final long serialVersionUID = 1L;
    public StringBuffer result= new StringBuffer() ;
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException
	{
		try{
			getQuotationInfoLoad(request, response);
					
		} catch(Exception e) {
			logger.debug(e);
		}
	}
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException
	{
		
		try{
			getQuotationInfoLoad(request, response);
			
		} catch(Exception e) {
			logger.debug(e);
		}
	}	
	
 public void getQuotationInfoLoad(final HttpServletRequest request, final HttpServletResponse response)  throws ServletException, IOException {
		 logger.info("Enters getQuotationInfoLoad");
		 Thread mainthread =Thread.currentThread();
	     RequestDispatcher rd = request.getRequestDispatcher("QuotationLoadTest.jsp");  
	     List responseList = null;
	     final String requestInfo = request.getParameter("request")==null?"":request.getParameter("request").trim();
		 StringTokenizer requestString = new StringTokenizer(requestInfo,",");
		 QuotationLoadDAO quoteDAO = new QuotationLoadDAO();
		 int i=0;
		 //runner.updation("delete from load_response");
		 Thread  t = null;
		 while(requestString.hasMoreTokens()) {
			i++;
		    String referenceNo = requestString.nextToken();
			try {
				String th = "th"+i;
				t = new QuoteRequest( referenceNo);
				t.setName(th);
				t.start();
				//t.join();
				//t.stop();
			} 			
			
			catch(Exception e)
			{
				System.out.println("EXCEPTION REFERENCE NO::>"+referenceNo);
				e.printStackTrace();
			}
			
		 }
		
		 if(requestInfo.equalsIgnoreCase("")){
			 request.setAttribute("validation", "invalid");
		 }
		 else
		 {
			 responseList =quoteDAO.getList();
			 request.setAttribute("result", responseList);
			//runner.updation("delete from load_response");
		 }
		 rd.forward(request, response);
		 logger.info("Exits getQuotationInfoLoad");
 }		

	 public class QuoteRequest extends Thread
	{
	   private String  referenceNo;
	   String response;
	   
	   public QuoteRequest(String referenceNo)
	   {
	      this.referenceNo = referenceNo;
	     
	   }
	   public void  run()
	   {
		  try {
			    Calendar cal;
				cal= Calendar.getInstance();
				System.out.println("------------Start-----------------");
				System.out.println("Request Start Time::>"+cal.getTime());
				System.out.println("REFERENCE NO ::>"+referenceNo);
				QuoteGeneration quoteGeneration = new QuoteGeneration();
				 PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
				String remarks= generate.quoteGeneration();
				response = quoteGeneration.generateResponse(referenceNo,remarks);
			    runner.updation("insert into load_response values('"+referenceNo+"','"+response+"')");
			   System.out.println("RESPONSE ::>"+response);
			   cal= Calendar.getInstance();
			   System.out.println("REFERENCE NO::>"+referenceNo);
			   System.out.println("Request End Time ::>"+cal.getTime());
			   System.out.println("------------End--------------------");
				
		} catch (Exception e) {
			 System.out.println("EXCEPTION REFERENCE NO ::>"+referenceNo);
			e.printStackTrace();
		}
			
	   }
	}
}
	