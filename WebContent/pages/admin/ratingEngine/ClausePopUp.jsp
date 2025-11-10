<%@ page isELIgnored="false" %>
<%@ page import = "java.io.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>

<jsp:useBean id="clause" class="com.maan.admin.ratingAdmin">
<jsp:setProperty name="clause" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>

<%
	DateFunction dt=new DateFunction();
	String branch = (String)session.getAttribute("LoginBranchCode");
%>

<html>
<head>
<title align="center">Emriates</title>
<h2 align="center" height="25" class = "DKblueBG"><font size="4"><font></h2>
</head>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<body>
<form name="group_view" method="post" action="">

<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center">
	
<%
String clausevalue=request.getParameter("ida")==null?"":request.getParameter("ida");
String clauseid=request.getParameter("from")==null?"":request.getParameter("from");
String id=request.getParameter("id")==null?"":request.getParameter("id");
 String val=request.getParameter("val")==null?"":request.getParameter("val").trim();
 String namee=request.getParameter("name")==null?"":request.getParameter("name");
String[][] Clause=new String[0][0];
String first="";
String second="";

//out.println("sdfsdfsdfsdfsdfs"+WARRANTY_ID1234);
String head="";
if(clausevalue.equalsIgnoreCase("C"))
{
Clause=clause.getClause(branch,clauseid,clausevalue);
head="CLAUSES";
}
else if(clausevalue.equalsIgnoreCase("E"))
{
Clause=clause.getClause(branch,clauseid,clausevalue);
head="EXCLUSION";
}
else 
{
Clause=clause.getClause(branch,clauseid,clausevalue);
head="WARRANTY";
}
if(clausevalue.equalsIgnoreCase("R"))
{
Clause=clause.getClause(branch,clauseid,clausevalue);
head="WAR COVER";
}
%>
<tr class = "DKblueBG">
<td align="center" colspan="2"><b>
<%=head%>
</b>
</td>
</tr>
<%
//out.println("sdfsdfsd"+from123);
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


//out.println("length of  data is"+tradeData.length);
int length123=Clause.length;
for(int i=0;i<Clause.length;i++)
	{
//String war=WARRANTY_ID.substring(0,WARRANTY_ID.indexOf(","));
	//out.println("sdfsdfsdfsdfsdf>>>>>>"+war);
	String check="";
for(int j=0;j<war12.length;j++)
		{
	if(war12[j].equalsIgnoreCase(Clause[i][0]))
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
	<tr><td width="50"><input type="checkbox" name="Clause<%=(i+1)%>" value="<%=Clause[i][0].trim()%>" 
<%=check%>> </td><td class = "LTblueBG"> (<%=Clause[i][0] %>) &nbsp;&nbsp; <%=Clause[i][1]%></td></tr>
<%
	}	
	%>

<input type="hidden" name="oldvalue"  id="oldvalue" value=""/>


</table>
<p align="center">

<input type="image" src="${pageContext.request.contextPath}/images/Proceed.jpg"   name="Image4" id="Image4" border="0" 
onclick="javascript:checkOther('<%=length123%>','<%=clausevalue%>','<%=id%>','<%=namee%>');">
<input type="image" src="${pageContext.request.contextPath}/images/Cancel.jpg"   name="Image4" id="Image4" border="0" 
onclick="javascript:close123();">
</p>
<script>
var calvalue="";
String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, "");
};

function checkOther(length123,clausevalue,id,namee)
{


var from=clausevalue;
var str="";
var h=0;
///alert("from:"+from);
for(var i=0;i<length123;i++)
	{
		if(eval("document.group_view.Clause"+(i+1)+".checked"))
		{
		h++;
		str=str+","+eval("document.group_view.Clause"+(i+1)+".value")
		}
	}
  var ids=id.trim();
	if(from=="C")
	{
     calvalue=str;
     if(h>0)
     {
         window.opener.info.elements[namee].value=calvalue.substring(1,calvalue.length);
     }
     else{
     alert("atleast select one clause");
     return false;
     }
	}
	
	if(from=="E")
	{
	calvalue=str;
	window.opener.info.elements[namee].value=calvalue.substring(1,calvalue.length);
	
	}
	
		 if(from=="W")
	{
	calvalue=str;
	window.opener.info.elements[namee].value=calvalue.substring(1,calvalue.length);
	}
		 if(from=="R")
	{
	///alert("namee:"+namee);
	calvalue=str;
	window.opener.info.elements[namee].value=calvalue.substring(1,calvalue.length);
	}		
		window.opener.focus();
self.close();
}




function close123()
{

window.opener.focus();
self.close();
}
</script>