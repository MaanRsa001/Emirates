<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Form</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script language="JavaScript">javascript:window.history.forward(1);</script>
</head>
  
  <body>
   <table width="900" border="0" align="center" cellpadding="0" cellspacing="0"> 
  <tr>
    <td height="5"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
      <tr>
        <td bgcolor="#FFFFFF" style="padding:10px; background:#E5E5E5">
	        <s:if test="'getDetail'.equalsIgnoreCase(display)">
		        <s:form id="customer" name="customer" method="post" action="getUpdateCustomer.action" theme="simple">
		            <table width="100%" border="0" cellspacing="0" cellpadding="0">
		            <tr>
			          <td  style="color:red;"><s:actionerror/></td>
			        </tr>
			        <tr>
			          <td  style="color:green;"><s:actionmessage/></td>
			        </tr>
		              <tr>
		                <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                      <td class="heading">
		                      <s:if test='#session.product_id == "41"'>
		                      	<s:label key="customer.proposerDetails" />
		                      </s:if>
		                      <s:else>
		                      	<s:label key="customer.customerDetails" />
		                      </s:else>
		                      </td>
		                    </tr>
		                    <tr>
		                      <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
		                          <tr>
		                            <td width="2%">&nbsp;</td>
		                            <td width="32%"><s:label key="customer.title"/><font color="red">*</font><br />
		                            	<s:select name="title" id="title" list="titleList" headerKey="" headerValue="-Select-"  listKey="CODEDESC" listValue="CODEDESC" cssClass="input"/>
		                            </td>
		                            <td width="32%"><s:label key="customer.fullName" /><font color="red">*</font><br />
		                                <s:textfield name="fullName" id="fullName" cssClass="input"  maxLength="200"/></td>
		                            <td width="32%"><s:label key="customer.gender" /><font color="red">*</font><br />
		                            	<s:select name="gender" id="gender" list="#{'F':'Female','M':'Male'}" headerKey="" headerValue="-Select-" cssClass="input"/>
		                            </td>
		                            <td width="2%">&nbsp;</td>
		                          </tr>
		                          <tr>
		                            <td height="5" colspan="5"></td>
		                          </tr>
		                          <tr>
		                            <td>&nbsp;</td>
		                            <td><s:label key="customer.address" /><font color="red">*</font><br />
		                                <s:textfield name="address" id="address" cssClass="input"  maxLength="150"/>
		                            </td>
		                            <td><s:label key="customer.cusCivilId" /><font color="red">*</font><br />
		                                <s:textfield name="cusCivilId" id="cusCivilId" cssClass="input"  maxlength="10" onkeyup="checkNumbers(this);"/></td>
		                            <td><s:label key="customer.poBox" /><font color="red">*</font><br />
		                                <s:textfield name="poBox" id="poBox" cssClass="input" maxlength="6" onkeyup="checkNumbers(this);"/></td>
		                            <td>&nbsp;</td>
		                          </tr>
		                          <tr>
		                            <td>&nbsp;</td>
		                             <td><s:label key="customer.city" /><font color="red">*</font><br />
		                           		<s:select name="city" id="city" list="cityList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="input"/>
		                           	</td>
		                            <td><s:label key="customer.country" /><br />
		                            	<s:select name="country" id="country" list="countryList" listKey="CODEDESC" listValue="CODEDESC" cssClass="input"/>
		                            </td>
		                           <td><s:label key="customer.nationality" /><br />
		                           	   <s:select name="nationality" id="nationality" list="nationalityList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="input"/>
		                           </td>
		                            <td>&nbsp;</td>
		                          </tr>
		                          <tr>
		                            <td>&nbsp;</td>
		                             <td><s:label key="customer.telephoneNo" /><font color="red">*</font><br />
		                                <s:textfield name="telephoneNo" id="telephoneNo" cssClass="input" maxlength="10" onkeyup="checkNumbers(this);"/></td>
		                            <td><s:label key="customer.mobileNo" /><font color="red">*</font><br />
		                                <s:textfield name="mobileNo" id="mobileNo" cssClass="input" maxlength="10" onkeyup="checkNumbers(this);"/></td>
		                           <td><s:label key="customer.fax" /><br />
		                                <s:textfield name="fax" id="fax" cssClass="input" maxlength="10" onkeyup="checkNumbers(this);"/></td>
		                            <td>&nbsp;</td>
		                          </tr>
		                          <tr>
		                            <td>&nbsp;</td>
		                            <td><s:label key="customer.emailId" /><font color="red">*</font><br />
		                                <s:textfield name="emailId" id="emailId" cssClass="input" maxlength="100"/></td>
		                            <td><s:label key="customer.dob" /><br />
		                            	<sj:datepicker name="dob" id="dob" displayFormat="dd/mm/yy" cssStyle="float: left;" minDate="-100Y" maxDate="-1D" readonly="true" cssClass="input" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast" yearRange="c-100:c+100"/>
		                            </td>
		                           <td><s:label key="customer.occupation" /><font color="red">*</font><br />
		                                <s:select name="occupation" id="occupation" list="occupationList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="input"/>
		                           </td>
		                           <td></td>
		                           <td>&nbsp;</td>
		                          </tr>
		                          <s:if test="searchList.size()>0">
		                          <tr>
		                            <td>&nbsp;</td>
		                            <td class="bg" colspan="3">
			                      	  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
			                      	    <tr>
		                      			<td class="heading" colspan="8"><s:label key="customer.searchDtls" /></td>
		                    			</tr>
		                    			 <tr>
		                      			<td class="bg" colspan="8">&nbsp;</td>
		                    			</tr>
			                      	    <tr>
		                      			<td width="2%">&nbsp;</td>
			                            	<td class="heading">
			                            		<s:label key="customer.sNo" />
			                            	</td>
			                             	<td class="heading">
			                             		<s:label key="customer.customerId" />		             
			                             	</td>
			                             	<td class="heading">		       
			                             		<s:label key="customer.customerName" />      
			                             	</td>
			                             	<td class="heading">		       
			                             		<s:label key="customer.cusCivilId" />      
			                             	</td>								
			                             	<td class="heading">
			                             		<s:label key="customer.emailId" />
			                             	</td>					                             	
			                             	<td class="heading">
			                             		<s:label key="customer.mobileNo" />
			                             	</td>					                             	
			                             	<td width="2%">&nbsp;</td>
		                    			</tr>
			                      		<s:iterator value="searchList" status="stat">
			                          	<tr>
			                            	<td width="2%">&nbsp;</td>
			                            	<td class="bg"><s:property value="%{#stat.count}"/></td>
			                             	<td class="bg">		
			                             	<s:radio list="#{'Y':''}" name='customerDtls' onclick="setCusDtls(this.form,'%{customerId}')"/><s:property value="customerId"/>
			                             	</td>
			                             	<td class="bg" >		       
		                             			<s:property value="fullName"/>
			                             	</td>			
			                             	<td class="bg" >		       
		                             			<s:property value="cusCivilId"/>
			                             	</td>			                             	
			                             	<td class="bg" >		             
			                             		<s:property value="emailId"/>
			                             	</td>					                             	
			                             	<td class="bg" >		             
			                      	       		<s:property value="mobileNo"/>
			                             	</td>					                             	
			                             	<td width="2%">&nbsp;</td>
			                            </tr>
			                            </s:iterator>
			                        </table>
		                      		</td>
		                      		<td>&nbsp;</td>
		              			  </tr>
		              			   <s:hidden name="customerIdYN"/>
		              			   </s:if>
		                          <tr>
			                            <td>&nbsp;</td>
			                            <td colspan="3" align="center">
			                              <input name="Submit1" type="button" class="btn" value="Cancel" onclick="getBack('<s:property value="quoteStatus"/>');" />
			                             &nbsp;
			                             <s:if test='#session.b2c != "b2c"'>
				                             <input name="Submit2" type="button" class="btn" value="Search" onclick="getSearch();" />
				                              <s:if test="!(searchList!=null && searchList.size()>0)">
				                            	&nbsp;
				                            	<input name="Submit3" type="button" class="btn"  value="Save" onclick="this.form.actionType.value='Save';this.form.quoteStatus.value='C';this.form.submit();" />
				                              </s:if>
			                            </s:if>
			                             &nbsp;
			                             <s:if test="searchList!=null && searchList.size()>0">
			                             	<input name="Submit4" type="button" class="btn"  value="Submit" onclick="this.form.actionType.value='Submit';this.form.submit();"/>
			                             &nbsp;
			                             </s:if>
			                             <s:if test='quoteStatus !="C"'>
			                            	<input name="Submit5" type="button" class="btn"  value="Proceed" onclick="this.form.actionType.value='Proceed';this.form.submit();"/>
			                             </s:if>
			                             <s:else>
			                             	<input name="Submit5" type="button" class="btn"  value="New Quote" onclick="this.form.actionType.value='Proceed';this.form.submit();"/>
			                             </s:else>
			                            <s:hidden name="display" />
			                            <s:hidden name="actionType" />
			                            <s:hidden name="quoteStatus" />
			                            <s:hidden name="customerId"/>
			                            <s:hidden name="quoteNo"/>
			                            <s:hidden name="applicationNo"/>
			                            </td>
			                            <td>&nbsp;</td>
		                          </tr>
		                      </table></td>
		                    </tr>
		                </table></td>
		              </tr>
		           </table>
			</s:form>
		</s:if>
		</td>
    </tr>
	</table>
  </td>
  </tr>
  </table>
  <script>
	function getBack(quoteStatus)
	{
	if('b2c'=='<s:property value="#session.b2c"/>')
		document.customer.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp';
	else
		document.customer.action ='${pageContext.request.contextPath}/initReport.action?menuType='+quoteStatus+'&loginId=<s:property value="loginId"/>';
	document.customer.submit();
	}
	function getSearch()
	{
		document.customer.action ='${pageContext.request.contextPath}/getSearchCustomer.action';
		document.customer.submit();
		return false;
	}
	function setCusDtls(frm,cusId){
		frm.customerId.value=cusId;
	}
  </script>
  </body>
</html>
