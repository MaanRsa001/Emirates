<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>

    <title>My JSP 'editExecutiveMaster.jsp' starting page</title>
    
	<SCRIPT language=Javascript>
      
       function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
       
       function fnSub(value){
    		document.info.action = value;
    		document.info.submit();
    	}
	     
    </SCRIPT>
    <style type="text/css">
    	.inputBox {
    		width: 50%;
    	}
    	.inputAppend {
    		width: 50%;
    		padding-left: 5px;
    	}
    	.inputSelect {
    		width: 51%;
    	}
    </style>
 <script>
	function getList(val, id)
   {
    	postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?exmRegion='+val+'&reqFrom='+id, id);
    }
</script>
    <script language="JavaScript">
        function stopRKey(evt) { 
          var evt = (evt) ? evt : ((event) ? event : null); 
          var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
          if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
        } 
        document.onkeypress = stopRKey; 
</script>
<script type="text/javascript" src="js/tcal.js">
                    </script>
        <script type="text/javascript" src="pages/admin/ratingEngine/menu.js">
                    </script>   
  </head>
  
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
                <div title="Sales Executive Master" style="padding:5px">
                <s:form id="info" name="info" method="post" action="saveexecutivemasterRating.action" theme="simple">
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
                                        <tr><td class="heading"><s:text name="exm.executivemaster"/></td></tr>
                                        <tr><td>&nbsp;</td></tr>
                                        <tr align="center">
											<td bgcolor="#FFFFFF">
                                                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                        
                                                        <tr>
                                                          <td class="bg">
                                                          <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="exm.executivename"/><span style="color: red;">*</span></td>
                                                                <td>
                                                                    <s:textfield name="executiveName" cssClass="inputBox" maxlength="50"></s:textfield>
                                                                <s:if test='editType == "edit"'>																					
																		<s:hidden name="executiveName" />
																</s:if>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="exm.oACode"/></td>
                                                                <td>
																<%--<s:if test='mode == "edit"'>
																<s:textfield name="otherpartyCode" cssClass="inputBox" onkeypress="return checkAlphaNumeric(event)" maxlength="25" disabled="true"></s:textfield>																					
																		<s:hidden name="otherpartyCode" />
																</s:if>
																<s:else>
																--%>
																<s:textfield name="otherpartyCode" cssClass="inputBox" onkeypress="return checkAlphaNumeric(event)" maxlength="25"></s:textfield> 
																<%--</s:else>--%>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="exm.commission"/></td>
                                                                <td>
                                                                    <s:textfield name="exmCommission" id="exmCommission" cssClass="inputBox" size="6" onkeyup="checkDecimal2(this);"/>
                                                                </td>
                                                                <td>&nbsp;</td>                                                                
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="exm.openCoverCommission"/></td>
                                                                <td>
                                                                    <s:textfield name="exmOpenCoverCommission" id="exmOpenCoverCommission" cssClass="inputBox" size="6" onkeyup="checkDecimal2(this);"/>
                                                                </td>
                                                                <td>&nbsp;</td>                                                                
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="exm.effectiveDate"/><span style="color: red;">*</span></td>
                                                                <td>
                                                                    <s:textfield name="exmEffectiveDate" id="exmEffectiveDate" cssClass="easyui-datebox" size="39"/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="exm.status"/><span style="color: red;">*</span></td>
                                                                <td>
                                                                    <s:radio name="exmStatus" id="exmStatus" list="#{'Y':'Active','N':'DeActive'}" value='%{exmStatus==null?"N":exmStatus}'/>
                                                                </td>
                                                                <td>&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td><s:text name="exm.remarks"/></td>
                                                                <td>
                                                                    <s:textfield name="exmRemarks" id="exmRemarks" cssClass="inputBox" size="100"/>
                                                                </td>
                                                                <td>&nbsp;</td>                                                                
                                                            </tr>
                                                            <tr>
                                                                <td colspan="4" align="center">
                                                                <input type="button" class="btn" value="Back" onclick="fnCall('executivemaster')"/>&nbsp;&nbsp;
<%--                                                                 <s:submit cssClass="btn" value="Save"  onclick="return oncheck()"/> --%>
                                                                <s:submit value="Save" cssClass="btn" onclick="return oncheck()"/>
<!--                                                                 <input type="button" class="btn" onchange="fnSub('saveexecutivemasterRating.action')" value="Save"  /> -->
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
                                </td>
                                </tr>
                                </table>
                                <s:hidden name="editType" id="editType"/>
                                <s:hidden name="mode" id="mode"/>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
  </body>
  <script type="text/javascript">
  
function oncheck(){
      if(document.getElementById("exmStatusN").checked){
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