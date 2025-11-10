<%String cpath1 = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Emirates</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
	<jsp:useBean id = "OCE" class = "com.maan.opencover.bean.opencoverEntry">
		<jsp:setProperty name = "OCE" property = "*"/>
	</jsp:useBean>

	<jsp:useBean id="newCover" class="com.maan.opencover.bean.newCoverBean">
		<jsp:setProperty name="newCover" property="*"/>
	</jsp:useBean>
	
	<link href="<%=cpath1%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=cpath1%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script language="JavaScript">
		function stopRKey(evt) { 
		 	 var evt = (evt) ? evt : ((event) ? event : null); 
		  	 var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		 	 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;}
		} 
		document.onkeypress = stopRKey; 
	</script>
</head>
<body>
<table width="90%"  border="0" cellspacing="0" cellpadding="0"  align="center">
<tr>
	<td>
		<%@include file="menus.jsp"%>
	</td>
</tr>
<tr>
	<td>
<form name="form1" method="post">
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
			
<%
String name="";
if(request.getParameter("coverno")==null || "null".equalsIgnoreCase(request.getParameter("coverno")))
	name=(String)session.getAttribute("opencoverNo");
else
	name=request.getParameter("coverno");
String msg="";
String action="";
if(request.getParameter("error")!=null && request.getParameter("error").length()>0){
	msg="INTEGRATION "+request.getParameter("error")+"<br/> POLICY CANNOT BE "+ ("Y".equalsIgnoreCase(request.getParameter("renewalYN"))?"RENEWED":"GENERATED") +" FOR THE PROPOSAL NO "+(String)session.getAttribute("proposalNo");
	action=pathq+"/premiumOpenCover/ShowOpenCoverEntry.jsp";
}else if(request.getParameter("coverno")!=null && request.getParameter("coverno").length()>0 && (name!=null || "null".equalsIgnoreCase(name)))
{
	msg="OpenCover  Number "+name+" are Generated Successfully for this Proposal Number "+(String)session.getAttribute("proposalNo");
	action=pathq+"/premiumOpenCover/showApprovedCover.jsp";
}else if("referral".equalsIgnoreCase(request.getParameter("referral"))){
	msg="Your Proposal Number "+(String)session.getAttribute("proposalNo")+" is Under Referral,<br/>UW will contact you";
	action=pathq+"/premiumOpenCover/ShowOpenCoverEntry.jsp";
}else if("YES".equalsIgnoreCase(request.getParameter("referral")) || "NO".equalsIgnoreCase(request.getParameter("referral"))){
	msg="Your Proposal Number "+(String)session.getAttribute("proposalNo")+" is Referred to UnderWritter,<br/>UW will contact you";
	action=pathq+"/premiumOpenCover/ShowOpenCoverEntry.jsp";
}
else 
{
	msg="Your Information are Stored Successfully for this Proposal Number "+(String)session.getAttribute("proposalNo");
	action=pathq+"/premiumOpenCover/ShowOpenCoverEntry.jsp";
}
String debitNoteNo=request.getParameter("debitNoteNo")==null?"":request.getParameter("debitNoteNo");
String creditNoteNo=request.getParameter("creditNoteNo")==null?"":request.getParameter("creditNoteNo");
%>
<br><br>
<table width='500' border='1' cellspacing='0' cellpadding='1' align='center' >
		 <tr>
		      <td height="60"  >
				<table width='100%' border='0' cellspacing='0' cellpadding='0'>
					 <tr>
				         <td height="38" width="5%" valign="top">
						
						 </td>
          <td height="38" align="center"><b>
          <font color=blue face="Arial, Helvetica, sans-serif" size="2"><span class="heading" >INFORMATION</span></font></b></td>
				     </tr>
					<tr>
						<td align="center" colspan="2">
						<b><font size="3">
		<%					
			out.println(msg);
		%>		
</font></b></td>
					</tr>
					<tr><td>&nbsp;</td></tr>			
					<tr>
					<td align='center' colspan='4' >
						&nbsp;&nbsp;&nbsp;
						<%if("GUEST".equalsIgnoreCase(loginIds)){%>
						<a href="#" onClick= 'return printQuote()' class="buttonsMenu" >
						<img src="<%=pathq%>/images/PrintQuote.jpg" ></a>
						<%}%>
						<%if(request.getParameter("coverno")!=null && request.getParameter("coverno").length()>0 && (name!=null || "null".equalsIgnoreCase(name))){ %>
					 	<a href="#" onclick="return viewPolicys('<%=name%>','<%=(String)session.getAttribute("user")%>','schedule','<%=name%>','','<%=(String)session.getAttribute("proposalNo")%>');">
					 	<img src="<%=path%>/images/Schedule.jpg"></a> 
					 	<%--
					 	<%}if(!"".equalsIgnoreCase(debitNoteNo)){%>
					 	&nbsp;&nbsp;&nbsp;
					 	<a href="#" onclick="return viewPolicys('<%=name%>','<%=(String)session.getAttribute("user")%>','debit','<%=debitNoteNo%>','');">
					 	<img src="<%=path%>/images/Debit.jpg"></a> 
					 	<%}if(!"".equalsIgnoreCase(creditNoteNo)){%>
					 	&nbsp;&nbsp;&nbsp;
					 	<a href="#" onclick="return viewPolicys('<%=name%>','<%=(String)session.getAttribute("user")%>','credit','<%=creditNoteNo%>','');">
					 	<img src="<%=path%>/images/Credit.JPG"></a>
					 	 --%>
					 <%}%>
					 &nbsp;&nbsp;
					  <%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
					 <a href="<%=action%>" class="buttonsMenu" ><img src="<%=pathq%>/images/Proceed.jpg"></a>
					  <%}else{%>
					  <a href= "#" onClick='return back1()'> <img src="<%=pathq%>/images/Proceed.jpg" > </a> </td>	
					  <input type="hidden" name="user" value="GUEST"/>
					  <%}%>
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<%if("GUEST".equalsIgnoreCase(loginIds)){%>
			<input type="hidden" name="user" value="GUEST"/>
<%}%>
</form>
	</td>
</tr>
</table>
<script type="text/javascript">
function back1()
{
	document.form1.action="onlineProposal.jsp";
	document.form1.submit();
	return true;
}
function viewPolicys(policynumber,loginId,docType,docNo,amendId,proposalNo)
{
	     var URL ="<%=pathq%>/GenerateDocument.jspa?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId+"&proposalNo="+proposalNo;
		 var windowName = "PolicyView";
		 var width  = screen.availWidth;
		 var height = screen.availHeight;
		 var w = 900;
		 var h =	500;
		 var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
		return false;
}
 function printQuote()
    {
		var URL = '';
		URL='PrintOpencoverSummary.jsp';
		var windowName = "PrintOpencoverSummary";
		var width  =	 screen.availWidth;
		var height = screen.availHeight;
		var w = 900;
		var h =	400;
		var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w) * .5)  +
		',top='			  + ((height - h ) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
		strOpen.focus();
		return false;
	}
</script>
</body>
</html>