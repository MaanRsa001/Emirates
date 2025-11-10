<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%" border="1" bordercolor="#A4A4A4"  align="center" cellpadding="4" cellspacing="0" >
	<tr>     				   
		<td width="2%" bgcolor="#4f6180" style="color: #FFFFFF" align="center"></td>
		<td width="4%" bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b>LC No</b></td>
		<td width="10%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b>LC Bank</b></td>
		<td width="6%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b>LC Amount</b></td>
		<td width="10%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b>LC_DATE</b></td>
	</tr>
	<s:if test="lcSelectionList.size > 0">					
		<s:iterator value="lcSelectionList" var="lcdetail" status="stat">
			<tr>   
			 	<td width="2%"><input type="radio" name="selects" onclick="fnLcDetail('<s:property value="%{#lcdetail.BANK_ID}"/>','<s:property value="%{#lcdetail.LC_NUMBER}"/>','<s:property value="%{#lcdetail.LC_DATE}"/>','<s:property value="%{#lcdetail.LC_AMOUNT}"/>','<s:property value="%{#lcdetail.LC_CURRENCY_ID}"/>')"/></td>   				  
				<td width="10%" class="bg"><s:property value="LC_NUMBER" /></td>
				<td width="4%" class="bg"><s:property value="BANK_NAME"  /></td>
				<td width="10%" class="bg"><s:property value="LC_AMOUNT" /></td>
				<td width="10%" class="bg"><s:property value="LC_DATE" /></td>
			</tr>
		</s:iterator>
	</s:if>
	<s:else>
		<tr><td colspan="5"><s:label key="quotation.norecords" theme="simple"></s:label> </tr>
	</s:else>
</table>
