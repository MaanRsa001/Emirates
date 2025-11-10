<%@ include file="submenu.jsp"%>
<head>
	<title> Choose Customer</title>
	
<style type="text/css">
	/*
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
}

a:hover {
	color: #003366;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #FFFFFF;
}

a:visited:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	color: #003366;
	text-decoration: none;
}

*/
</style>
 </head>
 <jsp:useBean id="customer" class="com.maan.opencover.bean.customerInfo">
 </jsp:useBean>
 
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/footable-0.1.css" rel="stylesheet" type="text/css">
<%
	String pathq = request.getContextPath();
	String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<%String loginIds = (String) session.getAttribute("user"); %>
<%  if(!"GUEST".equalsIgnoreCase(loginIds)){
	customers = customer.getExistingCustomers(request.getParameter("brokerId").replaceAll("\"","&#34;"));
	}else{
	customers = customer.getOnlineCustomers(request.getParameter("brokerId").replaceAll("\"","&#34;"),request.getParameter("customerId"));
	}
%>
<%!
   String[][] customers = new String[0][0];
%>
  <!-- Assign Values -->
<%
	customers = customer.getExistingCustomers(request.getParameter("brokerId").replaceAll("\"","&#34;"));
%>
 <!-- Assign Values -->

<script>

function setExisting(idd) {	
	if(idd!=null) {
		document.form1.customerName.value = window.opener.form1.customerName.value;
		document.form1.customerId.value = window.opener.form1.customerId.value;
	}
}

function setCustomer(ids) {
	var customername = eval("document.form1.customerName"+ids+".value");
	var customerid = eval("document.form1.customerId"+ids+".value");
	document.form1.customerName.value = customername;
	document.form1.customerId.value = customerid;
}

function newCus() {
 	document.form1.action="newCustomer.jsp?mode=new";
 	document.form1.submit();
}

function settingValues(lengthValue) {	
	if(document.form1.customerId.value != "null") {
		var custId = document.form1.customerId.value;
		var missippi = eval("document.form1.cust"+custId+".value");
		//if(missippi != 0) {
			window.opener.document.form1.customerName.value = document.form1.customerName.value;
			window.opener.document.form1.customerId.value = document.form1.customerId.value;
			window.close();
		//} 
		/*else {
			alert("Please enter the core application customer code");
		}*/
	} else {
		alert("Please select customer ");
	}
}

</script>
<body>
<form name="form1" method="post" action="chooseCustomer.jsp">
<div id="MissippiCodeError"> <font> <!-- Do not Delete MissippiCustomerCodeValidation AJAX Error --> </font> </div>
<table width="90%" class="footable">
	<thead>
	<tr>
		<th width="4%"></th>
		<th width="10%">Customer_Id</th>
		<th width="20%">Customer Name</th>
		<th width="20%">Email</th>
		<th width="10%">Core Application Code</th>
		<%--
		<th width="10%">Core Application Code</th>
		<th width="10%">AR Account Code</th>
		--%>
		<th width="24%">View Details</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td colspan="7">
			<input type="hidden" name="customerName">
			<input type="hidden" name="customerId">
			<input type="hidden" name="brokerId" value="<%=request.getParameter("brokerId")%>">
		</td>
	</tr>
	<% for(int i=0;i<customers.length;i++) { %>
	<tr>
		<td>
			<input type="hidden" name="customerName<%=i%>" value="<%=(customers[i][0]==null?"":customers[i][0]).replaceAll("\"","&#34;")%>">
			<input type="hidden" name="customerId<%=i%>" value="<%=customers[i][4]%>">
			<input type="radio" name="customerNameSelect" <%=customers[i][4].equalsIgnoreCase(request.getParameter("customerId"))?"checked":""%> onClick="setCustomer('<%=i%>')">
		</td>
		<td align="center">
			<%=customers[i][4]%><input type='hidden' name="cust<%=customers[i][4]%>" value='<%=customers[i][5]%>' />
		</td>
		<td>
			<%=customers[i][0]+" "+("0".equalsIgnoreCase(customers[i][1])?"":customers[i][1])%>
		</td>
		<td>
			<%=("0".equalsIgnoreCase(customers[i][2])?"":customers[i][2])%>
		</td>
		<td><%=(customers[i][5]==null || "0".equalsIgnoreCase(customers[i][5]))?"":customers[i][5]%></td>
		<%--
		<td><%=customers[i][5]%></td>
        <td><%=(customers[i][6]==null?"":customers[i][6])%></td>
        --%>
		<td align="center">
			<a href="#" title="View Customer Details" class="two" onClick="redirect('<%=i%>')"> <b>View</b></a>
		</td>
	</tr>
	<% }if(customers==null || customers.length<=0) { %>
	<tr class="ltbgyellow"> 
		<%--<td colspan="5"> <font color="red">There Is No Records In dataBase With This Name</font> </td>--%>
	</tr>
	<%}%>
	<%  if(!"GUEST".equalsIgnoreCase(loginIds)||("GUEST".equalsIgnoreCase(loginIds) && (customers==null || customers.length<=0))){%>					  
	<tr>
		<td colspan="6" align="center">
			Add New Customer Information &nbsp;&nbsp;&nbsp; 
			<a href="#" title="Add New Customer Information" class="two" onClick="newCus()"><b> Add New Customer </b> </a>
			<br/><br/>Add Existing Customer Information &nbsp;&nbsp;&nbsp; 
			<a href="chooseExistingCustomer.jsp?brokerId=<%=request.getParameter("brokerId")%>" title="Add Existing Customer" class="two"><b> Select Existing Customer </b> </a>
		</td>
	</tr>
	<%}%>
	</tbody>
</table>
<%if("GUEST".equalsIgnoreCase(loginIds)){%>
<input type="hidden" name="user" value="GUEST"/>
<%}%>
<div align="center" style="margin-top: 10px;">
	<a href= "#" onClick='window.close()'> <img src="<%=pathq%>/images/Back.jpg"> </a>
	&nbsp;&nbsp;&nbsp;
	<% if(customers != null && customers.length>0) { %>
	<a href="#" onClick= "return settingValues('<%=customers.length%>')">
	<img src="<%=pathq%>/images/Submit.jpg">		</a>	
	<%}%>
</div>
</form>
<script type="text/javascript">

function redirect(ids) {	
	var customerid = eval("document.form1.customerId"+ids+".value");	
	document.form1.action="newCustomer.jsp?customers="+customerid+"&&mode=edit";
	document.form1.submit();
}

setExisting('<%=request.getParameter("customerId")%>');

</script>
</body>