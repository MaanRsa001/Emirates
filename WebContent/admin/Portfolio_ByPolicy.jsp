<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="header.jsp" %>
<%@ page import="com.maan.common.util.StringUtil" %>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>


<jsp:useBean id="admin" class="com.maan.admin.AdminBean">

</jsp:useBean>

<jsp:useBean id="broker" class="com.maan.admin.BrokerCreationBean">

<jsp:setProperty name="broker" property="out" value="<%= response.getWriter() %>" /> 

</jsp:useBean>

 <%

	if(session.getAttribute("HashBacksDet")!=null)
		session.removeAttribute("HashBacksDet");
	HashMap brokDetails = new HashMap();
	String currencyType = "";
	String decimalPlace="";
	brokDetails = (HashMap) session.getAttribute("BrokerDetails");
	if(brokDetails.size() >0)
	{
		currencyType = (String) brokDetails.get("CurrencyName");
		decimalPlace =(String) brokDetails.get("CurrencyDecimal");
		decimalPlace = decimalPlace == null ? "2" :decimalPlace;
	}

	java.text.NumberFormat fmt=null;
	if(decimalPlace.equalsIgnoreCase("2"))
		fmt=new java.text.DecimalFormat("##,##0.00");
	else if(decimalPlace.equalsIgnoreCase("3"))
		fmt=new java.text.DecimalFormat("##,##0.000");
	else if(decimalPlace.equalsIgnoreCase("4"))
		fmt=new java.text.DecimalFormat("##,##0.0000");
	else
		fmt=new java.text.DecimalFormat("##,##0.00");
  
// for quote search
 String search_option=request.getParameter("search_option")==null?"":request.getParameter("search_option");
 String sts=request.getParameter("sts")==null?"":request.getParameter("sts");
 String policy_no=request.getParameter("policy_no")==null?"":request.getParameter("policy_no");

 String admBranch1 = "";
 admBranch1 = (String) session.getAttribute("LoginBranchCode");
 admBranch1 = admBranch1 == null ?"":admBranch1;
 String qno=request.getParameter("qno")==null?"":request.getParameter("qno");
 String productId=request.getAttribute("pid")==null?"":(String)request.getAttribute("pid");
	  
if(productId.equals(""))
{
	 productId=(String)session.getAttribute("pid")==null?"":(String)session.getAttribute("pid");
}
else
{
	session.removeAttribute("pid");
	session.setAttribute("pid",productId);
}

//end here


 String error="";
if(qno.trim().equals("")&&search_option.equalsIgnoreCase("YES"))
{
	
}
else
if(!StringUtil.isNumber(qno)&&search_option.equalsIgnoreCase("YES"))
{
	
}

int no_of_records=10;
int displaypages=5;
int samplepages=displaypages;
if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));

	String rep=request.getAttribute("rep1")==null?"":(String)request.getAttribute("rep1");
	String data1="";
	String data2="";
	String company="";
	if(rep.equalsIgnoreCase("d")||rep.equalsIgnoreCase("p")||rep.equalsIgnoreCase("c"))
	{
		data1=request.getAttribute("data1")==null?"":(String)request.getAttribute("data1");
		data2=request.getAttribute("data2")==null?"":(String)request.getAttribute("data2");
	}
	else
	{
		company=request.getAttribute("company")==null?"":(String)request.getAttribute("company");
	}
	if(data1.equals("")||data2.equals(""))
	{
			rep=request.getParameter("rep")==null?"":request.getParameter("rep");
			data1=request.getParameter("data1")==null?"":request.getParameter("data1");
			data2=request.getParameter("data2")==null?"":request.getParameter("data2");
			//brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
			company=request.getParameter("company")==null?"":request.getParameter("company");
	}
    String PoStatus="Policy No";
    String pdfstatus="View PDF";
	if(rep.equalsIgnoreCase("p"))
	{
		//PoStatus="Policy No";
		//pdfstatus="View PDF";
	}
	else
	{
		//PoStatus="Quote No";
		//pdfstatus="View Draft";
	}
   %>
<body>
<form name="form1" method="post" action="" >
<table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="left.jsp" --%>
</tr>
</table>
</td>
<td width="1"></td>
<td align="left" valign="top">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td width="8" height="25">&nbsp;</td>
<td width="1"></td>
<td width="98%"><span class="heading">PORTFOLIOp</span></td>
</tr>
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>
<tr align="center">
<td colspan="3">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<%
	String[][] app=new String[0][0];
	String[][] unApp=new String[0][0];
	String[][] unAppMulti=new String[0][0];
	String[][] unAppMultiDate=new String[0][0];
	String applicationNo="";
	String from123="";
	String policyno="";
	String searchstatus="";
	String eDate=request.getParameter("entryDate")==null?"":request.getParameter("entryDate");
	
		unApp=admin.getPortfolioByPolicy_no(policy_no,"policy_search",admBranch1);
		if(unApp.length<=0)
		{
			unAppMulti=admin.getPortfolioByDateMultiSearch(policy_no,admBranch1);
			unAppMultiDate=admin.getPortfolioByDateMultiSearchDate(policy_no,admBranch1);
		}
	
	int length123=0;
	if(unApp.length==0)
	{
		length123=1;
	}
	else
	{
		length123=unApp.length;
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
	String fontcolor="";
		
		
%>

<%//@ include file="admin_sub_menu_referal_approved.jsp" %>
<table width="100%"  border="0" cellspacing="1" cellpadding="0" >
		<tr>
		<td>
		<table  width="90%"   border="0">
		<tr><td align="center" colspan="6"><font color="red"><%=error%></font></td><tr>
		<%
		if(!search_option.equalsIgnoreCase("YES"))
		{
		%>
			<tr><td colspan="6" align="right"><b>Quotes Generated Date :</b><%=eDate%></td></tr>
		<%
		}
		%>
		<tr class="royamenuhead">
		<td class="bottomtext"> <b>S.No</b></td>
		<td class="bottomtext"> <b>Broker Organization</b></td>
		<td class="bottomtext"> <b>Quote Created By</b></td>
		<td class="bottomtext"> <b><%=PoStatus%></b></td>
		<td class="bottomtext"> <b>Customer Name</b></td>
		<td class="bottomtext"> <b>Premium(<%=currencyType%>)</b></td>
		<%--<td class="bottomtext"> <b>Status</b></td>
		<td class="bottomtext"> <b>Remarks</b></td>
		--%><td class="bottomtext"  ><b><%=pdfstatus%></b></td>
		<%--<td class="bottomtext"  ><b>Upload/Download</td>
		--%><%-- <td height="25" width="20" colspan="1" class="blueborder" style="padding-left:10px;">Validity Period </td> --%>
		</tr>
	<%
		int k=0;
		int skip=0;
		int count=0;
		for(int i=0;i<unApp.length;i++)
		{
            
	if((unApp[i][11]!=null&&!unApp[i][11].equalsIgnoreCase("NOTHING"))||(unApp[i][12]!=null&&!unApp[i][12].equalsIgnoreCase("NOTHING"))||(unApp[i][13]!=null&&!unApp[i][13].equalsIgnoreCase("NOTHING"))||(unApp[i][14]!=null&&!unApp[i][14].equalsIgnoreCase("NOTHING")))
                   fontcolor="class='fontred'";
	else
                   fontcolor="";
	k++;
									
	if(spage>1)
	{
		skip=spage-1;
		if(k<=(skip*no_of_records))
			continue;
	}
		String str="";
	if(unApp[i][7]==null||unApp[i][7].equals("")||unApp[i][7].equalsIgnoreCase("null"))
	{
		str=(unApp[i][4]==null?"":unApp[i][4])+" "+(unApp[i][8]==null?"":unApp[i][8]);
	}
	else
	{
		str=unApp[i][7];
	}
	String[][] QuotedUser= new String[0][0];
	if(unApp[i][17].equalsIgnoreCase("1"))
		QuotedUser = admin.getQuotedPerson(unApp[i][1],admBranch1);
	else
		QuotedUser = admin.getQuotedPerson(unApp[i][17],admBranch1);
	String user="";
	String broker123="";
	String[][] BCompanyName=admin.getBrokerNameByUserId123(unApp[i][1]);
	if(BCompanyName.length>0)
	{
		broker123=BCompanyName[0][1]==null?"":BCompanyName[0][1];
	}
	if(QuotedUser.length>0)
	{
		if(QuotedUser[0][0].equals("2"))
		{
			user=broker123;
		}
		else
		{
			user=QuotedUser[0][1]==null?"":QuotedUser[0][1];
		}
	}		
%>
<tr> 
<td class="formtxtc" <%=fontcolor%>><%=(k)%></td>
<td class="formtxtf" align="left" <%=fontcolor%>><%=broker123%></td>
<td class="formtxtf" <%=fontcolor%>><%=user%></td>
<td class="formtxtc" width="25%" <%=fontcolor%>><%=unApp[i][9]==null?"":unApp[i][9]%></td>
<td class="formtxtf" <%=fontcolor%>><%=str%></td>
<td class="formtxtr" align="right" <%=fontcolor%>><%=fmt.format((Double.parseDouble(unApp[i][5]==null?"0.0":unApp[i][5])+Double.parseDouble(unApp[i][6]==null?"":unApp[i][6])))%></td>
	<%--<td class="formtxtc" <%=fontcolor%>><%=unApp[i][3]==null?"":unApp[i][3]%></td>
	<td class="formtxtc" <%=fontcolor%>><%=unApp[i][10]==null?"":unApp[i][10]%></td>
	--%><%
		if(searchstatus.equalsIgnoreCase("search"))
		{
			if(true)	 
			return;
			if(unApp[i][11].equalsIgnoreCase("P"))
			{
				rep="P";
			}
			else
			{
				rep="";
			}
		}
		if(rep.equalsIgnoreCase("P"))
		{	  
	%>
	<td class="formtxtc"><a href="#" title="View Policy Schedule" class="two" onclick="return viewPolicys('<%=unApp[i][9]%>','<%=unApp[i][1]%>','schedule','<%=unApp[i][15]%>','<%=unApp[i][16]%>')"> <b> View </b></a></td>
	<%
	}	  
	else
	{
	%>
	<td class="formtxtc"><a href="#" title="View Policy Schedule" class="two" onclick="return viewPolicys('<%=unApp[i][9]%>','<%=unApp[i][1]%>','schedule','<%=unApp[i][15]%>','<%=unApp[i][16]%>')"><b> View </b></a></td>
	<%
	}	  
	%><%--
	 <td class="formtxtc">  <a href="#"><input type='image' border='0' src="<%=request.getContextPath() %>/images/icon_change_address.gif" title="Upload/Download File" width="22" height="19" onclick="return fileProcess('<%=unApp[i][2]%>','<%=unApp[i][15]%>','<%=eDate%>','<%=data1%>','<%=data2%>')"> </a></td>
	--%></tr>
	<%
		if(k==display)
		break;
	}
	if(unApp.length<=0&&error.length()<=0)
	{
	%>
	<tr>
	<td align="center" colspan="9">
	No Record For This QuoteNo
	</td>
	</tr>
	<%
	}	
	%>
	<tr class="royamenuhead">
	<td class="bottomtext" align="right" colspan="10">
	<%
		 if(unApp.length>11)
		 {
			if(start>0)
			{
	%>
<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>','<%=policy_no%>','<%=data1%>','<%=data2%>','<%=rep%>','<%=search_option%>')"><font  color="white"><<</font>&nbsp;&nbsp;</a>
	<%
	}
	boolean flag=false;
	int iValue=0;
	for(int i=start;i<pages;i++)	 
	{
	if(i<displaypages)
	{
	%>
	<a href ="javaScript:ExistingCustomers_B2B('<%=i+1%>','<%=displaypages%>','<%=start%>','<%=policy_no%>','<%=data1%>','<%=data2%>','<%=rep%>','<%=search_option%>')"><font  color="white"><%=i+1%></font></a>

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
<a href ="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>','<%=policy_no%>','<%=data1%>','<%=data2%>','<%=rep%>','<%=search_option%>')">&nbsp;&nbsp;<font  color="white">>></font></a>
<%
}
}
%>
</td>
</tr>
</table>
<%
if(unAppMulti.length>0)
{
%>
<table  width="90%"   border="0">
<tr><td align="center" colspan="6"><font color="red"><%=error%></font></td><tr>
<%
	if(search_option.equalsIgnoreCase("YES"))
	{
%>
<tr><td colspan="6" align="center"><b>DECLARATIONS </b></td></tr>
<%
	}
%>
<tr class="royamenuhead" >
<td class="bottomtext"><b>S.No</b></td>
<td class="bottomtext"><b>Policy No</b></td>
<td class="bottomtext"><b>LoginId</b></td>
<td class="bottomtext"><b>Open_Cover_No</b></td>
<td class="bottomtext"><b>Premium(AED)</b></td>
<td class="bottomtext"><b>Multiple</b></td>						
<td class="bottomtext"><b><%=pdfstatus%></b></td>
</tr>
<%
int k1=0;
for(int i=0;i<unAppMulti.length;i++)
{
	k1++;
	String str1="";
%>
<tr> 
<td class="formtxtc" <%=fontcolor%>><%=(k1)%></td>
<td class="formtxtc" align="left" <%=fontcolor%>><%=unAppMulti[i][0]%></td>
<td class="formtxtc" align="left" <%=fontcolor%>><%=unAppMulti[i][1]%></td>
<td class="formtxtc" align="left" <%=fontcolor%>><%=unAppMulti[i][2]%></td>
<td class="formtxtc" <%=fontcolor%>><%=Double.parseDouble(unAppMulti[i][3]==null?"0.0":unAppMulti[i][3])+Double.parseDouble(unAppMulti[i][4]==null?"0.0":unAppMulti[i][4])%>
</td>
<td class="formtxtc">
<a href="#" class="two" onclick="byDateMulti('<%=unAppMultiDate[0][0]%>','<%=data1%>','<%=data2%>','<%=unAppMulti[i][0]%>')">View Declarations</a></td>
<td align="center"><a href="#" title="View Multiple Policy Schedule" class="two" onclick="return viewPolicys('<%=unAppMulti[i][0]%>','<%=unAppMulti[i][1]%>','scheduleMultiple','11','<%=unAppMulti[i][2]%>')"><b> View </b></a></td>
</tr>
<%
}
%>
<%
if(unAppMulti.length<=0&&error.length()<=0)
{
%>
<tr>
<td align="center" colspan="9">
No Record For This QuoteNo
</td>
</tr>
<%
}	
%>
</table>
<%
	}
%>
<tr>
<td height="32" align="center" valign="middle" > 
<%
if(!search_option.equalsIgnoreCase("YES"))
{
%>
 <input name="image" type="image" src='../images/Cancel.jpg'  onclick="back('<%=rep%>','<%=data1%>','<%=data2%>','<%=company%>')" / >
 <%
}
else
{
%>
<input name="image" type="image" src='../images/Cancel.jpg' onclick="admProtfolio()"/>
<%
}
%>
</td>
</tr>
</td>
</tr>
</table>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>
 
<input type="hidden" name="applicationNo" >
<input type="hidden" name="from123" >
<input type="hidden" name="rep" >
<input type="hidden" name="data1" >
<input type="hidden" name="data2" >
<input type="hidden" name="eDate" >
<input type="hidden" name="entryDate" >
<input type="hidden" name="company" >
<input type="hidden" name="bname" >
<input type="hidden" name="uname" >
<input type="hidden" name="qno" >
<input type="hidden" name="qno1" >
<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
<input type="hidden" name="identifyCus">
<input type="hidden" name="search_option">
<input type="hidden" name="policy_no"/>
<input type="hidden" name="policynumber" value="<%=policy_no%>"/>
<input type="hidden" name="policyno" />
<input type="hidden" name="loginid" />
<input type="hidden" name="policyMode">
<input type="hidden" name="pid">
<input type="hidden" name="pqno"/>
<input type="hidden" name="screenFrom" value="AdminSearchPortfolio"/>
<input type="hidden" name="productId">
</form>
</body>
</html>
<script>

function fileProcess(pqno,productId,eDate,data1,data2)
{
	document.form1.entryDate.value=eDate;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.rep.value="p";

	document.form1.pqno.value = pqno;
	document.form1.productId.value = productId;
	document.form1.action = "<%=request.getContextPath()%>/marineUploadDownload.jsp";
	document.form1.submit();
}
function byDateMulti(eDate,data1,data2,policy)
{
	document.form1.entryDate.value=eDate;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.policyno.value=policy;
	document.form1.rep.value="p";
	document.form1.pid.value="11";
	document.form1.action="Portfolio_ByDateWithMulti.jsp"
	document.form1.submit();
}

function back(rep,data1,data2,company)
{
	document.form1.rep.value=rep;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.action="Approved_Policy_Portfolio.jsp";
	document.form1.submit();
}


function byApp(applicationNo,data1,data2,rep,eDate,bname,uname,qno,search_option,qno1)
{
	document.form1.applicationNo.value=applicationNo;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.rep.value=rep;
	document.form1.eDate.value=eDate;
	document.form1.bname.value=bname;
	document.form1.uname.value=uname;
	document.form1.qno.value=qno1;
	document.form1.qno1.value=qno;
	document.form1.search_option.value=search_option;
	document.form1.from123.value="admin";
	document.form1.action="Admin_Portfolio_Premium.jsp"
	document.form1.submit();
}



function viewPolicys(policynumber,loginid,policyModee,productId,openNo)
{
	
	 document.form1.policynumber.value=policynumber;
	 document.form1.loginid.value=loginid;
	 document.form1.policyMode.value=policyModee;
	
	var URL ="../Copy of information Admin.jsp?policyMode="+policyModee+"&policynumber="+policynumber+"&loginid="+loginid+"&productTypeIds="+productId+"&openCoverNoSettingCert="+openNo;
		var windowName = "PolicyView";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 700;
		var h =	450;
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
				  // document.list.submit();
                  //  return false;
		return false;
}

function ExistingCustomers_B2B(value123,displaypages,start,policy_no,data1,data2,rep,search_option)
{
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	document.form1.policy_no.value=policy_no;
	//document.form1.entryDate.value=eDate;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.rep.value=rep;
	document.form1.search_option.value=search_option;
	//document.form1.identifyCus.value=identifyCus;
	document.form1.displaypages.value=displaypages;
	document.form1.action="Portfolio_ByPolicy.jsp"
	document.form1.submit();
}

function admProtfolio()
{
	document.form1.action="AdminPortfolio.jsp"
	document.form1.submit();
}

</script>