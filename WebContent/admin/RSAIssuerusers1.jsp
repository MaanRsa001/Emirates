<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
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

<jsp:useBean id="BCB" class="com.maan.admin.BrokerCreationBean">
<jsp:setProperty name="BCB" property="out" value="<%= response.getWriter() %>" /> 
</jsp:useBean>

</head>

<body>
<form name="userPrevilege" method="post" action="BrokerCreationController">

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
            <td colspan="3"><table width="95%"  border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td align="center">


								<%@ include file="admin_sub_menu_RSAIssuer.jsp" %>
                              </td>
                            </tr>

					
							

	<%
		String prodcutId	=	"0";
		String coverageId	=	"0";
		String addonId		=	"0";
		String productcount	=	"0";
String  adminBranch = (String) session.getAttribute("adminBranch") ==null ? "":(String) session.getAttribute("adminBranch");
String selectbroker=request.getParameter("selectbroker")==null?"":request.getParameter("selectbroker");
String selectdiscarded=request.getParameter("selectdiscarded")==null?"":request.getParameter("selectdiscarded");
String brokers=request.getParameter("brokers")==null?"":request.getParameter("brokers");
String users=request.getParameter("user")==null?"":request.getParameter("user");
String accountEx=request.getParameter("accountEx")==null?"":request.getParameter("accountEx");

		String selectedBrokersStatus="";
		String error=request.getAttribute("error")==null?"":(String)request.getAttribute("error");
		if(selectbroker.equals("0")&&request.getMethod().equalsIgnoreCase("post"))
		{

			error="Please Select EmiratesUser/UWAdmin Name";
		}
		
		String[][] broker	=	BCB.getRSAAdmins((String)session.getAttribute("LoginBranchCode"),"RSAIssuer");
		String loginids="";
		BCB.setDisStatus(selectdiscarded);
String selectedBrokersType="";
if(selectbroker.length()>0&&request.getMethod().equalsIgnoreCase("post")&&!selectbroker.equalsIgnoreCase("0"))
{
	selectedBrokersStatus	=	BCB.getBrokersStatus(selectbroker);
	selectedBrokersType	=	BCB.getAdminUserType(selectbroker);
	selectedBrokersType=selectedBrokersType.replaceAll("Rsa","Emirates");
}
		

		

	%>

                          
			<table width="750" align='center'  border="1" cellpadding="0" cellspacing="0"  align="center" bgcolor="#FFFFFF"  bordercolorlight='#B7C6DC' bordercolordark='#6F8DB9'>
				<tr>
					<td> <font color="red"> <%=error%></font>
						<table width="100%" align='center'  border="0" cellpadding="0" cellspacing="1"  align="center" bgcolor="#FFFFFF"  bordercolorlight='' bordercolordark='#B7C6DC'>
							<tr>
								<td align="center" style="padding-left:10px;" height="25" class="text" ><b>Select Underwriter</b>
								
								<select  name="selectbroker" onchange="brokerselection(this.value)">
								<option value="0">select</option>
									
								<%
								String selectbro="";
								for(int i=0;i<broker.length;i++)
								{
									if(selectbroker.equalsIgnoreCase(broker[i][3]))
									{

										selectbro="selected";
									}
									else
									{
										selectbro="";
									}
										
								%>
									<option value="<%=broker[i][3]%>"  <%=selectbro%>><%=broker[i][2]%>(<%=broker[i][3]%>)</option>
							<%
								}	

								
							%>
								</select>
								</td>
							</tr>

							<%
								if(request.getMethod().equalsIgnoreCase("post") && !request.getParameter("selectbroker").equalsIgnoreCase("0"))
								{
									String statusMessage="";
									String status = selectedBrokersStatus;
									  if(status.equalsIgnoreCase("Y"))
									  {
											statusMessage="This "+selectedBrokersType+" is in Active Stage";
											
									  }
									  else if(status.equalsIgnoreCase("D"))
									  {
										 statusMessage="This "+selectedBrokersType+" is in DeActive Stage";
									  }
									  else if(status.equalsIgnoreCase("N"))
									  {
										 statusMessage="This "+selectedBrokersType+" is Deleted";
									  }
									  else if(status.equalsIgnoreCase("L"))
									  {
										 statusMessage="This "+selectedBrokersType+" is Locked";
									  }
							%>
								 <tr>
									<td align="left">
									<br><center><b><%=statusMessage%><b></center><br><b>Please select the choice to do modification of the selected Emirates User/UWAdmin's Login</b><br><br>
										<table width="100%" align='center'  border="0" cellpadding="0" cellspacing="1"  align="center" bgcolor="#FFFFFF">
										
											<th align="center" class="text" height="27" width="30%"><input type="radio" name="brokerStatus" value="Y" <%=selectedBrokersStatus.equalsIgnoreCase("Y")?"checked":""%>>&nbsp;Active</th>
											<th align="center" class="text" height="27" width="30%"><input type="radio" name="brokerStatus" value="D" <%=selectedBrokersStatus.equalsIgnoreCase("D")?"checked":""%>>&nbsp;Deactive</th>
											<th align="center" class="text"  height="27" width="30%"><input type="radio" name="brokerStatus" value="N" <%=selectedBrokersStatus.equalsIgnoreCase("N")?"checked":""%>>&nbsp;Delete</th>
											<th align="center" class="text"  height="27" width="30%"><input type="radio" name="brokerStatus" value="L" <%=selectedBrokersStatus.equalsIgnoreCase("L")?"checked":""%>>&nbsp;Lock</th>
											
								
				<%
								}
				%>

													</tr>
										</table>
									</td>
								</tr>
								</table>
				
			
			<br>

			<div align='center'><a href="javascript:onclick=document.userPrevilege.submit()"><img src='<%=path%>/images/Submit.jpg' border='0' onClick="createRights()"></a>&nbsp;&nbsp;
			
	       </td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>

</table>
<input type="hidden" name="requestfrom" value="RSAusers1">
<input type="hidden" name="rsaissuer" value="rsaissuer">
</form>
</body>
<script>
function brokerselection( )
{

	document.userPrevilege.action="RSAIssuerusers1.jsp";
	document.userPrevilege.submit();
}

</script>