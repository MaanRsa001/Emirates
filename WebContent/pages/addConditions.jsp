<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        
       	<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
    </head>
<body >
  <s:form name="conditions" theme="simple">
  	<table align="center" width="90%" border="0">
	  	<tr class="heading">
	  		<td colspan="2" align="center"><s:text name="%{'conditions'+conditionType}"></s:text></td>
	  	</tr>
  		<tr >
	  		<td colspan="2" align="center">&nbsp;</td>
	  	</tr>
	  	<s:if test="conditions.size > 0">
	  		<s:iterator value="conditions" var="conditons">
				<tr class="bg" >
					<td width="10%" align="center"><s:checkbox name="%{#conditons.CODE}" id="%{#conditons.CODE}" fieldValue="%{#conditons.CODE}" value="false"/> </td>
					<td width="90%"><s:textarea name="condition%{#conditons.CODE}" id="condition%{#conditons.CODE}" value="%{#conditons.CODE_DESC}" cols="90" rows="2" /></td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="2" align="center">
					<input type="image" src="${pageContext.request.contextPath}/images/Cancel.jpg"   name="Image4" id="Image4" border="0" onclick="javascript:window.close();">
					<a href="#" onClick="return Submit('<s:property value="%{conditionType}"/>')" class="buttonsMenu" >
		 			<img src="${pageContext.request.contextPath}/images/Submit.jpg"> </a>
				</td>
			</tr>
	  	</s:if>
	  	<s:else>
	  		<tr >
		  		<td colspan="2" align="center">Nothing found to display</td>
		  	</tr>
	  	</s:else>
	</table>
  </s:form>
  <script>
  function Submit(type)
  {
  	var elements=document.conditions.elements;
  	var conditions='';
  	for (var int = 0; int < elements.length; int++) 
	{
		var obj=elements[int];
		if(obj.type=='checkbox' && obj.checked)
		{
			conditions+='#'+obj.value+'~'+document.getElementById('condition'+obj.name).value;
		}
	}
	if(type=='1'){
		window.opener.premiumInfo.addClauses.value=conditions;
	}else if(type=='2'){
		window.opener.premiumInfo.addWarClauses.value=conditions;
	}else if(type=='3'){
		window.opener.premiumInfo.addExclusions.value=conditions;
	}else if(type=='4'){
		window.opener.premiumInfo.addWarranties.value=conditions;
	}
	window.close();
 	return false;
  }
  setValues('<s:property value="%{conditionType}"/>');
  function setValues(type)
  {
  		try{
  		var obj;
  		if(type=='1'){
			obj=window.opener.premiumInfo.addClauses;
		}else if(type=='2'){
			obj=window.opener.premiumInfo.addWarClauses;
		}else if(type=='3'){
			obj=window.opener.premiumInfo.addExclusions;
		}else if(type=='4'){
			obj=window.opener.premiumInfo.addWarranties;
		}
		if(obj && obj.value.length>0){
			var conditions=obj.value;
			if(conditions.indexOf('#')!=-1){
				var array=conditions.split('#');
				for ( var int = 0; int < array.length; int++) {
					var condition=array[int].split('~');
					var obj2=document.getElementById('condition'+condition[0]);
					if(obj2){
						document.getElementById(condition[0]).checked=true;
						obj2.value=condition[1];
					}
				}
			}else{
				var condition=obj.value.split('~');
				var obj2=document.getElementById('condition'+condition[0]);
				if(obj2){
					document.getElementById(condition[0]).checked=true;
					obj2.value=condition[1];
				}
			}
		}
		}catch(e){
		 alert("Error: " + e.name+ ". Error description: " + e.description+ ". Error number: " + e.number);	
	}
  }
  </script>
  </body>
</html>
