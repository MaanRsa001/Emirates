<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    </head>
	<body>
		<s:form name="links" theme="simple">
			<div class="container-fluid">
  				<s:property value="reqFrm"/>
  				<s:if test="linkType=='MAIL' && reqFrom=='QUOTEMAIL'">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
			    				<div class="col-md-12">
			    					<h4><s:text name="E-Mail Quote" /></h4>
			    				</div>
			    			</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<s:actionerror cssStyle="color:red;"/>
							</div>
							<div class="row">
								<div class="col-md-12" align="center">
									<table class="table">
										<thead>
						      				<tr>
						      					<th scope="col"><s:label key="lapsed.sno" /></th>
						      					<th scope="col"><s:label key="lapsed.quoteNo" /></th>
						      					<th scope="col"><s:label key="Customer Name" /></th>
						      					<th scope="col"><s:label key="lapsed.quoteDate" /></th>
						      					<th scope="col"><s:label key="lapsed.validity" /></th>
						      				</tr>
						      			</thead>
						      			<tbody>
						      				<s:iterator value="lapsedQuote" var="commodityList" status="stat">
						      					<tr>
						      						<td scope="row"><s:property value="#stat.index+1"/></td>
						      						<td><s:property value="QUOTE_NO"/></td>
						      						<td><s:property value="CUSTOMER_NAME"/></td>
						      						<td><s:property value="QUOTE_DATE"/></td>
						      						<td><s:property value="VALIDITY_DATE"/></td>
						      					</tr>
						      				</s:iterator>
						      			</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
			    					<div class="form-group">
										<s:label key="report.email"></s:label>&nbsp;<span style="color: red;">*</span>
										<s:textfield name="quoteMailIds" cssClass="form-control"/>
									</div>
								</div>
							</div>
						    <br/>
							<div class="row">
								<div class="col-md-12" align="center">
									<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="goIndex('QE',this.form)"/>
									<s:submit type="button" name="close"  key="Submit" cssClass="btn btn-success btn-oval" onclick="fnsubmit(this.form)"/>
								</div>
							</div>
						</div>
					</div>
			  		<s:hidden name="linkType"/>
			  		<s:hidden name="reqFrom"/>
			  		<s:hidden name="applicationNo"/>
			  		<s:hidden name="quoteNo"/>
			  		<s:hidden name="refStatus"/>
				</s:if>
				<s:elseif test="linkType=='MAIL'">
					 <div class="panel panel-primary">
					 	<div class="panel-body">
							<div class="row" align="center">
								<h5><s:text name="email.success"/></h5>
							</div>
							<br/>
							<div class="row" align="center">
								<input type="button" name="close"  value="Back" class="btn btn-danger btn-oval" onclick="goIndex('QE',this.form);"/>
							</div>
						</div>
					 </div>
				</s:elseif>
				<s:elseif test="linkType=='LAPSED'">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
			    				<div class="col-md-12">
			    					<h4><s:text name="Rejected Quote"/></h4>
			    				</div>
			    			</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<s:actionerror cssStyle="color:red;"/>
							</div>
							<div class="row">
								<div class="col-md-12" align="center">
									<table class="table">
										<thead>
						      				<tr>
						      					<th scope="col"><s:label key="lapsed.sno" /></th>
						      					<th scope="col"><s:label key="lapsed.quoteNo" /></th>
						      					<th scope="col"><s:label key="Customer Name" /></th>
						      					<th scope="col"><s:label key="lapsed.quoteDate" /></th>
						      					<th scope="col"><s:label key="lapsed.validity" /></th>
						      				</tr>
						      			</thead>
						      			<tbody>
						      				<s:iterator value="lapsedQuote" var="commodityList" status="stat">
						      					<tr>
						      						<td scope="row"><s:property value="#stat.index+1"/></td>
						      						<td><s:property value="QUOTE_NO"/></td>
						      						<td><s:property value="CUSTOMER_NAME"/></td>
						      						<td><s:property value="QUOTE_DATE"/></td>
						      						<td><s:property value="VALIDITY_DATE"/></td>
						      					</tr>
											    <s:hidden name="quoteNo" value="%{#commodityList.QUOTE_NO}"></s:hidden>
											    <s:hidden name="loginId" value="%{#session.user}"></s:hidden>
						      				</s:iterator>
						      			</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
			    					<div class="form-group">
										<s:label key="lapsed.reason"></s:label>&nbsp;<span style="color: red;">*</span>
										<s:select name="lapsedRemarks" cssClass="form-control" list="lapsedReason" headerKey="" headerValue="Select" listKey="DETAIL_NAME" listValue="DETAIL_NAME"></s:select>
									</div>
								</div>
							</div>
						    <br/>
							<div class="row">
								<div class="col-md-12" align="center">
									<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="goIndex('QE',this.form)"/>
									<s:submit type="button" name="close"  key="Submit" cssClass="btn btn-success btn-oval" onclick="fnsubmit(this.form)"/>
								</div>
							</div>
						</div>
					</div>
				</s:elseif>
				<s:else>
					<div class="panel panel-primary">
					 	<div class="panel-body">
							<div class="row" align="center">
								<h5><s:text name="lapsed.success"/></h5>
							</div>
							<br/>
							<div class="row" align="center">
								<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="goIndex('QE',this.form)"/>
							</div>
						</div>
					 </div>
				</s:else>
			</div>
			<s:hidden name="loginId" value="%{#session.user}"/>
		</s:form>
	</body>
  <SCRIPT type="text/javascript">
 function goIndex(val,forms)
 {
		forms.action = "${pageContext.request.contextPath}/initReport.action?menuType="+val;
		forms.submit();
 }
  function fnsubmit(forms)
	{			
		forms.action = "${pageContext.request.contextPath}/mailReport.action";
		forms.submit();
	}
 </SCRIPT>
</html>
