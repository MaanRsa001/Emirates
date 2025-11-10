<%@ include file="/login/sessionsCheckNormal.jsp" %>
<%@ page import = "java.io.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>

<jsp:useBean id="clause" class="com.maan.admin.ratingAdmin">
<jsp:setProperty name="clause" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>

<%
	DateFunction dt=new DateFunction();
	String branch = (String)session.getAttribute("BranchCode");
%>

<html>
<head>
<title align="center">Emirates</title>
<h2 align="center" height="25" class = "DKblueBG"><font size="4"><font></h2>
</head>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<body>
<form name="group_view" method="post" action="">

<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center">
	
<%
String WARRANTY_ID1234=request.getParameter("ida")==null?"":request.getParameter("ida");
////out.println("WARRANTY_ID1234:"+WARRANTY_ID1234);
String from123=request.getParameter("from")==null?"":request.getParameter("from");
////out.println("from123:"+from123);
String val=request.getParameter("val")==null?"":request.getParameter("val").trim();
String[][] Warranties=new String[0][0];

String[] war12=new String[0];
String sd=val;

if(sd.length()>0)
			{
				int i=0;
				java.util.StringTokenizer token = new java.util.StringTokenizer(sd,",");
			war12=new String[token.countTokens()];
		//	out.println("<br>countmmmmmmmmm>>>>>>>>>>>>>>>"+token.countTokens());
				while(token.hasMoreTokens())
				{
				
                war12[i]=token.nextToken();
						i++;
				}
			}

//out.println("sdfsdfsdfsdfsdfs"+WARRANTY_ID1234);
String head="";
if(from123.equalsIgnoreCase("cov"))
{
Warranties=clause.getCoverageRef(branch);
head="COVERAGE REFERAL";
}

%>
<tr class = "DKblueBG">
<td align="center" colspan="2"><b>
<font color="white"><%=head%></font>
</b>
</td>
</tr>
<%
//out.println("sdfsdfsd"+from123);
	/*String[] war12=new String[0];
String sd=WARRANTY_ID1234;
if(sd.length()>0)
			{
				int i=0;
				java.util.StringTokenizer token = new java.util.StringTokenizer(sd,",");
			war12=new String[token.countTokens()];
		//	out.println("<br>countmmmmmmmmm>>>>>>>>>>>>>>>"+token.countTokens());
				while(token.hasMoreTokens())
				{
				
                war12[i]=token.nextToken();
						i++;
				}
			}
*/

//out.println("length of  data is"+tradeData.length);
int length123=Warranties.length;
for(int i=0;i<Warranties.length;i++)
	{
//String war=WARRANTY_ID.substring(0,WARRANTY_ID.indexOf(","));
	//out.println("sdfsdfsdfsdfsdf>>>>>>"+war);
	String check="";
for(int j=0;j<war12.length;j++)
		{
	if(war12[j].equalsIgnoreCase(Warranties[i][0]))
			{
check="checked";
break;
			}
			else
			{
check="";

			}
		}

	%>
	<tr><td width="50"><input type="checkbox" name="warranties<%=(i+1)%>" value="<%=Warranties[i][0].trim()%>" 
<%=check%>> </td><td class = "LTblueBG"> (<%=Warranties[i][0] %>) &nbsp;&nbsp; <%=Warranties[i][1]%></td></tr>
<%
	}	
	%>



</table>
<p align="center">
<input type="image" src="<%=request.getContextPath() %>/images/Proceed.jpg"   name="Image4" id="Image4" border="0" 
onclick="javascript:checkOther('<%=length123%>','<%=from123%>');">
<input type="image" src="<%=request.getContextPath() %>/images/Cancel.jpg"   name="Image4" id="Image4" border="0" 
onclick="javascript:close123();">
</p>
<script>
function checkOther(length123,from123)
{
//alert("sdfsdfsdfsd"+from123);
var from=from123;
var str="";
for(var i=0;i<length123;i++)
	{

	//alert("jkljkljkljk"+eval("document.group_view.warranties"+(i+1)+".checked"))
		if(eval("document.group_view.warranties"+(i+1)+".checked"))
		str=str+","+eval("document.group_view.warranties"+(i+1)+".value")
	//alert("sdfsdfsdfsdfsdfsdf" +str);

	
	}
	//window.opener.form1.SP_WARRANTIES_CONDITIONS1.value=str;
	
	//alert("sdfsdfsdfsdfsd>>>>>>>>>>>>>>>>>>>>>..."+from);
	//alert("sdfsdfsdfsdfsd>>>>>>>>>>>>>>>>>>>>>..."+str.substring(1,str.length));

if(from=="sp")
	{
	
window.opener.info.SP_WARRANTIES_CONDITIONS1.value=str.substring(1,str.length);
//alert(window.opener.form1.SP_WARRANTIES_CONDITIONS1.value);
	}
else
	if(from=="ep")
	{
	
window.opener.info.EP_WARRANTIES_CONDITIONS1.value=str.substring(1,str.length);
//alert(window.opener.form1.EP_WARRANTIES_CONDITIONS1.value);
	}
	else
		if(from=="comm")
	{
//alert(window.opener.form1.WARRANTY_ID1.value);
window.opener.info.WARRANTY_ID.value=str.substring(1,str.length);
//alert(window.opener.form1.WARRANTY_ID.value);
	}
else
	{
window.opener.info.coverage_Referal.value=str.substring(1,str.length);
//alert(window.opener.form1.EXCLUSION_ID.value);
	}
//alert("sdfsdfsdfsdfsdfsdf" +document.group_view.warranties1.value);
window.opener.focus();
self.close();
}




function close123()
{

window.opener.focus();
self.close();
}
</script>