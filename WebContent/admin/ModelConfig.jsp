<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@
page contentType="text/html; charset=windows-1256"%> 
<%@
page pageEncoding="Cp1256"%>

<html>
<%@ include file="header.jsp" %>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
}
a:hover {
	color: #003366;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #FFFFFF;
}
a:visited:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	color: #003366;
	text-decoration: none;

}
-->
</style>
</head>
<br/><br/>
<jsp:useBean id="broker" class="com.maan.admin.DAO.MotorBodyCreation"/>
<form name="form1" method="post" action="../servlet/MotorBodyController">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td align="center" ></td> <tr>
<td width="1"></td> <td width="90%" class="heading"><strong>MOTOR TARIFF CONFIGURATION </strong></td>
</tr> <tr align="center"> <td colspan="3">&nbsp;</td> </tr> <tr align="center"> <td colspan="3">
<table width="90%" border="0" align="center" cellspacing='1'> 

<%@ include file="admin_sub_menu_motor_body.jsp" %>
<%
                String[][] model=(String[][])request.getAttribute("make");
                
				
				
 %>

	<tr>
	<td colspan="6" height="15" align="center">
	<font color="red"><%=(request.getAttribute("error")==null?"":((StringBuffer)request.getAttribute("error")).toString()) %></font></td>
	</tr>
    <tr > 
	<td colspan="6" height="15" class = "heading"> 
	<div align="center" ><B>Model Configuration</div>	</td> </tr> <tr> 
    </tr>
   <tr></tr>	<tr height="10"></tr>	<tr></tr>	<tr></tr>		
    				<tr>
    				<td align="center" class="formtxtf">
						<td class="formtxtf" align="center">
							<STRONG>Choose Make Type&nbsp;:</STRONG>
						</td>
						<td class="formtxtf">	<SELECT id="makeID" name="makeID" class="form">
							 <%for(int i=0;i<model.length;i++){
							 %>
								<OPTION value='<%=model[i][0]%>'><%=model[i][1]%></OPTION>
							<%}%>
								</SELECT>
					  </td>
					  <td class="formtxtf"></td>
					</td>		
				</tr>
					<tr height="10"></tr>				
				<tr>
				<td>
				<td></td>
				
												<td align="center">
												
												   <img  src="../images/Submit.jpg" onClick="goModel()"/>
																								  
												
				<td></td>								
				</td>
				</td>								
				</tr>
				
			
				
				</table>
				</table>
                
                
                 
                 <table width="90%" border="0" cellpadding="0" cellspacing="0" align='center'>
					<tr>
						<td valign="top">
							<table border="0" align="center" cellpadding="4" cellspacing="0">
								<tr>
									<td>
										<table border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												
											</tr>
												
										</table>
									</td>
									<td>
										<table border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												
												
											</tr>
										</table>
									</td>
								</tr>
							</table>						
	</table>
	<input type="hidden" name="requestFrom" value="modelconfig"/>
    <input type="hidden" name="val" value="display"/>					
</form>
<script>
function goModel()
{
	document.form1.action="../servlet/MotorBodyController";
	document.form1.submit();
}


</script>
</html>				

				


                
                
       