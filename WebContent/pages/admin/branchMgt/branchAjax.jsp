<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="cityAjax">
	<s:select name="city" id="city" list="cityList" cssClass="inputSelect" headerKey="" headerValue="-select-" listKey="CITY_ID" listValue="CITY_NAME"/>
</div>