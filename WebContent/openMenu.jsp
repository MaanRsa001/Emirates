<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
a{
text-decoration:none;
}
-->
</style>
<script>

function change_class(val) { 
document.getElementById(val).className='buttonsMenuBlueMOvar';
}

function RevertClass(val)
{
	document.getElementById(val).className='buttonsMenuBlue';
}
</script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
<tr>
<%

String productName="";
String openCoverMississippiNo="";
String openCoverCustomerName="";

String s1=""+session.getAttribute("mode");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
%>
	<td>&nbsp;
	</td>
    <td width="40%" align="right">
	<table width="50%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td ><a id=txtContent href="<%=path%>/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a id=txtContent href="<%=path%>/login/logout.jsp">Sign&nbsp;out&nbsp;&nbsp;</a></td>
        </tr>
    </table></td>
  </tr>

 <!-- <tr>
    <td height="13" colspan="2" ></td>
  </tr> -->
  <tr align="center">
   <td colspan="3" align="left"><table width="90%"  border="0" cellspacing="0" cellpadding="0">
      <tr >
       <td width="20">&nbsp;</td>
        <td valign="top" align="left"><img src="<%= path %>/images/logo.jpg" width="230" height="60"></td>
		
      </tr>
      </table>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" >
                  <tr>
                    <td width="20">&nbsp;</td>
                    <td width="250">&nbsp;</td>
                    <td>
					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="100">&nbsp;</td>
                        <td width="100">&nbsp;</td>
                        <td align="right" >&nbsp;</td>
                        <td width="20">&nbsp;</td>
                      </tr>
                    </table></td>
                  </tr>
				 
                </table>
				
</td>
  </tr>
  </table>
  <table width="100%"  border="0" cellpadding="0" cellspacing="0" >

   <tr>
                    <td width="20">&nbsp;</td>
		<%
  if(s1.trim().equals("s"))
  {
 %>
         <!-- <td height="34" align="left">Welcome 
<strong><%=(String)session.getAttribute("RSAUSER")%>&nbsp;</strong>logged in On Behalf 
of&nbsp;<strong><%=(String)session.getAttribute("user")%></strong>&nbsp;in&nbsp;<strong><%=(String)session.getAttribute("userLoginMode")%>&nbsp;Mode&nbsp;under <%=productName%>  </strong></td> -->

<td height="34" align="right">Login Id:  
<strong><%=(String)session.getAttribute("RSAUSER")%>&nbsp;</strong>&nbsp;- <%=productName%>  </strong></td>
<%
	  }
   else{
	   %>
   <!-- <td height="34" align="left">Welcome 
<strong><%=(String)session.getAttribute("user")%></strong>&nbsp;in&nbsp;<strong><%=(String)session.getAttribute("userLoginMode"
)%>&nbsp;Mode&nbsp;under <%=productName%>  </strong></td> -->
<td height="34" align="right">Login Id:  
<strong><%=(String)session.getAttribute("user")%></strong>&nbsp;- <%=productName%>  </strong></td>
<%	  
}
%>

 </tr>
  </table>
  <script>
 function ReleaseNotes()
{
	var URL = "<%=request.getContextPath()%>/login/ReleaseNotesMain.jsp";
	var windowName = "ReleaseNotes";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 600;
	var h = 450;
	var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=yes'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
	return false;
}	
</script>