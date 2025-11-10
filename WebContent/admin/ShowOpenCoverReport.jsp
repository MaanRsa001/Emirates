 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ include file="header.jsp" %>

<HTML>
<HEAD>
<TITLE></TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="../css/style.css" rel="stylesheet" type="text/css">
</HEAD>

	<jsp:useBean id = "broker" class = "com.maan.admin.DAO.DatewiseBrokerDetails">
	<jsp:setProperty name = "broker" property = "*"/>
	</jsp:useBean>

	<%
		/**Decimal Place**/
	String decimalPlace="";
	HashMap broDetails = new HashMap();

	if(session.getAttribute("BrokerDetails") !=null)
		broDetails = (HashMap) session.getAttribute("BrokerDetails");
	if(broDetails.size() >0)
		decimalPlace =(String) broDetails.get("CurrencyDecimal");

	decimalPlace = decimalPlace == null ? "2" :decimalPlace;
/**Decimal Place**/

	java.text.DecimalFormat fmt = null;

	if(decimalPlace.equalsIgnoreCase("2"))
		fmt=new java.text.DecimalFormat("##,##0.00");
	else if(decimalPlace.equalsIgnoreCase("3"))
		fmt=new java.text.DecimalFormat("##,##0.000");
	else if(decimalPlace.equalsIgnoreCase("4"))
		fmt=new java.text.DecimalFormat("##,##0.0000");
	else
		fmt=new java.text.DecimalFormat("##,##0.00");

		String brokerName[][] = new String[0][0];
		int policyTotal=0;
		double premiumTotal=0;
		double commissionTotal=0;
		double pro_ComTotal=0;
		String broNam="";
	    String brokerIds = request.getParameter("brokerIds")==null?"":request.getParameter("brokerIds");
		
		String sdate = "";
		String edate = "";
		//String mississipi_No="";
		String mississipi_No[][]=new String[0][0];
		String information[][] = new String [0][0];
		String info[][] = new String [0][0];
				
		if(request.getAttribute("sdate")!=null)
			sdate = (String)request.getAttribute("sdate");
		if(request.getAttribute("edate")!=null)
			edate = (String)request.getAttribute("edate");

		if(request.getAttribute("information")!=null)
			information = (String[][])request.getAttribute("information");
			
		brokerName = (String[][])session.getAttribute("brokerName");
		
		for(int i=0;i<brokerName.length;i++)
		{
			if(brokerIds.equalsIgnoreCase(brokerName[i][1]))
				broNam = brokerName[i][0];
		}
	%>
	<br/>
	<br/><br/>
<BODY>
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
            <td align="center" valign="top">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td width="90%" class="heading"><strong>OPEN COVER REPORTS</strong></td>
</tr><tr align="center">
<td colspan="3">&nbsp;</td>
</tr><tr align="center"><td colspan="3">
<table width="100%"  border="0" cellspacing="1" cellpadding="0" class="blueborder">
<tr> <td align="center" class="ltbgyellow">
<%@ include file="admin_sub_menu_reports.jsp" %>
<form name="brokerRptForm" method="post" action="ShowOpenCoverDatewiseRpt.jsp">
<table align="center" border="0" cellpadding="0" cellspacing="1" width="100%" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
<% if(!brokerIds.equalsIgnoreCase("all"))
	{
%>
<tr class="blueborder">
<td align="center">Broker Name </td> 
<td align="center"><%=broNam%></td><td></td><td></td>
</tr>
<%	
	}
%>
<tr class="royamenuhead"><td class="bottomtext" align="center">Start Date : <%=sdate%></td><td class="bottomtext"></td><td class="bottomtext"></td>
<td class="bottomtext" align="center">End Date : <%=edate%></td></tr>
<tr><!-- line break--></tr>
</table>
<table align="center" border="0" cellpadding="0" cellspacing="1" width="100%" style="align:justify; 
font-family:Arial;font-size:12px;font-weight:normal;">
<% 
	if (information.length > 0 )//&& info.length > 0)
	{	
		/*out.println("information.length"+information.length);
		out.println("info .length"+info.length);*/
	
%>
		<tr class="royamenuhead">
		<td class="bottomtext" align="center" width="5%">S.No</td> 
		<% if(brokerIds.equalsIgnoreCase("all"))
			{
		%>
		<td class="bottomtext" align="center">Broker Name</td> 
		<%	
			}
		%>
		<td class="bottomtext" align="center">Customer name</td> 
		<td class="bottomtext" align="center">Core Application Policy No </td> 
		<td class="bottomtext" align="center">Total Number Of Policies </td>
		<td class="bottomtext" align="center">&nbsp;&nbsp;Total Premium </td>
		<td class="bottomtext" align="center">&nbsp;&nbsp;Commission </td>
		<!--  <td class="bottomtext" align="center">&nbsp;&nbsp;Promotional Commission</td> -->
		</tr>
		<%	try{
			for(int i=0;i<information.length;i++)
			{	
				mississipi_No = broker.getMississipiNo(information[i][0]);
				
				if(mississipi_No != null && mississipi_No.length > 0)
				{	
					mississipi_No[0][0] = mississipi_No[0][0]==null?"":mississipi_No[0][0];
					mississipi_No[0][1] = mississipi_No[0][1]==null?"":mississipi_No[0][1];
					mississipi_No[0][2] = mississipi_No[0][2]==null?"":mississipi_No[0][2];
					mississipi_No[0][3] = mississipi_No[0][3]==null?"":mississipi_No[0][3];
				
				
				
				
		%> 
			<tr class="formtxtr" height="20">	
				<td align="center"> <%= i+1 %> </td>
				<% if(brokerIds.equalsIgnoreCase("all"))
					{
				%>
				<td align="left"> <%= mississipi_No[0][0] %></td>
				<%
					}
				%>
				<td align="left"><%= mississipi_No[0][3] %></td>
				<td align="center"><%= mississipi_No[0][1] %> </td>
				<td align="center"><%= information[i][1]==null?"" :information[i][1] %> </td>
				<td align="right"> <%= fmt.format(Double.parseDouble(information[i][2]==null?"0":information[i][2])) %> </td>
				<td align="right"> <%= fmt.format(Double.parseDouble(information[i][3]==null?"0":information[i][3])) %> </td>
				<!--  <td align="right"> <%= fmt.format(Double.parseDouble(information[i][4]==null?"0":information[i][4])) %> </td> -->
			</tr>
			
			
		<%		
		}
	
				//if(i==2) return;
				
				policyTotal = policyTotal + Integer.parseInt(information[i][1]==null?"0":information[i][1]);
				
	  			premiumTotal = premiumTotal + Double.parseDouble(information[i][2]==null?"0":information[i][2]);
	  			
			    commissionTotal = commissionTotal + Double.parseDouble(information[i][3]==null?"0":information[i][3]);
			    
			    pro_ComTotal = pro_ComTotal + Double.parseDouble(information[i][4]==null?"0":information[i][4]);
			    
			    
			    
			    
				
		   }
		   }
		   catch(Exception ex){
		     ex.printStackTrace();
		   }
		   //out.println("mississipi_No - 12  "+mississipi_No);
					//if(true) return; //for
		   
		 
		
		 
		%>
		<tr class="royamenuhead" height="10"><td class="bottomtext"></td>
		<% if(brokerIds.equalsIgnoreCase("all"))
			{
		%>
		<td class="bottomtext"></td>
		<%
			}
		%>
		<td class="bottomtext"></td>
		<td class="bottomtext" align="center">Total</td>
		
		<td class="bottomtext" align="center"><%=policyTotal%></td>
		
		<td class="bottomtext" align="right"><%=fmt.format(premiumTotal)%></td>
		<td class="bottomtext" align="right"><%=fmt.format(commissionTotal)%></td>
		<!--  <td align="right"><%//=fmt.format(pro_ComTotal)%></td> -->
		<tr>
	<% 
	
		
		}
		else
		{ 
	%>		
			<tr class="blueborder" align="center"> <td> NO RECORDS IN THIS PERIOD </td></tr>
	  <% } %>
<tr>
<td height="32" valign="middle" align="center" colspan="6"><br><input name="image" type="image"  src='../images/Cancel.jpg'/ >
</table>
<input type="hidden" name="requestfrom" value="brokerRpt">
</form>
</BODY>
</HTML>
