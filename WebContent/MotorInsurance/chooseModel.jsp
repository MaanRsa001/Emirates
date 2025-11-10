<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*" %>
 <jsp:useBean id="motor" class="com.maan.admin.DAO.MotorBodyCreation">
 </jsp:useBean>
 <html>
 <head>
 <title>Model selection</title>
<head>
<title>XYZ - Insurance</title>
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
<jsp:useBean id="broker" class="com.maan.admin.DAO.MotorBodyCreation"/>
<body>


<%  String[][] model = new String[0][0];
    String branchCode=(String)session.getAttribute("LoginBranchCode");
	model = motor.getModel((String)request.getParameter("makeId"),branchCode,(String)request.getParameter("typeID"));
	String modelids=(String)request.getParameter("modelId")==null?"":(String)request.getParameter("modelId");
	List lt=new ArrayList();
	if(modelids.length()!=0)
	{
	 StringTokenizer st=new StringTokenizer(modelids,"-");
	 while(st.hasMoreTokens())
	 {
	 lt.add(st.nextToken());
	 }
	}
	
	%>	
<form name="model">

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
	<td>
        <table width="100%" border="0" cellspacing="0" cellpadding="1" align="center">
          <tr> 
            <td> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;" >
               <tr>
                <%if(model.length==0){ %>
                   <td><font color="red" size="2"><center>No Models Exist For This Make</center></font></td>
                <%}%></br></br>                
                </tr>
               
               <tr> </tr>
                <tr><td class="st" height="16" align="center" class="formtxtf"><Strong>Select The Model</Strong></td></tr>
              </table>
              
              </br> </br>
              <table width="100%" border="0" cellspacing="0" cellpadding="0" style="width:180px" align="center" >
                <tr align="center">
                <td colspan="2" align="center">
                <%
               
                 String check = "";
                 //out.println("selList  "+selList);
                for(int i=0;i<model.length;i++){
                 
                   check = "";
                   if(lt.contains(model[i][0]))
                   {
                   
                   check = "checked";
                   }
                   %>
               
                
                <tr align="left" >
                 <td  nowrap = "nowrap"><input type="checkbox" name="model" value= "<%=model[i][1]+","+model[i][0]%>" <%=check %> /><strong> <%=model[i][1]%></strong></td></tr>
                <%} 
                %>
               
                 </td>  
                </tr>
               
               
				
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
  </tr>
</table>

 
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr align="center">
				  <td height="32" align="center" valign="middle">  
						
						<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr> 
				 <td align="center" class="button-left">
				 <a href="#" class="buttonsMenu" >
				 <img src="../images/Back.jpg" onClick="window.close()"> </td>&nbsp;&nbsp;&nbsp;
				 <%if(model.length>0){ %>
				<td align="center" class="button-left">
						 <a href="#" onClick="proceedaction()" class="buttonsMenu" >
						 <img src="../images/Submit.jpg"></a> </td> <% }%>
					</table>
						&nbsp;&nbsp;&nbsp;
				  </td>
				  
			</tr>
</table>

</form>

<script type="text/javascript">
function proceedaction()
{
var totalids="";
var totalmodels="";
var val="";
for(var i=0; i < document.model.model.length; i++){
	if(document.model.model[i].checked)
	{
		val =document.model.model[i].value;
		totalmodels=totalmodels+","+(val.substr(0,val.indexOf(',')));
		totalids=totalids+"-"+(val.substr(val.indexOf(',')+1,val.length));
	}
}
totalmodels=totalmodels.substr(1,totalmodels.length);
totalids=totalids.substr(1,totalids.length);

if(totalids==""){
	alert("select anyone");
	return false;
}
//alert('Check '+totalids);
//alert("previous value "+window.opener.document.form1.bodyid.value);
window.opener.document.form1.modelid.value=totalids;
window.opener.document.form1.modelname.value=totalmodels;
window.close();
}
</script>
</body>
</html>

