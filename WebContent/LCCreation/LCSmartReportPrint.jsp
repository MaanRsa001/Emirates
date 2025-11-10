
<%@ include file="../login/home1.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String paths = (String)request.getContextPath();
%>

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
		String brokerCode = (String)session.getAttribute("AdminBranchCode");
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

String[][] viewQuote123 = new String[0][0];
HashMap lcDetails= new HashMap();
HashMap cusDetails = new HashMap();
lcDetails = lcBean.getLCReports(brokerCode,cid);
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
<body onLoad="window.print()">
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
        <td width="18%" class="bottomtext" align="right"><B>&nbsp;As on date&nbsp;:&nbsp;<%=rptDate%></td>
		</tr>
		</table>							
		<table width="98%"  border="0" cellspacing="1" cellpadding="0">
        <tr class="royamenuhead"> 
        <td width="6%" height="23" align="center" class='bottomtext'><b>S.No</b></td>
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
   
	for(int i=0;i<viewQuote123.length;i++)
	{
		
		String str="";
		if(cusDetails.get(""+viewQuote123[i][2])!=null)
		    str=(String)cusDetails.get(""+viewQuote123[i][2]);
		double usedAmt = 0;
		usedAmt = lcBean.getLCUsedSumInsured(viewQuote123[i][11],viewQuote123[i][12],viewQuote123[i][7]);
		double exRate = lcBean.getExchangeRate(viewQuote123[i][13],cid);
		
%>
						<tr class = "formtxtf"> 
                              <td height="23" align="center" ><%=(i+1)%></td>
							  <td align="left" ><%=viewQuote123[i][0]==null?"":viewQuote123[i][0]%></td>
                              <td align="left" ><%=viewQuote123[i][1]==null?"":viewQuote123[i][1]%></td>
                              <td align="left" ><%=str%></td>
                              <td align="left" ><%=viewQuote123[i][3]==null?"":viewQuote123[i][3]%></td>
                              <td align="left"><%=(viewQuote123[i][4]==null?"":viewQuote123[i][4])%></td>
                              <td align="left"><%=(viewQuote123[i][8]==null?"":viewQuote123[i][8])%></td>
                              <td align="left"><%=(viewQuote123[i][9]==null?"":viewQuote123[i][9])%></td>
                              <td align="right"><%=fmt.format(Double.parseDouble((viewQuote123[i][10]==null?"0":viewQuote123[i][10])))%></td>
                              <!--  <td align="right"><%=fmt.format(exRate)%></td>-->
                              <td align="right" ><%=fmt.format(usedAmt)%></td>
                              <td align="right"><%=fmt.format(Double.parseDouble((viewQuote123[i][5]!=null?viewQuote123[i][5]:"0")))%></td>
                        </tr>


<%
	}
%>
            </table></td>
              </tr>
            </table></td>
          </tr>
          <tr class="royamenuhead" align="center">
                  <td colspan="3"></td>
          </tr>          
          <tr align="center">
            <td colspan="3">
			<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr> 
			 <td align="center" class="button-left">
				 <a href="#"  class="buttonsMenu" onCLick="window.close()">
				 <img src="<%=paths%>/images/Back.jpg"></a> </td>
		   </tr>
			</table>
			</td>
          </tr>
        </table>
