
<jsp:directive.page import="java.util.*"/>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.maan.product.ProductSelection" isELIgnored="false"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.maan.report.dao.ReportDAO"%>
<% 
String productSelection = request.getParameter("productSelection")==null?"":request.getParameter("productSelection");
//if((request.getQueryString())!=null&&!productSelection.equalsIgnoreCase("productSelection"))
if((request.getQueryString())!=null&&!productSelection.equalsIgnoreCase("productSelection") && !"rsa".equals((String)session.getAttribute("mode")))
{
	//session.removeAttribute("ses");
}
%>
<%@ include file="login/home1.jsp" %>
<%@ include file="login/sessionsCheckNormal.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
		String productTypeId="";
		String QuoteOpt="";
		QuoteOpt=request.getParameter("QuoteOpt")==null?"":request.getParameter("QuoteOpt");
		productTypeId=request.getParameter("selectProd")==null?(String) session.getAttribute("product_id")==null?"0":(String) session.getAttribute("product_id"):request.getParameter("selectProd");

		if("0".equalsIgnoreCase(productTypeId))
			response.sendRedirect("../login/error_messg.jsp");
		else
			session.setAttribute("product_id",productTypeId);
			
		String userTypes = (String)session.getAttribute("usertype");
		String loginId = (String)session.getAttribute("user");
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="5%" >&nbsp;</td><td  width="90%" align="center">
<%@include file="openMenu.jsp"%>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/displaytag.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
a{
	text-decoration:none;
}
-->

</style>
</head>
<%
	
	String dtdiff=request.getParameter("dtdiff")==null?"":request.getParameter("dtdiff");
	String searchby=null;
	searchby=request.getParameter("searchby")==null?"":request.getParameter("searchby");
	String  customer_names=null;
	customer_names=request.getParameter("customer_names")==null?"":request.getParameter("customer_names");
	String  search_option=null;
	search_option=request.getParameter("search_option")==null?"":request.getParameter("search_option");
	
	int no_of_records=10;
	int lapsed_no_of_records=10;
	
	int displaypages=5;
	int samplepages=displaypages;
	int lapsed_displaypages=5;
	int lapsed_samplepages=lapsed_displaypages;
	
	if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
		displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
	session.setAttribute("company_id","1");
	String cusName=null;
	cusName=loginId;
	
		 if(request.getParameter("selectUser")!=null&&!"select".equalsIgnoreCase(request.getParameter("selectUser")))
		 {
			cusName=request.getParameter("selectUser");
		  }
		  else
		  {
	        cusName=loginId;
			session.removeAttribute("userName");
		  }
	
		com.maan.services.util.dataCollection collect=null;
		collect = new com.maan.services.util.dataCollection();
		com.maan.premium.DAO.PremiumInputsBean prem = null;
		prem = new com.maan.premium.DAO.PremiumInputsBean();

		String[][] viewQuote123=new String[0][0];
		String[][] viewQuote124=new String[0][0];
		
		String DateValid=null;
		String rsaIssuer = (String)session.getAttribute("RSAISSUER");
		
		/*if(rsaIssuer!=null)
			userTypes = rsaIssuer;*/
		
		if("DateWise".equalsIgnoreCase(searchby) && "Invalid".equalsIgnoreCase(collect.checkDate(customer_names)) )
		{
			DateValid="* Search Date Invalid ";
			viewQuote123=collect.getCreatedOpenCoversSearch(cusName,"select",customer_names,userTypes,(String)session.getAttribute("LoginBranchCode"), rsaIssuer);
		}
		else
		{
			viewQuote123=collect.getCreatedOpenCoversSearch(cusName,searchby,customer_names,userTypes,(String)session.getAttribute("LoginBranchCode"), rsaIssuer);
		}
		Map yetStartMOC = new HashMap();
		String ymocList = "";
		
		if(viewQuote123.length > 0)
		{
			ymocList = prem.removeLastChar(prem.getQuotedStringFromArray(viewQuote123,0),',');//String array,index value
			if(ymocList.length() > 0)
				yetStartMOC = prem.getYetToStratMOC(ymocList);
		}
	String[][] loginCustomers = new String[0][0];
	if(rsaIssuer==null)
		loginCustomers=collect.getNewLogIds(loginId);
	viewQuote124 = collect.getLapsedOpenCoversSearch(cusName,searchby,customer_names);
%>

<table width="80%" align="center" border="0" cellpadding="0" cellspacing="1">

	<%   int length124=0;
			if(viewQuote124.length==0)
					length124=1;
			else
				length124=viewQuote124.length;

			int lapsed_pages=length124/lapsed_no_of_records;
			int lapsed_rem=length124%lapsed_no_of_records;
			
			if(lapsed_rem!=0)
				lapsed_pages=lapsed_pages+1;

			int lapsed_display=0;
			int lapsed_spage=1;
			int  lapsed_start=0;

			if(request.getParameter("lapsed_spage")!=null&&!request.getParameter("lapsed_spage").equalsIgnoreCase(""))
				lapsed_spage=request.getParameter("lapsed_spage")==null?1:Integer.parseInt(request.getParameter("lapsed_spage"));
			if(request.getParameter("lapsed_start")!=null&&!request.getParameter("lapsed_start").equalsIgnoreCase(""))
				lapsed_start=request.getParameter("lapsed_start")==null?0:Integer.parseInt(request.getParameter("lapsed_start"));
			lapsed_display=lapsed_no_of_records*lapsed_spage;
			if(lapsed_spage >= lapsed_displaypages)
			{
				if(lapsed_pages>lapsed_displaypages)
				{
					lapsed_start++;
					lapsed_displaypages++;
				}
			}
			else if((lapsed_displaypages-lapsed_spage)==(lapsed_samplepages-1)&& lapsed_start!=0)
			{
					lapsed_start--;
				lapsed_displaypages--;
			}
			if(viewQuote124.length>0)
			{
			%>
			<table width="98%"  border="0" cellspacing="0" cellpadding="0" align="center">
			<tr></tr>
			<tr ><TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
                            <tr> 
                              <td width="18%" align="left"><span class="heading">LAPSED OPEN COVERS</span></td>
							</tr>
					</table>							
					<table width="100%"  border="0" cellspacing="1" cellpadding="0">
                            <tr class="royamenuhead"> 
                              <td class="bottomtext">S.No</td>
							  <td class="bottomtext">Core Application Policy No. </td>
                              <td class="bottomtext">OpenCover No. </td>
                              <td class="bottomtext">Customer Name </td>
							  <td class="bottomtext">OpenCover Date </td>
                              <td class="bottomtext">Validity Period </td>
                              <td class="bottomtext">Remarks</td>
                              <td class="bottomtext">Certificate</td>
                               <td class="bottomtext">View Certificate</td>
							</tr>
													
						<%
							 int lapsed_k=0;
							 int lapsed_skip=0;
							 int lapsed_count=0;
						for(int i=0;i<viewQuote124.length;i++)
						{
								int k=i+1;lapsed_k++;
								if(lapsed_spage>1)
								{
									 lapsed_skip=lapsed_spage-1;
						             if(k<=(lapsed_skip*lapsed_no_of_records))
													continue;
								}
		
								String DateEntry	=	viewQuote124[i][2]==null?"":viewQuote124[i][2];
								String MonthEntry	=	viewQuote124[i][3]==null?"":viewQuote124[i][3];
								String YearEntry	=	viewQuote124[i][4]==null?"":viewQuote124[i][4];
								String DateExp		=	viewQuote124[i][5]==null?"":viewQuote124[i][5];
								String MonthExp	=	viewQuote124[i][6]==null?"":viewQuote124[i][6];
								String YearExp		=	viewQuote124[i][7]==null?"":viewQuote124[i][7];

								String adminState = viewQuote124[i][15]==null?"":viewQuote124[i][15];
								String lapsRemarks="";
								if(adminState.equalsIgnoreCase("N"))
									lapsRemarks = "Admin Cancelled";
								else
									lapsRemarks = "Date Expired";
						%>
						
                            <tr> 
                              <td class="formtxtc"> <%=k%></td>
							  <td class="formtxtc"><%=viewQuote124[i][14]==null?"":viewQuote124[i][14]%></td>
                              <td class="formtxtc"><%=viewQuote124[i][0]==null?"":viewQuote124[i][0]%></td>
                              <td class="formtxtf"><%=viewQuote124[i][8]==null?"":viewQuote124[i][8]%></td>
                              <td class="formtxtc"><%=DateEntry+"/"+MonthEntry+"/"+YearEntry%></td>
                              <td class="formtxtc"><%=DateExp+"/"+MonthExp+"/"+YearExp%></td>
                              <td class="formtxtc"><%=lapsRemarks%></td>
                              <td class="formtxtc">
                              <%--<%if(Integer.parseInt(viewQuote124[i][16])<60){ --%>
                              	<a href="#" class="two"  onClick="return newQuote('<%=viewQuote124[i][0]%>','<%=productTypeId%>','<%=viewQuote124[i][1]%>','<%=QuoteOpt%>')"><b> Certificate</b> </a>
                             <%-- <%}else{%>
                              	N/A
                              <%} %>--%>
                              </td>
                              <td class="formtxtc">
                              <a href="#" class="two" name="viewCert" id="viewCert" onClick="return viewcert('<%=viewQuote124[i][14]==null?"":viewQuote124[i][14]%>','<%=productTypeId%>','<%=cusName%>')"><b>View Certificate</b> </a>
                              </td>
 		<%
							if(lapsed_k == lapsed_display)
								  break;
						}
						if(viewQuote124.length==0)
						{
	%>
							<tr align="center" ><td colspan="6"><B>
							<font size=2 color="red">THERE IS NO LAPSED OPEN COVERS FOR THIS USER</B>
							</td></tr>
	<%				
						}
                            
							 if(length124>lapsed_no_of_records)
							 {
			%>
								<tr class="royamenuhead">
							 	<td height="12" align="right" colspan="8">
			<%
								if(lapsed_start>0)
								{
	%>
								
								<a href ="javaScript:lapsed_ExistingCustomers_B2B('<%=(lapsed_start+1)%>','<%=lapsed_displaypages%>','<%=lapsed_start%>')">
								<font  color="white"><<</font>&nbsp;&nbsp;</a>
	<%
							}
							 boolean lapsed_flag=false;
							 int lapsed_iValue=0;
							for(int i=lapsed_start;i<lapsed_pages;i++)	 
							{
								if(i<lapsed_displaypages)
								{
	%>
						<a href ="javaScript:lapsed_ExistingCustomers_B2B('<%=i+1%>','<%=lapsed_displaypages%>','<%=lapsed_start%>')">
						<font  color="white"><%=i+1%></font></a>
	<%
								}
								 else
								{
									 lapsed_flag=true;
									 lapsed_iValue=i;
									 break;
								}
						}	 
						if(lapsed_flag)
						{
		%>					
							<a href ="javaScript:lapsed_ExistingCustomers_B2B('<%=lapsed_iValue%>','<%=lapsed_displaypages%>','<%=lapsed_start%>')">
							&nbsp;&nbsp;<font color="white">>></font></a>
		<%
						}
		%>
					</td>
					</tr>
		 <%
					 }
		 %>
	 				
					</table>	
					</TR>
					</TABLE>
<%
	} // Lapsed Open Cover If Length Ends here
int length123=0;
if(viewQuote123.length==0)
{
	length123=1;
}
else
{
	length123=viewQuote123.length;
}
int pages=length123/no_of_records;

int rem=length123%no_of_records;
if(rem!=0)
{
pages=pages+1;
}

int display=0;
int spage=1;
int  start=0;

if(request.getParameter("spage")!=null&&!request.getParameter("spage").equalsIgnoreCase(""))
spage=request.getParameter("spage")==null?1:Integer.parseInt(request.getParameter("spage"));

if(request.getParameter("start")!=null&&!request.getParameter("start").equalsIgnoreCase(""))
start=request.getParameter("start")==null?0:Integer.parseInt(request.getParameter("start"));
display=no_of_records*spage;
if(spage>=displaypages)
{
	if(pages>displaypages)
	{
start++;
displaypages++;
	}
}
else
if((displaypages-spage)==(samplepages-1)&&start!=0)
{
	start--;
displaypages--;
}
/****************pagenaaaaaaaaaa************/
	if(!"select".equalsIgnoreCase(request.getParameter("selectUser")) && request.getParameter("selectUser")!=null)
		session.setAttribute("userName",request.getParameter("selectUser"));

	if(session.getAttribute("userName")==null)
		session.setAttribute("userName",cusName);
	cusName = (String)session.getAttribute("userName");

try
   {
        if(session.getAttribute("applicationNo") !=null)
			session.removeAttribute("applicationNo");
        
        if(session.getAttribute("countryDetails") !=null)
			session.removeAttribute("countryDetails");
        
        if(session.getAttribute("quote_no") !=null)
        session.removeAttribute("quote_no");

		if(session.getAttribute("openCoverNo") !=null)
			session.removeAttribute("openCoverNo");

		if(session.getAttribute("customer_id") !=null)
		 session.removeAttribute("customer_id");
        
        
        if(session.getAttribute("fullCommodity") !=null)
			session.removeAttribute("fullCommodity");
        
        if(session.getAttribute("fullDetails") !=null)
			session.removeAttribute("fullDetails");

		if(session.getAttribute("fullDetailss") !=null)
			session.removeAttribute("fullDetailss");
        
        if(session.getAttribute("sessionCountry") !=null)
			session.removeAttribute("sessionCountry");
        
        if(session.getAttribute("fromCountryId") !=null)
			session.removeAttribute("fromCountryId");
        
         if(session.getAttribute("toCountryId") !=null)
			session.removeAttribute("toCountryId");
        
   }catch(Exception ex){}

	%>



<body onLoad="datealert('<%=dtdiff%>')">

<form name="ViewOpenCover" method="post">
		<input type="hidden" name="brokerName" value='<%=cusName%>'/> 
		<input type="hidden" name="custName" id="custName"/>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr align="center">
				<td><font color="red"><%=DateValid==null?"":DateValid%></font></td>
			</tr>
			
          <tr align="center">
            <td colspan="3">
							  <input type="hidden" name="quoteIds">
							  <input type="hidden" name="mode">
							  <input type="hidden" name="quote_no">
							  <input type="hidden" name="openCoverNo">
							  <input type="hidden" name="originalOpenCoverNo">
							  <input type="hidden" name="openCustomerId">
							  <input type="hidden" name="freshMode">
							  <input type="hidden" name="openCover">
					<table width="100%">
					<tr>
					<td></td>
					<td>
					<table style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
						</tr>
						<td align="right" class='text'>Search By
					 </td>
					 <td align="left">
					  <select name="searchby" onChange="setData()">
					 <option value="Select">All </option>
					<option value="FIRST_NAME" <%=("FIRST_NAME").equalsIgnoreCase(searchby)?"Selected":""%>>Customer Name</option>
					<option value="opencover_nos" <%=("opencover_nos").equalsIgnoreCase(searchby)?"Selected":""%>>Open Cover Number</option>
					<option value="DateWise" <%=("DateWise").equalsIgnoreCase(searchby)?"Selected":""%>>Date wise</option>
					</select>
					</td>
					<td align="right" class='text'>Enter Data For Search 
					</td>
					<td align="right">
					<input type="text" name="customer_names" value="<%=customer_names%>" onsubmit='return focus1()'>
					</td>
					<td>
					<input type='image' src="<%=path%>/images/Go.jpg" onclick="return search_details()"  id='go'></td>
					</tr>
					</table>
					</td>
					</tr>
					<tr>
					<%if(rsaIssuer!=null){
					String[][] Broker = new ProductSelection().getBrokersList((String)session.getAttribute("LoginBranchCode"), rsaIssuer);
					%>
					<td colspan="8" align="right">
					Select Broker:
					<select name="selectUser" onChange="samePage()">
						<option value="0">
							Select
						</option>
						<% for (int i = 0; i < Broker.length; i++) {%>
						<option value="<%=Broker[i][0]%>" <%=cusName.equalsIgnoreCase(Broker[i][0])?"selected":""%>><%=Broker[i][1]%>(<%=Broker[i][0]%>)
						</option>
						<%}%>
					</select>
					<% for (int i = 0; i < Broker.length; i++) {%>
						<input type="hidden" value="<%=Broker[i][2]%>" id="<%=Broker[i][0]%>" title="<%=Broker[i][3]%>"/>
					<%}%>	
					</td>
					<%}%>
					<td class='text'>&nbsp;&nbsp;User Name   <%=cusName%></td>
					 <%
					  if("Broker".equalsIgnoreCase((String)session.getAttribute("usertype")))
					  {
					 if(loginCustomers.length>0)
				{%>
						<td align="right" class='text'> Select User <select class="ltbgyellow" name="selectUser" onChange="samePage()">
						<option value="select">Select</option>
						<%for(int i=0;i<loginCustomers.length;i++)
						{
						%>
						<option value="<%=loginCustomers[i][0]%>" <%=cusName.equalsIgnoreCase(loginCustomers[i][0])?"selected":""%>><%=loginCustomers[i][0]%></option>
						<%}%>
						</select></td>
					<%}
					  }%>
					</tr>
			  </table>
			</td>
	      </tr>
        <tr align="center">
        <td colspan="3">
		<table width="100%"  border="0" cellspacing="1" cellpadding="0" >
        <tr>
        <td align="center" valign="top" bgcolor="#FFFFFF">
        <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr> 
        <td width="18%" align="left"><span class="heading" >EXISTING OPEN COVERS</span></td>
		</tr>
		</table>							
		<table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr class="royamenuhead"> 
        <td class="bottomtext">S.No</td>
        <td class="bottomtext">Core Application Policy No. </td>
        <td class="bottomtext">OpenCover No. </td> 
        <td class="bottomtext">Customer Name </td>
        <td class="bottomtext">OpenCover Date </td>
        <td class="bottomtext">Validity Period </td>
        <td class="bottomtext">Balance Sum Insured</td>
        <% if("admin".equalsIgnoreCase(userTypes)||"RSAIssuer".equalsIgnoreCase(userTypes)) { %>
        <td class="bottomtext">Schedule</td>
        <td class="bottomtext">Policy Wordings</td>
        <% } %>
        <td class="bottomtext">Certificate</td>
	<%
		if(!QuoteOpt.equalsIgnoreCase("None"))
		{
	%>
        <td class="bottomtext">LC Details</td>
	<%
		}
	%>
		</tr>
	<%
    int k=0;
	int skip=0;
	int count=0;
	for(int i=0;i<viewQuote123.length;i++)
	{
		k++;
		 if(spage>1)
		{
			 skip=spage-1;
             if(k<=(skip*no_of_records))
			 {
				continue;
			}
		}
		String str="";
		if(viewQuote123[i][11]==null||viewQuote123[i][11].equals("")||viewQuote123[i][11].equalsIgnoreCase("null"))
		{
           str=(viewQuote123[i][8]==null?"":viewQuote123[i][8])+" "+(viewQuote123[i][9]==null?"":viewQuote123[i][9]);
		}
		else
		{
           str=viewQuote123[i][11];
		}
%>
							  <tr> 
                              <td class="formtxtc"><%=k%></td>
							  <%--<td class="formtxtc"><%=viewQuote123[i][14]==null?"":viewQuote123[i][14]%></td>
                              <td class="formtxtc"><%=viewQuote123[i][0]==null?"":viewQuote123[i][0]%></td>--%>
							  <td class="formtxtc"><%=viewQuote123[i][15]==null?"":viewQuote123[i][15]%></td>
                              <td class="formtxtc"><%=viewQuote123[i][15]==null?"":viewQuote123[i][15]%></td>
                              <td class="formtxtf"><%=viewQuote123[i][8]==null?"":viewQuote123[i][8]%></td>
                              <td class="formtxtc"><%=viewQuote123[i][2]%>/<%=viewQuote123[i][3]%>/<%=viewQuote123[i][4]%></td>
                              <td class="formtxtc"><%=viewQuote123[i][5]%>/<%=viewQuote123[i][6]%>/<%=viewQuote123[i][7]%></td>
                              <td class="formtxtc"><%=viewQuote123[i][16]%></td>
                               <% if("admin".equalsIgnoreCase(userTypes)||"RSAIssuer".equalsIgnoreCase(userTypes)) { %>
                              <td class="formtxtc">
                              	<a href="#" title="View Schedule" class="two" onclick="return viewDoc('<%=viewQuote123[i][15]%>','<%=(String)session.getAttribute("user")%>','schedule','<%=viewQuote123[i][15]%>','','<%=viewQuote123[i][12]%>','false');" > <b> Schedule </b></a>
			 				  </td>
			 				  <td class="formtxtc">
                              	<a href="#" title="Policy Wordings" class="two" onclick="return viewDoc('<%=viewQuote123[i][15]%>','<%=(String)session.getAttribute("user")%>','clauses','<%=viewQuote123[i][15]%>','','<%=viewQuote123[i][12]%>','false');" > <b> View </b></a>
			 				  </td>
                              <% } %>
                              <td class="formtxtc">
                        
   <%		
   		String newmode = (String)request.getAttribute("mode");
   		String val = (String)session.getAttribute("identify_Id");
   		if("1".equalsIgnoreCase(newmode))
   		  {
   	%>
   		  <%--<a href="#" class="two" title="New Quote" onClick="return newQuotes('<%=viewQuote123[i][0]%>','<%=productTypeId%>','<%=val%>','<%=QuoteOpt%>')"><b> Certificate</b> </a>--%>
   		  <a href="#" class="two" title="New Quote" onClick="return newQuote('<%=viewQuote123[i][0]%>','<%=productTypeId%>','<%=cusName%>')"><b> Certificate</b> </a>
 	<%
   		  }
   		  else
   		  {
   		  	if(!yetStartMOC.isEmpty() && yetStartMOC.get("moc"+viewQuote123[i][0]) != null && yetStartMOC.get("moc"+viewQuote123[i][0]).equals(viewQuote123[i][0]) )
   		  	{
   %>
   		  	<a href="#" class="two" title="Certificate not Started"> <b> Yet to Start </b></a>
   <% 
   			}
   			else
   			{
   	%>
   			<%--<a href="#" class="two" title="New Quote" onClick="return newQuotes('<%=viewQuote123[i][0]%>','<%=productTypeId%>','<%=viewQuote123[i][1]==null?newmode:viewQuote123[i][1]%>','<%=QuoteOpt%>')"> <b> Certificate </b></a>--%>
   			<a href="#" class="two" title="New Quote" onClick="return newQuote('<%=viewQuote123[i][0]%>','<%=productTypeId%>','<%=cusName%>','<%=viewQuote123[i][1]==null?newmode:viewQuote123[i][1]%>')"><b> Certificate</b> </a>
   	<%
   			}
      	 }
    %>
   </td>
   <%
		if(!QuoteOpt.equalsIgnoreCase("None"))
		{
	%>
         <td class="formtxtc"> <a href="#" class="two" title="LC Details" onClick="return openLCDetailsTest('<%=cusName%>','<%=viewQuote123[i][0]%>','<%=viewQuote123[i][8]%>');"> <b>LC Details </b> </a> </td>
	<%
		}
	%>
                         </tr>
						  <%
							  if(k==display)
								  break;
							}
						  if(viewQuote123.length==0)
							{
				   %>
							<tr align="center" ><td colspan="6"><B><font size=2 color="red">THERE IS NO OPEN COVERS FOR THIS USER</B>
							</td></tr>
					<%
							}
							if(length123>no_of_records)
							 {
					%>
 							 <tr class="royamenuhead">
							 	<td height="12" align="right" 
							 	colspan="<%=(("admin".equalsIgnoreCase(userTypes)||"RSAIssuer".equalsIgnoreCase(userTypes))?"11":"9") %>">
					<%
								if(start>0)
								{
					%>
								<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>')">
								<font  color="blue"><<</font>&nbsp;&nbsp;</a>
					<%
							}
							 boolean flag=false;
							 int iValue=0;
							for(int i=start;i<pages;i++)	 
							{
								if(i<displaypages)
								{
					 %>
						<a href ="javaScript:ExistingCustomers_B2B('<%=i+1%>','<%=displaypages%>','<%=start%>')">
						<font  color="blue"><%=i+1%></font></a>
					 <%
								}
								 else
								{
									 flag=true;
									 iValue=i;
									 break;
								}
						}	 
						if(flag)
						{
							 %>
<a href ="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>')">&nbsp;&nbsp;<font  color="blue">>></font></a>
							<%
						}
			%>
					</td>
	    	        </tr>
			<%
				 }
			 %>
			 
			<tr><td colspan="6">&nbsp;</td></tr>
			
            </table></td>
              </tr>
              <%if((String) session.getAttribute("RSAISSUER")!=null){%>
              <tr align="center" height=25>
						<td colspan=2>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="98%" colspan="3" align="left">
							&nbsp;&nbsp;&nbsp;
							<span class="heading">ENDORSEMENT SEARCH</span>
						</td>
					</tr>
					<tr align="center" height=20>
						<td colspan=2>
							&nbsp;
						</td>
					</tr>
					<tr align="center">
					<td colspan="3" width="98%">
						<table width="100%" border="0" cellpadding="2" cellspacing="2" height="40px">
							<tr >
								<td align="center" valign="middle">
									<b>Search By Policy No&nbsp;&nbsp;</b>
									<input type="text" size="25" class="fde1" maxlength='50' id="policyNo"  name="searchPolicyNo" style="width: 200px;" value="<%=StringUtils.defaultIfEmpty(request.getParameter("searchPolicyNo"),"") %>"/>&nbsp;&nbsp;
									<input name="image" type="image" src='<%=path%>/images/Search.jpg' onclick="return searchByPolicy()"  style="margin-bottom: -5px">
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
									String policyNo=StringUtils.defaultIfEmpty(request.getParameter("searchPolicyNo"),"");
									if(StringUtils.isNotEmpty(policyNo)){
										request.setAttribute("policyList", new ReportDAO().getSearchResult(policyNo,(String) session.getAttribute("LoginBranchCode"), productTypeId));
									}
								%>
								<table border="1" width="98%" cellpadding="2" cellspacing="0">
									<tr >
										<td align="center" width="100%">
											<display:table name="policyList" pagesize="15" requestURI="/ViewOpenCovers.jsp" class="table" uid="row" id="record" style="width:100%;" >
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.no_items_found" value="" />
												<display:setProperty name="paging.banner.placement" value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<display:setProperty name="basic.msg.empty_list" value="<span style=font-size:12px >No data found</span>" />
												<display:setProperty name="basic.empty.showtable" value="true"/>
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
								<input type="hidden" name="selectProd" value="<%=productTypeId %>">
								<input type="hidden" name="searchFrom" value="BS">
								<input type="hidden" name="searchValue">
								<input type="hidden" name="policyNo">
							</td>
						</tr>
						<%}%>
						<tr align="center" >
							<td colspan=2>
								&nbsp;
							</td>
						</tr>
              
            </table></td>
          </tr>
          
          <tr align="center">
            <td colspan="3">
			<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			<%
				if(productSelection.equalsIgnoreCase("productSelection"))
				{
			%>
				 <td valign="middle" ><a href="<%=path%>" class="buttonsMenu">
				 <img src="<%=path%>/images/Back.jpg"> </td>
			<%
				}
				else
				{
			%>
				<td valign="middle" ><a href="<%=path%>/HomeUser.action" class="buttonsMenu">
				<img src="<%=path%>/images/Back.jpg"></a> 
				<%if((String) session.getAttribute("RSAISSUER")!=null){%>
				<input name="image" type="image" src='<%=path%>/images/Proceed.jpg' onclick="return redirect()"></td>
			<%
				}}
			%><td></td>
		   </tr>
			</table>
			</td>
          </tr>
          
        </table>

</td><td width="5%"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
</table>
<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
<input type="hidden" name="identifyCus">
<input type="hidden" name="search_option">
<input type="hidden" name="lapsed_spage">
<input type="hidden" name="lapsed_displaypages">
<input type="hidden" name="lapsed_start">

<input type="hidden" name="opencover">
<input type="hidden" name="openNo">
<input type="hidden" name="lcNos">
<input type="hidden" name="mode">
<input type="hidden" name="lcBroker">
<input type="hidden" name="fromBroker">
<input type="hidden" name="actionPath">
<input type="hidden" name="OpenSesCheck">
<input type="hidden" name="productId">
<input type="hidden" name="loginId">
<input type="hidden" name="brokerCode">
<input type="hidden" name="executive">
<input type="hidden" name="OpenSesCheck">
<input type="hidden" name="renewal">
</form>

</body>
<script>
function stopRKey(evt) 
{ 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 
document.onkeypress = stopRKey;
function samePage()
{
	//document.ViewOpenCover.action="ViewQuote_B2B.jsp";
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp";
	document.ViewOpenCover.submit();
}

function newQuotes(val,productTypeId,cusId,qopt)
{
	document.ViewOpenCover.openCoverNo.value=val;
	document.ViewOpenCover.openCustomerId.value=cusId;
	document.ViewOpenCover.freshMode.value='fresh';
	document.ViewOpenCover.OpenSesCheck.value="NewQuote";
	if(qopt=='None')
		document.ViewOpenCover.action="<%=path%>/final.jsp";
	else
	{
		document.ViewOpenCover.actionPath.value="premium/QuotationOpen.jsp";
		document.ViewOpenCover.action="newquote.dos";
	}
	document.ViewOpenCover.submit();
return false;
}

function delQuotes(val)
{
	document.ViewOpenCover.action="<%=path%>/LapsedQuote1.jsp?Quote_No="+val;
	document.ViewOpenCover.submit();
}
function redirectt(ids)
{

	document.ViewOpenCover.quoteIds.value=ids;
	document.ViewOpenCover.action="<%=path%>/missing.jsp";
	document.ViewOpenCover.submit();
	return false;
}

function ExistingCustomers_B2B(value123,displaypages,start)
{
	document.ViewOpenCover.spage.value=value123;
	document.ViewOpenCover.start.value=start;
	document.ViewOpenCover.displaypages.value=displaypages;
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp"
	document.ViewOpenCover.submit();
}

function focus1()
{
	  var elem = document.getElementById("go");
	  elem.focus();
}
function search_details()
{
	document.ViewOpenCover.search_option.value="YES"
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp"
	document.ViewOpenCover.submit();
   return false;
}
function setData()
{
	var search=document.ViewOpenCover.searchby.value;
	if(search=='Select')
			document.ViewOpenCover.customer_names.value="";
	else if(search=='DateWise')
			document.ViewOpenCover.customer_names.value="DD/MM/YYYY";
	//alert(search);
}

function sentMail(val)//,val1)
{
	document.ViewOpenCover.quote_no.value=val;
	document.ViewOpenCover.action="mailController";
	document.ViewOpenCover.submit();
}

function datealert(dtdiff)
{
		if(dtdiff>=-10 & dtdiff<=-1)
			 alert("You Must Change Your Password After  "+(Math.abs(dtdiff)-1)+" Days");;
		
}

/*function openLCDetails(opencover,lcNos)
{
	document.ViewOpenCover.opencover.value = opencover;
	document.ViewOpenCover.lcNos.value = lcNos;
	document.ViewOpenCover.mode.value = "add";
	document.ViewOpenCover.action="<%=path%>/premium/OpenCoverView.jsp";
	document.ViewOpenCover.submit();
}*/

function openLCDetails(log,open)
{
	document.ViewOpenCover.openNo.value = open;
	document.ViewOpenCover.lcBroker.value = log;
	document.ViewOpenCover.fromBroker.value = "Yes";
	document.ViewOpenCover.action="<%=path%>/LCCreation/LCOpenCoverWise.jsp";
	document.ViewOpenCover.submit();
}

function openLCDetailsTest(log,open,cust)
{
	document.ViewOpenCover.openNo.value = open;
	document.ViewOpenCover.lcBroker.value = log;
	document.getElementById("custName").value=cust;
	document.ViewOpenCover.openCover.value=open;
	document.ViewOpenCover.fromBroker.value = "Yes";
	document.ViewOpenCover.action="lcDetailsLC.action";
	document.ViewOpenCover.submit();
}


function lapsed_ExistingCustomers_B2B(value124,lapsed_displaypages,lapsed_start)
{
	document.ViewOpenCover.lapsed_spage.value=value124;
	document.ViewOpenCover.lapsed_start.value=lapsed_start;
	document.ViewOpenCover.lapsed_displaypages.value=lapsed_displaypages;
	document.ViewOpenCover.action="<%=path%>/ViewOpenCovers.jsp"
	document.ViewOpenCover.submit();
}
function newQuote(openCoverNo, productId, loginId, customerId)
{
	var selected=document.getElementById(loginId);
	if(selected)
	{
		document.ViewOpenCover.brokerCode.value=selected.value;
		document.ViewOpenCover.executive.value=selected.title;
	}	
	document.ViewOpenCover.loginId.value=loginId;
	document.ViewOpenCover.productId.value=productId;
	document.ViewOpenCover.openCoverNo.value=openCoverNo;
	document.ViewOpenCover.openCustomerId.value=customerId;
	document.ViewOpenCover.action="<%=path%>/initReport.action?menuType=CHART";
	document.ViewOpenCover.submit();
	return false;
}
function searchByPolicy()
{
	if(document.ViewOpenCover.searchPolicyNo.value==''){
		alert('Please enter Policy No to search');
		return false;
	}else{
		document.ViewOpenCover.action='<%=path%>/ViewOpenCovers.jsp';
		document.ViewOpenCover.submit();
		return true;
	}
}
function redirect()
{
	if(document.ViewOpenCover.searchValue.value==''){
		alert('Please select Policy No');
		return false;
	}else{
		document.ViewOpenCover.productId.value='<%=productTypeId%>';
		document.ViewOpenCover.action='<%=path%>/initReport.action?menuType=P&searchBy=policyNo';
		document.ViewOpenCover.submit();
		return true;
	}
}
function setPolicyInfo(policyNo, openCoverNo, loginId, openCustomerId)
{
	document.ViewOpenCover.searchValue.value=policyNo;
	document.ViewOpenCover.openCoverNo.value=openCoverNo;
	document.ViewOpenCover.loginId.value=loginId;
	document.ViewOpenCover.openCustomerId.value=openCustomerId;
}




function viewDoc(policynumber,loginId,docType,docNo,amendId,proposalNo,endtstatus)
{
	     var URL ="<%=request.getContextPath()%>/GenerateDocument.jspa?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId+"&proposalNo="+proposalNo+"&endtstatus="+endtstatus;
		 var windowName = "PolicyView";
		 var width  = screen.availWidth;
		 var height = screen.availHeight;
		 var w = 900;
		 var h =	500;
		 var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
		return false;
}
function viewcert(openCoverNo, productId, loginId, customerId)
{

	var selected=document.getElementById(loginId);
	if(selected)
	{
		document.ViewOpenCover.brokerCode.value=selected.value;
		document.ViewOpenCover.executive.value=selected.title;
	}	
	document.ViewOpenCover.loginId.value=loginId;
	document.ViewOpenCover.productId.value=productId;
	document.ViewOpenCover.openCoverNo.value=openCoverNo;
	document.ViewOpenCover.openCustomerId.value=customerId;
	document.ViewOpenCover.action="<%=path%>/initReport.action?menuType=P&menuBlocker=viewcert";
	document.ViewOpenCover.submit();
	return false;
}
</script>
</html>
