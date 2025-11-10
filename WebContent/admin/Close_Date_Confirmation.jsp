<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<jsp:useBean id="exportBean" class="com.maan.admin.ExportBean">
</jsp:useBean>
</head>

<body>
<form name="form1" method="post">
<%

String error=request.getParameter("error")==null?"":request.getParameter("error");
String user=(String)session.getAttribute("user");
/////////////////Closed Date
String	closedDay="";
String	closedMonth="";
String	closedYear="";
/////////////////Open Date
String	openDay="";
String	openMonth="";
String	openYear="";
/////////////////Entry Date
String	entryDay = "";
String	entryMonth= "";
String	entryYear= "";

%>
<%
	
	String error_msg = "";
	if(request.getAttribute("Trn_Error")!=null)
		error_msg = (String)request.getAttribute("Trn_Error");
	String result_TRN[][] = new String[0][0];
	if(error_msg.length()>0)
	{
		try{		    
			String productCode="";			
			productCode=(String)request.getAttribute("productCode");		   
			result_TRN = exportBean.selectCloseTRN(actualBranch,productCode);		
			
			if(result_TRN.length>0)
			{
				StringTokenizer stClose = new StringTokenizer((result_TRN[0][0]==null?"":result_TRN[0][0]),"-");
				while(stClose.hasMoreTokens())
				{
					closedDay = stClose.nextToken();
					closedMonth = stClose.nextToken();
					closedYear = stClose.nextToken();
				}
				StringTokenizer stentry = new StringTokenizer((result_TRN[0][2]==null?"":result_TRN[0][2]),"-");
				while(stentry.hasMoreTokens())
				{
					entryDay = stentry.nextToken();
					entryMonth = stentry.nextToken();
					entryYear = stentry.nextToken();
				}
				StringTokenizer stopen = new StringTokenizer((result_TRN[0][3]==null?"":result_TRN[0][3]),"-");
				while(stopen.hasMoreTokens())
				{
					openDay = stopen.nextToken();
					openMonth = stopen.nextToken();
					openYear = stopen.nextToken();
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in selecting data from T_TRN_CLOSING"+e.toString());
		}
	}
	int cm =0;
	int cm1 =0;
	int om =0;
	int em =0;
	String cmm = "";
	String cmm1 = "";
	String omm = "";
	String emm = "";
	String closedYear1 = "";
	try
	{
			String[] MMM={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
			for(int i=0;i<MMM.length;i++)
			{
				if(MMM[i].equalsIgnoreCase(closedMonth))
					cm = i+1;
				if(MMM[i].equalsIgnoreCase(openMonth))
					om = i+1;
				if(MMM[i].equalsIgnoreCase(entryMonth))
					em = i+1;
			}
			if(cm<=9)
				cmm = "0"+cm;
			else
				cmm = ""+cm;
			if(om<=9)
				omm = "0"+om;
			else
				omm = ""+om;
			if(em<=9)
				emm = "0"+em;
			else
				emm = ""+em;
			if(cm==12)
			{
				cm1=0;
				closedYear1 = ""+(Integer.parseInt(closedYear)+1);
			}
			else
			{
				cm1=cm;
				closedYear1 = ""+(Integer.parseInt(closedYear));
			}
			if(cm1<=9)
				cmm1 = "0"+(cm1+1);
			else
				cmm1 = ""+(cm1+1);
	}
	catch(Exception e)
	{
		System.out.println("Exception in T_TRN_CLOSING"+e.toString());
	}
%>

<br/><br/>
 <table width="900" border="0" cellspacing="0" cellpadding="0" align="center">

                <tr> 
                  <td width="8" height="25" class="heading">&nbsp;</td>
                  <td width="1" class="heading"></td>
                 <td width="100%" class="heading"><strong>TRN CLOSING DETAILS CONFIRMATION</strong></td>
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
	<table width="100%"  border="0" cellspacing="1" cellpadding="0">
		<tr> 
			  <td width="10%">&nbsp;</td>
			  <td width="20%" height="33" align="center" colspan="4"><Strong>TRN Closing Details Confirmations</Strong></td>
		</tr>
		<tr> 
			  <td width="10%">&nbsp;</td>
			  <td width="20%" height="33" align="right" colspan="4"><Strong><%=entryDay%>:<%=emm%>:<%=entryYear%></Strong></td>
		</tr>
		<tr> 
			  <td width="10%">&nbsp;</td>
			  <td width="20%" height="33" align="left" colspan="4"><Strong><u>For the Month of <%=entryMonth%> <%=entryYear%></u></Strong></td>
		</tr>
		<tr>
			  <td width="35%" align="left" colspan="2">&nbsp;</td>
			  <td width="10%" align="left"><Strong>Policy Open Date	:</Strong></td>
			  <td width="25%" align="left"><Strong><font color="DARKPINK"> <%=openDay%>:<%=omm%>:<%=openYear%></font></Strong></td>
		</tr>
		<tr> 
			  <td width="35%" align="left" colspan="2">&nbsp;</td>
			  <td width="10%" align="left"><Strong>Policy Close Date :</Strong></td>
			  <td width="25%" align="left"><Strong> <font color="DARKPINK"> <%=closedDay%>:<%=cmm%>:<%=closedYear%></font></Strong></td>
        </tr>
		<tr> </tr>
		<tr>
              <td width="10%">&nbsp;</td>
              <td width="20%" height="33" align="left" colspan="4"><Strong>&nbsp;&nbsp;&nbsp;&nbsp;After <font color="blue"><%=closedDay%>:<%=cmm%>:<%=closedYear%></font></Strong></td>
       </tr>
	   <tr>
              <td width="10%" colspan="2">&nbsp;</td>
              <td width="20%" height="33" align="left" colspan="3"><Strong>All policies generated from <font color="blue"><%=(Integer.parseInt(closedDay)+1)%>:<%=cmm%>:<%=closedYear%></font> to <font color="blue"><%=openDay%>:<%=cmm1%>:<%=closedYear1%></font> are inserted under <font color="blue"><%=openDay%>:<%=cmm1%>:<%=closedYear1%></font></Strong></td>
       </tr>
			 <tr> 
					<td width="10%">&nbsp;</td>
					  <td width="20%" height="33" align="left">&nbsp;</td>
					  <td width="30%" align="left">&nbsp;</td>
					  <td width="35%" align="left">&nbsp;</td>
					  <td width="5%" align="left">&nbsp;</td>
              </tr>
                     
                          <tr>
                        <td height="32" align="center" colspan="8"> 
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                          <a href="#" onclick="back()"><img src="<%=path%>/images/Back.jpg"></a>                  </td>
              </tr>
                      
	                
                          </table>
                        </td>
              </tr>
            </table></td>
            </tr>
          <tr align="center">
            <td height="1" colspan="3"></td>
          </tr>
          <tr align="center">
            <td colspan="3"><table width="100%"  border="0" cellspacing="0" cellpadding="0">

              
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" class="darkblue">&nbsp;</td>
  </tr>
</table>
</form>
</body>

</html>

<script>
function back()
{
	document.form1.action="Close_Date.jsp"
	document.form1.submit();
} 

 
</script>
