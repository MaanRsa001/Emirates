<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../login/sessionsCheckNormal.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*,java.io.*,java.text.*" %>
<jsp:useBean id="RoyalAdmin" class="com.maan.admin.BrokerCreationBean">
<jsp:setProperty name="RoyalAdmin" property="out" value="<%= response.getWriter() %>" /> 
</jsp:useBean>
<jsp:useBean id="royalAdminBean" class="com.maan.admin.AdminBean">
</jsp:useBean>
	<head>
	    <link href="${pageContext.request.contextPath}/css/icon.css" rel="stylesheet" type="text/css">
	    <link href="${pageContext.request.contextPath}/css/demo.css" rel="stylesheet" type="text/css">
	    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css" />
	    <link href="${pageContext.request.contextPath}/css/tcal.css" rel="stylesheet" type="text/css" />
	    <link href="${pageContext.request.contextPath}/css/ddlevelsmenu-topbar.css" rel="stylesheet" type="text/css" />
	    <link href="${pageContext.request.contextPath}/css/ddlevelsmenu-base.css" rel="stylesheet" type="text/css" />
	    <link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tcal.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	    <script src="${pageContext.request.contextPath}/js/ddlevelsmenujsp.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/js/ddtabmenu.js" type="text/javascript"></script>
	
		<script type="text/javascript">
		ddlevelsmenu.setup("ddtopmenubar", "topbar")
		</script>
	</head>
<br/><br/>
<table width="90%"  border="0" cellspacing="0" cellpadding="0" align="center"> 
<s:url forceAddSchemeHostAndPort="true" includeParams="all" var="myurl" escapeAmp="false" encode="false">
		<s:param name="request_locale"/>
	</s:url>
	<tr >
		<td bgColor=#ffffff width="20" align="right"></td>
		<td><img src="${pageContext.request.contextPath}/images/logo.jpg" border="0" width="230" height="60"></td>
		<td bgColor=#ffffff width="400" align="right"><font color="red" style="font: bold;font-size: 30px;"><s:property value="#session.userLoginMode"/>&nbsp;&nbsp;Environment</font></td>
		<td bgColor=#ffffff width="300" align="right">
		<s:if test='#session.LANG == "Y"'>
			<a href="" onclick="setLang(this, '<s:property value="#myurl"/>', 'en')" >English</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="" onclick="setLang(this, '<s:property value="#myurl"/>', 'ar')" >Arabic</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</s:if>
		<s:if test="#session.user1==getText('admin')">
			<a href="${pageContext.request.contextPath}/homeAdmin.action" ><font style="color:black">Home</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;
		</s:if>
		<s:else>
			<a href="${pageContext.request.contextPath}/HomeUser.action" ><font style="color:black">Home</font></a>&nbsp;&nbsp;|&nbsp;&nbsp;
		</s:else>				
			<a href="${pageContext.request.contextPath}/Loginout.action"><font style="color:black">Sign Out</font></a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	 </tr>
	 <tr >
		 <td colspan="4" align="right">
		 	<font style="font-weight: bold;color:#000000"> LoginId&nbsp;:&nbsp;&nbsp;<s:property value="#session.user"/></font>&nbsp;&nbsp;
		</td>
	</tr>
</table>
 <table width="90%" align="center" border="0" cellspacing="0" cellpadding="0" bgColor="#4f6180">
            <tr> 
                <td>
                    <div id="ddtopmenubar" class="mattblackmenu">
                        <ul>
                            <s:iterator value="%{#session.MenuList}" var="menuList" status="stat">
                                <s:if test='%{PARENT_MENU==null && !"60".equals(CATEGORY_DETAIL_ID) && !"Parent".equals(DETAIL_NAME)}'>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/<s:property value="REMARKS"/>" /><s:property value="DETAIL_NAME"/></a>
                                    </li>
                                </s:if>
                                <s:elseif test='PARENT_MENU=="60"'>
                                    <li>
                                        <a rel="ddsubmenu<s:property value="CATEGORY_DETAIL_ID"/>" style="cursor:pointer"/><s:property value="DETAIL_NAME"/></a>
                                    </li>
                                </s:elseif>
                                <s:elseif test='%{PARENT_MENU!="60" && PARENT_MENU!=null && !"".equals(CATEGORY_DETAIL_ID)}'>
                                    <ul id="ddsubmenu<s:property value="PARENT_MENU"/>" class="ddsubmenustyle">
                                        <s:iterator value="%{#session.MenuList}" var="menuSubList">
                                            <s:if test="#menuSubList.PARENT_MENU == #menuList.PARENT_MENU">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/<s:property value="REMARKS"/>" /><s:property value="DETAIL_NAME"/></a>
                                                </li>
                                            </s:if>
                                        </s:iterator>
                                    </ul>
                                </s:elseif>
                            </s:iterator>
                            <%-- <li><a href="viewDeposit.action"/><s:text name="Deposit Master"/></a> </li> --%>
                        </ul>
                    </div>
                </td>
            </tr>
        </table>

<% String usertype123=(String)session.getAttribute("usertype");

/***Garbage***/

	int totalmemory = (int)Runtime.getRuntime().totalMemory();
    int freememory = (int)Runtime.getRuntime().freeMemory();
	int totalUsedBefore = totalmemory - freememory;
	int garbageMemory;
	garbageMemory = totalUsedBefore/(1024*1024);

	try 
	{   
		DateFormat fmt = new SimpleDateFormat("hh:mm:ss aa");
	    String currenttime = fmt.format(new java.util.Date());
		String time ="current time is "+currenttime;
		String pat = getServletContext().getRealPath("/");
		String nameOfTextFile = pat+"GarbageReport.txt";
		String str  = "Admin Garbage Collection Total Memory is "+(totalmemory/(1024*1024))+"MB \n";
		String str1 = str+"Garbage Collection Free Memory is "+(freememory/(1024*1024))+"MB \n";
		String str2 = str1+"Garbage Collection Total Used Memory is "+garbageMemory+"MB  "+time+"\n";
		String str3= " ";
		if( (totalmemory /(1024*1024))>60)
		{
			str3 = "Admin Garbage Collector Calling Here....Becoz Total Memory Is "+(totalmemory/(1024*1024))+"MB \n";
			System.gc();
		}
		str3 = str2 + str3;
		//System.out.println("File Path "+nameOfTextFile);
		PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfTextFile,true));
	    pw.println(str3);
        pw.close();
	}
	catch(IOException e) 
	{
	   out.println(e.getMessage());
	}

	/***Garbage***/

String adminloginPersonId=request.getParameter("user")==null?"":request.getParameter("user");
if(adminloginPersonId.equals(""))
{
	adminloginPersonId=(String)session.getAttribute("user");
}
session.setAttribute("loginPersonId",adminloginPersonId);
String bracnAdmins[][] = new String[0][0];
String countryAdmins = "";
String productsAdmins = "";
bracnAdmins = RoyalAdmin.getAdminBranch(adminloginPersonId);
countryAdmins = RoyalAdmin.getAdminCountry(adminloginPersonId);
productsAdmins = RoyalAdmin.getAdminProductId(adminloginPersonId);


String braName="";
String braCode="";
String actualBranch="";
if(bracnAdmins.length>0)
{
	if(bracnAdmins.length==1)
	{
		braName = bracnAdmins[0][1]!=null?bracnAdmins[0][1]:"";
		braCode = "'"+bracnAdmins[0][0]+"'";
		actualBranch = "'"+bracnAdmins[0][2]+"'";
		braName = braName + " Branch";
	}
	else
	{
		for(int b=0;b<bracnAdmins.length;b++)
		{
			braName = braName +bracnAdmins[b][1] + ", ";
			braCode = braCode+"'"+bracnAdmins[b][0]+"',";
		}
		braName = braName.substring(0,braName.length()-2);
		braCode = braCode.substring(0,braCode.length()-1);
		braName = braName + " Branches";
	}
}
//Royal World WOrk
java.util.HashMap brokerDetails = new java.util.HashMap();
String brokerBra = braCode;
if(brokerBra.indexOf("'")!=-1)
	brokerBra = brokerBra.replaceAll("'","");
if(actualBranch.indexOf("'")!=-1)
	actualBranch = actualBranch.replaceAll("'","");
brokerDetails = RoyalAdmin.getBrokerUserDetails(actualBranch);
session.setAttribute("LoginBranchCode",brokerBra);//This is defaulted Admin Branch code 01
session.setAttribute("BrokerDetails",brokerDetails);
//For Home
session.setAttribute("adminBranch",actualBranch);//This is actual admin Branch code means corresponding admin branch code
session.setAttribute("AdminBranchCode",braCode);//This is defaulted Admin Branch code 01
session.setAttribute("AdminCountryId",countryAdmins);
session.setAttribute("AdminPid",productsAdmins);
session.setAttribute("branchName",braName);
		
		String adminProductId="";
		adminProductId = (String)session.getAttribute("pro_Id");
		adminProductId = adminProductId == null ? "" : adminProductId;
		if(adminProductId.length()<=0)
		{
			adminProductId = royalAdminBean.getLoginProIds(adminloginPersonId); 
		}
		session.setAttribute("pro_Id",adminProductId);
%>

</script>
		