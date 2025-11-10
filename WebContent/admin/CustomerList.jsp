<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
%>
<%@ include file="header.jsp" %>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<form name="form1" method="post" action="">

          
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
                  <td width="98%" class="heading"><b>CUSTOMER INFORMATION 
                    </b><strong></strong></td>
                </tr>
                <tr align="center"> 
                  <td colspan="3">&nbsp;</td>
                </tr>
                <tr align="center"> 
                  <td colspan="3">
                    <table width="95%"  border="0" cellspacing="1" cellpadding="0" class="blueborder">
                      <tr> 
                        <td align="left" bgcolor="#FFFFFF"> 
                          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td height="5"></td>
                            </tr>
                           <%
						   String indexvalue="";
						   String requestfrom="";
						   String error="";
						   error=request.getParameter("error")==null?"":(String)request.getParameter("error");
						   
						   
						   %>
                            <tr> 
                              <td align="left" class="ltbgyellow"><div align="center"> <font color="red"><%=error%></font>	</div>
                                <table width="100%" border="0" cellspacing="1" cellpadding="2">
                                  <tr aling="left"> 
                                    <td height="23" align="left" width="24%"><b>Search 
                                      Customers</b> </td>
                                  </tr>
                                  <tr aling="left">
                                    <td height="23" align="center"><table width="468" border="0">
                                      <tr>
                                          <td width="24"><a href="javascript:onclick=index('A')"><b>A</b></a></td>
                                        <td width="32"><a href="javascript:onclick=index('B')"><b>B</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('C')"><b>C</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('D')"><b>D</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('E')"><b>E</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('F')"><b>F</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('G')"><b>G</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('H')"><b>H</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('I')"><b>I</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('J')"><b>J</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('K')"><b>K</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('L')"><b>L</b></a></td>
                                        <td width="22"><a href="javascript:onclick=index('M')"><b>M</b></a></td>
                                        <td width="40"><a href="javascript:onclick=index('N')"><b>N</b></a></td>
                                        <td width="36"><a href="javascript:onclick=index('O')"><b>O</b></a></td>
                                      </tr>
                                      <tr>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="javascript:onclick=index('P')"><b>P</b></a></td>
                                        <td><a href="javascript:onclick=index('Q')"><b>Q</b></a></td>
                                        <td><a href="javascript:onclick=index('R')"><b>R</b></a></td>
                                        <td><a href="javascript:onclick=index('S')"><b>S</b></a></td>
                                        <td><a href="javascript:onclick=index('T')"><b>T</b></a></td>
                                        <td><a href="javascript:onclick=index('U')"><b>U</b></a></td>
                                        <td><a href="javascript:onclick=index('V')"><b>V</b></a></td>
                                        <td><a href="javascript:onclick=index('W')"><b>W</b></a></td>
                                        <td><a href="javascript:onclick=index('X')"><b>X</b></a></td>
                                        <td><a href="javascript:onclick=index('Y')"><b>Y</b></a></td>
                                        <td><a href="javascript:onclick=index('Z')"><b>Z</b></a></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                      </tr>
                                    </table></td>
                                  </tr>
                                  <tr aling="left">
                                    <td height="23" align="left"><table width="447" border="0">
                                      <tr>
                                          <td width="161"><b>Enter Customer Name</b> 
                                          </td>
                                        <td width="82"><label>
                                          <input type="text" name="customername"  tabindex="100">
                                        </label></td>
                                        <td width="190">
                                         <a href=""></a></td>
										 
                                      </tr>
                                    </table></td>
                                  </tr>
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
                       <td height="23" align="center" ><a href="javascript:onclick=index123()"   tabindex="101"><img src="<%=path%>/images/Submit.jpg"></a></td>
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
    <tr> 
      <td colspan="2" class="darkblue">&nbsp;</td>
    </tr>
  </table>
<input type="hidden" name="indexvalue">
<input type="hidden" name="requestfrom" value="cutomerlist">
</form>
</body>
</html>
<script>
function index(indexvalue)
{
	document.form1.indexvalue.value=indexvalue;
	document.form1.requestfrom.value="cutomerlist";
	document.form1.action="viewCustomers.jsp"
	document.form1.submit();
}

function index123()
{
	document.form1.requestfrom.value="cutomerlist";
	document.form1.action="viewCustomers.jsp"
	document.form1.submit();
}
</script>