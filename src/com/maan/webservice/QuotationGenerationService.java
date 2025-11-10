package com.maan.webservice;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.maan.common.*;
import com.maan.webservice.service.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


public class QuotationGenerationService {
	private static final Logger logger = LogUtil.getLogger(QuotationGenerationService.class);

	public String quoteRequest(String requestInfo)
	{
		logger.info("Enter quoteRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "";
		String remarks = "", sno="", referenceNo="";
		//System.out.println("Servlet Context::>"+MessageContext.SERVLET_CONTEXT);
		try
		{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "quoteRequest");
			//requestInfo=requestInfo.replaceAll("&", "&amp;");
			authenticated = quoteGeneration.authentication(requestInfo,"PartnerInformation");
			//System.out.println("Authentication::>"+authenticated);
			//requestInfo=requestInfo.replaceAll("&amp;", "&");
			remarks = quoteGeneration.quoteRequestXMLReader(requestInfo);
			referenceNo = quoteGeneration.getReferenceNo(requestInfo,"PartnerInformation");
			
			if(authenticated){
				//remarks = quoteGeneration.quoteRequestXMLReader(requestInfo);
				//String referenceNo = quoteGeneration.getReferenceNo(requestInfo,"PartnerInformation");
			    //Add code here
				if(remarks.equalsIgnoreCase("")){
				PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
				remarks= remarks.concat(generate.quoteGeneration());
				}
				//System.out.println("test enters::");
				response = quoteGeneration.generateResponse(referenceNo,remarks);
			}
			else{
				System.out.println("Not Autheticated");
				response="<QuoteInformation><ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber></QuoteNumber>" +
						"<MarinePremium></MarinePremium>" +
						"<WarPremium></WarPremium>" +
						"<AdditionalPremium></AdditionalPremium>" +
						"<IssuanceFee></IssuanceFee>" +
						"<TotalPremium></TotalPremium>" +
						"<TotalSumInsured></TotalSumInsured>" +
						"<RefferalStatus></RefferalStatus>" +
						"<RefferalRemarks>Not Authenticated</RefferalRemarks>" +
						"</QuoteInformation> ";
			}
		
			//new PolicyGenerationAction("HTQ100009").quoteGeneration();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, ((referenceNo==null || "".equals(referenceNo))?remarks:referenceNo), sno);
		
		logger.info("Exits quoteRequest");
		return response;
		
	}
	public String draftRequest(	String requestInfo)
	{
		logger.info("Enter draftRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="", referenceNo="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "draftRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"DraftInformation");
			//System.out.println("Authentication::>"+authenticated);
			List listValues = quoteGeneration.getDraftDetails(requestInfo,"DraftInformation");
			referenceNo = listValues.get(0).toString();
			if(authenticated){
				
				String count = listValues.get(1).toString();
				String status = listValues.get(2).toString();
				String referralStatus = listValues.get(3).toString();
				System.out.println("referralStatus::>"+referralStatus);
				if(count.equalsIgnoreCase("0") && (!status.equalsIgnoreCase("0") || referralStatus.equalsIgnoreCase("No"))){
				//Add code here to generate Draft by passing reference number
				PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
				generate.draftGeneration();
				System.out.println("Block Executed");
				}
				
			    response = quoteGeneration.generateDraftResponse(requestInfo);
			}
			else{
				System.out.println("Not Autheticated");
				response=" <DraftInformation><ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						" <QuoteNumber></QuoteNumber><FileName></FileName>" +
						" <FileContent></FileContent><ErrorStatus></ErrorStatus>" +
						" <Remarks>Not Authenticated</Remarks> </DraftInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, referenceNo, sno);
		
		System.out.println("Response::>"+response);
		logger.info("Exits draftRequest");
		return response;
	}
	public String policyRequest(String requestInfo)
	{
		logger.info("Enter policyRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="", referenceNo="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "policyRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"PolicyInformation");
			System.out.println("Authentication::>"+authenticated);
			List listValues = quoteGeneration.getPolicyDetails(requestInfo,"PolicyInformation");
			referenceNo = listValues.get(1).toString();
			if(authenticated){
				String policyRequest = listValues.get(0).toString();
				String count = listValues.get(2).toString();
				String status = listValues.get(3).toString(); 	
				String referralStatus = listValues.get(4).toString();
				String remarks = "";
				System.out.println("count::>"+count);
				System.out.println("referralStatus::>"+referralStatus);
				if(policyRequest.equalsIgnoreCase("Yes") && count.equalsIgnoreCase("0") && !status.equalsIgnoreCase("0") && referralStatus.equalsIgnoreCase("No"))
				{
					String policyProcess[][] = quoteGeneration.getPolicyProcessStatus(referenceNo, "Y");
					if(!"Y".equals(policyProcess[0][0])){
						//Add code here to generate Policy by passing reference number
						PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
						generate.policyGeneration();
					}else{
						response="<PolicyInformation><ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber></QuoteNumber><PolicyNo></PolicyNo>" +
						"<FileName></FileName>" +
						"<FileContent>" +
						"</FileContent>" +
						"<ErrorStatus></ErrorStatus><Remarks>Policy Process already Started</Remarks>" +
						"</PolicyInformation>";
					}
					quoteGeneration.getPolicyProcessStatus(referenceNo, "");
					//System.out.println("Block Executed");
					//Add code here to generate Policy by passing reference number
					//PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					//generate.policyGeneration();
					//System.out.println("Block Executed");
					////quoteGeneration.updatePolicyRequest(referenceNo);
				}
				else if(policyRequest.equalsIgnoreCase("Yes") && !count.equalsIgnoreCase("0"))
				{
					System.out.println( "Policy Generation already requested ");
				
				}
				else
				{
					System.out.println("Policy Generation not requested or  not quote ");
				}
				
				if(status.equalsIgnoreCase("0")){
					System.out.println( remarks);
				}
				if("".equals(response)){
					response = quoteGeneration.generatePolicyResponse(requestInfo,remarks);
				}
			    //response = quoteGeneration.generatePolicyResponse(requestInfo,remarks);
			}
			else{
				System.out.println("Not Autheticated");
				response="<PolicyInformation><ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber></QuoteNumber><PolicyNo></PolicyNo>" +
						"<FileName></FileName>" +
						"<FileContent>" +
						"</FileContent>" +
						"<ErrorStatus></ErrorStatus><Remarks>Not Authenticated</Remarks>" +
						"</PolicyInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, referenceNo, sno);
		System.out.println("Response::>"+response);
		logger.info("Exits policyRequest");
		return response;
	}
	public String adminReferral(String requestInfo)
	{
		logger.info("Enter adminReferralRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="",referenceNo="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "adminReferral");
			authenticated = quoteGeneration.authentication(requestInfo,"ReferralInformation");
			//System.out.println("Authentication::>"+authenticated);
			List listValues = quoteGeneration.getReferralDetails(requestInfo,"ReferralInformation");
			referenceNo = listValues.get(0).toString();
			String quoteNo = listValues.get(1).toString();
			String AdminReferralStatus = listValues.get(2).toString();
			String AdminReferralRemarks = listValues.get(3).toString();
			String referralRemarks = listValues.get(4).toString();
			String remarks = "";
			if(authenticated){
				String count = listValues.get(1).toString();
				System.out.println("count::>"+count);
				System.out.println("AdminReferralStatus::>"+AdminReferralStatus);
				System.out.println("referralRemarks::>"+referralRemarks);
				
				String policyStatus=quoteGeneration.getPolicyStatus(referenceNo);
				if(policyStatus.equalsIgnoreCase("0")){
					if(AdminReferralStatus.equalsIgnoreCase("Y") && !AdminReferralRemarks.equalsIgnoreCase("") && !count.equalsIgnoreCase("0"))
					{
					System.out.println("STATUS Y Method Calling");
					PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					generate.updateAdminReferralInfo();
					}
					else if(AdminReferralStatus.equalsIgnoreCase("N") && AdminReferralRemarks.equalsIgnoreCase("") && !count.equalsIgnoreCase("0") && !referralRemarks.equalsIgnoreCase("Admin"))
					{
						System.out.println("STATUS N Method Calling");
						PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
						generate.updateAdminReferralInfo();
					}
					else
					{
						System.out.println("Quote Not Available");
						response="<ReferralInformation><ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
								 "<Remarks>Quote Not Available</Remarks>" +
								 "</ReferralInformation>";
				    }
					response = quoteGeneration.generateReferralResponse(requestInfo,remarks);
				}else{
					System.out.println("Policy Already Generated");
					response="<ReferralInformation><ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
					         "<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
							 "<Remarks>Given Quote was already generated as policy</Remarks>" +
							 "</ReferralInformation>";
				}
			    }
			  else{
				System.out.println("Not Autheticated");
				response="<ReferralInformation><ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber></QuoteNumber><PolicyNo></PolicyNo>" +
						"<FileName></FileName>" +
						"<FileContent>" +
						"</FileContent>" +
						"<ErrorStatus></ErrorStatus><Remarks>Not Authenticated</Remarks>" +
						"</ReferralInformation>";
			      }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, referenceNo, sno);
		System.out.println("Response::>"+response);
		logger.info("Exits ReferralRequest");
		return response;
	}
	public String premiumRequest(String requestInfo)
	{
		logger.info("Enter premiumRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "premiumRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"PremiumInformation");
			//System.out.println("Authentication::>"+authenticated);
			if(authenticated){
			
			    response = quoteGeneration.generatePremiumResponse(requestInfo);
			}
			else{
				System.out.println("Not Autheticated");
				response="<PremiumInformation><ReferenceNumber></ReferenceNumber>" +
						"<QuoteNumber></QuoteNumber>" +
						"<Premium></Premium>" +
						"<RefferalStatus></RefferalStatus>" +
						"<RefferalRemarks>Not Authenticated</RefferalRemarks>" +
						"</PremiumInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, "", sno);
		//System.out.println("Response::>"+response);
		logger.info("Exits premiumRequest");
		
		return response;

	}
	public String quotePdfRequest(String requestInfo)
	{
		logger.info("Enter quotePdfRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="", referenceNo="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "quotePdfRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"QuoteInformation");
			//System.out.println("Authentication::>"+authenticated);
			List listValues = quoteGeneration.getQuotePDFDetails(requestInfo,"QuoteInformation");
			referenceNo = listValues.get(0).toString();
			String quoteNo = listValues.get(3).toString();
			if(authenticated){
				
				String count = listValues.get(1).toString();
				String status = listValues.get(2).toString();
				//String referralStatus = listValues.get(3).toString();
				
				if(count.equalsIgnoreCase("0") && (!status.equalsIgnoreCase("0") )){
				//Add code here to generate Draft by passing reference number
				PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
				generate.docGeneration("QUOTE");
				System.out.println("Block Executed");
				}
				
			    response = quoteGeneration.generatePDFResponse(requestInfo,"quote");
			}
			else{
				System.out.println("Not Autheticated");
				response=" <QuoteInformation>" +
						" <QuoteNumber>"+quoteNo+"</QuoteNumber><FileName></FileName>" +
						" <FileContent></FileContent><ErrorStatus></ErrorStatus>" +
						" <Remarks>Not Authenticated</Remarks> </QuoteInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, referenceNo, sno);
		System.out.println("Response::>"+response);
		logger.info("Exits quotePdfRequest");
		return response;
		

	}
	public String debitPdfRequest(String requestInfo)
	{
		logger.info("Enter debitPdfRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="", referenceNo="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "debitPdfRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"DebitInformation");
			//System.out.println("Authentication::>"+authenticated);
			List listValues = quoteGeneration.getDebitPDFDetails(requestInfo,"DebitInformation");
			referenceNo = listValues.get(0).toString();
			String quoteNo = listValues.get(3).toString();
			if(authenticated){
				
				String count = listValues.get(1).toString();
				String status = listValues.get(2).toString();
				//String referralStatus = listValues.get(3).toString();
				
				if(count.equalsIgnoreCase("0") && (!status.equalsIgnoreCase("0") )){
				//Add code here to generate Draft by passing reference number
				PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
				generate.docGeneration("DEBIT");
				System.out.println("Block Executed");
				}
				 response = quoteGeneration.generatePDFResponse(requestInfo,"debit");
			}
			else{
				System.out.println("Not Autheticated");
				response=" <DebitInformation>" +
						" <QuoteNumber>"+quoteNo+"</QuoteNumber><FileName></FileName>" +
						" <FileContent></FileContent><ErrorStatus></ErrorStatus>" +
						" <Remarks>Not Authenticated</Remarks> </DebitInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, referenceNo, sno);
		System.out.println("Response::>"+response);
		logger.info("Exits debitPdfRequest");
		return response;
		

	}
	public String creditPdfRequest(String requestInfo)
	{
		logger.info("Enter creditPdfRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="", referenceNo="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "creditPdfRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"CreditInformation");
			//System.out.println("Authentication::>"+authenticated);
			List listValues = quoteGeneration.getCreditPDFDetails(requestInfo,"CreditInformation");
			referenceNo = listValues.get(0).toString();
			String quoteNo = listValues.get(3).toString();
			if(authenticated){
				
				String count = listValues.get(1).toString();
				String status = listValues.get(2).toString();
				//String referralStatus = listValues.get(3).toString();
				
				if(count.equalsIgnoreCase("0") && (!status.equalsIgnoreCase("0") )){
				//Add code here to generate Draft by passing reference number
				PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
				generate.docGeneration("CREDIT");
				System.out.println("Block Executed");
				}
				
			    response = quoteGeneration.generatePDFResponse(requestInfo,"credit");
			}
			else{
				System.out.println("Not Autheticated");
				response=" <CreditInformation>" +
						" <QuoteNumber>"+quoteNo+"</QuoteNumber><FileName></FileName>" +
						" <FileContent></FileContent><ErrorStatus></ErrorStatus>" +
						" <Remarks>Not Authenticated</Remarks> </CreditInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, referenceNo, sno);
		System.out.println("Response::>"+response);
		logger.info("Exits creditPdfRequest");
		return response;
		

	}
	public String quoteInfoRequest(String requestInfo) {
		logger.info("Enter quoteInfoRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "quoteInfoRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"QuoteInformation");
			//System.out.println("Authentication::>"+authenticated);
			if(authenticated){
			
			    response = quoteGeneration.generateQuoteInfoResponse(requestInfo);
			}
			else{
				System.out.println("Not Autheticated");
				response="<QuoteInformation><ReferenceNumber></ReferenceNumber>" +
						"<QuoteNumber></QuoteNumber>" +
						"<TotalPremium></TotalPremium>" +
						"<RefferalStatus></RefferalStatus>" +
						"<RefferalRemarks>Not Authenticated</RefferalRemarks>" +
						"</QuoteInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			//System.out.println("Empty Response===>"+requestInfo);
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, "", sno);
		//System.out.println("Response::>"+response);
		logger.info("Exits quoteInfoRequest");
		
		return response;

	}
	public String receiptPdfRequest(String requestInfo) {
		logger.info("Enter receiptPdfRequest");
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		boolean authenticated = false;
		String response = "", sno="", referenceNo="";
		try{
			sno=quoteGeneration.insertDTMonitor(requestInfo, "", "receiptPdfRequest");
			authenticated = quoteGeneration.authentication(requestInfo,"ReceiptInformation");
			List listValues = quoteGeneration.getReceiptPdfDetails(requestInfo,"ReceiptInformation");
			referenceNo = listValues.get(0).toString();
			String quoteNo = listValues.get(2).toString();
			if(authenticated){
				String policyNo = listValues.get(1).toString();
				if(policyNo!=null && !"".equals(policyNo)){
					//WebServicePolicyInfo info = new WebServicePolicyInfo();
					//new PolicyGenerationService().setInfo(info);
					PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					//info.setPolicyNo(policyNo);
					String fileName=generate.receiptGeneration();
					StringBuffer fileContent =new StringBuffer();
					String remarks = "";
					try {
						File file = new File(fileName);
						InputStream in = new FileInputStream(file);
						byte[] bytes = new byte[(int)file.length()];
						in.read(bytes);
						in.close();
						fileContent.append( Base64.encode(bytes));
					} catch (RuntimeException e) {
						remarks = " File Not Generated";
						System.out.println("File not genrated");
						e.printStackTrace();
					}
					fileName =fileName.substring(fileName.lastIndexOf("/")+1);
					response="<ReceiptInformation>" +
							"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
							"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
							"<PolicyNo>"+policyNo+"</PolicyNo>" +
							"<FileName>"+fileName+"</FileName>" +
							"<FileContent>"+fileContent+"</FileContent>" +
							"<ErrorStatus></ErrorStatus>" +
							"<Remarks>"+remarks+"</Remarks>" +
							"</ReceiptInformation>";
				}else if(referenceNo!=null && !"".equals(referenceNo)){
					response=" <ReceiptInformation>" +
					" <QuoteNumber>"+quoteNo+"</QuoteNumber><FileName></FileName>" +
					" <FileContent></FileContent><ErrorStatus></ErrorStatus>" +
					" <Remarks>This Quote not converted as Policy</Remarks> </ReceiptInformation>";
				}else{
					response=" <ReceiptInformation>" +
					" <QuoteNumber>"+quoteNo+"</QuoteNumber><FileName></FileName>" +
					" <FileContent></FileContent><ErrorStatus></ErrorStatus>" +
					" <Remarks>Quote not found</Remarks> </ReceiptInformation>";
				}
			} else{
				response=" <ReceiptInformation>" +
						" <QuoteNumber>"+quoteNo+"</QuoteNumber><FileName></FileName>" +
						" <FileContent></FileContent><ErrorStatus></ErrorStatus>" +
						" <Remarks>Not Authenticated</Remarks> </ReceiptInformation>";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response=="" || response==null){
			response="<ErrorStatus>Given XML input was invalid</ErrorStatus>";
		}
		if(sno!=null && !"".equals(sno))
			quoteGeneration.updateDTMonitor(response, referenceNo, sno);
		System.out.println("Response::>"+response);
		logger.info("Exits receiptPdfRequest");
		return response;
	}
}
