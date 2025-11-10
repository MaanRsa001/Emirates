
<%@ page import="java.util.HashMap,java.math.BigDecimal" %>
<html>
    <head>
        <title>Emirates - MarineInsurance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
          
</style>
</head>
<body>
<%
	String chkProposalNo = (String)session.getAttribute("proposalNo");
	HashMap fullDetails=new HashMap();
	String selectedCount="0";
	session.removeAttribute("fullDetails");
	fullDetails=(HashMap)request.getAttribute("fullDetails");
	session.setAttribute("fullDetailss",fullDetails);
    selectedCount=(String)fullDetails.get("finalCount");
    String commodity="";
	String openCoverNo="";
	String proposalNo="";
	String coverNo ="";
	String productId="0";
	openCoverNo=(String) session.getAttribute("openCoverNo")==null?"0":(String) session.getAttribute("openCoverNo");
	proposalNo=request.getParameter("proposalNo")==null?(String)session.getAttribute("proposalNo"):request.getParameter("proposalNo");
	
	coverNo=request.getParameter("coverNo")==null?"0":request.getParameter("coverNo");
	session.setAttribute("proposalNo",proposalNo);  
 	productId=(String) session.getAttribute("product_id")==null?"0":(String) session.getAttribute("product_id");
    String applicationNo="";
    applicationNo=(String)request.getAttribute("applicationNo")==null?	"0":((String)request.getAttribute("applicationNo")).equals("")?"111":(String)request.getAttribute("applicationNo");
					
	session.setAttribute("applicationNo",applicationNo);
    java.util.HashMap map= new java.util.HashMap();
    java.util.Collections.synchronizedMap(map);
    int i=1,no=4;
    String []s=new String [5];
    while(i<=no)
    {
       if(request.getParameter("c"+i)!=null)
       {
		   if(request.getParameter("s"+i)!=null)
			{
			   s[i]=request.getParameter("s"+i);
			   map.put(request.getParameter("c"+i),request.getParameter("s"+i));
			}
			else
			{
				// no si entered need to validate
			}
        }
       i++;
    }
  %>
	<form name="clauses" method="post"  >
	<input type ="hidden" name = "coverNo" value = "<%=coverNo%>">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td width="29" height="34" align="center">&nbsp;</td>
	<td width="726" align="right"><table width="340" border="0" cellspacing="0" cellpadding="0">
	</table></td>
	</tr>
	<tr>
	<td height="17" ></td>
	<td></td>
	</tr>		
	<tr align="center">
	<td colspan="2"> 
	<table width="95%"  border="0" cellspacing="1" cellpadding="0" class="">
	<tr>
	<td align="center" class="ltbgyellow">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
	<tr> 
	<td height="17" colspan="7" class="" align="left">&nbsp;&nbsp;&nbsp;<strong><span class="heading">CLAUSES SUMMARY </span></strong></td>
	</tr>
	<tr> 
	<td colspan="7">&nbsp;</td>
	</tr>
	<tr> 
	<td width="13">&nbsp;</td>
	<td width="50" height="33" align="center"><strong>SI No </strong></td>
	<td width="500" align="left"><strong>Opted Clauses </strong></td>
	</tr>
	<%
			String clauses="";
			boolean isFirstClauses=true;
			int item=1; 
			java.text.NumberFormat fmt=new java.text.DecimalFormat("##,##0.00") ;
			java.text.NumberFormat fmt1=new java.text.DecimalFormat("##,##0.000") ;
			String clausesSize="0";
			String commName="";
			String sumInsured="0";
			String[] selectedClauses=new String[0];
			String[] selectedSI=new String[0];
			clausesSize=request.getParameter("clausesSize")==null?clausesSize:request.getParameter("clausesSize");
			 try
			 {
					for(int k=0;k<Integer.parseInt(selectedCount);k++)
					{
						commName=(String)fullDetails.get("description"+(k+1));
		  %>
						<tr> 
						  <td width="13">&nbsp;</td>
						  <td width="50" height="33" align="center"><strong><%=(k+1)%></strong></td>
						  <td width="650" align="left"><%=commName%></td>
						</tr>
		  <%
					clauses=clauses+commName;
					}
			 }
					catch(Exception e){System.out.println("Exception is 298.."+e);e.printStackTrace();}
             %>
			<input type="hidden" name="clausesSummary" value="summary" >
			</table>
			</td>
			</tr>
			</table>
			</td>
			<tr>
			<td colspan="2" >
			<table width="98%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td height="32" align="right" valign="middle" class="medblue">
			<table border="0" align="right" cellpadding="0" cellspacing="0">
			<tr> 
			<td align="center" >
			<a href="#" onClick="return back()"  class="buttonsMenu" >
			<img src="../images/Back.jpg"> </a></td>
			<td>&nbsp;</td>
			<td align="center" >
			<a href="#" onClick=" reDirectMe('submitt','<%=coverNo%>','<%=i%>')"  class="buttonsMenu" >
			<img src="../images/Submit.jpg" ></a> </td>
			<td>&nbsp;</td>
		</table>
		</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
<input type="hidden" name="chkProposalNo" value="<%=chkProposalNo%>"/>
</form>
</body>
</html>

<script>

function back()
{
	document.clauses.action="conditionsOpenEdit.jsp";
	document.clauses.submit();
	return false;
}

function reDirectMe(value,coverNo,i)
{
	if(value=='cancel')
	{
		document.clauses.action="conditionsOpenEdit1.jsp";
	}
	if(value='submitt')
	{
		if(window.opener.document.form1.chkProposalNo.value == document.clauses.chkProposalNo.value)
		{
			eval("window.opener.document.form1.clauses_"+coverNo+".value="+i);
			var r=eval("window.opener.document.form1.clauses_"+coverNo+".value");
		}
		else
		{
			alert("please close the clauses selection window and reopen again");
		}
		window.close();
	}
}

function setValue(Form)
{
	document.forms[0].submit();
	return true;
	alert(document.forms.length);
	alert(document.forms[0].box.value);
	with(Form)
	{
		var n=3;
		for (var i=0; i <n; i++)
        {
			if(c[i].checked)
            {
				var rad_val = c[i].value;
			}	
		}
	}
}
    
</script>
