<%@ include file="/login/sessionsCheckNormal.jsp" %>

<jsp:useBean id="com" class="com.maan.admin.ratingAdmin">
<jsp:setProperty name="com" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>



<%
String cid=request.getParameter("cid")==null?"":request.getParameter("cid");
String errorMessage = request.getParameter("error");

if(null !=errorMessage && !(errorMessage.equals("")))
{
	//out.println("<font color='red' face='ariel'><b>"+err_mes+"</b></font>");
}
		
String[][] tradeData = new String[0][0];
String branchCode = (String) session.getAttribute("BranchCode");
tradeData=com.getAmendedComDetails(cid,branchCode);
%>

<html>
<head>
<title>Emirates</title>
</head>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<body>
<form name="group_view" method="post" action="rating_factor.jsp">

<table width="100%" border="0" cellpadding='2' cellspacing='1' align='center'>
	<tr class="royamenuhead"> 
		<td class = "bottomtext">COMMODITY_ID</td>
		<td class = "bottomtext">COMMODITY_NAME</td>
		<td class = "bottomtext">ICC_A_SEA</td>
		<td class = "bottomtext">ICC_B_SEA </td>
		<td class = "bottomtext">ICC_C_SEA</td>
		<td class = "bottomtext">ICC (C) + NON DELIVERY</td>
		<td class = "bottomtext">FROZEN MEAT (A)</td>
		<td class = "bottomtext">FROZEN FOOD(A)</td>
		<td class = "bottomtext">FROZEN MEAT(C)</td>
		<td class = "bottomtext">FROZEN FOOD(C)</td>
		<td class = "bottomtext">ICC Air Cargo Air</td>
		<td class = "bottomtext">ICC AIR CARGO ALL RISKS</td>
		<td class = "bottomtext">All Risks Land Transit</td>
		<td class = "bottomtext">Land Transit Land</td>
		<td class = "bottomtext">All Risks Parcel Post Air</td>
		<td class = "bottomtext">All Risks Sea and Air</td>
		<td class = "bottomtext"> RAG</td>
		<td class = "bottomtext"> EFFECTIVE&nbsp;DATE</td>
		<td class = "bottomtext">COMMODITY_TYPE_ID</td>
		<td class = "bottomtext">STATUS</td>
		<td class = "bottomtext"> FRAGILE</td>
		 <td class = "bottomtext"> EXCLUSION_ID</td>
		<td class = "bottomtext"> WARRANTY_ID</td>
		<td class = "bottomtext"> REMARKS</td>	
	</tr>
	<%
		for(int i=0;i<tradeData.length;i++)
		{
	%>
	<tr>
	    <td class = "LTblueBG" ><%=tradeData[i][0]==null?"":tradeData[i][0]%></td>
		<td class = "LTblueBG" ><%=tradeData[i][1]==null?"":tradeData[i][1]%></td>
		<td class = "LTblueBG"><%=tradeData[i][2]==null?"":tradeData[i][2]%></td>
		<td class = "LTblueBG"><%=tradeData[i][3]==null?"":tradeData[i][3]%></td>
		<td class = "LTblueBG"><%=tradeData[i][4]==null?"":tradeData[i][4]%></td>
		<td class = "LTblueBG"><%=tradeData[i][5]==null?"":tradeData[i][5]%></td>
		<td class = "LTblueBG"><%=tradeData[i][6]==null?"":tradeData[i][6]%></td>
		<td class = "LTblueBG"><%=tradeData[i][7]==null?"":tradeData[i][7]%></td>
		<td class = "LTblueBG"><%=tradeData[i][8]==null?"":tradeData[i][8]%></td>
		<td class = "LTblueBG"><%=tradeData[i][9]==null?"":tradeData[i][9]%></td>
		<td class = "LTblueBG"><%=tradeData[i][10]==null?"":tradeData[i][10]%></td>
		<td class = "LTblueBG"><%=tradeData[i][11]==null?"":tradeData[i][11]%></td>
		<td class = "LTblueBG"><%=tradeData[i][12]==null?"":tradeData[i][12]%></td>
		<td class = "LTblueBG"><%=tradeData[i][13]==null?"":tradeData[i][13]%></td>
		<td class = "LTblueBG"><%=tradeData[i][14]==null?"":tradeData[i][14]%></td>
		<td class = "LTblueBG"><%=tradeData[i][15]==null?"":tradeData[i][15]%></td>	
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][16]%></td>
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][17]%></td>
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][18]%></td>
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][19]%></td>
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][20]%></td>
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][21]%></td>
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][22]%></td>
		<td class = "LTblueBG"><%=tradeData[i][16]==null?"":tradeData[i][23]%></td>
		 
		 
	</tr>
	<%
		}
	%>

</table>
<p align="center">
<input type="image" src="<%=request.getContextPath() %>/images/Cancel.jpg" onclick="javascript:checkOther('10');">
</p>
<script>
function checkOther(hda)
{
	window.opener.focus();
	self.close();
}
</script>
</html>