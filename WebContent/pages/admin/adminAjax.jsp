<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<s:if test='"brokerLists".equals(reqFrom)'>
    <display:table name="brokerList" pagesize="10" requestURI="/getABListBrokerMgm.action" class="table" uid="row" id="record">
            <display:setProperty name="paging.banner.one_item_found" value="" />
            <display:setProperty name="paging.banner.one_items_found" value="" />
            <display:setProperty name="paging.banner.all_items_found" value="" />
            <display:setProperty name="paging.banner.some_items_found" value="" />
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="paging.banner.onepage" value="" />
            <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
            <display:column sortable="true" style="text-align:left;font-size:13px;" title="Broker Name">
                ${record.COMPANY_NAME}&nbsp;(${record.AGENCY_CODE})
            </display:column>
            <display:column sortable="true" style="text-align:left;font-size:13px;"  title="Broker Code" property="RSA_BROKER_CODE" />
            <display:column sortable="true" style="text-align:left;font-size:13px;" title="Login Id" property="LOGIN_ID" />
            <display:column sortable="true" style="text-align:left;font-size:13px;" title=" CreatedDate " property="cr_date" />
            <display:column sortable="true" style="text-align:left;font-size:13px;" title="More">
                <a href="viewBrokerMgm.action?mode=edit&agencyCode=${record.AGENCY_CODE}">More</a>
            </display:column>
            <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
    </display:table>
</s:if>
<s:elseif test='"coverLists".equals(reqFrom)'>
<display:table name="coverList" pagesize="10" requestURI="/getCOVListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COVER NAME" property="COVER_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COVER RATE" property="COVER_RATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="MODE OF TRANSPORT" property="TRANSPORT_DESCRIPTION" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editCoverRating.action?coverID=${record.COVER_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                     </display:table>
</s:elseif>
<s:elseif test='"constantLists".equals(reqFrom)'>
<display:table name="constantList" pagesize="10" requestURI="/getCONListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="DETAIL NAME" property="DETAIL_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CATEGORY DETAIL ID" property="CATEGORY_DETAIL_ID" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editConstantRating.action?constantID=${record.CATEGORY_ID}&category_detail_id=${record.CATEGORY_DETAIL_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                                
</s:elseif>
<s:elseif test='"exchangeLists".equals(reqFrom)'>
<display:table name="exchangeList" pagesize="10" requestURI="/getEXListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CURRENCY TYPE" property="CURRENCY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EXCHANGE RATE" property="EXCHANGE_RATE" />
                                                    
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editExchangeRating.action?exchangeID=${record.EXCHANGE_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                     </display:table>
</s:elseif>
<s:elseif test='"currencyLists".equals(reqFrom)'>

                                                    <display:table name="currencyList" pagesize="10" requestURI="/getCURListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CURRENCY NAME" property="CURRENCY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="SUB CURRENCY" property="SUB_CURRENCY" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="SHORT NAME" property="SHORT_NAME" />
                                                    
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editCurrencyRating.action?currencyID=${record.CURRENCY_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                     </display:table>

</s:elseif>
<s:elseif test='"countryDetLists".equals(reqFrom)'>
<display:table name="countryDetList" pagesize="10" requestURI="/getCDListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COUNTRY NAME" property="COUNTRY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="WAR RATE" property="WAR_RATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EXPORT" property="STARTING_PLACE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="IMPORT" property="ENDING_PLACE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editCountryDetRating.action?countryDetID=${record.COUNTRY_ID}&countryDetSNO=${record.SNO__}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"vesselLists".equals(reqFrom)'>
<display:table name="vesselList" pagesize="10" requestURI="/getVListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="VESSEL NAME" property="VESSEL_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="VESSEL CLASS"  property="VESSEL_CLASS" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editVesselRating.action?vesselID=${record.VESSEL_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                        
</s:elseif>
<s:elseif test='"warrantyLists".equals(reqFrom)'>
<display:table name="warrantyList" pagesize="10" requestURI="/getWRListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="WARRANTY DESCRIPTION" property="WARRANTY_DESCRIPTION" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editWarrantyRating.action?warrantyID=${record.WARRANTY_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                                
</s:elseif>
<s:elseif test='"toleranceLists".equals(reqFrom)'>
<display:table name="toleranceList" pagesize="10" requestURI="/getToleListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="TOLERANCE NAME" property="TOLERANCE_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="TOLERANCE VALUE"  property="TOLERANCE_VALUE" />
                                                    
                                                    
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editToleRating.action?toleID=${record.TOLERANCE_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                    
</s:elseif>
<s:elseif test='"comExLists".equals(reqFrom)'>
<display:table name="comExList" pagesize="10" requestURI="/getCEListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="START AMOUNT" property="START_SUM_INSURED" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="END AMOUNT"  property="END_SUM_INSURED" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="DEDUCTIBLE"  property="DEDUCTIBLE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="RATE"  property="RATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EFFECTIVE DATE"  property="EFFECTIVE_DATE" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editComExRating.action?comExID=${record.SNO}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"saletermLists".equals(reqFrom)'>
<display:table name="saletermList" pagesize="10" requestURI="/getSaleListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="SALE DESC" property="SALE_TERM_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="SALE RATE"  property="SALE_TERM_VALUE" />
                                                    
                                                    
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editSaleRating.action?saleID=${record.SALE_TERM_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"warrateLists".equals(reqFrom)'>
<display:table name="warrateList" pagesize="10" requestURI="/getWListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="WAR DESC" property="WAR_DESC" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="WAR RATE"  property="WAR_RATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="TRANSPORT DESC"  property="TRANSPORT_DESCRIPTION" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EFFECTIVE DATE"  property="EFFECTIVE_DATE" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editWarRating.action?warID=${record.WAR_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"bankLists".equals(reqFrom)'>
                                                
                                                    <display:table name="bankList" pagesize="10" requestURI="/getBListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="BANK NAME" property="BANK_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EFFECTIVE DATE"  property="EFFECTIVE_DATE" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editBankRating.action?bankID=${record.BANK_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                                                  
                                                  
</s:elseif>
<s:elseif test='"materialLists".equals(reqFrom)'>

                                                    <display:table name="materialList" pagesize="10" requestURI="/getMListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="MATERIAL NAME" property="MATERIAL_DESC" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EFFECTIVE DATE"  property="EFFECTIVE_DATE" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editMaterialRating.action?materialID=${record.MATERIAL_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"countryLists".equals(reqFrom)'>
<display:table name="countryList" pagesize="20" requestURI="/getCListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COUNTRY NAME"  property="COUNTRY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="NATIONALITY NAME" property="NATIONALITY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EFFECTIVE DATE" property="EFFECTIVE_DATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editCountryRating.action?countryID=${record.COUNTRY_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"conveyanceLists".equals(reqFrom)'>

                                                    <display:table name="conveyanceList" pagesize="20" requestURI="/getConveyListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CONVEYANCE NAME" property="CONVDESC" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EFFECTIVE DATE"  property="EFFECTIVE_DATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CONVEYANCE RATE" property="CONV_RATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editConveyanceRating.action?conveyID=${record.CONV_MEAN}&modeTransportId=${record.MODE_TRANSPORT_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                                                 
</s:elseif>
<s:elseif test='"transportLists".equals(reqFrom)'>
<display:table name="transportList" pagesize="10" requestURI="/getTListRatingEngine.action" class="table" uid="row" id="record">
<display:setProperty name="paging.banner.one_item_found" value="" />
<display:setProperty name="paging.banner.one_items_found" value="" />
<display:setProperty name="paging.banner.all_items_found" value="" />
<display:setProperty name="paging.banner.some_items_found" value="" />
<display:setProperty name="paging.banner.placement" value="bottom" />
<display:setProperty name="paging.banner.onepage" value="" />


                                                    
                                
<display:column sortable="true" style="text-align:left;font-size:13px;"  title="TRANSPORT ID" property="MODE_TRANSPORT_ID" />
<display:column sortable="true" style="text-align:left;font-size:13px;" title="MODE OF TRANSPORT" property="TRANSPORT_DESCRIPTION" />
<display:column sortable="true" style="text-align:left;font-size:13px;" title="CORE APP CODE" property="RSACODE" />
<display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
<a href="editTransportRatingEngine.action?transID=${record.MODE_TRANSPORT_ID}">Edit</a>
</display:column>
<display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
</display:table>
                                              
</s:elseif>
<s:elseif test='"baserates".equals(reqFrom)'>
<td>
<s:select name="coverageID" id="coverageID" cssClass="inputSelect" headerKey="-1" headerValue="-- Select --" list="coverages" listKey="COVER_ID" listValue="COVER_NAME"/>
</td>                                 
</s:elseif>                                                            
<s:elseif test='"baserates1".equals(reqFrom)'>
<td>
<s:select name="coveyID" id="coveyID" cssClass="inputSelect" headerKey="-1" headerValue="-- Select --" list="covey" listKey="CONV_MEAN" listValue="CONVDESC" />
</td>
</s:elseif>
<s:elseif test='"commodityLists".equals(reqFrom)'>
<display:table name="commodityList" pagesize="10" requestURI="/getComListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COMMODITY NAME" property="COMMODITY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="AMEND NO"  property="AMEND_ID" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EFFECTIVE DATE"  property="EFFECTIVE_DATE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editCommodityRating.action?commodityID=${record.COMMODITY_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                                
</s:elseif>
<s:elseif test='"agentLists".equals(reqFrom)'>
<display:table name="agentList" pagesize="10" requestURI="/getSAgentListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="SETTLING AGENT NAME" property="SETTLING_AGENT_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COUNTRY NAME" property="COUNTRY_NAME" />
                                                     <display:column sortable="true" style="text-align:left;font-size:13px;" title="CITY NAME" property="CITY_NAME" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editAgentRating.action?agentID=${record.SETTLING_AGENT_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"cityIDs".equals(reqFrom)'>
<td id="cityIDs">
<s:select name="cityID" cssClass="inputSelect" id="cityID" list="cityList" listKey="CITY_ID" listValue="CITY_NAME" headerKey="-1" headerValue="-Select-"/>
</td>

</s:elseif>
<s:elseif test='"stagename".equals(reqFrom)'>

<s:select name="stagename" headerKey="stagename" id="stagename" cssClass="inputSelect" headerValue="--Select--" list="stagenames" listKey="STAGE_ID" listValue="STAGE_DESC"/>

</s:elseif>
<s:elseif test='"covername".equals(reqFrom)'>
<td>
<s:select name="covername" id="covername" listKey="COVER_ID" cssClass="inputSelect" listValue="COVER_NAME" list="coverNames" headerKey="-1" headerValue="-- Select --"/>
</td>
</s:elseif>
<s:elseif test='"errorLists".equals(reqFrom)'>
<display:table name="errorList" pagesize="10" requestURI="/getERRListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    
                                
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="ERROR DESCRIPTION" property="ERROR_DESC" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="STAGE NAME"  property="STAGE_DESC" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="PRODUCT NAME" property="PRODUCT_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editErrorRating.action?errorID=${record.ERROR_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"clauseIDLists".equals(reqFrom)'>
<display:table name="clauseIDList" pagesize="10" requestURI="/getClauseIDListRatingEngine.action" class="table" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CLAUSES_DESCRIPTION"  property="CLAUSES_DESCRIPTION" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COVER NAME"  property="COVER_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editClauseIDRating.action?clauseID=${record.CLAUSES_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"productselections".equals(reqFrom)'>
    <s:form name="productSelect" theme="simple">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background: #ffffff;">    		
    		<s:if test='"newAjax".equals(mode1)'>
    			<tr>
                    <td class="heading"><s:text name="broker.new.product" /></td>
                </tr>
                <tr height="5"><td></td></tr>
                <tr>
                	<td class="bg" align="center">
                		<table width="50%">
	                		<tr>
	                			<td width="50%"><s:text name="product.product"/>&nbsp;&nbsp;&nbsp;:</td>
	                			<td width="50%"><s:select name="productID" id="prodID" list="productList" headerKey="" cssClass="inputSelect" headerValue="-Select-" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onchange="productsel(this.value)"/></td>
	                		</tr>
	                	</table>
                	</td>
                </tr>
    		</s:if>
    		<s:else>
    			<tr>
                     <td class="heading"><s:text name="broker.modify.product" /></td>
                </tr>
                <tr height="5"><td></td></tr>
                <tr>
                	<td align="center"> <s:text name="product.product"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<s:property value="productName"/></td>
                </tr>
                    <s:hidden name="productName"/>
                    <s:hidden name="productID"/>
    		</s:else>
    		<s:if test='%{"newAjax".equals(mode1) && productList.size==0}'>
                <tr><td align="center"><s:text name="label.no.more.product"/></td></tr>
            </s:if>
            <s:if test='"11".equals(productID)'>
            	<%--
            	<tr>
                    <td class="heading"><s:text name="broker.provision.details" /></td>
                </tr>
                --%>
                <tr height="5"><td></td></tr>                
                <tr>
                	<td class="bg">
						<table width="100%">
						<%--
						<tr>
							<td width="5%">&nbsp;</td>
							<td width="30%" >
								<s:label key="product.provision" /> <font color="red">*</font><br/>
								<s:radio name="user_Id_Creation" list="#{'Y':'Yes','N':'No'}" value='%{user_Id_Creation==null?"N":user_Id_Creation}'/>
							</td>
							<td width="30%">
								<s:label key="product.paymentrequired" /> <font color="red">*</font><br/>
								<s:radio name="payReceipt" list="#{'Y':'Yes','N':'No'}" value='%{payReceipt==null?"N":payReceipt}'/>
							</td>
							<td width="30%">
								<s:label key="product.provisiondebit" /> <font color="red">*</font><br/>
								<s:radio name="freight" list="#{'Y':'Yes','N':'No'}"  value='%{freight==null?"N":freight}'/>
							</td>
							<td width="5%">&nbsp;</td>
						</tr>
						<tr height="5"><td colspan="5"></td></tr>
						<tr>
							<td></td>
							<td>
								<s:label key="product.provisionloading" /> <font color="red">*</font><br/>
								<s:radio name="provision" list="#{'Y':'Yes','N':'No'}" value='%{provision==null?"N":provision}'/>
							</td>
							<td>
								<s:label key="product.remarks.required" /> <font color="red">*</font><br/>
								<s:radio name="remark" list="#{'Y':'Yes','N':'No'}" value='%{remark==null?"N":remark}'/>
							</td>
							<td></td>
							<td></td>
						</tr>
						--%>
						<tr height="5"><td colspan="5"></td></tr>
						<tr>
							<td colspan="5" align="center">
<%-- 								<s:submit name="bck1" value="Submit" cssClass="btn" action="addProductBrokerMgm"/> --%>
								<input type="button" onclick="addProductBroker()" class="btn" value="Submit">
								<s:if test='"editAjax".equals(mode1)'>
						     		&nbsp;&nbsp;&nbsp;<input type="button" name="bck1" value="Delete" class="btn" onclick="deleteProduct();"/>
							 	</s:if> 
							</td>
						</tr>
						</table>
                	</td>
                </tr>
                <s:hidden name="payReceipt" value="N"/>
				<s:hidden name="freight" value="N"/>
				<s:hidden name="remark" value="N"/>
				<s:hidden name="provision" value="Y"/>
            </s:if>
            <s:elseif test='"3".equals(productID)'>
            	<tr>
                    <td class="heading"><s:text name="broker.premium.details" /></td>
                </tr>
                <tr height="5"><td></td></tr>
                <tr>
                	<td class="bg">
                		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                			<tr>
			                    <td width="5%">&nbsp;</td>
			                    <td width="30%" align="left"><s:label key="product.commission" /> <font color="red">*</font><br/>
			                        <s:textfield name="commission" maxlength="10" size="10" cssClass="inputBox" /></td>
			                    <td width="30%" align="left">SumInsured&nbsp;[<s:property value="%{#session.BrokerDetails.CurrencyName}"/>] <font color="red">*</font><br/>
			                        <s:textfield name="insurance_End_Limit" maxlength="10" size="10" cssClass="inputBox" /></td>
			                    <td width="30%" align="left"><s:label key="product.minpremium" />&nbsp;[<s:property value="%{#session.BrokerDetails.CurrencyName}"/>] <font color="red">*</font><br/>
			                        <s:textfield name="min_Premium_Amount" maxlength="10" size="10" cssClass="inputBox" /></td>
			                    <td width="5%">&nbsp;</td>
			                </tr>
			                <tr height="5"><td colspan="5"></td></tr>
			                <tr>
			                	<td></td>
			                	<td><s:label key="product.backdays" /> <font color="red">*</font><br/>
                        			<s:textfield name="back_Date_Allowed" maxlength="4" size="10" cssClass="inputBox" /></td>
			                	<td></td>
			                	<td></td>
			                	<td></td>
			                </tr>
                		</table>
                	</td>
                </tr>
                
                <tr height="5"><td></td></tr>
                <tr>
                	<td class="heading"><s:text name="broker.provision.loadingYN" /></td>
                </tr>
                <tr height="5"><td></td></tr>
                <tr>
                	<td class="bg">
                		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                			<tr>
                				<td width="5%"></td>
                				<td width="45%"><s:label key="product.provisionloading" /> <font color="red">*</font>
                    				<s:radio name="provision" id="provision" list="#{'Y':'Yes','N':'No'}" value='%{provision==null?"N":provision}' onclick="provisionDisplay(this.value)"/></td>
                				<td width="45%"></td>
                				<td width="5%"></td>
                			</tr>
                			<tr height="5"><td colspan="4"></td></tr>
                			<tr id="provision_Display" style="display: <s:if test='"Y".equals(provision)'></s:if><s:else>none</s:else>">
                				<td width="5%"></td>                				
                				<td width="45%"><s:label key="product.loading" /> <font color="red">*</font>
                        			<s:textfield name="loadingPremium" id="loadingPremium" maxlength="5"  size="10" cssClass="inputBox"/></td>
                				<td width="45%"><s:label key="product.discount" /> <font color="red">*</font>
                        			<s:textfield name="discountPremium" id="discountPremium" maxlength="5" size="10" cssClass="inputBox" /></td>                        		
                				<td width="5%"></td>
                			</tr>
                		</table>
                	</td>
                </tr>
                <tr height="5"><td></td></tr>
                <tr>
                	<td class="heading"><s:text name="broker.provision.details" /></td>
                </tr>
                <tr height="5"><td></td></tr>
                <tr>
                	<td class="bg">
                		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                			<tr>
                				<td width="5%"></td>
                				<td width="45%"><s:label key="product.provision" /> <font color="red">*</font><br/>
	                        		<s:radio name="user_Id_Creation" list="#{'Y':'Yes','N':'No'}" value='%{user_Id_Creation==null?"N":user_Id_Creation}'/></td>
	                        	<%--	
                				<td width="45%"><s:label key="product.paymentrequired" /> <font color="red">*</font><br/>
	                        		<s:radio name="payReceipt" list="#{'Y':'Yes','N':'No'}" value='%{payReceipt==null?"N":payReceipt}'/></td>
	                        	--%>                				
                				<td width="5%"></td>
                			</tr>
                			<%--
                			<tr height="5"><td colspan="4"></td></tr>
                			<tr>
                				<td></td>
                				<td><s:label key="product.provisiondebit" /> <font color="red">*</font><br/>
	                        		<s:radio name="freight" list="#{'Y':'Yes','N':'No'}"  value='%{freight==null?"N":freight}'/>
	                        	</td>
	                        	<td>
	                        		<s:label key="product.provisionloading" /> <font color="red">*</font><br/>
	                        		<s:radio name="provision" list="#{'Y':'Yes','N':'No'}" value='%{provision==null?"N":provision}'/>
	                        	</td>
	                        	<td>
	                        		<s:label key="product.remarks.required" /> <font color="red">*</font><br/>
	                        		<s:radio name="remark" list="#{'Y':'Yes','N':'No'}" value='%{remark==null?"N":remark}'/>
	                        	</td>
                				<td>
                				</td>
                			</tr>
                			--%> 
                			<%--
                			<tr height="5"><td colspan="4"></td></tr>
                			<tr>
                				<td></td>
                				<td><s:label key="product.remarks.required" /> <font color="red">*</font><br/>
	                        		<s:radio name="remark" list="#{'Y':'Yes','N':'No'}" value='%{remark==null?"N":remark}'/></td>
                				<td></td>
                				<td></td>
                			</tr>
                			--%>
                			<tr height="5"><td colspan="4"></td></tr>
                			<tr>
                				<td colspan="4" align="center">
<%--                 					<s:submit name="bck1" value="Submit" cssClass="btn" action="addProductBrokerMgm"/> --%>
                					<input type="button" onclick="addProductBroker()" class="btn" value="Submit">
                					<s:if test='"editAjax".equals(mode1)'>
                           				<input type="button" name="bck1" value="Delete" class="btn" onclick="deleteProduct();"/>
                    			 	</s:if> 
                				</td>
                			</tr>
                		</table>
                	</td>
                </tr>
                <s:hidden name="payReceipt" value="N"/>
				<s:hidden name="freight" value="N"/>
				<s:hidden name="remark" value="N"/>                
            </s:elseif>
            <s:else>
            	<tr id="opencov" style="display: <s:if test='"11".equals(prodID)'></s:if><s:else>none</s:else>">
            		<td>
            			<table width="100%">
            				<%--
            				<tr>
            					<td class="heading"><s:text name="broker.provision.details" /></td>
            				</tr>
            				--%>
            				<tr height="5"><td></td></tr>
            				<tr>
            					<td>
            						<table width="100%">
            							<%--
            							<tr>
            								<td class="heading"><s:text name="broker.provision.details" /></td>
            							</tr>
            							--%>
            							<tr height="5"><td></td></tr>
            							<tr>
            								<td class="bg">
            									<table width="100%">
            										<%--
            										<tr>
            											<td width="5%"></td>
			            								<td width="45%"><s:label key="product.provision" /> <font color="red">*</font><br/>
			                        						<s:radio name="user_Id_Creation" list="#{'Y':'Yes','N':'No'}" value='%{user_Id_Creation==null?"N":user_Id_Creation}'/></td>
			            								<td width="45%"><s:label key="product.paymentrequired" /> <font color="red">*</font><br/>
			                        						<s:radio name="payReceipt" list="#{'Y':'Yes','N':'No'}" value='%{payReceipt==null?"N":payReceipt}'/></td>
			            								<td width="5%"></td>
            										</tr>
            										<tr height="5"><td colspan="4"></td></tr>
            										<tr>
            											<td></td>
            											<td><s:label key="product.provisiondebit" /> <font color="red">*</font><br/>
                        									<s:radio name="freight" list="#{'Y':'Yes','N':'No'}"  value='%{freight==null?"N":freight}'/></td>
            											<td><s:label key="product.provisionloading" /> <font color="red">*</font><br/>
                        									<s:radio name="provision" list="#{'Y':'Yes','N':'No'}" value='%{provision==null?"N":provision}'/></td>
            											<td></td>
            										</tr>
            										<tr height="5"><td colspan="4"></td></tr>
            										<tr>
            											<td></td>
            											<td><s:label key="product.remarks.required" /> <font color="red">*</font><br/>
                        									<s:radio name="remark" list="#{'Y':'Yes','N':'No'}" value='%{remark==null?"N":remark}'/></td>
            											<td></td>
            											<td></td>
            										</tr>
            										--%>
            										<tr height="5"><td colspan="4"></td></tr>
            										<tr>
            											<td colspan="4" align="center">
<%--             												<s:submit name="bck1" value="Submit" cssClass="btn" action="addProductBrokerMgm"/> --%>
																<input type="button" onclick="addProductBroker()" class="btn" value="Submit">
						                					<s:if test='"editAjax".equals(mode1)'>
						                           				<input type="button" name="bck1" value="Delete" class="btn" onclick="deleteProduct();"/>
						                    			 	</s:if> 
            											</td>
            										</tr>
            									</table>
            								</td>            								
            							</tr>
            						</table>
            					</td>
            				</tr>
            			</table>
            		</td>
            	</tr>
            	<tr height="5"><td></td></tr>
            	<tr id="oneoff" style="display: <s:if test='"3".equals(prodID)'></s:if><s:else>none</s:else>">
            		<td>
            			<table width="100%">
            				<tr>
            					 <td class="heading"><s:text name="broker.premium.details" /></td>
            				</tr>
            				<tr height="5"><td></td></tr>
            				<tr>
            					<td class="bg">
            						<table width="100%">
            							<tr>
            								<td width="5%"></td>
            								<td width="30%"><s:label key="product.commission" /> <font color="red">*</font><br/>
                        									<s:textfield name="commission" cssClass="inputBox" maxlength="10" size="10"/></td>
            								<td width="30%"><s:label key="product.suminsured" /> <font color="red">*</font><br/>
                        									<s:textfield name="insurance_End_Limit" cssClass="inputBox" maxlength="10" size="10"/></td>
            								<td width="30%"><s:label key="product.minpremium" /> <font color="red">*</font><br/>
                        									<s:textfield name="min_Premium_Amount" cssClass="inputBox" maxlength="10" size="10"/></td>
            								<td width="5%"></td>
            							</tr>
            							<tr height="5"><td colspan="5"></td></tr>
            							<tr>
            								<td></td>
            								<td><s:label key="product.backdays" /> <font color="red">*</font><br/>
                        						<s:textfield name="back_Date_Allowed" cssClass="inputBox" maxlength="4" size="10"/></td>
            								<td></td>
            								<td></td>
            								<td></td>
            							</tr>
            						</table>
            					</td>
            				</tr>
            				<tr height="5"><td></td></tr>
            				<tr>
            					<td class="heading"><s:text name="broker.provision.details" /></td>
            				</tr>
            				<tr height="5"><td></td></tr>
            				<tr>
            					<td>
            						<table width="100%">
            							<tr>
            								<td width="5%"></td>
            								<td width="45%"><s:label key="product.provisionloading" /> <font color="red">*</font>
                    							<s:radio name="provision" id="provision" list="#{'Y':'Yes','N':'No'}" value='%{provision==null?"N":provision}' onclick="provisionDisplay(this.value)"/></td>
            								<td width="45%"></td>
            								<td width="5%"></td>
            							</tr>
            							<tr height="5"><td colspan="4"></td></tr>
            							<tr id="provision_Display" style="display: <s:if test='"Y".equals(provision)'></s:if><s:else>none</s:else>">
            								<td></td>
            								<td><s:label key="product.loading" /> <font color="red">*</font>
                        						<s:textfield name="loadingPremium" cssClass="inputBox" id="loadingPremium" maxlength="5"  size="10"/></td>
            								<td><s:label key="product.discount" /> <font color="red">*</font>
                        						<s:textfield name="discountPremium" cssClass="inputBox" id="discountPremium" maxlength="5" size="10"/></td>
            								<td></td>
            							</tr>
            							<tr height="5"><td colspan="4"></td></tr>
            							<tr>
            								<td colspan="4" class="heading"><s:text name="broker.provision.details" /></td>
            							</tr>
            							<tr height="5"><td colspan="4"></td></tr>
            							<tr>
            								<td colspan="4">
            									<table width="100%">
            										<tr>
            											<td width="5%"></td>
            											<td width="45%"><s:label key="product.provision" /> <font color="red">*</font><br/>
	                        								<s:radio name="user_Id_Creation" list="#{'Y':'Yes','N':'No'}" value='%{user_Id_Creation==null?"N":user_Id_Creation}'/></td>
	                        							<%--
            											<td width="45%"><s:label key="product.paymentrequired" /> <font color="red">*</font><br/>
	                        								<s:radio name="payReceipt" list="#{'Y':'Yes','N':'No'}" value='%{payReceipt==null?"N":payReceipt}'/></td>
	                        							--%>
            											<td width="5%"></td>
            										</tr>
            										<s:hidden name="payReceipt" value="N"/>
            										<s:hidden name="freight" value="N"/>
            										<s:hidden name="remark" value="N"/>
            										<%--
            										<tr height="5"><td colspan="4"></td></tr>
            										<tr>
            											<td></td>
            											<td><s:label key="product.provisiondebit" /> <font color="red">*</font><br/>
	                        								<s:radio name="freight" list="#{'Y':'Yes','N':'No'}"  value='%{freight==null?"N":freight}'/></td>
            											<td><s:label key="product.provisionloading" /> <font color="red">*</font><br/>
	                        								<s:radio name="provision" list="#{'Y':'Yes','N':'No'}" value='%{provision==null?"N":provision}'/></td>
            											<td></td>
            										</tr>
            										<tr height="5"><td colspan="4"></td></tr>
            										<tr>
            											<td></td>
            											<td><s:label key="product.remarks.required" /> <font color="red">*</font><br/>
	                        								<s:radio name="remark" list="#{'Y':'Yes','N':'No'}" value='%{remark==null?"N":remark}'/></td>
            											<td></td>
            											<td></td>
            										</tr>
            										--%>
            										<tr height="5"><td colspan="4"></td></tr>
            										<tr>
            											<td colspan="4" align="center">
<%--             												<s:submit name="bck1" value="Submit" cssClass="btn" action="addProductBrokerMgm"/> --%>
            												<input type="button" onclick="addProductBroker()" class="btn" value="Submit">            												
            											</td>
            										</tr>
            									</table>
            								</td>
            							</tr>
            						</table>
            					</td>
            				</tr>
            			</table>
            		</td>
            	</tr>
            </s:else>
    	</table>
        <s:hidden name="mode1"/>
        <s:hidden name="agencyCode"/>
		<s:hidden name="borganization"/>
		<s:hidden name="bcode"/>
		<s:hidden name="firstname"/>
		<s:hidden name="login_Id"/>
		<s:hidden name="comtypes"/>
    </s:form>
</s:elseif>
<s:elseif test='"userLists".equals(reqFrom)'>
    <display:table name="userList" pagesize="10" requestURI="/getABListUserMgm.action" class="footable" uid="row" id="record">
        <display:setProperty name="paging.banner.one_item_found" value="" />
        <display:setProperty name="paging.banner.one_items_found" value="" />
        <display:setProperty name="paging.banner.all_items_found" value="" />
        <display:setProperty name="paging.banner.some_items_found" value="" />
        <display:setProperty name="paging.banner.placement" value="bottom" />
        <display:setProperty name="paging.banner.onepage" value="" />
        <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="User Name" property="company_name"/>
        <display:column sortable="true" style="text-align:left;font-size:13px;"  title="User Code" property="AGENCY_CODE" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Login Id" property="LOGIN_ID" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Broker Name" property="brokerName" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Created Date" property="cr_date" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="More">
            <a href="viewUserMgm.action?mode=edit&uagencyCode=${record.AGENCY_CODE}">More</a>
        </display:column>
        <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
    </display:table>
</s:elseif>
<s:elseif test='"exclusionLists".equals(reqFrom)'>

                                                    <display:table name="exclusionList" pagesize="10" requestURI="/getExListRatingEngine.action" class="footable" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EXCLUSION NAME" property="EXCLUSION_DESCRIPTION" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editExclusionRating.action?exclusionID=${record.EXCLUSION_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
                                                  
</s:elseif>
<s:elseif test='"customerLists".equals(reqFrom)'>
    <display:table name="customerList" pagesize="10" requestURI="/getABListCustomerMgm.action" class="footable" uid="row" id="record">
        <display:setProperty name="paging.banner.one_item_found" value="" />
        <display:setProperty name="paging.banner.one_items_found" value="" />
        <display:setProperty name="paging.banner.all_items_found" value="" />
        <display:setProperty name="paging.banner.some_items_found" value="" />
        <display:setProperty name="paging.banner.placement" value="bottom" />
        <display:setProperty name="paging.banner.onepage" value="" />
        <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Customer Name" property="company_name"/>
        <display:column sortable="true" style="text-align:left;font-size:13px;"  title="Customer Code" property="customer_id" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Open Cover No" property="open_cover_no" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="More">
            <a href="viewCustomerMgm.action?mode=edit&cagencyCode=${record.AGENCY_CODE}">More</a>
        </display:column>
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Create Master Login">
            <a href="viewCustomerMgm.action?mode=edit&cagencyCode=${record.AGENCY_CODE}">Create</a>
        </display:column>
    </display:table>
</s:elseif>
<s:elseif test='"opencover".equals(reqFrom)'>
    <display:table name="openCover" pagesize="2" requestURI="/viewCustomerMgm.action" class="footable" uid="row" id="record">
        <display:setProperty name="paging.banner.one_item_found" value="" />
        <display:setProperty name="paging.banner.one_items_found" value="" />
        <display:setProperty name="paging.banner.all_items_found" value="" />
        <display:setProperty name="paging.banner.some_items_found" value="" />
        <display:setProperty name="paging.banner.placement" value="bottom" />
        <display:setProperty name="paging.banner.onepage" value="" />
        <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
        <display:column sortable="true" style="text-align:left;font-size:13px;"  title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Open Cover No" property="open_cover_no"/>
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Start Date" property="pol_start_date" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Expiry Date" property="pol_expire_date" />
        <display:column sortable="true" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
    </display:table>
</s:elseif>

<s:elseif test='("pending".equals(reqFrom)) && (rdate==null || "".equals(rdate))'>
    <div id="<s:property value='reqFrom'/>Policies">
        <s:form id="approved" name="approved" theme="simple">
            <table width="100%" align="center">
                <tr><td></td></tr>
                <tr><td align="center"> <s:text name="product.product"/>&nbsp;:</td><td><s:select name="productID" id="productID" list="productDet" cssStyle="width: 50%;" cssClass="inputSelect" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onchange="fnTab(this.value,'%{reqFrom}','','')"/></td></tr>
                <tr><td></td></tr>
                <tr>
                    <td colspan="2">
                        <display:table name="occList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=%{index}" class="footable" uid="row" id="record1" excludedParams="from1, index" style="text-align:center;font-size:13px;">
                            <display:setProperty name="paging.banner.one_item_found" value="" />
                            <display:setProperty name="paging.banner.one_items_found" value="" />
                            <display:setProperty name="paging.banner.all_items_found" value="" />
                            <display:setProperty name="paging.banner.some_items_found" value="" />
                            <display:setProperty name="paging.banner.placement" value="bottom" />
                            <display:setProperty name="paging.banner.onepage" value="" />
                            <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;width:100px;" title=" S.No " value="${record1_rowNum}"/>
                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Date">
                                <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="fnTab('${productID}', '${record1.entery_date}','${reqFrom}','${index}')">${record1.entery_date}</a></display:column>
                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Number of Policies" property="count_entery_date" />
                        </display:table>
                    </td>
                </tr>
            </table>
        </s:form>
    </div>
</s:elseif>
<s:elseif test='("approved".equals(reqFrom)) && (rdate==null || "".equals(rdate))'>
    <div id="<s:property value='reqFrom'/>Policies">
        <s:form id="approved" name="approved" theme="simple">
            <table width="100%" align="center">
                <tr><td></td></tr>
                <tr><td align="center"> <s:text name="product.product"/>&nbsp;:&nbsp;</td><td><s:select name="productID" id="productID" list="productDet" headerKey="" cssStyle="width: 50%;" cssClass="inputSelect" headerValue="-Select-" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onchange="fnTab(this.value,'%{reqFrom}','','')"/></td></tr>
                <tr><td></td></tr>
                <tr>
                    <td colspan="2">
                        <display:table name="occList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=%{index}" class="footable" uid="row" id="record3" excludedParams="from1, index" style="text-align:center;font-size:13px;">
                            <display:setProperty name="paging.banner.one_item_found" value="" />
                            <display:setProperty name="paging.banner.one_items_found" value="" />
                            <display:setProperty name="paging.banner.all_items_found" value="" />
                            <display:setProperty name="paging.banner.some_items_found" value="" />
                            <display:setProperty name="paging.banner.placement" value="bottom" />
                            <display:setProperty name="paging.banner.onepage" value="" />
                            <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;width:100px;" title=" S.No " value="${record3_rowNum}"/>
                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Date">
                                <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="fnTab('${productID}', '${record3.entery_date}','${reqFrom}','${index}')">${record3.entery_date}</a></display:column>
                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Number of Policies" property="count_entery_date" />
                            <display:column sortable="false"  style="text-align:right;font-size:13px;width:250px;" title="Premium(INR)" property="PREMIUM" format="{0,number,0.00}"/>
                        </display:table>
                    </td>
                </tr>
            </table>
        </s:form>
    </div>
</s:elseif>
<s:elseif test='("rejected".equals(reqFrom)) && (rdate==null || "".equals(rdate))'>
    <div id="<s:property value='reqFrom'/>Policies">
        <s:form id="approved" name="approved" theme="simple">
            <table width="100%" align="center">
                <tr><td></td></tr>
                <tr><td align="center"> <s:text name="product.product"/>&nbsp;:</td><td><s:select name="productID" id="productID" list="productDet" headerKey="" cssStyle="width: 50%;" cssClass="inputSelect"  headerValue="-Select-" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onchange="fnTab(this.value,'%{reqFrom}','','')"/></td></tr>
                <tr><td></td></tr>
                <tr>
                    <td colspan="2">
                        <display:table name="occList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=%{index}" class="table" uid="row" id="record5" excludedParams="from1, index" style="text-align:center;font-size:13px;">
                            <display:setProperty name="paging.banner.one_item_found" value="" />
                            <display:setProperty name="paging.banner.one_items_found" value="" />
                            <display:setProperty name="paging.banner.all_items_found" value="" />
                            <display:setProperty name="paging.banner.some_items_found" value="" />
                            <display:setProperty name="paging.banner.placement" value="bottom" />
                            <display:setProperty name="paging.banner.onepage" value="" />
                            <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;width:100px;" title=" S.No " value="${record5_rowNum}"/>
                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Date">
                                <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="fnTab('${productID}', '${record5.entery_date}','${reqFrom}','${index}')">${record5.entery_date}</a></display:column>
                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Number of Policies" property="count_entery_date" />
                        </display:table>
                    </td>
                </tr>
            </table>
        </s:form>
    </div>
</s:elseif>
<s:elseif test='("pending".equals(reqFrom)) && (rdate!=null || !"".equals(rdate))'>
    <div id="<s:property value='reqFrom'/>Policies">
        <s:form id="approved" name="approved" theme="simple">
        	<table width="50%" style="margin: 0 auto;">
        		<tr><td align="center"> <s:text name="product.product"/>&nbsp;:</td><td><s:select name="productID" list="productDet"  headerKey="" headerValue="-Select-" listKey="PRODUCT_ID" cssStyle="width: 50%;" cssClass="inputSelect" listValue="PRODUCT_NAME" onchange="fnTab(this.value,'%{reqFrom}','','')"/></td></tr>
        		<tr>
        			<td colspan="2">
        				<b>Selected Date is <s:property value="rdate"/></b>
        			</td>
        		</tr>
        	</table>
            <br/>
            <display:table name="policyList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=%{index}" class="footable" uid="row" id="record" excludedParams="from1" style="text-align:center;font-size:13px;">
                  <display:setProperty name="paging.banner.one_item_found" value="" />
                  <display:setProperty name="paging.banner.one_items_found" value="" />
                  <display:setProperty name="paging.banner.all_items_found" value="" />
                  <display:setProperty name="paging.banner.some_items_found" value="" />
                  <display:setProperty name="paging.banner.placement" value="bottom" />
                  <display:setProperty name="paging.banner.onepage" value="" />
                  <%--<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Application No" property="APPLICATION_NO"/>--%>
                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Broker Organization" property="brokername"/>
                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Quote Created By" property="quote_Created"/>
                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Customer Name" property="CUST_NAME"/>
                  <display:column sortable="false" style="text-align:center;font-size:13px;" title="Quote No">
                      <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="editQuote('${record.APPLICATION_NO}', '${session.user}','${productID}','${rdate}',${record.QUOTE_NO},'${record.SCHEME_ID}','RU');">${record.QUOTE_NO}</a>
                  </display:column>
                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Remarks" property="REMARKS"/>
                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
              </display:table>
        </s:form>
    </div>
</s:elseif> 
<s:elseif test='("approved".equals(reqFrom)) && (rdate!=null || !"".equals(rdate))'>
    <div id="<s:property value='reqFrom'/>Policies">
        <s:form id="approved" name="approved" theme="simple">
        	<table width="50%" style="margin: 0 auto;">
        		<tr><td align="center"> <s:text name="product.product"/>&nbsp;:</td><td><s:select name="productID" list="productDet" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID" cssStyle="width: 50%;" cssClass="inputSelect" listValue="PRODUCT_NAME" onchange="fnTab(this.value,'%{reqFrom}','','')"/></td></tr>
        		<tr>
        			<td colspan="2">
        				<b>Selected Date is <s:property value="rdate"/></b>
        			</td>
        		</tr>
        	</table>
            <br/>
            <display:table name="policyList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=%{index}" class="footable" uid="row" id="record2" excludedParams="from1" style="text-align:center;font-size:13px;">
                 <display:setProperty name="paging.banner.one_item_found" value="" />
                 <display:setProperty name="paging.banner.one_items_found" value="" />
                 <display:setProperty name="paging.banner.all_items_found" value="" />
                 <display:setProperty name="paging.banner.some_items_found" value="" />
                 <display:setProperty name="paging.banner.placement" value="bottom" />
                 <display:setProperty name="paging.banner.onepage" value="" />
                  <%--<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Application No" property="APPLICATION_NO"/>--%>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Broker Organization" property="brokername"/>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Quote Created By" property="quote_Created"/>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Customer Name" property="CUST_NAME"/>
                 <display:column sortable="false" style="text-align:center;font-size:13px;" title="Quote No">
                     <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="editQuote('${record2.APPLICATION_NO}', '${session.user}','${productID}','${rdate}',${record2.QUOTE_NO},'${record2.SCHEME_ID}','RA');">${record2.QUOTE_NO}</a>
                 </display:column>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Remarks" property="REMARKS"/>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
             </display:table>
        </s:form>
    </div>
</s:elseif>
<s:elseif test='("rejected".equals(reqFrom)) && (rdate!=null || !"".equals(rdate))'>
    <div id="<s:property value='reqFrom'/>Policies">
        <s:form id="approved" name="approved" theme="simple">
        	<table width="50%" style="margin: 0 auto;">
        		<tr><td align="center"> <s:text name="product.product"/>&nbsp;:</td><td><s:select name="productID" list="productDet" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID" cssStyle="width: 50%;" cssClass="inputSelect" listValue="PRODUCT_NAME" onchange="fnTab(this.value,'%{reqFrom}','','')"/></td></tr>
        		<tr>
        			<td colspan="2">
        				<b>Selected Date is <s:property value="rdate"/></b>
        			</td>
        		</tr>
        	</table>
            <br/>
            <display:table name="policyList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=%{index}" class="footable" uid="row" id="record4" excludedParams="from1" style="text-align:center;font-size:13px;">
                 <display:setProperty name="paging.banner.one_item_found" value="" />
                 <display:setProperty name="paging.banner.one_items_found" value="" />
                 <display:setProperty name="paging.banner.all_items_found" value="" />
                 <display:setProperty name="paging.banner.some_items_found" value="" />
                 <display:setProperty name="paging.banner.placement" value="bottom" />
                 <display:setProperty name="paging.banner.onepage" value="" />
                 <%--<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Application No" property="APPLICATION_NO"/>--%>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Broker Organization" property="brokername"/>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Quote Created By" property="quote_Created"/>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Customer Name" property="CUST_NAME"/>
                 <display:column sortable="false" style="text-align:center;font-size:13px;" title="Quote No">
                     <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="editQuote('${record4.APPLICATION_NO}', '${session.user}','${productID}','${rdate}',${record4.QUOTE_NO},'${record4.SCHEME_ID}','RR');">${record4.QUOTE_NO}</a>
                 </display:column>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Remarks" property="REMARKS"/>
                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
             </display:table>
        </s:form>
    </div>
</s:elseif>
<s:elseif test='"regQuotes".equals(reqFrom)'>
        <display:table name="regQuoteList" pagesize="10" requestURI="getregQuoteOC.action?from1=Normal&index=1" class="footable" uid="row" id="record" excludedParams="from1, index" style="text-align:center;font-size:13px;">
                <display:setProperty name="paging.banner.one_item_found" value="" />
                <display:setProperty name="paging.banner.one_items_found" value="" />
                <display:setProperty name="paging.banner.all_items_found" value="" />
                <display:setProperty name="paging.banner.some_items_found" value="" />
                <display:setProperty name="paging.banner.placement" value="bottom" />
                <display:setProperty name="paging.banner.onepage" value="" />
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Proposal No" property="proposal_no"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="cust_name"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy Start Date" property="start_date"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy End date" property="end_date"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Edit">
                    <a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Edit</a>
                </display:column>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Schedule">
                    <s:if test='RENEWAL_STATUS=="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Schedule</a></s:if>
                    <s:else>N/A</s:else>
                </display:column>
        </display:table>
</s:elseif>
<s:elseif test='"portfo".equals(reqFrom)'>
        <display:table name="portfolioList" pagesize="10" requestURI="getPortfolioOC.action?from1=Normal&index=2" class="footable" uid="row" id="record" excludedParams="from1, index" style="text-align:center;font-size:13px;">
                <display:setProperty name="paging.banner.one_item_found" value="" />
                <display:setProperty name="paging.banner.one_items_found" value="" />
                <display:setProperty name="paging.banner.all_items_found" value="" />
                <display:setProperty name="paging.banner.some_items_found" value="" />
                <display:setProperty name="paging.banner.placement" value="bottom" />
                <display:setProperty name="paging.banner.onepage" value="" />
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Proposal No" property="PROPOSAL_NO"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="cust_name"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy Start Date" property="start_date"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy End date" property="end_date"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Endorse">
                    <s:if test='STATUS=="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Edit</a></s:if>
                    <s:elseif test='ENDT_STATUS!="N"'>Endorse</s:elseif>
                </display:column>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="View">
                    <a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">View</a>
                </display:column>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Schedule">
                    <s:if test='STATUS!="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Schedule</a></s:if>
                    <s:else>N/A</s:else>
                </display:column>
                
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Endt Schedule">
                    <s:if test='ENDT_STATUS=="N"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Schedule</a></s:if>
                    <s:else>N/A</s:else>
                </display:column>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="DeActivate">
                    <s:if test='RENEWAL_STATUS=="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Delete</a></s:if>
                    <s:else>N/A</s:else>
                </display:column>
        </display:table>
</s:elseif>
<s:elseif test='"policylist".equals(reqFrom)'>
            <table width="100%" align="center" >
                <tr><td><table width="90%" align="center"><tr><td width="50%" align="center"><s:text name="policy.report.startdate"/>&nbsp; : &nbsp;<s:property value="startDate"/></td><s:hidden name="startDate" id="startdate"/>
                                                      <td width="50%" align="center"><s:text name="policy.report.enddate"/>&nbsp; : &nbsp;<s:property value="endDate"/></td><s:hidden name="endDate" id="enddate"/><s:hidden name="productID" id="productid"/>
                                                    </tr>
                        </table>
                </td></tr>
                <tr><td colspan="5" align="center">
                        <display:table name="policyList" pagesize="10" requestURI="getRePolicyAreport.action?from1=ajax" class="footable" uid="row" id="record" excludedParams="from1" style="text-align:center;font-size:13px;">
                                <display:setProperty name="paging.banner.one_item_found" value="" />
                                <display:setProperty name="paging.banner.one_items_found" value="" />
                                <display:setProperty name="paging.banner.all_items_found" value="" />
                                <display:setProperty name="paging.banner.some_items_found" value="" />
                                <display:setProperty name="paging.banner.placement" value="bottom" />
                                <display:setProperty name="paging.banner.onepage" value="" />
                                <display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Broker Name" property="company_name"/>
                                <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;" title="No.Of Policies" >
                                  <a href="#" onclick="getPolicyReport('${record.UW_LOGIN_ID}','reportBR','${record.LOGIN_ID}')" >${record.POLICY}</a>
                               	</display:column>
                                <display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="Premium" property="premium" format="{0,number,0,000.00}"/>
                                <display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="Commission" property="COMMISSION" format="{0,number,0,000.00}"/>
                        </display:table>
                </td></tr>
                <tr><td height="5px;" align="center"><s:hidden name="index"/><s:hidden name="mode1"/></td><s:hidden name="broker" id="rbroker"/></tr>
                <tr align="center">
                    <td colspan="5">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td height="25" align="center" >
<%--                                 <s:submit name="back" value="Back" cssClass="btn" action="policyAreport" theme="simple"/> --%>
                                <input type="button" value="Back" class="btn" onclick="fnBack('policyAreport.action')">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
</s:elseif>
<s:elseif test='"policylistUW".equals(reqFrom)'>
               <table width="100%" border="0" cellspacing="0" cellpadding="0">
   					<tr>	                                                 
    						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
         						<tr>
         							<td width="20%"></td>
           						<td width="25%" align="right"><s:text name="policy.report.startdate"/> <font color="red">*</font></td>
              						<td width="3%"></td>
           					    <td width="32%" align="left">
           					    <sj:datepicker name="startDate" id="startDate3" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
           					    </td>
               					<td width="20%"></td>
               				</tr>
         						<tr>
         							<td width="20%"></td>
           						<td width="25%" align="right"><s:text name="policy.report.enddate"/> <font color="red">*</font></td>
              						<td width="3%"></td>
           					    <td width="32%" align="left">
           					    <sj:datepicker name="endDate" id="endDate4" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
           					    </td>
               					<td width="20%"></td>
               				</tr>
               				<tr>
         							<td width="20%"></td>
           						<td width="25%" align="right"><s:text name="policy.report.select.uw"/> <font color="red">*</font></td>
              						<td width="3%"></td>
           					    <td width="32%" align="left"><s:select name="broker" id="broker" list="uwList" headerKey="ALL" headerValue="-ALL-" listKey="login_id"  listValue="username" cssClass="inputSelect"/></td>
               					<td width="20%"></td>
               				</tr>
               				<tr>
         							<td width="20%"></td>
           						<td width="25%" align="right"><s:text name="policy.report.product"/> <font color="red">*</font></td>
              						<td width="3%"></td>
           					    <td width="32%" align="left"><s:select name="productID" id="productID" list="productList" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputSelect"/></td>
                 					<td width="20%"></td>
                 				</tr>
           					   <tr><td colspan="5"></td></tr>
    						   </table></td>
    						</tr>
    						 <tr>
 							<td height="2" bgcolor="#FFFFFF"></td>
		    </tr>
			    <tr><td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="25" align="center" valign="middle">
			<input type="button" name="submit1" class="btn" value="Submit" onclick="forward('reportUW',this.form)"/>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr align="center">
	<td height="20" colspan="2" >
		<div id="loaderImage" style="display:none">
			<br>
				<img src='${pageContext.request.contextPath}/images/ajax-loader.gif' width="50px" height="50px"/>
					<br>
				</div>
			</td>
		</tr>
</table>
  </s:elseif>
<s:elseif test='"policylistBR".equals(reqFrom)'>
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
             					<tr>	                                                 
              						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                   						<tr>
                   							<td width="20%"></td>
                     						<td width="25%" align="right"><s:text name="policy.report.startdate"/> <font color="red">*</font></td>
                        						<td width="3%"></td>
                     					    <td width="32%" align="left">
                     					    <sj:datepicker name="startDate" id="startDate4" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
                     					    </td>
                         					<td width="20%"></td>
                         				</tr>
                   						<tr>
                   							<td width="20%"></td>
                     						<td width="25%" align="right"><s:text name="policy.report.enddate"/> <font color="red">*</font></td>
                        						<td width="3%"></td>
                     					    <td width="32%" align="left">
                     					    <sj:datepicker name="endDate" id="endDate4" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
                     					    </td>
                         					<td width="20%"></td>
                         				</tr>
                         				<tr>
                   							<td width="20%"></td>
                     						<td width="25%" align="right"><s:text name="policy.report.select.br"/> <font color="red">*</font></td>
                        						<td width="3%"></td>
                     					    <td width="32%" align="left"><s:select name="broker" id="broker1" list="brokerList" headerKey="ALL" headerValue="-ALL-" listKey="login_id"  listValue="COMPANY_NAME" cssClass="inputSelect"/></td>
                         					<td width="20%"></td>
                         				</tr>
                         				<tr>
                   							<td width="20%"></td>
                     						<td width="25%" align="right"><s:text name="policy.report.product"/> <font color="red">*</font></td>
                        						<td width="3%"></td>
                     					    <td width="32%" align="left"><s:select name="productID" id="productID1" list="productList" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputSelect"/></td>
                         					<td width="20%"></td>
                         				</tr>
                   					   <tr><td colspan="5"></td></tr>
            						   </table></td>
            						</tr>
            						 <tr>
         							<td height="2" bgcolor="#FFFFFF"></td>
   							    </tr>
     						    <tr><td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="25" align="center" valign="middle">
											<input type="button" name="submit1" class="btn" value="Submit" onclick="forward('reportBR',this.form)"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td height="20" colspan="2" >
								<div id="loaderImage1" style="display:none">
									<br>
										<img src='${pageContext.request.contextPath}/images/ajax-loader.gif' width="50px" height="50px"/>
									<br>
								</div>
							</td>
						</tr>
					</table>
</s:elseif>
<s:elseif test='"lcsmartLists".equals(reqFrom)'>
        <table width="100%" align="center">
        <tr><td align="center">
            <img id="ajaxLoader" style="display:none" src='${pageContext.request.contextPath}/images/ajax-loader.gif' width="50px" height="50px"/>
        </td></tr>
        <tr><td>
                <display:table name="lcsmartList" pagesize="20" requestURI="lcSmartAreport.action?from1=ajax" class="footable" uid="row" id="record" export="false" excludedParams="from1">
                    <display:setProperty name="paging.banner.one_item_found" value="" />
                    <display:setProperty name="paging.banner.one_items_found" value="" />
                    <display:setProperty name="paging.banner.all_items_found" value="" />
                    <display:setProperty name="paging.banner.some_items_found" value="" />
                    <display:setProperty name="paging.banner.placement" value="bottom" />
                    <display:setProperty name="paging.banner.onepage" value="" />
                    <display:setProperty name="export.types" value="excel pdf" />
                    <display:setProperty name="export.excel" value="false" />
                    <display:setProperty name="export.pdf" value="false" />
                    <display:setProperty name="export.csv" value="false" />
                    <display:setProperty name="export.xml" value="false" />
                    <display:setProperty name="export.pdf.filename" value="LCSmartReport.pdf" />
                    <display:setProperty name="export.excel.filename" value="LCSmartReport.xls" />
                    
                    <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
                    <display:column sortable="true" style="text-align:left;font-size:13px;"  title="Open Cover No" property="MISSIPPI_OPENCOVER_NO" />
                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="Customer Name" property="cust_name" />
                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="Bank Name" property="bank_name" />
                    <display:column sortable="true" style="text-align:left;font-size:13px;"  title="LC Number" property="LC_NUMBER" />
                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="Start Date" property="start_date" />
                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="End Date" property="end_date" />
                    <display:column sortable="true" style="text-align:right;font-size:13px;"  title="LC Currency" property="SHORT_NAME" />
                    <display:column sortable="true" style="text-align:right;font-size:13px;" title="LC Currency Amount" property="EXCHANGE_RATE" format="{0,number,0.00}"/>
                    <display:column sortable="true" style="text-align:right;font-size:13px;"  title="LC Amount" property="LC_AMOUNT" format="{0,number,0,000.00}"/>
                    <display:column sortable="true" style="text-align:right;font-size:13px;" title="LC Amount(INR)" property="LC_AMT_DH" format="{0,number,0,000.00}"/>
                    <display:column sortable="true" style="text-align:right;font-size:13px;" title="Used Amount(INR)" property="used_amt" format="{0,number,0,000.00}"/>
                    <display:column sortable="true" style="text-align:right;font-size:13px;" title="Pending LC Amount(INR)" property="LC_BALANCE_AMOUNT" format="{0,number,0,000.00}"/>
                </display:table>
        </td></tr>
    </table>
</s:elseif>
<s:elseif test='"adminlists".equals(reqFrom)'>
        <display:table name="adminList" pagesize="10" requestURI="adminMgtAdmin.action?index=0" class="footable" uid="row2" id="record1" excludedParams="index">
                <s:set var="myrow" value="#attr.record1"/>
                <display:setProperty name="paging.banner.one_item_found" value="" />
                <display:setProperty name="paging.banner.one_items_found" value="" />
                <display:setProperty name="paging.banner.all_items_found" value="" />
                <display:setProperty name="paging.banner.some_items_found" value="" />
                <display:setProperty name="paging.banner.placement" value="bottom" />
                <display:setProperty name="paging.banner.onepage" value="" />
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="S.No" value="${record1_rowNum}"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Login ID" property="LOGIN_ID"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="User Name" property="USERNAME"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="UserType" property="userType"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Branch" property="BRANCH_NAME"/>
                <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Status" property="STATUS1"/>
                <display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="Edit">
                    <a href="#" onclick="forward('<s:property value="#myrow.LOGIN_ID"/>','editadminAdmin','adminlist')">Edit</a>
                </display:column>
        </display:table>
</s:elseif>
<s:elseif test='"extraCoverLists".equals(reqFrom)'>
<display:table name="extraCoverList" pagesize="10" requestURI="/getECoverListRatingEngine.action" class="footable" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EXTRA COVER NAME" property="EXTRA_COVER_NAME" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="MODE OF TRANSPORT" property="TRANSPORT_DESCRIPTION" />
                                                    
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editExtraCoverRating.action?extraCoverId=${record.EXTRA_COVER_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                     
                                                    </display:table>
                                                    <s:hidden name="extraCoverId"/>
</s:elseif>
<s:elseif test='"cityLists".equals(reqFrom)'>
<display:table name="cityList" pagesize="10" requestURI="/getCitysListRatingEngine.action" class="footable" uid="row" id="record">
                                                    <display:setProperty name="paging.banner.one_item_found" value="" />
                                                    <display:setProperty name="paging.banner.one_items_found" value="" />
                                                    <display:setProperty name="paging.banner.all_items_found" value="" />
                                                    <display:setProperty name="paging.banner.some_items_found" value="" />
                                                    <display:setProperty name="paging.banner.placement" value="bottom" />
                                                    <display:setProperty name="paging.banner.onepage" value="" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="COUNTRY NAME" property="COUNTRY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CITY NAME" property="CITY_NAME" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CORE APP CODE"  property="RSACODE" />
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
                                                    <a href="editCityRating.action?cityID=${record.CITY_ID}">Edit</a>
                                                    </display:column>
                                                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
                                                    </display:table>
</s:elseif>
<s:elseif test='"menulists".equals(reqFrom)'>
        <display:table name="menuList" pagesize="10" requestURI="adminMgtAdmin.action?index=1" class="footable" uid="row" id="record" excludedParams="index">
            <s:set var="myrow" value="#attr.record"/>
            <display:setProperty name="paging.banner.one_item_found" value="" />
            <display:setProperty name="paging.banner.one_items_found" value="" />
            <display:setProperty name="paging.banner.all_items_found" value="" />
            <display:setProperty name="paging.banner.some_items_found" value="" />
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="paging.banner.onepage" value="" />
            <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="S.No" value="${record_rowNum}"/>
            <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Menu Name" property="DETAIL_NAME"/>
            <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Menu URL Path" property="REMARKS"/>
            <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Parent Menu" property="PARENT_MENU_NAME"/>
            <display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Status" property="STATUS1"/>
            <display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="Edit">
                <a href="#" onclick="forward('<s:property value="#myrow.CATEGORY_DETAIL_ID"/>','editMenuAdmin','menulist')">Edit</a>
            </display:column>
        </display:table>
</s:elseif>
<s:elseif test='"depositTYPE".equals(reqFrom) && "INDIVIDUAL".equals(depositType)'>
	<s:radio name="productsId" id="productsId" list="productList" cssClass="input" listKey="PRODUCT_ID" listValue="PRODUCT_NAME"  onclick="radioOc()"/>
</s:elseif>
<s:elseif test='"depositTYPE".equals(reqFrom) && "FLOAT".equals(depositType)'>
	<s:checkboxlist name="productsId" id="productsId" list="productList" cssClass="input" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onclick="checkOc()"/>
</s:elseif>
<s:elseif  test='"scheme".equals(reqFrom)'>
        <table><tr>
	 	<td><s:text name="Choose Scheme" /><font color="red">*</font>
	  	<s:select name="schemeId" id="schemeId" list="scheme" headerKey="" headerValue="-Select-" listKey="SCHEME_ID" listValue="SCHEME_NAME" cssClass="input" onchange="fnOption(this.value)"/></td>
	  	</tr></table>
</s:elseif>
<s:elseif  test='"option".equals(reqFrom)'>
        <table><tr>
	 	<td><s:text name="Choose Option" /><font color="red">*</font><br />
		<s:checkboxlist name="linkOption"  list="option"  listKey="OPTION_ID" listValue="OPTION_NAME"/></td>
		
		<td><s:text name="travel.core.app.code" /><font color="red">*</font>
		<s:textfield name="linkOptionCode" id="linkOptionCode" cssClass="input" /></td>
		
		<td><s:text name="travel.effectivedate" /><font color="red">*</font>
		<s:textfield name="linkOptionDate"  readonly="true" id="effectiveDt"/></td>
		
		<td><s:text name="travel.status" /><font color="red">*</font>
		<s:radio list="#{'Y':'Active','N':'DeActive'}" name="linkOptionStatus" id="linkOptionStatus" value="%{linkOptionStatus==null?'N':linkOptionStatus}" cssClass="input2"/></td>
		
		<td align="center"><input type="button" name="sub" value="Submit" onclick="fnsubmit(this.form,'insert','')" class="btn" /></td>
		</tr></table>
</s:elseif>
<s:elseif  test='"coverage".equals(reqFrom)'>
        <table><tr>
	 	<td><s:text name="Choose Scheme" /><font color="red">*</font>
	  	<s:select name="schemeId" id="schemeId" list="scheme" listKey="SCHEME_ID" listValue="SCHEME_NAME" cssClass="input" onchange="fnOption(this.value)"/></td>
	  	</tr></table>
</s:elseif>
<s:elseif  test='"cover".equals(reqFrom)'>
        <table><tr>
        <td><s:text name="Choose Option" /><font color="red">*</font>
	 	<s:select name="linkCover" id="linkCover" list="cover" listKey="COVER_ID" listValue="COVER_NAME" cssClass="input" onchange=""/></td>
        
	 	<td><s:text name="travel.core.app.code" /><font color="red">*</font>
		<s:textfield name="linkCoverageCode" id="linkCoverageCode" cssClass="input" /></td>
	 	
		<td><s:text name="Select Coverages" /><font color="red">*</font></td>
		<td><s:checkboxlist name="linkCoverageId" list="#{'1':'Do you want to include Winter Sports Extension'}"  /></td>
		
		<td><s:text name="Rate" /><font color="red">*</font></td>
		<td><s:textfield name="linkCoverageRate" id="linkCoverageRate" cssClass="input" /></td>
		
		<td><s:text name="travel.effectivedate" /><font color="red">*</font>
		<s:textfield name="linkCoverageDate"  readonly="true" id="effectiveDt"/></td>
		
		<td><s:text name="Rate Status" /><font color="red">*</font>
		<td><s:radio list="#{'Y':'Loading','N':'Discount'}"  name="linkRateStatus" id="linkRateStatus" value="%{linkRateStatus==null?'Y':linkRateStatus}" onclick="Status(this);"/></td>
		
	 	<td><s:text name="Status" /><font color="red">*</font>
		<td><s:radio list="#{'Y':'Active','N':'DeActive'}" name="linkCoverageStatus" id="linkCoverageStatus" value="%{linkCoverageStatus==null?'N':linkCoverageStatus}" cssClass="input2"/></td>
		<td><input type="button" name="sub" value="Submit" onclick="fnsubmit(this.form,'insert','')" class="btn" /></td>
	 	</tr></table>
</s:elseif>
<s:elseif test='"issuerList".equals(reqFrom)'>
   <div id="underwriterList">
		<display:table name="underwriterList" pagesize="10" requestURI="/getABListUnderwriterMgm.action" class="footable" uid="row" id="record">
			<display:setProperty name="paging.banner.one_item_found" value="" />
			<display:setProperty name="paging.banner.one_items_found" value="" />
			<display:setProperty name="paging.banner.all_items_found" value="" />
			<display:setProperty name="paging.banner.some_items_found" value="" />
			<display:setProperty name="paging.banner.placement"	value="bottom" />
			<display:setProperty name="paging.banner.onepage" value="" />
			<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Issuer Name" property="COMPANY_NAME"/>
			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Issuer Type" property="USERTYPE" />
			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Login Id" property="LOGIN_ID" />
			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Created Date" property="CR_DATE" />
			<display:column sortable="true" style="text-align:left;font-size:13px;" title="More">
				<a href="viewUnderwriterMgm.action?mode=edit&issurName=${record.COMPANY_NAME}&loginId=${record.LOGIN_ID}">More</a>
			</display:column>
			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
		</display:table>
	</div>
</s:elseif>


<s:elseif test='("claimPending".equals(reqFrom))'>
    <div id="<s:property value='reqFrom'/>Policies">
        <s:form id="pending" name="pending" theme="simple">
							<s:if test='"claimPending".equals(reqFrom)'>
								<display:table name="claimIntimation" pagesize="10"
									requestURI="claimIntimationAjax.action" class="footable" uid="row"
									id="record1" excludedParams="from1"
									style="text-align:center;font-size:13px;">
									<display:setProperty name="paging.banner.one_item_found"
										value="" />
									<display:setProperty name="paging.banner.one_items_found"
										value="" />
									<display:setProperty name="paging.banner.all_items_found"
										value="" />
									<display:setProperty name="paging.banner.some_items_found"
										value="" />
									<display:setProperty name="paging.banner.placement"
										value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									<display:column sortable="false"
										style="text-align:center;font-size:13px;height:30px;width:100px;"
										title=" S.No ">
										${record1_rowNum}
									</display:column>
									<display:column sortable="false"
										style="text-align:center;font-size:13px;width:250px;"
										title="Policie Number" property="POLICY_NO" />
									<display:column sortable="false"
										style="text-align:center;font-size:13px;width:250px;"
										title="Customer Name" property="CUSTOMER_NAME" />
									<display:column sortable="false"
										style="text-align:center;font-size:13px;width:250px;"
										title="Reported Date" property="REPORTED_DATE" />	
									<display:column sortable="false"
										style="text-align:center;font-size:13px;width:250px;"
										title="Loss Description" property="LOSS_DESCRIPTION" />
									
									<display:column sortable="false"
										style="text-align:center;font-size:13px;width:250px;"
										title="View">
										<a type="button" class="btn btn-sm btn-primary"
											style="color: #ffffff;" href="#"
											onclick="fnTab('${record1.POLICY_NO}','View')">View</a>
									</display:column>

								</display:table>
							</s:if>
							
						</s:form>
    </div>
</s:elseif>