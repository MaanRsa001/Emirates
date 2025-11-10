<%@ include file="login/sessionsCheckNormal.jsp"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="pdf" class="rsa.pdf.PDFDisplay">
<jsp:setProperty name="pdf" property="*" />
</jsp:useBean>

<jsp:useBean id="pol" class="com.maan.services.policyInfo">
<jsp:setProperty name="pol" property="*" />
</jsp:useBean>

<jsp:useBean id="bean" class="rsa.opencoverpdf.finalprint">
<jsp:setProperty name="bean" property="*" />
</jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>
<link href="css/style.css" rel="stylesheet" type="text/css">

<%
	String path3 = request.getContextPath();
	String usrModeSCPOLICYTT = (String) session.getAttribute("userLoginMode") == null ? "": (String) session.getAttribute("userLoginMode");
	com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeSCPOLICYTT;

	boolean senFlag = false;
	// Marine Download File
	String downloadInfo[][] = new String[0][0];
	String downloadRemarks = "";
	String custFileName = "";
	String fileType = "";
	// Marine Download File

	String policynumber = "";
	String openCoverNoSettingCert = "";
	String loginid = "";
	String policyMode = "";
	String disPlayText = "";
	String displayMode = "";
	String pdfStatus = "DATAS";
	String productTypeIds = "0";
	String[][] preBankOptions = new String[0][0];
	String[][] noteNo = new String[0][0];
	String preOpted = "";
	String bankOpted = "";
	String bankAssuredOpted = "";
	String currencyOpted = "", creditNote="", debitNote="";

	String verNo = "";

	policynumber = request.getParameter("policynumber") == null ? policynumber: request.getParameter("policynumber");
	noteNo=pol.getNoteNo(policynumber);
	if(noteNo!=null && noteNo.length>0){
		debitNote=noteNo[0][0];
		creditNote=noteNo[0][1];
	}
	verNo = request.getParameter("verNo") == null ? "0" : request.getParameter("verNo");
	openCoverNoSettingCert = request.getParameter("openCoverNoSettingCert") == null ? openCoverNoSettingCert: request.getParameter("openCoverNoSettingCert");

	productTypeIds = request.getParameter("productTypeIds") == null ? productTypeIds: request.getParameter("productTypeIds");
	pdfStatus = request.getParameter("pdfStatus") == null ? pdfStatus: request.getParameter("pdfStatus");

	disPlayText = request.getParameter("disPlayText") == null ? disPlayText: request.getParameter("disPlayText");

	loginid = request.getParameter("loginid") == null ? loginid: request.getParameter("loginid");

	if ("Y".equalsIgnoreCase(request.getParameter("authorized")))
		pdf.getUpdatePDFStatus(policynumber, loginid, "0");
	policyMode = request.getParameter("policyMode") == null ? policyMode: request.getParameter("policyMode");

	if ("scheduleMultiple".equalsIgnoreCase(policyMode)	|| "debitMultiple".equalsIgnoreCase(policyMode)) {
		displayMode = "NormalMultiple";
		//Checked Okay...
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid, "policy");

	}
	else if ("suppleMentMultiple".equalsIgnoreCase(policyMode)|| "suppleMentDebitMultiple".equalsIgnoreCase(policyMode)) {
		displayMode = "NormalSupplement";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"MultiQuotes");
	}
	else if ("schedule".equalsIgnoreCase(policyMode)) {
		displayMode = "Normal";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"policy");
	}
	else if ("draft".equalsIgnoreCase(policyMode)) {
		displayMode = "draftMode";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"draft");
	}
	else if ("certificate".equalsIgnoreCase(policyMode)) {
		displayMode = "certificateMode";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"certificate");
	}
	else if ("draftModeMultiple".equalsIgnoreCase(policyMode)) {
		displayMode = "draftModeMultiple";
		preBankOptions = pdf.getPreBankOptions(policynumber, loginid,"MultiQuotesDisplay");
	}

	if(preBankOptions.length > 0) 
	{
		preOpted = preBankOptions[0][0] == null ? "": preBankOptions[0][0];
		bankOpted = preBankOptions[0][1] == null ? "": preBankOptions[0][1];
		bankAssuredOpted = preBankOptions[0][6] == null ? "": preBankOptions[0][6];
		currencyOpted = preBankOptions[0][7] == null ? "": preBankOptions[0][7];
	}
	else 
	{
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>
<link href="css/style.css" rel="stylesheet" type="text/css">
<form name="form1" method="post">
<body>
	<table width='650' border='1' cellspacing='0' cellpadding='1' align='center'>
	<tr>
	<td height="60">
	<table class="mdbgyelllow" width='100%' border='0' cellspacing='0' cellpadding='0'>
<%
		if ("Y".equalsIgnoreCase(request.getParameter("authorized"))) 
		{
%>
	<tr class="royamenuhead">
	<td height="38" colspan="4" class="bottomtext" align="center">
	Now Broker can take One more Original and Original copy</td>
	</tr>
<%
	}
	else
	{
%>
	<tr class="royamenuhead">
	<td height="38" colspan="8" class="bottomtext" align="center">
	<b>PRINTING OPTIONS</b>
	</td>
	</tr>
	<tr>
		<%--<td width="16%">
		<input type="radio" name="displayPrintOption" id='idORIGINAL'  value="ORIGINAL" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','')">
		ORIGINAL </td>
		<td width="16%">
		<input type="radio" name="displayPrintOption" id='idORIGINALCOPY'  value="ORIGINAL COPY" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','ORIGINAL COPY','<%=verNo%>')">
		ORIGINAL&nbsp;COPY</td>
		<td width="16%">
		<input type="radio" name="displayPrintOption" id='idCOPY'  value="COPY" checked onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','COPY','<%=verNo%>')">
		COPY</td>--%>
				<%
			 		if (("draftMode".equalsIgnoreCase(displayMode) || "draftModeMultiple".equalsIgnoreCase(displayMode)) && "DATAS".equalsIgnoreCase(pdfStatus)) {
				%>
               				<td width="16%" align='center'></td>
				<%
							if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT))
							{
				%>
							<td width="16%" align='center'>
							<b>
								<a href="#"	onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID DRAFT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
									VIEW DRAFT </a></b>
							</td>
				<%			} 
							else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) 
							{	
				%>
						<td width="16%" align='center'>
						<b>
						<a href="#"	onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DRAFT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">	VIEW DRAFT </a></b>
						</td>
				<% } %>
						<td width="16%" align='center'></td>
				<%
				  }
				else if("certificateMode".equalsIgnoreCase(displayMode))
				{
					if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT))
					{
				%>
					<td width="16%" align='center'>	<b>
					<a href="#"	onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID CERTIFICATE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
					VIEW&nbsp;CERTIFICATE </a></b>	</td>
				<%	
					}
					else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) 
					{	
				%>
					<td width="16%" align='center'>	<b>
					<a href="#"	onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','CERTIFICATE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
					VIEW&nbsp;CERTIFICATE </a></b></td>
				<%
					}
				}
				else if (("Normal".equalsIgnoreCase(displayMode) || "NormalMultiple".equalsIgnoreCase(displayMode) || "NormalSupplement".equalsIgnoreCase(displayMode))	&& "DATAS".equalsIgnoreCase(pdfStatus)) {
				%>

				<%--<td width="16%">
                                <b><a href="#" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DEBIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=verNo%>')">
                                DEBIT NOTE </a></b></td>--%>
				<%	if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) {	%>
					<%if(debitNote!=null && debitNote.length()>0){ %>
					<td width="16%">
						<b><a href="#" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID DEBIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">DEBIT NOTE </a>&nbsp;&nbsp;&nbsp;</b>
					</td>
					<%}if(creditNote!=null && creditNote.length()>0){ %>
					<td width="16%">
						<b><a href="#" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID CREDIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">CREDIT NOTE </a>&nbsp;&nbsp;&nbsp;</b>
					</td>
					<%} %>
				<%
				} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>
				<%if(debitNote!=null && debitNote.length()>0){ %>
				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DEBIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
				DEBIT NOTE </a></b>&nbsp;&nbsp;&nbsp;</td>
				<%}if(creditNote!=null && creditNote.length()>0){ %>
				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','CREDIT','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
				CREDIT NOTE </a></b>&nbsp;&nbsp;&nbsp;</td>
				<%
				}}
				%>


				<%--<td width="16%">
                                <b><a href="#" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=verNo%>')">
                                ORIGINAL </a></b></td>--%>
				<%
				if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>
				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID POLICY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
				ORIGINAL </a></b>&nbsp;&nbsp;&nbsp;</td>
				<%
				} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>

				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
				ORIGINAL </a></b>&nbsp;&nbsp;&nbsp;</td>
				<%
				}
				%>
				<%--<td width="16%">
                                <b><a href="#" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','ORIGINAL COPY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=verNo%>')">
                                ORIGINAL COPY</a></b></td>--%>
				<%
				if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>
				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID POLICY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">ORIGINAL&nbsp;COPY</a></b>&nbsp;&nbsp;&nbsp;</td>
				<%
				} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>

				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','ORIGINAL COPY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">ORIGINAL&nbsp;COPY</a></b>&nbsp;&nbsp;&nbsp;</td>
				<%
				}
				%>
				
				<%--
				<td width="16%">
                <b><a href="#" onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','COPY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=verNo%>')">COPY</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				--%>
				<%
				if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>
				<td width="16%" align="center"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','INVALID POLICY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
				COPY</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<%
				} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>
				<td width="16%" align="center"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','COPY','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">				COPY</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<%
				}
				%>
				<%  String[][] res = bean.getCustomerDebitDetails(policynumber,"");
				    if(res != null && res.length > 0 && senFlag){	%>
						<td>
						<a href ="<%=request.getContextPath()%>/PDFCreator.customerDebit?requestFrom=FromAdmin&policyNumber=<%=policynumber%>" >Customer&nbsp;Debit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<%  }	%>
				<%
				if ("Test".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>
				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DELETE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
				DELETE</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<%
				} else if ("Live".equalsIgnoreCase(usrModeSCPOLICYTT)) {
				%>
				<td width="16%"><b><a href="#"
					onClick="return viewPdfCopy('<%=policynumber%>','<%=loginid%>','DELETE','<%=preOpted%>','<%=bankOpted%>','<%=displayMode%>','<%=bankAssuredOpted%>','<%=currencyOpted%>','<%=productTypeIds%>','<%=openCoverNoSettingCert%>','<%=verNo%>')">
				DELETE</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<%
				}
				%>

				<td width="16%"><a href="#" onClick="return viewPolicyss()"><b>Authorization</b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				
				<td width="16%"><a href="#" onClick="return viewDocuments('<%=policynumber%>')"><b>DOCUMENTS</b></a></td>

				<%
				} else if ("NODATAS".equalsIgnoreCase(pdfStatus)) {
				%>
				<td width="16%" align='center'></td>
				<td width="43%" align='center'><b>SORRY ! SOME INFORMATIONS	ARE MISSING</b></td>
				<td width="8%" align='center'></td>

				<%
				}
				%>
			</tr>
		</table>
		</td>
	</tr>
	<%
	}
	%>

	<tr>
		<td align='center' colspan='4'>&nbsp;</td>
	</tr>
	<!-- Marine Download File -->
	<table border="0" cellpadding="0" cellspacing="1" style="border-collapse:collapse" width="650" align="center">
		<tr>
			<td colspan="4">&nbsp; </b></td>
		</tr>
		<%
			String branch = "";
			branch = (String) session.getAttribute("LoginBranchCode"); 
			branch = branch == null ? "" : branch;
			//downloadInfo = pol.getInfoForFileDownload(policynumber,productTypeIds,branch);
			if (downloadInfo.length > 0) 
			{
		%>
		<tr class="blueborder">
			<td class="heading" colspan="5">Download Information </b></td>
		</tr>
		<tr class="blueborder">
			<td class="txtf" align="center" width='5%'><b>S.No.</br></td>
			<td align="center" width='20%'> <b>File Type</b> </td>
			<td class="txtf" align="center" width='30%'><b>Customer File Name</b></td>
			<td class="txtf" align="center" width='30%'><b>Description</b></td>
			<td class="txtf" align="center" width='10%'><b>Download</b></td>
		</tr>
		<%
					for (int i = 0; i < downloadInfo.length; i++) 
					{
						custFileName = downloadInfo[i][1] == null ? "": downloadInfo[i][1];
						downloadRemarks = downloadInfo[i][3] == null ? "": downloadInfo[i][3];
						fileType = downloadInfo[i][5]==null ? "" :downloadInfo[i][5];
		%>
		<tr>
			<td align="center" width='5%'><strong><%=(i + 1)%></strong></td>
			<td align="center" width='20%'> <%=fileType%></td>
			<td align="center" width='30%'><%=custFileName%></td>
			<td align="center" width='30%'><textarea name="downloadRemarks"
				rows="2" cols="40" readonly><%=downloadRemarks%></textarea></td>
			</td>
			<td align="center" width='10%'><a href="#"> <img border='0'
				src="images/icon_view_schedule.gif" title="File Download" width="12"
				height="17"
				onclick="return download('<%=downloadInfo[i][1]%>','<%=downloadInfo[i][2]%>')">
			</a></td>
		<tr>
		<%
				} // For Loop
			} // If DownLoad Information
		%>
	</table>
	<!-- Marine Download File -->
	<tr>
	<td align='center' colspan='4'>&nbsp;</td>
	</tr>
	<tr>
	<td align='center' colspan='4'>
	<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	<td>
	<a href="javaScript:onclick=window.close()" class="buttonsMenu"> 
	<img src="<%=path3%>/images/Cancel.jpg" /> </a></td>
	</table>
	</td>
	</tr>
</table>

</body>

<input type="hidden" name="policynumber" value="<%=policynumber%>">
<input type="hidden" name="loginid" value="<%=loginid%>">
<input type="hidden" name="displayText"> 
<input type="hidden" name="displayPrintOption"> 
<input type="hidden" name="displayMode"> 
<input type="hidden" name="bankerOption">
<input type="hidden" name="bankerAssuredOption"> 
<input type="hidden" name="currencyOption"> 
<input type="hidden" name="printoption"> 
<input type="hidden" name="authorized">
<input type="hidden" name="custFileName">
<input type="hidden" name="systemFileName">
<input type="hidden" name="policyMode" value="<%=policyMode%>">
<input type="hidden" name="policyNo" id="policyNo">

</form>
</html>

<script>
function viewPdfCopy(policynumber,loginid1,displayTextVal,preOptedVal,bankOptedVal,displayModeVal,bankAssuredOptedVal,currencyOptedVal,productCheckId,openNo,verNo)
{
			document.form1.policynumber.value=policynumber;
			document.form1.loginid.value=loginid1;
			document.form1.displayText.value=displayTextVal;
			document.form1.displayMode.value=displayModeVal;
			document.form1.bankerOption.value=bankOptedVal;
			document.form1.printoption.value=preOptedVal;
			document.form1.bankerAssuredOption.value=bankAssuredOptedVal;
			var policyModee = displayTextVal;
			//alert(displayModeVal);
			if(displayModeVal=='NormalSupplement'&&policyModee=='DEBIT')
				policyModee = "suppleMentDebitMultiple";
			else if(displayModeVal=='NormalMultiple'&&policyModee=='DEBIT')
				policyModee = "debitMultiple";

			document.form1.currencyOption.value=currencyOptedVal;

			if(displayTextVal == 'DRAFT'||displayTextVal == 'INVALID DRAFT')
			{
				if(productCheckId=='11')
				{
					document.form1.action="print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			else if(displayTextVal == 'CERTIFICATE'||displayTextVal == 'INVALID CERTIFICATE')
			{
				if(productCheckId=='11')
				{
					document.form1.action="print4ScheduleOpen.OpenCertificate?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="print4Schedule.pdfSchedule?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			else if(displayTextVal=='DEBIT' || displayTextVal=='INVALID DEBIT')
			{
				if(displayTextVal=='INVALID DEBIT')
				{
					displayTextVal="INVALID DEBIT";
					if(displayModeVal=='NormalSupplement')
						policyModee = "suppleMentDebitMultiple";
					else if(displayModeVal=='NormalMultiple')
						policyModee = "debitMultiple";
					else
						policyModee = "debit";
				}
				else if(displayTextVal=='DEBIT')
				{
					displayTextVal="";
				}

				if(productCheckId=='11')
				{
					document.form1.action="Copy of information.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&verNo="+verNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="Copy of information.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			else if(displayTextVal == 'CREDIT'||displayTextVal == 'INVALID CREDIT')
			{
				if(displayTextVal=='INVALID CREDIT')
				{
					displayTextVal="INVALID CREDIT";
					if(displayModeVal=='NormalSupplement')
						policyModee = "suppleMentCreditMultiple";
					else if(displayModeVal=='NormalMultiple')
						policyModee = "creditMultiple";
					else
						policyModee = "credit";
				}
				else if(displayTextVal=='CREDIT')
				{
					displayTextVal="";
				}

				if(productCheckId=='11')
				{
					document.form1.action="Copy of information.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&verNo="+verNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="Copy of information.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&policyMode="+policyModee+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			else
			{
				if(productCheckId=='11')
				{
					document.form1.action="Copy of information.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&verNo="+verNo+"&currencyOption="+currencyOptedVal+"";
				}
				else if(productCheckId=='3')
				{
					document.form1.action="Copy of information.jsp?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&productTypeIdCert="+productCheckId+"&openCoverNoSettingCert="+openNo+"&currencyOption="+currencyOptedVal+"";
				}
			}
			if(displayTextVal=='DELETE')
			{
				displayTextVal="DELETE";
				document.form1.action="Delete4Debit.DeleteDebit?policynumber="+policynumber+"&loginid="+loginid1+"&displayText="+displayTextVal+"&displayMode="+displayModeVal+"&printoption="+preOptedVal+"&bankerOption="+bankOptedVal+"&bankerAssuredOption="+bankAssuredOptedVal+"&currencyOption="+currencyOptedVal+"&productID="+productCheckId+"";
			}
			document.form1.submit();
        return false;
}

function viewPolicyss(authorized)
{
	document.form1.authorized.value="Y";
	document.form1.action="Copy of information Admin.jsp";
	document.form1.submit();
	return false;
}
function download(cFileName,sFileName)
{
	document.form1.custFileName.value=cFileName;
	document.form1.systemFileName.value=sFileName;
	document.form1.action="MarineDownloadFile.jsp";
	document.form1.submit();
	return false;
}
function viewDocuments(policyNo){
	document.form1.policyNo.value = policyNo;
	document.form1.action="viewDocumentReport.action";
	document.form1.submit();
	return false;
}

</script>
