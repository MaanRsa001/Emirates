
<%@ page import="java.util.*" %>
<%!
String n=null;
String coverId=null;
	%>
<%
n=request.getParameter("n")==null?"0":request.getParameter("n");

if("new".equalsIgnoreCase(request.getParameter("input")))
	n=""+(Integer.parseInt(n)+1);
coverId="1";
if("BASIC COVER".equalsIgnoreCase(request.getParameter("coverName")))
	coverId="7";
else if("ALL RISK".equalsIgnoreCase(request.getParameter("coverName")))
	coverId="6";
else if("icc_nondelivery".equalsIgnoreCase(request.getParameter("coverName")))
	coverId="3";
else if("icc_c".equalsIgnoreCase(request.getParameter("coverName")))
	coverId="2";
else if("icc_a".equalsIgnoreCase(request.getParameter("coverName")))
	coverId="1";
else if("TOTAL LOSS".equalsIgnoreCase(request.getParameter("coverName")))
	coverId="5";
else if("icc_air".equalsIgnoreCase(request.getParameter("coverName")))
	coverId="4";
String[][] commodities=null;
commodities=com.maan.services.util.runner.multipleSelection("select clauses_id,clauses_description from clauses_master where status='Y' and cover_id='"+coverId+"'");

%>


<html>
<script>

</script>
    <head>
        <title>** Emirates - Marine Insurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="/css/style.css" rel="stylesheet" type="text/css">
        <style type="text/css">
            <!--
#Layer1 {
	position:absolute;
	width:200px;
	height:115px;
	z-index:1;
	left: 128px;
	top: 284px;
}
            -->
        </style>
    </head>
    <body>
     <form name="commodity" method="post">
	<br>
           <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="dkbgyellow">&nbsp;</td>
              <td class="bordercolor">&nbsp;<strong> Clauses && Warrenties DETAILS </strong></td>
            </tr>
				 <tr>
                <td height="17" ></td>
              <td ></td>
            </tr>		
			<tr >
			<td colspan="2"> 
			<table width="95%"  border="1" cellspacing="1" cellpadding="0" class="blueborder" align='center'>
              <tr>
			    <td align="left" class="ltbgyellow">
			    <div  STYLE="overflow: auto; width:100%; height:350; border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 1px">
                          <table width="100%"  border="0" cellspacing="0" cellpadding="0" >
                            
						   <tr align="left">
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           </td>
						   <td></td>
                            </tr>
						<%
	for(int i=0;i<commodities.length;i++)
	{%>
							<tr>
								<td align="left"><input type="checkbox" name="clausesId<%=commodities[i][0]%>"></td>
								<td align="center"><textarea name="ss" rows="3" cols="70"><%=commodities[i][1]%></textarea></td>
							</tr>
	<%}
	for(int i=0;i<Integer.parseInt(n);i++)
	{%>	
		<tr>
								<td align="left"><input type="checkbox" name="extraClauses<%=n%>"></td>
								<td align="center"><textarea name="ss" rows="3" cols="70"></textarea></td>
							</tr>
	<%}%>
		</table>
		</div>
		</td></tr>
		<tr>
			
			<td height="32" align="right" valign="middle" class="medblue">  
				<input type="hidden" name="totalLength" value="<%=commodities.length%>">

				<input type="image"  src="../images/btn_cancel.gif"   onClick="window.close()" tabindex=0 accesskey='c'>	
			<!--	<input type="button"  value="ADD CLAUSES"   onClick="return addText()">	-->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="image"  src="../images/button_submit.gif"   onClick="window.close()" tabindex=0 accesskey='p'>	
				
				&nbsp;&nbsp;&nbsp;</td>
              </tr>
            </table>
			  </td>
            </tr>
            <tr>
              <td colspan="2" class="darkblue">&nbsp;</td>
			  <input type="hidden" name="coverName" value="<%=request.getParameter("coverName")%>">
			  <input type="hidden" name="n" value="<%=n%>">
            </tr>
          </table>
        </form>
    </body>
</html>

<script>
function addText()
{
	
	
	document.commodity.action="showClausesEdit.jsp?input=new";
	document.commodity.submit();
	return true;
}
				
</script>