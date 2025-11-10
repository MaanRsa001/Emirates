<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<%@ include file="menus.jsp"%>
<body leftmargin = "0" topmargin = "0" marginwidth = "0" marginheight = "0">
<form name="renewalSuccess" method="post">
<%
	String proposalNo=request.getParameter("proposalNo")==null?"":request.getParameter("proposalNo");
	String openCoverNo=request.getParameter("openCoverNo")==null?"":request.getParameter("openCoverNo");
%>
<br><br>
	 <table width='500' border='1' cellspacing='0' cellpadding='1' align='center' >
		 <tr>
		      <td height="60"  >
				<table width='100%' border='0' cellspacing='0' cellpadding='0'>
					 <tr>
          				 <td height="38" align="center" colspan="2"><b>
          					<font color=blue face="Arial, Helvetica, sans-serif" size="2"><span class="heading" >INFORMATION</span></font></b>
       					 </td>
				     </tr>
       				 <%if("null".equals(proposalNo)){%>
     				 <tr>
          				 <td height="38" align="center" colspan="2">
          					<b><font color=red face="Arial, Helvetica, sans-serif" size="2">Open Cover <%=openCoverNo%> Renewal Failed.</font></b><br/>
          				  </td>
				     </tr>
					 <tr>
          				 <td height="38" align="center" colspan="2">
          					<a href="<%=request.getContextPath() %>/premiumOpenCover/showApprovedCover.jsp" class="buttonsMenu" ><img src="<%=request.getContextPath()%>/images/Proceed.jpg"></a>
       					 </td>
				     </tr>
       				 <%}else{ %>
       				 <tr>
          				 <td height="38" align="center" colspan="2">
          					<b>Open Cover <font color=blue face="Arial, Helvetica, sans-serif" size="2"><%=openCoverNo%></font> Renewed Successfully.<br/>
          					New Proposal No.&nbsp;<font color=blue face="Arial, Helvetica, sans-serif" size="2"><%=proposalNo%></font>&nbsp;can be found in Quote Register</b>
          				  </td>
				     </tr>
					 <tr>
          				 <td height="38" align="center" colspan="2">
          					<a href="<%=request.getContextPath() %>/premiumOpenCover/ShowOpenCoverEntry.jsp" class="buttonsMenu" ><img src="<%=request.getContextPath()%>/images/Proceed.jpg"></a>
       					 </td>
				     </tr>
       				 <%} %>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">
</script>						
</html>
