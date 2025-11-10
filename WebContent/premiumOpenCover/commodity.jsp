
<%@ include file="submenu.jsp"%>
<html>
<%@ page import="java.util.StringTokenizer" %>
<script>

function controls()
{
	document.commodity.action='commodity.jsp';
	document.commodity.submit();
}
</script>
    <head>
        <title>** Emirates - Marine Insurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath() %>/css/footable-0.1.css" rel="stylesheet" type="text/css">        
    </head>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>

<jsp:useBean id="beans" class="com.maan.opencover.bean.openCoverQuotation">
<jsp:setProperty name="beans" property="*"/>
</jsp:useBean>

<%!	String[][] commodities=null; %>

<body>
<%
		String chkProposalNo = request.getParameter("chkProposalNo") == null ? "0" : request.getParameter("chkProposalNo");
		
		String adminBranch = (String) session.getAttribute("AdminBranchCode");
		//Belonging Branch
		String belongingBranch = (String) session.getAttribute("BelongingBranch");
		if(adminBranch.indexOf("'")!=-1)
			adminBranch = adminBranch.replaceAll("'","");
		String cid = (String) session.getAttribute("AdminCountryId");

			commodities=beans.getCommodity(belongingBranch);

	String search=request.getParameter("search")==null?"":request.getParameter("search");
	String commodityTypeDetail[][] = new String[0][0];
	commodityTypeDetail = beans.getCommodityTypeId(belongingBranch);
	String commodityType = "";
	commodityType = commodityType == null ? "" : commodityType;
	String selected ="";
	String comm ="";
	
	String bgcolor="";
	String as="";
	int l=0;
	int matches=0;
	String error = request.getParameter("error")==null?"":request.getParameter("error");
	 %>
<form name="commodity" method="post">
<br>
<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
<tr>
<td >&nbsp;</td>
<td align="left" >&nbsp;<span class="heading"> Category Of Goods Information </span></td>
</tr>

<tr>
<td colspan="2" align='center'> Goods Search &nbsp;&nbsp;
<input type='text' name='search' class="fde1"value="<%=request.getParameter("search")==null?"":request.getParameter("search")%>"/>
&nbsp;&nbsp;
<input type='submit' class="fde1" value='Search' onClick="controls()"  accesskey='s'/>
</td>
</tr>

<tr>
<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td height="17" align="left"><font color="red"><%=error%></font></td>
</tr>		
<tr>
<td colspan="2"> 
<table width="95%"  border="1" cellspacing="1" cellpadding="0" class="" align='center'>
<tr>
<td align="left" class="">
<div  STYLE="overflow: auto; width:100%; height:65vh;">
<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;" class="footable">
<thead>
<tr class="royamenuhead"> 
<th width="30%" class="bottomtext">Category Of Goods&nbsp;<FONT color=red>**</FONT></th>
<th width="30%" class="bottomtext">Description&nbsp;</th>
<!--<th width="30%" class="bottomtext">Cargo Type&nbsp;<FONT color=red>**</FONT></th>
--><th width="10%" class="bottomtext">Fragile</th>
</tr>
</thead>
<tbody>
<%
	for(int i=1;i<=commodities.length;i++)
	{
		String event="";
		if(!search.equals(""))
		{
			StringTokenizer st = new StringTokenizer(commodities[i-1][1]," ");
			if(commodities[i-1][1].equalsIgnoreCase(search.trim()) && search.trim().indexOf(" ")!=-1)	
			{
				matches=matches+1;
				bgcolor="class='bordercolor'";
				if(l==0)	
				{
					as=""+1;
					l++;
				}
			}else if(commodities[i-1][1].indexOf(search.trim())!=-1 && search.trim().indexOf(" ")!=-1)	
			{
				matches=matches+1;
				bgcolor="class='bordercolor'";
				if(l==0)	
				{
					as=""+1;
					l++;
				}
			}
			  while (st.hasMoreTokens()) 
				{
					event=st.nextToken();
					if(event.equalsIgnoreCase(search.trim()))
					{
						matches=matches+1;
						bgcolor="class='bordercolor'";
						if(l==0)
						{
							as=""+i;
							l++;
						}
					}
					else if((event.toLowerCase()).indexOf((search.toLowerCase()).trim())!=-1)	
					{
						matches=matches+1;
						bgcolor="class='blueborder'";
						if(l==0)	
						{
							as=""+1;
							l++;
						}
					}
				}
		}
%>
		<tr <%=bgcolor%>>
			<td align="left"><input type="checkbox" name="commodityId_<%=i%>" id="<%=commodities[i-1][0]%>" value="<%=commodities[i-1][1]%>" <%=request.getParameter("commodityId_"+i)!=null?"checked":""%>><%=commodities[i-1][1]%></td>
			<input type="hidden" name="opencommodityId<%=i%>" value="<%=commodities[i-1][0]%>"/>
			<input type="hidden" name="comName<%=i%>" value="<%=commodities[i-1][1]%>"/>
			<td align="center">
			<textarea name="commodity_desc<%=i%>" id="<%=commodities[i-1][0]%>" onkeyup="textLimit(this,480)" rows="3" cols="20"><%=request.getParameter("commodity_desc"+i)==null?commodities[i-1][1]:request.getParameter("commodity_desc"+i)%></textarea></td>
			<!--<td align="center">
			<select name="commodityType<%=i%>" id="commodityType<%=i%>"  class="inputSelect">
			<option value='select'>Select</option>
<%		
		try{
				for(int ct=0;ct<commodityTypeDetail.length;ct++)
				{
					comm = request.getParameter("commodityType"+i)==null?(commodityType+ct+1):request.getParameter("commodityType"+i);
					if(comm.equalsIgnoreCase(commodityTypeDetail[ct][0]))
						selected = "selected";
					else
						selected = "";
%>
			<option value=<%=commodityTypeDetail[ct][0]%> <%=selected%>><%=commodityTypeDetail[ct][1]%></option>
<%	
				}
			}catch(Exception e)
				{	System.out.println("Exception for"+e.toString());		e.printStackTrace();		}
%>
			</select> 
			</td>
			--><td align="center"><input type="checkbox" id="<%=commodities[i-1][0]%>" name="commodityFragile<%=i%>" <%=request.getParameter("commodityFragile"+i)!=null?"checked":""%>></td>
		</tr>
			
	<% bgcolor="";}%>	
		</tbody>
		</table>
		</div>
		</td></tr>
		<tr>
	<%
	if(!search.equals(""))
	{

	 %>
<font size="2" color="#660000">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><%=matches%> Match Found!!</b>
</font>	 <%
	 }	 
	 %>	 
			  
               <table width="176" border="0" align="center" cellpadding="0" cellspacing="0">
               		<tr>
	                    <td width="46" valign="middle" class="">
						<a href= "#" onClick='window.close()'  >
						<img src="<%=pathq%>/images/Back.jpg"></a>	</td>
	      	      		<input type="hidden" name="identify" value="<%=request.getParameter("identify")%>">
	      	      		<td width="46" valign="right" class="">
						<a href="#" onClick='return submitForms()'  >
						<img src="<%=pathq%>/images/Submit.jpg"></a>			</td>
					</tr>
            </table>
             </tr>
              </table>
			  </td>
            </tr>
           </table>
            <input type="hidden" name="totalLength" value="<%=commodities.length%>">
				<input type="hidden" name="error" maxlength="65535"/>
				<input type="hidden" name="totalTypeIDLength" value="<%=commodityTypeDetail.length%>">
            <input type="hidden" name="chkProposalNo" value="<%=chkProposalNo%>"/>
           </form>
    </body>
</html>

<script>
var elem = document.getElementById("commodity_desc<%=as%>");
if(elem!=null)
{
	elem.focus();
	elem.select();
}

function settingBefore(selectLength)
{
	if(selectLength.length>=1)
	{
		var commodityLen=document.commodity.totalLength.value;
		var idLen=document.commodity.totalTypeIDLength.value;
		for(var i=1;i<=commodityLen;i++)
		{
			var c=eval("window.opener.Quotation.commodityId"+i+".value");
			if('selected'==c)
			{
				eval("document.commodity.commodityId_"+i+".checked=true");
				eval("document.commodity.commodity_desc"+i+".value=window.opener.Quotation.commodity_desc"+i+".value");
				c=eval("window.opener.Quotation.commodity_Fragile"+i+".value");
				if('selected'==c)
					eval("document.commodity.commodityFragile"+i+".checked=true");
				/*c=eval("window.opener.Quotation.commodityType"+i+".value");
				for(var ct=1;ct<=idLen;ct++)
				{
					if(ct == c)
						eval("document.commodity.commodityType"+i+".value="+c);
				}*/
			}
		}
	}
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Character is Exceed Maximum Length!');
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
} 

function submitForms()
{	
	//with(Form)
	//{
	//	var r=document.commodity.commodityLen.length;
		var count=0;
		var commodityLen = document.commodity.totalLength.value;
		var k=0;
		var name='';
		var nameId='';
		var error="";
		var cmName = ' ';
	if(window.opener.document.Quotation.chkProposalNo.value == document.commodity.chkProposalNo.value)
	{
		for(var i=1;i<=commodityLen;i++)
		{			
			var c = eval("document.commodity.commodityId_"+i+".checked");
			if(c)
			{ 
				/*var selObj = document.getElementById("commodityType"+i);
				var typeid = selObj.options[selObj.selectedIndex].value;*/
				k++;	
				//val = selObj.options[selObj.selectedIndex].value;
				var openComId = eval("document.commodity.opencommodityId"+i+".value");
				nameId=nameId+','+openComId;
				name=name+','+eval("document.commodity.commodityId_"+i+".value");
				eval("window.opener.document.Quotation.commodityId"+i+".value='selected'");
				eval("window.opener.document.Quotation.commodity_desc"+i+".value=document.commodity.commodity_desc"+i+".value");
				if(eval("document.commodity.commodityFragile"+i+".checked"))
				{	
					eval("window.opener.document.Quotation.commodity_Fragile"+i+".value='selected'");	
				}
				
				var comDesc = eval("document.commodity.commodity_desc"+i+".value");
				comDesc = removeSpaces(comDesc);
				var errname = eval("document.commodity.comName"+i+".value");
				errname = "<font color='blue'>"+errname+"</font>";

				if(comDesc.length == 0)
				{
					error = error+"Please enter goods desription for - "+errname+"<br/>";
				}

				/*if(typeid=='select')
				{
					error = error+"Please select cargo type for goods - "+errname+"<br/>";
				}*/
				else
					eval("window.opener.document.Quotation.commodityType"+i+".value=1");
			}
			else {
				eval("window.opener.document.Quotation.commodityId"+i+".value=''");
			}
		}
		name=name.substr(1,name.length); 
		nameId=nameId.substr(1,nameId.length);
		window.opener.document.Quotation.TotalCommudity.value=k;
		window.opener.document.Quotation.commodityIds.value=nameId;
		window.opener.document.Quotation.commodity_TA.value=name;
		//}
		//document.Quotation.submit();
		//return false;
		//document.commodity.submit();
		if(error.length>0)
		{
			document.commodity.error.value=error;
			document.commodity.action='commodity.jsp';
			document.commodity.submit();
		}
		else
			window.close();
	} // chkproposalNo if statement...
	else
	{
		alert("please close the commodity selection window and reopen again");
		window.close();
	}
	return false;
}

function removeSpaces(string) {
 return string.split(' ').join('');
}

settingBefore('<%=request.getParameter("commodityIds")%>');					
</script>
