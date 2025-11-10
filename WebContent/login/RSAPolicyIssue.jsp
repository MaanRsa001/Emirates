<%try{ %>
<%@page import="com.maan.report.dao.ReportDAO"%>
<%@page import="org.apache.commons.lang.StringUtils"%><%@ include file="../login/sessionsCheckNormal.jsp"%>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="../login/home1.jsp"%>
	<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
	<%@ page isELIgnored ="false" %> 
	<head>
		<title>Saudi United Cooperative Insurance Company</title>
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	  <link href="../css/displaytag.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
	<%@ include file="../AutoComplete/AutoComplete.jsp"%>
	</head>

	<jsp:useBean id="product" class="com.maan.product.ProductSelection">
		<jsp:setProperty name="product" property="*" />
	</jsp:useBean>
	<jsp:useBean id="loginSession" class="com.maan.session.LoginSession" />
	<%
		String loginId = session.getAttribute("RSAISSUER")==null?request.getParameter("loginId"):(String)session.getAttribute("RSAISSUER");
		loginId = loginId == null ? "" : loginId;
		/*boolean statusRSAIssuer = product.getStatusRSAIssuer(request.getParameter("loginId"));
		if(statusRSAIssuer)
		{
			session.setAttribute("RSAISSUER", (String) session.getAttribute("user"));
		}*/
		String productTypeId = request.getParameter("selectProd") == null ? (String) session
					.getAttribute("product_id") == null ? "0"
					: (String) session.getAttribute("product_id")
					: request.getParameter("selectProd");
		String path = request.getContextPath();
		String reqFrom=request.getParameter("reqFrom");
		if("productSelection".equalsIgnoreCase(reqFrom))
		{
			session.setAttribute("user", request.getParameter("loginId"));
		}
	%>
	<%
							String BROKER = "";
							if (request.getMethod().equalsIgnoreCase("POST")) {
								try {
									BROKER = request.getParameter("userID") == null ? ""
											: request.getParameter("userID");
									if (!BROKER.equals("") && !BROKER.equals("0")) {
										session.removeAttribute("user");
										session.setAttribute("user", BROKER);
										session.setAttribute("loginPersonId", BROKER);
										session.setAttribute("mode", "rsa");
										session.setAttribute("user1", "Broker");
										session.setAttribute("usertype", "Broker");
										session.setAttribute("sessionCheckCondition", "Yes");
//										request.getRequestDispatcher( "/login/ProductSelection.jsp").forward(request,response);
										if("3".equalsIgnoreCase(productTypeId))
										{
											request.getRequestDispatcher( "newquote.dos?actionPath=premium/Quotation.jsp&freshMode=fresh").forward(request,response);
											return;
										}
										else if("11".equalsIgnoreCase(productTypeId))
										{
											request.getRequestDispatcher( "/ViewOpenCovers.jsp").forward(request,response);
											return;
										}
										else if("31".equalsIgnoreCase(productTypeId)||"32".equalsIgnoreCase(productTypeId)||"33".equalsIgnoreCase(productTypeId)||"41".equalsIgnoreCase(productTypeId))
										{
											//request.getRequestDispatcher("/getDetailCustomer.action").forward(request,response);
											response.sendRedirect(request.getContextPath()+"/getDetailCustomer.action?productId="+productTypeId+"&quoteStatus=QE");
											//productId="+productTypeId+"&quoteStatus=QE"
											return;
										}
										/*else if("41".equalsIgnoreCase(productTypeId))
										{
											request.getRequestDispatcher( "/ViewOpenCovers.jsp").forward(request,response);
											return;
										}*/
									}
								} catch (Exception e) {e.printStackTrace();}
							} else if(session.getAttribute("RSAISSUER")==null)
								session.setAttribute("RSAISSUER", (String) session.getAttribute("user"));
						%>
	<body>
		<form name="brokerSelection" method="post" action="">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="40%" align="right">
						<table width="100%" align="center" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td width="30%">
									&nbsp;
								</td>
								<td width="70%" align="right">
									&nbsp;
								</td>
							</tr>

							<tr>
								<td width="40%">
									&nbsp;
								</td>
								<td width="60%" align="right">
									<table width="40%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="right">
												&nbsp;&nbsp;
												<a id=txtContent href="<%=path%>/login/logout.jsp">Sign&nbsp;out
												</a> &nbsp;&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="13" colspan="2">
									&nbsp;
								</td>
							</tr>
							<tr align="center" bgColor="#FFFFFF">
								<td colspan="3">
									<table width="100%" bgColor="#FFFFFF" border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<td width="20">
												&nbsp;
											</td>
											<td valign="top" align="left">
												<img src="<%=path%>/images/logo.jpg">
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan=3 height='3'>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan=3 height='3'>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan=3>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<%
						String s1 = "" + session.getAttribute("mode");
//						if (s1.trim().equals("s")) {
						if (s1.trim().equals("rsa")) {
					%>
					<%--<td width="80%" height="34" align="right">
						Welcome
						<%=(String) session.getAttribute("RSAISSUER")%>&nbsp;logged in On
						Behalf of &nbsp;
						<%=(String) session.getAttribute("user")%>&nbsp;in&nbsp;<%=(String) session.getAttribute("userLoginMode")%>&nbsp;Mode&nbsp;
					</td>
					--%>
					<td width="50%" height="34" align="right">
									Login
									Id:&nbsp;
						<b><%=(String) session.getAttribute("RSAISSUER")%> </b>&nbsp;
					</td>
					<%
						} else {
					%>
					<td width="60%" height="34" align="right">
						Welcome
						<strong> <%=(String) session.getAttribute("user")%></strong>&nbsp;in&nbsp;
						<strong><%=(String) session.getAttribute("userLoginMode")%>&nbsp;Mode
							&nbsp;</strong>
					</td>
					<%
						}
					%>
				</tr>
				<tr>
					<td width="98%" colspan="2" align="center">
						&nbsp;&nbsp;&nbsp;
						<span class="heading">WALA'A POLICY ISSUER</span>
					</td>
				</tr>
				<%
					String brokerBra = (String) session.getAttribute("LoginBranchCode");
					String actualBrench = (String) session.getAttribute("adminBranch");
					String directBroker=product.getDirectBroker(brokerBra);
					if (request.getMethod().equals("POST")) {
						if (request.getParameter("userID") != null
								&& request.getParameter("userID").equals("0") && "".equals(request.getParameter("policyNo"))) {
				%>
				<tr>
					<td align="center">
						<font color="red">Please Select The Broker</font>
					</td>
				</tr>
				<%
					}
					}
				%>
				<tr align="center" height=25>
					<td colspan=2>
						&nbsp;
					</td>
				</tr>
				<tr height="35">
					<td align="center" style="font-weight: bold">
						<table border="1" cellpadding="2" cellspacing="2">
							<tr height="35" class="royamenuhead">
								<td align="left" class="bottomtext">
									Search by&nbsp;Broker&nbsp;&nbsp;&nbsp;
								</td>
								<td align="left" class="bottomtext">
									Choose&nbsp;Broker&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td >
									<input type="text" size="25" class="fde1" maxlength='50' id="brokerName"  onFocus="BrokerAutoComplete()" name="brokerName" style="width: 300px;"/>
								</td>
								<td align="center" style="font-weight: bold">
									<select name="userID" style="width: 300px;">
										<option value="0">
											Select
										</option>
										<%
											String[][] Broker = product.getBrokersList(actualBrench, loginId);
											for (int i = 0; i < Broker.length; i++) {
										%>
										<option value="<%=Broker[i][0]%>"><%=Broker[i][1]%>(<%=Broker[i][0]%>)
										</option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="center" height="10px">
					<td height="1" colspan="3"></td>
				</tr>
				<tr height="35">
					<td align="center" style="font-weight: bold">
						<button name="direct" style="width:150px;height: 50px" value="" onclick="return directBroker('<%=directBroker%>')">WALA'A DIRECT</button>
					</td>
				</tr>
				<tr align="center" height="10px">
					<td height="1" colspan="3"></td>
				</tr>
				<tr height="35">
					<td align="center" style="font-weight: bold;width:100%">
						<table border="1" width="610" cellpadding="2" cellspacing="2">
							<tr height="35" class="royamenuhead">
								<td align="left" class="bottomtext">
									Recently Used Brokers
								</td>
							</tr>
							<tr >
								<td align="center" width="100%">
								<%
									request.setAttribute("brokerList", product.getRecentlyUsedBrokerList(brokerBra, loginId));
									PremiumInputsBean premiumInputs=new PremiumInputsBean();
									String brokerIds = premiumInputs.removeLastChar(premiumInputs.getStringFromArray(Broker,0),',');
									String brokerNames = premiumInputs.removeLastChar(premiumInputs.getStringFromArray(Broker,1),',');
								%>
								<display:table name="brokerList" pagesize="15" requestURI="../login/RSAPolicyIssue.jsp" class="table" uid="row" id="record" style="width:100%">
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement" value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<display:column sortable="false" style="text-align:center;" title="Select" >
									 		<input type="radio" name="select" value="${record.loginId}" onclick="setCode(this.value)"/>
										</display:column>
										<display:column sortable="true" style="text-align:left;" title="Broker Name" property="companyName" headerClass="" />
										<display:column sortable="true" style="text-align:left;" title="Phone" property="phone" />
										<display:column sortable="true" style="text-align:left;" title="Fax" property="fax" />
									</display:table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td height="1" colspan="3"></td>
				</tr>
				<tr align="center">
					<td colspan="3">
						<table width="95%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="32" align="center" valign="middle">
									&nbsp;&nbsp;&nbsp;
									<input name="image" type="image" src='../images/Back.jpg'
										onclick="return goBack()">
									<input name="image" type="image" src='../images/Proceed.jpg'
										onclick="return fnSubmit()">
								</td>
							</tr>
						</table>
						
						</td>
					</tr>
					<%if("11".equals(productTypeId)){%>
					<tr align="center" height=25>
						<td colspan=2>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="98%" colspan="3" align="center">
							&nbsp;&nbsp;&nbsp;
							<span class="heading">ENDORSEMENT</span>
						</td>
					</tr>
					<tr align="center" height=20>
						<td colspan=2>
							&nbsp;
						</td>
					</tr>
					<tr align="center">
					<td colspan="3" width="98%">
						<table width="60%" border="1" cellpadding="2" cellspacing="2" height="40px">
							<tr >
								<td align="center" valign="middle">
									<b>Search By Policy No&nbsp;&nbsp;</b>
									<input type="text" size="25" class="fde1" maxlength='50' id="policyNo"  name="searchPolicyNo" style="width: 200px;" value="<%=StringUtils.defaultIfEmpty(request.getParameter("policyNo"),"") %>"/>&nbsp;&nbsp;
									<input name="image" type="image" src='../images/Search.jpg' onclick="return searchByPolicy()"  style="margin-bottom: -5px">
								</td>
							</tr>
						</table>
						
						</td>
					</tr>
					<tr align="center" >
						<td colspan=2>
							&nbsp;
						</td>
					</tr>
					<tr >
							<td align="center" style="font-weight: bold;width:100%">
								<%
									String policyNo=StringUtils.defaultIfEmpty(request.getParameter("policyNo"),"");
									if(StringUtils.isNotEmpty(policyNo)){
										request.setAttribute("policyList", new ReportDAO().getSearchResult(policyNo,brokerBra, productTypeId));
								%>
								<table border="1" width="60%" cellpadding="2" cellspacing="2">
									<tr >
										<td align="center" width="100%">
											<display:table name="policyList" pagesize="15" requestURI="../login/RSAPolicyIssue.jsp" class="table" uid="row" id="record" style="width:100%;" >
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<display:column sortable="false" style="text-align:center;" title="Select" >
											 		<input type="radio" name="selectPolicy" value="${record.POLICY_NO}" onclick="setPolicyInfo('${record.POLICY_NO}','${record.OPEN_COVER_NO}','${record.LOGIN_ID}','${record.OC_CUST_ID}')"/>
												</display:column>
												<display:column sortable="true" style="text-align:left;" title="Policy No" property="POLICY_NO" headerClass="" />
												<display:column sortable="true" style="text-align:left;" title="Broker Name" property="BROKER_NAME" headerClass="" />
												<display:column sortable="true" style="text-align:left;" title="Customer Name" property="CUSTOMER_NAME" />
												<display:column sortable="true" style="text-align:left;" title="Policy Date" property="POLICY_START_DATE" />
											</display:table>
										</td>
									</tr>
								</table>
								<%}%>
								<input type="hidden" name="selectProd" value="<%=productTypeId %>">
								<input type="hidden" name="searchFrom" value="BS">
								<input type="hidden" name="searchValue">
								<input type="hidden" name="loginId">
								<input type="hidden" name="openCoverNo">
								<input type="hidden" name="openCustomerId">
							</td>
						</tr>
						<%if(StringUtils.isNotEmpty(policyNo)){ %>
						<tr align="center">
							<td colspan="3">
								<table width="95%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="32" align="center" valign="middle">
											&nbsp;&nbsp;&nbsp;
											<input name="image" type="image" src='../images/Back.jpg'
												onclick="return goBack()">
											<input name="image" type="image" src='../images/Proceed.jpg'
												onclick="return redirect()">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<%} %>
						<tr align="center" >
							<td colspan=2>
								&nbsp;
							</td>
						</tr>
					<%}%>
				</table>
		</form>
	</body>
	<script type="text/javascript">
	function BrokerAutoComplete()
	{
	  var brokerNames="<%=brokerNames%>";
	  jQuery.Autocompleter.defaults.max = 5;
	 	$("#brokerName").autocomplete(brokerNames.split(','));
	}
	function fnSubmit()
	{
		var obj=document.brokerSelection.userID;
		var brokerName=document.brokerSelection.brokerName;
		if(obj.value=='' || obj.value=='0')
		{
			if(brokerName.value!='')
			{
				var brokerNames="<%=brokerNames%>";
				var brokerIds="<%=brokerIds%>";
				var nameArray=brokerNames.split(',');
				var idArray=brokerIds.split(',');
				if(nameArray.length>0)
				{
					for(var i=0; i<nameArray.length; i++)
					{
						if(nameArray[i]==brokerName.value)
						{
							obj.value=idArray[i];
						}
					}
				}
			}
			else
				alert('Please Select Broker');
		}
		if(obj.value!='' && obj.value!='0'){
		document.brokerSelection.submit();
			}
		return false;
	}
	function setCode(userID)
  	{
  		document.brokerSelection.userID.value=userID;
  	}
	function directBroker(userID)
  	{
  		document.brokerSelection.userID.value=userID;
  		return fnSubmit();
  	}
  	function goBack()
  	{
  		document.brokerSelection.action='<%=request.getContextPath()%>/login/ProductSelection.jsp';
  		document.brokerSelection.submit();
  	}
  	function searchByPolicy()
	{
		if(document.brokerSelection.searchPolicyNo.value==''){
			alert('Please enter Policy No to search');
			return false;
		}else{
			document.brokerSelection.action='<%=request.getContextPath()%>/login/RSAPolicyIssue.jsp?policyNo='+document.brokerSelection.searchPolicyNo.value;
			document.brokerSelection.submit();
			return true;
		}
	}
  	function redirect()
	{
		if(document.brokerSelection.searchValue.value==''){
			alert('Please select Policy No');
			return false;
		}else{
			document.brokerSelection.action='<%=request.getContextPath()%>/initReport.action?menuType=P&searchBy=policyNo&productId=<%=productTypeId%>';
			document.brokerSelection.submit();
			return true;
		}
	}
	function setPolicyInfo(policyNo, openCoverNo, loginId, openCustomerId)
	{
		document.brokerSelection.searchValue.value=policyNo;
		document.brokerSelection.openCoverNo.value=openCoverNo;
		document.brokerSelection.loginId.value=loginId;
		document.brokerSelection.openCustomerId.value=openCustomerId;
	}
  	</script>
</html>
<%}catch(Exception e){e.printStackTrace();}%>