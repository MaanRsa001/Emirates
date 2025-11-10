<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="admin.new.user"/></title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common/tcal.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
<script language="JavaScript">javascript:window.history.forward(1);</script>
	<script type="text/javascript">
	function forward(url){
		document.quotation.action = "${pageContext.request.contextPath}/"+url;
		document.quotation.submit();
	}
	
	function LoadFromApp(value){
    	var params = "reqFrom=LoadFromApp&appId="+value;
    	getAjaxContentPost("${pageContext.request.contextPath}/adminajaxValue.do", "DynamicList", params);
    	document.getElementById("DynamicMailLists").style.display = "block";
    }
    
     <s:if test='appId!=null'>
   		 var app='<s:property value="appId"/>';
   		 var params = "reqFrom=LoadFromApp&appId="+app;
   		 getAjaxContentPost("${pageContext.request.contextPath}/adminajaxValue.do", "DynamicList", params);
    	 document.getElementById("DynamicMailLists").style.display = "block";
    </s:if>
	</script>
</head>

<body>
<table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
      <tr>
        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			<s:form id="mailList" name="mailList" method="post" action="" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td bgcolor="#FFFFFF">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
                		<tr>
							<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
                		</tr>
                		<s:if test='"success"==display'>
                			<tr><td>&nbsp;</td></tr>
			                <tr>
			                   <td align="center" style="padding-right:20px;"> <s:text name="mail.master.modify"/> </td>
			                </tr>
			                <tr><td>&nbsp;</td></tr>
                		</s:if>
                		<s:else>
				            <tr>
		                      <td class="heading"><s:text name="mail.master"/></td>
		                    </tr>
		                    <tr><td style="padding:10px; background:#F8F8F8">&nbsp;</td></tr>
		                    <tr>	                                                 
		                      <td  bgcolor="#FFFFFF">
		                      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		                      	  <tr><td>&nbsp;</td></tr>
		                      	  <tr><td>&nbsp;</td></tr>
		                      	   <tr><td id="DynamicList" style="display: ;"><table>
		                      	  <tr align="center">
		                          		<td width="5%"> &nbsp;</td>
		                          		<td width="5%" align="right"><s:text name="application" /> <font color="red">*</font> : </td>
	                            		<td width="2%">&nbsp;</td>
	                            		<td width="15%" align="left"><s:select name="appId" id="appId" list="applList" listKey="app_id" listValue="app_name" headerKey="" headerValue="-Select-" cssClass="input" onchange="LoadFromApp(this.value)"/></td>
			                      </tr>
		                      	  <tr><td>&nbsp;</td></tr>
		                      	 <div id="DynamicMailLists" style="display: none;">
			                      	  <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="mail.mailcc" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:textfield name="mailcc" cssClass="input" /></td>
				                      </tr>
				                      <tr><td>&nbsp;</td></tr>
				                      <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="mail.smtphost" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:textfield name="smtphost" cssClass="input" /></td>
				                      </tr>
				                      <tr><td>&nbsp;</td></tr>
				                     <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="mail.smtpuser" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:textfield name="smtpuser" cssClass="input" /> </td>
				                      </tr>
				                      <tr><td> &nbsp;</td></tr>
				                      <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="mail.smtppwd" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:textfield name="smtppwd" cssClass="input" /> </td>
				                      </tr>
				                      <tr><td>&nbsp;</td></tr>
				                      <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="mail.tmp.pwdexp" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:textfield name="pwdexp" cssClass="input" /> </td>
				                      </tr>
		                      	  </div></table></td></tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr><td>&nbsp;</td></tr>
			                     </table>
			                   </td>
			                </tr>
			             </s:else>
		              </table>
		            </td>
                   </tr>
                   <tr>
	                  <td>
                   		<table align="center">
	                   		<s:if test='"success"==display'>
	                   			<tr>
	                            	<td align="center">
	                              		<s:submit name="Submit1" cssClass="btn" cssStyle="cursor:pointer;" value=" Proceed " action="adminmailMaster" />
	                            	</td>
	                       	 	</tr>
	                   		</s:if>
	                   		<s:else>
	                   			<tr>
	                            	<td align="center">
	                              		<s:submit name="Submit1" cssClass="btn" cssStyle="cursor:pointer;" value=" Back " action="adminhome" />
	                              		<s:submit name="Submit2"  cssClass="btn" cssStyle="cursor:pointer;" value=" Submit " action="adminmailSave"/>
	                            	</td>
	                       	 	</tr>
	                       	 </s:else>
                   		</table>
	                   	</td>
                    </tr>
        		 </table>
    		</s:form>
    	</td>
    	</tr>
    </table>
    </td></tr>
    </table>
</body>
</html>
