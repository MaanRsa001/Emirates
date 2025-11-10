<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
	<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/jquery/jquery-1.11.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/responsive.bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.buttons.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.flash.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.html5.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.print.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.fixedHeader.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.keyTable.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.responsive.min.js"></script>
	
	<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/buttons.bootstrap.min.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/fixedHeader.bootstrap.min.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/responsive.bootstrap.min.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/scroller.bootstrap.min.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
<%-- 	<link href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" rel="stylesheet"/> --%>
<%-- 	<link href="<%=request.getContextPath()%>/cssbootstrap/jquery.min.js" rel="stylesheet"/> --%>
	
        <script>
        
		function formatconstantdetail(cellvalue) {
			//var ss='${pageContext.request.contextPath}/editconstantdetailRating.action?mode=edit&category_detail_id='+cellvalue;
			var ss='${pageContext.request.contextPath}/editconstantdetailRating.action?mode=edit&constantID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function setformatter(cellvalue) {
			return cellvalue;
		}
		function reloadGridScheme() { 
            $.publish('reloadScheme');
        } 
        
		function funcedit(response, postdata){
   			var success = true;
   			var message = ""; 
   			if(response.responseText==''){
            	reloadGridScheme();
            	success = false;
            	message="success";
            }else{
	            var json = eval('(' + response.responseText + ')');
	            if(json.actionErrors){
	            	success = false;
	                for(i=0; i < json.actionErrors.length; i++){
	                	message += json.actionErrors[i] + '<br/>'; 
	                }
	            }
			}
			 return [success,message];
         }
		
         $(function(){
    		$("#transID").change(function(e){
            var param = $(this).val(); 
            alert(param); 

         	});  
 		}); 
         
         jQuery(function ($) {
        		try {
        			var table = $('#gridListTable').DataTable({
        				 ajax: {
        			        url: 'jsonAjaxGridRatingjson.action?menuType=<s:property value="menuType"/>&categoryID=<s:property value="categoryID"/>',
        			        dataSrc: 'gridJsonList'
        			    },
        			    "columns": [
        				  		 <s:if test='menuType=="conveyance"'>
        			                   {"visible": true,"searchable": true,"data":"conveyID"},
        			                   {"visible": true,"searchable": true,"data":"transDesc"},
        			                   {"visible": true,"searchable": true,"data":"conveyName"},
        			                   {"visible": true,"searchable": true,"data":"conveyRate"},
        			                   {"visible": true,"searchable": true,"data":"eff_date"},
        			                   {"visible": true,"searchable": true,"data":"status"},
        			                   {"render" : function(data,type,row,meta){
        			                   		return '<a href="${pageContext.request.contextPath}/editconveyanceRating.action?mode=edit&conveyID='+row.edit+'">Edit</a>'
        			                   },'class': 'text-center'}
      			                </s:if>
      			               <s:elseif test='menuType=="countrymaster"'>
     			                   {"visible": true,"searchable": true,"data":"countryID"},
     			                   {"visible": true,"searchable": true,"data":"countryName"},
     			                   {"visible": true,"searchable": true,"data":"countryNat"},
     			                   {"visible": true,"searchable": true,"data":"eff_date"},
     			                   {"visible": true,"searchable": true,"data":"status"},
     			                   {"render" : function(data,type,row,meta){
     			                   		return '<a href="${pageContext.request.contextPath}/editcountrymasterRating.action?mode=edit&countryID='+row.countryID+'">Edit</a>'
     			                   },'class': 'text-center'}
     			                </s:elseif>
      			              <s:elseif test='menuType=="country"'>
		      			            {"visible": true,"searchable": true,"data":"countryDetID"},
		      			          	{"visible": true,"searchable": true,"data":"countryID"},
			      			        {"visible": true,"searchable": true,"data":"warRate"},
			      			        {"visible": true,"searchable": true,"data":"startPlace"},
			      			    	{"visible": true,"searchable": true,"data":"endPlace"},
			      			    	{"visible": true,"searchable": true,"data":"status"},
			      			    	 {"render" : function(data,type,row,meta){
					                   		return '<a href="${pageContext.request.contextPath}/editcountryRating.action?mode=edit&countryDetID='+row.edit+'">Edit</a>'
					                   },'class': 'text-center'}
     			              </s:elseif>
     			             <s:elseif test='menuType=="bankmaster"'>
		      			        {"visible": true,"searchable": true,"data":"bankID"},
		      			        {"visible": true,"searchable": true,"data":"bankName"},
		      			    	{"visible": true,"searchable": true,"data":"eff_date"},
		      			    	{"visible": true,"searchable": true,"data":"status"},
		      			    	 {"render" : function(data,type,row,meta){
				                   		return '<a href="${pageContext.request.contextPath}/editbankmasterRating.action?mode=edit&bankID='+row.bankID+'">Edit</a>'
			                   	  },'class': 'text-center'}
			              </s:elseif>
			              <s:elseif test='menuType=="materialmaster"'>
	      			        {"visible": true,"searchable": true,"data":"materialID"},
	      			        {"visible": true,"searchable": true,"data":"materialName"},
	      			      	{"visible": true,"searchable": true,"data":"coverName"},
	      			   	 	{"visible": true,"searchable": true,"data":"materialRate"},
	      			    	{"visible": true,"searchable": true,"data":"eff_date"},
	      			    	{"visible": true,"searchable": true,"data":"status"},
	      			    	 {"render" : function(data,type,row,meta){
			                   		return '<a href="${pageContext.request.contextPath}/editmaterialmasterRating.action?mode=edit&materialID='+row.edit+'">Edit</a>'
		                   	  },'class': 'text-center'}
		             		 </s:elseif> 
		             		<s:elseif test='menuType=="commoditymaster"'>
		      			        {"visible": true,"searchable": true,"data":"commodityID"}, 
		      			        {"visible": true,"searchable": true,"data":"commodityName"},
		      			    	{"visible": true,"searchable": true,"data":"eff_date"},
		      			    	{"visible": true,"searchable": true,"data":"status"},
		      			    	 {"render" : function(data,type,row,meta){
				                   		return '<a href="${pageContext.request.contextPath}/editcommoditymasterRating.action?mode=edit&commodityID='+row.commodityID+'">Edit</a>'
			                   	  },'class': 'text-center'}
		             		 </s:elseif>
		             		 
		             		<s:elseif test='menuType=="warratemaster"'>
	      			        {"visible": true,"searchable": true,"data":"warID"},
	      			        {"visible": true,"searchable": true,"data":"warName"},
	      			      	{"visible": true,"searchable": true,"data":"warRate"},
	      			    	{"visible": true,"searchable": true,"data":"transID"},
	      			    	{"visible": true,"searchable": true,"data":"eff_date"},
	      			    	{"visible": true,"searchable": true,"data":"status"},
	      			    	 {"render" : function(data,type,row,meta){
			                   		return '<a href="${pageContext.request.contextPath}/editwarratemasterRating.action?mode=edit&warID='+row.warID+'">Edit</a>'
		                   	  },'class': 'text-center'}
	             		 </s:elseif>
	             		<s:elseif test='menuType=="saletermmaster"'>
	      			        {"visible": true,"searchable": true,"data":"saleID"},
	      			        {"visible": true,"searchable": true,"data":"saleName"},
	      			      	{"visible": true,"searchable": true,"data":"saleValue"},
	      			    	{"visible": true,"searchable": true,"data":"status"},
	      			    	 {"render" : function(data,type,row,meta){
			                   		return '<a href="${pageContext.request.contextPath}/editsaletermmasterRating.action?mode=edit&saleID='+row.saleID+'">Edit</a>'
		                   	  },'class': 'text-center'}
             		 </s:elseif>
             		<s:elseif test='menuType=="tolerancemaster"'>
	  			        {"visible": true,"searchable": true,"data":"toleID"},
	  			        {"visible": true,"searchable": true,"data":"toleName"},
	  			      	{"visible": true,"searchable": true,"data":"toleValue"},
	  			    	{"visible": true,"searchable": true,"data":"status"},
	  			    	 {"render" : function(data,type,row,meta){
		                   		return '<a href="${pageContext.request.contextPath}/edittolerancemasterRating.action?mode=edit&toleID='+row.toleID+'">Edit</a>'
	                   	  },'class': 'text-center'}
		     		 </s:elseif>
		     		<s:elseif test='menuType=="commodityexcess"'>
	  			        {"visible": true,"searchable": true,"data":"comExID"},
	  			        {"visible": true,"searchable": true,"data":"startAmt"},
	  			      	{"visible": true,"searchable": true,"data":"endAmt"},
	  			      	{"visible": true,"searchable": true,"data":"deductible"},
	  			    	{"visible": true,"searchable": true,"data":"comExRate"},
	  			  		{"visible": true,"searchable": true,"data":"eff_date"},
	  			    	{"visible": true,"searchable": true,"data":"status"},
	  			    	 {"render" : function(data,type,row,meta){
		                   		return '<a href="${pageContext.request.contextPath}/editcommodityexcessRating.action?mode=edit&comExID='+row.comExID+'">Edit</a>'
	                   	  },'class': 'text-center'}
	     		 </s:elseif>
	     		<s:elseif test='menuType=="vesselmaster"'>
			        {"visible": true,"searchable": true,"data":"vesselID"},
			        {"visible": true,"searchable": true,"data":"vesselName"},
			      	{"visible": true,"searchable": true,"data":"vesselClass"},
			    	{"visible": true,"searchable": true,"data":"status"},
			    	 {"render" : function(data,type,row,meta){
                   		return '<a href="${pageContext.request.contextPath}/editvesselmasterRating.action?mode=edit&vesselID='+row.edit+'">Edit</a>'
               	  },'class': 'text-center'}
 		 		</s:elseif>
 		 		
 		 		<s:elseif test='menuType=="settlingagent"'>
			        {"visible": true,"searchable": true,"data":"agentID"},
			        {"visible": true,"searchable": true,"data":"agentName"},
			      	{"visible": true,"searchable": true,"data":"countryID"},
			    	{"visible": true,"searchable": true,"data":"status"},
			    	 {"render" : function(data,type,row,meta){
	               		return '<a href="${pageContext.request.contextPath}/editsettlingagentRating.action?mode=edit&agentID='+row.agentID+'">Edit</a>'
	           	  },'class': 'text-center'}
		 		</s:elseif>
		 		<s:elseif test='menuType=="exchangemaster"'> 
			        {"visible": true,"searchable": true,"data":"exchangeID"},
			        {"visible": true,"searchable": true,"data":"currencyID"},
			      	{"visible": true,"searchable": true,"data":"exchangeRate"},
			    	{"visible": true,"searchable": true,"data":"status"},
			    	 {"render" : function(data,type,row,meta){
	               		return '<a href="${pageContext.request.contextPath}/editexchangemasterRating.action?mode=edit&exchangeID='+row.exchangeID+'">Edit</a>'
	           	  },'class': 'text-center'}
	 			</s:elseif>
	 			<s:elseif test='menuType=="currencymaster"'> 
			        {"visible": true,"searchable": true,"data":"currencyID"},
			        {"visible": true,"searchable": true,"data":"currencyType"},
			      	{"visible": true,"searchable": true,"data":"subCurrency"},
			      	{"visible": true,"searchable": true,"data":"currencyShortName"},
			    	{"visible": true,"searchable": true,"data":"status"},
			    	 {"render" : function(data,type,row,meta){ 
	               		return '<a href="${pageContext.request.contextPath}/editcurrencymasterRating.action?mode=edit&currencyID='+row.currencyID+'">Edit</a>'
	           	  },'class': 'text-center'}
 				</s:elseif>
 				<s:elseif test='menuType=="extracover"'> 
			        {"visible": true,"searchable": true,"data":"extraCoverId"},
			        {"visible": true,"searchable": true,"data":"extraCoverName"},
			      	{"visible": true,"searchable": true,"data":"transID"},
			    	{"visible": true,"searchable": true,"data":"status"},
			    	 {"render" : function(data,type,row,meta){
	               		return '<a href="${pageContext.request.contextPath}/editextracoverRating.action?mode=edit&extraCoverId='+row.extraCoverId+'">Edit</a>'
	           	  	},'class': 'text-center'}
				</s:elseif>
				<s:elseif test='menuType=="modeoftransport"'> 
			        {"visible": true,"searchable": true,"data":"transID"},
			        {"visible": true,"searchable": true,"data":"transDesc"},
			    	{"visible": true,"searchable": true,"data":"status"},
			    	 {"render" : function(data,type,row,meta){
	               		return '<a href="${pageContext.request.contextPath}/editmodeoftransportRating.action?mode=edit&transID='+row.transID+'">Edit</a>'
	           	  	},'class': 'text-center'}
				</s:elseif>
				<s:elseif test='menuType=="warrantymaster"'>  
			        {"visible": true,"searchable": true,"data":"warrantyID"},
			        {"visible": true,"searchable": true,"data":"warrantyDesc"}, 
			    	{"visible": true,"searchable": true,"data":"status"},
			    	 {"render" : function(data,type,row,meta){
	               		return '<a href="${pageContext.request.contextPath}/editwarrantymasterRating.action?mode=edit&warrantyID='+row.warrantyID+'">Edit</a>'
	           	  	},'class': 'text-center'}
			</s:elseif>
			<s:elseif test='menuType=="constantMaster"'>  
		        {"visible": true,"searchable": true,"data":"categoryId"},
		        {"visible": true,"searchable": true,"data":"categoryName"},
		    	{"visible": true,"searchable": true,"data":"status"},
		    	 {"render" : function(data,type,row,meta){
	           		return '<a href="${pageContext.request.contextPath}/editconstantMasterRating.action?mode=edit&categoryId='+row.categoryId+'">Edit</a>'
	       	  	},'class': 'text-center'},
		    	{"render" : function(data,type,row,meta){
	           		return '<a href="${pageContext.request.contextPath}/constantdetailRating.action?mode=edit&categoryID='+row.categoryId+'">Add Detail</a>'
	       	  	},'class': 'text-center'} 
			</s:elseif>
			<s:elseif test='menuType=="constantdetail"'>  
		        {"visible": true,"searchable": true,"data":"category_detail_id"},
		        {"visible": true,"searchable": true,"data":"detailName"},
		    	{"visible": true,"searchable": true,"data":"status"},
		    	 {"render" : function(data,type,row,meta){
	           		return '<a href="${pageContext.request.contextPath}/editconstantdetailRating.action?mode=edit&constantID='+row.edit+'">Edit</a>'
	       	  	},'class': 'text-center'}
			</s:elseif>
			
			<s:elseif test='menuType=="exclusionmaster"'>  
		        {"visible": true,"searchable": true,"data":"exclusionID"},
		        {"visible": true,"searchable": true,"data":"exclusionName"},
		    	{"visible": true,"searchable": true,"data":"status"},
		    	 {"render" : function(data,type,row,meta){
	           		return '<a href="${pageContext.request.contextPath}/editexclusionmasterRating.action?mode=edit&exclusionID='+row.exclusionID+'">Edit</a>'
	       	  	},'class': 'text-center'}
		</s:elseif>
		<s:elseif test='menuType=="citymaster"'>
	        {"visible": true,"searchable": true,"data":"cityID"},
	        {"visible": true,"searchable": true,"data":"countryID"},
	        {"visible": true,"searchable": true,"data":"cityName"},
	    	{"visible": true,"searchable": true,"data":"status"},
	    	 {"render" : function(data,type,row,meta){
	       		return '<a href="${pageContext.request.contextPath}/editcitymasterRating.action?mode=edit&cityID='+row.cityID+'">Edit</a>'
	   	  	},'class': 'text-center'}
		</s:elseif>
		<s:elseif test='menuType=="clause"'>
	        {"visible": true,"searchable": true,"data":"clauseID"},
	        {"visible": true,"searchable": true,"data":"transDesc"},
	        {"visible": true,"searchable": true,"data":"coverName"},
	        {"visible": true,"searchable": true,"data":"clauseDesc"},
	    	{"visible": true,"searchable": true,"data":"status"},
	    	 {"render" : function(data,type,row,meta){
	       		return '<a href="${pageContext.request.contextPath}/editclauseRating.action?mode=edit&clauseID='+row.clauseID+'">Edit</a>'
	   	  	},'class': 'text-center'}
		</s:elseif>
		
		<s:elseif test='menuType=="wsrcc"'>
        {"visible": true,"searchable": true,"data":"wsrccid"},
        {"visible": true,"searchable": true,"data":"transDesc"},
        {"visible": true,"searchable": true,"data":"coverName"},
        {"visible": true,"searchable": true,"data":"wsrccdesc"},
    	{"visible": true,"searchable": true,"data":"status"},
    	 {"render" : function(data,type,row,meta){
       		return '<a href="${pageContext.request.contextPath}/editwsrccRating.action?mode=edit&wsrccid='+row.wsrccid+'">Edit</a>'
   	  	},'class': 'text-center'}
	</s:elseif>
	
	<s:elseif test='menuType=="covermaster"'>
	    {"visible": true,"searchable": true,"data":"coverID"},
	    {"visible": true,"searchable": true,"data":"coverName"},
	    {"visible": true,"searchable": true,"data":"coverRate"},
	    {"visible": true,"searchable": true,"data":"transID"},
		{"visible": true,"searchable": true,"data":"status"},
		 {"render" : function(data,type,row,meta){
	   		return '<a href="${pageContext.request.contextPath}/editcovermasterRating.action?mode=edit&coverID='+row.coverID+'">Edit</a>'
		  	},'class': 'text-center'}
	</s:elseif>
	<s:elseif test='menuType=="error"'>
	    {"visible": true,"searchable": true,"data":"errorID"},
	    {"visible": true,"searchable": true,"data":"errorDesc"},
	    {"visible": true,"searchable": true,"data":"stagename"},
	    {"visible": true,"searchable": true,"data":"productname"},
		{"visible": true,"searchable": true,"data":"status"},
		 {"render" : function(data,type,row,meta){
	   		return '<a href="${pageContext.request.contextPath}/editerrorRating.action?mode=edit&errorID='+row.errorID+'">Edit</a>'
		  	},'class': 'text-center'}
	</s:elseif>
	<s:elseif test='menuType=="executivemaster"'>
		{"visible": true,"searchable": true,"data":"exmexecutiveId"},
		{"visible": true,"searchable": true,"data":"executiveName"},
		{"visible": true,"searchable": true,"data":"exmEffectiveDate"},
		{"visible": true,"searchable": true,"data":"exmStatus"},
		 {"render" : function(data,type,row,meta){
				return '<a href="${pageContext.request.contextPath}/editexecutivemasterRating.action?mode=edit&exmexecutiveId='+row.exmexecutiveId+'">Edit</a>'
		  	},'class': 'text-center'}
	</s:elseif>
       			                   ],
       		        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
       				//"order": [[ 1, "desc" ]],
       				"responsive": true,
       				"deferRender": true
       			});
       	    
       		} catch(err){ console.log(err);}
       	});
  
</script>
<style>
* {
    font-size: 13px;
}

  #gridListTable_paginate ul li{
  list-style-type:none;
  display:inline-block;
  padding:10px;	
/*   border-radius:10px; */

  }
  
  #gridListTable_paginate ul li a{
    color:white;
    text-decoration: none;
  }
  
  .dataTable thead th, .dataTable tbody td{
    border:1px solid #ccc;
    padding:15px;
	} 
	.dataTable thead tr {
    background-color: #337ab7;
    color:white;
}

.dataTable thead tr th:first-child{
  border-radius:10px 0px 0px 0px; 
}
.dataTable thead tr th:last-child{
  border-radius:0px 10px 0px 0px;
}

.dataTable tbody tr:last-child{
  border-radius:0px 10px 0px 0px;
}

.paginate_button {
  background-color: #337ab7;
  border: none;
  color: white !important;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

</style>
	</head>
	<body>
	<div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
       <div data-options="region:'west',split:true" title="Options" style="width:190px;">
            <div class="easyui-accordion" style="overflow-y: scroll" data-options="fit:true,border:false" >
                <%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp" %>
            </div>
        </div>
        
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="${menuType}" style="padding:5px">
					<div class="row">
						<div align="right">
							<s:if test='"constantdetail".equalsIgnoreCase(menuType)'>
					   			<a href="edit${menuType}Rating.action?mode=add&categoryID=${categoryID}">Add New</a>
					   		</s:if>
					   		<s:else>
					   			<a href="edit${menuType}Rating.action?mode=add">Add New</a>
					   		</s:else>
						</div>
						<div>
							<table class="table table-striped" width="100%" border="0" cellpadding="4" cellspacing="0" id="gridListTable"> 
								<thead>
									<tr>
										<s:if test='menuType=="conveyance"'>
											<th>CONVEY ID</th>
											<th>MODE OF TRANSPORT</th>
											<th>CONVEY NAME</th>
											<th>CONVEY RATE</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:if>
										<s:elseif test='menuType=="countrymaster"'>
											<th>COUNTRY ID</th>
											<th>COUNTRY NAME</th>
											<th>NATIONALITY NAME</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="country"'>
											<th>COUNTRY ID</th>
											<th>COUNTRY NAME</th>
											<th>WAR RATE</th>
											<th>IMPORT</th>
											<th>EXPORT</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="bankmaster"'>
											<th>BANK ID</th>
											<th>BANK NAME</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										
										<s:elseif test='menuType=="materialmaster"'>
											<th>MATERIAL ID</th>
											<th>MATERIAL NAME</th>
											<th>COVER NAME</th>
											<th>MATERIAL RATE</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="commoditymaster"'>
											<th>COMMODITY ID</th>
											<th>COMMODITY NAME</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="covercommomaster"'>
											<th>CATEGORY</th>
											<th>COVER NAME</th>
											<th>COVER DESCRIPTION</th>
											<th>COVER RATE</th>
											<th>CATEGORY RATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="categorymaster"'>
											<th>CATEGORY ID</th>
											<th>CATEGORY NAME</th>
											<th>CATEGORY RATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="warratemaster"'>
											<th>WAR ID</th>
											<th>WAR DESC</th>
											<th>WAR RATE</th>
											<th>TRANSPORT DESC</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="saletermmaster"'>
											<th>SALE TERM ID</th>
											<th>SALE TERM NAME</th>
											<th>SALE TERM VALUE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="tolerancemaster"'>
											<th>TOLE ID</th>
											<th>TOLERANCE NAME</th>
											<th>TOLERANCE VALUE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="commodityexcess"'>
											<th>COMEX ID</th>
											<th>START AMOUNT</th>
											<th>END AMOUNT</th>
											<th>DEDUCTIBLE</th>
											<th>RATE</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="vesselmaster"'>
											<th>VESSEL ID</th>
											<th>VESSEL NAME</th>
											<th>VESSEL CLASS</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="settlingagent"'>
											<th>AGENT ID</th>
											<th>SETTLING AGENT NAME</th>
											<th>COUNTRY NAME</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="exchangemaster"'>
											<th>EXCHANGE ID</th>
											<th>CURRENCY TYPE</th>
											<th>EXCHANGE RATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="currencymaster"'>
											<th>CURRENCY ID</th>
											<th>CURRENCY TYPE</th>
											<th>SUB CURRENCY</th>
											<th>SHORT NAME</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="extracover"'>
											<th>EXTRA COVER ID</th>
											<th>EXTRA COVER NAME</th>
											<th>MODE OF TRANSPORT</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="modeoftransport"'>
											<th>MODE_TRANSPORT_ID</th>
											<th>MODE OF TRANSPORT</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="warrantymaster"'>
											<th>WARRANTY ID</th>
											<th>WARRANTY DESCRIPTION</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="constantMaster"'>
											<th>CATEGORY ID</th>
											<th>CATEGORY NAME</th>
											<th>STATUS</th>
											<th>EDIT</th>
											<th>ADD DETAIL</th>
										</s:elseif>
										<s:elseif test='menuType=="constantdetail"'>
											<th>CONSTANT ID</th>
											<th>DETAIL NAME</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="exclusionmaster"'>
											<th>EXCLUSION ID</th>
											<th>EXCLUSION NAME</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="citymaster"'>
											<th>CITY ID</th>
											<th>COUNTRY NAME</th>
											<th>CITY NAME</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="wsrcc"'>
											<th>WSRCC ID</th>
											<th>MODE OF TRANSPORT</th>
											<th>COVER</th>
											<th>WSRCC DESCRIPTION</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="covermaster"'>
											<th>COVER ID</th>
											<th>COVER NAME</th>
											<th>COVER RATE</th>
											<th>TRANSPORT DESCRIPTION</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="clause"'>
											<th>CLAUSE ID</th>
											<th>MODE OF TRANSPORT</th>
											<th>COVER</th>
											<th>CLAUSE DESCRIPTION</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="error"'>
											<th>ERROR ID</th>
											<th>ERROR DESCRIPTION</th>
											<th>STAGE NAME</th>
											<th>PRODUCT NAME</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
										<s:elseif test='menuType=="executivemaster"'>
											<th>EXECUTIVE ID</th>
											<th>EXECUTIVE NAME</th>
											<th>EFFECTIVE DATE</th>
											<th>STATUS</th>
											<th>EDIT</th>
										</s:elseif>
									</tr>
								</thead>
							</table>
							<s:if test = '"constantdetail".equalsIgnoreCase(menuType)' >
								<div align="center">
				 					<s:hidden name="categoryID"/>
				 					<input type="button" class="btn" value="Back" onclick="fnCall('constantMaster')"/>
			 					</div>
				 			</s:if>
						</div>
					</div>
		   </div>
            </div>
        </div>
    </div>
    <s:hidden name="transID" id="transID"/>
    <s:hidden name="coverID" id="coverID"/>
    <s:hidden name="menuType" id="menuType"/>
    <s:form name="info" id="info">
    </s:form>
    </body>
</html>