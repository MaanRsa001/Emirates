<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Form</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/datepicker/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/datepicker/core.js"></script>
<script type="text/javascript" src="js/datepicker/widget.js"></script>
<script type="text/javascript" src="js/datepicker/datepicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script language="JavaScript">javascript:window.history.forward(1);</script>
<script>
	$(function() {
		$( "#effectiveDt" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat : "dd/mm/yy",
			minDate: "-0M " , 
		    maxDate: "+12M"
		});
	});
</script>
<script type="text/javascript" src="pages/admin/travel/menu.js"></script>
</head>
	<body>
	<div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:1000px;">
    <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <%@ include file="/pages/admin/travel/travelRatingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Relation Discount" style="padding:5px">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      		<tr>
		        		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
							<s:if test ="display==null or display == ''">
								<s:form name="form1" theme="simple">
							 		<table width="100%" align="center">
								        <tr>
								            <td class="heading" width="100%"><s:text name="travel.discountrate"/></td>
								            <td>&nbsp;</td>
								        </tr>
								 		<tr>
								        	<td height="10%">&nbsp;</td>
								        	<td>&nbsp;</td>
							        	</tr>
							        	<tr>
				  						    <td align="center"><s:text name="ChooseProduct" /><font color="red">*</font>
				  						    <s:select name="product" id="product" list="#{'31':'Travel Insurance Walla'}" cssClass="input" /></td>
			  							</tr>
			  							<tr><td>
								      	<display:table name="list" pagesize="10"  requestURI="${pageContext.request.contextPath}/relationdiscountTravelConfigure.action" class="table" uid="row" id="record" style="align:center; " cellpadding="1" cellspacing="1"  varTotals="totals" >
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.onepage" value="" />	
										<display:setProperty name="basic.empty.showtable" value="true"/>
										<display:setProperty name="paging.banner.no_items_found" value=""/>												
										<display:column sortable="true" style="text-align:center;" titleKey="SNo" value="${record_rowNum}"/> 
										<display:column sortable="true" style="text-align:center;" title="Discount Start" property="DISCOUNT_START"/>
										<display:column sortable="true" style="text-align:center;" title="Discount End" property="DISCOUNT_END"/>
										<display:column sortable="true" style="text-align:center;" title="Rate Value" property="RATE_VALUE"/>
										<display:column sortable="true" style="text-align:center;" title="Relation Type" property="RELATION_TYPE"/>
										<display:column sortable="true" style="text-align:center;" title="Core App Code" property="COREAPP_CODE"/>
										<display:column sortable="true"	style="text-align:center;" title="Effective Date" property="EFFECTIVE_DATE"/>
										<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS"/>
										<display:column sortable="true"	style="text-align:center;width:10%" title="History" >
		                    					<a href="#" onclick="return popUp('${pageContext.request.contextPath}/discountrateTravelConfigure.action?display=history&disId=${record.DISCOUNT_ID}','400','350');">View</a>
										</display:column>
										<display:column sortable="true" style="text-align:center;" title="Edit"  >
												<input type="button" name="reset" value="Edit" onclick="return fnsubmit(this.form,'edit','${record.DISCOUNT_ID}');" class="btn" />
										</display:column>
										</display:table>
										</td></tr>
										<tr><td align="center">
											<input type="button" name="sub" value="Add More" onclick="fnsubmit(this.form,'new','')" class="btn" />
										</td></tr>
									</table>
								</s:form>
							</s:if>
							<s:elseif test="'edit'.equalsIgnoreCase(display) || 'new'.equalsIgnoreCase(display)">
								<s:form name="form1"  method="post" theme="simple">
							 		<s:if test="hasActionErrors()">
							  			<table width="100%" align="center">
								        	<tr>
								            	<td  style="color:red;"><s:actionerror/></td>
								        	</tr>
							 			</table>
							 		</s:if>
							 		<table width="100%" align="center" bgcolor="#FFFFFF">
							 			<tr>
							 				<td>
							  					<table width="100%" align="center" cellpadding="4" border="0">
							  						<tr>
								                        <td class="heading" colspan="2"><s:text name="travel.discountrate"/></td>
								                        <td>&nbsp;</td>
								                    </tr>
								                    <tr>
								                         <td><s:text name="travel.relation.type" /><font color="red">*</font></td>
								                         <td><s:select name="disType" id="disType" list="#{'Self':'SELF','Spouse':'SPOUSE','Family':'FAMILY','Group':'GROUP'}" headerKey="" headerValue="-Select-" cssClass="input" /></td>
								                    </tr>
								                    <tr>
								                         <td><s:text name="travel.discount.start" /><font color="red">*</font></td>
								                         <td><s:select name="disStart" id="disStart" list="sage" headerKey="" headerValue="-Select-" cssClass="input" /></td>
								                    </tr>
								                    <tr>
								                         <td><s:text name="travel.discount.end" /><font color="red">*</font></td>
								                         <td><s:select name="disEnd" id="disEnd" list="eage" headerKey="" headerValue="-Select-" cssClass="input" /></td>
								                    </tr>
							                        <tr>
								                         <td><s:text name="travel.rate.value" /><font color="red">*</font></td>
								                         <td><s:textfield name="disRateValue" id="disRateValue" cssClass="input" /></td>
								                    </tr>
							                        <tr>
								                         <td><s:text name="travel.core.app.code" /><font color="red">*</font></td>
								                         <td><s:textfield name="disCode" id="disCode" cssClass="input" /></td>
								                    </tr>
							                        <tr>
							                             <td><s:text name="travel.effectivedate" /><font color="red">*</font></td>
		                            					 <td><s:textfield name="disDate"  readonly="true" id="effectiveDt"/></td>
								                    </tr>
									        		<tr>
								                         <td><s:text name="travel.status" /><font color="red">*</font></td>
								                         <td><s:radio list="#{'Y':'Active','N':'DeActive'}" name="disStatus" id="disStatus" value="%{disStatus==null?'N':disStatus}" cssClass="input2"/></td>
								                    </tr>
								   	  			</table>
							      	  		</td>
							      	  	</tr>
							      	   	<tr>
							      	   		<td align="center" colspan="2">
							      	   			<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'back','')" class="btn" />
								      	   		<s:if test="'new'.equalsIgnoreCase(display)">
													<input type="button" name="sub" value="Insert" onclick="fnsubmit(this.form,'insert','')" class="btn" />
								      	   		</s:if>
												<s:if test="'edit'.equalsIgnoreCase(display)">
													<input type="button" name="sub" value="Update" onclick="fnsubmit(this.form,'update','')" class="btn" />
								      	   		</s:if>
								      	   	</td>
										</tr>
							    	</table>
							    	<br/>
							    <s:hidden name="disId"/>
								</s:form>
							</s:elseif>
							<s:elseif test="'history'.equalsIgnoreCase(display)">
								<s:form name="form1" theme="simple">
							 		<table align="center" width="100%">
								        <tr>
								            <td class="heading" ><s:text name="travel.discountrate"/></td>
								        </tr>
								 		<tr>
								        	<td height="10%">
								        		&nbsp;
								        	</td>
							        	</tr>
							        	<tr><td align="center">
								      	<display:table name="list" pagesize="10"  requestURI="${pageContext.request.contextPath}/relationdiscountTravelConfigure.action" class="table" uid="row" id="record" style="align:center; " cellpadding="1" cellspacing="1"  varTotals="totals" >
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.onepage" value="" />	
										<display:setProperty name="basic.empty.showtable" value="true"/>
										<display:setProperty name="paging.banner.no_items_found" value=""/>												
										<display:column sortable="true" style="text-align:center;" titleKey="SNo" value="${record_rowNum}"/> 
										<display:column sortable="true" style="text-align:center;" title="Discount Start" property="DISCOUNT_START"/>
										<display:column sortable="true" style="text-align:center;" title="Discount End" property="DISCOUNT_END"/>
										<display:column sortable="true" style="text-align:center;" title="Rate Value" property="RATE_VALUE"/>
										<display:column sortable="true" style="text-align:center;" title="Relation Type" property="RELATION_TYPE"/>
										<display:column sortable="true" style="text-align:center;" title="Core App Code" property="COREAPP_CODE"/>
										<display:column sortable="true"	style="text-align:center;" title="Effective Date" property="EFFECTIVE_DATE"/>
										<display:column sortable="true" style="text-align:center;" title="Status" property="STATUS"/>
										</display:table>
										</td></tr>
										<tr><td align="center">
											<s:submit type="button" name="Cancel"  key="close" cssClass="btn" onclick="window.close();return false;"/>&nbsp;
										</td></tr>
										</table>
								</s:form>
							</s:elseif>
						</td>
					</tr>
				</table>
<script type="text/javascript">
function fnsubmit(frm,from,val)
{
if(from == 'edit'){
document.form1.action = "${pageContext.request.contextPath}/relationdiscountTravelConfigure.action?disId="+val+'&display='+from;
}else{
document.form1.action = "${pageContext.request.contextPath}/relationdiscountTravelConfigure.action?display="+from;
}
document.form1.submit();
}
</script>
</div>
</div>
</div>
</div>
	</body>
</html>