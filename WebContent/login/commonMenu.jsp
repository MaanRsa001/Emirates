<%@ taglib uri="../WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="../WEB-INF/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ include file="sessionsCheckNormalHome.jsp"%>
<% 
//FOR B2C - BY Karthick
String b2c = "";
b2c = (String)session.getAttribute("b2c");
b2c = b2c==null ? "" :b2c;	
 
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="../login/error_messg_handle.jsp" %> 
<jsp:useBean id= "productsBean" class = "com.maan.product.ProductSelection">
<%--<jsp:useBean id= "tb1"  class = "com.maan.Travel.DAO.TravelBean" >--%>
<jsp:setProperty name= "tb1" property = "*"/>
</jsp:useBean>


<%
//PRODUCT ID COMING FROM PRODUCT SELECTION PAGE
String officeUserType = "";
officeUserType = session.getAttribute("user1")==null?"":(String) session.getAttribute("user1");
//officeUserType = officeUserType == null ? "" : officeUserType;

String pid = request.getParameter("selectProd");
String officeScheme = request.getParameter("officeScheme");
String OFSName = request.getParameter("OFSName");

String comFrom=request.getParameter("compFrom")!=null?request.getParameter("compFrom"):"";
if(pid == null&&comFrom.equalsIgnoreCase("compNew"))
	pid=request.getParameter("spids");
if(pid == null)
   pid = session.getAttribute("product_id")==null?"":(String)session.getAttribute("product_id");

if(officeScheme == null)
	officeScheme =  session.getAttribute("scheme_id")==null?"":(String) session.getAttribute("scheme_id");

if(OFSName == null)
	OFSName =  session.getAttribute("OFSName")==null?"":(String) session.getAttribute("OFSName");

com.maan.common.Customer.DataSelect  ds = new com.maan.common.Customer.DataSelect();

String branch = session.getAttribute("LoginBranchCode")==null?"":(String)session.getAttribute("LoginBranchCode");
HashMap brokerDetails = session.getAttribute("BrokerDetails")==null?new HashMap():(HashMap)session.getAttribute("BrokerDetails");
String cid = "";
String currencyType = "";
if(brokerDetails.size()>0)
{
	cid = (String)brokerDetails.get("Orgination");
	currencyType = (String)brokerDetails.get("CurrencyAbb");
}
String productname =  ds.getProductName(pid,branch);
productname = productname==null?"":productname;

String typeOfProduct = productsBean.getProductType((pid==null?"":pid),branch);

if((pid.equalsIgnoreCase("30") || "O".equalsIgnoreCase(typeOfProduct)) && !officeUserType.equalsIgnoreCase("Admin"))
	productname = OFSName;

session.setAttribute("cid1",cid);
session.setAttribute("product_id",pid);
session.setAttribute("scheme_id",officeScheme);
session.setAttribute("OFSName",OFSName);
session.setAttribute("typeOfProduct",typeOfProduct);
String productTypeId = pid;
//For common Home
String homeProductDetails[][] = new String[0][0];
if(session.getAttribute("HomeProIds")!=null)
{
	homeProductDetails = (String[][])session.getAttribute("HomeProIds");
}
int homeFlag = 0;
String homePid="0";
if(homeProductDetails.length>0)
{
	for(int h=0;h<homeProductDetails.length;h++)
	{
		if(pid.equals(homeProductDetails[h][0]))
		{
			homeFlag = 1;
			homePid = homeProductDetails[h][0];
		}
	}
	if(homeFlag==0)
	{
		homePid = "0";
	}
}
else
{
	homePid = "0";
}
//
//String better = productsBean.getBetterStatus(pid);


//New Added by chinna
String[][] ProductDetails = new String[0][0];
if(session.getAttribute("ProductDetails")!=null)
{
	ProductDetails = (String[][])session.getAttribute("ProductDetails");
}
		
String[][] office = new String[0][0];
if(session.getAttribute("officeScheme")!=null)
{
	office = (String[][])session.getAttribute("officeScheme");
}
//End
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Saudi United Cooperative Insurance Company</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="online,insurance,marine,travel,home,office,marinequote,quotation,policy,schedule,marinepolicy,marineschedule,dubaiinsurace,Walaa,walaaonline">
    <meta http-equiv="description" content="This is my page"> 
    
<style type="text/css">
<!--
a{
text-decoration:none;
}

a:link {
	text-decoration: none;
	color: #FFFFFF;
}

a:link:hover {
	text-decoration: none;
	color: #000000;
}

a:visited {
	text-decoration: none;
	color: #FFFFFF;
}

a:visited:hover {
	text-decoration: none;
	color: #000000;
}
-->
</style>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/displaytag.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/style1.css" rel="stylesheet" type="text/css">
<script>
function change_class(val) { 
document.getElementById(val).className='buttonsMenuBlueMOvar';
}

function RevertClass(val)
{
	document.getElementById(val).className='buttonsMenuBlue';
}
</script>
  </head>
   <body>
<%
if(b2c.equalsIgnoreCase("b2c"))
{
%>
<%@include file="../login/BtoCmenu.jsp"%>
<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" >
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
<%
}
else{
%>
<form name="menus" method="post" >

<table width="95%" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2">
						<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td bgColor="#efefef" width="30%">
									&nbsp;
								</td>
								<td width="70%" align="right" bgColor="#efefef">
									&nbsp;
								</td>
							</tr>
							
							<tr>
								<td bgColor="#efefef" width="40%">
									&nbsp;
								</td>
								<td width="60%" align="right" bgColor="#efefef">
									<table width="40%" border="0" cellspacing="0" cellpadding="0">
										<tr>
										 <%
											if(!officeUserType.equalsIgnoreCase("Admin"))
											{
										%>
											<td align="right" >
											&nbsp;&nbsp;
												<a id=txtContent href="<%=path%>/login/ProductSelection.jsp">Home
												</a>
											&nbsp;&nbsp;
											<%--	|
											&nbsp;&nbsp;
												<a id=txtContent href="<%=path%>/login/CommonMenu2.jsp">Menu
												</a>
											&nbsp;&nbsp;
											--%>
												|
											&nbsp;&nbsp;
												<a id=txtContent href="<%=path%>/login/logout.jsp">Sign&nbsp;out
												</a>
											&nbsp;&nbsp;
											</td>
										<%
											}else{
										 %>
										 		<td align="right" >
											&nbsp;&nbsp;
												<a id=txtContent href="<%=path%>/login/logout.jsp">Sign&nbsp;out
												</a>
											&nbsp;&nbsp;
											</td>
										 <%} %>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="13" colspan="2" bgColor="#efefef">
									&nbsp;
								</td>
							</tr>
							<tr align="center" bgColor="#efefef">
								<td colspan="3">
									<table width="100%" bgColor="#efefef" border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<td width="20">
												&nbsp;
											</td>
											<td valign="top" align="left">
												<img src="<%=path%>/images/logo.jpg" >
											</td>
										<td align="right" width="78" height="58" >
										<% if(!pid.equalsIgnoreCase("30") && ProductDetails.length>0){
										for(int p=0; p<ProductDetails.length; p++) {
										if(pid.equalsIgnoreCase(ProductDetails[p][0])){
										%>
											<img width="78" height="58" style="cursor: pointer;" src="<%=request.getContextPath() %>/images/<%=ProductDetails[p][1] %>.gif" alt="<%=ProductDetails[p][1]%>" />
										<%}}}else if(office.length>0){ 
											for(int o=0; o<office.length; o++){
											if(officeScheme.equalsIgnoreCase(office[o][0])){
										%>
										<img width="78" height="58" style="cursor: pointer;" src="<%=request.getContextPath() %>/images/<%=office[o][1] %>.gif" alt="<%=office[o][1]%>"/>
										<%}}}%>
										</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan=3 bgColor="#efefef" height='3'>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan=3 bgColor="#efefef" height='3'>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan=3>
									&nbsp;
								</td>
							</tr>
							
						</table>
						<input type="hidden" name="freshMode">
			       <input type="hidden" name="freshMode1">
			       <input type="hidden" name="DisplayValue">
				   <input type="hidden" name="mode" value="">
				   <input type="hidden" name="productid" value="<%=pid%>" id = "productid">
				   
					</td>
				</tr>
	<%
		if(!officeUserType.equalsIgnoreCase("Admin"))
		{
	%>
				<tr>
					<td rowspan="2" width="13%" valign="top">
						<table border="0" align="left" cellpadding="0" cellspacing="0">
							<%		
								if(homePid.equalsIgnoreCase("75"))
								{
							%>		
							<tr>
								<td align="left">
									<table border="0" align="left" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="certificates" class="buttonsMenuBlue"
												align="center">
												<a href="<%=path%>/ViewFleetOpenCover.jsp"
													onmousemove='change_class("certificates");'
													onmouseout='RevertClass("certificates");'>
													CERTIFICATES</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<%	} %>
							<tr>
								<td align="left">
									<table border="0" align="left" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="newQuote" class="buttonsMenuBlue"
												align="center">
												<a  href="#"  onmouseover='change_class("newQuote");' onmouseout='RevertClass("newQuote");' onClick="return openNew('<%=homePid%>')">&nbsp;&nbsp;NEW QUOTE</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
		<%
			if(homePid.equalsIgnoreCase("30") || "O".equalsIgnoreCase(typeOfProduct))
			{
		%>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							
							<tr>
								<td align="left">
									<table border="0" align="left" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="mergeLoc" class="buttonsMenuBlue"
												align="center">
												<a  href="#" onmouseover='change_class("mergeLoc");' onmouseout='RevertClass("mergeLoc");' onClick="return openDeclaration('<%=homePid%>')">&nbsp;MERGE&nbsp;LOCATIONS&nbsp;</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
		<%
			}
		%>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center">
									<table border="0" align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="quote" class="buttonsMenuBlue">
												<a  href="#" onClick="javascript:quoteRegister('<%=homePid%>');" onmouseover='change_class("quote");' onmouseout='RevertClass("quote");' >&nbsp;&nbsp;QUOTE REGISTER</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center">
									<table border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>

											<td valign="middle" id="referral" class="buttonsMenuBlue" align="center">
									<a href="#" onClick="javascript:Referral('<%=homePid%>');" onmouseover='change_class("referral");' onmouseout='RevertClass("referral");'>&nbsp;&nbsp;REFERRAL QUOTE</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center">
									<table border="0" align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="portFolio" class="buttonsMenuBlue">
												 <a  href="#" onClick="javascript:portFolio('<%=homePid%>');" onmouseover='change_class("portFolio");' onmouseout='RevertClass("portFolio");'>&nbsp;&nbsp;PORTFOLIO</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center">
									<table border="0" cellpadding="0" cellspacing="0" align="center">
										<tr>
											<td id="customer" class="buttonsMenuBlue" align="center">
												<a href="#"onClick="javascript:customer('<%=homePid%>')" onmouseover='change_class("customer");' onmouseout='RevertClass("customer");'>&nbsp;&nbsp;CUSTOMER</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
								<% if(homePid.equalsIgnoreCase("11")){ %>
								<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="Declaration" class="buttonsMenuBlue">
												<a href="#"onClick="javascript:declaration('<%=homePid%>');" onmouseover='change_class("Declaration");' onmouseout='RevertClass("Declaration");'>&nbsp;&nbsp;DECLARATION
		  </a>
											</td>
										</tr>
									</table>
								</td>
							</tr><tr>
								<td>
									&nbsp;
								</td>
							</tr>
								<%} %>
							
								<% if((!homePid.equalsIgnoreCase("30") && !homePid.equalsIgnoreCase("65") && !homePid.equalsIgnoreCase("75")) || 
										(!"O".equalsIgnoreCase(typeOfProduct) && !"M".equalsIgnoreCase(typeOfProduct))){ %>
								<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="copyQuote" class="buttonsMenuBlue">
												<a href="#"onClick="javascript:copyQuote('<%=homePid%>');" onmouseover='change_class("copyQuote");' onmouseout='RevertClass("copyQuote");'>&nbsp;&nbsp;COPY QUOTE
		 										</a>
											</td>
										</tr>
									</table>
								</td>
							</tr><tr>
								<td>
									&nbsp;
								</td>
							</tr>
								<%} %>
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="lapsedQuote" class="buttonsMenuBlue">
												<a href="#" onClick="javascript:TrashQuote('<%=homePid%>');" onmousemove='change_class("lapsedQuote");'
													onmouseout='RevertClass("lapsedQuote");'>LAPSED&nbsp;QUOTE
												</a>
											</td>
										</tr>
									</table>
								</td>
							</tr><tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<%
								if("65".equalsIgnoreCase(homePid) || "75".equalsIgnoreCase(homePid) || "M".equalsIgnoreCase(typeOfProduct)){
									String[][] statuses = null;//tb1.getPaymentCheckStatus((String) session.getAttribute("user"),homePid);
                                	//if("Y".equalsIgnoreCase(statuses[0][3])){
							%>
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="endorsement" class="buttonsMenuBlue">
												<a href="#" onClick="javascript:EndtRenewal('<%=homePid%>','endorsement');" onmousemove='change_class("endorsement");'
													onmouseout='RevertClass("endorsement");'>ENDORSED&nbsp;DETAILS
												</a>
											</td>
										</tr>
									</table>
								</td>
							</tr><tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<%
									//}
									if("Y".equalsIgnoreCase(statuses[0][4])){
							%>
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="renewal" class="buttonsMenuBlue">
												<a href="#" onClick="javascript:EndtRenewal('<%=homePid%>','renewal');" onmousemove='change_class("renewal");'
													onmouseout='RevertClass("renewal");'>RENEWAL&nbsp;DETAILS
												</a>
											</td>
										</tr>
									</table>
								</td>
							</tr><tr>
								<td>
									&nbsp;
								</td>
							</tr> 
							<%
									}
								} 
							%>							
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="report" class="buttonsMenuBlue">
												<a href="<%=path%>/admin/HomeFinalReports.jsp" onmousemove='change_class("report");'
													onmouseout='RevertClass("report");'><b>REPORTS</b></a>
											</td>
										</tr>
									</table>
								</td>
							</tr><tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td  align="center">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="middle" id="changepassword" class="buttonsMenuBlue">
												<a href="<%=path%>/broker/assignPasswordIndividual.jsp?requestfrom=Home"
													onmousemove='change_class("changepassword");'
													onmouseout='RevertClass("changepassword");'>CHANGE&nbsp;PASSWORD
												</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
	<%
		}else{}
	%>
	<%
		if(officeUserType.equalsIgnoreCase("Admin")){
	%>
		<tr>
	<% } %>					

					<td valign="top" width="80%">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<%
									String s1 = "" + session.getAttribute("mode");
									if (s1.trim().equals("s")) {
								%>
								<td width="95%" height="34" align="right" class="formtxtRSA">
									Welcome
									<strong><%=session.getAttribute("RSAUSER")%>&nbsp;</strong>logged
									in On Behalf of&nbsp;
									<strong><%=session.getAttribute("user")%> </strong>&nbsp;in&nbsp;
									<strong> <%=session.getAttribute("userLoginMode")%>&nbsp;Mode&nbsp;under
										<%=productname%> </strong>
								</td>
								<%
								} else {
								%>
								<td width="95%" height="10" align="right">
									<strong>Login
										Id: </strong>&nbsp;
									<strong><%=session.getAttribute("user")%> </strong>
																<%
									}
									if ("75".equalsIgnoreCase(productTypeId)) {
								%>
									<b>&nbsp;&nbsp;Core Application MOC No :&nbsp; </b><%=session.getAttribute("coreid")%>
									
									<b>&nbsp;&nbsp;Customer Name :&nbsp; </b><%=session.getAttribute("custname")%>
									&nbsp;&nbsp;
								<%
									}
								%>									
								</td>										
							</tr>
						</table>
						<input type="hidden" name="actionPath" />
					</td>
				</tr>
				<tr>
					<td valign="top">
<%} %>
</body>

</form>


<script>
function ReleaseNotes()
{
	var URL = "<%=request.getContextPath()%>/login/ReleaseNotes.jsp";
	var windowName = "ReleaseNotes";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h = 450;
	var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=yes'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
	return false;
}	
function stopRKey(evt) 
{ 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
  else if ((evt.keyCode == 13) && (node.type=="checkbox"))  {return false;}
} 
document.onkeypress = stopRKey; 
function openDeclaration(hpid)
{
	document.menus.action="<%=basePath%>OfficeInsurance/showMultipleQuotes.jsp";
	document.menus.submit();
}

function TrashQuote(hpid)
{
	 var pid = document.getElementById("productid").value;
	 document.menus.DisplayValue.value = "lapsedquote";
	<%--  if(pid == "31"||pid == "22" || "T"=="<%=typeOfProduct %>")
	 {
		 document.menus.action = "<%=basePath%>TravelInsurance/TrashQuote_B2B1.jsp";
	 }
	 else if(pid == hpid)
	 {
		  document.menus.action = "<%=basePath%>HomeInsurance/TrashQuote_B2B1.jsp";
	 }--%>
	 document.menus.action = "<%=basePath%>CommonDisplayController";
	 document.menus.submit();
	 return false;
}
//GOES TO FIRST PAGE FOR CUSTOMER CREATION(NEW QUOTE)
function openNew(hpid)
{	
	var pid = document.getElementById("productid").value;	
	document.menus.DisplayValue.value = "newquote";
	<%-- 
	if(pid == "31"||pid == "22" || "T"=="<%=typeOfProduct %>")
	{
		document.menus.action="<%=basePath%>CustomerInfo/CustomerInfo.jsp";
		document.menus.submit();
	}else if(pid == "65"||pid=="75" || "M"=="<%=typeOfProduct %>")
	{
		document.menus.action="<%=basePath%>servlet/motorQuote";
		document.menus.submit();
	}
	else if(pid == hpid && pid!= "30" || "O"!="<%=typeOfProduct %>")
	{
		document.menus.action="<%=basePath%>CustomerInfo/CustomerInfoHome.jsp";
		document.menus.submit();
	}
	else if(pid == "30" || "O"=="<%=typeOfProduct %>")
	{
		document.menus.action="<%=basePath%>CustomerInfo/CustomerInfoHome.jsp";
		document.menus.submit();
	} 
	if(pid == "65"||pid=="75" || "M"=="<%=typeOfProduct %>")
	{
		document.menus.action="<%=basePath%>servlet/motorQuote";
		document.menus.submit();
	}else{
		document.menus.action="<%=basePath%>CustomerInfo/CustomerInfoHome.jsp";
		document.menus.submit();
	}	
	--%>
	document.menus.action = "<%=basePath%>CommonDisplayController";
	document.menus.submit();
	return false;
}
//GOES TO QUOTE REGISTER FOR CORRESPONDING PRODUCTS
function quoteRegister(hpid)
{ 
    var pid = document.getElementById("productid").value;
	document.menus.DisplayValue.value = "quoteregister";
	<%--if(pid == "31"||pid == "22" || "T"=="<%=typeOfProduct %>") 
         document.menus.action="<%=basePath%>TravelDisplayController";
	else if(pid == hpid) {
		document.menus.action="<%=basePath%>HomeDisplayController";
		//document.menus.selectProd.value=pid;
	}--%>
	document.menus.action = "<%=basePath%>CommonDisplayController";
	document.menus.submit();
	return false;   
}

//ENDORSEMENT AND RENEWAL MENU PROCESS
function EndtRenewal(hpid,reqto)
{ 
	document.menus.action="<%=basePath%>EndtRenewalController";
	document.menus.DisplayValue.value = reqto;
	document.menus.submit();
	return false;    
}

//GOES TO PORTFOLIO FOR CORRESPONDING PRODUCTS
function portFolio(hpid)
{ 
   var pid = document.getElementById("productid").value;
	document.menus.DisplayValue.value='portfolio';
	<%--if(pid == "31"||pid == "22" || "T"=="<%=typeOfProduct %>")
        document.menus.action="<%=basePath%>TravelDisplayController";
	else if(pid == hpid){
		document.menus.action="<%=basePath%>HomeDisplayController";			
	}--%>
	document.menus.action = "<%=basePath%>CommonDisplayController";
	document.menus.submit();
	return false;
}

//GOES TO CUSTOMER FOR CORRESPONDING PRODUCTS 
function customer(hpid)
{ 
    var pid = document.getElementById("productid").value;
    document.menus.DisplayValue.value='customer';
	<%--if(pid == "31"||pid == "22" || "T"=="<%=typeOfProduct %>") 
        document.menus.action="<%=basePath%>TravelDisplayController";
	else if(pid == hpid){
		document.menus.action="<%=basePath%>HomeDisplayController";		
	}--%>
	document.menus.action = "<%=basePath%>CommonDisplayController";
	document.menus.submit();
	return false;
}

//GOES TO COPY QUOTE FOR CORRESPONDING PRODUCTS
function copyQuote(hpid)
{ 
	var pid = document.getElementById("productid").value;
	document.menus.DisplayValue.value='copyquote';
	<%--if(pid == "31"||pid == "22" || "T"=="<%=typeOfProduct %>")
	{
		document.menus.action="<%=basePath%>CopyQuote/TravelCopyQuote.jsp";
	}
	else if(pid == hpid)
	{
		document.menus.action="<%=basePath%>CopyQuote/CopyQuote.jsp";		
	}--%>
	document.menus.action = "<%=basePath%>CommonDisplayController";
	document.menus.submit();
	return false;
}

function declaration(hpid)
{ 
	var pid = document.getElementById("productid").value;
	if(pid == hpid)
	{
		//document.menus.action="<%=basePath%>CopyQuote/CopyQuote.jsp";
		document.menus.action="<%=basePath%>showMultipleQuotes.jsp";
		document.menus.submit();
	}
	return false;
}

function GoToContactUS(hpid)
{
   var pid = document.getElementById("productid").value;
   if(pid == "31"||pid == "22" )//|| "T"=="<%=typeOfProduct %>")
      document.menus.action="<%=basePath%>TravelInsurance/ContactUs.jsp";
   else if(pid == hpid)  
      document.menus.action="<%=basePath%>HomeInsurance/ContactUs.jsp";
   document.menus.submit();
   return false;  
}

function Referral(hpid)
{
	var pid = document.getElementById("productid").value;
	document.menus.DisplayValue.value='referralPolicyUnApprove';
	<%--if(pid == hpid)
	{
		document.menus.action="<%=basePath%>HomeDisplayController";		
	}
	if(pid == "31" || pid == "22"  || "T"=="<%=typeOfProduct %>")
	{
		document.menus.action="<%=basePath%>TravelDisplayController";		
	}--%>
	document.menus.action = "<%=basePath%>CommonDisplayController";
	document.menus.submit();
	return false;
}
function liveHelp()
{
	window.open('http://server.iad.liveperson.net/hc/52599130/?cmd=file&file=visitorWantsToChat&site=52599130&SESSIONVAR!operator=Lalith&imageUrl=http://server.iad.liveperson.net/hcp/Gallery/ChatButton-Gallery/English/General/3a&referrer='+escape(document.location),'chat52599130','width=475,height=400,resizable=yes');
	return false;
}

function stopRKey(evt) 
{ 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 
document.onkeypress = stopRKey; 

</script>
</html>