<%@ page import="java.util.*" %>
<%@ include file="home1.jsp" %>
<jsp:useBean id="ct" class = "com.maan.opencover.ConditionsOpenCover">
	<jsp:setProperty property="*" name="ct"/>
</jsp:useBean>
<%
	String reqFrom=request.getParameter("reqFrom")==null?"":request.getParameter("reqFrom");
	String pathq = request.getContextPath();
	String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
	boolean flag	=	false;
	int tt=0, tt1=0;
	boolean fragileFlag	=	false;
	String commName_S="",desc_S="",checkStatus="unchecked";
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int k=0;
	Calendar cal = Calendar.getInstance();
	java.util.Date today = cal.getTime();
	java.text.SimpleDateFormat fmt =new java.text.SimpleDateFormat("dd-MM-yyyy");
	String todayStr = fmt.format(today);
%>
<html>
<head>
<title>** Emirates - Marine Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
<script>
function controls() {
	document.clauses.action= '<%=path%>/premiumOpenCover/freetext.jsp';
	document.clauses.submit();
}

function formSubmit() {
	if(window.opener.document.form1.chkProposalNo.value == document.clauses.chkProposalNo.value) {
		document.clauses.action="ConditionsController"
		document.clauses.submit();
	} else {
		alert("please close the exclusion selection window and reopen again");
		window.close();
	}
}
</script>        
<script language="JavaScript" src="<%=path%>/css/calendar1.js"></script>
</head>
<body>
<%	String search=request.getParameter("search")==null?"":request.getParameter("search");
	String bgcolor="";
	String as="";
	int l=0;
	int rowcount = 30;
	int matches=0;
%>
<form name="clauses" method="post" >

<%	String sessionId="";
	String companyId="1";
	String productId="0";
	String coverNo="0";
	String loginId="";
	String applicationNo="";
	String proposalNo="";
	String openCoverNo="";
	openCoverNo=(String) session.getAttribute("openCoverNo")==null?"0":(String) session.getAttribute("openCoverNo");
	productId=(String) session.getAttribute("product_id")==null?"0":(String) session.getAttribute("product_id");
	applicationNo=(String)session.getAttribute("applicationNo")==null?(request.getParameter("applicationNo")==null?"0":request.getParameter("applicationNo")):(String)session.getAttribute("applicationNo");
	String effectDate=request.getParameter("effectDate")==null?"":request.getParameter("effectDate");
	String next=request.getParameter("next");
    //String action="Premium.jsp";
    // sessionId=(String) session.getAttribute("ses");
    // loginId=(String) session.getAttribute("user");
	sessionId=(String) session.getAttribute("ses")==null?"":(String) session.getAttribute("ses");
    loginId=(String) session.getAttribute("user")==null?"":(String) session.getAttribute("user");
	companyId=(String) session.getAttribute("company_id")==null?"":(String) session.getAttribute("company_id");
    productId=(String) session.getAttribute("product_id")==null?"":(String) session.getAttribute("product_id");
	proposalNo=request.getParameter("proposalNo")==null?(String)session.getAttribute("proposalNo"):request.getParameter("proposalNo");
	String co = request.getParameter("coverNo");
	String co1 = (String)session.getAttribute("coverNo");
	coverNo = request.getParameter("coverNo")==null?(String)session.getAttribute("coverNo"):request.getParameter("coverNo");
	try	{
		coverNo=""+Integer.parseInt(coverNo);
	} catch(Exception e) {
		coverNo="0";
	}

	ct.setSessionId(sessionId);
	ct.setLoginCode(loginId);
	ct.setCompanyId(companyId);
	ct.setProductId(productId);
	ct.setOpenCoverNo(openCoverNo);
	ct.setProposalNo(proposalNo);
	ct.setCoverId(coverNo);
	//ct.setModeOfTransport("1");

	boolean isErrorPage=false;
	StringBuffer error=new StringBuffer();
	if(request.getAttribute("errorMessageClauses") !=null) {
		error=(StringBuffer)request.getAttribute("errorMessageClauses");
		isErrorPage=true;
	}
    String option =request.getParameter("mode");
    boolean isEditMode=false;
    if("edit".equals(option)) {
		isEditMode=true;
	}

	HashMap conditionsList=new HashMap();
	HashMap conditionsIdsList=new HashMap();
	HashMap totalConditions=null;
	totalConditions = new HashMap();
	totalConditions=ct.getOpencoverFreeText();
	conditionsList=(HashMap)totalConditions.get("conditionsList")==null?conditionsList:(HashMap)totalConditions.get("conditionsList");
	conditionsIdsList=(HashMap)totalConditions.get("conditionsIdsList")==null?conditionsIdsList:(HashMap)totalConditions.get("conditionsIdsList");
	
    HashMap selectedAddons=new HashMap();
    HashMap totalSelectedAddons=new HashMap();

    if(isErrorPage) {
		if(request.getAttribute("fullDetails") !=null) {
			selectedAddons=(HashMap)request.getAttribute("fullDetails");
        }
	} else {
        //if(session.getAttribute("fullDetailss") !=null)
        //{
		selectedAddons=(HashMap)ct.getOpencoverFreeText();
       // }
		//else
    }
%>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<span class="heading">Free Text Entries</span>
		</td>
	</tr>
</table>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<%=error.length()>0?error.toString():"&nbsp;"%>
		</td>
	</tr>
</table>
<input type="hidden" name="clausesSize" value="30" >
<input type="hidden" name="productId" value="<%=productId%>" />
<input type="hidden" name="loginId" value="<%=loginId%>" />
<input type="hidden" name="sessionId" value="<%=sessionId%>" />
<input type="hidden" name="companyId" value="<%=companyId%>" />
<input type="hidden" name="proposalNo" value="<%=proposalNo%>" />
<input type="hidden" name="chkProposalNo" value="<%=proposalNo%>" />
<input type="hidden" name="openCoverNo" value="<%=openCoverNo%>" />
<input type ="hidden" name="coverNo" value = "<%=coverNo%>"/>
<input type="hidden" name="textNoEDIT" value="9" />
<table class="footable" width="100%">
	<tbody>
	<%
		String commName="";
		String frag_ES="";
		String dummyK="";
		int dummyInt=0;
		String dummyKName="";
		if(selectedAddons.size()<=0) {
		commName=(String)conditionsList.get(""+(dummyInt))==null?"No Clauses":(String)conditionsList.get(""+(dummyInt));
		for(k=0;k<rowcount;k++) {
	%>
	<tr>
		<td><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" <%=(request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""%>> </td>
		<td><textarea name="description<%=(k+1)%>" rows="4" cols="60" onkeyup="textLimit(this,4000)" id="description<%=k%>"><%=(request.getParameter("description"+(k+1))==null?commName:request.getParameter("description"+(k+1)))%></textarea>
			<input type="hidden" name="clausesIdOrg<%=(k+1)%>" value="<%=(k+1)%>">
		</td>
	</tr>
	<% } %>
	</tbody>
</table>
<br>
<div align="right">
<input type="hidden" name="effectDate" value="<%=(effectDate==""?todayStr:effectDate	)%>" />
<!-- 
Effective Date<input type="text" class="fde1"name="effectDate" value="<%=(effectDate==""?todayStr:effectDate)				%>" Readonly>
<a href="javascript:cal6.popup();">
<img src="../images/cal.gif" width="16" height="16" border="0" alt="Click Here Pick up the date"></a>-->
</div>
<div align="center" style="margin-top: 10px;">
	<a href='javascript:history.go(-1)'><img src="<%=pathq%>/images/Back.jpg"></a>
	&nbsp;&nbsp;&nbsp;
	<a href= "#" onClick='return reDirectMe()' ><img src="<%=pathq%>/images/Proceed.jpg"></a>
</div>
<table class="footable" width="100%">	
<%	
		}
		if(selectedAddons.size()>0) {
			//for(k=0;k<rowcount;k++)
			//{
			dummyK=(String)conditionsIdsList.get(""+(k))==null?"0":(String)conditionsIdsList.get(""+(k));
			dummyInt=Integer.parseInt(dummyK);
			commName=(String)conditionsList.get(""+(dummyInt))==null?"No Clauses":(String)conditionsList.get(""+(dummyInt));
			String selectedCount=(String)selectedAddons.get("finalCount")==null?"0":(String)selectedAddons.get("finalCount");
	 		try {
				effectDate=(String)selectedAddons.get("effectDate")==null?(((String)selectedAddons.get("effectDate")).equalsIgnoreCase("null")?"":(String)selectedAddons.get("effectDate")):(String)selectedAddons.get("effectDate");
			} catch(Exception ex) {	}
			if(selectedAddons.size()>0) {
	    		int v = rowcount-Integer.parseInt(selectedCount);		
				if(v==0) {
					v = 30;
				}
				if(v>0) {
		 			for( tt1=1; tt1<=30; tt1++) {
		  	   			commName_S=(String)selectedAddons.get("clausesId"+(tt1));
			   			desc_S=(String)selectedAddons.get("description"+(tt1));
%>
	<tr>
		<td><%=tt1%>&nbsp;<input type="checkbox" name="clauses<%=(tt1)%>" value="<%=commName_S%>"<%=(request.getParameter("clauses"+(tt1))==null?(commName_S==null?"":commName_S):request.getParameter("clauses"+(tt1))).equalsIgnoreCase(commName_S)?"checked":""%>> 
			<input type="hidden" name="clausesIdOrg<%=(tt1)%>" value="<%=(tt1)%>">
		</td>			  				  
		<td>
			<textarea name="description<%=(tt1)%>" rows="4" cols="60" onkeyup="textLimit(this,4000)" id="description<%=tt1%>"><%=(request.getParameter("description"+(tt1))==null?((desc_S==null)?"":desc_S):request.getParameter("description"+(tt1)))%></textarea>
		</td>
	</tr>
 	<%  			}
 				}
  			}
			if(flag) {
%>
	<tr>
		<td><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" <%=isErrorPage?((request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""):"checked"%> value="<%= commName %>" ></td>
		<td><textarea name="description<%=(k+1)%>" rows="4" cols="60" onkeyup="textLimit(this,4000)" id="description<%=(k+1)%>"><%=isErrorPage?(request.getParameter("description"+(k+1))==null?desc_S:request.getParameter("description"+(k+1))):desc_S%></textarea></td>
    </tr>    
<%
	} else {
		String event="";
		bgcolor="";
	}
%>
</table>
<br/>
<div align="right">
<input type="hidden" value="<%=effectDate%>" name="effectDate">
<!-- 
Effective Date &nbsp;&nbsp;
<input type="text" class="fde1"name="effectDate" value="<%=effectDate%>" Readonly>
<a href="javascript:cal6.popup();">
<img src="../images/cal.gif" width="16" height="16" border="0" title="Click Here Pick up the date"></a>  -->
</div>
<div align="center" style="margin-top: 10px;">
	<a href='javascript:history.go(-1)' ><img src="<%=pathq%>/images/Back.jpg"></a>
	&nbsp;&nbsp;&nbsp;
	<a href= "#" onClick='return reDirectMe()' ><img src="<%=pathq%>/images/Proceed.jpg" ></a>
</div>
<br>
<%}%>
</form>
</body>
<script>

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Character is Exceed Maximum Length!'+maxlen);
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
} 

function reDirectMe()
{
	if(window.opener.document.form1.chkProposalNo.value == document.clauses.chkProposalNo.value)
	{
		document.clauses.action="ConditionsController"
		document.clauses.submit()
	}
	else
	{
		alert("please close the free text entry window and reopen again");
	}
	return false;
}

var elem = document.getElementById("sumInsured<%=as%>");
if(elem!=null)
{
	elem.focus();
	elem.select();
}
var cal6 = new calendar1(document.forms['clauses'].elements['effectDate']);
cal6.year_scroll = true;
cal6.time_comp = false;

</script>
</html>
