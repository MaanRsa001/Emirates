<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Update Payment Detail</title>
</head>
<body>
<br/><br/>

<center>    <table border="1" cellpadding="8" cellspacing="0" >
  <thead>
    <tr>
      <th>Field Label</th>
      <th>Input</th>
    </tr>
  </thead>
  <s:form theme="simple" name="frmable" action="submitPayPortfolio.action" >
   <tbody>
    <tr>
      <td><label>Merchant Reference <span style="color:red">*</span></label></td>
      <td><s:textfield name="merchantReference" required="true" theme="simple" cssClass="inputBox1" readonly="true" /></td>
    </tr>
    <tr>
      <td><label>Response Status <span style="color:red">*</span></label></td>
      <td><s:select 
    name="responseStatus" 
    label="Response Status" 
    list="#{'SUCCESS':'SUCCESS'}" 
    theme="simple" 
    cssClass="inputBox1" 
    required="true" /></td>
    </tr>
    
    <tr style="display: none;">
      <td><label>Response Message <span style="color:red">*</span></label></td>
      <td><s:textfield name="responseMessage" required="true" theme="simple" cssClass="inputBox1" value="Updated successfully." /></td>
    </tr>
    
    <tr>
      <td><label>Auth Trans Ref No <span style="color:red">*</span></label></td>
      <td><s:textfield name="resAuthTransRefNo" required="true" theme="simple" cssClass="inputBox1" /></td>
    </tr>
    <tr>
      <td><label>Auth Amount <span style="color:red">*</span></label></td>
      <td><s:textfield name="resAuthAmount" required="true" theme="simple" cssClass="inputBox1" /></td>
    </tr>
    
    <tr>
      <td><label>Transaction ID <span style="color:red">*</span></label></td>
      <td><s:textfield name="resTransactionId" required="true" theme="simple" cssClass="inputBox1" /></td>
    </tr>
    <tr>
      <td><label>Auth Code <span style="color:red">*</span></label></td>
      <td><s:textfield name="resAuthCode" required="true" theme="simple" cssClass="inputBox1" /></td>
    </tr>
    <tr>
      <td><label>Card Number Masked <span style="color:red">*</span></label></td>
      <td><s:textfield name="cardNumberMasked" required="true" theme="simple" cssClass="inputBox1" /></td>
    </tr>
    <tr style="display: none;">
      <td><label>Reason Code <span style="color:red">*</span></label></td>
      <td><s:textfield name="resReasonCode" required="true" theme="simple" cssClass="inputBox1" value="100" /></td>
    </tr>
    <tr style="display: none;">
      <td><label>Decision <span style="color:red">*</span></label></td>
      <td><s:textfield name="resDecision" required="true" theme="simple" cssClass="inputBox1"  value="ACCEPT"/></td>
    </tr>
    
    <tr>
    	<td>
    		<s:submit type="button" class="btn" theme="simple" value="Back" onclick="getBack()"/>
    	</td>
    	<td>
    		<s:submit type="submit"  class="btn"  theme="simple"	value="Save"  />
    	</td>    	
    </tr>
    
  </tbody>
  <s:hidden name="quoteno"></s:hidden>
  <s:hidden name="applicationno"></s:hidden>
  </s:form>
</table></center>
<script type="text/javascript">
function getBack(){
	 
	 document.frmable.action='paymentPortfolio.action';
	 document.frmable.submit();
}
</script>
    
</body>
</html>
