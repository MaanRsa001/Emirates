<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>
<%@ include file="submenu.jsp"%>
<jsp:useBean id="newCover" class="com.maan.opencover.bean.openCoverQuotation">
<jsp:setProperty name="newCover" property="*"/>
</jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String branchCode=session.getAttribute("adminBranch")==null?"":(String)session.getAttribute("adminBranch");
//Belonging Branch
String belongingBranch = session.getAttribute("BelongingBranch")==null?"":(String) session.getAttribute("BelongingBranch");
branchCode=branchCode.replaceAll("'","");
String coverTypeName=request.getParameter("coverTypeName")==null?"":request.getParameter("coverTypeName");
String coverTypeIds=request.getParameter("coverTypeId")==null?"":request.getParameter("coverTypeId");
String modeOfTransport=request.getParameter("modeOfTransport")==null?"":request.getParameter("modeOfTransport");
String list[][]=new PremiumInputsBean().getSeaCoverValues(belongingBranch);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>Cover Type Selection</title>   
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
<body onload="return getValues('<%=coverTypeName%>')">
<form action="" name="tolerance" >
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<span class="heading">Conveyance Type Selection</span>
		</td>
	</tr>
</table>
<div style="width: 95%; margin: 0 auto;">
<table width="100%" class="footable">
	<tbody>
	<%	if(list!=null && list.length>0){
			for(int i=0; i<list.length; i++){
				if(modeOfTransport.equalsIgnoreCase(list[i][0])){
	%>
	<tr>
		<td>
			<input type="checkbox" name="coverType<%=list[i][1]%>" value="<%=list[i][1]%>" title="<%=list[i][2]%>" id="coverType<%=list[i][1]%>"/><%=list[i][2]%>
		</td>
	</tr>
	<%}}}%>
	</tbody>
</table>
</div>
<div style="margin-top: 10px;" align="center">
	 <a onClick="window.close()" style="cursor: pointer;"> <img src="<%=request.getContextPath() %>/images/Back.jpg" ></a>
	 &nbsp;&nbsp;&nbsp;<input type="hidden" name="identify" value="<%=request.getParameter("identify")%>"/>
	<a style="cursor: pointer;" onClick="return setValues('<%=coverTypeName%>')"  ><img src="<%=request.getContextPath() %>/images/Submit.jpg"></a>
</div>  
</form>
</body>
<script language="javascript">
function setValues(coverTypeId)
{
	try
	{
		var elems=document.forms[0].elements;
		var ids='';
		var checkedCount=0;
		for(var i=0; i<elems.length; i++)
		{
			var obj=elems[i];
			if(obj.type=='checkbox' && obj.checked)
			{
				ids+=obj.value+',';
				//checkedCount++;
			}
		}
		if(ids.length>0)
			ids=ids.substring(0, ids.length-1);
		window.opener.document.getElementById(coverTypeId).value=ids;
		window.close();
	}catch(e)
	{
		alert(e);
	}
	return false;
}
function getValues(obj)
{
	try
	{
		var coverTypeId=window.opener.document.getElementById(obj).value;
		if(coverTypeId.length>0)
		{
			var ids=coverTypeId.split(',');
			for(var i=0; i<ids.length; i++)
			{
				var obj1=document.getElementById('coverType'+ids[i]);
				if(obj1)
					obj1.checked=true;
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
