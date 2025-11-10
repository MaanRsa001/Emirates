<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/footable-0.1.css" rel="stylesheet" type="text/css">
</head>
<jsp:useBean id="customer" class="com.maan.opencover.bean.customerInfo"></jsp:useBean>
<%
	String pathq = request.getContextPath();
	String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<%!
   String[][] customers = new String[0][0];
%>
  <!-- Assign Values -->
<%
	customers = customer.getExistingCustomers(request.getParameter("brokerId").replaceAll("\"","&#34;"));
	String originalInsured = request.getParameter("originalInsured")==null?"":request.getParameter("originalInsured");
	List list=new ArrayList();
	if(customers.length>0){
		for(int i=0; i<customers.length; i++){
			if(originalInsured.indexOf(customers[i][4])==-1){
				list.add(customers[i]);
			}
		}
		customers=new String[list.size()][customers[0].length];
		for(int k=0; k<list.size(); k++){
			customers[k]=(String[])list.get(k);
		}
	}
%>
 <!-- Assign Values -->
<body>
<form name="form1" method="post" action="chooseCustomer.jsp">
<div id="MissippiCodeError"> <font> <!-- Do not Delete MissippiCustomerCodeValidation AJAX Error --> </font> </div>
<table width="90%" class="footable">
	<thead>
	<tr>
		<th width="4%"></th>
		<th width="24%">Customer_Id</th>
		<th width="24%">Customer Name</th>
		<th width="24%">Email</th>
		<th width="24%">View Details</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td colspan="5">
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
			<input type="checkbox" name="customerSelect" id="customerSelect<%=customers[i][4]%>" value="<%=customers[i][4]%>" title="<%=(customers[i][0]==null?"":customers[i][0]).replaceAll("\"","&#34;")%>">
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
		<td align="center">
			<a href="#" title="View Customer Details" class="two" onClick="redirect('<%=i%>')"> <b>View</b></a>
		</td>
	</tr>
	<% }if(customers==null || customers.length<=0) { %>
	<tr class="ltbgyellow"> 
		<td colspan="5"> <font color="red">There Is No Records In dataBase With This Name</font> </td>
	</tr>
	<%}%>
	<tr>
		<td colspan="5" align="center">
			Add New Customer Information &nbsp;&nbsp;&nbsp; 
			<a href="#" title="Add New Customer Information" class="two" onClick="newCus()"><b> Add New Customer </b> </a>
		</td>
	</tr>
	</tbody>
</table>
<br/>
<table width="100%">
	<tr>
		<td width="50%" align="center">
			<a href= "#" onClick='window.close()'> <img src="<%=pathq%>/images/Back.jpg"> </a>
			<%
				if(customers != null && customers.length>0)
				{
			%>
					<a href="#" onClick= "Submit()"> <img src="<%=pathq%>/images/Submit.jpg"> </a>
			<%
				}
			%>
		</td>
	</tr>
</table>

</form>
<script>

function redirect(ids)
{	
	var customerid = eval("document.form1.customerId"+ids+".value");	
	document.form1.action="newCustomer.jsp?customers="+customerid+"&&mode=edit";
	document.form1.submit();
}
function setCustomer(ids)
{
	var customername = eval("document.form1.customerName"+ids+".value");
	var customerid = eval("document.form1.customerId"+ids+".value");
	document.form1.customerName.value = customername;
	document.form1.customerId.value = customerid;
}

function newCus()
{
 	document.form1.action="newCustomer.jsp?mode=new";
 	document.form1.submit();
}
settingValues();
function settingValues()
{	
	var customerId=window.opener.document.Quotation.customerId.value;
	var array=customerId.split(',');
	if(array.length>0){
		for ( var int = 0; int < array.length; int++) {
			var obj=document.getElementById('customerSelect'+array[int]);
			if(obj){
				obj.checked=true;
			}
		}
	}
}
function Submit()
{
	var elems=document.form1.customerSelect;
	var customerId='';
	var customer='';
	if(elems.length>0){
		for ( var int = 0; int < elems.length; int++) {
			var obj=elems[int];
			if(obj.checked){
				customerId+=','+obj.value;
				customer+=','+obj.title;
			}
		}
	}else if(elems){
		if(elems.checked){
				customerId+=','+elems.value;
				customer+=','+elems.title;
			}
	}
	if(customerId.length>0){
		customerId=customerId.substring(1, customerId.length);
	}
	if(customer.length>0){
		customer=customer.substring(1, customer.length);
	}
	window.opener.document.Quotation.customer.value = customer;
	window.opener.document.Quotation.customerId.value = customerId;
	window.close();
}
</script>
</body>
                     
