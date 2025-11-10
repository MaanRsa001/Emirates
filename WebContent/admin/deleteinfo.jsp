<%
 String	A1=(String)request.getAttribute("A1")==null?"":(String)request.getAttribute("A1");
 String	A2=(String)request.getAttribute("A2")==null?"":(String)request.getAttribute("A2");
 String	A3=(String)request.getAttribute("A3")==null?"":(String)request.getAttribute("A3");
 String	A4=(String)request.getAttribute("A4")==null?"":(String)request.getAttribute("A4");
 String	A5=(String)request.getAttribute("A5")==null?"":(String)request.getAttribute("A5");
 String	A6=(String)request.getAttribute("A6")==null?"":(String)request.getAttribute("A6");
 String	A7=(String)request.getAttribute("A7")==null?"":(String)request.getAttribute("A7");
 String	A8=(String)request.getAttribute("A8")==null?"":(String)request.getAttribute("A8");
 String	error=(String)request.getAttribute("error")==null?"":(String)request.getAttribute("error");
 String path = request.getContextPath();
%>

<html>
<head>
        <title>Emirates</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
    </head>
<body>



<FORM NAME="first" METHOD="GET">
       <table width="213" border="0" cellspacing="0" cellpadding="0">
		

                </tr>
              </table>
            </td>

		
            <td width="1"></td>
            <td align="left" valign="top">

          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr align="center">
            <td colspan="3">
			                          				  
			<tr align="center">
            <td colspan="3">
			<table width="100%"  border="0" cellspacing="1" cellpadding="0" class="blueborder">
              <tr>
               <td align="center" >
<table width="100%"  border=0 >
<tr  height="25"  class="blueborder">
	<td align="center" width="75%"><font size="2" color="Blue"><b><u>Select the files to Delete</u></b></td>
</tr>
<% if( !("".equalsIgnoreCase(error)))    { %>
<tr height="25" >
<td align="center" width="45%"><font size="2" color="red"><b><%=error%> </b></font></td>
 </tr>
 <%}  else {%>
<% if( !("".equalsIgnoreCase(A1)))    { %>
 <tr height="25" >
<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A1"><%=A1%> </font></td>
 </tr>
 <%}%>
 <% if( !("".equalsIgnoreCase(A2)))    { %>
 <tr height="25">
	<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A2"><%=A2%></font></td>
 </tr>
 <%}%>
 <% if( !("".equalsIgnoreCase(A3)))    { %>
 <tr height="25">
	<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A3"><%=A3%></font></td>
	</tr>
	<%}%>
	<% if( !("".equalsIgnoreCase(A4)))    { %>
<tr height="25">
	<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A4"> <%=A4%></font></td>
 </tr>
 <%}%>
 
 <% if( !("".equalsIgnoreCase(A5)))    { %>
<tr height="25">
	<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A5"> <%=A5%></font></td>
 </tr>
 <%}%>

 <% if( !("".equalsIgnoreCase(A6)))    { %>
<tr height="25">
	<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A6"> <%=A6%></font></td>
 </tr>
 <%}%>
 <% if( !("".equalsIgnoreCase(A7)))    { %>
<tr height="25">
	<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A7"> <%=A7%></font></td>
 </tr>
 <%}%>
 <% if( !("".equalsIgnoreCase(A8)))    { %>
<tr height="25">
	<td align="left" width="45%"><font size="2"><INPUT TYPE="CHECKBOX" NAME="del" VALUE="A8"> <%=A8%></font></td>
 </tr>
 <%}%>

 <% if(("".equalsIgnoreCase(A1))  &&  ("".equalsIgnoreCase(A2)) &&  ("".equalsIgnoreCase(A3)) && ("".equalsIgnoreCase(A4)) && ("".equalsIgnoreCase(A5)) && ("".equalsIgnoreCase(A6))&&  ("".equalsIgnoreCase(A7)) &&  ("".equalsIgnoreCase(A8)) && ("".equalsIgnoreCase(error)))    
{

	%>
<tr height="25">
	<td align="center" width="45%"><font size="3" color="red"> <b>No Files Available to Delete </b></font></td>
 </tr>
 <%}%>
 <%}%>
 <tr height="25">
 <td align="center" >
 <input type="image" border=0  src='<%=path%>/images/Proceed.jpg' onClick="return SubmitAction()" style="cursor:hand">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=path%>/images/Back.jpg" onclick="window.close()" style="cursor:hand">
					
		</td>
		 </tr>
</table>
</td>
 </tr>
</table>
</form>
</body>
</html>
<script language="Javascript">
function SubmitAction()
{
	document.first.action='Delete4Debit.DeleteDebit';
	document.first.submit();
	return false;
}
</script>