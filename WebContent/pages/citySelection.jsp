<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    </head>
	<body>
		<div class="panel panel-primary">
			<div class="panel-body"> 
				<s:if test='countrySelect=="O"'>
					<s:form name="originCitySelection" id="originCitySelection" theme="simple">
						<div class="row">
							<s:iterator value="orgCityList" var="city">
					   			<div class="col-md-4">
					   				<s:radio list="#{city}" name="originCityName"  listKey="CODE" listValue="CODEDESC" title="%{#city.CODEDESC}" onclick="toggleOriginField('')" ></s:radio>
					   			</div>
							</s:iterator>
							<div class="col-md-4">
								<s:radio list="#{'9999':'Others'}" name="originCityName" onclick="toggleOriginField('Others')" title="Others"/><s:textfield id="otherOriginCity" maxlength="120" cssClass="form-control"/>
							</div>
						</div>
						<br/>
						<div class="row" align="center">
				             <button type="button" name="close" class="btn btn-danger btn-oval" data-dismiss="modal">Close</button>
							<button type="button" name="submit" class="btn btn-success btn-oval" onclick="fnOriginSubmit()">Submit</button>
						</div>
						<br/>
						<script type="text/javascript">
							 setOriginValues();
							 function setOriginValues(){
								 try{
								 	var cityId=document.quotation.originCity.value;
								 	var elements=document.originCitySelection.elements;
								 	for ( var int = 0; int < elements.length; int++) {
										var obj=elements[int];
										if(obj.name=='originCityName' && obj.value==cityId){
											obj.checked=true;
										}else if(cityId=="9999"){			
											obj.checked[cityId]=true;
											document.originCitySelection.otherOriginCity.value=document.quotation.originCityName.value
										}
									}
								 }catch(err){
									 console.log(err);
								 }
							 }
						</script>
					</s:form>
				</s:if>
				<s:elseif test='countrySelect=="D"'>
					<s:form name="destCitySelection" id="destCitySelection" theme="simple">
						<div class="row">
							<s:iterator value="destCityList" var="city">
								<div class="col-md-4">
									<s:radio list="#{city}" name="destCityName"  listKey="CODE" listValue="CODEDESC" title="%{#city.CODEDESC}" onclick="toggleDestField('')"></s:radio>
					   			</div>
							</s:iterator>
							<div class="col-md-4">
								<s:radio list="#{'9999':'Others'}" name="destCityName" onclick="toggleDestField('Others')" title="Others"/><s:textfield id="otherDestCity" maxlength="120" cssClass="form-control"/>
							</div>
						</div>
						<br/>
						<div class="row" align="center">
				             <button type="button" name="close" class="btn btn-danger btn-oval" data-dismiss="modal">Close</button>
							 <button type="button" name="submit" class="btn btn-success btn-oval" onclick="fnDestSubmit()">Submit</button>
						</div>
						<br/>
						<script type="text/javascript">
							setDestValues();
							 function setDestValues(){
								 try{
								 	var cityId=document.quotation.destCity.value;
								 	var elements=document.destCitySelection.elements;
								 	for ( var int = 0; int < elements.length; int++) {
										var obj=elements[int];
										if(obj.name=='destCityName' && obj.value==cityId){
											obj.checked=true;
										}else if(cityId=="9999"){			
											obj.checked[cityId]=true;
											document.destCitySelection.otherDestCity.value=document.quotation.destCityName.value
										}
									}
								 }catch(err){
									 console.log(err);
								 }
							 }
						</script>
					</s:form>
				</s:elseif>
			</div>
		</div>
	</body>
</html>
