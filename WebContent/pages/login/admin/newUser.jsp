<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>	<s:if test="'edit'.equals(reqFrom)"><s:text name="admin.upd.user"/></s:if>
				<s:else><s:text name="admin.new.user"/></s:else>
		</title>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link href="css/tcal.css" rel="stylesheet" type="text/css" />
		<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="javascript/tcal.js"></script>
		<link rel="stylesheet" href="css/jquery-ui.css" />
		<script src="js/common/jquery-1.9.1.js"></script>
		<script src="js/common/jquery-ui.js"></script>

		<script type="text/javascript">
		 $(function() {
   			$( "#datepicker" ).datepicker({
   				showOn: "button",
				buttonImage: "images/cal.gif",
				buttonImageOnly: true,
     		 	changeMonth: true,
      			changeYear: true,
      			dateFormat : "dd/mm/yy"
    		});
  		});
	
      function AppSelection(){	 
		var URL='${pageContext.request.contextPath}/admingetappList.do';	    
		var windowName = "Selection";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 520;
		var h = 350;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 0) * .4)  +
			',top='			  + ((height - h - 0) * .4) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=yes'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
	}
	
	function forward(url){
		document.appList.action = "${pageContext.request.contextPath}/"+url;
		document.appList.submit();
		return false;
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
			<s:form id="appList" name="appList" method="post" action="" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td bgcolor="#FFFFFF">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
                		<tr>
							<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
                		</tr>
                		<s:if test='"success"==display'>
                			<tr><td height="150px;">&nbsp;</td></tr>
			                <tr>
			                    <td align="center" style="padding-right:20px;"><s:if test="'edit'.equals(reqFrom)"><s:text name="upd.user.success"/></s:if>
																			  <s:else><s:text name="new.user.success"/></s:else> 
								</td>
			                </tr>
			                <tr><td height="150px;">&nbsp;</td></tr>
                		</s:if>
                		<s:else>
				            <tr>
		                      <td class="heading">	<s:if test="'edit'.equals(reqFrom)"><s:text name="admin.upd.user"/></s:if>
													<s:else><s:text name="admin.new.user"/></s:else>
							  </td>
		                    </tr>
		                    <tr><td style="padding:10px; background:#F8F8F8">&nbsp;</td></tr>
		                    <tr>	                                                 
		                      <td  bgcolor="#FFFFFF">
		                      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		                      	  <tr><td>&nbsp;</td></tr>
		                      	  <tr><td>&nbsp;</td></tr>
		                      	  <s:if test='"edit".equals(reqFrom)'>
			                          <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="new.user.name" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:property value="username"/></td>
				                      </tr>
				                      <tr><td>&nbsp;</td></tr>
				                      <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="new.user.id" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:property value="userID"/></td>
		                            		<s:hidden name="userID"/>
		                            		<s:hidden name="appId"/>
				                      </tr>
			                      </s:if>
			                      <s:else>
			                      	  <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="new.user.name" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:textfield name="username" cssClass="input" /></td>
				                      </tr>
				                      <tr><td>&nbsp;</td></tr>
				                      <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="new.user.id" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:textfield name="userID" cssClass="input" /></td>
				                      </tr>
				                      
				                      <tr><td>&nbsp;</td></tr>
				                     <tr align="center">
			                          		<td width="5%"> &nbsp;</td>
			                          		<td width="5%" align="right"><s:label key="new.user.pwd" /> <font color="red">*</font> : </td>
		                            		<td width="2%">&nbsp;</td>
		                            		<td width="15%" align="left"><s:password name="pwd" cssClass="input" maxlength="20"/> </td>
				                      </tr>
			                      </s:else>
			                      <tr><td> &nbsp;</td></tr>
			                      <tr align="center">
		                          		<td width="5%"> &nbsp;</td>
		                          		<td width="5%" align="right"><s:label key="new.user.appId" /> <font color="red">*</font> : </td>
	                            		<td width="2%">&nbsp;</td>
	                            		<td width="15%" align="left"><s:textfield id="appIds" name="appIds" cssClass="input" readonly="true" />&nbsp;&nbsp;<input type="button" onclick="AppSelection()" name="btnn1" class="btn" style="cursor:pointer;" value="..." /></td>
			                      </tr>
			                      <tr><td> &nbsp;</td></tr>
			                      <tr align="center">
		                          		<td width="5%"> &nbsp;</td>
		                          		<td width="5%" align="right"><s:label key="new.user.mail" /> <font color="red">*</font> : </td>
	                            		<td width="2%">&nbsp;</td>
	                            		<td width="15%" align="left"><s:textfield id="mail" name="mail" cssClass="input"/></td>
			                      </tr>
			                      <tr><td> &nbsp;</td></tr>
			                      <tr align="center">
		                          		<td width="5%"> &nbsp;</td>
		                          		<td width="5%" align="right"><s:label key="new.user.type" /> <font color="red">*</font> : </td>
	                            		<td width="2%">&nbsp;</td>
	                            		<td width="15%" align="left"><s:select id="userType" name="userType" list="#{'user':'User','admin':'Admin'}" headerValue="-Select-" headerKey="" cssClass="input"/></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                      <tr align="center">
		                          		<td width="5%"> &nbsp;</td>
		                          		<td width="5%" align="right"><s:label key="new.user.status" /> <font color="red">*</font> : </td>
	                            		<td width="2%">&nbsp;</td>
	                            		<td width="15%" align="left"><s:radio id="status" name="status" list="#{'Y':'Active','N':'DeActive'}" /></td>
			                      </tr>
			                      <tr><td>&nbsp;</td></tr>
			                       <tr align="center">
		                          		<td width="5%"> &nbsp;</td>
		                          		<td width="5%" align="right"><s:label key="new.user.startdate" /> <font color="red">*</font> : </td>
	                            		<td width="2%">&nbsp;</td>
	                            		<td width="15%" align="left"><s:textfield name="startdate" id="datepicker" readonly="true"/></td>
	                            		<s:hidden name="reqFrom"/>
			                      </tr>
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
	                              		<input type="button"  name="Submit1" class="btn" style="cursor:pointer;" value=" Proceed " onclick="forward('adminexistUser.do')" />
	                            	</td>
	                       	 	</tr>
	                   		</s:if>
	                   		<s:else>
	                   			<tr>
	                            	<td align="center">
	                              		<input type="button" name="Submit1" class="btn" style="cursor:pointer;" value=" Back " onclick="forward('adminexistUser.do')" />
	                              		<input type="button" name="Submit2" style="cursor:pointer;" class="btn" value=" Submit " onclick="forward('adminaddnew.do')"/>
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
