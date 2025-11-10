<%try{ %>
<%@ include file="submenu.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>** Emirates **</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
.inputBox {
	height: 20px;
	width: 90%;
}
</style>
</head>
<jsp:useBean id= "cus" class = "com.maan.opencover.bean.customerInfo">
	<jsp:setProperty name= "cus"   property = "*"/>
</jsp:useBean>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";


   String defCode="";
   if(session.getAttribute("AdminBranchCode")!=null){
		defCode = (String)session.getAttribute("AdminBranchCode");
	}
	   defCode = defCode.replaceAll("'","");
%>
<script type="text/javascript">
	function backs()
	{
		 document.personal.action="chooseCustomer.jsp";
		 document.personal.submit();
	}
	
function enableComponent(value)
	{
		if(value)
		{
			//document.personal.orgName.disabled =false;
			document.personal.title.disabled=false;
			document.personal.gender[0].disabled=false;
			document.personal.gender[1].disabled=false;
			document.personal.firstName.disabled=false;
			document.personal.lastName.disabled=false;
			document.personal.nationality.disabled=false;
			document.personal.dobDay.disabled=false;
			document.personal.dobMonth.disabled=false;
			document.personal.dobYear.disabled=false;
			document.personal.telephone.disabled=false;
			document.personal.mobile.disabled=false;
			document.personal.fax.disabled=false;
			document.personal.email.disabled=false;
			document.personal.occupation.disabled=false;
			document.personal.emirate.disabled=false;
			document.personal.country.disabled=false;
			document.personal.poBox.disabled=false;
			
		}
		document.personal.submit();
		return false;
	}
	
	function settingNames(name,id)
	{
		if(window.opener.form1){
			window.opener.form1.customerName.value=name;
			window.opener.form1.customerId.value=id;
		}
		window.close();
	}
	
<%
    if(request.getAttribute("errorDetail")==null && request.getParameter("stageId")!=null)
	{
		String TestString1 = request.getParameter("firstName").replaceAll("\"","&#34;");
%>
        settingNames("<%=TestString1%>","<%=(String)session.getAttribute("customer_id")%>");
<%
    }
%>
	</script>	
<% 

	String cid = (String) session.getAttribute("AdminCountryId");
	String action=null;
	String next=null;
	String option=null;
	try
	{
		option =request.getParameter("mode");
		if(request.getParameter("customers")!=null && "edit".equalsIgnoreCase(option))
			session.setAttribute("customer_id",request.getParameter("customers"));
		else if("new".equalsIgnoreCase(option))
			session.removeAttribute("customer_id");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	com.maan.opencover.util.dataCollection data=new com.maan.opencover.util.dataCollection();
	String[][] title=data.titleCollection(defCode);
	String mode=request.getParameter("mode")==null?"":request.getParameter("mode");

    boolean isEditable=false,disp=true,readOnly=false;
    if("edit".equalsIgnoreCase(option) && request.getAttribute("errorDetail")==null) 
	{
	    isEditable=true;
        cus.settingValues((String)session.getAttribute("customer_id")==null?"1":(String)session.getAttribute("customer_id"));
	}
	if("new".equalsIgnoreCase(option) && request.getAttribute("errorDetail")==null && (request.getParameter("coreMode")!=null && !"".equals(request.getParameter("coreMode")))) {
		isEditable=true;
		cus.settingCoreCustValues(request.getParameter("corecustomers")==null?"":request.getParameter("corecustomers"));
	}
     
    if("Y".equals(request.getParameter("address")))
    {
        readOnly= true;
       // action="final.jsp";
    }         
    action="premiumOpenCover/newCustomer.jsp";
 
 %>
    <%String loginIds = (String) session.getAttribute("user"); %>
    
<body onload='disableCity()'>
<form name="personal" method="post" action="newCustomer.jspa">
<input type="hidden" name="actionPath" value="<%=action%>"/>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td width="25%"></td>		
		<td align="left" width="75%"> <font color="red" size="2"><%=request.getAttribute("errorDetail")!=null?(String)request.getAttribute("errorDetail"):"&nbsp;"%></font></td>
	</tr>
</table>
<table align="center" border="0" cellpadding="2" cellspacing="2" width="90%">
	<tr>
		<td colspan="4">
			<span class ="heading">Customer details</span>
		</td>
	</tr>
	<tr>
		<td class="formtxtf1" width="20%" style="padding: 5px;">Title<FONT color=red>**</FONT></td>
		<td class="formtxtf1" width="30%" style="padding: 5px;">
			<select name="title"  <%=readOnly?"DISABLED":""%> class="inputSelect">
				<option value="">Select Title</option>
				<% for(int i=0;i<title.length;i++) {%>
				<option value="<%=title[i][0]%>" <%=isEditable?((cus.getTitle()==null?"":cus.getTitle()).equalsIgnoreCase(title[i][0])?"selected":""):(request.getParameter("title")==null?"":(request.getParameter("title").equalsIgnoreCase(title[i][0]==null?"":title[i][0])?"selected":""))%>><%=title[i][0]%></option>
				<%}%>
			</select>
		</td>
		<td class="formtxtf1" width="20%" style="padding: 5px;">Name/Organization Name <FONT color=red>**</FONT></td>
		<td class="formtxtf1" width="30%" style="padding: 5px;">
			<input name="firstName" maxLength="150" type="text" style="width: 95%;" class="inputBox" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?(cus.getFirstName()==null?"":cus.getFirstName()):(request.getParameter("firstName")==null?"":(request.getParameter("firstName").replaceAll("\"","&#34;")))	%>" >
		</td>
	</tr>
	<!-- Nationality & DOB Restriction -->
	<%//if(!brokerBra.equalsIgnoreCase("001") && !brokerBra.equalsIgnoreCase("002") && !brokerBra.equalsIgnoreCase("003") && !brokerBra.equalsIgnoreCase("010") ) 
		{
	%>
	
	<%--<tr>
		<td class="formtxtf1" style="padding: 5px;">Nationality</td>
		<td class="formtxtf1" style="padding: 5px;">
			<select name="nationality"  <%=readOnly?"DISABLED":""%> class="inputSelect" >
				<option value="select">Select Nationality</option>
				<% title=data.nationalityCollection();
			 		for(int i=0;i<title.length;i++) {
				%>
				<option value="<%=title[i][0]%>" <%=isEditable?((cus.getNationality()==null?"":cus.getNationality()).equalsIgnoreCase(title[i][0])?"selected":""):(request.getParameter("nationality")==null?"":(request.getParameter("nationality").equalsIgnoreCase(""+title[i][0])?"selected":""))%>><%=title[i][0]%></option>
				<% } %>
			</select>
		</td> 
		<td class="formtxtf1" style="padding: 5px;">DOB</td>
		<td class="formtxtf1" style="padding: 5px;">
			<select name="dobDay" class='inputSelect' style="width: 33%; float: left;" <%=readOnly?"DISABLED":""%>>
				<option value='DD' >DD</option>
				<% for(int i=1;i<=31;i++) { %>
				<option value="<%=i%>" <%=isEditable?((""+i).equalsIgnoreCase(cus.getDobDay()==null?"":""+Integer.parseInt(cus.getDobDay()))?"selected":""):(request.getParameter("dobDay")==null?"":(request.getParameter("dobDay").equalsIgnoreCase(""+i)?"selected":""))%>><%=i%></option>
				<% } %>
			</select>
			<!--  DOB MONTH    ----------->
			<select name="dobMonth" class='inputSelect' style="width: 33%; float: left;" <%=readOnly?"DISABLED":""%>>
			<option value='MMM' >MMM</option>
			<% String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			for(int i=1;i<=months.length;i++) { %>
			<option value="<%=i%>" <%=isEditable?((""+i).equalsIgnoreCase(cus.getDobMonth()==null?"":""+Integer.parseInt(cus.getDobMonth()))?"selected":""):(request.getParameter("dobMonth")==null?"":(request.getParameter("dobMonth").equalsIgnoreCase(""+i)?"selected":""))%> ><%=months[i-1]%></option>
			<% }%>
			</select>
			<select name="dobYear" class='inputSelect' style="width: 33%; float: left;" <%=readOnly?"DISABLED":""%>>
			<option value='YYYY' >YYYY</option>
			<% for(int i=1924;i<2000;i++) { %>
			<option value="<%=i%>" <%=isEditable?((""+i).equalsIgnoreCase(cus.getDobYear()==null?"":cus.getDobYear())?"selected":""):(request.getParameter("dobYear")==null?"":(request.getParameter("dobYear").equalsIgnoreCase(""+i)?"selected":""))%>><%=i%></option>
			<% }%>
			</select>
		</td>
	</tr>--%>
	<%
	}//Branchwise Restriction
	%>
	<!-- Nationality & DOB Restriction -->
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Core Application Code</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="customerCode" type="text" style="width: 95%;" class="inputBox" maxLength="49" value="<%=isEditable?(cus.getCustomerCode()==null?"":cus.getCustomerCode()):(request.getParameter("customerCode")==null?"":(request.getParameter("customerCode")))%>" readonly="readonly">
		</td>
		<td class="formtxtf1" style="padding: 5px;">Address 1</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="address1" type="text" style="width: 95%;" class="inputBox" maxLength="49" value="<%=isEditable?(cus.getAddress1()==null?"":cus.getAddress1()):(request.getParameter("address1")==null?"":(request.getParameter("address1").replaceAll("\"","&#34;")))%>" >
		</td>
	</tr>
	<!-- Emirate ----------->
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Address 2</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="address2" type="text" style="width: 95%;" class="inputBox" maxLength="49" value="<%=isEditable?(cus.getAddress2()==null?"":cus.getAddress2()):(request.getParameter("address2")==null?"":(request.getParameter("address2").replaceAll("\"","&#34;")))%>" >
		</td>
		<td class="formtxtf1" style="padding: 5px;">City <FONT color=red>**</FONT></td>
		<td class="formtxtf1" style="padding: 5px;">
			<select name="emirate" onChange="enableCity()"  <%=readOnly?"DISABLED":""%> class="inputSelect">
				<option value="select">Select City</option>
				<% boolean emiratestatus=false;
				title = data.emirateCollection(cid);
				for(int i=0;i<title.length;i++) {      
					if(  (isEditable) && !(cus.getEmirate().equalsIgnoreCase(title[i][0].trim())) ) {   
						System.out.println("val"+i+"  "+ cus.getEmirate()+ " " +title[i][0].trim() );
						emiratestatus=true;  
					} else { 
						emiratestatus=false;  
						i=title.length;
					}
				}
				if(emiratestatus) {  
					for(int i=0;i<title.length;i++) {
				%>
				<option value="<%=title[i][0]%>"  <%=("others".equalsIgnoreCase(title[i][0]))?"selected":""%>><%=title[i][0]%></option>
				<%			
					}  
				}
				else { 
            		for(int i=0;i<title.length;i++) {
				%>
				<option value="<%=title[i][0]%>" <%=isEditable?((cus.getEmirate()==null?"":cus.getEmirate()).equalsIgnoreCase(title[i][0])?"selected":""):(request.getParameter("emirate")==null?"":(request.getParameter("emirate").equalsIgnoreCase(title[i][0])?"selected":""))%>><%=title[i][0]%></option>
				<%	}
				}
				%>
			</select>
		</td>
		<%--
		<td class="formtxtf1" style="padding: 5px;">Other City</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="city" maxLength="12" type="text" id="city" style="width: 95%;" class="inputBox" value="<%=isEditable?(cus.getCity()==null?"":cus.getCity()):(request.getParameter("city")==null?"":request.getParameter("city"))%>" >
	   		<input name="cityStatus" type="hidden">
		</td>
		--%>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px;">P.O.Box <FONT color=red>**</FONT></td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="poBox" type="text" class="inputBox" style="width: 95%;" maxLength="6" value="<%=isEditable?(cus.getPoBox()==null?"":cus.getPoBox()):(request.getParameter("poBox")==null?"":request.getParameter("poBox"))%>" >
		</td>
		<td class="formtxtf1" style="padding: 5px;">Email<FONT color=red>**</FONT></td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="email" type="text" class="inputBox" style="width: 95%;" maxLength="39" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?(cus.getEmail()==null?"":cus.getEmail()):(request.getParameter("email")==null?"":(request.getParameter("email").replaceAll("\"","&#34;")))%>" >
		</td>
		<%--
		<td class="formtxtf1" style="padding: 5px;">Country</td>
		<td class="formtxtf1" style="padding: 5px;">
			<select name="country" <%=readOnly?"DISABLED":""%> class="inputSelect" ><!--onchange = "changeCountry()" -->
			<%
				title = data.countryCollectionWithId(defCode);
				String countryId = cus.getCountry()==null?"":cus.getCountry();
				for(int i=0;i<title.length;i++) {
			%>
			<option value="<%=title[i][0]%>" <%=request.getParameter("country")==null?(countryId.length()<=0?(cid.equalsIgnoreCase(title[i][1])?"selected":""):countryId.equalsIgnoreCase(title[i][0])?"selected":""):(title[i][0].equalsIgnoreCase(request.getParameter("country"))?"selected":"")%>><%=title[i][0]%></option>
			<% } %>
			</select>
		</td>
		--%>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Telephone</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="telephone" maxLength="12" type="text" style="width: 95%;" class="inputBox" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?(cus.getTelephone()==null?"":cus.getTelephone()):(request.getParameter("telephone")==null?"":request.getParameter("telephone"))%>" >
		</td>
		<td class="formtxtf1" style="padding: 5px;">Mobile</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="mobile" maxLength="25" type="text" style="width: 95%;" class="inputBox" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?(cus.getMobile()==null?"":cus.getMobile()):(request.getParameter("mobile")==null?"":request.getParameter("mobile"))%>" >
		</td>
	</tr>
	
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Vat Reg No</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="custVatRegNo" maxLength="12" type="text" style="width: 95%;" class="inputBox" readonly="readonly" value="<%=isEditable?(cus.getCustVatRegNo()==null?"":cus.getCustVatRegNo()):(request.getParameter("custVatRegNo")==null?"":request.getParameter("custVatRegNo"))%>" >
		</td>
		<td class="formtxtf1" style="padding: 5px;"></td>
		
		<td class="formtxtf1" style="padding: 5px;">
					<input name="custVatRegYn"  type="hidden" value="<%=isEditable?(cus.getCustVatRegYn()==null?"":cus.getCustVatRegYn()):(request.getParameter("custVatRegNo")==null?"":request.getParameter("custVatRegNo"))%>"/>
		
		</td>
	</tr>
	<%--
	<tr>--%>
		<%--<td class="formtxtf1" style="padding: 5px;">Fax</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="fax" type="text" class="inputBox" style="width: 95%;" maxLength="12" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?(cus.getFax()==null?"":cus.getFax()):(request.getParameter("fax")==null?"":request.getParameter("fax"))%>" >
		</td>--%>
		<%--
		<td class="formtxtf1" style="padding: 5px;">Email<FONT color=red>**</FONT></td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="email" type="text" class="inputBox" style="width: 95%;" maxLength="39" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?(cus.getEmail()==null?"":cus.getEmail()):(request.getParameter("email")==null?"":(request.getParameter("email").replaceAll("\"","&#34;")))%>" >
		</td>
		
		<td class="formtxtf1" colspan="2"></td>
		--%>
		<%-- Removed for Emirates
		<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
		--%>
	<%--
		<td class="formtxtf1" style="padding: 5px;">Occupation</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="occupation" type="text" class="inputBox" style="width: 95%;" maxLength="24" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?(cus.getOccupation()==null?"":cus.getOccupation()):(request.getParameter("occupation")==null?"":(request.getParameter("occupation").replaceAll("\"","&#34;")))%>" >
		</td>--%>
		<%-- Removed for Emirates
		<td class="formtxtf1" style="padding: 5px;">Core Customer Code&nbsp;<FONT color=red>**</FONT></td>
		<td class="formtxtf1" style="padding: 5px;">
		<%try{
		String arNo = isEditable?(cus.getArNo()==null?"":cus.getArNo()):(request.getParameter("arNo")==null?"":(request.getParameter("arNo").replaceAll("\"","&#34;")));
		String customerName = isEditable?(cus.getCustomerName()==null?"":cus.getCustomerName()):(request.getParameter("customerName")==null?"":(request.getParameter("customerName").replaceAll("\"","&#34;")));
		String customerCodes = isEditable?(cus.getCustomerCode()==null?"":cus.getCustomerCode()):(request.getParameter("customerCode")==null?"":(request.getParameter("customerCode").replaceAll("\"","&#34;")));
		if(customerCodes.equals("0"))
		customerCodes = "";
		%>		
		<div class="inputAppend">
			<input name="customerCode" type="hidden" maxLength="10" readonly value="<%=customerCodes%>" >
			<input name="customerName" type="hidden" maxLength="10" readonly value="<%=customerName%>" >
			<input name="nameAndCode" class="" style="width: 80%; float: left; border: none; background: transparent;" type="text" maxLength="10" readonly value="<%=customerName+(customerCodes.length()>0?" ("+customerCodes+")":"") %>" >
			<input type="button" width="2px" name="chooseTransit" value="..." class='inputButton' style="float: left;" onClick="getCoreCustomerInfo()"/>
			<input type = "hidden" name = "arNo" value = "<%=arNo%>">
			<br clear="both"/>
		</div>
		
		<%}catch(Exception e){}%>
		</td>
		<%} else {%>
		<td class="formtxtf1" colspan="2"></td>
		<%} %>
		--%>		
	<%--</tr>--%>
	
	<%
		if("RSABROKER123".equalsIgnoreCase(request.getParameter("brokerId"))&&!mode.equalsIgnoreCase("edit")) {
	%>
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Enter LoginID <FONT color=red>*</FONT></td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="loginid" class="inputBox" type="text" style="width: 95%;" maxLength="14" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?"":(request.getParameter("loginid")==null?"":request.getParameter("loginid"))%>" >
		</td>
		<td class="formtxtf1" style="padding: 5px;">Password <FONT color=red>*</FONT></td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="password" type="password" style="width: 95%;" class="inputBox" maxLength="25" <%=readOnly?"disabled=true'":""%> value="<%=isEditable?"":(request.getParameter("password")==null?"":request.getParameter("password"))%>" >
		</td>
	</tr>
	<%}else{%>
		<input type="hidden" name="loginid" value="NONE">
		<input type="hidden" name="password" value="NONE">
	<%}%>	
</table>
<input type="hidden" name="brokerId" value="<%=request.getParameter("brokerId")%>">
<input type="hidden" name="stageId" value="1">

<div style="margin-top: 10px;" align="center">
	<a href= "#" onClick='window.close()' class="buttonsMenu" ><img src="<%=pathq%>/images/Back.jpg"></a>
	&nbsp;&nbsp;&nbsp;
	<a href="#" onClick= 'enableComponent(<%=readOnly%>)' class="buttonsMenu" ><img src="<%=pathq%>/images/Submit.jpg"></a>
	<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
</div>

</form>
</body>

<script>

function enableCity()
{	
	if(document.personal.emirate.value=="VARIOUS")
	{
	   document.getElementById("city").disabled = false;
	   document.personal.cityStatus.value="true";
	}
	else
	{
		document.getElementById("city").disabled = true;
		document.personal.cityStatus.value="false";
	}
}

function disableCity()
{	
	if(document.personal.emirate.value=="VARIOUS")
	{
	   document.getElementById("city").disabled = false;
	   document.personal.cityStatus.value="true";
	}
	else
	{
		document.getElementById("city").disabled = true;
		document.personal.cityStatus.value="false";
	}
}
function getCoreCustomerInfo()
{
		var URL='<%=request.getContextPath()%>/CustomerInfo/CoreCustomerSearch.jsp';
		var windowName = "Customer";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 700;
		var h = 400;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 0) * .4)  +
			',top='			  + ((height - h - 0) * .4) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=yes'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
}
</script>
</html>
<%}catch(Exception e){e.printStackTrace();}%>