<%@ page import = "java.io.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>
<%@ include file="../login/home1.jsp" %>
<jsp:useBean id="lcBean" class="com.maan.opencover.LCDetails.LCInputsBean">
</jsp:useBean>
</head>
<%
//String userType=(String) session.getAttribute("user1");
String userType="admin";
String productTypeId = "";
if(session.getAttribute("product_id")!=null)
	productTypeId = (String)session.getAttribute("product_id");
String fromBroker = request.getParameter("fromBroker");
fromBroker = fromBroker==null?"No":fromBroker;
if(fromBroker.equalsIgnoreCase("Yes"))
{
%>
	<table width="90%"  border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td align="left" valign="top" width="100%">
				<%@include file="../openMenu.jsp"%>
			</td>
		</tr>
	</table>
<%
}
else
{
%>
	<%@ include file="../admin/header.jsp" %>
	</td></tr></table>
</td></tr></table>
</td></tr></table>
<table width="90%"  border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
<td align="center" valign="top" width="100%">
<%
}
%>
<%
String path = request.getContextPath();

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

String errorMessage = request.getParameter("error");
String[][] tradeData = new String[0][0];
DateFunction dt=new DateFunction();
%>
<html>
<head>
<title>Emirates</title>

</head>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<body>
<form name="group_view" method="post">

  
<table width="90%"  border="0" cellspacing="0" cellpadding="0" align="center">
<tr> 
<td width="8">&nbsp;</td>
<td width="98%" ><span class="heading">LC REPORT</span></td>
  
<%
	String broLogin = request.getParameter("lcBroker");
	if(broLogin==null)
		broLogin = (String)request.getAttribute("lcBroker");
	broLogin = broLogin!=null?broLogin:"";
	String bcName = request.getParameter(""+broLogin);
	if(bcName==null)
		bcName = request.getParameter("bcName");
	bcName = bcName!=null?bcName:"";
	String openAll[][] = new String[0][0];
	String openNo = "";
	openNo = request.getParameter("openNo");
	openNo = openNo==null?"0":openNo;
	
	if(fromBroker.equalsIgnoreCase("Yes"))
	{
		String result[][] = new String[0][0];
		result = lcBean.getBcName(broLogin);
		if(result.length>0)
		{
			bcName  = result[0][0];
			broLogin  = result[0][1];
		}
		openAll = lcBean.getOpenCoverNos(broLogin,openNo);
	}
	else if(broLogin.length()>0)
		openAll = lcBean.getOpenCoverNos(broLogin,openNo);
%>
 
<%
for(int m=0;m<openAll.length;m++)
{
	String cov = openAll[m][0];
	String[][] coverDesc = lcBean.getLCs(cov);
	if(m>0)
	{
%>
		
		<br>
<%
	}
	else
	{
%>
		<tr> <td> </td></tr>
		<tr class="royamenuhead"><td width="18%" align="center" colspan="9" class="bottomtext"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Broker Name : <%=bcName%></b></td></tr> 
<%
	}
	String cusNames = openAll[m][2].replaceAll("'","*");
%>
		<table width="90%" border="0" cellpadding="2" cellspacing="1" align="center">
		 <tr class="formtextf" > 																																																																																																														
                              <td width="18%" align="center" colspan="10" class="formtextf"><B>Core Application Policy No&nbsp;:&nbsp;<%=openAll[m][1]%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Customer Name&nbsp;:&nbsp;<%=openAll[m][2]%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <% 
                              if(userType.equalsIgnoreCase("admin")) {
                               %>
							  Add New LC Number&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="two" onClick="return openLCDetails('<%=cov%>','0','add','<%=bcName%>','<%=openAll[m][1]%>','<%=cusNames%>','0')">Click here</a></b>
							  <%
							  }
							   %>
							  </td>
							  </tr>
	<%
		if(coverDesc.length>0)
		{
	%>
				<tr class="royamenuhead"> 
                  <td class="bottomtext" width="34">&nbsp;<b>S No</b></td>
			      <td class="bottomtext">&nbsp;<b>LC NUMBER</b></td>
				  <td class="bottomtext">&nbsp;<b>BANK NAME</b></td>
				  <td class="bottomtext">&nbsp;<b>LC AMOUNT</b>&nbsp;&nbsp;&nbsp;</td>
				  <td class="bottomtext">&nbsp;<b>CURRENCY TYPE</b></td>
				  <td class="bottomtext">&nbsp;&nbsp;&nbsp;&nbsp;<b>LC DATE</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				  <td class="bottomtext">&nbsp;<b>EXPIRY DATE</b></td>
				  <td class="bottomtext">&nbsp;<b>USED AMOUNT</b></td>
				<% 
				 if(userType.equalsIgnoreCase("admin")) {
				 %>
					<td class="bottomtext">&nbsp;<b>EDIT</b></td>
					<td class="bottomtext">&nbsp;<b>DELETE</b></td>
			   <% } %>
		        </tr>
				<%	for(int i=0;i<coverDesc.length;i++)
				{
					String[][] usedammount=lcBean.getCertificates(cov,coverDesc[i][2]);
					double sum=0.0;
					if(usedammount.length>0)
					{
						for(int j=0;j<usedammount.length;j++)
						{
							sum=sum+Double.parseDouble(usedammount[j][4]);
						}
					}
				%>
				<%
					 String dateis="";
					if(coverDesc[i][3]!=null&&coverDesc[i][3].length()>0)
					{
						 dateis=coverDesc[i][3].substring(0,10);
					}
					 String dateis1="";
					if(coverDesc[i][6]!=null&&coverDesc[i][6].length()>0)
					{
						 dateis1=coverDesc[i][6].substring(0,10);
					}
					String bankName = lcBean.getBname(coverDesc[i][1]==null?"":coverDesc[i][1],cid);
					String curName = lcBean.getCName(coverDesc[i][12]==null?"":coverDesc[i][12].trim(),cid);
				%>
	<tr>
	   <td class='formtxtc'><%=(i+1)%></td>
		<td class='formtxtc' ><%=coverDesc[i][2]==null?"":coverDesc[i][2]%></td>
		<td class='formtxtf'><%=bankName%></td>
		<td class='formtxtc' align="right" ><%=fmt.format(Double.parseDouble(coverDesc[i][4]==null?"0":coverDesc[i][4]))%></td>
		<td class='formtxtc' ><%=curName%></td>
		
		<%
         java.util.Date dtTmp = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateis);
          String newdateis = new  java.text.SimpleDateFormat("dd-MM-yyyy").format(dtTmp);   
		 
		 java.util.Date dtTmp1 = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateis1);
         String newdateis1 = new  java.text.SimpleDateFormat("dd-MM-yyyy").format(dtTmp1);  
		cusNames = openAll[m][2].replaceAll("'","*");
	   %>

		<td class='formtxtc'><%=newdateis%></td>
		<td class='formtxtc'><%=newdateis1%></td>
		
		<td class='formtxtc'><a href="#" class="two" onClick="javascript:OpencoverCertificate('<%=cov%>','<%=coverDesc[i][11]==null?"0":coverDesc[i][11]%>','<%=bcName%>','<%=openAll[m][1]%>','<%=cusNames%>','<%=broLogin%>','<%=coverDesc[i][2]==null?"":coverDesc[i][2]%>','<%=bankName%>','<%=curName%>','<%=coverDesc[i][4]==null?"0":coverDesc[i][4]%>','<%=coverDesc[i][1]==null?"":coverDesc[i][1]%>');"><%=fmt.format(sum)%></a></td>
		<% 
         if(userType.equalsIgnoreCase("admin")) {
         %>
		<td class='formtxtc'><a class="two" href="#" onClick="javascript:openLCDetails('<%=cov%>','<%=coverDesc[i][11]==null?"0":coverDesc[i][11]%>','edit','<%=bcName%>','<%=openAll[m][1]%>','<%=cusNames%>','<%=sum%>','<%=coverDesc[i][2]==null?"":coverDesc[i][2]%>','<%=bankName%>','<%=curName%>','<%=coverDesc[i][1]==null?"":coverDesc[i][1]%>')"> Edit</a></td>
		<%
			if(sum<=0)
			{
		%>
			<td class='formtxtc' ><a class="two" href="#" onClick="javascript:DeleteLCDetails('<%=cov%>','<%=coverDesc[i][11]==null?"0":coverDesc[i][11]%>','<%=bcName%>','<%=openAll[m][1]%>','<%=cusNames%>','<%=broLogin%>')" border="0"> Delete </a></td>
		<%
			}
			else
			{
		%>
				<td  class='formtxtc' align="center"> - </td>
		<%
			}
		%>
		<%
		}
		 %>
	</tr>
<%
		}
	}
	else
	{
%>
		
<%
	}
}
%>
</table>

<table border="0" align="center" cellpadding="0" cellspacing="0">
<tr> 
<td align="center" >
<a href="#" class="buttonsMenu" onCLick="back('<%=fromBroker%>','<%=broLogin%>','<%=bcName%>')">
<img src="<%=path%>/images/Back.jpg"></a> </td>
</tr>
</table>

<input type="hidden" name="opencover"/>
<input type="hidden" name="openNo" value="<%=openNo%>"/>
<input type="hidden" name="lcNos"/>
<input type="hidden" name="mode"/>
<input type="hidden" name="bcName"/>
<input type="hidden" name="moc"/>
<input type="hidden" name="cName"/>
<input type="hidden" name="usedamt"/>
<input type="hidden" name="login" value="<%=broLogin%>"/>
<input type="hidden" name="from"/>
<input type="hidden" name="lcBroker"/>
<input type="hidden" name="bankName"/>
<input type="hidden" name="LcNo"/>
<input type="hidden" name="curName"/>
<input type="hidden" name="bankId"/>
<input type="hidden" name="fromBroker" value="<%=fromBroker%>"/>
</form>
<script>
function back(from,log,bn)
{
	document.group_view.lcBroker.value = log;
	document.group_view.bcName.value = bn;
	if(from=='Yes')
		document.group_view.action="../ViewOpenCovers.jsp";
	else
		document.group_view.action="../LCCreation/LCOpenCovers.jsp";
	document.group_view.submit();
}
function openLCDetails(opencover,lcNos,mode,bn,moc,cn,uamt,lccno,bank,curNames,bid)
{
	cn = cn.replace("*","'");
	document.group_view.opencover.value = opencover;
	document.group_view.lcNos.value = lcNos;
	document.group_view.mode.value = mode;
	document.group_view.bcName.value = bn;
	document.group_view.moc.value = moc;
	document.group_view.cName.value = cn;
	document.group_view.usedamt.value = uamt;
	document.group_view.LcNo.value = lccno;
	document.group_view.bankName.value = bank;
	document.group_view.curName.value = curNames;
	document.group_view.bankId.value = bid;
	document.group_view.action="../LCCreation/lcDetailsEntryAdmin.jsp";
	document.group_view.submit();
}
function DeleteLCDetails(opencover,lcNos,bn,moc,cn,log)
{
	var status = confirm("Are you sure want remove LC Details?","Yes","No");
	if(status)
	{
		document.group_view.opencover.value = opencover;
		document.group_view.lcNos.value = lcNos;
		document.group_view.bcName.value = bn;
		document.group_view.moc.value = moc;
		document.group_view.cName.value = cn;
		document.group_view.lcBroker.value = log;
		document.group_view.from.value="AdminLCMasterDelete";
		document.group_view.action="<%=path%>/LCControl/LCDetailsController";
		document.group_view.submit();
	}
}
function OpencoverCertificate(opencover,lcNos,bn,moc,cn,log,lccno,bank,curNames,uamt,bid)
{
		document.group_view.opencover.value = opencover;
		document.group_view.lcNos.value = lcNos;
		document.group_view.bcName.value = bn;
		document.group_view.moc.value = moc;
		document.group_view.cName.value = cn;
		document.group_view.lcBroker.value = log;
		document.group_view.LcNo.value = lccno;
		document.group_view.bankName.value = bank;
		document.group_view.curName.value = curNames;
		document.group_view.usedamt.value = uamt;
		document.group_view.bankId.value = bid;
		document.group_view.action="<%=path%>/LCCreation/lcDetailsReports.jsp";
		document.group_view.submit();
}
</script>