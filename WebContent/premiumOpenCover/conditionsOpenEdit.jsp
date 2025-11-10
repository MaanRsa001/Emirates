

<%@ page import="java.util.*" %>
<%//@ page import="java.util.Hashtable" %>
<%@ page import = "java.text.SimpleDateFormat"%>
<%@ page import = "java.util.Date"%>

<jsp:useBean id="ct" class = "com.maan.opencover.ConditionsOpenCover">
<jsp:setProperty property="*" name="ct"/>
</jsp:useBean>

<%
 String path=request.getContextPath();
 String mode = "";
Calendar cal = Calendar.getInstance();
java.util.Date today = cal.getTime();
java.text.SimpleDateFormat fmt =new java.text.SimpleDateFormat("dd-MM-yyyy");
String todayStr = fmt.format(today);
%>
<html>
<script>
function controls()
{
	document.clauses.action='conditionsOpenEdit.jsp';
	document.clauses.submit();
}

function formSubmit()
{
	if(window.opener.document.form1.chkProposalNo.value == document.clauses.chkProposalNo.value)
	{
		document.clauses.action="ConditionsController"
		document.clauses.submit();
	}
	else
	{
		alert("please close the clauses selection window and reopen again");
		window.close();
	}
}

</script>
<head>
	<title>** Emirates - Marine Insurance</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath() %>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="<%=path%>/css/calendar1.js"></script>
		
</head>
    <body>
   		<%
		 String search=request.getParameter("search")==null?"":request.getParameter("search");
	 String bgcolor="";
			String as="";
		int l=0;
	 int matches=0;
	 %>
     <form name="clauses" method="post"  >
     <div style="width: 90%; margin: 0 auto;">      
     
	 
<%--<input type='text' name='search' value="<%=request.getParameter("search")==null?"":request.getParameter("search")%>"  >&nbsp;&nbsp;<input type='submit'  value='Search' onclick="controls()"  accesskey='s'>--%>

	 <%
	
	    String sessionId="";
        String companyId="1";
        String productId="0";
        String coverNo="0";
        String loginId="";
        int tt=0;
        String applicationNo="";
        String proposalNo="";
        
		String adminBranch = (String) session.getAttribute("AdminBranchCode");
		//BelongingBranch
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
		String cvrName = request.getParameter("cvrName");
		String co1 = (String)session.getAttribute("coverNo");
		coverNo = request.getParameter("coverNo")==null?(String)session.getAttribute("coverNo"):request.getParameter("coverNo");
		
        ct.setSessionId(sessionId);
        ct.setLoginCode(loginId);
        ct.setCompanyId(companyId);
        ct.setProductId(productId);
		ct.setOpenCoverNo(openCoverNo);
		ct.setProposalNo(proposalNo);
		session.setAttribute("coverNo", coverNo);
		ct.setCoverId(coverNo);
		ct.setModeOfTransport("1");

		if(applicationNo.equalsIgnoreCase("0"))
        {
        
        }
		else
        {
        
        }
        
        boolean isErrorPage=false;
        StringBuffer error=new StringBuffer();
        if(request.getAttribute("errorMessageClauses") !=null)
        {
            error=(StringBuffer)request.getAttribute("errorMessageClauses");
			isErrorPage=true;
        }
        
       String option =request.getParameter("mode");
	  
        boolean isEditMode=false;
        if("edit".equals(option))
         {  
            isEditMode=true;
         }
       
		HashMap conditionsList=new HashMap();
        HashMap conditionsIdsList=new HashMap();
        HashMap totalConditions=new HashMap();
        HashMap conditionIds = new HashMap();

        totalConditions=ct.getConditionsFromMaster(belongingBranch);
	conditionsList=(HashMap)totalConditions.get("conditionsList")==null?conditionsList:(HashMap)totalConditions.get("conditionsList");
	conditionsIdsList=(HashMap)totalConditions.get("conditionsIdsList")==null?conditionsIdsList:(HashMap)totalConditions.get("conditionsIdsList");
	conditionIds = (HashMap)totalConditions.get("clausesId");
			
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
			selectedAddons=(HashMap)ct.getConditionsFromOpenCoverMaster();
	    }    
     %>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class="heading">Clauses &nbsp;&nbsp;<%=cvrName%></span></td>
	</tr>
	<tr>
		<td><%=error.length()>0?error.toString():"&nbsp;"%></td>
	</tr>
	<tr>
		<td>
			<table width="100%" class="footable">
				<thead>
				<tr>
					<th width="5%"></th>
					<th width="20%">Clauses&nbsp;ID</th>
					<th width="75%">Clauses Description
						<input type="hidden" name="clausesSize" value="<%=conditionsList.size()%>" />
                            <input type="hidden" name="productId" value="<%=productId%>" />
                            <input type="hidden" name="loginId" value="<%=loginId%>" />
                            <input type="hidden" name="sessionId" value="<%=sessionId%>" />
                            <input type="hidden" name="companyId" value="<%=companyId%>" />
                            <input type="hidden" name="proposalNo" value="<%=proposalNo%>" />
                            <input type="hidden" name="chkProposalNo" value="<%=proposalNo%>" />
                            <input type="hidden" name="openCoverNo" value="<%=openCoverNo%>" />
                            <input type="hidden" name="coverNo" value = "<%=coverNo%>"/>
                            <input type="hidden" name="conditionOpen" value = "3"/>
                            <input type="hidden" name="applicationNoEDIT" value="<%=applicationNo%>" />
                            <input type="hidden" name="cvrName" value="<%=cvrName%>" />
					</th>
				</tr>
				</thead>
				<tbody>
				<%
			 String commName="";
			 String frag_ES="";
			 String dummyK="";
			 int dummyInt=0;
			 String dummyKName="";

			if(conditionsList.size()>0)
			{
				for(int k=0;k<conditionsList.size();k++)
				{
					try
					{
						dummyK=(String)conditionsIdsList.get(""+(k))==null?"0":(String)conditionsIdsList.get(""+(k));
						dummyInt=Integer.parseInt(dummyK);
					}catch(Exception e){System.out.println("Exception is 225.."+e);e.printStackTrace();}
			
				%>
				<input type="hidden" name="clausesIdOrg<%=(k+1)%>" value="<%=dummyInt%>">
				<%
				commName=(String)conditionsList.get(""+(dummyInt))==null?"No Clauses":(String)conditionsList.get(""+(dummyInt));

				boolean flag	=	false;
				boolean fragileFlag	=	false;
				String commName_S="",desc_S="",checkStatus="unchecked";
				String selectedCount=(String)selectedAddons.get("finalCount")==null?"0":(String)selectedAddons.get("finalCount");
				try
				{
					effectDate=(String)selectedAddons.get("effectDate")==null?(((String)selectedAddons.get("effectDate")).equalsIgnoreCase("null")?"":(String)selectedAddons.get("effectDate")):(String)selectedAddons.get("effectDate");
					
						if(Integer.parseInt(selectedCount)==0)
						{
						   mode = "fresh";
						}
				}
				catch(Exception e)
				{
					System.out.println("Exception is 298.."+e);
					e.printStackTrace();
				}
					if((selectedAddons.size()>0)&& (!"fresh".equalsIgnoreCase(mode)))
					{
						//isEditMode=true;
						for(tt=0;tt<Integer.parseInt(selectedCount);tt++)
						{
							commName_S=(String)selectedAddons.get("clauses"+(tt+1));
							commName_S=(String)selectedAddons.get("clausesId"+(tt+1));
                          	try
							{		/*out.println("dummyInt.."+dummyInt);
									out.println("commName_S..."+Integer.parseInt(commName_S)); out.println("<br>");*/
								if(((dummyInt)==Integer.parseInt(commName_S)))
								{
									flag	=	true;
									desc_S=(String)selectedAddons.get("description"+(tt+1));
								}
							}catch(Exception e){System.out.println("Exception is 298.."+e);e.printStackTrace();}
						}
					}
					%>
					<tr>
					<%
					if(flag)
					{
			%>
				<td align="left" <%=bgcolor%>><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" <%=isErrorPage?((request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""):"checked"%> value="<%= commName %>" ></td>
					<td   width="20%" ><b><div align="center"><%=(String)conditionsIdsList.get(""+(k))%></div></b></td>
					
					<td ><textarea name="description<%=(k+1)%>" rows="4" cols="60" id="description<%=(k+1)%>" onkeyup="textLimit(this,2000)"><%=isErrorPage?(request.getParameter("description"+(k+1))==null?desc_S:request.getParameter("description"+(k+1))):desc_S%></textarea></td>
                   
                    </tr>
	<%					
		}
		else if(!"fresh".equalsIgnoreCase(mode))
		{
			String event="";
	%>
	<tr <%=bgcolor%> >		
		
		
		<td align="left" ><%=k+1%>&nbsp;
		<input type="checkbox" name="clauses<%=(k+1)%>" value="<%= commName %>" <%=(request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":""%> >
		</td>
		<td   align="left" width="20%" ><div align="center"><b><%=(String)conditionsIdsList.get(""+(k))%></b></div></td>
						
		<td align="left">
		<textarea name="description<%=(k+1)%>" rows="4" cols="60" id="description<%=k%>" onkeyup="textLimit(this,2000)"><%=(request.getParameter("description"+(k+1))==null?commName:request.getParameter("description"+(k+1)))%></textarea></td>
                                                           
	</tr>
	<%
								
					}
					else if("fresh".equalsIgnoreCase(mode))
					{
				%>
				<tr <%=bgcolor%> >		
						<td   align="left" ><%=k+1%>&nbsp;<input type="checkbox" name="clauses<%=(k+1)%>" value="<%= commName %>" <%=(request.getParameter("clauses"+(k+1))==null?"":request.getParameter("clauses"+(k+1))).equalsIgnoreCase(commName)?"checked":"checked"%> ></td>
										
					<td   align="left" width="20%" ><div align="center"><b><%=(String)conditionsIdsList.get(""+(k))%></b></div></td>
						<td align="left"><textarea name="description<%=(k+1)%>" rows="4" cols="60" id="description<%=k%>" onkeyup="textLimit(this,2000)"><%=(request.getParameter("description"+(k+1))==null?commName:request.getParameter("description"+(k+1)))%></textarea></td>
				</tr>
				<%}%>						
				<%
				}//ENd of FOR
	%>
					<tr>
						<td colspan="3" align="right">
							Effective Date <input readOnly value = "<%=(effectDate==""?todayStr:effectDate)%>" type="text" class="fde1"name="effectDate" size="20">
                			<a href="javascript:cal6.popup();"><img alt="Click Here Pick up the date" src="<%=path%>/images/cal.gif" border="0" width="16" height="16"></a><br>
						</td>						
					</tr>			
				</tbody>
			<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<!--<input type="image"  src="../images/btn_cancel.gif"   onClick="window.close()" tabindex=0 accesskey='c'>	
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="image" accesskey="p"  src="../images/button_submit.gif" / >	
				-->
			<a href="javascript:window.close()"  class="buttonsMenu" > <img src="../images/Back.jpg" ></a>
			&nbsp;&nbsp;&nbsp;
			<a href="#" onClick="formSubmit()" class="buttonsMenu" > <img src="../images/Submit.jpg" ></a>
		</td>
	</tr>
</table>
</div>    
</form>
<script type="text/javascript">
	
 var elem = document.getElementById("sumInsured<%=as%>");
 if(elem!=null)
 {
	elem.focus();
	elem.select();
 }
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Character is Exceed Maximum Length!'+maxlen);
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
} 

	var cal6 = new calendar1(document.forms['clauses'].elements['effectDate']);
	cal6.year_scroll = true;
	cal6.time_comp = false;
	
	
</script>
</body>
</html>

