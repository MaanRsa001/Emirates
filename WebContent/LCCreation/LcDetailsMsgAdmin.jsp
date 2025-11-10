

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="java.util.*" %>
<%@ include file="../login/home1.jsp" %>

<jsp:useBean id="premiumInputs" class = "com.maan.opencover.LCDetails.LCInputsBean">
<jsp:setProperty property="*" name="premiumInputs"/>
</jsp:useBean>

<jsp:useBean id="cover" class="com.maan.broker.UserCreationBean">
</jsp:useBean>

<%
String productTypeId = "";
if(session.getAttribute("product_id")!=null)
	productTypeId = (String)session.getAttribute("product_id");
String fromBroker = request.getParameter("fromBroker");
fromBroker = fromBroker==null?"No":fromBroker;
if(fromBroker.equalsIgnoreCase("Yes"))
{
%>
	<table width="90%"  border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	<td align="center" valign="top" width="90%">
	<%@include file="../openMenu.jsp"%>
	</td></tr></table>
<%
}
else
{
%>
	<%@ include file="../admin/header.jsp" %>
	</td></tr></table>
</td></tr></table>
</td></tr></table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0" align=left>
<tr>
<td align="left" valign="top" width="100%">
<%
}
%>


<html>
<head>
<title>Emirates</title>

</head>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
	.style1 {color: #FF0000}
-->
</style>
<body>


<br><br><br><br>
<%
	String msgFlag = "";
	if(request.getAttribute("Delete")!=null)
		msgFlag = (String)request.getAttribute("Delete");
	String msg = "";
	String openNo = request.getParameter("openNo");
	openNo = openNo==null?"0":openNo;
	if(msgFlag.equalsIgnoreCase("Yes"))
		msg = "LC DETAILS DELETED SUCCESSFULLY";
	else
		msg = "LC DETAILS ADDED SUCCESSFULLY";
	String openCoverNo = (String)request.getAttribute("openCoverNo")==null?"":(String)request.getAttribute("openCoverNo");
		        
        String bcName = request.getParameter("bcName")==null?"":request.getParameter("bcName");
		String login = request.getParameter("lcBroker")==null?"":request.getParameter("lcBroker");
		String moc = request.getParameter("moc")==null?"":request.getParameter("moc");
		String cName = request.getParameter("cName")==null?"":request.getParameter("cName");
        
%>
<body  leftmargin = "0" topmargin = "0" marginwidth = "0" marginheight = "0">
<form name="form2"  method="post" action="">

<input type="hidden" name="opencover" value='<%=openCoverNo%>'>
<input type="hidden" name="openNo" value="<%=openNo%>"/>
<input type="hidden" name="lcBroker" value='<%=login%>'>
<input type="hidden" name="bcName" value='<%=bcName%>'>
<input type="hidden" name="cName" value='<%=cName%>'>
<input type="hidden" name="moc" value='<%=moc%>'>

	 <table width='350' border='1' cellspacing='0' cellpadding='1' align='center' >
		 <tr>
		      <td height="60"  >
				<table width='100%' border='0' cellspacing='0' cellpadding='0'>
					 <tr>
				         <td height="38" width="10%" valign="top"  class="heading">
						
						 </td>
          <td height="38" align="center">
          <span class="heading">INFORMATION</span></td>
				     </tr>
					<tr>
						<td height="40" align="center" colspan="2">
						<b><font color="red" >
<%								out.println(msg);
%>					
</font></b></td>
					</tr>			
					<tr>
						<td bgcolor='#FFFFFF' height='45' align='center' colspan='4'> 
						
<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr> 
				 <td><a href="#" onClick='ParentSubmit("<%=fromBroker%>")' ><img src="../images/Proceed.jpg"></a> </td>
				 
				 </table>

					    </td>
					</tr>
				</table>
				<input type="hidden" name="fromBroker" value="<%=fromBroker%>"/>
						</form></body>
</html>

<script>
function ParentSubmit(from)
{
	document.form2.fromBroker.value = from;
	document.form2.action="../LCCreation/LCOpenCoverWise.jsp";
	document.form2.submit();
	return false;

}
</script>