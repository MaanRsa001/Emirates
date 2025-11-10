<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
	  <link href="../css/style.css" rel="stylesheet" type="text/css">
  </head>
   
  <body>
 
  	<s:form action="/uploadDoUpload.action" method="post" enctype="multipart/form-data" theme="simple"  >
    	<table width="80%" border="0" cellspacing="2" cellpadding="2" align="center" >
    		<tr align="left">
				<td height="20" colspan="2" style="color:red">
					<s:actionerror/>
					<s:fielderror/>
				</td>
			</tr>
		</table>
    	<table width="80%" border="0" cellspacing="2" cellpadding="2" align="center" >
    		<tr align="center">
				<td height="20" colspan="2" >
					<div id="loaderImage" style="display:none">
						<b><s:label key="upload.progress" cssStyle="color:#073e8f" /></b>
						<br>
						<br>
					</div>
				</td>
			</tr>
		</table>
    	<table width="55%" border="0" cellspacing="2" cellpadding="2" align="center" class="data-table-border" >
		    <tr class="runtext">
    			<td align="center" colspan="2" class="heading">
    				<b><s:label key="upload.fileUpload"/></b>
    			</td>
    		</tr>
    		
    		<tr class="runtext">
    			<td width="30%"><s:label key="upload.fileToUpload"/><font color="red">*</font></td>
    			<td align="left" width="70%">
    				<s:file name="upload" cssClass="form" cssStyle="width:200px"/>
    			</td>
    		</tr>
    		<tr >
    			<td align="center" colspan="2">
    				<b>&nbsp;</b>
    			</td>
    		</tr>
    		<tr >
   				<td colspan="2" align="center">
   					<s:submit align="center"  key="upload.upload" />
   					
   				</td>
    		</tr>
    	</table>
    </s:form>
 
 
	</table>
	
  </body>
  <SCRIPT type="text/javascript">
  
  	function loaderImage()
	{
		document.getElementById('loaderImage').style.display="block";
	}

  </SCRIPT>
</html>
