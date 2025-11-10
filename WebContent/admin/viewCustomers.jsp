<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	String path = request.getContextPath();
%>
<%@ include file="header.jsp" %>

<jsp:useBean id="admin" class="com.maan.admin.AdminBean">



</jsp:useBean>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css"></head>

<body>
<form name="form1" method="get" action="">
 
              
               <table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="left.jsp" --%>

                </tr>
              </table>
            </td>

		
            <td width="1"></td>
            <td align="left" valign="top">

              <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="8" height="25" class="heading">&nbsp;</td>
                  <td width="1"></td>
                 <td width="100%" class="heading"><strong>CUSTOMER INFORMATION</strong></td>
          </tr>

	<%
			String loginId = (String) session.getAttribute("user");
			String adminBranch = "";
			adminBranch = admin.getAdminBranch(loginId);

			String indexvalue=request.getParameter("indexvalue")==null?"":request.getParameter("indexvalue");
			String customername=request.getParameter("customername")==null?"":request.getParameter("customername");
			
			String[][]  CutomerData=new String[0][0];
			if(indexvalue!=null&&indexvalue.length()>0&&!indexvalue.equals(""))
			{
				CutomerData=admin.getCustomerData(indexvalue,adminBranch);
			}
			else if(customername!=null&&customername.length()>0&&!customername.equals(""))
			{
				CutomerData=admin.getCustomerData(customername,adminBranch);
			}
			else
			{
				CutomerData=admin.getCustomerData(customername,adminBranch);
				
			}
			
			%>
            
                <tr align="center"> 
                  <td colspan="3">&nbsp;</td>
                </tr>
                <tr align="center"> 
                  <td colspan="3">
                    <table width="95%"  border="0" cellspacing="1" cellpadding="0">
                      <tr> 
                        <td align="left" bgcolor="#FFFFFF"> 
                          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td height="5"></td>
                            </tr>
                           
                            <tr> 
                              <td align="left"> 
                                <table width="100%" border="1" cellspacing="1" cellpadding="2">
<%
if(CutomerData.length>0)
{
%>

                            <tr> 
								   
                                    <td height="23" align="center" width="26%" class="text"><b>Name</b></td>
									 <Td height="23" align="center" width="22%" class="text"><strong>Broker Company Name</Strong></td>
                                    <td height="26" align="center" width="40%" class="text"><B>Address</B></td>
									

                                    <td height="10" align="center" width="22%" class="text"><strong>Phone Number </strong></td>
                                   
                                  </tr>

								<%
								for(int i=0;i<CutomerData.length;i++)
								{
									String fullCusAdd="";
									if(CutomerData[i][6]!=null)
										fullCusAdd = fullCusAdd +CutomerData[i][6]+", ";
									if(CutomerData[i][2]!=null)
										fullCusAdd = fullCusAdd +CutomerData[i][2]+", ";
									if(CutomerData[i][4]!=null)
										fullCusAdd = fullCusAdd +CutomerData[i][4]+", ";
									if(CutomerData[i][7]!=null&&!CutomerData[i][7].equalsIgnoreCase("select"))
										fullCusAdd = fullCusAdd +CutomerData[i][7]+",";
									if(CutomerData[i][8]!=null&&!CutomerData[i][8].equalsIgnoreCase("select"))
										fullCusAdd = fullCusAdd +CutomerData[i][8]+".";
									else
										fullCusAdd = fullCusAdd +"UNITED ARAB EMIRATES"+".";
								%>
                                  <tr class="class="text""> 
								   
                                    <td height="23" align="left" width="25%"><%=CutomerData[i][1]==null?"--":CutomerData[i][1]%></td>
									<td height="23" align="left" width="25%"><%=CutomerData[i][5]==null?"--":CutomerData[i][5]%></td>
                                    <td height="26" align="left" width="25%"><%=fullCusAdd%></td>
									
									<td height="10" align="left" width="25%"><%=CutomerData[i][3]==null?"&nbsp;":CutomerData[i][3]%></td>
                                    

                                    <!--td>&nbsp;</td-->

                                  </tr>

								  <%
									  
								}
								  %>
                                
                                                                   </tr>

								 <%
}
								 else
								 {
								 %>
<tr>
<td align="center"><b>There is No Records In Data Base With this Name</b>
</td>
</tr>

								 <%
									 
								 }
								 %>
                                </table>
                              </td>
                            </tr>
                            <tr> 
                              <td height="5"></td>
                            </tr>
                            <tr> 
                              <td height="5"></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr align="center"> 
                  <td colspan="3">
                    <table width="95%"  border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                        <td height="32" align="center" valign="middle">
                          <a href="CustomerList.jsp"><img src="<%=path%>/images/Cancel.jpg" width="74" height="23"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                          &nbsp;&nbsp;</td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr align="center"> 
                  <td colspan="3">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
<script>
function showAll()
{
//alert('hai');
document.all.checkbox1.checked=true;
document.all.checkbox2.checked=true;
document.all.checkbox3.checked=true;
document.all.checkbox4.checked=true;
document.all.checkbox5.checked=true;
}
</script>
</html>
