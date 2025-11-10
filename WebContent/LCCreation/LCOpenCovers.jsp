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
	String path = (String)request.getContextPath();
%>
<%@ include file="../admin/header.jsp" %>

</td></tr></table>
</td></tr></table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0" align=left>
<tr>
<td align="left" valign="top" width="100%">
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
a{
	text-decoration:none;
}
-->
</style>
<jsp:useBean id="lcBean" class="com.maan.opencover.LCDetails.LCInputsBean">
</jsp:useBean>
<jsp:useBean id="premiumBean" class="com.maan.premium.DAO.PremiumInputsBean">
</jsp:useBean>
</head>
<%

HashMap brokerDetails1 = new HashMap();
brokerDetails1 = (HashMap)session.getAttribute("BrokerDetails");
String cid="";
String decimalPlace ="";
String currencyType ="";
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

com.maan.services.util.dataCollection collect = new com.maan.services.util.dataCollection();
	
String[][] viewQuote123 = new String[0][0];
		
	String broLogin = request.getParameter("lcBroker");
	if(broLogin==null)
		broLogin = (String)request.getAttribute("lcBroker");
	broLogin = broLogin!=null?broLogin:"";
	String bcName = request.getParameter(""+broLogin);
	if(bcName==null)
		bcName = request.getParameter("bcName");
	bcName = bcName!=null?bcName:"";

viewQuote123=collect.getCreatedOpenCoversSearch(broLogin,"","","select","royal","Admin");
	


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
        <td width="18%" class="bottomtext" align="left"><B>EXISTING OPEN COVERS</td>
        <td width="18%" class="bottomtext" align="left"><B>Broker&nbsp;:&nbsp;<%=bcName%></td>
		</tr>
		</table>							
		<table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <tr class="royamenuhead"> 
        <td width="7%" height="23" align="center" class='bottomtext'><b>S.No</b></td>
        <td width="13%" align="center" class='bottomtext'><b>Core Application Policy No</b></td>
        <td width="10%" align="center" class='bottomtext'><b>Open Cover No</b></td>
        <td width="23%" align="center" class='bottomtext'><b>Customer Name</b> </td>
        <td width="14%" align="center" class='bottomtext'><b>Open Cover Date</b> </td>
        <td width="14%" align="center" class='bottomtext'><b>Validity Period</b> </td>
		<td width="10%" align="center" class='bottomtext'><b>LC DETAILS</b></td>
	
		</tr>
	<%
    int k=0;
	int skip=0;
	int count=0;
	String openNos = "";
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
		if(viewQuote123[i][11]==null||viewQuote123[i][11].equals("")||viewQuote123[i][11].equalsIgnoreCase("null"))
		{
               str=(viewQuote123[i][8]==null?"":viewQuote123[i][8])+" "+(viewQuote123[i][9]==null?"":viewQuote123[i][9]);
		}
		else
		{
                str=viewQuote123[i][11];
		}
		openNos = openNos + viewQuote123[i][0] +",";
%>
								<tr class = "formtxtf"> 
                              <td height="23" align="center" ><%=k%></td>
							  <td align="left" ><%=viewQuote123[i][14]==null?"":viewQuote123[i][14]%></td>
                              <td align="center"><%=viewQuote123[i][0]==null?"":viewQuote123[i][0]%></td>
                              <td align="left"><%=str%></td>
                              <td align="center" ><%=viewQuote123[i][2]%>/<%=viewQuote123[i][3]%>/<%=viewQuote123[i][4]%></td>
                              <td align="center"><%=viewQuote123[i][5]%>/<%=viewQuote123[i][6]%>/<%=viewQuote123[i][7]%></td>
                            
                            <td align="center">&nbsp;<a href="#" class="link" onClick="javascript:return openLCDetails('<%=broLogin%>','<%=viewQuote123[i][0]%>');"> View </a>&nbsp;</td>
	
                         </tr>


						  <%
							  if(k==display)
								  break;
							}
						  if(viewQuote123.length==0)
								{
						  %>
							<tr align="center" ><td class="formtxtf" colspan="6"><B><font size=2 color="red">THERE IS NO OPEN COVERS FOR THIS USER</B>
							</td></tr>
							<%}
						 %>
                            <tr class="royamenuhead">
							 <td class='bottomtext' height="12" align="right" colspan="8">
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
				 <a href="#"  class="buttonsMenu" onCLick="back()">
				 <img src="<%=path%>/images/Back.jpg"></a> </td>
		   </tr>
			</table>
			</td>
          </tr>
        </table>
<%
		if(openNos.length()>0)
			openNos = openNos.substring(0,(openNos.length()-1));
		//HashMap HashLCR = new HashMap();
		String LCReports[][] = new String[0][0];
		//LCReports = lcBean.getLCReportsold(openNos,cid);
		
		if(LCReports.length>0)
		{
%>
<table width="100%"  border="0" cellspacing="1" cellpadding="0" >
        <tr>
        <td align="center" valign="top" bgcolor="#FFFFFF">
        <table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <tr class="blueborder"> 
        <td width="18%" class="heading" align="left"><B>LC SMART REPORTS</td>
        <td width="18%" class="heading" align="left"></td>
		</tr>
		</table>							
		<table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <tr class="blueborder"> 
        <td width="3%" align="center" class='text'><b>S.No</b></td>
        <td width="20%" align="center" class='text'><b>Broker Name</b></td>
        <td width="15%" align="center" class='text'><b>Core Application Policy No</b></td>
        <td width="20%" align="center" class='text'><b>Customer Name</b> </td>
        <td width="15%" align="center" class='text'><b>Bank Name</b> </td>
        <td width="10%" align="center" class='text'><b>LC Number</b> </td>
		<td width="10%" align="center" class='text'><b>Pending LC Amount</b></td>
	
		</tr>
<%
	int kc = 0;
	for(int i=0;i<viewQuote123.length;i++)
	{
		
		for(int j=0;j<LCReports.length;j++)
		{
			if(LCReports[j][0].equalsIgnoreCase(viewQuote123[i][0]))
			{
				double LCPending = 0;
				/*String tempSum[][] = premiumBean.getLCBasedTotalSumInsured(LCReports[j][4],LCReports[j][5],viewQuote123[i][0]);
				if(tempSum.length>0)
				{
					LCPending = Double.parseDouble(LCReports[j][3])-Double.parseDouble(tempSum[0][0]); 
				}*/
				LCPending = Double.parseDouble(LCReports[j][3]);
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
				<tr class="LTblueBG"> 
				<td width="3%" align="center" class='text'><%=(++kc)%></td>
				<td width="20%" align="left" class='text'><%=bcName%></td>
				<td width="15%" align="left" class='text'><%=viewQuote123[i][14]==null?"":viewQuote123[i][14]%></td>
				<td width="20%" align="left" class='text'><%=str%> </td>
				<td width="15%" align="left" class='text'><%=LCReports[j][1]%> </td>
				<td width="10%" align="left" class='text'><%=LCReports[j][2]%> </td>
				<td width="10%" align="right" class='text'><%=fmt.format(LCPending)%></td>
			
				</tr>
	<%
			}
		}
	}
%>
		<tr> 
			<td align="center" colspan="3">&nbsp;</td>
			 <td align="center">
				 <a href="#"  class="buttonsMenu" onCLick="back()">
				 <img src="<%=path%>/images/Back.jpg"></a> 
				 &nbsp;<a href="#"  class="buttonsMenu" onCLick="PrintReport('<%=broLogin%>','<%=bcName%>')">
				 <img src="<%=path%>/images/Print.jpg"></a>
				&nbsp; 
				 </td>

				 
				 <td align="left" colspan="3"><a href="#"  class="buttonsMenu" onCLick="SaveReport('<%=broLogin%>','<%=bcName%>')">
				 <img src="<%=path%>/images/Save.jpg"></a></td>
		   </tr>
<%
		}
%>

<input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">

<input type="hidden" name="openNo">
<input type="hidden" name="lcBroker" value="<%=broLogin%>"/>
<input type="hidden" name="bcName" value="<%=bcName%>">
<input type="hidden" name="actionPath">


</form>

</body>
<script>
function back()
{
	document.ViewOpenCover.action="../LCCreation/LCCreationBrokerList.jsp";
	document.ViewOpenCover.submit();
}
function ExistingCustomers_B2B(value123,displaypages,start)
{
	document.ViewOpenCover.spage.value=value123;
	document.ViewOpenCover.start.value=start;
	document.ViewOpenCover.displaypages.value=displaypages;
	document.ViewOpenCover.action="<%=path%>/LCCreation/LCOpenCovers.jsp"
	document.ViewOpenCover.submit();
}
function openLCDetails(log,open)
{
	document.ViewOpenCover.openNo.value = open;
	document.ViewOpenCover.lcBroker.value = log;
	document.ViewOpenCover.action="<%=path%>/LCCreation/LCOpenCoverWise.jsp";
	document.ViewOpenCover.submit();
}
function SaveReport(broLog,bcn)
{
	document.ViewOpenCover.action="<%=path%>/LCCreation/LCSmartReportSave.jsp?lcBroker="+broLog+"&bcName="+bcn;
	document.ViewOpenCover.submit();
	return false;
}
function PrintReport(broLog,bcn)
{
	var URL = "<%=path%>/LCCreation/LCSmartReportPrint.jsp?lcBroker="+broLog+"&bcName="+bcn;
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
