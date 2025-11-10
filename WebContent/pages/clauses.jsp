<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/common.js"></script>
    </head>

<body>
<s:form name="clauses" theme="simple" action="updateConditionsPremium.action">
	<table width="100%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#FFFFFF">
	<tr>
		<td width="100%" style="color: green;font-weight: bold;" align="left">	
  			<s:actionmessage/>			
		</td>
	</tr>
	<s:if test='#session.user1 == "admin"'>
		<s:if test="conditionsList['clausesDesc'].size > 0">
			<tr>
				<td class="heading" width="100%" colspan="2">	
	     			<s:label key="premiumInfo.clauses" />			
				</td>
			</tr>			
	    	<s:iterator value="conditionsList['clausesDesc']" var="clauses">
	    		<s:if test="%{CODE!=null}">
	    			<tr class="bg" >
						<td width="10%" align="center"><s:checkbox name="" id="clausesDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('clausesDesc%{CODE}');"/><s:hidden name="clausesId" value="%{CODE}"/> </td>
						<td width="90%"><s:textarea id="clausesDesc%{CODE}" name="clausesDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)"/></td>
					</tr>
		  		</s:if>
	    	</s:iterator>
	    	<s:iterator value="conditionsList['extraClausesDesc']" >
	    		<s:if test="%{CODE!=null}">
	    			<tr class="bg" >
						<td width="10%" align="center"><s:checkbox name="" id="warDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('warDesc%{CODE}');"/><s:hidden name="warId" value="%{CODE}"/> </td>
						<td width="90%"><s:textarea id="warDesc%{CODE}" name="warDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)"/></td>
					</tr>
			  	</s:if>
	    	</s:iterator>
		</s:if>
		<tr><td>&nbsp;</td></tr>
		<s:if test="conditionsList['exclusionsDesc'].size > 0">
			<tr >
				<td class="heading" width="100%" colspan="2">	
	     			<s:label key="premiumInfo.exclusions" />			
				</td>
			</tr>
	    	<s:iterator value="conditionsList['exclusionsDesc']" >
	    		<s:if test="%{CODE!=null}">	
	    			<tr class="bg" >
						<td width="10%" align="center"><s:checkbox name="" id="exclusionDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('exclusionDesc%{CODE}');"/><s:hidden name="exclusionId" value="%{CODE}"/> </td>
						<td width="90%"><s:textarea id="exclusionDesc%{CODE}" name="exclusionDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)"/></td>
					</tr>
		  		</s:if>
	    	</s:iterator>
		</s:if>
		<tr><td>&nbsp;</td></tr>	
		<s:if test="conditionsList['warrantyDesc'].size > 0">
			<tr>
				<td class="heading" width="100%" colspan="2">	
	     			<s:label key="premiumInfo.warranties" />			
				</td>
			</tr>
	    	<s:iterator value="conditionsList['warrantyDesc']" >
	    		<s:if test="%{CODE!=null}">
	    			<tr class="bg" >
						<td width="10%" align="center"><s:checkbox name="" id="warrantyDesc%{CODE}Check" fieldValue="%{CODE}" value="true" onclick="fnRemoveClauses('warrantyDesc%{CODE}');"/><s:hidden name="warrantyId" value="%{CODE}"/> </td>
						<td width="90%"><s:textarea id="warrantyDesc%{CODE}" name="warrantyDesc" value="%{CODEDESC}" cols="119" onkeyup="textLimit(this,250)"/></td>
					</tr>
		  		</s:if>
	    	</s:iterator>
		</s:if>
		</s:if>
		<s:else>
			<s:if test="conditionsList['clausesDesc'].size > 0">
				<tr>
					<td class="heading" width="100%" colspan="2">	
		     			<s:label key="premiumInfo.clauses" />			
					</td>
				</tr>		
				<tr class="bg">
					<td>				
				    	<s:iterator value="conditionsList['clausesDesc']" var="clauses">			  		
					  		<s:property value="CODEDESC"/><br/>
				    	</s:iterator>		
				    	<s:iterator value="conditionsList['extraClausesDesc']" >			  		
						  	<s:property value="CODEDESC"/><br/>
				    	</s:iterator>		
					</td>
				</tr>
			</s:if>
			<tr><td>&nbsp;</td></tr>
			<s:if test="conditionsList['exclusionsDesc'].size > 0">
				<tr >
					<td class="heading" width="100%"  colspan="2">	
		     			<s:label key="premiumInfo.exclusions" />			
					</td>
				</tr>
				<tr class="bg">
					<td>
				    	<s:iterator value="conditionsList['exclusionsDesc']" >			  		
					  		<s:property value="CODEDESC"/><br/>
				    	</s:iterator>		
					</td>
				</tr>
			</s:if>
			<tr><td>&nbsp;</td></tr>	
			<s:if test="conditionsList['warrantyDesc'].size > 0">
				<tr>
					<td class="heading" width="100%" colspan="2">
		     			<s:label key="premiumInfo.warranties" />			
					</td>
				</tr>
				<tr class="bg">
					<td>
				    	<s:iterator value="conditionsList['warrantyDesc']" >			  		
					  		<s:property value="CODEDESC"/><br/>
				    	</s:iterator>		
					</td>
				</tr>
			</s:if>
		</s:else>
			<tr><td>&nbsp;</td></tr>
			<tr align="center">
				<td class="text" colspan="7">
					<s:hidden name="applicationNo"/>			
					<input type="button" name="submit" key="commodity.close" class="btn" value="Close" onclick="window.close();return true;"/>
					<s:if test='#session.user1 == "admin" && !hasActionMessages()'>
						<s:submit type="submit" name="submit" key="commodity.submit" cssClass="btn" onclick="disableForm(this.form,false,'');window.opener.premiumInfo.updateClauses.value='N'"/>
					</s:if>
				</td>
			</tr>
	</table>
	</s:form>
  </body>
  <script>
  	function fnRemoveClauses(id){
  		if(document.getElementById(id+'Check').checked){
  			document.getElementById(id).disabled=false;
  		}else{
  			document.getElementById(id).value='';
  			document.getElementById(id).disabled=true;
  		}
  	}
  </script>
</html>
