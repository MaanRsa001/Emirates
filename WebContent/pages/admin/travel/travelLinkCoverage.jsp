<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Form</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/datepicker/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/datepicker/core.js"></script>
<script type="text/javascript" src="js/datepicker/widget.js"></script>
<script type="text/javascript" src="js/datepicker/datepicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script language="JavaScript">javascript:window.history.forward(1);</script>
<script type="text/javascript" src="pages/admin/travel/menu.js"></script>
</head>
	<body>
	<div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:1000px;">
    <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <%@ include file="/pages/admin/travel/travelRatingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Link Coverage" style="padding:5px">
<table width="900" border="0" align="center" cellpadding="0" cellspacing="0"> 
  <tr>
    <td height="5"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
      <tr>
        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
        <s:form id="form1" name="form1" method="post" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr><td>&nbsp;</td></tr>
            <tr>
	           <td class="heading" colspan="2"><s:label key="Travel Link Coverage" /></td>
	        </tr>
            <tr>
	          <td  style="color:red;"><s:actionerror/></td>
	        </tr>
	        <tr><td>&nbsp;</td></tr>
	        <s:if test ="display==null or display == ''">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	           <tr>
				  <td><s:text name="ChooseProduct" /><font color="red">*</font>
				  <s:select name="productId" id="productId" list="#{'33':'Travel Insurance Walla'}" headerKey="" headerValue="-Select-" cssClass="input" onchange="fnScheme(this.value)"/></td>
			   </tr>
			   <tr>
                  <td><div id="schemeSelect"/></td>
	  		   </tr>
			   <tr>
                  <td><div id="optionCover"/></td>
	  		   </tr>
	  		  </table>
			</s:if>
            </table>
        </s:form></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<script type="text/javascript">
function fnsubmit(frm,from,val)
{
if(from == 'edit'){
document.form1.action = "${pageContext.request.contextPath}/linkcoverageTravelConfigure.action?optionId="+val+'&display='+from;
}else{
document.form1.action = "${pageContext.request.contextPath}/linkcoverageTravelConfigure.action?display="+from;
}
document.form1.submit();
}
function fnScheme(val){
	postRequest('${pageContext.request.contextPath}/linkcoverageTravelConfigure.action?reqFrom=coverage&schemeId='+val, 'schemeSelect');
}
function fnOption(val){
	postRequest('${pageContext.request.contextPath}/linkcoverageTravelConfigure.action?reqFrom=cover&schemeId='+val, 'optionCover');
}
</script>
</div>
</div>
</div>
</div>
</body>
</html>