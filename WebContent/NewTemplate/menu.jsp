<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	  <style>
	      .mynavbar, .dropdown ul{
		  background-color:#337ab7 !important;
		  box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 3px, rgba(0, 0, 0, 0.24) 0px 1px 2px;
		  }
		  .navbar ul li a, .dropdown .dropdown-menu li a{
		   color:white !important;
		   font-family:Tahoma;
		   font-weight:bold;
		   font-size: 14px;
		  }
		  .mynavbar .dropdown ul li a{
			  padding:10px;
		  }
	      .navbar ul li a:hover{
		   color:black !important;
			background-color: #1ea55d;

		  }
		  .navbar-toggle, .navbar-toggle .glyphicon{
			  background-color:#1ea55d;
			  color:white;
		  }
		  .nav .open>a, .nav .open>a:focus, .nav .open>a:hover {
			background-color: #1ea55d;
			border-color: #1ea55d;
		  }
	  </style>
  </head>
<body> 
	<s:if test="menuBlocker != 'viewcert'">
		<s:if test='%{!"admin".equalsIgnoreCase(#session.user1)}'>
			<form action="initReport.action">
				<nav class="navbar mynavbar">
				  <div class="container-fluid">
				    <div class="navbar-header">
				      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>                        
				      </button>
				    </div>
				    <div class="collapse navbar-collapse" id="myNavbar">
				      <ul class="nav navbar-nav">
				      	<s:if test='#session.b2c=="b2c"'>
				      		<li><a href="${pageContext.request.contextPath}/login/ProductSelection.jsp">PRODUCTS</a></li>
							<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchQuote.jsp">RETRIEVE QUOTE</a></li>
							<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchQuote.jsp">RETRIEVE POLICY</a></li>
							<s:if test='#session.typeOfProduct=="M"'>
								<li><a href="${pageContext.request.contextPath}/CopyQuote/BtoCSearchPolicy.jsp?reqFrom=renew">RENEW POLICY</a></li>
							</s:if>
				      	</s:if>
				      	<s:else>
					        <li><a href="#" onclick="newQuote('<s:property value="%{#session.product_id}"/>')">New Quote</a></li>
					        <li class="dropdown">
					          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Quote Register <span class="caret"></span></a>
					          <ul class="dropdown-menu">
					           <li><a href="#" onclick="menuSelect('QE')">Existing Quotes</a></li>
								<%--<li><a href="#" onclick="menuSelect('QS')">Saved Quotes</a></li> --%>
								<li><a href="#" onclick="menuSelect('QL')">Lapsed Quotes</a></li>
								<li><a href="#" onclick="menuSelect('L')">Rejected Quotes</a></li>
								<li><a href="#" onclick="menuSelect('O')">Pending Online Pay Quotes</a></li>
					          </ul>
					        </li>
					        <s:if test='#session.product_id==getText("OPEN_COVER")'>	
						        <li class="dropdown">
						          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Portfolio <span class="caret"></span></a>
						          <ul class="dropdown-menu">
						           		<li><a href="#" onclick="menuSelect('P')">Certificate</a></li>
						           		<%--<li><a href="#" onclick="menuSelect('PD')">Declaration Certificate</a></li>--%>
						          </ul>
						        </li>
							</s:if>
							<s:else>
								<li><a href="#" onclick="menuSelect('P')">Portfolio</a></li>
							</s:else>
							<li class="dropdown">
					          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Referral Quote<span class="caret"></span></a>
					          <ul class="dropdown-menu">
					           	<li><a href="#" onclick="menuSelect('RA')" >Referral Approved</a></li>
								<li><a href="#" onclick="menuSelect('RU')">Referral UnApproved</a></li>
								<li><a href="#" onclick="menuSelect('RR')">Referral Rejected</a></li>          
							  </ul>
					        </li>
							<li><a href="#" onclick="menuSelect('C')">Customer</a></li>
							<li><a href="#" onclick="menuSelect('R')">Reports</a></li>
							<s:if test='issuer !=null'>
								<li><a href="#" onclick="menuSelect('PE')">Endorsement</a></li>
							</s:if>				
							<li><a href="initCopyQuote.action" >Copy Quote</a></li>						
							<li><a href="initSearchReport.action" >Search</a></li>
							 <s:if test='#session.product_id!=getText("OPEN_COVER")'>	
								<li><a href="initxReport.action" >Payment Report</a></li>
							</s:if>
				      	</s:else>
				      </ul>
				    </div>
				  </div>
				</nav>
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


