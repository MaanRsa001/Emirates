<%String cpath1 = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Emirates</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
	<jsp:useBean id = "OCE" class = "com.maan.opencover.bean.opencoverEntry">
		<jsp:setProperty name = "OCE" property = "*"/>
	</jsp:useBean>

	<jsp:useBean id="newCover" class="com.maan.opencover.bean.newCoverBean">
		<jsp:setProperty name="newCover" property="*"/>
	</jsp:useBean>
	
	<link href="<%=cpath1%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=cpath1%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script language="JavaScript">
		function stopRKey(evt) { 
		 	 var evt = (evt) ? evt : ((event) ? event : null); 
		  	 var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		 	 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;}
		} 
		document.onkeypress = stopRKey; 
	</script>
</head>
<body>
<table width="90%"  border="0" cellspacing="0" cellpadding="0"  align="center">
<tr>
	<td>
		<%@include file="menus.jsp"%>
	</td>
</tr>
<tr>
	<td>
<%
	String loginId = (String)session.getAttribute("user");
	if(session.getAttribute("openCoverNo")!=null)
		session.removeAttribute("openCoverNo");
	
	String brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
	java.util.ArrayList values = OCE.getQuoteDetails(brokerId);
	String[][] information=(String[][])values.get(0);
	String[][] informations=(String[][])values.get(1);
	for(int i=0;i<information.length;i++) {
		try {
			for(int j=0;j<informations.length;j++) {
				if(information[i][0].equalsIgnoreCase(informations[j][0])) {
					information[i][2]=informations[j][1];
					information[i][3]=informations[j][2];
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	String[][] BrokerNames=newCover.getBrokersQuoteRegister(actualBranch);
	int no_of_records=5;
	int displaypages=3;
	int samplepages=displaypages;
		if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
			displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
%>
<form name="form1" method="post" action="newOpenCover.jsp">
<table width="100%">
<tr>
	<td align="right">
		<table width="33%">
			<tr>
				<td width="50%" align="right">Select Broker:</td>
				<td width="50%">
					<select name="brokerId" onChange="bidselection(this.value)" class="inputSelect">
						<option value="Select">Select</option>
						<% for(int i=0;i<BrokerNames.length;i++) { %>
						<option value="<%=BrokerNames[i][1]%>" <%=BrokerNames[i][1].equalsIgnoreCase(brokerId)?"selected":""%>><%=BrokerNames[i][0]%></option>
						<% } %>
					</select>
				</td>
			</tr>
		</table>	
	</td>
</tr>
<tr>
	<td>
<%
int length123=0;
if(information.length==0) {
	length123=1;
} else {
	length123=information.length;
}

int pages=length123/no_of_records;
int rem=length123%no_of_records;
if(rem!=0) {
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
if(spage>=displaypages) {
	if(pages>displaypages) {
		start++;
		displaypages++;
	}
} else if((displaypages-spage)==(samplepages-1)&&start!=0) {
	start--;
	displaypages--;
}
%>
		<table width="100%" class="footable">
			<thead>
			<tr>
				<th width="5%">S.No</th>
				<th width="15.83%">Proposal No</th>
				<th width="26.66%">Customer Name</th>
				<th width="15.83%">Policy Start Date</th>
				<th width="15.83%">Policy End date</th>
				<th width="5%">Edit</th>
				<th width="15.83%">Schedule</th>
			</tr>
			</thead>
			<tbody>
			<%	int k=0;
				int skip=0;
				int count=0;
				for(int i=0;i<information.length;i++) {
					k++;
					if(spage>1) {
				  		skip=spage-1;
				  		if(k<=(skip*no_of_records))
							continue;
					}
					String bgColor="Y".equalsIgnoreCase(information[i][5])?"style='background-color: silver;'":"";
			%>
			<tr>
				<td><%=(i+1)%></td>
				<td><%=information[i][0]%></td>
				<td><%=information[i][4]%></td>
				<td><%=information[i][2]%></td>
				<td><%=information[i][3]%></td>
				<td>
					<a href="#" class="two" title="Edit" onClick="return viewPolicys('<%=information[i][0]%>','<%=information[i][1]%>','<%=information[i][5]%>')"> <b> Edit </b></a>
				</td>
				<td>
					<%if("Y".equalsIgnoreCase(information[i][5])){ %>
						<a href="#" class="two" title="View Policy Schedule" onclick="return viewDoc('<%=information[i][6]%>','<%=(String)session.getAttribute("user")%>','schedule','<%=information[i][6]%>','','<%=information[i][0]%>');"> <b> Schedule </b></a>
					<%}else{%>
						N/A
					<%}%>
				</td>
			</tr>
			<% 	if(k==display)
						break;
			} %>
			<tr>
				<td colspan="7">
				<%
					if(length123>no_of_records) {
						if(start>0) {
				%>
				<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>')"><font  color="white"><<</font>&nbsp;&nbsp;</a>
				<%
						}
						boolean flag=false;
						int iValue=0;
						for(int i=start;i<pages;i++) {
							if(i<displaypages) {
				%>
				<a href ="javaScript:ExistingCustomers_B2B('<%=i+1%>','<%=displaypages%>','<%=start%>')"><font  color="white"><%=i+1%></font></a>
				<%
							} else {
	 							flag=true;
	 							iValue=i;
	 							break;
							}
						}	 
						if(flag) {
				%>
				<a href ="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>')">&nbsp;&nbsp;<font  color="white">>></font></a>
				<%
						}
					}
				%>				
				</td>
			</tr>
			</tbody>
		</table>
	</td>
</tr>
<tr>
</tr>
</table>
<input type = "hidden" name = "proposalno"/>
<input type = "hidden" name = "renewalYN"/>
<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
</form>	
	</td>
</tr>
</table>
</body>

<script language = "javascript">

function viewPolicys(s,s1,s2)
{
	  document.form1.proposalno.value = s;
	  document.form1.renewalYN.value = s2;
	  document.form1.action = "newOpenCover.jsp";
	  document.form1.submit();
}
function bidselection(val)
{
   document.form1.brokerId.value=val;
   document.form1.action = "ShowOpenCoverEntry.jsp";
   document.form1.submit();
}
function ExistingCustomers_B2B(value123,displaypages,start)
{
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	document.form1.displaypages.value=displaypages;
	document.form1.action="ShowOpenCoverEntry.jsp"
	document.form1.submit();
}
function viewDoc(policynumber,loginId,docType,docNo,amendId,proposalNo)
{
	     var URL ="<%=request.getContextPath()%>/GenerateDocument.jspa?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId+"&proposalNo="+proposalNo;
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
</script>
</body>
</html>