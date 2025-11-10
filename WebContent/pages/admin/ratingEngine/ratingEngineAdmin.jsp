<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
	<head>

		
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:727px;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false"><table>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="conveyanceRatingEngine.action" target="cen"><font color="#ffffff">Conveyance Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="countrymasRatingEngine.action" target="cen"><font color="#ffffff">Country Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="countryRatingEngine.action" target="cen"><font color="#ffffff">Country</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="commodityRatingEngine.action" target="cen"><font color="#ffffff">Commodity</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="bankRatingEngine.action" target="cen"><font color="#ffffff">Bank Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="materialRatingEngine.action" target="cen"><font color="#ffffff">Material Type Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="warrateRatingEngine.action" target="cen"><font color="#ffffff">War Rate Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="saletermRatingEngine.action" target="cen"><font color="#ffffff">Sale Term Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="toleranceRatingEngine.action" target="cen"><font color="#ffffff">Tolerance Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="commodityexcessRatingEngine.action" target="cen"><font color="#ffffff">Commodity Excess</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="vesselRatingEngine.action" target="cen"><font color="#ffffff">Vessel Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="settingRatingEngine.action" target="cen"><font color="#ffffff">Setting Agent</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="exchangeRatingEngine.action" target="cen"><font color="#ffffff">Exchange Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a href="uploadRating.action?menuType=exchangeUpload&reqFrom=" target="cen"><font color="#ffffff">Exchange Master Upload</font></a></td></tr>
           <%-- <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="baserateRatingEngine.action" target="cen"><font color="#ffffff">Base Rate</font></a></td></tr> --%>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="currencyRatingEngine.action" target="cen"><font color="#ffffff">Currency Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="extracoverRatingEngine.action" target="cen"><font color="#ffffff">Extra Cover</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="transportRatingEngine.action" target="cen"><font color="#ffffff">Mode of Transport</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="warrantyRatingEngine.action" target="cen"><font color="#ffffff">Warranty Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="constantRatingEngine.action" target="cen"><font color="#ffffff">Constant Detail</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="exclusionRatingEngine.action" target="cen"><font color="#ffffff">Exclusion Master</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="cityRatingEngine.action" target="cen"><font color="#ffffff">City</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="clauseidRatingEngine.action" target="cen"><font color="#ffffff">Clause ID</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="coverRatingEngine.action" target="cen"><font color="#ffffff">Cover</font></a></td></tr>
            <tr class="btntab" ><td style="width:150px;height:25px;" align="center"><a  href="errorRatingEngine.action" target="cen"><font color="#ffffff">Error</font></a></td></tr>
            
            
            </table>
                
                
                
            </div>
        </div>
        <div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
           <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
            <iframe frameborder="0" name="cen" width="700px" height="700px" id="cen"></iframe>
            </div>
        </div>
    </div>
</body>
</html>

