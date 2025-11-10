<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	  
  </head>
  
<body> 
<s:if test="menuBlocker != 'viewcert'">
<s:if test='%{!"admin".equalsIgnoreCase(#session.user1)}'>
<script type="text/javascript">
ddlevelsmenu.setup("ddtopmenubar", "topbar")
</script>
<form action="initReport.action">
	<table width="100%">
		<tr>
			<td>
				<ul id="quoteSubMenu"  class="ddsubmenustyle">
					<li><a href="#" onclick="menuSelect('QE')">Existing Quotes</a></li>
					<%--<li><a href="#" onclick="menuSelect('QS')">Saved Quotes</a></li> --%>
					<li><a href="#" onclick="menuSelect('QL')">Lapsed Quotes</a></li>
					<li><a href="#" onclick="menuSelect('L')">Rejected Quotes</a></li>
				</ul>
				<ul id="referrlSubMenu" class="ddsubmenustyle">
					<li><a href="#" onclick="menuSelect('RA')" >Referral Approved</a></li>
					<li><a href="#" onclick="menuSelect('RU')">Referral UnApproved</a></li>
					<li><a href="#" onclick="menuSelect('RR')">Referral Rejected</a></li>
				</ul>
				<%--
				<ul id="openCoverSubMenu" class="ddsubmenustyle">
					<li><a href="${pageContext.request.contextPath}/ViewOpenCovers.jsp" onclick="menuSelect('C')" >Certificate</a></li>
					<li><a href="#" onclick="declarationMenu('D')">Declaration</a></li>
					<li><a href="#" onclick="declarationMenu('DE')">Declaration Menu</a></li>
					<li><a href="${pageContext.request.contextPath}/redirectOpenUpload.action" onclick="menuSelect('RU')">Declaration Upload</a></li>
					<li><a href="#" onclick="menuSelect('T')">Upload Transaction</a></li>
				</ul>
				--%>
				<ul id="portfolioSubMenu" class="ddsubmenustyle">
					<li><a href="#" onclick="menuSelect('P')">Certificate</a></li>
					<%--
					<li><a href="#" onclick="menuSelect('PD')">Declaration Certificate</a></li>
					--%>				
				</ul>
			</td>
		</tr>
		<tr>	
			<td height="28" colspan="2"  bgcolor="#4e6181" width="100%" >
			<s:if test='#session.b2c=="b2c"'>
				<div id="ddtopmenubar" class="mattblackmenu">
					<ul>
						<li><a href="${pageContext.request.contextPath}/login/ProductSelection.jsp">PRODUCTS</a></li>
						<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchQuote.jsp">RETRIEVE QUOTE</a></li>
						<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchQuote.jsp">RETRIEVE POLICY</a></li>
						<s:if test='#session.typeOfProduct=="M"'>
							<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchPolicy.jsp?reqFrom=renew">RENEW POLICY</a></li>
						</s:if>
					</ul>
				</div>
			</s:if>
			<s:else>    	
				<div id="ddtopmenubar" class="mattblackmenu">
					<ul>
						<li><a href="#" onclick="newQuote('<s:property value="%{#session.product_id}"/>')">New Quote</a></li>
						<li><a href="#" rel="quoteSubMenu">Quote Register</a></li>							
						<s:if test='#session.product_id==getText("OPEN_COVER")'>	
						<li><a href="#" rel="portfolioSubMenu">Portfolio</a></li>
						</s:if>
						<s:else>
						<li><a href="#" onclick="menuSelect('P')">Portfolio</a></li>
						</s:else>
						<li><a href="#" rel="referrlSubMenu">Referral Quote</a></li>
						<li><a href="#" onclick="menuSelect('C')">Customer</a></li>
						<li><a href="#" onclick="menuSelect('R')">Reports</a></li>
						<%--
						<s:if test='#session.product_id==getText("OPEN_COVER")'>	
						<li><a href="#" rel="openCoverSubMenu">Open Cover</a></li>	
						</s:if>
						--%>
						<s:if test='issuer !=null'>
							<li><a href="#" onclick="menuSelect('PE')">Endorsement</a></li>
						</s:if>				
						<li><a href="initCopyQuote.action" >Copy Quote</a></li>						
						<li><a href="initSearchReport.action" >Search</a></li>						
					</ul>
				</div>
			</s:else>
			</td>
		</tr>
	</table>
<s:hidden name="menuType"/>		
<s:hidden name="productId"/>
<s:hidden name="quoteStatus"/>
<s:hidden name="loginId"/>	
</form>
</s:if>	
</s:if>
</body>
<script type="text/javascript">
function menuSelect(obj)
{	
	document.forms[0].menuType.value=obj;
	document.forms[0].loginId.value='<s:property value="#session.user"/>';
	document.forms[0].action='${pageContext.request.contextPath}/initReport.action';	
	document.forms[0].submit();
}
function newQuote(productId)
{
	if(null=='<s:property value="#session.RSAISSUER"/>' || ''=='<s:property value="#session.RSAISSUER"/>')
	{
		document.forms[0].productId.value=productId;
		document.forms[0].quoteStatus.value='QE';	
		if(productId=='31'||productId=='32'||productId=='33'||productId=='34'||productId=='41')//31-Travel & 32-Travel Standard &33-Travel -Walaa &41-Helath Insurance Walaa & 30-Home Insurance-Walaa
		{
			document.forms[0].action="${pageContext.request.contextPath}/getDetailCustomer.action";
		}else if(productId=='65')
			document.forms[0].action="${pageContext.request.contextPath}/newQuoteMotor.action";
		else if(productId=='30')
			document.forms[0].action="${pageContext.request.contextPath}/initHome.action";
		else
			document.forms[0].action="${pageContext.request.contextPath}/newQuoteQuotation.action";
	}else{
		document.forms[0].quoteStatus.value='QE';
		if(productId=='65')
			document.forms[0].action="${pageContext.request.contextPath}/newQuoteMotor.action";
		else if(productId=='30')
			document.forms[0].action="${pageContext.request.contextPath}/initHome.action";
		else if(productId=='11' || productId=='3')
			document.forms[0].action="${pageContext.request.contextPath}/newQuoteQuotation.action";
		else{
			document.forms[0].loginId.value='<s:property value="#session.user"/>';
			document.forms[0].action='${pageContext.request.contextPath}/login/RSAPolicyIssue.jsp';
		}
	}
	document.forms[0].submit();
	return false;
}
function declarationMenu(obj)
{
	document.forms[0].menuType.value=obj;
	document.forms[0].action='${pageContext.request.contextPath}/initDeclaration.action';	
	document.forms[0].submit();
}
</script>
</html>


