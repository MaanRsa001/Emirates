<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
    <head>
    	<sj:head jqueryui="true" jquerytheme="start" />
		<SCRIPT language=Javascript>
			function isNumberKey(evt) {
				var charCode = (evt.which) ? evt.which : event.keyCode;
				if (charCode != 46 && charCode > 31 
				&& (charCode < 48 || charCode > 57))
				return false;
				return true;
			}

		</SCRIPT>
		<script language="JavaScript">
			function stopRKey(evt) { 
				var evt = (evt) ? evt : ((event) ? event : null); 
				var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
		</script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/tcal.js"></script>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js">
		</script>		
	</head>
	<body>
	<div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:1000px;">
		<div data-options="region:'west',split:true" title="Options" style="width:150px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<%@ include file="/pages/admin/motor/motormenu.jsp" %>
			</div>
		</div>
		<div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Area Coverage" style="padding:5px">
					<s:form name="info" method="post" theme="simple">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						<tr>
							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
								<table style="width: 700px;" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td  style="color:red;"><s:actionerror/></td>
									</tr>
									<tr>
										<td class="heading"><s:text name="adminMaster.motorAreaCoverageMaster"/></td>
									</tr>
									<tr>
										<td>
											<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
											<tr>
												<td class="tCols1">
													<div class="text">
														<s:text name="adminMaster.areaNameInEnglish"/><font color="red">*</font>
													</div>
												</td>
												<td class="tCols1">
													<div class="tbox">
														<s:textarea name="areaDesc" cssClass="inputBoxA" cssStyle="width:75%;" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="tCols25">
													<div class="text">
														<s:text name="adminMaster.areaNameInArabic"/>
													</div>
												</td>
												<td class="tCols25">
													<div class="tbox">
														<s:textarea name="arabic" cssClass="inputBoxA" cssStyle="width:75%;" />
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="text">
														<s:text name="adminMaster.areaCode"/><font color="red">*</font>
													</div>
												</td>
												<td>
													<div class="tbox">
														<s:textfield name="areaCode" cssClass="inputBox" cssStyle="width:76%;" />
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="text">
														<s:text name="adminMaster.effectiveDate"/><font color="red">*</font>
													</div>
												</td>
												<td>
													<div class="tbox">
														<div class="inputAppend" style="width: 77%;">
															<sj:datepicker name="eff_date" cssClass="inputBox1" cssStyle="border: none;background: transparent;" displayFormat="dd/mm/yy" readonly="true" showAnim="slideDown" duration="fast" disabled="#disable" />
															
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="text">
														<s:text name="adminMaster.status"/><font color="red">*</font>
													</div>
												</td>
												<td>
													<div class="tbox">
														<s:radio name="status" list="#{'Y':'Active','N':'DeActive'}" value='%{status==null?"N":status}'/>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="text">
														<s:text name="adminMaster.remarks"/>
													</div>
												</td>
												<td>
													<div class="tbox">
														<div class="">
															<s:textarea name="remarks" cssClass="inputBoxA" cssStyle="width:75%;"/>												
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="4" align="center">										                                                           
													<input type="button" class="btn" value="Back" onclick="fnCall('area')"/>&nbsp;&nbsp;<s:submit value="Save" cssClass="btn" action="updateareaMotorAdmin"/>										                
												</td>
											</tr>
											
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<s:hidden name="mode"></s:hidden>
					<s:hidden name="areaID"></s:hidden>
					</s:form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>   
