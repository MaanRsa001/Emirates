<%
String productSelection = request.getParameter("productSelection")==null?"":request.getParameter("productSelection");
if((request.getQueryString())!=null&&!productSelection.equalsIgnoreCase("productSelection"))
{
	session.removeAttribute("ses");
}
%>
<%@ include file="../login/home1.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String paths = (String)request.getContextPath();
%>
<%@ include file="../admin/header.jsp" %>
	</td></tr></table>
</td></tr></table>
</td></tr></table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0" align=left>
<tr>
<td align="left" valign="top" width="100%">
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=paths%>/css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
a{
	text-decoration:none;
}
-->
</style>
<jsp:useBean id="lcBean" class="com.maan.opencover.LCDetails.LCInputsBean">
</jsp:useBean>


</head>
<%
		String currencyType = "";
		HashMap brokerDetails1 = new HashMap();
		brokerDetails1 = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String decimalPlace ="";
		if(brokerDetails1.size()>0)
		{
			cid = (String)brokerDetails1.get("Orgination");
			currencyType = (String)brokerDetails1.get("CurrencyAbb");
			decimalPlace = (String)brokerDetails1.get("CurrencyDecimal");
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

int no_of_records = 10;
int displaypages = 5;
int samplepages = displaypages;

if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
	displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));

String[][] viewQuote123 = new String[0][0];
HashMap lcDetails= new HashMap();
HashMap cusDetails = new HashMap();
lcDetails = lcBean.getLCReports(actualBranch,cid);
if(lcDetails.size()>0)
{
	viewQuote123 = (String[][])lcDetails.get("LCAll");
	cusDetails = (HashMap)lcDetails.get("cusDetails");
}

java.util.Date date = new java.util.Date();
java.text.SimpleDateFormat YearOnly = new java.text.SimpleDateFormat("yyyy");
java.text.SimpleDateFormat MonthOnly = new java.text.SimpleDateFormat("MM");
java.text.SimpleDateFormat DayOnly = new java.text.SimpleDateFormat("dd");

String YearString = YearOnly.format(date);
String MonthString = MonthOnly.format(date);
String DayString = DayOnly.format(date);
String rptDate = DayString+"/"+MonthString+"/"+YearString;

%>


<%
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
else if((displaypages-spage)==(samplepages-1)&&start!=0)
{
	start--;
	displaypages--;
}

	%>

<form name="ViewOpenCover" method="post">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">

			
			
          <tr align="center">
            <td colspan="3">
					<table width="100%">
					<tr>
					<td></td>
					<td>
				
			</td>
	      </tr>
        <tr align="center">
        <td colspan="3">
		<table width="100%"  border="0" cellspacing="1" cellpadding="0" >
        <tr>
        <td align="center" valign="top" bgcolor="#FFFFFF">
        <table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <tr class="royamenuhead"> 
        <td width="18%" class="bottomtext" align="left"><B>LC SMART REPORTS</td>
        <td width="18%" class="bottomtext" align="right"><B>&nbsp;As On Date&nbsp;:&nbsp;<%=rptDate%></td>
		</tr>
		</table>							
		<table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <tr class="royamenuhead"> 
        <td width="6%" height="23" align="center" class='text'><b>S.No</b></td>
        <td width="20%" align="center" class='bottomtext'><b>Broker Name</b></td>
        <td width="15%" align="center" class='bottomtext'><b>Open Cover Code</b></td>
        <td width="20%" align="center" class='bottomtext'><b>Customer Name</b> </td>
        <td width="15%" align="center" class='bottomtext'><b>Bank Name</b> </td>
		<td width="12%" align="center" class='bottomtext'><b>LC Number</b></td>
		<td width="12%" align="center" class='bottomtext'><b>LC Start Date</b></td>
		<td width="12%" align="center" class='bottomtext'><b>LC Expiry Date</b></td>
		<td width="12%" align="center" class='bottomtext'><b>LC Amount</b></td>
		<!--<td width="12%" align="center" class='bottomtext'><b>Exchange Rate</b></td>-->
		<td width="12%" align="center" class='bottomtext'><b>Used Amount(<%=currencyType%>)</b></td>
		<td width="7%" align="center" class='bottomtext' nowrap><b>Pending LC Amount(<%=currencyType%>)</b></td>
	
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
		if(k==1)
		{
		}
		String str="";
		if(cusDetails.get(""+viewQuote123[i][2])!=null)
		    str=(String)cusDetails.get(""+viewQuote123[i][2]);
		double usedAmt = 0;
		usedAmt = lcBean.getLCUsedSumInsured(viewQuote123[i][11],viewQuote123[i][12],viewQuote123[i][7]);
		double exRate = lcBean.getExchangeRate(viewQuote123[i][13],cid);
%>
						<tr "class="formtxtr"> 
                              <td height="23" align="center" class='text'><%=k%></td>
							  <td align="left" class='text'><%=viewQuote123[i][0]==null?"":viewQuote123[i][0]%></td>
                              <td align="left" class='text'><%=viewQuote123[i][1]==null?"":viewQuote123[i][1]%></td>
                              <td align="left" class='text'><%=str%></td>
                              <td align="left" class='text'><%=viewQuote123[i][3]==null?"":viewQuote123[i][3]%></td>
                              <td align="left" class='text'><%=(viewQuote123[i][4]==null?"":viewQuote123[i][4])%></td>
                              <td align="left" class='text'><%=(viewQuote123[i][8]==null?"":viewQuote123[i][8])%></td>
                              <td align="left" class='text'><%=(viewQuote123[i][9]==null?"":viewQuote123[i][9])%></td>
                              <td align="right" class='text'><%=fmt.format(Double.parseDouble((viewQuote123[i][10]==null?"0":viewQuote123[i][10])))%></td>
                              <!--  <td align="right" class='text'><%=fmt.format(exRate)%></td>-->
                              <td align="right" class='text'><%=fmt.format(usedAmt)%></td>
                              <td align="right" class='text'><%=fmt.format(Double.parseDouble((viewQuote123[i][5]==null?"0":viewQuote123[i][5])))%></td>
                        </tr>


						  <%
							  if(k==display)
								  break;
							}
						  if(viewQuote123.length==0)
								{
						  %>
							<tr align="center" class="formtxtf"><td colspan="6"><B><font size=2 color="red">THERE IS NO OPEN COVERS FOR THIS USER</B>
							</td></tr>
							<%}
						 %>
                            <tr class="royamenuhead">
							 <td class="bottomtext" height="12" align="right" colspan="11">
							 <%
							 if(length123>no_of_records)
							 {
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
					 }
							 
							 
							 %>
							</td>
              </tr>
            </table></td>
              </tr>
            </table></td>
          </tr>
          <tr align="center">
                  <td colspan="3"></td>
          </tr>          
          <tr align="center">
            <td colspan="3">
			<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr> 
			 <td align="center">
				<a href="#"  class="buttonsMenu" onCLick="Backs()">
				 <img src="<%=paths%>/images/Back.jpg"></a>&nbsp; <a href="#"  class="buttonsMenu" onCLick="PrintReport()">
				 <img src="<%=paths%>/images/PrintQuote.jpg"></a>&nbsp;<a href="#"  class="buttonsMenu" onCLick="SaveReport()">
				 <img src="<%=paths%>/images/Save.jpg"></a> </td>
		   </tr>
			</table>
			</td>
          </tr>
        </table>


<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
<input type="hidden" name="actionPath">

</form>

</body>
<script>
function Backs()
{
	document.ViewOpenCover.action="../admin/admin_welcome.jsp";
	document.ViewOpenCover.submit();
}
function ExistingCustomers_B2B(value123,displaypages,start)
{
	document.ViewOpenCover.spage.value=value123;
	document.ViewOpenCover.start.value=start;
	document.ViewOpenCover.displaypages.value=displaypages;
	document.ViewOpenCover.action="<%=paths%>/LCCreation/LCSmartReprot.jsp"
	document.ViewOpenCover.submit();
}
function SaveReport()
{
	document.ViewOpenCover.action="<%=paths%>/LCCreation/LCSmartReportSave.jsp";
	document.ViewOpenCover.submit();
	return false;
}
function PrintReport()
{
	var URL = "<%=paths%>/LCCreation/LCSmartReportPrint.jsp";
	var windowName = "LCREPORT";
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
</script>
</html>
