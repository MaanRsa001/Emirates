<%@ page import="java.util.*" %>
<jsp:useBean id="ct" class = "com.maan.opencover.ConditionsOpenCover">
<jsp:setProperty property="*" name="ct"/>
</jsp:useBean>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Calendar cal = Calendar.getInstance();
	java.util.Date today = cal.getTime();
	java.text.SimpleDateFormat fmt =new java.text.SimpleDateFormat("dd-MM-yyyy");
	String todayStr = fmt.format(today);
	//boolean emode=true;
%>

<html>
<script>
function controls() {
	document.clauses.action='<%=path%>/premiumOpenCover/warrantiesShow.jsp';
	document.clauses.submit();
}

function formSubmit() {
	if(window.opener.document.form1.chkProposalNo.value == document.clauses.chkProposalNo.value) {
		document.clauses.action="ConditionsController"
		document.clauses.submit();
	} else {
		alert("please close the warranties selection window and reopen again");
		window.close();
	}
}

</script>
<head>
<title>** Emirates - Marine Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
<style type="text/css">
            /*
#Layer1 {
	position:absolute;
	width:200px;
	height:115px;
	z-index:1;
	left: 128px;
	top: 284px;
}
         */
</style>
<script language="JavaScript" src="<%=path%>/css/calendar1.js"></script>
</head>
<body>
<%	String search=request.getParameter("search")==null?"":request.getParameter("search");
	String bgcolor="";
	String as="";
	int l=0;
	int matches=0;
%>
<form name="clauses" method="post" >
    <BR>   
     <CENTER>
	 
<%--<input type='text' name='search' value="<%=request.getParameter("search")==null?"":request.getParameter("search")%>"  >&nbsp;&nbsp;<input type='submit'  value='Search' onclick="controls()"  accesskey='s'>--%>

</CENTER>        
	<br>
	 <%
        String sessionId="";
        String companyId="1";
        String productId="0";
        String coverNo="0";
        String loginId="";
        
        String applicationNo="";
        String proposalNo="";
        
		String adminBranch = (String) session.getAttribute("AdminBranchCode");
		String belongingBranch = (String) session.getAttribute("BelongingBranch");
		if(adminBranch.indexOf("'")!=-1)
			adminBranch = adminBranch.replaceAll("'","");
		String cid = (String) session.getAttribute("AdminCountryId");
		String openCoverNo="";

		openCoverNo=(String) session.getAttribute("openCoverNo")==null?"0":(String) session.getAttribute("openCoverNo");


		productId=(String) session.getAttribute("product_id")==null?"0":(String) session.getAttribute("product_id");

        applicationNo=(String)session.getAttribute("applicationNo")==null?
        (request.getParameter("applicationNo")==null?"0":request.getParameter("applicationNo")):
        (String)session.getAttribute("applicationNo");
        

		String effectDate=request.getParameter("effectDate")==null?todayStr:request.getParameter("effectDate");
		
        String next=request.getParameter("next");
        
		sessionId=(String) session.getAttribute("ses")==null?"":(String) session.getAttribute("ses");

        loginId=(String) session.getAttribute("user")==null?"":(String) session.getAttribute("user");
       
		companyId=(String) session.getAttribute("company_id")==null?"":(String) session.getAttribute("company_id");

        productId=(String) session.getAttribute("product_id")==null?"":(String) session.getAttribute("product_id");

		proposalNo=request.getParameter("proposalNo")==null?(String)session.getAttribute("proposalNo"):request.getParameter("proposalNo");
		String co = request.getParameter("coverNo");
		String co1 = (String)session.getAttribute("coverNo");
		coverNo = request.getParameter("coverNo")==null?(String)session.getAttribute("coverNo"):request.getParameter("coverNo");
		
        ct.setSessionId(sessionId);
        ct.setLoginCode(loginId);
        ct.setCompanyId(companyId);
        ct.setProductId(productId);
		ct.setOpenCoverNo(openCoverNo);
		ct.setProposalNo(proposalNo);
		ct.setCoverId(coverNo);
		ct.setModeOfTransport("1");
        
        if(applicationNo.equalsIgnoreCase("0"))
        {
        
        }else
        {
        
        }
        
        boolean isErrorPage=false;
        StringBuffer error=new StringBuffer();
        if(request.getAttribute("errorMessageClauses") !=null)
        {
        
        error=(StringBuffer)request.getAttribute("errorMessageClauses");
        isErrorPage=true;
                
        }
        String mode = "";
       String option =request.getParameter("mode");
        boolean isEditMode=false;
        if("edit".equals(option))
          {  
             isEditMode=true;
             
           }
        
        
        HashMap conditionsList=new HashMap();
        HashMap conditionsIdsList=new HashMap();
        HashMap totalConditions=new HashMap();

        totalConditions=ct.getWarrantiesFromMaster(belongingBranch);

	conditionsList=(HashMap)totalConditions.get("conditionsList")==null?conditionsList:(HashMap)totalConditions.get("conditionsList");
	conditionsIdsList=(HashMap)totalConditions.get("conditionsIdsList")==null?conditionsIdsList:(HashMap)totalConditions.get("conditionsIdsList");
  
        HashMap selectedAddons=new HashMap();
        HashMap totalSelectedAddons=new HashMap();
        
        if(isErrorPage)
        {
		    if(request.getAttribute("fullDetails") !=null)
	        {
				selectedAddons=(HashMap)request.getAttribute("fullDetails");
	        }
        }
        else
        {
	        //if(session.getAttribute("fullDetailss") !=null)
	        //{
			selectedAddons=(HashMap)ct.getWarrantyFromOpenCoverMaster();
	    }    
        
        %>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<span class="heading">Warranties</span>
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
<table class="footable" width="100%">
	<thead>
	<tr>
		<th>
			<input type="hidden" name="clausesSize" value="<%=conditionsList.size()%>" />
            <input type="hidden" name="productId" value="<%=productId%>" />
            <input type="hidden" name="loginId" value="<%=loginId%>" />
            <input type="hidden" name="sessionId" value="<%=sessionId%>" />
            <input type="hidden" name="companyId" value="<%=companyId%>" />
            <input type="hidden" name="proposalNo" value="<%=proposalNo%>" />
            <input type="hidden" name="chkProposalNo" value="<%=proposalNo%>" />
            <input type="hidden" name="openCoverNo" value="<%=openCoverNo%>" />
            <input type ="hidden" name = "coverNo" value = "<%=coverNo%>"/>
            <input type="hidden" name="warrantyNoEDIT" value="1" />
		</th>
		<th>Warranties ID</th>
		<th>Warranties Description</th>
	</tr>
	</thead>
	<tbody>
	<tr>
<%
	String commName="";
	String frag_ES="";
	String dummyK="";
	int dummyInt=0;
	String dummyKName="";

	/*for(int kk=0;kk<conditionsIdsList.size();kk++) {
		dummyK=(String)conditionsIdsList.get(""+(kk))==null?"0":(String)conditionsIdsList.get(""+(kk));
	 	dummyKName=(String)conditionsList.get(""+(dummyK))==null?"0":(String)conditionsList.get(""+(dummyK));
	}*/
	if(conditionsList.size()>0) {
		for(int k=0;k<conditionsList.size();k++) {
			try{
				dummyK=(String)conditionsIdsList.get(""+(k))==null?"0":(String)conditionsIdsList.get(""+(k));
				dummyInt=Integer.parseInt(dummyK);
			} catch(Exception e) { e.printStackTrace();}
%>
	<input type="hidden" name="clausesIdOrg<%=(k+1)%>" value="<%=dummyInt%>">
<%	commName=(String)conditionsList.get(""+(dummyInt))==null?"No Clauses":(String)conditionsList.get(""+(dummyInt));					
	boolean flag	=	false;
	boolean falg = false;
	boolean fragileFlag	=	false;
	String commName_S="",desc_S="",checkStatus="unchecked";
	String selectedCount=(String)selectedAddons.get("finalCount")==null?"0":(String)selectedAddons.get("finalCount");
	try {
		effectDate=(String)selectedAddons.get("effectDate")==null?(((String)selectedAddons.get("effectDate")).equalsIgnoreCase("null")?todayStr:(String)selectedAddons.get("effectDate")):(String)selectedAddons.get("effectDate");
	}catch(Exception ex) {}
	if(Integer.parseInt(selectedCount)==0) {
		mode = "fresh";
	}			 
	if ((selectedAddons.size()>0)&& (!"fresh".equalsIgnoreCase(mode))) {
		for(int tt=0;tt<Integer.parseInt(selectedCount);tt++) {
			//commName_S=(String)selectedAddons.get("clauses"+(tt+1));
			commName_S=(String)selectedAddons.get("clausesId"+(tt+1));
			if(((dummyInt)==Integer.parseInt(commName_S))) {
				flag	=	true;
	            desc_S=(String)selectedAddons.get("description"+(tt+1));
			}
		}
	} %>
	<% 	if(flag) { %>
	<td><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" <%=isErrorPage?((request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""):"checked"%> value="<%= commName %>" ></td>
	<td align="center" style="font-weight: bold;"><%=(String)conditionsIdsList.get(""+(k))%></td>
	<td><textarea name="description<%=(k+1)%>" rows="4" cols="60" style="width: 100%" id="description<%=(k+1)%>" onkeyup="textLimit(this,2000)"><%=isErrorPage?(request.getParameter("description"+(k+1))==null?desc_S:request.getParameter("description"+(k+1))):desc_S%></textarea></td>
	</tr>
	<%	}else if(!"fresh".equalsIgnoreCase(mode)) {
			String event="";
	%>
	<tr>
	<td><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" value="<%= commName %>" <%=(request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""%> ></td>
	<td align="center" style="font-weight: bold;"><%=(String)conditionsIdsList.get(""+(k))%></td>
	<td><textarea name="description<%=(k+1)%>" style="width: 100%;" rows="4" cols="60" id="description<%=k%>" onkeyup="textLimit(this,2000)"><%=(request.getParameter("description"+(k+1))==null?commName:request.getParameter("description"+(k+1)))%></textarea></td>
	</tr>
	<%	} else if("fresh".equalsIgnoreCase(mode)) {	%>
	<tr>
	<td><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" value="<%= commName %>" <%=(request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""%> ></td>
	<td align="center" style="font-weight: bold;"><%=(String)conditionsIdsList.get(""+(k))%></td>
	<td><textarea name="description<%=(k+1)%>" style="width: 100%" rows="4" cols="60" id="description<%=k%>" onkeyup="textLimit(this,2000)"><%=(request.getParameter("description"+(k+1))==null?commName:request.getParameter("description"+(k+1)))%></textarea></td>
	</tr>
	<%}%>
	<%}//ENd of FOR %>
	</tbody>
</table>
<div align="right">
<input type="hidden" name="effectDate" value="<%=(effectDate==""?todayStr:effectDate	)%>" />
<!-- 
Effective Date<input readOnly value="<%=(effectDate==null?todayStr:todayStr)%>" type="text" class="fde1"name="effectDate" size="20">
<a href="javascript:cal6.popup();"><img alt="Click Here Pick up the date" src="../images/cal.gif" border="0" width="16" height="16"></a><br>  -->
</div>
<% } %>
<div align="center" style="margin-top: 10px;">
	<a href="javascript:window.close()"  class="buttonsMenu" ><img src="../images/Back.jpg" > </a> &nbsp;&nbsp;&nbsp;
	<a href="#" onClick="formSubmit()" class="buttonsMenu" > <img src="../images/Submit.jpg"></a> 
</div>
          
</form>
</body>
<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Character is Exceed Maximum Length!'+maxlen);
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
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