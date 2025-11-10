<%@ include file="login/sessionsCheckNormal.jsp"%>
<%@ page import="java.util.*,java.io.*" %>
<jsp:useBean id= "pdf" class = "rsa.pdf.PDFDisplay">
<jsp:setProperty name= "pdf"   property = "*"/>
</jsp:useBean>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>
<link href="css/style.css" rel="stylesheet" type="text/css">

<%

String paths = request.getContextPath();
String usrModeSCPOLICYT=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeSCPOLICYT;



String productTypeIdCert="";
String openCoverNoSettingCert="";
productTypeIdCert=request.getParameter("productTypeIdCert");

if(productTypeIdCert==null)
	productTypeIdCert=(String) session.getAttribute("product_id")==null?"0":(String) session.getAttribute("product_id");
String userType = (String)session.getAttribute("user1");

if("11".equalsIgnoreCase(productTypeIdCert) && ("".equalsIgnoreCase(openCoverNoSettingCert))&&userType.equalsIgnoreCase("admin"))
{
	openCoverNoSettingCert = request.getParameter("openCoverNoSettingCert")==null?"0":request.getParameter("openCoverNoSettingCert");
}
else if(!userType.equalsIgnoreCase("admin"))
{
	openCoverNoSettingCert=(String)session.getAttribute("openCoverNo")==null?"0":(String)session.getAttribute("openCoverNo");
	
}
	session.setAttribute("openCoverNo",openCoverNoSettingCert);
	if(productTypeIdCert!=null && productTypeIdCert.length()>0){
		session.setAttribute("product_id",productTypeIdCert);
	}

if("0".equalsIgnoreCase(productTypeIdCert) || "".equalsIgnoreCase(productTypeIdCert))
{
	response.sendRedirect("login/error_messg.jsp");
}
if("11".equalsIgnoreCase(productTypeIdCert) && ("0".equalsIgnoreCase(openCoverNoSettingCert) || "".equalsIgnoreCase(openCoverNoSettingCert) ))
{
	
	response.sendRedirect("login/error_messg.jsp");
}


					String policynumber="";
					String loginid="";

					String policynumberStatus="";
					String loginidStatus="";

					String urlStatus="";

					String policyMode="";
					String disPlayText="";
					String disPlayMode="";
					String verNo = "";
					policynumber=request.getParameter("policynumber")==null?policynumber:request.getParameter("policynumber");
					verNo=request.getParameter("verNo")==null?"0":request.getParameter("verNo");
					
					disPlayText=request.getParameter("displayText")==null?disPlayText:request.getParameter("displayText");
					
				
	%>
	<%
	
	com.maan.services.util.dataCollection collect=new com.maan.services.util.dataCollection();
		String pstatus = collect.getPolicyCanceledStatus(policynumber);
		
		if(pstatus.equalsIgnoreCase("D")&&!userType.equalsIgnoreCase("admin"))
		{
	%>

<table width='600' border='1' cellspacing='0' cellpadding='1' align='center' >
		 <tr>
		      <td height="60"  >
				<table class="mdbgyelllow" width='100%' border='0' cellspacing='0' cellpadding='0'>

				<td width="16%"  align='center'> </td>
								<td width="43%"  align='center'> 
                                <b>SORRY ! POLICY ALREADY CANCELLED BY ADMIN. PLEASE CONTACT ADMIN FOR FURTHER DETAILS</b></td>
								<td width="8%"  align='center'> </td>
</tr></table>
</td></tr></table>
	<%
					if(true)return;
		}
		
	%>
	<%
					policynumberStatus=policynumber;
					loginid=request.getParameter("loginid")==null?loginid:request.getParameter("loginid");
					policyMode=request.getParameter("policyMode")==null?policyMode:request.getParameter("policyMode");

					
					if("vatdebit".equalsIgnoreCase(policyMode)){
					  session.putValue("VATINVOICE","VATINVOICE");
					   policyMode="debit";
					}
					
					if("vatcredit".equalsIgnoreCase(policyMode)){
					  	session.putValue("VATINVOICE","VATINVOICE");
					  policyMode="credit";
					}

					if("scheduleMultiple".equalsIgnoreCase(policyMode) || "debitMultiple".equalsIgnoreCase(policyMode) || "creditMultiple".equalsIgnoreCase(policyMode))
					{
					disPlayMode="NormalMultiple";
					}else if("suppleMentMultiple".equalsIgnoreCase(policyMode) || "suppleMentDebitMultiple".equalsIgnoreCase(policyMode) )
					{
					disPlayMode="NormalSupplement";
					}
					else if("schedule".equalsIgnoreCase(policyMode) || "debit".equalsIgnoreCase(policyMode) || "credit".equalsIgnoreCase(policyMode)|| "clauses".equalsIgnoreCase(policyMode))
					{
						if("11".equalsIgnoreCase(productTypeIdCert))
						{
						//disPlayMode="NormalSupplement";
						disPlayMode="Normal";
						}else if("3".equalsIgnoreCase(productTypeIdCert))
						{
						disPlayMode="Normal";
						}
					}

					
					loginidStatus=loginid;
			
					String clickCountStatus="";
					String userTypeStatus="";

					String[][] preBankOptions=new String[0][0];

					Map pdfHash=new HashMap();

					String preOpted="";
					String bankOpted="";
					String bankAssuredOpted="";
					String currencyOpted="";
					String excessOpted="";
					String quoteBasedPolicyNo="";
					userTypeStatus=pdf.getUserType(loginidStatus);
					if("NormalSupplement".equalsIgnoreCase(disPlayMode) &&  "11".equalsIgnoreCase(productTypeIdCert))
					{
						quoteBasedPolicyNo=pdf.getQuoteBasedPolicyNo(policynumberStatus);
						
						pdfHash=pdf.getPDFStatus(quoteBasedPolicyNo,loginidStatus);	

						preBankOptions=pdf.getPreBankOptions(quoteBasedPolicyNo,loginidStatus,"policy");

					
					}else
					{
						pdfHash=pdf.getPDFStatus(policynumberStatus,loginidStatus);	

						preBankOptions=pdf.getPreBankOptions(policynumberStatus,loginidStatus,"policy");
					}
					boolean debitGenSts=false,creditGenSts=false;
					String[][] noteStatus=pdf.getNoteGenerateStatus(policynumberStatus);
					if(noteStatus!=null && noteStatus.length>0){
						debitGenSts=Integer.parseInt(noteStatus[0][0])>0;
						creditGenSts=Integer.parseInt(noteStatus[0][1])>0;
					}
					if(preBankOptions.length > 0)
					{
						preOpted=preBankOptions[0][0]==null?"":preBankOptions[0][0];
						bankOpted=preBankOptions[0][1]==null?"":preBankOptions[0][1];
						bankAssuredOpted=preBankOptions[0][6]==null?"":preBankOptions[0][6];
						currencyOpted=preBankOptions[0][7]==null?"":preBankOptions[0][7];
						excessOpted=preBankOptions[0][8]==null?"":preBankOptions[0][8];

					}else
					{

					}

					try
					{
						clickCountStatus=(String)pdfHash.get("PDFStatus")==null?"0":
						(String)pdfHash.get("PDFStatus");
					}catch(Exception exStatus)
					{
						clickCountStatus="0";
					}

		if("admin".equalsIgnoreCase((String)session.getAttribute("user1")))
		{
			if("ORIGINAL COPY".equalsIgnoreCase(disPlayText))
				clickCountStatus = "1";
			else if("COPY".equalsIgnoreCase(disPlayText))
				clickCountStatus = "2";
			else if("INVALID POLICY".equalsIgnoreCase(disPlayText))
				clickCountStatus = "0";
			else if("INVALID DEBIT".equalsIgnoreCase(disPlayText))
				clickCountStatus = "0";
			else if("DEBIT".equalsIgnoreCase(disPlayText))
				clickCountStatus = "0";
			else
				clickCountStatus = "0";
		}
		
			/*StringTokenizer token = null;
			if(policynumber.indexOf("-")!=-1){
				token = new StringTokenizer(policynumber, "-");
			}else if(policynumber.indexOf("/")!=-1){
				token = new StringTokenizer(policynumber, "/");
			}*/
			String PolicyNoFour = "";
			String pdfFilePath = "";
			
			/*String previousToken = "";
			while (token!=null && token.hasMoreTokens())
			{	
				previousToken = PolicyNoFour;
				PolicyNoFour = token.nextToken();
			}
			System.out.println("Policy Number in Print==>"+PolicyNoFour);
			if(policynumber.indexOf("-")!=-1){
				PolicyNoFour = previousToken + "-" + PolicyNoFour;
			}else if(policynumber.indexOf("/")!=-1){
				PolicyNoFour = previousToken + "/" + PolicyNoFour;
			}*/
			
			PolicyNoFour = policynumber;
			System.out.println("Policy Number in Print==>"+PolicyNoFour);
			//Block Added by Chinna
			System.out.println("polino: >>>>>>> bef"+policynumber);
			if(policynumber!=null && policynumber.length()>0 && policynumber.indexOf("/")!=-1)
			{
				PolicyNoFour=policynumber.replaceAll("/", "_");
				System.out.println("polino: >>>>>>>"+PolicyNoFour);
			}
		System.out.println("$$$$$$$$$$$ ClickCountStatus: "+clickCountStatus);
	
	if("0".equalsIgnoreCase(clickCountStatus))
	{
		if("debit".equalsIgnoreCase(policyMode) || "debitMultiple".equalsIgnoreCase(policyMode) || "suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
		{
			if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
			{
				disPlayText="INVALID DEBIT";

			}
			System.out.println("Policy Number in Print==>"+PolicyNoFour);
			if("11".equalsIgnoreCase(productTypeIdCert))
			{
				if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
					pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
				else
					pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
			}
			else if("3".equalsIgnoreCase(productTypeIdCert))
				pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"Debit.pdf");
			File checkPolicyFile = new File(pdfFilePath);
			
			System.out.println("*************Exists 1: "+pdfFilePath);
			if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && debitGenSts))
			{
				
				if("11".equalsIgnoreCase(productTypeIdCert))
				{
						response.sendRedirect("print4DebitOpen.OpenDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
				}else if("3".equalsIgnoreCase(productTypeIdCert)) 
				{
						response.sendRedirect("print4Debit.pdfDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
				}else
				{
					response.sendRedirect("login/error_messg_pdf.jsp");	
					return;
				}
			}
			else
			{
				if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
						response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
					else
						response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"Debit.pdf");
			}
		}
		else if("schedule".equalsIgnoreCase(policyMode) || "scheduleMultiple".equalsIgnoreCase(policyMode) || "suppleMentMultiple".equalsIgnoreCase(policyMode))
		{
			if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
			{
				disPlayText="INVALID POLICY";
			}else if("Live".equalsIgnoreCase(usrModeSCPOLICYT))
			{
				disPlayText="";
			}
			System.out.println("Policy Number in Print==>"+PolicyNoFour);
			if("INVALID POLICY".equalsIgnoreCase(disPlayText))
			{
				if("11".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
			}
			else if("NormalSupplement".equalsIgnoreCase(disPlayMode) && "11".equalsIgnoreCase(productTypeIdCert)&&!"suppleMentMultiple".equalsIgnoreCase(policyMode))
				pdfFilePath = request.getRealPath("/LiveOriginalMultiplePdf/"+PolicyNoFour+"ScheduleOpen.pdf");
			else if("11".equalsIgnoreCase(productTypeIdCert))
			{
				if("suppleMentMultiple".equalsIgnoreCase(policyMode))
				{
					pdfFilePath = request.getRealPath("/OriginalPdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
					
				}
				else
					pdfFilePath = request.getRealPath("/OriginalPdf/"+PolicyNoFour+"ScheduleOpen.pdf");
			}
			else if("3".equalsIgnoreCase(productTypeIdCert))
				pdfFilePath = request.getRealPath("/OriginalPdf/"+PolicyNoFour+"Schedule.pdf");
			File checkPolicyFile = new File(pdfFilePath);
			System.out.println("*************Exists 2: "+pdfFilePath);
			if(!checkPolicyFile.exists())
			{
				if("11".equalsIgnoreCase(productTypeIdCert))
				{
						response.sendRedirect("print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
				}else if("3".equalsIgnoreCase(productTypeIdCert))
				{
						response.sendRedirect("print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
				}
				else
				{
					response.sendRedirect("login/error_messg_pdf.jsp");	
					return;
				}
				
			}
			else
			{
				if("INVALID POLICY".equalsIgnoreCase(disPlayText))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
				}
				else if("NormalSupplement".equalsIgnoreCase(disPlayMode) && "11".equalsIgnoreCase(productTypeIdCert)&&!"suppleMentMultiple".equalsIgnoreCase(policyMode))
					response.sendRedirect(paths+"/LiveOriginalMultiplePdf/"+PolicyNoFour+"ScheduleOpen.pdf");				
				else if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentMultiple".equalsIgnoreCase(policyMode))
						response.sendRedirect(paths+"/OriginalPdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
					else
						response.sendRedirect(paths+"/OriginalPdf/"+PolicyNoFour+"ScheduleOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					response.sendRedirect(paths+"/OriginalPdf/"+PolicyNoFour+"Schedule.pdf");
			}
				clickCountStatus="1";
				if("NormalSupplement".equalsIgnoreCase(disPlayMode) && "11".equalsIgnoreCase(productTypeIdCert))
				{
				//pdf.getUpdatePDFStatus(quoteBasedPolicyNo,loginidStatus,clickCountStatus);
				}else
				{
					pdf.getUpdatePDFStatus(policynumberStatus,loginidStatus,clickCountStatus);
				}
				clickCountStatus="";
		}else if("credit".equalsIgnoreCase(policyMode)|| "creditMultiple".equalsIgnoreCase(policyMode))
        {
            if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
            {
                disPlayText="TEST CREDIT";

            }
            System.out.println("Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                else
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
                pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"Credit.pdf");
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && creditGenSts))
            {
                
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        response.sendRedirect("print4CreditOpen.OpenCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Credit.pdfCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else
                {
                    response.sendRedirect("login/error_messg_pdf.jsp");    
                    return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                    if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                    else
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                    response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"Credit.pdf");
            }
        }else if("clauses".equalsIgnoreCase(policyMode))
        {
            System.out.println("clauses Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists())
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        //response.sendRedirect("print4Clauses.OpenClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else
                {
                        response.sendRedirect("login/error_messg_pdf.jsp");    
                        return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
        }else
		{
		}
			
	}
	else if("1".equalsIgnoreCase(clickCountStatus))
	{
			System.out.println("Policy Number in Print==>"+PolicyNoFour+" clickCountStatus: "+clickCountStatus);
			
		if("admin".equalsIgnoreCase((String)session.getAttribute("user1")))
		{
			if("debit".equalsIgnoreCase(policyMode) || "debitMultiple".equalsIgnoreCase(policyMode) || "suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
			{
				
				System.out.println("Policy Number in Print==>"+PolicyNoFour);
				
				if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
					else
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"Debit.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 3: "+pdfFilePath);
				if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && debitGenSts))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{	
						response.sendRedirect("print4DebitOpen.OpenDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");

					}else if("3".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4Debit.pdfDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");

					}else
					{
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
				}
				else
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
						else
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"Debit.pdf");
				}
			}
			else if("schedule".equalsIgnoreCase(policyMode) || "scheduleMultiple".equalsIgnoreCase(policyMode) || "suppleMentMultiple".equalsIgnoreCase(policyMode))
			{
				if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
				{
					disPlayText="INVALID POLICY";
				}
				System.out.println("Policy Number in Print==>"+PolicyNoFour);
				
				if("INVALID POLICY".equalsIgnoreCase(disPlayText))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					else if("3".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
				}
				else if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/OriginalCopyPdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
					else
						pdfFilePath = request.getRealPath("/OriginalCopyPdf/"+PolicyNoFour+"ScheduleOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/OriginalCopyPdf/"+PolicyNoFour+"Schedule.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 4: "+pdfFilePath);
				if(!checkPolicyFile.exists())
				{	
					
					if("11".equalsIgnoreCase(productTypeIdCert))
					{	
						response.sendRedirect("print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");

					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
					{
								response.sendRedirect("print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");

					}else
					{	
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
				}
				else
				{
					if("INVALID POLICY".equalsIgnoreCase(disPlayText))
					{
						if("11".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
						else if("3".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
					}
					else if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/OriginalCopyPdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
						else
							response.sendRedirect(paths+"/OriginalCopyPdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/OriginalCopyPdf/"+PolicyNoFour+"Schedule.pdf");
				}

			}else if("credit".equalsIgnoreCase(policyMode)|| "creditMultiple".equalsIgnoreCase(policyMode))
        {
            if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
            {
                disPlayText="TEST CREDIT";

            }
            System.out.println("Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                else
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
                pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"Credit.pdf");
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && creditGenSts))
            {
                
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        response.sendRedirect("print4CreditOpen.OpenCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Credit.pdfCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else
                {
                    response.sendRedirect("login/error_messg_pdf.jsp");    
                    return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                    if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                    else
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                    response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"Credit.pdf");
            }
        }
			else
			{
			}
		}
		else
		{
						
			if("debit".equalsIgnoreCase(policyMode) || "debitMultiple".equalsIgnoreCase(policyMode) || "suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
			{

				if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
				{
					disPlayText="INVALID DEBIT";
				}else if("Live".equalsIgnoreCase(usrModeSCPOLICYT))
				{
					disPlayText="";
				}
				System.out.println("Policy Number in Print==>"+PolicyNoFour);
				
				if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
					else
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"Debit.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 5: "+pdfFilePath);
				if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && debitGenSts))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4DebitOpen.OpenDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else if("3".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4Debit.pdfDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else
					{
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
				}
				else
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
						else
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"Debit.pdf");
				}
			}
			else if("schedule".equalsIgnoreCase(policyMode) || "scheduleMultiple".equalsIgnoreCase(policyMode) || "suppleMentMultiple".equalsIgnoreCase(policyMode))
			{
				if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
				{
					disPlayText="INVALID POLICY";
				}else if("Live".equalsIgnoreCase(usrModeSCPOLICYT))
				{
					disPlayText="ORIGINAL COPY";
				}
				System.out.println("Policy Number in Print==>"+PolicyNoFour);
				
				if("INVALID POLICY".equalsIgnoreCase(disPlayText))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					else if("3".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
				}
				else if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/OriginalCopyPdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
					else
						pdfFilePath = request.getRealPath("/OriginalCopyPdf/"+PolicyNoFour+"ScheduleOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/OriginalCopyPdf/"+PolicyNoFour+"Schedule.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 6: "+pdfFilePath);
				if(!checkPolicyFile.exists())
				{

					if("11".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else if("3".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else
					{
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
					
				}
				else
				{
					if("INVALID POLICY".equalsIgnoreCase(disPlayText))
					{
						if("11".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
						else if("3".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
					}
					else if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/OriginalCopyPdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
						else
							response.sendRedirect(paths+"/OriginalCopyPdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/OriginalCopyPdf/"+PolicyNoFour+"Schedule.pdf");
				}
				clickCountStatus="2";
				if("NormalSupplement".equalsIgnoreCase(disPlayMode) && "11".equalsIgnoreCase(productTypeIdCert))
				{
				}else
				{
					pdf.getUpdatePDFStatus(policynumberStatus,loginidStatus,clickCountStatus);
				}
				clickCountStatus="";
			}else if("credit".equalsIgnoreCase(policyMode)|| "creditMultiple".equalsIgnoreCase(policyMode))
        {
            if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
            {
                disPlayText="TEST CREDIT";

            }
            System.out.println("Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                else
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
                pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"Credit.pdf");
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && creditGenSts))
            {
                
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        response.sendRedirect("print4CreditOpen.OpenCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Credit.pdfCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else
                {
                    response.sendRedirect("login/error_messg_pdf.jsp");    
                    return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                    if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                    else
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                    response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"Credit.pdf");
            }
        }else if("clauses".equalsIgnoreCase(policyMode))
        {
            System.out.println("clauses Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists())
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        //response.sendRedirect("print4Clauses.OpenClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else
                {
                        response.sendRedirect("login/error_messg_pdf.jsp");    
                        return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
        }
			else
			{
			}
		}
	}
	else //("2".equalsIgnoreCase(clickCountStatus))
	{
		if("admin".equalsIgnoreCase((String)session.getAttribute("user1")))
		{
						
			if("debit".equalsIgnoreCase(policyMode) || "debitMultiple".equalsIgnoreCase(policyMode) || "suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
			{
				System.out.println("Policy Number in Print==>"+PolicyNoFour);
				
				if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
					else
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"Debit.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 7: "+pdfFilePath);
				if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && debitGenSts))
				{
				
					if("11".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4DebitOpen.OpenDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4Debit.pdfDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else
					{
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
				}
				else
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
						else
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"Debit.pdf");
				}
			}
			else if("schedule".equalsIgnoreCase(policyMode) || "scheduleMultiple".equalsIgnoreCase(policyMode) || "suppleMentMultiple".equalsIgnoreCase(policyMode))
			{
				if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
				{
					disPlayText="INVALID POLICY";
				}

				System.out.println("Policy Number in Print==>"+PolicyNoFour);
				
				if("INVALID POLICY".equalsIgnoreCase(disPlayText))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					else if("3".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
				}
				else if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/duplicatecopypdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
					else
						pdfFilePath = request.getRealPath("/duplicatecopypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/duplicatecopypdf/"+PolicyNoFour+"Schedule.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 8: "+pdfFilePath);
				if(!checkPolicyFile.exists())
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{			response.sendRedirect("print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else
					{
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
				}
				else
				{
					if("INVALID POLICY".equalsIgnoreCase(disPlayText))
					{
						if("11".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
						else if("3".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
					}
					else if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/duplicatecopypdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
						else
							response.sendRedirect(paths+"/duplicatecopypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/duplicatecopypdf/"+PolicyNoFour+"Schedule.pdf");
				}
			}else if("credit".equalsIgnoreCase(policyMode)|| "creditMultiple".equalsIgnoreCase(policyMode))
        {
            if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
            {
                disPlayText="TEST CREDIT";

            }
            System.out.println("Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                else
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
                pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"Credit.pdf");
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && creditGenSts))
            {
                
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        response.sendRedirect("print4CreditOpen.OpenCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Credit.pdfCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else
                {
                    response.sendRedirect("login/error_messg_pdf.jsp");    
                    return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                    if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                    else
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                    response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"Credit.pdf");
            }
        }else if("clauses".equalsIgnoreCase(policyMode))
        {
            System.out.println("clauses Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists())
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        //response.sendRedirect("print4Clauses.OpenClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else
                {
                        response.sendRedirect("login/error_messg_pdf.jsp");    
                        return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
        }
			else
			{
			}
		}
		else
		{
					
			if("debit".equalsIgnoreCase(policyMode) || "debitMultiple".equalsIgnoreCase(policyMode) || "suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
			{
				if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
				{
				disPlayText="INVALID DEBIT";
				}else if("Live".equalsIgnoreCase(usrModeSCPOLICYT))
				{	
					disPlayText="";
				}
				System.out.println("Policy Number in Print==>"+PolicyNoFour);
			
				if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
					else
						pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/debitpdf/"+PolicyNoFour+"Debit.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 9: "+pdfFilePath);
				if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && debitGenSts))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{		response.sendRedirect("print4DebitOpen.OpenDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else if("3".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4Debit.pdfDebit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else
					{
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
				}
				else
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentDebitMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"QuoteDebitOpen.pdf");
						else
							response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"DebitOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/debitpdf/"+PolicyNoFour+"Debit.pdf");
				}
			}
			else if("schedule".equalsIgnoreCase(policyMode) || "scheduleMultiple".equalsIgnoreCase(policyMode) || "suppleMentMultiple".equalsIgnoreCase(policyMode))
			{

				if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
				{

					disPlayText="INVALID POLICY";

				}else if("Live".equalsIgnoreCase(usrModeSCPOLICYT))
				{

					disPlayText="COPY";

				}
				System.out.println("Policy Number in Print==>"+PolicyNoFour);
				
				if("INVALID POLICY".equalsIgnoreCase(disPlayText))
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					else if("3".equalsIgnoreCase(productTypeIdCert))
						pdfFilePath = request.getRealPath("/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
				}
				else if("11".equalsIgnoreCase(productTypeIdCert))
				{
					if("suppleMentMultiple".equalsIgnoreCase(policyMode))
						pdfFilePath = request.getRealPath("/duplicatecopypdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
					else 
						pdfFilePath = request.getRealPath("/duplicatecopypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
				}
				else if("3".equalsIgnoreCase(productTypeIdCert))
					pdfFilePath = request.getRealPath("/duplicatecopypdf/"+PolicyNoFour+"Schedule.pdf");
				File checkPolicyFile = new File(pdfFilePath);
				System.out.println("*************Exists 10: "+pdfFilePath);
				if(!checkPolicyFile.exists())
				{
					if("11".equalsIgnoreCase(productTypeIdCert))
					{									response.sendRedirect("print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else if("3".equalsIgnoreCase(productTypeIdCert))
					{
						response.sendRedirect("print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
					}else
					{
						response.sendRedirect("login/error_messg_pdf.jsp");	
						return;
					}
				}
				else
				{
					if("INVALID POLICY".equalsIgnoreCase(disPlayText))
					{
						if("11".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
						else if("3".equalsIgnoreCase(productTypeIdCert))
							response.sendRedirect(paths+"/testpolicypdf/"+PolicyNoFour+"Schedule.pdf");
					}
					else if("11".equalsIgnoreCase(productTypeIdCert))
					{
						if("suppleMentMultiple".equalsIgnoreCase(policyMode))
							response.sendRedirect(paths+"/duplicatecopypdf/"+PolicyNoFour+"QuoteScheduleOpen.pdf");
						else
							response.sendRedirect(paths+"/duplicatecopypdf/"+PolicyNoFour+"ScheduleOpen.pdf");
					}
					else if("3".equalsIgnoreCase(productTypeIdCert))
						response.sendRedirect(paths+"/duplicatecopypdf/"+PolicyNoFour+"Schedule.pdf");
				}

			}else if("credit".equalsIgnoreCase(policyMode)|| "creditMultiple".equalsIgnoreCase(policyMode))
        {
            if("Test".equalsIgnoreCase(usrModeSCPOLICYT))
            {
                disPlayText="TEST CREDIT";

            }
            System.out.println("Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                else
                    pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
                pdfFilePath = request.getRealPath("/creditpdf/"+PolicyNoFour+"Credit.pdf");
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists() || (checkPolicyFile.exists() && creditGenSts))
            {
                
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        response.sendRedirect("print4CreditOpen.OpenCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&verNo="+verNo+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Credit.pdfCredit?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"&displayMode="+disPlayMode+"&printoption="+preOpted+"&bankerOption="+bankOpted+"&bankerAssuredOption="+bankAssuredOpted+"&currencyOption="+currencyOpted+"&excessOption="+excessOpted+"");
                }else
                {
                    response.sendRedirect("login/error_messg_pdf.jsp");    
                    return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                    if("suppleMentCreditMultiple".equalsIgnoreCase(policyMode))
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"QuoteCreditOpen.pdf");
                    else
                        response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"CreditOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                    response.sendRedirect(paths+"/creditpdf/"+PolicyNoFour+"Credit.pdf");
            }
        }else if("clauses".equalsIgnoreCase(policyMode))
        {
            System.out.println("clauses Policy Number in Print==>"+PolicyNoFour);
            if("11".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
            }
            else if("3".equalsIgnoreCase(productTypeIdCert))
            {
                pdfFilePath = request.getRealPath("/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
            File checkPolicyFile = new File(pdfFilePath);
            
            System.out.println("*************Exists 1: "+pdfFilePath);
            if(!checkPolicyFile.exists())
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                        //response.sendRedirect("print4Clauses.OpenClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else if("3".equalsIgnoreCase(productTypeIdCert)) 
                {
                        response.sendRedirect("print4Clauses.pdfClauses?policynumber="+policynumber+"&loginid="+loginid+"&displayText="+disPlayText+"");
                }else
                {
                        response.sendRedirect("login/error_messg_pdf.jsp");    
                        return;
                }
            }
            else
            {
                if("11".equalsIgnoreCase(productTypeIdCert))
                {
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"ClausesOpen.pdf");
                }
                else if("3".equalsIgnoreCase(productTypeIdCert))
                       response.sendRedirect(paths+"/clausespdf/"+PolicyNoFour+"Clauses.pdf");
            }
        }
        else
			{
			}
		}
}
%>