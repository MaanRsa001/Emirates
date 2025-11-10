<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>
<%@ include file="submenu.jsp"%>
<jsp:useBean id="newCover" class="com.maan.opencover.bean.newCoverBean">
<jsp:setProperty name="newCover" property="*"/>
</jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String branchCode=session.getAttribute("AdminBranchCode")==null?"":(String)session.getAttribute("AdminBranchCode");
//Belonging Branch
String belongingBranch = session.getAttribute("BelongingBranch")==null?"":(String)session.getAttribute("BelongingBranch");
branchCode=branchCode.replaceAll("'","");
String saleTermIds=request.getParameter("saleTermIds")==null?"":request.getParameter("saleTermIds");
String toleranceList[][]=new PremiumInputsBean().getToleranceDetails(belongingBranch);
String saleTermList[][]=newCover.getSaleTermList(belongingBranch, saleTermIds);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Tolerance Selection</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 	<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
 	<link href="<%=request.getContextPath() %>/css/footable-0.1.css" rel="stylesheet" type="text/css">
  </head>
  
  <body onload="getValues()">
	  <form action="" name="tolerance" >
		   <table width="40%" align="center" >
			   <tr><td colspan="2" class="heading" align="center">Tolerance Selection</td></tr>
			   <tr><td colspan="2">&nbsp;</td></tr>
		   </table>
		   <div  STYLE="overflow: auto; width:50%; height:65vh; border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 0 auto;">
		   <table align="center" border="1" cellspacing="2" cellpadding="0" class="footable">
		   <tbody>
		   <%if(saleTermList!=null && saleTermList.length>0){
		   		for(int i=0; i<saleTermList.length; i++){
		   %>
		   <tr >
			<td width="50%">
		   		<input type="checkbox" name="saleTerm<%=saleTermList[i][0]%>" value="<%=saleTermList[i][0]%>" title="<%=saleTermList[i][1]%>" id="saleTerm<%=saleTermList[i][0]%>"/><%=saleTermList[i][1]%>
			</td>
			<td width="50%">
				<select name='tolerance<%=saleTermList[i][0]%>' class='inputSelect' id='tolerance<%=saleTermList[i][0]%>'>
				<%for (int j = 0; j < toleranceList.length; j++) {%>
				<option value=<%=toleranceList[j][0]%> <%="4".equalsIgnoreCase(toleranceList[j][0])?"selected":""%>>
				<%=toleranceList[j][1]%>
				</option>
				<%}%>
				</select>
			</td>
		   </tr>
		   <%}} %>
		   </tbody>
		   </table>
		   </div>
		   <table width="40%"  border="0" cellspacing="0" cellpadding="0" align="center">
		   	<tr><td>&nbsp;</td></tr>
			<tr align="center">
				  <td height="32" align="center" valign="middle" >  
						<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr> 
				 <td align="center" class="">
				 <a href="#" onClick="window.close()" >
				 <img src="<%=request.getContextPath() %>/images/Back.jpg" ></a> </td>
				<td>&nbsp;<input type="hidden" name="identify" value="<%=request.getParameter("identify")%>"/></td>
						 <td align="center" class="">
						 <a href="#" onClick="return setValues()"  >
						 <img src="<%=request.getContextPath() %>/images/Submit.jpg"></a> </td>
					</table>
						&nbsp;&nbsp;&nbsp;
				  </td>
			</tr>
		</table>
	</form>
  </body>
  <script language="javascript">
function setValues()
{
	try
	{
		var elems=document.forms[0].elements;
		var basisVal='';
		var basisValIds='';
		var tolObj='';
		var tolPercent;
		var checkedCount=0;
		for(var i=0; i<elems.length; i++)
		{
			var obj=elems[i];
			if(obj.type=='checkbox' && obj.checked)
			{
				tolObj=document.getElementById('tolerance'+obj.value);
				tolPercent=tolObj.options[tolObj.selectedIndex].text;
				tolPercent=tolPercent=='NONE'?'':('+'+tolPercent);
				basisVal+=obj.title+tolPercent+', ';
				basisValIds+=obj.value+'~'+tolObj.value+',';
				checkedCount++;
			}
		}
		if(checkedCount!=0)
		{
			basisVal=basisVal.substring(0, basisVal.length-2); 
			basisValIds=basisValIds.substring(0, basisValIds.length-1);
			window.opener.document.Quotation.tolerance.value=basisVal;
			window.opener.document.Quotation.toleranceId.value=basisValIds; 
			window.close();
		}else
		{
			alert('Please select atleast one item');
		}
	}catch(e)
	{
		alert(e);
	}
	return false;
}
function getValues()
{
	try
	{
		var basisValIds=window.opener.document.Quotation.toleranceId.value;
		if(basisValIds.length>0)
		{
			var ids=basisValIds.split(',');
			for(var i=0; i<ids.length; i++)
			{
				var id=ids[i].split('~');
				var obj1=document.getElementById('saleTerm'+id[0]);
				var obj2=document.getElementById('tolerance'+id[0]);
				if(obj1)
					obj1.checked=true;
				if(obj2)	
					obj2.value=id[1];
			}
		}
	}catch(e)
	{
		alert(e);
	}
	return false;
}
</script>
</html>
