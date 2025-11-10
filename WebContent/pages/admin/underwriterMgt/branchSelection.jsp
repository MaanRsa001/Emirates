<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class=" js  -webkit-">
	<head>
		<s:head/>
		<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript">	 
	
    </script>
		
	
	<style type="text/css">
		.textfield {
			height: 50px;
		}
		
		.ui-datepicker-trigger{
			float:left;
			width:10%;
			padding: 0;
			margin-left: 5px;
		}
		
		input[type=button] {
		    color: #fff;
			border: none;	
			background-color: #177de3;	
			padding: 5px;
		    cursor:pointer;
		    font-weight: bold;
		    
		    border-top-right-radius: 5px;
			border-top-left-radius: 5px;
			border-bottom-right-radius: 5px;
			border-bottom-left-radius: 5px;
			
			-moz-border-radius: 5px;
			-khtml-border-radius: 5px;
			-webkit-border-radius: 5px;
			border-radius: 5px;
		}
	</style>
</head>

<body>
	<div id="">
		<div id="">		
	   		<s:form id="enqUwSelection" name="enqUwSelection" method="post" action="" theme="simple">
	   		<div class="table">
	   			<div class="tablerow" style="margin-top: 10px;">
	   				<div class="disBorder rEdge">
					    <div class="botBorder" align="center">
					   		<span class="heading">				    			
								Branch Selection
					   		</span>
					   	</div>	
				    	<div class="dataPad" >
		    				<div class="boxcontent" align="center">
								<div id="uwList">
									<s:optiontransferselect
										 label="Branch List"
									     name="leftBranches"
									     leftTitle="UnderWriter Available"
									     rightTitle="UnderWriter Selected"
									     list="leftBranchList"
									     listKey="BRANCH_CODE"
				    					 listValue="BRANCH_NAME"
									     multiple="true"
									     headerKey="-1"
									     headerValue="--- Please Select ---"
									     doubleList="rightBranchList"
									     doubleListKey="BRANCH_CODE"
   									     doubleListValue="BRANCH_NAME"
									     doubleName="rightBranches"									     
									     doubleHeaderKey="-1"
									     doubleHeaderValue="--- Please Select ---"
									 />
								</div>
								<div align="center">
									<input type="button" class="iButton rEdge" value=" Back " onclick="window.close();return false;" />
									&nbsp;&nbsp;&nbsp;
									<input type="button" name="Submit2"  class="iButton rEdge" value=" Submit " onclick="fnsubmit();"/>
								</div>	
			   				</div>
		  				</div>
					</div>
	   			</div>  			
	   		</div>
	   		</s:form>	
	   		<s:hidden name="mode"/>
		</div>
	</div>	
	
	<script>
	
	function fnsubmit(){
		var checkedItems='';
		var branchNames='';
		try{
			var elements=document.enqUwSelection.rightBranches;
			for(i=1; i<elements.length; i++){
				obj=elements[i];
				checkedItems+=obj.value+',';
				branchNames+=obj.text+',';
			}
		}catch(e){}
		if(checkedItems.length>1)
		{
			checkedItems=checkedItems.substring(0, checkedItems.length-1);
			window.opener.underwriter.branchSelected.value=checkedItems;
			window.opener.underwriter.branchNames.value=branchNames;
	   		window.close();
		}
		else
		{
		    alert('Please Select Branch');
		}	
		
	}
	</script>
	
</body>
	
</html>