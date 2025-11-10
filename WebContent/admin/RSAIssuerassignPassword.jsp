<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="header.jsp" %>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->


</style>

<jsp:useBean id="broker" class="com.maan.admin.BrokerCreationBean">

<jsp:setProperty name="broker" property="out" value="<%= response.getWriter() %>" /> 

</jsp:useBean>
</head>

<body>
<form name="form1" method="post" action="RSAconformPassword.jsp">


		<table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="left.jsp" --%>
		
            </tr>
        </table></td>
        <td width="1"></td>
        <td align="left" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="8" height="25" class="heading" bgcolor="#FCC721">&nbsp;</td>
            <td width="1"></td>
                  <td width="98%" class="heading"><strong>ADMIN INFORMATION</strong></td>
          </tr>
          <tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr align="center">
            <td colspan="3"><table width="95%"  border="0" cellspacing="1" cellpadding="0" class="blueborder">
              <tr>
                <td align="center" class="ltbgyellow">
                          
							<%@ include file="admin_sub_menu_RSAIssuer.jsp" %>
							
							</td>
                            </tr>
							  <tr> 
                              <td width="4">&nbsp;</td>
                              <td width="156" height="33" align="left">&nbsp;</td>
                              <td width="144" align="left">&nbsp;</td>
                              <td width="75" align="left">&nbsp;</td>
                              <td width="138" align="left">&nbsp;</td>
                            </tr>

							<%
							String loginBranch = "";
							loginBranch = (String) session.getAttribute("LoginBranchCode");
							String brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
							String error=request.getParameter("error")==null?"":request.getParameter("error");
							String efrom="";
							
								if(request.getMethod().equalsIgnoreCase("post")&&brokerId.length()>0)
								{
									if(!broker.checkCodeForAdmin(brokerId))
									{
										error="Please Select Broker";
										efrom="brokerid";
									}


								}
								else if(request.getMethod().equalsIgnoreCase("post"))
								{
									error="Enter BrokerId";
									efrom="brokerid";
								}
							if(error.length()>0)
							{
							
							%>
 <tr> 
 <td colspan="3">
 <div align="center"><font color="red"><%=error%></font></div>
 <td>
 </tr>
							<%
							}
							%>
                            <tr> 
							
<%
	String[][] brokers=broker.getRSAAdmins(loginBranch,"RSAIssuer");
				
%>



<td height="23" colspan="6" >
							  
							  	<tr>
									<td width="238" align="right"><b>Select  Name For Edit  : </b>&nbsp;</td>
<td width="396" align="left">
<select name="brokerId"  >
<option value="0">Select</option>
<%
String select="";
for(int i=0;i<brokers.length;i++)
{
if(brokerId.equalsIgnoreCase(brokers[i][3]))
select="selected";
else
	select="";
%>
<option value="<%=brokers[i][3]%>"  <%=select%> ><%=brokers[i][2]%>(<%=brokers[i][3]%>)</option>
<%
}	
%>
</select>
</td>
<td width="144" align="left"></td>
							 			</tr>
							 
							  </td>

					
                            </tr>

							<%
if(request.getMethod().equalsIgnoreCase("post")&&brokerId.length()>0&&(error.length()<=0||efrom.length()==0))
							{
							%>
<tr> 
                              <td width="238"  align="right"><b>Enter New Password : </b>&nbsp;</td>
                              <td width="144" align="left">
                                  <input type="password" name="password" value="<%=request.getParameter("password")==null?"":request.getParameter("password")%>">
                              </td>
							   <td width="38%" align="right" colspan="2">(min 8 chars with a number and a lower case letter)</td>
                            </tr>
							<tr> 
                              <td width="238" height="33" align="right"><b>ReType Password :</b> &nbsp;</td>
                              <td width="144" align="left">
                                  <input type="password" name="repassword"  value="<%=request.getParameter("repassword")==null?"":request.getParameter("repassword")%>">
                              </td>
							  <td width="144" align="left"></td>
							   <td width="144" align="left"></td>
                            </tr>


<%}

%>

                           <tr> 
                              <td width="4">&nbsp;</td>
                              <td width="156" height="33" align="left">&nbsp;</td>
                              <td width="144" align="left">&nbsp;</td>
                              <td width="75" align="left">&nbsp;</td>
                              <td width="138" align="left">&nbsp;</td>
                            </tr>
                      
                          
                      
						
                         
                          
                          </table>
                        </td>
              </tr>
            </table></td>
            </tr>
          <tr align="center">
            <td height="1" colspan="3"></td>
          </tr>
          <tr align="center">
            <td colspan="3"><table width="95%"  border="0" cellspacing="0" cellpadding="0">

			<%
			
			if(!request.getMethod().equalsIgnoreCase("post")||brokerId.length()<=0||(error.length()>0&&efrom.length()>0))
			{
			%>
              <tr>
                        <td height="32" align="right" valign="middle"> 
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                          <!--<a href="ViewQuote_B2C.html"><img src="images/save.gif" width="89" height="23"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                          <a href="javascript:posting()"><img src="../images/Submit.jpg" ></a>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
                        </td>

						<%
			}
						else
						{
						
						%>
 <tr>
                        <td height="32" align="right" valign="middle"> 
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                          <!--<a href="ViewQuote_B2C.html"><img src="images/save.gif" width="89" height="23"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                          <a href="javascript:document.form1.submit()"><img src="../images/Submit.jpg"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
                        </td>
<%
						}	
						%>

              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
<input type="hidden" name="rsaissuer" value="rsaissuer">
</table>
</form>
</body>

</html>

<script>
function posting()
{
	document.form1.action="RSAIssuerassignPassword.jsp"
	document.form1.submit();
}
</script>
