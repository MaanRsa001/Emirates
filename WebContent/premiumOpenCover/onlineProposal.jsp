
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title> Emirates - Marine Insurance</title>
    
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

<%@include file="menus.jsp"%>
<link href="<%=cpath%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=cpath%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	<!--
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
.bottomtext 
	{
		FONT-SIZE: 11px;
		COLOR: white;
		FONT-FAMILY: Arial;
		TEXT-DECORATION: none;
		background-color: #337ab7;
		text-align: center;
		font-weight: BOLD;
	}
-->
</style>
</head>
<%
	boolean status = false;
	String search=request.getParameter("search")==null?"":request.getParameter("search");
	String text=request.getParameter("text")==null?"":request.getParameter("text");
	String mail=request.getParameter("mail")==null?"":request.getParameter("mail");
	String proposalNo = request.getParameter("proposalNo")==null?"":request.getParameter("proposalNo");
	
	String loginId = (String)session.getAttribute("user");
	String branchCode=OCE.getBranchCode(loginId);
	String branchPrefix=OCE.getBranchPrefix(branchCode);
	String[][] information = new String[0][0];
	if(mail.length()>0 && text.length()>0){
    information = OCE.getonlineDetails(search,text,mail);
    }
	String[][] lapsedInformation = new String[0][0];
	String[][] BrokerNames=newCover.getBrokersHasCover(actualBranch);
	lapsedInformation = OCE.getAdminLapsedPortfolioDetails(search);

	int no_of_records=10;
	int lapsed_no_of_records=5;
	//**************total display pages
	int displaypages=3;
	int samplepages=displaypages;
	if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
		displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
	int lapsed_displaypages=3;
	int lapsed_samplepages=displaypages;
if(request.getParameter("lapsed_displaypages")!=null&&!request.getParameter("lapsed_displaypages").equalsIgnoreCase(""))
lapsed_displaypages=request.getParameter("lapsed_displaypages")==null?3:Integer.parseInt(request.getParameter("lapsed_displaypages"));
	/********************pages************************/
%>
  <body>
  </body>
<form name="form1" method="post" action="newOpenCover.jsp">

<%
/**pagenation**/ 
int length123=0;
if(information.length==0)
{
	length123=1;
}
else
{
	length123=information.length;
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
else if((displaypages-spage)==(samplepages-1)&&start!=0)
{
	start--;
	displaypages--;
}

%><br/><br/>
<table class="footable" width="100%">
	<thead>
	<tr>
		<th width="50%">Search Existing Proposal</th>
		<th width="50%">Get New Proposal</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			<table width="100%">
				<tr>
					<td width="50%">Search By</td>
					<td width="50%">
						<select name='search' id="search" class='inputSelect'  onchange="PolicyQuoteSelecta()">
							<option value = 1 <%=("").equalsIgnoreCase(search)?"Selected":""%>>select</option>
							<option value = 2 <%=("2").equalsIgnoreCase(search)?"Selected":""%>>Proposal No</option>
							<option value = 3 <%=("3").equalsIgnoreCase(search)?"Selected":""%>>Policy No</option>
							<option value = 4 <%=("4").equalsIgnoreCase(search)?"Selected":""%>>Customer Name</option> 
						</select>
					</td>
				</tr>
				<tr>
					<td>Enter Proposal No</td>
					<td><input type="text" name="text" class="inputBox" id="text" value="<%=text%>"/></td>
				</tr>
				<tr>
					<td>Enter Email Id</td>
					<td><input type="text" name="mail" class="inputBox" id="mail" value="<%=mail%>"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type='image' src="<%=path%>/images/Go.jpg" onclick="policysearch()">
					</td>
				</tr>
			</table>
		</td>
		<td>
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;"><font color = green size = 4>Click Here to Get the New Proposal </font></td>
				</tr>
				<tr>
					<td align="center"><input type='image' src="<%=path%>/images/Go.jpg" onClick="return openNew()""></td>
				</tr>
			</table>
		</td>
	</tr>
	</tbody>
</table>
<input type="hidden" name="searchBy" />
<br/><br/><br/>
<%if(information.length>0) {%>
<table width="100%"  border="0" cellspacing="1" cellpadding="0" class="blueborder" >
 	 <tr> 
           <td align="left" colspan="9"><span class="heading">Guest Quotes</span></td>
     </tr>
        <tr class=""> 
           <td class="bottomtext">S.No</td>
           <td class="bottomtext">Proposal No</td>
           <td class="bottomtext">Customer Name </td>
		   <td class="bottomtext">Quote Requested Date</td>
           <td class="bottomtext">Edit</td>
           <td class="bottomtext">Schedule</td>
           <td class="bottomtext"></td>
        </tr>
<%
		 int k=0;
		 int skip=0;
		 int count=0;
    	 for(int i=0;i<information.length;i++)
     	 {
		 k++;
		 if(spage>1)
		 {
			 skip=spage-1;
		     if(k<=(skip*no_of_records))
			 continue;
		 }
		 String bgColor="";
%>
				     
		<tr class="formtxtc"> 
	        <td class="formtxtc" <%=bgColor%>><%=(i+1)%></td>
	        <td class="formtxtc" <%=bgColor%>><%=information[i][0]%></td>
	        <td class="formtxtc" width='25%' <%=bgColor%>><%=information[i][4]%></td>
	        <td class="formtxtc" <%=bgColor%>><%=information[i][2]%></td>
			<td class="formtxtc" <%=bgColor%>> &nbsp; 
			<%if("Y".equalsIgnoreCase(information[i][8])){%>
				<a href="#"  title="Edit Policy" class="two" onClick="return viewPolicys('<%=information[i][0]%>','<%=information[i][1]%>')"> <b> Edit </b></a>
			<%}else if(!"N".equalsIgnoreCase(information[i][9])){ %>
				N/A
			<%} %>
               &nbsp; </td>             
               <td class="formtxtc" <%=bgColor%>>
               <%if(!"Y".equalsIgnoreCase(information[i][8])){%>
				<a href="#" title="View Schedule" class="two" onclick="return viewDoc('<%=information[i][1]%>','<%=(String)session.getAttribute("user")%>','schedule','<%=information[i][1]%>','','<%=information[i][0]%>','false');" > <b> Schedule </b></a>
			<%}else{%>
				N/A
			<%} %>
               </td>   
               <td class="formtxtc" <%=bgColor%>>
               &nbsp;
               </td>             
	    </tr>   
			<%
			if(k==display)
				  break;
			}%>
	        <tr bgcolor="#4f6180">
			 <td height="12" align="right" colspan="11">
					<%
					if(length123>no_of_records)
						{
						if(start>0)
						{
							 %>
<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>')"><font  color="white"><<</font>&nbsp;&nbsp;</a>
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
						<font  color="white"><%=i+1%></font></a>
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
<a href ="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>')">&nbsp;&nbsp;<font  color="white">>></font></a>
							<%
						}
				 }
%>
	</td>
	</tr>
	</table></td>
	</tr>
	<table width="95%"  border="0" cellspacing="1" cellpadding="0" >
	<tr align="center">
	<td colspan="3">
	&nbsp; &nbsp; &nbsp; &nbsp;
		      		                                <td>  
		      		                            </td>
		          </tr>   
		    </table></td>
		  </tr>
<input type = "hidden" name = "proposalno"/>
</table>
<%}%>
<%if(information.length<=0 && mail.length()>0 && text.length()>0) {%>
<table align="center">
<tr>
	<td style="font-weight: bold;">
		&nbsp;
	</td>
	<td>
		<font color = red size = 4>No Results Found</font>
	</td>
</tr>
</table>
<%}%>
<div align="center">
	<a href= "#" onClick='callSubmit();' class="buttonsMenu" ><img src="<%=path%>/images/Back.jpg"></a>
</div>
<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
<input type="hidden" name="lapsed_spage">
<input type="hidden" name="lapsed_displaypages">
<input type="hidden" name="lapsed_start">
<input type="hidden" name="proposalNo">
<input type="hidden" name="requestfrom" value="showApprovedCover">

<input type="hidden" name="freshMode">
<input type="hidden" name="freshMode1">
<input type="hidden" name="user" value="GUEST">

<!--Royal New on 18/05 -->
</form>
<script language = "javascript">
function openNew()
{
	document.form1.freshMode.value='fresh';
	document.form1.freshMode1.value='fresh';
	document.form1.action="<%=basePath%>premiumOpenCover/newOpenCover.jsp";
	document.form1.submit();
	return true;
}

function viewPolicys(s,s1)
{
	  document.form1.proposalno.value = s;
	  document.form1.action = "newOpenCover.jsp";
	  document.form1.submit();
}

function policysearch()
{
    var text=document.getElementById("text").value;
    var mail=document.getElementById("mail").value;
    var search=document.getElementById("search").value;
    if(search == 1) {
		alert('Please select the Search By');
	}
    if(text.length == 0) {
		alert('Please Enter the Proposal No to Search');
	}
    else if(mail.length == 0) {
		alert('Please Enter the mail to Search');
	}
	if(text.length > 0 && mail.length > 0){
	    document.form1.text.value=document.getElementById("text").value;
	    document.form1.mail.value=document.getElementById("mail").value;
	    document.form1.search.value=document.getElementById("search").value;
	}
	document.form1.action = "onlineProposal.jsp";
	document.form1.submit();
}

function ExistingCustomers_B2B(value123,displaypages,start)
{
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	//document.form1.identifyCus.value=identifyCus;
	document.form1.displaypages.value=displaypages;
	document.form1.action="showApprovedCover.jsp"
	document.form1.submit();
}

function printQuote(proposalNo)
{
	var URL = '';
	URL='PrintOpencoverSummary.jsp?portfolio=portfolio&proposalNo='+proposalNo;
	var windowName = "PrintOpencoverSummary";
	var width  =	 screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h =	400;
	var features =
	'width='          + w +
	',height='		  + h +
	',left='		  + ((width - w) * .5)  +
	',top='			  + ((height - h ) * .5) +
	',directories=no' +
	',location=no'	  +
	',menubar=no'	  +
	',scrollbars=yes' +
	',status=no'	  +
	',toolbar=no'	  +
	',resizable=false';
	//alert(URL);
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	strOpen.focus();
	return false;
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
function callSubmit(){        	
	document.form1.action="<%=cpath%>/Loginpage.action";
    //document.getElementById("status").value="";
    document.form1.submit();
}
</script>
</html>