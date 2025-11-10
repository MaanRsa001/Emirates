<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="application.name"/></title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">javascript:window.history.forward(1);</script>
<script type="text/javascript" src="javascript/tcal.js"></script>
	<script type="text/javascript">
		 function callSubmit(val){
           	if(val=='back'){
           		document.changepwd.action="Loginpage.action";
           		document.getElementById("status").value="";
           	}else if(val=='submit'){
           		document.changepwd.action="${pageContext.request.contextPath}/LoginpwdForgot.action";
           	}
              document.changepwd.submit();
     	}
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
			<s:form id="changepwd" name="changepwd" method="post" action="" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
              	<td bgcolor="#FFFFFF"><h1><s:text name="application.name"/></h1></td>
              </tr>
              <tr><td>&nbsp;</td> </tr>
              <tr><td>&nbsp;</td> </tr>
              <tr>
                <td bgcolor="#FFFFFF">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
                		<tr>
							<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
                		</tr>
                		<tr>
		                    <td class="heading"><s:text name="forgot.password"/></td>
		                </tr>
                		<s:if test='"success"==status'>
                			<tr><td height="100px;">&nbsp;</td></tr>
			                <tr>
			                   <td align="center" style="padding-right:20px;"> <s:text name="forgot.pass.success"/></td>
			                </tr>
			                <tr><td height="100px;">&nbsp;</td></tr>
                		</s:if>
                		<s:else>
		                    <tr><td style="padding:10px; background:#F8F8F8">&nbsp;</td></tr>
		                    <tr>	                                                 
		                      <td  bgcolor="">
		                      	<table width="" border="0" align="center" cellpadding="0" cellspacing="0" style="border: 1px solid #808080; background-color: #F0F0F0;" >
		                      	  <tr><td colspan="2">&nbsp;</td></tr>
		                      	  <tr><td colspan="2" class="headingtext"><s:text name="forgot.password.reset" /></td></tr>
		                      	  <tr><td colspan="2">&nbsp;</td></tr>
			                      <tr align="center">
		                          		
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="user.name" /> <font color="red">*</font> : </td>
	                            		
	                            		<td width="300px" align="left" style="padding-left: 10px;"><s:textfield name="loginId" id="userId" cssClass="input"/></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr align="center">
		                          		
		                          		<td width="200px" align="right" style="padding-right: 10px;"><s:text name="forgot.email" /> <font color="red">*</font> : </td>
	                            		
	                            		<td width="300px" align="left" style="padding-left: 10px;"><s:textfield name="mailId" id="mailId" cssClass="input" /></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr><td>&nbsp;</td></tr>
			                     </table>
			                   </td>
			                </tr>
			             </s:else>
			             <s:hidden name="status" id="status"/>
		              </table>
		            </td>
                   </tr>
                   <tr>
	                  <td>
                   		<table align="center">
	                   		<s:if test='"success"==status'>
	                   			<tr>
	                            	<td align="center">
	                            		<s:hidden name="home"/>
	                              		<input type="button"  name="Submit1" style="cursor:pointer;" value=" Back " onclick="callSubmit('back')" class="btn" style="cursor:pointer;" />
	                            	</td>
	                       	 	</tr>
	                   		</s:if>
	                   		<s:else>
	                   			<tr>
	                            	<td align="center">
	                            		<s:hidden name="home"/>
	                            		<s:submit name="Submit1" cssStyle="cursor:pointer;" cssClass="btn" value=" Cancel " onclick="callSubmit('back')"/>&nbsp;&nbsp;&nbsp;
	                              		<s:reset name="Submit2" cssStyle="cursor:pointer;" value=" Reset " cssClass="btn"/>&nbsp;&nbsp;&nbsp;
	                              		<s:submit name="Submit1" cssStyle="cursor:pointer;" cssClass="btn" value=" Submit " onclick="callSubmit('submit')"/>	                              		
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