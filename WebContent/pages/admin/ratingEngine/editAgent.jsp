<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
    <head>
    <script language="JavaScript">
        function stopRKey(evt) { 
          var evt = (evt) ? evt : ((event) ? event : null); 
          var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
          if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
        } 
        document.onkeypress = stopRKey; 
</script>
    <SCRIPT language=Javascript>
      
       function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
       function callCity(val,id)
       {
         postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?countryID='+val+'&reqFrom='+id, id);
       }
       
    </SCRIPT>
        <script type="text/javascript" src="pages/admin/ratingEngine/menu.js">
                    </script>   </head>
    <body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" style="overflow-y: scroll" data-options="fit:true,border:false" >
                <%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Settling Agent" style="padding:5px">
                <s:form id="info" name="info" method="post" action="updateAgentRating.action" theme="simple">
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
                                        <tr><td class="heading"><s:text name="rating.mode.agent"/></td></tr>
                                        <tr><td>&nbsp;</td></tr>
                                        <tr align="center">
                                                 <td bgcolor="#FFFFFF">
                                                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                        
                                                        <tr>
                                                          <td class="bg">
                                                          <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="country.name"/><font color="red">*</font></td>
                                                                <td>
                                                                    <s:select name="countryID" cssClass="inputSelect" cssStyle="width:51%;" id="countryID" list="countryList" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" headerKey="-1" headerValue="-Select-" onchange="callCity(this.value,'cityIDs')"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.name"/><font color="red">*</font></td>
                                                                <td>
                                                                    <s:textfield name="agentName" cssClass="inputBox" cssStyle="width:50%;" maxlength="75" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.shortname"/></td>
                                                                <td>
                                                                    <s:textfield name="shortName" cssClass="inputBox" cssStyle="width:50%;" maxlength="75" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.address1"/></td>
                                                                <td>
                                                                    <s:textfield name="address1" cssClass="inputBox" cssStyle="width:50%;" maxlength="75"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.address2"/></td>
                                                                <td>
                                                                    <s:textfield name="address2" cssClass="inputBox" cssStyle="width:50%;" maxlength="100"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.address3"/></td>
                                                                <td>
                                                                    <s:textfield name="address3" cssClass="inputBox" cssStyle="width:50%;" maxlength="75"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="citi.name"/><font color="red">*</font></td>
                                                                <td>
                                                                <s:textfield name="cityName" cssClass="inputBox" cssStyle="width:50%;" maxlength="100" onkeyup="allLetter(this);"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.zipcode"/></td>
                                                                <td>
                                                                    <s:textfield name="zipCode" cssClass="inputBox" cssStyle="width:50%;" maxlength="15" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.teleNo"/></td>
                                                                <td>
                                                                    <s:textfield name="teleNo" cssClass="inputBox" cssStyle="width:50%;" maxlength="25" onkeyup="checkMobileNo(this)"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.faxNo"/></td>
                                                                <td>
                                                                    <s:textfield name="faxNo" cssClass="inputBox" cssStyle="width:50%;" maxlength="25" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.mobileNo"/></td>
                                                                <td>
                                                                    <s:textfield name="mobileNo" cssClass="inputBox" cssStyle="width:50%;" maxlength="25" onkeyup="checkMobileNo(this)"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="agent.email"/></td>
                                                                <td>
                                                                    <s:textfield name="email" cssClass="inputBox" cssStyle="width:50%;" maxlength="35" />
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="rating.remark"/></td>
                                                                <td>
										<!--<s:textfield name="remarks" cssClass="inputBox" cssStyle="width:50%;" size="35" value=""/>
										  
                                                                -->
                                                                <s:textfield name="remarks" cssClass="inputBox" cssStyle="width:50%" maxlength="80"> </s:textfield>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="rating.core.code"/><font color="red">*</font></td>
                                                                <td>
                                                                    <s:textfield name="code" cssClass="inputBox" cssStyle="width:50%;" maxlength="10" onkeypress="return checkAlphaNumeric(event)"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="rating.status"/><font color="red">*</font></td>
                                                                <td> 
                                                                    <s:hidden name="agentID"/>
                                                                    <s:radio name="status" id="status" list="#{'Y':'Active','N':'DeActive'}" value='%{status==null?"N":status}'/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4" align="center">
                                                                    <input type="button" class="btn" value="Back" onclick="fnCall('settlingagent')"/>&nbsp;&nbsp;<s:submit value="Save" cssClass="btn" onclick="return oncheck()"/>
                                                                </td>
                                                            </tr>
                                                         </table></td>
                                                         </tr>
                                                        </table>
                                                      </td>
                                                    </tr>
                                        </table>
                                        </tr>
                                    </table>
                                    <s:hidden name="agencyCode"/>
                                    <s:hidden name="login_Id"/>
                                    <s:hidden name="borganization"/>
                                    <s:hidden name="bcode"/>
                                    <s:hidden name="mode" id="mode"/>
                                   

                                </td>
                                </tr>
                                </table>
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

