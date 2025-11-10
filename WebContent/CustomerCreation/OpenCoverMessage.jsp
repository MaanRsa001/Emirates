

<%@ page import="java.util.*" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<%
	String msg="Record Updated Successfully";
	String openCoverNos = (String)(request.getAttribute("openCoverNos")==null ? "" : request.getAttribute("openCoverNos"));
	if(openCoverNos == null || openCoverNos.equals("null") || openCoverNos.equals("") || openCoverNos.length() == 0)
	{
		openCoverNos = request.getParameter("openCoverNos")== null ? "" :request.getParameter("openCoverNos");
	}
	if(openCoverNos!= null && openCoverNos.length() > 0)
	{	
		openCoverNos = openCoverNos.replaceAll("'","");
	}
%>
<body leftmargin = "0" topmargin = "0" marginwidth = "0" marginheight = "0">
<form name="form2"  method="post" action="">
<br><br><br><br>
	 <table width='350' border='1' cellspacing='0' cellpadding='1' align='center' >
		 <tr>
		      <td height="60"  >
				<table width='100%' border='0' cellspacing='0' cellpadding='0'>
					 <tr class="heading">
				         <td height="38" width="10%" valign="top" class="heading">
						 </td>
          <td height="38" class="heading" align="center"><b><font color=blue face="Arial, Helvetica, sans-serif" size="2">INFORMATION</font></b></td>
				     </tr>
					<tr>
						<td height="40" align="center" colspan="2">
						<b><font color="red" >
					<%	out.println(msg);	%>					
					</font></b></td>
					</tr>			
					<tr>
						<td bgcolor='#FFFFFF' height='45' align='center' colspan='4'> 
						  <input type ="image" src='../images/Proceed.jpg' onClick="return ParentSubmit('<%=openCoverNos %>')" border='0'>
					    </td>
					</tr>
				</table>
				</td></tr></table>
				<input type="hidden" name="openCoverNos" value="<%=openCoverNos%>" />
</form>
</body>
<script>
function ParentSubmit(openCoverNos)
{	
	window.opener.document.form1.openCoverNos.value = openCoverNos;
	self.close();
	return false;
}
</script>
</html>