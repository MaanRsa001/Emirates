package com.maan.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.admin.DAO.BranchCreationBean;

public class BranchDisplayController extends HttpServlet{

   private static final long serialVersionUID = 1L;

   public void doPost(final HttpServletRequest request,final HttpServletResponse response) throws ServletException,IOException{

       String requestFrom = request.getParameter("requestFrom");
       requestFrom = requestFrom==null?"":requestFrom;
       RequestDispatcher reqDis = null;
       HttpSession session=request.getSession();

       if("branchlist".equalsIgnoreCase(requestFrom)){

           String branchName = "";
           String branchCode = "";
           ArrayList list = new ArrayList();

           String[][] result = new BranchCreationBean().getBranchList();
           result = result == null?new String[0][0]:result;

           for(int i=0;i<result.length;i++){
                 branchName = result[i][1]==null?"":result[i][1];
                 branchCode = result[i][2]==null?"":result[i][2];
                 BranchCreationBean bean = new BranchCreationBean();
                 bean.setSno(i+1);
                 bean.setBranchName(branchName);
                 bean.setBranchCode(branchCode);
                 list.add(bean);
           }
           System.out.println("Aray List .......Senthil ................"+list.size());
           request.setAttribute("result", list);
           reqDis = request.getRequestDispatcher("/branch/BranchList.jsp");
           if(reqDis != null){
                reqDis.forward(request,response);
           }
       }
       if("BranchWiseProduct".equalsIgnoreCase(requestFrom)){
    	   String productCode="";
    	   String productName="";
    	   
    	   java.util.List list=new ArrayList();
    	   String branchCode=request.getParameter("sno")==null?"":request.getParameter("sno");
    	   //request.setAttribute("branchCode", branchCode);
    	   String[][] result = new BranchCreationBean().getProductList();
    	   for(int i=0;i<result.length;i++){
               productName = result[i][1]==null?"":result[i][1];
               productCode = result[i][0]==null?"":result[i][0];
               BranchCreationBean bean = new BranchCreationBean();
               bean.setSno(i+1);
               bean.setProductName(productName);
               bean.setProductCode(productCode);
               list.add(bean);
         }
    	   System.out.println("Aray List ..."+branchCode+"....felix ................"+list.size());
    	   request.setAttribute("result", list);
    	   reqDis = request.getRequestDispatcher("/branch/BranchWiseProduct.jsp?branchCode="+branchCode);
    	   if(reqDis != null){
               reqDis.forward(request,response);   
               return;
          }
       }
       else if("DetailedProductInfo".equalsIgnoreCase(requestFrom)){
    	   
    	   BranchCreationBean branchCreationBean=new BranchCreationBean();
    	   
    	   String mode=request.getParameter("mode")==null?"":request.getParameter("mode");
    	   
    	   String branchCode=request.getParameter("branch_code")==null?"":request.getParameter("branch_code");
    	   String productId=request.getParameter("PRODUCT_ID")==null?"":request.getParameter("PRODUCT_ID");
    	   String effectiveDate=request.getParameter("EFFECTIVE_DATE")==null?"":request.getParameter("EFFECTIVE_DATE");
    	   String remarks=request.getParameter("REMARKS")==null?"":request.getParameter("REMARKS");
    	   String status=request.getParameter("STATUS")==null?"":request.getParameter("STATUS");
    	   String quoteTypeId=request.getParameter("QUOTE_TYPE_ID")==null?"":request.getParameter("QUOTE_TYPE_ID");
    	   String policyTypeId=request.getParameter("POLICY_TYPE_ID")==null?"":request.getParameter("POLICY_TYPE_ID");
    	   String debitTypeId=request.getParameter("DEBIT_TYPE_ID")==null?"":request.getParameter("DEBIT_TYPE_ID");
    	   String proposalNoTypeId=request.getParameter("PROPOSAL_NO_TYPE_ID")==null?"":request.getParameter("PROPOSAL_NO_TYPE_ID");
    	   String dataTransferTypeId=request.getParameter("DATA_TRANSFER_TYPE_ID")==null?"":request.getParameter("DATA_TRANSFER_TYPE_ID");
    	   String transferTypeId=request.getParameter("TRANSFER_TYPE_ID")==null?"":request.getParameter("TRANSFER_TYPE_ID");
    	   String claimTypeId=request.getParameter("CLAIM_TYPE_ID")==null?"":request.getParameter("CLAIM_TYPE_ID");
    	   String customerTypeId=request.getParameter("CUSTOMER_TYPE_ID")==null?"":request.getParameter("CUSTOMER_TYPE_ID");
    	   String applicationTypeId=request.getParameter("APPLICATION_TYPE_ID")==null?"":request.getParameter("APPLICATION_TYPE_ID");
    	   String openCoverNoTypeId=request.getParameter("OPEN_COVER_NO_TYPE_ID")==null?"":request.getParameter("OPEN_COVER_NO_TYPE_ID");
    	   String certificateSlNoTypeId=request.getParameter("CERTIFICATE_SLNO_TYPE_ID")==null?"":request.getParameter("CERTIFICATE_SLNO_TYPE_ID");
    	   String certificateNoTypeId=request.getParameter("CERTIFICATE_NO_TYPE_ID")==null?"":request.getParameter("CERTIFICATE_NO_TYPE_ID");
    	   String headerImg=request.getParameter("HEADER_IMG")==null?"":request.getParameter("HEADER_IMG");
    	   String footerImg=request.getParameter("FOOTER_IMG")==null?"":request.getParameter("FOOTER_IMG");
    	   String signImg=request.getParameter("SIGN_IMG")==null?"":request.getParameter("SIGN_IMG");
    	   String stampImg=request.getParameter("STAMP_IMG")==null?"":request.getParameter("STAMP_IMG");
    	   String receiptTypeId=request.getParameter("RECEIPT_TYPE_ID")==null?"":request.getParameter("RECEIPT_TYPE_ID");
    	   
    	   branchCreationBean.setBranchCode(branchCode);
    	   branchCreationBean.setProductCode(productId);
    	   branchCreationBean.setEffectiveDate(effectiveDate);
    	   branchCreationBean.setRemarks(remarks);
    	   branchCreationBean.setStatus(status);
    	   branchCreationBean.setQuoteTypeId(quoteTypeId);
    	   branchCreationBean.setPolicyTypeId(policyTypeId);
    	   branchCreationBean.setDebitTypeId(debitTypeId);
    	   branchCreationBean.setProposalNoTypeId(proposalNoTypeId);
    	   branchCreationBean.setDataTransferTypeId(dataTransferTypeId);
    	   branchCreationBean.setTransferTypeId(transferTypeId);
    	   branchCreationBean.setClaimTypeId(claimTypeId);
    	   branchCreationBean.setCustomerTypeId(customerTypeId);
    	   branchCreationBean.setApplicationTypeId(applicationTypeId);
    	   branchCreationBean.setOpenCoverNoTypeId(openCoverNoTypeId);
    	   branchCreationBean.setCertificateSlNoTypeId(certificateSlNoTypeId);
    	   branchCreationBean.setCertificateNoTypeId(certificateNoTypeId);
    	   branchCreationBean.setHeaderImage(headerImg);
    	   branchCreationBean.setFooterImage(footerImg);
    	   branchCreationBean.setSignImage(signImg);
    	   branchCreationBean.setStamp(stampImg);
    	   branchCreationBean.setReceiptTypeId(receiptTypeId);
    	   
    	   branchCreationBean.validateBranchWiseProductDetails();
    	   String error="";
    	   error=branchCreationBean.getError();
    	   
    	   String process="NO";
    	   if(error.length()>0){
    		request.setAttribute("error", error);
    		process="NO";
    	   }
    	   else {
    	   try {
    		   process=branchCreationBean.insertOrUpdateBranchDetail(branchCode, productId, effectiveDate, remarks, status, quoteTypeId, policyTypeId, debitTypeId, proposalNoTypeId, dataTransferTypeId, transferTypeId, claimTypeId, customerTypeId, applicationTypeId, openCoverNoTypeId, certificateSlNoTypeId, certificateNoTypeId, headerImg, footerImg, signImg, stampImg, receiptTypeId, mode);
    	   }
    	   catch(Exception e){
    		   e.printStackTrace();
    	   }
    	   
    	   }
    	   if(process.equalsIgnoreCase("YES")){
    		   session.setAttribute("comFrom", "branchdetailupdation");
    		   session.setAttribute("urlProperty", "?requestFrom=BranchWiseProduct&sno="+branchCode);
    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/ratingAdmin/CommodityConformation.jsp");
				if (dispatcher != null)
					dispatcher.forward(request, response);
    		   System.out.println("success--------------------->>>>>>>>>>>>>>");
    	   }
    	   else if(process.equalsIgnoreCase("NO")){
    		   System.out.println("Failure--------------------->>>>>>>>>>>>>>");
    		   RequestDispatcher dispatcher=request.getRequestDispatcher("/branch/DetailedProductInfo.jsp");
    		   dispatcher.forward(request,response);
    	   }
    	   
    	   
    	   
       }
       else if("BranchCreation".equalsIgnoreCase(requestFrom)){

            String branchCode      = request.getParameter("BranchCode");
            String branchName      = request.getParameter("BranchName");
            String address1        = request.getParameter("Address1");
            String address2        = request.getParameter("Address2");
            String address3        = request.getParameter("Address3");
            String city            = request.getParameter("City");
            String country         = request.getParameter("Country");
            String currencyAbbre   = request.getParameter("CurrencyAbbreviation");
            String currencyAcronym = request.getParameter("CurrencyAcronym");
            String curDecimalDigit = request.getParameter("CurrencyDecimalDigit");
            String curDecimalName  = request.getParameter("CurrencyDecimalName");
            String currencyName    = request.getParameter("CurrencyName");
            String decimalPlaces   = request.getParameter("DecimalPlaces");
            String desCountryId    = request.getParameter("DestinationCountryId");
            String email           = request.getParameter("E-Mail");
            String fax             = request.getParameter("Fax");
            String headerImage     = request.getParameter("HeaderImg");
            String footerImage     = request.getParameter("FooterImg");
            String originCountryId = request.getParameter("originCountryId");
            String phone           = request.getParameter("phone");
            String stamp           = request.getParameter("Stamp");
            String tax             = request.getParameter("tax");
            String effectiveDate   = request.getParameter("startDate");
            String status          = request.getParameter("status");
            String signImage       = request.getParameter("SignImage");

              branchCode      = branchCode==null?"":branchCode;
              branchName      = branchName==null?"":branchName;
              address1        = address1==null?"":address1;
              address2        = address2==null?"":address2;
              address3        = address3==null?"":address3;
              city            = city==null?"":city;
              country         = country==null?"":country;
              currencyAbbre   = currencyAbbre==null?"":currencyAbbre;
              currencyAcronym = currencyAcronym==null?"":currencyAcronym;
              curDecimalDigit = curDecimalDigit==null?"":curDecimalDigit;
              curDecimalName  = curDecimalName==null?"":curDecimalName;
              currencyName    = currencyName==null?"":currencyName;
              decimalPlaces   = decimalPlaces==null?"":decimalPlaces;
              desCountryId    = desCountryId ==null?"":desCountryId;
              email           = email==null?"":email;
              fax             = fax==null?"":fax;
              headerImage     = headerImage==null?"":headerImage;
              footerImage     = footerImage==null?"":footerImage;
              originCountryId = originCountryId==null?"":originCountryId;
              phone           = phone==null?"":phone;
              stamp           = stamp==null?"":stamp;
              tax             = tax==null?"":tax;
              effectiveDate   = effectiveDate==null?"":effectiveDate;
              status          = status==null?"":status;
              signImage       = signImage==null?"":signImage;

              BranchCreationBean bean = new BranchCreationBean();

              bean.setBranchCode(branchCode);
              bean.setBranchName(branchName);
              bean.setAddress1(address1);
              bean.setAddress2(address2);
              bean.setAddress3(address3);
              bean.setCity(city);
              bean.setCountry(country);
              bean.setCurrencyAbbre(currencyAbbre);
              bean.setCurrencyAcronym(currencyAcronym);
              bean.setCurDecimalDigit(curDecimalDigit);
              bean.setCurDecimalName(curDecimalName);
              bean.setCurrencyName(currencyName);
              bean.setDecimalPlaces(decimalPlaces);
              bean.setDesCountryId(desCountryId);
              bean.setEffectiveDate(effectiveDate);
              bean.setEmail(email);
              bean.setFax(fax);
              bean.setFooterImage(footerImage);
              bean.setHeaderImage(headerImage);
              bean.setOriginCountryId(originCountryId);
              bean.setPhone(phone);
              bean.setSignImage(signImage);
              //bean.setSno(no);
              bean.setStamp(stamp);
              bean.setStatus(status);
              bean.setTax(tax);
       }
       return;
   }

   public void doGet(final HttpServletRequest request,final HttpServletResponse response) throws ServletException,IOException{
          doPost(request,response);
   }
}
