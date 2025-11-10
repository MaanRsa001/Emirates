<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="header.jsp" %>
<%
	String path = request.getContextPath();
%>
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
</head>

<body>
<form name="form1" method="get" action="">

		<table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="left.jsp" --%>
		
            </tr>
        </table></td>
        <td width="1"></td>
        <td align="left" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="8" height="25" class="heading">&nbsp;</td>
            <td width="1"></td>
                  <td width="98%" class="heading"><strong>ADMIN/RSA USERS MANAGEMENT</strong></td>
          </tr>
          <tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr align="center">
            <td colspan="3"><table width="95%"  border="0" cellspacing="0" cellpadding="0" class="blueborder">
              <tr>
                <td align="center" class="ltbgyellow">&nbsp;</td>
                      </tr>
              <tr>
                <td align="center" class="ltbgyellow">&nbsp;</td>
              </tr>
			  <% 
			  String status=(String)request.getAttribute("status")==null?"":(String)request.getAttribute("status");
			  //out.println("status iss>>>>>>>>>>>>"+status);
			  String statusMessage="";

			
			  String deActivatedDate="";
			  if(request.getAttribute("deActivatedDate")!=null)
			  {
				deActivatedDate=(String)request.getAttribute("deActivatedDate");
			  }
				else
				{
					deActivatedDate="Problem in getting the date";
				}
		
			    if(status.equalsIgnoreCase("Y"))
			  {
				statusMessage="This Admin is Activated ";
				deActivatedDate="";
			  }
			  else
			  {
				statusMessage="This Admin is  DeActivated on";

			  }
			  String rsaissuer=request.getParameter("rsaissuer")==null?"":request.getParameter("rsaissuer");
			  String paths="";
			if(rsaissuer.equalsIgnoreCase("rsaissuer"))
				paths="RSAIssuerusers1.jsp";
			else
				paths = "RSAusers1.jsp";
			  %>
              <tr>
                <td align="center" class="ltbgyellow"><B><%=statusMessage %> <%=deActivatedDate%> &nbsp;&nbsp;&nbsp;</B></td>
              </tr>
              <tr>
                <td align="center" class="ltbgyellow">&nbsp;</td>
              </tr>
                  <tr align="center">
            <td colspan="3" class="medblue"><table width="95%"  border="0" cellspacing="0" cellpadding="0">
              <tr class="medblue">
                        <td height="32" align="right" valign="middle" class="medblue"> 
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                          <!--<a href="ViewQuote_B2C.html"><img src="images/save.gif" width="89" height="23"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                           <a href="<%=paths%>"><img src="<%=path%>/images/Proceed.jpg"></a>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
                        </td>
              </tr>
            </table></td>
          </tr>       
                          </table>
                  </td>
              </tr>
            </table></td>
            </tr>
          <tr align="center">
            <td height="1" colspan="3"></td>
          </tr>
         
        </table></td>
      </tr>
    </table></td>
  </tr>
 </table>

</form>
</body>
</html>
