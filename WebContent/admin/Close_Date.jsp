<!DOCTYPE HTML >
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<jsp:useBean id="exportBean" class="com.maan.admin.ExportBean">
</jsp:useBean>
</head>
<%@ include file="header.jsp" %>
<%
	String proCodes = "";
	proCodes=request.getParameter("productCode")==null?"":(String)request.getParameter("productCode");
 %>
<body onLoad="dislayAll('<%=proCodes%>')">
<form name="form1" method="post">
<%

	String error=request.getParameter("error")==null?"":request.getParameter("error");
	String user=(String)session.getAttribute("user");
	String closedDay=request.getParameter("closedDay")==null?"Select":request.getParameter("closedDay");
	String closedMonth=request.getParameter("closedMonth")==null?"Select":request.getParameter("closedMonth");
	String closedYear=request.getParameter("closedYear")==null?"Select":request.getParameter("closedYear");
	String openDay=request.getParameter("openDay")==null?"Select":request.getParameter("openDay");
	String openMonth=request.getParameter("openMonth")==null?"Select":request.getParameter("openMonth");
	String openYear=request.getParameter("openYear")==null?"Select":request.getParameter("openYear");
	String entryDay = "";
	String entryMonth= "";
	String entryYear= "";
	String Closed_Remarks = request.getParameter("Closed_Remarks")==null?"":request.getParameter("Closed_Remarks");
	
	String productDetail[][]=exportBean.getProductDetail(actualBranch);

	String error_msg = "";
	if(request.getAttribute("Trn_Error")!=null)
		error_msg = (String)request.getAttribute("Trn_Error");
	String result_TRN[][] = new String[0][0];
%>
<%
String[] DD={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
String[] MMM={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
String[] YYYY={"2009","2010","2011","2012","2013"};

	Date date = new Date();
	String rptDate;
	SimpleDateFormat YearOnly = new SimpleDateFormat("yyyy");
	SimpleDateFormat MonthOnly = new SimpleDateFormat("MMM");
	SimpleDateFormat DayOnly = new SimpleDateFormat("dd");

	entryYear = YearOnly.format(date);
	entryMonth = MonthOnly.format(date);
	entryDay = DayOnly.format(date);

%>
<br/><br/>
 <table width="900" border="0" cellspacing="0" cellpadding="0" align="center">

                <tr> 
                  <td width="8" height="25" class="heading">&nbsp;</td>
                  <td width="1" class="heading"></td>
                 <td width="100%" class="heading"><strong style="color: #ffffff;">TRN CLOSING DETAILS</strong></td>
          </tr>
          <tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
		  <%
				if(error_msg.length()>0)
				{
		  %>
					<tr align="center">
						<td colspan="3"><font color="red"><%=error_msg%></font></td>
					</tr>
		  <%
				}
		  %>
<tr align="center">
  <td colspan="3">
	<table width="100%"  border="0" cellspacing="1" cellpadding="0" 
style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
		<tr> 
			  <td width="10%">&nbsp;</td>
			  <td width="20%" height="33" align="center" colspan="4"><Strong>TRN Closing Detials</Strong></td>
		</tr>
		<tr height="5"><td colspan="5"></td></tr>
		<tr> 
			  <td width="10%">&nbsp;</td>
			  <td width="20%" height="33" align="right" colspan="4"><Strong><%=entryDay%>th&nbsp;<%=entryMonth%>&nbsp;<%=entryYear%></Strong></td>
		</tr>
		<tr height="5"><td colspan="5"></td></tr>
		<tr> 
           <td width="10%">&nbsp;</td>
           <td width="20%" height="33" align="left">&nbsp;</td>
           <td width="30%" align="left"><Strong>Please select the product :</Strong></td>
           <td width="50%" align="left">
				<select name="productCode" id="procode" onChange="hideRows()" class="inputSelect">
				<option value="0" id="0">select</option>
				<%
					for(int pro=0;pro<productDetail.length;pro++)
					{
						if(productDetail[pro][0].equals(proCodes)){
				%>
						<option value="<%=productDetail[pro][0]%>" id="<%=productDetail[pro][0]%>" selected> <%=productDetail[pro][1]%></option>
				<%
						}
						else{
				%>
						<option value="<%=productDetail[pro][0]%>" id="<%=productDetail[pro][0]%>"> <%=productDetail[pro][1]%></option>
				<%
						}
					}
				%>
					</select>
					</td>
				   <td width="5%" align="left">&nbsp;</td>
                 </tr>
	<%
		if(error_msg.length()<=0)
		{
			try
			{
				//result_TRN = exportBean.selectCloseTRN(actualBranch);
				result_TRN = exportBean.selectCloseTRN(actualBranch,proCodes);
				if(result_TRN.length>0)
				{
					StringTokenizer stClose = new StringTokenizer((result_TRN[0][0]==null?"":result_TRN[0][0]),"-");
					while(stClose.hasMoreTokens())
					{
						closedDay = stClose.nextToken();
						closedMonth = stClose.nextToken();
						closedYear = stClose.nextToken();
					}
				
					StringTokenizer stopen = new StringTokenizer((result_TRN[0][3]==null?"":result_TRN[0][3]),"-");
					while(stopen.hasMoreTokens())
					{
						openDay = stopen.nextToken();
						openMonth = stopen.nextToken();
						openYear = stopen.nextToken();
					}
					Closed_Remarks = result_TRN[0][1]==null?"":result_TRN[0][1];
				}
			}
			catch(Exception e)
			{
			System.out.println("Exception in selecting data from T_TRN_CLOSING"+e.toString());
			}
		}
	%>
		<tr height="5"><td colspan="5"></td></tr>
		<tr id="policyOpenDateRow"> 
			  <td width="10%">&nbsp;</td>
			  <td width="20%" height="33" align="left">&nbsp;</td>
			  <td width="30%" align="left"><Strong>Policy Open Date :</Strong></td>
			  <td width="35%" align="left">
			<SELECT name='openDay' class="inputSelect" style="width: 30%; float: left;">  
			   <OPTION value='Select' >DD</OPTION>
			   <%
			   for(int Sele=0;Sele<DD.length;Sele++)
			   {
			   %>
					<OPTION value=<%=DD[Sele]%> <%=openDay.equalsIgnoreCase(DD[Sele])?"SELECTED":""%>><%=DD[Sele]%></OPTION>	
			   <%
			   }
			   %>
				</select>
				<SELECT name='openMonth' class="inputSelect" style="width: 30%; float: left;">  
				   <OPTION value='Select' >MMM</OPTION>	
			   <%
			   for(int Sele=0;Sele<MMM.length;Sele++)
			   {
			   %>
					<OPTION value=<%=MMM[Sele]%> <%=openMonth.equalsIgnoreCase(MMM[Sele])?"SELECTED":""%>> <%=MMM[Sele]%></OPTION>	
			   <%
			   }
			   %>
			</select>
			<SELECT name='openYear' class="inputSelect" style="width: 30%; float: left;">  
			   <OPTION value='Select' >YYYY</OPTION>	
				<%
		   java.util.Calendar cal = java.util.Calendar.getInstance();
		   for(int Sele=cal.get(java.util.Calendar.YEAR)-2;Sele<cal.get(java.util.Calendar.YEAR)+2;Sele++)
		   {
		   %>
					<OPTION value=<%=Sele%> <%=openYear.equalsIgnoreCase(String.valueOf(Sele))?"SELECTED":""%>><%=Sele%></OPTION>	
				   <%
				   }
				   %>
				</select>
				</td>
			  <td width="5%" align="left">&nbsp;</td>
          </tr>
          <tr height="5"><td colspan="5"></td></tr>
		  <tr id="policyCloseDateRow"> 
			  <td width="10%">&nbsp;</td>
			  <td width="20%" height="33" align="left">&nbsp;</td>
			  <td width="30%" align="left"><Strong>Policy Close Date :</Strong></td>
			  <td width="35%" align="left">
				<SELECT name='closedDay' class="inputSelect" style="width: 30%; float: left;">  
			   <OPTION value='Select' >DD</OPTION>	
		   <%
			   for(int Sele=0;Sele<DD.length;Sele++)
			   {
		   %>
				<OPTION value=<%=DD[Sele]%> <%=closedDay.equalsIgnoreCase(DD[Sele])?"SELECTED":""%>><%=DD[Sele]%></OPTION>	
		 <%
			   }
		  %>
			</select>
			<SELECT name='closedMonth' class="inputSelect" style="width: 30%; float: left;">  
		   <OPTION value='Select' >MMM</OPTION>	
	   <%
		   for(int Sele=0;Sele<MMM.length;Sele++)
		   {
	   %>
			<OPTION value=<%=MMM[Sele]%> <%=closedMonth.equalsIgnoreCase(MMM[Sele])?"SELECTED":""%>><%=MMM[Sele]%></OPTION>	
		<%
		   }
		%>
		</select>
		<SELECT name='closedYear' class="inputSelect" style="width: 30%; float: left;">  
						   <OPTION value='Select' >YYYY</OPTION>	
		   <%
		   for(int Sele=cal.get(java.util.Calendar.YEAR)-2;Sele<cal.get(java.util.Calendar.YEAR)+2;Sele++)
		   {
		   %>
			<OPTION value=<%=Sele%> <%=closedYear.equalsIgnoreCase(String.valueOf(Sele))?"SELECTED":""%>><%=Sele%></OPTION>	
		   <%
		   }
		   %>
			</select>
		  </td>
           <td width="5%" align="left">&nbsp;</td>
        </tr>
        <tr height="5"><td colspan="5"></td></tr>
		<tr id="remarksRow"> 
                  <td width="10%">&nbsp;</td>
                   <td width="20%" height="33" align="left">&nbsp;</td>
                   <td width="30%" align="left"><Strong>Remarks :</Strong></td>
	               <td  width="50%" align="left">
									<textarea name="Closed_Remarks" class="inputBoxA" style="width: 90%;" onkeyup="textLimit(this,110)"><%=Closed_Remarks%></textarea>
					</td>
				   <td width="5%" align="left">&nbsp;</td>
                 </tr>
			 <tr id="viewReportRow"> 
					<td width="10%">&nbsp;</td>
					<td width="20%" height="33" align="left" colspan="4">
					<a class="two" href="javascript:viewReport();"><strong>VIEW REPORT<strong></a></td>					  
              </tr>
                     
                          <tr id="submitButtonRow">
                        <td height="32" align="center"  colspan="8"> 
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                          <a onclick="Actionsubmit()"><img src="<%=path%>/images/Submit.jpg" ></a>                  </td>


              </tr>
                      
	                
                          </table>
                        </td>
              </tr>
            </table></td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
 
</table>
<input type="hidden" name="loginId" value='<%=user%>'/>
<input type="hidden" name="requestfrom" value="Close_Date"/>
<input type="hidden" name="path" id="pathid" value="<%=path%>/admin/Close_Date.jsp"/>
<input type="hidden" name="entryYear" value='<%=entryYear%>'/>
<input type="hidden" name="entryMonth" value='<%=entryMonth%>'/>
<input type="hidden" name="entryDay" value='<%=entryDay%>'/>
</form>
</body>
<script type="text/javascript">
function viewReport()
{

	var urlargv=window.document.getElementById("procode").value
	var urlargn=window.document.getElementById("procode").options[window.document.getElementById("procode").selectedIndex].text;
	document.form1.action="../admin/Close_Date_Report.jsp?productCode="+urlargv+"&productName="+urlargn;
	document.form1.submit();
}

function hideRows()
{
	var procode=window.document.getElementById("procode").value;
	switch(procode)
	{
		case "0":
			window.document.getElementById("policyOpenDateRow").style.display='none';
			window.document.getElementById("policyCloseDateRow").style.display='none';
			window.document.getElementById("remarksRow").style.display='none';
			window.document.getElementById("submitButtonRow").style.display='none';
			window.document.getElementById("viewReportRow").style.display='none';
			break;
		default:
			window.document.getElementById("policyOpenDateRow").style.display='';
			window.document.getElementById("policyCloseDateRow").style.display='';
			window.document.getElementById("remarksRow").style.display='';
			window.document.getElementById("submitButtonRow").style.display='';
			window.document.getElementById("viewReportRow").style.display='';
		var url="";
		url=window.document.getElementById("pathid").value+"?productCode="+procode;
		window.location.href=url;
	}
}

function Actionsubmit()
{
	document.form1.action="ExportTableController"
	document.form1.submit();
} 

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Max Length is 120 Charectors only!');
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
} 

function dislayAll(proval)
{
	if(proval=="")
	{
		window.document.getElementById("policyOpenDateRow").style.display='none';
		window.document.getElementById("policyCloseDateRow").style.display='none';
		window.document.getElementById("remarksRow").style.display='none';
		window.document.getElementById("submitButtonRow").style.display='none';
		window.document.getElementById("viewReportRow").style.display='none';
	}
}
</script>
</html>