<%@ page import = "java.io.*, java.util.Date,java.util.StringTokenizer, java.text.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE HTML>
<%@ include file="../admin/header.jsp" %>
<html>
<% String path=request.getContextPath();%>
<head>
<sj:head jqueryui="true" jquerytheme="start" />
<title>Emirates</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="../css/calendar1.js"></script>
        <style type="text/css">
            <!--
			.style1 {color: #FF0000}
            -->
        </style>
<jsp:useBean id="com" class="com.maan.admin.DAO.ratingAdmin">
<jsp:setProperty name="com" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>
<style type="text/css">
	.inputBox {
		width: 50%;
	}
	
	.inputAppend {
		width: 51%;
	}

	.inputSelect {
		width: 52%;
	}
</style>
</head>
<body >
<%  boolean isEditable=false,disp=true,readOnly=false;%>
<form name="form1" method="post"  action ="../admin/AdminController">
<%@ include file="/admin/left.jsp" %>
<%
 

try{
 String sdate="";
   String s;
  Format formatter;
 
  Date date = new Date();  // 01/09/02
  formatter = new SimpleDateFormat("MM/dd/yyyy");
  s = formatter.format(date);
  StringTokenizer st=new StringTokenizer(s,"/");
  while(st.hasMoreTokens())
  {
     String mm=st.nextToken();
     String dd=st.nextToken();
     String yy=st.nextToken();  
     sdate=dd+"-"+mm+"-"+yy;
  }


String branch = (String)session.getAttribute("LoginBranchCode");		
String startDate =sdate;
String endDate =sdate;
String error = request.getAttribute("error")==null?"":(String)request.getAttribute("error");
///out.println("error:"+error);	
	  String PRstartdate="";
	  String PRenddate="";
	  String PRproduct="";
	if(error.length()>0)
		{
		    PRstartdate=request.getParameter("startDate") == null ? "" : request.getParameter("startDate");
			PRenddate=request.getParameter("endDate") == null ? "" : request.getParameter("endDate");
			PRproduct=request.getParameter("product") == null ? "" : request.getParameter("product");
		}
		
	
	%>  
<table width="90%" style="margin: 0 auto;">
	<tr>
		<td><div class="heading"><strong>OpenCover Policy Integration Report</strong></div></td>
	</tr>
	<tr height="5"><td></td></tr>
	<tr>
		<td><font color="red" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=request.getAttribute("errorDetail")!=null?(String)request.getAttribute("errorDetail"):"&nbsp;"%></font></td>
	</tr>
	<tr height="5"><td></td></tr>
	<tr>
		<td><font color="red"><%=error%></font></td>
	</tr>
	<tr height="5"><td></td></tr>
	<tr>
		<td>
			<table width="75%" style="margin: 0 auto;">
				<tr>
					<td width="50%" align="right"><span style="color: #000000;">Start Date:</span></td>
					<td width="50%"><div class="inputAppend"><sj:datepicker id="startDate" name="startDate" cssClass="inputBox1" displayFormat="dd-mm-yy" changeMonth="true" changeYear="true" readonly="true" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div></td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td align="right"><span style="color: #000000;">End Date:</span></td>
					<td><div class="inputAppend"><sj:datepicker id="endDate" name="endDate" cssClass="inputBox1" displayFormat="dd-mm-yy" changeMonth="true" changeYear="true" readonly="true" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div></td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td align="right"><span style="color: #000000;">Product:</span></td>
					<td>
						<select name="product" class="inputSelect" >
							<option value=''>Select</option>
							<%
								String selected ="";
								String ProductDetail[][] = new String[0][0];
								ProductDetail = com.getProducts();
								for(int i=0;i<ProductDetail.length;i++)
								{
							%>								
							<option value=<%=ProductDetail[i][0]%>><%=ProductDetail[i][1]%></option>
							<%
								}
							%>
						</select>
					</td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td colspan="2" align="center">
						<input name="image" type="image"  src='<%=path%>/images/Proceed.jpg' / > &nbsp;&nbsp;&nbsp;
						<a href="../admin/ExportTables.jsp"><img src='<%=path%>/images/Back.jpg'/></a> 
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>
<input type="hidden" name="requestfrom" value="policyreport">
</form>
<script>

</script>
</body>
</html>
<%
}catch(Exception e)
{
out.println(e.getMessage());
}
%>