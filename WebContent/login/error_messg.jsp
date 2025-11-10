<%
	String path = request.getContextPath();
	StringBuffer urlfrom=request.getRequestURL();
	String cpath = request.getContextPath();
	String cargoPath = "";
	cpath = cpath.replaceAll("/","");
	String cargoName = "Emirates";
	if(cpath.equalsIgnoreCase("E-Cargo-Emirates"))
		cargoPath = "971 4 302 9800/334 4474";
	else if(cpath.equalsIgnoreCase("TE-Cargo-Global"))
		cargoPath = "971 4 302 9800/334 4474";	
	else if(cpath.equalsIgnoreCase("E-Cargo-Saudi"))
	{
		cargoPath = "966 2 692 7085";	
		cargoName = "AL ALAMIYA";
	}
	else if(cpath.equalsIgnoreCase("E-Cargo-Oman"))
		cargoPath = "971 4 302 9800/334 4474";	
	else if(cpath.equalsIgnoreCase("E-Cargo-Bahrain"))
		cargoPath = "966 2 692 7085";	
%>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF">
<br><form name ='form1' action="<%=path%>/login/logout.jsp">

<table width="760" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="21" align="right" valign="middle"  class="bodytext"><font color="#FFFFFF"></font></td>
  </tr>
  <tr>
<table width="550" border="1" cellspacing="0" cellpadding="0" align="center" class="RSA_Cream_BG3">
  <tr bgcolor="#000099" align="right" valign="middle"> 
    <td > 
      <table width="500" border="0" cellspacing="0" cellpadding="0" align="left">
        <tr>
          <td><font face="Verdana, Arial, Helvetica, sans-serif" size="2" color="#FFFFFF"><b>&nbsp;&nbsp;Error</b></font></td>
        </tr>
      </table>
      <img src="../images/top_but.gif" width="50" height="14"></td>
  </tr>
  <tr bgcolor="#F2F2F2" align="center" valign="top"> 
    <td height="212"> 
      <table width="89%" border="0" cellspacing="0" cellpadding="0" class="RSA_Cream_BG3">
        <tr> 
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr> 
          <td height="38" width="10%" valign="top"><img src="<%=path%>/images/error.gif" width="34" height="33"></td>
          <td height="38" width="90%"><b><font face="Arial, Helvetica, sans-serif" size="2"><%=cargoName%></font></b></td>
        </tr>
        <tr> 
          <td height="90" width="10%">&nbsp;</td>
          <td height="90" width="90%" valign="top"> <font face="Arial, Helvetica, sans-serif" size="2">
		  
	   						  <table width='100%' border='1' cellspacing='1' cellpadding='1' align='center' class="bodytext">
						 <tr> 
						   <td> 
							 <p><b><br>Sorry, the  requested page could not be found. The reason may be :</b><br>
							   <br>1.Session ID is not valid.<br>
							   <br>2.We are unable to find a page with this session ID.<br>
							   <br>If the problem continues, please contact <%=cargoPath%><br>
							   <br><b>Please note the error message above, click the Back button on your 
							   browser and then continue</b></p>
						   </td>
						 </tr>
					   </table>		  
		  
		  </font></td>
        </tr>
        <tr align="center"> 
          <td height="39" colspan="2">
		   <table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr> 
				 <td valign="middle" class="button-left"><a href="javascript:onClick=document.form1.submit()" class="buttonsMenu" >
				<img src="<%=path%>/images/Cancel.gif"> </td>
			</table>
		  </form>
          </td>
        </tr>
	</td></tr></table>
	</td></tr>
	</table>

  <tr align="right">
   <td class="formtxt" height="21" background="../images/bottom_bg.gif"><font color="#FFFFFF" align="right">&nbsp;Developed by </font><a href ="http://www.maansarovar.com" target="_blank"><font color="#FFFFFF">Maan Sarovar Software </font></a></td>
  </tr>
  </table>
 
</body>
</html>
