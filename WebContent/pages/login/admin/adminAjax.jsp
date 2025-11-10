<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html><head>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/tcal.js"></script>
</head>
<s:if test='"LoadFromApp".equals(reqFrom)'>
		<tr><td id="DynamicMailList"><table>
			<tr align="center">
              		<td width="5%"> &nbsp;</td>
              		<td width="5%" align="right">Application<font color="red">*</font> : </td>
               		<td width="2%">&nbsp;</td>
               		<td width="15%" align="left"><s:select name="appId" id="appId" list="applList" listKey="app_id" listValue="app_name" headerKey="" headerValue="-Select-" cssClass="input" onchange="LoadFromApp(this.value)" theme="simple"/></td>
           </tr>
          	  <tr><td>&nbsp;</td></tr>
          	 <div id="DynamicMailLists" style="display: none;">
           	  <tr align="center">\
               		<td width="5%"> &nbsp;</td>
               		<td width="5%" align="right"><s:label key="mail.mailcc" theme="simple"/> <font color="red">*</font> : </td>
                		<td width="2%">&nbsp;</td>
                		<td width="15%" align="left"><s:textfield name="mailcc" cssClass="input" theme="simple"/></td>
            </tr>
            <tr><td>&nbsp;</td></tr>
            <tr align="center">
               		<td width="5%"> &nbsp;</td>
               		<td width="5%" align="right"><s:label key="mail.smtphost" theme="simple"/> <font color="red">*</font> : </td>
                		<td width="2%">&nbsp;</td>
                		<td width="15%" align="left"><s:textfield name="smtphost" cssClass="input" theme="simple"/></td>
            </tr>
            <tr><td>&nbsp;</td></tr>
           <tr align="center">
               		<td width="5%"> &nbsp;</td>
               		<td width="5%" align="right"><s:label key="mail.smtpuser" theme="simple"/> <font color="red">*</font> : </td>
                		<td width="2%">&nbsp;</td>
                		<td width="15%" align="left"><s:textfield name="smtpuser" cssClass="input" theme="simple"/> </td>
            </tr>
            <tr><td> &nbsp;</td></tr>
            <tr align="center">
               		<td width="5%"> &nbsp;</td>
               		<td width="5%" align="right"><s:label key="mail.smtppwd" theme="simple"/> <font color="red">*</font> : </td>
                		<td width="2%">&nbsp;</td>
                		<td width="15%" align="left"><s:textfield name="smtppwd" cssClass="input" theme="simple"/> </td>
            </tr>
            <tr><td>&nbsp;</td></tr>
            <tr align="center">
               		<td width="5%"> &nbsp;</td>
               		<td width="5%" align="right"><s:label key="mail.tmp.pwdexp" theme="simple"/> <font color="red">*</font> : </td>
                		<td width="2%">&nbsp;</td>
                		<td width="15%" align="left"><s:textfield name="pwdexp" cssClass="input" theme="simple"/> </td>
            </tr>
         </div></table>
</s:if>
<s:elseif test='"LoadFromExistUser".equals(reqFrom)'>
	    <div id="DynamicUserLists">
	    <table width="100%">
		  <tr>
		  	<td align="center" colspan="4">
				<display:table name="existUserListAjax" pagesize="10" cellpadding="1" cellspacing="1" requestURI="/adminajaxValue.do?from=Normal" class="table" uid="row" id="existUser" excludedParams="from">
					<display:setProperty name="basic.msg.empty_list" value="<font color='red'>No Recods Found</font>" />
					<display:setProperty name="paging.banner.one_item_found" value="" />
					<display:setProperty name="paging.banner.one_items_found" value="" />
					<display:setProperty name="paging.banner.all_items_found" value="" />
					<display:setProperty name="paging.banner.some_items_found" value="" />
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:setProperty name="paging.banner.onepage" value="" />
					<display:column sortable="true" style="text-align:left;font-size:15px;" title="User Id" property="login_id" />
					<display:column sortable="true" style="text-align:left;font-size:15px;" title="User Name" property="username" />
					<display:column sortable="true" style="text-align:left;font-size:15px;" title="User Type" property="usertype" />
					<display:column sortable="true" style="text-align:left;font-size:15px;" title="Email Id" property="user_mail" />
					<display:column sortable="true" style="text-align:left;font-size:15px;" title="Status" property="status" />
					<display:column sortable="true" style="text-align:center;font-size:15px;" title="Edit">
						<input type="button" name="edit" onclick="editUser('${existUser.login_id}','${appId}')" class="btn" value="Edit" />
					</display:column>
				</display:table>
			</td>
		</tr>
	</table>
	</div>
</s:elseif>
