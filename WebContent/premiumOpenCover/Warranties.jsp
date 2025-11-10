<%@ include file="submenu.jsp"%>
<%@ page import="java.util.*" %>
<%!
int i=0;
String wsrcc =null;
String proposalNo = null;
%>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<jsp:useBean id="theBean" class="com.maan.opencover.bean.opencoverSummary">
<jsp:setProperty name="theBean" property="*"/>
</jsp:useBean>
<%

wsrcc = (String)session.getAttribute("coverName");
//	out.println("session value is"+wsrcc);
int sea=0, air=0,road = 0;
String sb="";

proposalNo = request.getParameter("proposalNo")==null?(String)
session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");

int n = 1;

%>
<%

air = wsrcc.lastIndexOf("AIR");
road = wsrcc.lastIndexOf("ROAD");
sea = wsrcc.lastIndexOf("SEA");
if(air>0)
  {
    air=2;
  }
 else
   {
   air = 101;
   }
if(sea>0)
  {
    sea = 1;
  }
  else
    {
      sea = 101;
      }
if(road>0)
  {
  road = 0;
 
  
  }
  String adminBranch = (String) session.getAttribute("AdminBranchCode");
		if(adminBranch.indexOf("'")!=-1)
			adminBranch = adminBranch.replaceAll("'","");
		String cid = (String) session.getAttribute("AdminCountryId");
  String[][] commodities= theBean.getWSRCC(proposalNo);

%>
<html>
<script>

</script>
    <head>
        <title>** Emirates - Marine Insurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="../css/style.css" rel="stylesheet" type="text/css">
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
   	

     <form name="wsrcc" method="post">
   
	

	
	<br>
           <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align = "center">&nbsp;</td>
              <td>&nbsp;<strong><span class="heading"> Warranties Details</span></strong></td>
            </tr>
				 <tr>
                <td height="17" ></td>
              <td ></td>
            </tr>		
			<tr >
			<td colspan="2"> 
			<table width="95%"  border="1" cellspacing="1" cellpadding="0" class="" align='center'>
              <tr>		
			    <td align="left" class="ltbgyellow">
			    <div  STYLE="overflow: auto; width:100%; height:450; border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 1px">
                          <table width="100%"  border="0" cellspacing="0" cellpadding="0" >
                            
						   <tr align="left">
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           </td>
						   <td></td>
                            </tr>
						<%
						if(commodities.length > 0)
						  {
						
	for(int i=0;i<commodities.length;i++)
	{%>
							<tr>
								
								<td align="Left" valign = "top" height = "40" class="" ><font size = '2'><%=n%></td><td valign = "top" height = "40"><font size = '2'> <%=commodities[i][1]%></td>
															</tr>
	<%
	n++;}
	}
	else
	  {%>
	  <td align="Left" valign = "top" height = "40" ><font size = '1'></td><td valign = "top" height = "40"><font size = '2' color = 'red'><b> No warranties for Commodity<b></td>
	 <% }
	  
	
	%>
		</table>
		</div>
		</td></tr>
		<tr>
			
			<td height="32" align="right" valign="middle" class="">  
				<input type="hidden" name="totalLength" value="<%=commodities.length%>">

				<!--<input type="image"  src="../images/btn_cancel.gif"   onClick="window.close()" tabindex=0 accesskey='c'>	-->
			<!--	<input type="button"  value="ADD CLAUSES"   onClick="return addText()">	-->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!--<input type="image"  src="../images/button_submit.gif"   onClick="window.close()" tabindex=0 accesskey='p'>	
				
				&nbsp;&nbsp;&nbsp;</td>
              </tr>-->
              <br>
	      				  <table width="176" border="0" align="center" cellpadding="0" cellspacing="0">
	      						  						
	      			 <td width="46" valign="middle">
					 <a href= "#" onClick='window.close()' class="buttonsMenu" >
					 <img src="<%=pathq%>/images/Close.jpg" >			</td>
	      						  					
	      	
	      						  								
	      		                          </table>
							
            </table>
			  </td>
            </tr>
            <tr>
              <td colspan="2" >&nbsp;</td>
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
	
	
	document.wsrcc.action="showClausesEdit.jsp?input=new";
	document.wsrcc.submit();
	return true;
}
				
</script>
