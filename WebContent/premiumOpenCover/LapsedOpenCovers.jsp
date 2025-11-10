
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Emirates - Marine Insurance</title>
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
</head>

<%
	boolean status = false;
	String brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
	String proposalNo = request.getParameter("proposalNo")==null?"":request.getParameter("proposalNo");
	String[][] information = OCE.getDeActvatedPortfolioDetails(brokerId);
	String[][] lapsedInformation = new String[0][0];
	String[][] BrokerNames=newCover.getDeactivateOpencCoverBrokersHasCover(actualBranch);
	int no_of_records=5;
	//**************total display pages
	int displaypages=3;
	int samplepages=displaypages;
	if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
		displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
	/********************pages************************/
%>
  <body>
  </body>
  </form>
</html>

<head>
	<title> New OpenCover Generation</title>
</head>
<link href="/css/style.css" rel="stylesheet" type="text/css"></head>
<body>
<form name="form1" method="post">
<table  align="center" border="0" cellpadding="0" cellspacing="1" width="100%">
<tr>
<%
/**********pagenation**************Brahmaiah*/ 
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
/****************pagenaaaaaaaaaa************/
%>
<table width="100%"  border="0" cellspacing="1" cellpadding="0" class="blueborder" >
<tr>
<td align="center" valign="top" bgcolor="#FFFFFF">
<table width="100%"  border="0" cellspacing="1" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
<tr>
<td colspan="8" align="right">
Select Broker:
<select name="brokerId" onchange="bidselection(this.value)">
<option value="Select">Select</option>
<%for(int i=0;i<BrokerNames.length;i++)
		{
%>
<option value="<%=BrokerNames[i][1]%>" <%=BrokerNames[i][1].equalsIgnoreCase(brokerId)?"selected":""%>><%=BrokerNames[i][0]%></option>
<%}%>
</select>	
</td>
</tr>
 <tr class="blueborder">  <td colspan="6" align="center"> Deactivated Open Covers </td>
</tr>
		                <tr class="blueborder"> 
		                            <td align="center">S.No</td>
		                            <!-- <td align="center">OpenCover Number</td>-->
		                            <td align="center">Mississippi OpenCover Number</td>
		                            <td align="center">Proposal No</td>
		                            <td align="center">Customer Name </td>
									<td align="center">Policy Start Date</td>
		                            <td align="center">Policy End date </td>
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
%>
				     
					<tr class="ltbgyellow"> 
		           <td align = "center" height="23"><%=(i+1)%></td>
		        <!--   <td align = "center" height="23"><%=information[i][1]%></td>-->
				<td align = "center" height="23"><%=information[i][7]%>
		        <td align="center"><%=information[i][0]%></td>
		        <td align="center"><%=information[i][4]%></td>
		        <td align="center"><%=information[i][2]%></td>
		        <td align="center"><%=information[i][3]%></td>
				</tr>   
		          <%
						 if(k==display)
								  break;
						  }%>
		        <tr class="blueborder">
			 <td height="12" align="right" colspan="8">
<%
					if(length123>no_of_records)
						{
						if(start>0)
						{
							 %>
<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>')"><font  color="blue"><<</font>&nbsp;&nbsp;</a>
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
<input type = "hidden" name = "coverNo"/>
</table>
<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
<input type="hidden" name="proposalNo">
<input type="hidden" name="requestfrom" value="LapsedOpenCovers">
</form>

<script language = "javascript">

function bidselection(val)
{
	document.form1.brokerId.value=val;
	document.form1.action = "LapsedOpenCovers.jsp";
	document.form1.submit();
}

function ExistingCustomers_B2B(value123,displaypages,start)
{
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	document.form1.displaypages.value=displaypages;
	document.form1.action="LapsedOpenCovers.jsp"
	document.form1.submit();
}

</script>