<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="admin.exist.user"/></title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/tcal.js"></script>
	<script type="text/javascript">
	function forward(url)
	{
		document.existuser.action = "${pageContext.request.contextPath}/"+url;
		document.existuser.submit();
	}
	
	function LoadFromApp(value){
   		var params = "?reqFrom=LoadFromExistUser&appId="+value;
   		postRequest("${pageContext.request.contextPath}/adminajaxValue.do"+params, "DynamicUserLists");
   	}
   	
    function editUser(userId, appId){
    	document.existuser.action = "${pageContext.request.contextPath}/adminnewUser.do?reqFrom=edit&userID="+userId;
		document.existuser.submit();
    }
    
    <s:if test='appId!=null && from==null'>
   		 var app='<s:property value="appId"/>';
   		 postRequest("${pageContext.request.contextPath}/adminajaxValue.do?reqFrom=LoadFromExistUser&appId="+app, "DynamicUserLists");
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
			<s:form id="existuser" name="existuser" method="post" action="" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td bgcolor="#FFFFFF">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
                		<tr>
							<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
                		</tr>
			            <tr>
	                      <td class="heading"><s:text name="admin.exist.user"/></td>
	                    </tr>
	                    <tr><td style="padding:10px; background:#F8F8F8">&nbsp;</td></tr>
	                    <tr>	                                                 
	                      <td  bgcolor="#FFFFFF">
	                      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	                      	  <tr><td>&nbsp;</td></tr>
	                      	  <tr><td>&nbsp;</td></tr>
	                      	  <tr><td id="DynamicUserList">
                      	  			  <table>
				                      	  <tr align="center">
				                          		<td width="5%"> &nbsp;</td>
				                          		<td width="5%" align="right"><s:text name="application" /> <font color="red">*</font> : </td>
			                            		<td width="2%">&nbsp;</td>
			                            		<td width="15%" align="left"><s:select name="appId" id="appId" list="applList" listKey="app_id" listValue="app_name" headerKey="" headerValue="-Select-" cssClass="input" onchange="LoadFromApp(this.value)"/></td>
					                      </tr>
				                      	  <tr><td>&nbsp;</td></tr>
				                      	  <tr><td>&nbsp;</td></tr>
			                      	  </table>
			                      	  <div id="DynamicUserLists">
			                      	   <table width="100%">
			                      	      <tr>
										  	<td align="center" colspan="4">
										  		<s:if test='appId!=null'>
												<display:table name="existUserListAjax" pagesize="10" cellpadding="1" cellspacing="1" requestURI="/adminajaxValue.do?from=Normal" class="table" uid="row" id="existUser" excludedParams="from">
													<display:setProperty name="basic.msg.empty_list" value="No Recods Found" />
													<display:setProperty name="paging.banner.one_item_found" value="" />
													<display:setProperty name="paging.banner.one_items_found" value="" />
													<display:setProperty name="paging.banner.all_items_found" value="" />
													<display:setProperty name="paging.banner.some_items_found" value="" />
													<display:setProperty name="paging.banner.placement" value="bottom" />
													<display:setProperty name="paging.banner.onepage" value="" />
													<display:column sortable="true" style="text-align:left;font-size:15px;" title="User Id" property="login_id" />
													<display:column sortable="true" style="text-align:left;font-size:15px;" title="User Name" property="username"/>
													<display:column sortable="true" style="text-align:left;font-size:15px;" title="User Type" property="usertype"/>
													<display:column sortable="true" style="text-align:left;font-size:15px;" title="Email Id" property="user_mail" />
													<display:column sortable="true" style="text-align:left;font-size:15px;" title="Status" property="status"/>
													<display:column sortable="true" style="text-align:center;font-size:15px;" title="Edit">
														<input type="button" name="edit" class="btn" onclick="editUser('${existUser.login_id}','${appId}')" value="Edit"/>
													</display:column>
												</display:table>
												</s:if>
											</td></tr>
										  </table>
			                      	   </div>
			                      </td></tr>
		                      <tr><td>&nbsp;</td></tr>
		                      <tr><td>&nbsp;</td></tr>
		                     </table>
		                   </td>
		                </tr>
		              </table>
		            </td>
                   </tr>
                   <tr>
	                  <td>
                   		<table align="center">
	                   			<tr>
	                            	<td align="center">
	                              		<s:submit name="Submit1" cssClass="btn" cssStyle="cursor:pointer;" value=" Back " action="adminhome" />
	                            	</td>
	                       	 	</tr>
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
