<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
    <head>
    <sj:head jqueryui="true" jquerytheme="start" />
    <SCRIPT language=Javascript>
      
       function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
       function getbaseratesadd(val, id){	
								
					postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?reqFrom='+id+'&searchValue='+val, id);				
			}
       function getbaserates(val, id){		
					postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?reqFrom='+id+'&searchValue='+val, id);				
			}
			<s:if test='%{transID!=null && transID!=""}'>
				postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?reqFrom=baserates&searchValue=<s:property value="transID"/>&coverageID=<s:property value="coverageID"/>','baserates');	 			
	 			//document.info.getElementById('coverageID').value=<s:property value='coverageID'/>;
	 			document.getElementById('coverageID').value=<s:property value='coverageID'/>;	
	 					
	        </s:if>		        
    </SCRIPT>
    <script type="text/javascript" src="js/tcal.js"></script>
        <script type="text/javascript" src="js/common.js"></script>
        <script type="text/javascript" src="pages/admin/ratingEngine/menu.js">
                    </script>   </head>
    <body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:1000px;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Cover Commodity Master" style="padding:5px">
                <s:form id="info" name="info" method="post" action="updatecovercommRating.action" theme="simple">
                    <table width="100%">
                          <tr>
                            <td height="5"></td>
                          </tr>
                          <tr>
                            <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
                              <tr>
                                <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
                                
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td  style="color:red;"><s:actionerror/></td>
                                        </tr>
                                        <tr> <td>&nbsp;&nbsp;</td> </tr>
                                        <tr><td class="heading"><s:text name="covCom.coverCommodityMaster"/></td></tr>
                                        <tr><td>&nbsp;</td></tr>
                                        <tr align="center">
                                                 <td bgcolor="#FFFFFF">
                                                 <table width="100%" border="0" cellspacing="0" cellpadding="0">                                                        
                                                        <tr>
                                                          <td class="bg">
                                                          <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                                                          <s:if test="mode=='edit'">
                                                            <tr>
                                                                <td width="5%">&nbsp;</td>
                                                                <td width="45%"><s:text name="covCom.modeOfTransport"/><font color="red">*</font></td>
                                                                <td width="45%">
                                                                    <s:hidden name="transID" id="transID"></s:hidden>
                                                                    <s:select name="transID" id="transID" list="transports" listKey="MODE_TRANSPORT_ID" listValue="TRANSPORT_DESCRIPTION" headerKey="-1" headerValue="---Select---" disabled="true" cssClass="inputSelect" cssStyle="width: 51%;" />
                                                                </td>
                                                                <td width="5%">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.coverName" /> <font color="red">*</font></td>
                                                                <td>
                                                                	<s:hidden name="coverageID" id="coverageID"></s:hidden>
                                                                	<s:textfield name="coverName" id="coverName" cssClass="inputBox" cssStyle="width:50%;" disabled="true" />
                                                                	<s:hidden name="coverName"/>
                                                                   </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.commodityType" /> <font color="red">*</font></td>
                                                                <td>
                                                                	<s:hidden name="commodityID" id="commodityID"></s:hidden>
                                                                    <s:select name="commodityID" id="commodityID"  list="commcategList" listKey="COMMODITY_TYPEID" listValue="DETAIL_NAME" headerKey="-1" headerValue="---Select---" disabled="true" cssClass="inputSelect" cssStyle="width:50%;"  />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            </s:if>
                                                            <s:else>
                                                            <tr>
                                                                <td width="5%">&nbsp;</td>
                                                                <td width="40%"><s:text name="covCom.modeOfTransport"/><font color="red">*</font></td>
                                                                <td width="50%">
                                                                    <s:select name="transID" id="transID"  list="transports" listKey="MODE_TRANSPORT_ID" listValue="TRANSPORT_DESCRIPTION" headerKey="-1" headerValue="---Select---" onchange="getbaserates(this.value,'baserates');" cssClass="inputSelect" cssStyle="width:51%;" />
                                                                </td>
                                                                <td width="5%">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.coverName" /> <font color="red">*</font></td>
                                                                <td id="baserates">
                                                                    <s:select name="coverageID" id="coverageID" headerKey="-1" headerValue="-- Select --" list="coverages" listKey="COVER_ID" listValue="COVER_NAME" cssClass="inputSelect" cssStyle="width:51%;"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.commodityType" /> <font color="red">*</font></td>
                                                                <td>
                                                                    <s:select name="commodityID" id="commodityID"  list="commcategList" listKey="COMMODITY_TYPEID" listValue="DETAIL_NAME" headerKey="-1" headerValue="---Select---" cssClass="inputSelect" cssStyle="width:51%;"  />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            </s:else>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.categoryRate" /> <font color="red">*</font></td>
                                                                <td>
                                                                    <s:textfield name="catrate" id="catrate" cssClass="inputBox" cssStyle="width:50%;" maxLength="12" onkeypress="return isNumberKey(event)" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.coverRate" /> <font color="red">*</font></td>
                                                                <td>
                                                                    <s:textfield name="coverRate" id="coverRate" cssClass="inputBox" cssStyle="width:50%;" maxLength="6" onkeypress="return isNumberKey(event)" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.effectiveDate" /> <font color="red">*</font></td>
                                                                <td>
                                                                    <div class="inputAppend" style="width:51%;"><sj:datepicker id="eff_date" name="eff_date" cssClass="inputBox1" cssStyle="border: none;background: transparent;width:80%;" displayFormat="mm/dd/yy" changeMonth="true" changeYear="true" readonly="true" showAnim="slideDown" duration="fast" disabled="#disable" /></div>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.referralStatus" /> <font color="red">*</font></td>
                                                                <td>
                                                                    <s:radio name="referralStatus" id="" list="#{'R':'Yes','N':'No'}" value='%{referralStatus==null?"N":referralStatus}'/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>                                                            
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.remarks" /> </td>
                                                                <td>
                                                                    <s:textfield name="remarks" id="remarks" cssClass="inputBox" cssStyle="width:50%;" maxLength="80"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="covCom.status" /> <font color="red">*</font></td>
                                                                <td>
                                                                    <s:radio name="status" id="status" list="#{'Y':'Active','N':'DeActive'}" value='%{status==null?"N":status}'/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4" align="center"><input type="button" class="btn" value="Back" onclick="fnCall('covercommomaster')"/>&nbsp;&nbsp;<s:submit cssClass="btn" value="Save" onclick="return oncheck()"/></td>
                                                            </tr>
                                                         </table></td>
                                                         </tr>
                                                        </table>
                                                      </td>
                                                    </tr>                                                    
                                            </table>
                                        </tr>
                                    </table>                                   
				                  </td>
                                </tr>
                           </table>
                           <s:hidden name="mode"></s:hidden>
                      </s:form>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
function oncheck(){
      if(document.getElementById("statusN").checked){
        	answer=confirm("Are You want to Deactive");
     	if(answer)
     	return true;
     	else
     	return false;
     	}
    else return true;
}

</script>
</html>