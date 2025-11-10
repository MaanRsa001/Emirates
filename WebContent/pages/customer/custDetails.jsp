<%@ page language="java" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <s:if test="issuer != null">
       <tr>
         <td class="heading"><s:text name="customer.brokerInfo" /></td>
       </tr>
       <tr>	                                                 
         <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
             <tr>
               <td width="2%">&nbsp;</td>
               <td width="25%"><s:text name="customer.broker"  /><font color="red">*</font><br />
                 	<s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="-Select-"  listKey="CODE" listValue="CODEDESC" cssClass="input" onchange="getExecutive('?brokerCode='+this.value,'executiveList');emptyCustInfo();" disabled="%{brokerCode!=null && brokerCode!=''}"/>
                 	<s:hidden name="brokerName" />	                              
               </td>
               <td width="25%"><s:text name="customer.executive" /><font color="red">*</font><br />
                   <div id="executiveList"><s:select name="executive" list="executiveList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="input" disabled="#disable" value='executive==null?getText("customer.executiveDefault"):executive'/></div></td>
               <td width="25%">&nbsp;</td>
               <td width="2%">&nbsp;</td>
             </tr>
         </table></td>
       </tr>
  </s:if>
  <s:else>
  	<s:hidden name="brokerCode" />
  	<s:hidden name="executive" />
  </s:else>
     <tr>
       <td class="heading"><s:text name="customer.customerInfo" /></td>
     </tr>
      <tr>
       <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
           <tr>
             <td width="2%">&nbsp;</td>
             <td width="25%"><s:text name="customer.title"  /><font color="red">*</font><br />
                <s:select name="title" id="title" list="titleList" headerKey="" headerValue="-Select-"  listKey="CODEDESC" listValue="CODEDESC" cssClass="input" disabled="#disable1"/>
               </td>
             <td width="25%"><s:text name="customer.customerName" /><font color="red">*</font><br />
                 <s:textfield name="customerName" id="customerName" cssClass="input"  maxLength="200" disabled="#disable1" /><s:hidden name="customerId"  id="customerId"/><s:submit type="button" value="..." onclick="return customerSelectionAction(this.form)" /></td>
             <td width="25%"><s:text name="customer.coreAppCode" /><br />
                 <s:textfield name="coreAppCode" id="coreAppCode" cssClass="input" readonly="true" disabled="#disable1"/><s:submit type="button" value="..." onclick="return coreCustomerSearch()" />
                 <s:hidden name="custArNo" id="custArNo"/>
                 <s:hidden name="custCoreCode" id="custCoreCode"/>
                 <s:hidden name="clientCustomerId" id="clientCustomerId"/>
             </td>
             <td width="2%">&nbsp;</td>
           </tr>
           <tr>
             <td height="5" colspan="5"></td>
             </tr>
           <tr>
             <td>&nbsp;</td>
             <td><s:text name="customer.address1" /><br />
                 <s:textfield name="address1" id="address1" cssClass="input"  maxLength="200" disabled="#disable1"/></td>
             <td><s:text name="customer.address2" /><br />
                 <s:textfield name="address2" id="address2" cssClass="input" maxlength="200" disabled="#disable1"/></td>
             <td><s:text name="customer.city" /><font color="red">*</font><br />
                 <s:select name="city" id="city" list="cityList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="input" disabled="#disable1"/></td>
             <td>&nbsp;</td>
           </tr>
           <tr>
             <td>&nbsp;</td>
             <td><s:text name="customer.poBox" /><font color="red">*</font><br />
                 <s:textfield name="poBox" id="poBox" cssClass="input" maxLength="6" onkeyup="checkNumbers(this);" disabled="#disable1"/></td>
             <td><s:text name="customer.mobileNo" /><font color="red">*</font><br />
                 <s:textfield name="mobileNo" id="mobileNo" cssClass="input" maxLength="10" onkeyup="checkNumbers(this);" disabled="#disable1"/></td>
             <td><s:text name="customer.email" /><font color="red">*</font><br />
                 <s:textfield name="email" id="email" cssClass="input" disabled="#disable1"/></td>
             <td>&nbsp;</td>
           </tr>
       </table></td>
     </tr>
 </table>
 <script  type="text/javascript">
 	function getExecutive(val, id)
	{
			postRequest('${pageContext.request.contextPath}/'+id+'Customer.action'+val, id);
	}
	function emptyCustInfo()
	{
		var s="poBox&mobileNo&email&title&customerName&coreAppCode&address2&address1&city"; 
		var stringToArray = new Array;
		stringToArray = s.split("&");
		for ( var int = 0; int < stringToArray.length; int++) {		
				var obj=stringToArray[int]
				document.getElementById(""+obj).value="";
			}
	}
	function customerSelectionAction(frm)
	{	
		var brokerCode='';	
		if(frm.brokerCode){			
				brokerCode=frm.brokerCode.value;		
		}
	     var URL ='${pageContext.request.contextPath}/customerSelectionCustomer.action?brokerCode='+brokerCode;
	     return popUp(URL,'700','500');
	}
	function coreCustomerSearch()
	{
	     var URL ='${pageContext.request.contextPath}/coreCustSearchCustomer.action';
		 return popUp(URL,'700','500');
	}
</script>	