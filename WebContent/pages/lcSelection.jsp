<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


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
		<script type="text/javascript" src="js/common.js"></script>					
    </head>

<body> 
<s:form name="lcSelection" id="lcSelection" theme="simple">
	<table width="90%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#FFFFFF">
		<tr class="bg">
			<td>
				<table>
					<tr>
						<td><s:label key="customer.searchBy" />&nbsp;LC No</td>
						<td><s:textfield name="searchValue" id="searchValue" onkeyup="searchList(this)"/></td>					
					</tr>
				</table>	
			</td>
		</tr>
		
    	<tr>
    	     <td>   
    	     <div id="lcSearchList" style="overflow-y: auto; min-height:100px;">    	          
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
         	 	</div> 		
         		<s:hidden name="lcBank" />
         		<s:hidden name="lcNo" />
         		<s:hidden name="lcDate" />
         		<s:hidden name="lcCurrency" />
         		<s:hidden name="lcAmount" />
    	     </td>
    	</tr>
    	<tr>
	      	<td align="center">
	      		<table>
	      			<tr>
                   		<td>&nbsp;</td>
                   		<td colspan="3" align="center"> 
                   		<input type="button" name="close"  value="Close" class="btn" onclick="window.close()"/>&nbsp;
						<input type="button" name="submit" value="Submit" class="btn" onclick="fnSubmit()"/>
                    	</td>
                    	<td>
                    	&nbsp;
                    	</td>
                   </tr>
                 </table>
             </td>
         </tr>
   </table>   
 </s:form>

 <script type="text/javascript">
 function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
document.onkeypress = stopRKey;
 function searchList(obj)
 { 
 	var search=obj.value; 
	postRequest('${pageContext.request.contextPath}/lcSelectionQuotation.action?searchValue='+search,'lcSearchList');
	
 }
 function fnLcDetail(lcBank,lcNo,lcDate,lcAmount,lcCurrency)
 { 
	document.lcSelection.lcBank.value=lcBank;
	document.lcSelection.lcNo.value=lcNo;
	document.lcSelection.lcDate.value=lcDate;
	document.lcSelection.lcAmount.value=lcAmount;
	document.lcSelection.lcCurrency.value=lcCurrency;
 }
 function fnSubmit()
 { 
 	if(!document.lcSelection.lcNo.value){
 		alert("Please Select LC Number");
 	}else{
		try{
			window.opener.document.getElementById("lcBank").value=document.lcSelection.lcBank.value;
			window.opener.document.getElementById("lcNo").value=document.lcSelection.lcNo.value;
			window.opener.document.getElementById("lcDate").value=document.lcSelection.lcDate.value;
			window.opener.document.getElementById("lcAmount").value=document.lcSelection.lcAmount.value;
			window.opener.document.getElementById("lcCurrency").value=document.lcSelection.lcCurrency.value;
			window.opener.exchageRate(document.lcSelection.lcCurrency,'lccurrency');
		}catch(err){alert(err)};
	window.close();
	}
 }
 function trim(text) 
{
    return text.replace(/^\s+|\s+$/g, "");
}
 </script>
  </body>
</html>
