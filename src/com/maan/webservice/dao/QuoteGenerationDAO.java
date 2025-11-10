package com.maan.webservice.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.maan.common.*;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.maan.services.util.runner;
import com.maan.webservice.PolicyGenerationAction;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
public class QuoteGenerationDAO extends MyJdbcTemplate {

	private static final Logger logger = LogUtil.getLogger(QuoteGenerationDAO.class);
	public String quoteRequestXMLReader(String request)throws Exception
	{

	    String result = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
	 	InputSource is = new InputSource();
	 	
		//request = request.replaceAll("&", "&amp;");
		is.setCharacterStream(new StringReader(request));
		Document doc = db.parse(is);
		doc.getDocumentElement().normalize();
		// To read and insert  into webservice_marine_quote from XML 
		String refNo = "";
		String type = "";
		String count = "";
		String qrySelection1 = "select tagname, tag_value,table_name from webservice_request_reader where field_name='REFERENCE_NUMBER' and status='Y' and request_id='101'";
		System.out.println("Query selection::"+qrySelection1);
		String refValues[][] = runner.multipleSelection(qrySelection1);
		String qrySelection2 = "select tagname, tag_value,table_name from webservice_request_reader where field_name='TYPE' and status='Y' and request_id='101'";
		String typeValues[][] = runner.multipleSelection(qrySelection2);
		NodeList nList1 = doc.getElementsByTagName(refValues[0][0]);
		Node nNode1 = nList1.item(0);
		if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode1;
		    refNo =  getTagValue(refValues[0][1], eElement);
		    type  =  getTagValue(typeValues[0][1], eElement);
		    System.out.println("Read reference no::>"+refNo);
		    String checkQry = "select count(*) from webservice_marine_quote where reference_number='"+refNo+"'";
		    count = runner.singleSelection(checkQry);
		}
		//Fresh Entry
		 if((!refNo.equalsIgnoreCase("") && count.equalsIgnoreCase("0") && type.equalsIgnoreCase("F") ) || (!refNo.equalsIgnoreCase("") && !count.equalsIgnoreCase("0") && type.equalsIgnoreCase("M")))
		   {
			 request=request.replaceAll("\n","").replaceAll("\t", "");
			 //request=request.replaceAll("</PartnerInformation>","").replaceAll("<PartnerInformation>", "");
			// String []xmlVals=request.split("<commodityInfo>");
			 //String []xmlVals2=xmlVals[1].split("</commodityInfo>");
			 //if(("<commodityInfo>"+xmlVals2[0]+"</commodityInfo>").length()>4000)
			//	result="Number of commodities exceeds the limit";
			 //else
			 	//runner.CallProcedure("CALL XML_RAW_INSERT_TEST(xmltype(?),xmltype(?))", new Object[]{(xmlVals[0]+xmlVals2[1]),("<commodityInfo>"+xmlVals2[0]+"</commodityInfo>")});
			 	runner.CallProcedure("CALL XML_RAW_INSERT(?)", new Object[]{request});
			 	//runner.CallProcedure("CALL XML_RAW_INSERT(xmltype(?))", new Object[]{request});
			 }
		
		   if(count.equalsIgnoreCase("0") && type.equalsIgnoreCase("M"))
		   {
			    logger.info("Record not exists for updation");
			    result = "Record not exists for updation";
			}
	       else if(!count.equalsIgnoreCase("0")  && type.equalsIgnoreCase("F"))
		   {
			   logger.info("Already exists and type fresh is not valid");
			   result = "Already exists and type fresh is not valid";
		   }
	       else if(!count.equalsIgnoreCase("0")  && type.equalsIgnoreCase("M"))
		   {
	    	   String checkQry = "select status from webservice_marine_quote where reference_number='"+refNo+"'";
			   String status = runner.singleSelection(checkQry);
			   if(status.equalsIgnoreCase("P"))
	     	   result = "Given Reference Number Already Generated as Policy";
		   }
	       else if(refNo.equalsIgnoreCase(""))
		   {
	     		logger.info("XML Invalid");
	     		result = "Reference number required";
		   }
		   System.out.println("Type received::>"+type);
		   if(!(type.equalsIgnoreCase("F") || type.equalsIgnoreCase("M")))
		   {
			  result = result.concat("Not valid type");
		    }
		   
		   if((!refNo.equalsIgnoreCase("") && count.equalsIgnoreCase("0") && type.equalsIgnoreCase("F") ) || (!refNo.equalsIgnoreCase("") && !count.equalsIgnoreCase("0") && type.equalsIgnoreCase("M")))
		   {
			   String maxAmendSelect="select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"'";
			   String maxAmend = runner.singleSelection(maxAmendSelect);
			   String Qry = "SELECT PROMOTIONAL_CODE FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"' and amend_id="+maxAmend+"";;
			   String promotional = runner.singleSelection(Qry);
			   if(!"".equalsIgnoreCase(promotional) && promotional!=null){
				   String Qry1 = "SELECT COUNT(*) FROM PROMOTIONAL_RATE_MASTER WHERE PROMOTIONAL_CODE='"+promotional+"' AND STATUS='Y'";
				   String count1 = runner.singleSelection(Qry1);
				   if(count1.equalsIgnoreCase("0")){
					   result = "Given Promotional Number Not Exists";
				   }else{
					   String Qry2="SELECT  COUNT(*) FROM PROMOTIONAL_RATE_MASTER WHERE PROMOTIONAL_CODE='"+promotional+"' AND TRUNC(END_DATE) >= TRUNC(SYSDATE) AND STATUS='Y'";
					   String count2 = runner.singleSelection(Qry2);
					   if(count2.equalsIgnoreCase("0")){
						   result = "Given Promotional Number Was Expired";
					   }
				   }
			   }
		   }
		 
		 if(result.equalsIgnoreCase(""))
		 {
			 
			 System.out.println("Empty result");
			 result = quoteValidation(refNo);
			 if(result.equalsIgnoreCase(""))
			 {
				 quoteInsertion(refNo);
			 }
			
		 }
		return result;
	    
	}
	public void quoteInsertion(String refNo)
	{
		
		String maxAmendSelect="select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"'";
		String maxAmend = runner.singleSelection(maxAmendSelect);
		String maxAmendCmd="select max(amend_id) from webservice_marine_commodity_rw where reference_number='"+refNo+"'";
		String maxAmendCmdty = runner.singleSelection(maxAmendCmd);
		String selectOperation = "select type from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id="+maxAmend+"";
		String operation = runner.singleSelection(selectOperation);
		String countExists = "select count(*) from webservice_marine_quote where reference_number='"+refNo+"'";
		String exists = runner.singleSelection(countExists);
		
		if(operation.equalsIgnoreCase("F") || exists.equalsIgnoreCase("0")){
			 /////////INSERTION BLOCK
			System.out.println("<::::Inside Fresh Mode:::>");
			String selectBranch = "select b.branch_code from broker_company_master b , webservice_marine_quote_raw w where b.rsa_broker_code = w.broker_code and w.reference_number='"+refNo+"' and w.amend_id="+maxAmend+"";
			String branchCode = runner.singleSelection(selectBranch);
			String selectBelongingBranch = "select belonging_branch from branch_master where branch_code='"+branchCode+"'";
			String belongingBranch = runner.singleSelection(selectBelongingBranch);
			String selectCountry = "SELECT ORIGINATION_COUNTRY_ID from BRANCH_MASTER where BRANCH_CODE='"+branchCode+"'";
			String countryId = runner.singleSelection(selectCountry);
			System.out.println("countryId::>"+ countryId);
			String originCountryId = runner.singleSelection("select country_id from country_master where rsacode in (select ORIGINATING_COUNTRY from  webservice_marine_quote_raw w where  w.reference_number='"+refNo+"' and w.amend_id="+maxAmend+")");
			String destCountryId = runner.singleSelection("select country_id from country_master where rsacode in (select DESTINATION_COUNTRY from  webservice_marine_quote_raw w where  w.reference_number='"+refNo+"' and w.amend_id="+maxAmend+")");
			String qrySelection = "select  field_name,type,reference,add_refer_column,reference_table,reference_column from webservice_request_reader where status='Y' and request_id='101' order by id";
			System.out.println("Query selection::"+qrySelection);
			String fields[][] = runner.multipleSelection(qrySelection);
			String insertFieldNames = "insert into webservice_marine_quote( received_date" ;
			String insertValues = "values(sysdate";
			String insertQuery = ""; 
			String selectQuery = "select ";
			
			for(int k=0;k<fields.length;k++)
			{
				 if(k!=0){
			     selectQuery = selectQuery.concat(","+fields[k][0]);
				 }
				 else{
					 selectQuery = selectQuery.concat(fields[k][0]);
				 }
			}
			selectQuery = selectQuery.concat(" from webservice_marine_quote_raw where reference_number='"+refNo+"' and  amend_id="+maxAmend+""); 
			System.out.println("sel query"+ selectQuery);
			String finalValues[][] = runner.multipleSelection(selectQuery);
			System.out.println(finalValues);
			//String count2="select count(*) from webservice_request_reader where type='DATE' and request_id='101'";
			//int dates = Integer.parseInt(runner.singleSelection(count2));
			String values[]=new String[fields.length];
			System.out.println("fields.length"+fields.length);
			
			for(int k=0;k<fields.length;k++)
			{
				insertFieldNames=insertFieldNames.concat(" ,"+fields[k][0]);
				String type = fields[k][1]==null?"":fields[k][1];
				String refaddColumn = fields[k][3]==null?"":fields[k][3];
				String refColumn = fields[k][5]==null?"":fields[k][5];
				String refTable = fields[k][4]==null?"":fields[k][4];
				String reference = fields[k][2]==null?"":fields[k][2];
				String coverage ="";
				if(type.equalsIgnoreCase("DATE"))
				{
					//finalValues[0][k] = "TO_DATE(? ,'DD/MM/YYYY')";
					insertValues = insertValues.concat(",TO_DATE(? ,'DD/MM/YYYY')");
					//+finalValues[0][k]);
				}
				else if(!reference.equalsIgnoreCase(""))
				{
					String referenceCode = "";
					String amendment = "";
					if(refaddColumn.equalsIgnoreCase("BRANCH_CODE")){
						
						referenceCode = "and "+refaddColumn+" = '"+belongingBranch+"'";
					}
					else if(refaddColumn.equalsIgnoreCase("COUNTRY_ID") || refaddColumn.equalsIgnoreCase("BELONGING_COUNTRY_ID")){
						
						referenceCode = "and "+refaddColumn+" = '"+countryId+"'";
					}
					if((refaddColumn.equalsIgnoreCase("COUNTRY_ID") ) &&(fields[k][0].equalsIgnoreCase("ORIGINATING_COUNTRY_CITY") ))
					{
						referenceCode = "and "+refaddColumn+" = '"+originCountryId+"'";
					}
					if((refaddColumn.equalsIgnoreCase("COUNTRY_ID") )&&(fields[k][0].equalsIgnoreCase("DESTINATION_COUNTRY_CITY") ))
					{
					
						referenceCode = "and "+refaddColumn+" = '"+destCountryId+"'";
					}
					if(type!=null && type.equalsIgnoreCase("AMEND"))
					{
						amendment = " and amend_id = (select max(amend_id ) from "+refTable+" where " +
									(refaddColumn.equalsIgnoreCase("BRANCH_CODE")?(" branch_code = '"+belongingBranch+"' and "):"")+
									" rsacode='"+finalValues[0][k]+"')";
					}
					/*if(fields[k][0].equalsIgnoreCase("SEA_COVERAGES")){
						coverage=" and category_id=2";
					}
					else{
						coverage="";
					}*/
					String app="rsacode";
				
					String selectId = "select "+refColumn+" from "+refTable+" where status in ('Y','R') and "+app+"='"+finalValues[0][k]+"'"+referenceCode+amendment+coverage;
					
					if(refTable.trim().equalsIgnoreCase("CONVEYAN_MASTER")){
						System.out.println("boolean::"+refaddColumn.equalsIgnoreCase("BRANCH_CODE"));
						selectId ="select "+refColumn+" from "+refTable+" where status in ('Y','R') and " +
								" mode_transport_id=(select MODE_TRANSPORT_ID from MODE_OF_TRANSPORT where status in ('Y','R')" +
								" and rsacode=(select MODE_OF_TRANSPORT from webservice_marine_quote_raw where reference_number='"+refNo+"' and  amend_id="+maxAmend+" ) and BRANCH_CODE = '"+belongingBranch+"')" +
								" and core_app_id='"+finalValues[0][k]+"' "+referenceCode+amendment+coverage;
					}	
					System.out.println("IDS Selection"+selectId);	
					System.out.println("boolean::"+refaddColumn.equalsIgnoreCase("BRANCH_CODE"));
					
					//"select SNO from CONVEYAN_MASTER where status in ('Y','R') and core_app_id='11'and BRANCH_CODE = '01'";
					finalValues[0][k] = runner.singleSelection(selectId)==null?"":runner.singleSelection(selectId);

					insertValues = insertValues.concat(",?");
				}
				else
				{
					insertValues = insertValues.concat(",?");
				}
				values[k] = finalValues[0][k]==null?"":finalValues[0][k];
				
			}
			insertFieldNames = insertFieldNames.concat(")");
			insertValues = insertValues.concat(")");
			insertQuery = insertFieldNames+insertValues;
			System.out.println("Main table Insert Query ::>>>>"+insertQuery);
			runner.multipleUpdation(insertQuery, values);
			String updateProduct = " update webservice_marine_quote set status='' ,remarks='',product_id=(select  (case policy when 'C01' then '3' when 'C02' then '11' else null end) product from webservice_marine_quote where  reference_number='"+refNo+"') where reference_number='"+refNo+"'";
			runner.updation(updateProduct);
			
			
			 ///INSERTION COMMODITY
			 	String qrySelectionCommodity = "select  field_name,type,reference,add_refer_column,reference_table,reference_column from webservice_request_reader where status='Y' and request_id='102' order by id";
				System.out.println("Query selection Commodity::"+qrySelectionCommodity);
				String fieldsCmdty[][] = runner.multipleSelection(qrySelectionCommodity);
				
				String commodities[][]= runner.multipleSelection("select commodity_code from webservice_marine_commodity_rw where reference_number='"+refNo+"' and amend_id=" +maxAmendCmdty+"");
				for(int i=0;i<commodities.length;i++){
					
					String insertFieldNamesCmdty = "insert into webservice_marine_commodity( reference_number" ;
					String insertValuesCmdty = "values('"+refNo+"'";
					String insertQueryCmdty = ""; 
					String selectQueryCmdty = "select ";
					String commodityCode = commodities[i][0];
					for(int k=0;k<fieldsCmdty.length;k++)
					{
						 if(k!=0){
							 selectQueryCmdty = selectQueryCmdty.concat(","+fieldsCmdty[k][0]);
						 }
						 else{
							 selectQueryCmdty = selectQueryCmdty.concat(fieldsCmdty[k][0]);
						 }
					}
					selectQueryCmdty = selectQueryCmdty.concat(" from webservice_marine_commodity_rw where commodity_code='"+commodityCode+"' and reference_number='"+refNo+"' and amend_id=" +maxAmendCmdty+""); 
					System.out.println("sel insert values commodity query"+ selectQueryCmdty);
					String finalValuesCmdty[][] = runner.multipleSelection(selectQueryCmdty);
					System.out.println(finalValuesCmdty);
					//String countcmdty2="select count(*) from webservice_request_reader where type='DATE' and request_id='102'";
					//int datesCmdty = Integer.parseInt(runner.singleSelection(countcmdty2));
					String valuesCmdty[]=new String[fieldsCmdty.length];
					System.out.println("fieldsCmdty.length"+fieldsCmdty.length);
					
					
					for(int k=0;k<fieldsCmdty.length;k++)
					{
						insertFieldNamesCmdty=insertFieldNamesCmdty.concat(" ,"+fieldsCmdty[k][0]);
						String type = fieldsCmdty[k][1]==null?"":fieldsCmdty[k][1];
						String refaddColumn = fieldsCmdty[k][3]==null?"":fieldsCmdty[k][3];
						String refColumn = fieldsCmdty[k][5]==null?"":fieldsCmdty[k][5];
						String refTable = fieldsCmdty[k][4]==null?"":fieldsCmdty[k][4];
						String reference = fieldsCmdty[k][2]==null?"":fieldsCmdty[k][2];
						
						if(type.equalsIgnoreCase("DATE"))
						{
							//finalValues[0][k] = "TO_DATE(? ,'DD/MM/YYYY')";
							insertValuesCmdty = insertValuesCmdty.concat(",TO_DATE(? ,'DD/MM/YYYY')");
						}
						else if(!reference.equalsIgnoreCase(""))
						{
							String referenceCode="";
							String amendment="";
							if(refaddColumn.equalsIgnoreCase("BRANCH_CODE")){
								
								referenceCode = "and "+refaddColumn+" = '"+belongingBranch+"'";
							}
							else if(refaddColumn.equalsIgnoreCase("COUNTRY_ID") || refaddColumn.equalsIgnoreCase("BELONGING_COUNTRY_ID")){
								
								referenceCode = "and "+refaddColumn+" = '"+countryId+"'";
							}
							if(type!=null && type.equalsIgnoreCase("amend"))
							{
								amendment = " and amend_id = (select max(amend_id ) from "+refTable+" where  " +
											(refaddColumn.equalsIgnoreCase("BRANCH_CODE")?(" branch_code = '"+belongingBranch+"' and "):"")+
											" rsacode='"+finalValuesCmdty[0][k]+"')";
							}
							
							String selectId = "select "+refColumn+" from "+refTable+" where status in ('Y','R') and rsacode='"+finalValuesCmdty[0][k]+"'"+referenceCode+amendment;
							System.out.println("IDS Selection"+selectId);
							finalValuesCmdty[0][k] = runner.singleSelection(selectId)==null?"":runner.singleSelection(selectId);
							insertValuesCmdty = insertValuesCmdty.concat(",?");
							
							//valuesCmdty[l] = finalValuesCmdty[0][k]==null?"":finalValuesCmdty[0][k];
							
						}
						else
						{
							insertValuesCmdty = insertValuesCmdty.concat(",?");
							
						}
						valuesCmdty[k] = finalValuesCmdty[0][k]==null?"":finalValuesCmdty[0][k];
						
					}
					insertFieldNamesCmdty = insertFieldNamesCmdty.concat(")");
					insertValuesCmdty = insertValuesCmdty.concat(")");
					insertQueryCmdty = insertFieldNamesCmdty+insertValuesCmdty;
					System.out.println("Main table Insert Query Commodity ::>>>>"+insertQueryCmdty);
					runner.multipleUpdation(insertQueryCmdty, valuesCmdty);
					
			}
			 ///END INSERTION COMMODITY
				String saleTerm[][] = runner.multipleSelection("select saleterm_value,saleterm_percent from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=" +maxAmend);
				String saleTermVal = saleTerm[0][0];
				String saleTermPercent = saleTerm[0][1];
				//
				//runner.updation("update webservice_marine_quote q set saleterm_value=(select sale_term_id from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+branchCode+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				runner.updation("update webservice_marine_quote q set saleterm_value=(select RSACODE from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+belongingBranch+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				runner.updation("update webservice_marine_quote q set saleterm_percent=(select sale_term_id from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+belongingBranch+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				System.out.println("update webservice_marine_quote q set saleterm_percent=(select sale_term_id from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+belongingBranch+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				
		}
		else if(operation.equalsIgnoreCase("M") || !exists.equalsIgnoreCase("0")){
			String selCount = "select count(*) from webservice_marine_quote where reference_number='"+refNo+"'";
			String count = runner.singleSelection(selCount);
			if(!count.equalsIgnoreCase("0")){
				 /////////EDIT BLOCK
				System.out.println("<::::Inside Edit Mode:::>");
				String selectBranch = "select b.branch_code from broker_company_master b , webservice_marine_quote_raw w where b.rsa_broker_code = w.broker_code and w.reference_number='"+refNo+"'";
				String branchCode = runner.singleSelection(selectBranch);
				String selectBelongingBranch = "select belonging_branch from branch_master where branch_code='"+branchCode+"'";
				String belongingBranch = runner.singleSelection(selectBelongingBranch);
				String selectCountry = "SELECT ORIGINATION_COUNTRY_ID from BRANCH_MASTER where BRANCH_CODE='"+branchCode+"'";
				String countryId = runner.singleSelection(selectCountry);
				String originCountryId = runner.singleSelection("select country_id from country_master where rsacode in (select ORIGINATING_COUNTRY from  webservice_marine_quote_raw w where  w.reference_number='"+refNo+"' and w.amend_id="+maxAmend+")");
				String destCountryId = runner.singleSelection("select country_id from country_master where rsacode in (select DESTINATION_COUNTRY from  webservice_marine_quote_raw w where  w.reference_number='"+refNo+"' and w.amend_id="+maxAmend+")");
							
				System.out.println("countryId::>"+ countryId);
				String qrySelection = "select  field_name,type,reference,add_refer_column,reference_table,reference_column from webservice_request_reader where status='Y' and request_id='101' order by id";
				System.out.println("Query selection::"+qrySelection);
				String fields[][] = runner.multipleSelection(qrySelection);
				String updateFieldNames = "update webservice_marine_quote set received_date=sysdate" ;
			//	String insertValues = "values(sysdate";
				String updateValues="";
				String updateQuery = ""; 
				String selectQuery = "select ";
				
				for(int k=0;k<fields.length;k++)
				{
					 if(k!=0){
				     selectQuery = selectQuery.concat(","+fields[k][0]);
					 }
					 else{
						 selectQuery = selectQuery.concat(fields[k][0]);
					 }
				}
				selectQuery = selectQuery.concat(" from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=" +maxAmend+""); 
				System.out.println("sel query"+ selectQuery);
				String finalValues[][] = runner.multipleSelection(selectQuery);
				System.out.println(finalValues);
				//String count2="select count(*) from webservice_request_reader where type='DATE' and request_id='101'";
				//int dates = Integer.parseInt(runner.singleSelection(count2));
				String values[]=new String[fields.length];
				System.out.println("fields.length"+fields.length);
				//int j=0;
				
				for(int k=0;k<fields.length;k++)
				{
					
					String type = fields[k][1]==null?"":fields[k][1];
					String refaddColumn = fields[k][3]==null?"":fields[k][3];
					String refColumn = fields[k][5]==null?"":fields[k][5];
					String refTable = fields[k][4]==null?"":fields[k][4];
					String reference = fields[k][2]==null?"":fields[k][2];
					String referenceCode = "";
					String amendment = "";
					String coverage = "";
					if(type.equalsIgnoreCase("DATE"))
					{
						//finalValues[0][k] = "TO_DATE(? ,'DD/MM/YYYY')";
						updateValues = "TO_DATE(? ,'DD/MM/YYYY')";
					}
					else if(!reference.equalsIgnoreCase(""))
					{
						if(refaddColumn.equalsIgnoreCase("BRANCH_CODE")){
							
							referenceCode = "and "+refaddColumn+" = '"+belongingBranch+"'";
						}
						else if(refaddColumn.equalsIgnoreCase("COUNTRY_ID") || refaddColumn.equalsIgnoreCase("BELONGING_COUNTRY_ID")){
							
							referenceCode = "and "+refaddColumn+" = '"+countryId+"'";
						}
						if((refaddColumn.equalsIgnoreCase("COUNTRY_ID") ) &&(fields[k][0].equalsIgnoreCase("ORIGINATING_COUNTRY_CITY") ))
						{
							referenceCode = "and "+refaddColumn+" = '"+originCountryId+"'";
						}
						if((refaddColumn.equalsIgnoreCase("COUNTRY_ID") )&&(fields[k][0].equalsIgnoreCase("DESTINATION_COUNTRY_CITY") ))
						{
						
							referenceCode = "and "+refaddColumn+" = '"+destCountryId+"'";
						}
						if(type!=null && type.equalsIgnoreCase("amend"))
						{
							amendment = " and amend_id = (select max(amend_id ) from "+refTable+" where " +
							(refaddColumn.equalsIgnoreCase("BRANCH_CODE")?(" branch_code = '"+belongingBranch+"' and "):"")+
							" rsacode='"+finalValues[0][k]+"')";
							//amendment = " and amend_id = (select max(amend_id ) from "+refTable+" where  rsacode='"+finalValues[0][k]+"')";
						}
						/*if(fields[k][0].equalsIgnoreCase("SEA_COVERAGES")){
							coverage=" and category_id=2";
						}
						else{
							coverage="";
						}*/
						String appId = "rsacode";
						if(refTable.trim().equalsIgnoreCase("CONVEYAN_MASTER")){
							appId = "core_app_id";
						}
						String selectId = "select "+refColumn+" from "+refTable+" where status in ('Y','R') and "+appId+"='"+finalValues[0][k]+"' "+referenceCode+amendment+coverage;
						
						if(refTable.trim().equalsIgnoreCase("CONVEYAN_MASTER")){
							System.out.println("boolean::"+refaddColumn.equalsIgnoreCase("BRANCH_CODE"));
							selectId ="select "+refColumn+" from "+refTable+" where status in ('Y','R') and " +
									" mode_transport_id=(select MODE_TRANSPORT_ID from MODE_OF_TRANSPORT where status in ('Y','R')" +
									" and rsacode=(select MODE_OF_TRANSPORT from webservice_marine_quote_raw where reference_number='"+refNo+"' and  amend_id="+maxAmend+" ) and BRANCH_CODE = '"+belongingBranch+"')" +
									" and core_app_id='"+finalValues[0][k]+"' "+referenceCode+amendment+coverage;
						}
						
						System.out.println("IDS Selection"+selectId);
						finalValues[0][k] = runner.singleSelection(selectId)==null?"":runner.singleSelection(selectId);
						updateValues = "?";
						
					}
					else
					{
						updateValues = "?";
						
					}
					values[k] = finalValues[0][k]==null?"":finalValues[0][k];
					
					updateFieldNames=updateFieldNames.concat(" ,"+fields[k][0]+"="+updateValues);
				}
				//updateFieldNames = updateFieldNames.concat(")");
				//insertValues = insertValues.concat(")");
				updateQuery = updateFieldNames+ " where reference_number='"+refNo+"'" ;
				System.out.println("Main table Update Query ::>>>>"+updateQuery);
				runner.multipleUpdation(updateQuery, values);
				
				String updateProduct = " update webservice_marine_quote set status='' ,remarks='',product_id=(select  (case policy when 'C01' then '3' when 'C02' then '11' else null end) product from webservice_marine_quote where  reference_number='"+refNo+"') where reference_number='"+refNo+"'";
				runner.updation(updateProduct);
				
				

				 ///INSERTION EDIT COMMODITY
				 	String qrySelectionCommodity = "select  field_name,type,reference,add_refer_column,reference_table,reference_column from webservice_request_reader where status='Y' and request_id='102' order by id";
					System.out.println("Query selection Commodity::"+qrySelectionCommodity);
					String fieldsCmdty[][] = runner.multipleSelection(qrySelectionCommodity);
					runner.updation("delete from webservice_marine_commodity where reference_number='"+refNo+"'");
					String commodities[][]= runner.multipleSelection("select commodity_code from webservice_marine_commodity_rw where reference_number='"+refNo+"' and amend_id=" +maxAmendCmdty+"");
					for(int i=0;i<commodities.length;i++){
						
						String insertFieldNamesCmdty = "insert into webservice_marine_commodity( reference_number" ;
						String insertValuesCmdty = "values('"+refNo+"'";
						String insertQueryCmdty = ""; 
						String selectQueryCmdty = "select ";
						String commodityCode = commodities[i][0];
						for(int k=0;k<fieldsCmdty.length;k++)
						{
							 if(k!=0){
								 selectQueryCmdty = selectQueryCmdty.concat(","+fieldsCmdty[k][0]);
							 }
							 else{
								 selectQueryCmdty = selectQueryCmdty.concat(fieldsCmdty[k][0]);
							 }
						}
						selectQueryCmdty = selectQueryCmdty.concat(" from webservice_marine_commodity_rw where commodity_code='"+commodityCode+"' and reference_number='"+refNo+"' and amend_id=" +maxAmendCmdty+""); 
						System.out.println("sel insert values commodity query"+ selectQueryCmdty);
						String finalValuesCmdty[][] = runner.multipleSelection(selectQueryCmdty);
						System.out.println(finalValuesCmdty);
						//String countcmdty2="select count(*) from webservice_request_reader where type='DATE' and request_id='102'";
						//int datesCmdty = Integer.parseInt(runner.singleSelection(countcmdty2));
						String valuesCmdty[]=new String[fieldsCmdty.length];
						System.out.println("fieldsCmdty.length"+fieldsCmdty.length);
						
						
						for(int k=0;k<fieldsCmdty.length;k++)
						{
							insertFieldNamesCmdty=insertFieldNamesCmdty.concat(" ,"+fieldsCmdty[k][0]);
							String type = fieldsCmdty[k][1]==null?"":fieldsCmdty[k][1];
							String refaddColumn = fieldsCmdty[k][3]==null?"":fieldsCmdty[k][3];
							String refColumn = fieldsCmdty[k][5]==null?"":fieldsCmdty[k][5];
							String refTable = fieldsCmdty[k][4]==null?"":fieldsCmdty[k][4];
							String reference = fieldsCmdty[k][2]==null?"":fieldsCmdty[k][2];
							
							if(type.equalsIgnoreCase("DATE"))
							{
								//finalValues[0][k] = "TO_DATE(? ,'DD/MM/YYYY')";
								insertValuesCmdty = insertValuesCmdty.concat(",TO_DATE(? ,'DD/MM/YYYY')");
							}
							else if(!reference.equalsIgnoreCase(""))
							{
								String referenceCode="";
								String amendment="";
								if(refaddColumn.equalsIgnoreCase("BRANCH_CODE")){
									
									referenceCode = "and "+refaddColumn+" = '"+belongingBranch+"'";
								}
								else if(refaddColumn.equalsIgnoreCase("COUNTRY_ID") || refaddColumn.equalsIgnoreCase("BELONGING_COUNTRY_ID")){
									
									referenceCode = "and "+refaddColumn+" = '"+countryId+"'";
								}
								if(type!=null && type.equalsIgnoreCase("amend"))
								{
									amendment = " and amend_id = (select max(amend_id ) from "+refTable+" where  rsacode='"+finalValuesCmdty[0][k]+"')";
								}
								
								
								String selectId = "select "+refColumn+" from "+refTable+" where status in ('Y','R') and rsacode='"+finalValuesCmdty[0][k]+"'"+referenceCode+amendment;
								System.out.println("IDS Selection"+selectId);
								finalValuesCmdty[0][k] = runner.singleSelection(selectId)==null?"":runner.singleSelection(selectId);
								insertValuesCmdty = insertValuesCmdty.concat(",?");
								
								//valuesCmdty[l] = finalValuesCmdty[0][k]==null?"":finalValuesCmdty[0][k];
								
							}
							else
							{
								insertValuesCmdty = insertValuesCmdty.concat(",?");
								
							}
							valuesCmdty[k] = finalValuesCmdty[0][k]==null?"":finalValuesCmdty[0][k];
							
						}
						insertFieldNamesCmdty = insertFieldNamesCmdty.concat(")");
						insertValuesCmdty = insertValuesCmdty.concat(")");
						insertQueryCmdty = insertFieldNamesCmdty+insertValuesCmdty;
						System.out.println("Main table Insert Query Commodity ::>>>>"+insertQueryCmdty);
						runner.multipleUpdation(insertQueryCmdty, valuesCmdty);
						
				}
				 ///END INSERTION EDIT COMMODITY
			/*	//Commodity

				 /////////EDIT BLOCK
				System.out.println("<::::Inside Edit Commodity Mode:::>");
				
				String qrySelectioncmdty = "select  field_name,type,reference,add_refer_column,reference_table,reference_column from webservice_request_reader where status='Y' and request_id='102' order by id";
				System.out.println("Query selection commodity: :"+qrySelectioncmdty);
				String fieldsCommodity[][] = runner.multipleSelection(qrySelectioncmdty);
				
				String commodities[][]= runner.multipleSelection("select commodity_code from webservice_marine_commodity where reference_number='"+refNo+"'");
				for(int i=0;i<commodities.length;i++){
					//	String insertValues = "values(sysdate";
					String commodityCode = commodities[i][0];
					String updateCmdtyValues="";
					String updateCmdtyQuery = ""; 
					String selectCmdtyQuery = "select ";

					String updateCmdtyFieldNames = "update webservice_marine_commodity set reference_number='"+refNo+"'" ;
					for(int k=0;k<fieldsCommodity.length;k++)
					{
						 if(k!=0){
							 selectCmdtyQuery = selectCmdtyQuery.concat(","+fieldsCommodity[k][0]);
						 }
						 else{
							 selectCmdtyQuery = selectCmdtyQuery.concat(fieldsCommodity[k][0]);
						 }
					}
					selectCmdtyQuery = selectCmdtyQuery.concat(" from webservice_marine_commodity_rw where commodity_code=(select unique rsacode from commoditymaster where amend_id=(select max(amend_id) from commoditymaster where branch_code='"+branchCode+"' and commodity_id='"+commodityCode+"') and commodity_id='"+commodityCode+"' and branch_code='"+branchCode+"' ) and reference_number='"+refNo+"' and amend_id=" +maxAmendCmdty+""); 
					System.out.println("sel commodity query"+ selectCmdtyQuery);
					String finalCmdtyValues[][] = runner.multipleSelection(selectCmdtyQuery);
					System.out.println(finalCmdtyValues);
					//String countCmdty2="select count(*) from webservice_request_reader where type='DATE' and request_id='102'";
					//int datesCmdty = Integer.parseInt(runner.singleSelection(countCmdty2));
					String valuesCmdty[]=new String[fieldsCommodity.length];
					System.out.println("fields.length of commodity"+fieldsCommodity.length);
					
					for(int k=0;k<fieldsCommodity.length;k++)
					{
						
						String type = fieldsCommodity[k][1]==null?"":fieldsCommodity[k][1];
						String refaddColumn = fieldsCommodity[k][3]==null?"":fieldsCommodity[k][3];
						String refColumn = fieldsCommodity[k][5]==null?"":fieldsCommodity[k][5];
						String refTable = fieldsCommodity[k][4]==null?"":fieldsCommodity[k][4];
						String reference = fieldsCommodity[k][2]==null?"":fieldsCommodity[k][2];
						
						if(type.equalsIgnoreCase("DATE"))
						{
							//finalCmdtyValues[0][k] = "TO_DATE(? ,'DD/MM/YYYY')";
							updateCmdtyValues = "TO_DATE(? ,'DD/MM/YYYY')";
						}
						else if(!reference.equalsIgnoreCase(""))
						{
							String referenceCode="";
							String amendment="";
							if(refaddColumn.equalsIgnoreCase("BRANCH_CODE")){
								
								referenceCode = "and "+refaddColumn+" = '"+branchCode+"'";
							}
							else if(refaddColumn.equalsIgnoreCase("COUNTRY_ID") || refaddColumn.equalsIgnoreCase("BELONGING_COUNTRY_ID")){
								
								referenceCode = "and "+refaddColumn+" = '"+countryId+"'";
							}
							if(type!=null && type.equalsIgnoreCase("amend"))
							{
								amendment = " and amend_id = (select max(amend_id ) from "+refTable+" where  rsacode='"+finalCmdtyValues[0][k]+"')";
							}
							String selectId = "select "+refColumn+" from "+refTable+" where status in ('Y','R') and rsacode='"+finalCmdtyValues[0][k]+"'"+referenceCode+amendment;
							System.out.println("IDS Selection"+selectId);
							finalCmdtyValues[0][k] = runner.singleSelection(selectId)==null?"":runner.singleSelection(selectId);
							updateCmdtyValues = "?";
							//valuesCmdty[l] = finalCmdtyValues[0][k]==null?"":finalCmdtyValues[0][k];
							//l++;
						}
						else
						{
							updateCmdtyValues = "?";
							
						}
						valuesCmdty[k] = finalCmdtyValues[0][k]==null?"":finalCmdtyValues[0][k];
						
						updateCmdtyFieldNames=updateCmdtyFieldNames.concat(" ,"+fieldsCommodity[k][0]+"="+updateCmdtyValues);
					} 
					//updateFieldNames = updateFieldNames.concat(")");
					//insertValues = insertValues.concat(")");
					updateCmdtyQuery = updateCmdtyFieldNames+ " where commodity_code = '"+commodityCode+"' and reference_number='"+refNo+"'" ;
					System.out.println("Main table Update Query ::>>>>"+updateCmdtyQuery);
					runner.multipleUpdation(updateCmdtyQuery, valuesCmdty);
				}*/
			//commodity ends
				String saleTerm[][] = runner.multipleSelection("select saleterm_value,saleterm_percent from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=" +maxAmend);
				String saleTermVal = saleTerm[0][0];
				String saleTermPercent = saleTerm[0][1];
				//Modified to store sale_term_id in sale_term_percent field by chinna
				//runner.updation("update webservice_marine_quote q set saleterm_value=(select sale_term_id from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+branchCode+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				runner.updation("update webservice_marine_quote q set saleterm_value=(select RSACODE from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+belongingBranch+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				runner.updation("update webservice_marine_quote q set saleterm_percent=(select sale_term_id from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+belongingBranch+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				System.out.println("update webservice_marine_quote q set saleterm_percent=(select sale_term_id from sale_term_master where sale_term_value='"+saleTermPercent+"' and branch_code ='"+belongingBranch+"' and rsacode='"+saleTermVal+"') where q.reference_number='"+refNo+"'");
				// Default WSRCC Update for Open Cover 
				
			}
		}
		//update Branch Code
		 String updateBranch = "update webservice_marine_quote w set branch_code=(select branch_code from webservice_marine_quote_raw where amend_id='"+maxAmend+"' and reference_number='"+refNo+"' ) where reference_number='"+refNo+"' " ;
	 		//"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
		 runner.updation(updateBranch);
		 
		/* String commodityQuery = "select commodity_code, commodity_description, supplier_name, invoice_number, package_description from webservice_marine_commodity where reference_number='"+refNo+"'";
		 String commodityList[][] = runner.multipleSelection(commodityQuery);
		 
		 for(int k=0; k<commodityList.length; k++){
			 //commodity_description, supplier_name, invoice_number, package_description;
			 String updateCommodityDesc = "update webservice_marine_commodity set  commodity_description=?, supplier_name=?, invoice_number=?, package_description=? where reference_number='"+refNo+"' and commodity_code=?";
		 		//"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			 runner.multipleUpdation(updateCommodityDesc, new String []{commodityList[k][1].replaceAll("&apos;", "'").replaceAll("&quot;", "\""), commodityList[k][2].replaceAll("&apos;", "'").replaceAll("&quot;", "\""), commodityList[k][3].replaceAll("&apos;", "'").replaceAll("&quot;", "\""), commodityList[k][4].replaceAll("&apos;", "'").replaceAll("&quot;", "\""), commodityList[k][0]});
		 }*/
		
		 //update conveyance
			String updateConveyance = " update webservice_marine_quote set sea_coverages='0' where sea_coverages is null and reference_number='"+refNo+"'";
			runner.updation(updateConveyance);
		
	  // update executive 
			String updateExecutive = "update webservice_marine_quote wr set wr.AC_EXECUTIVE_ID = (select AC_EXECUTIVE_ID FROM BROKER_COMPANY_MASTER WHERE RSA_BROKER_CODE= wr.BROKER_CODE) where reference_number='"+refNo+"'";
			runner.updation(updateExecutive);
		// Update Broker Code
		runner.updation("update webservice_marine_quote q set " +
				" broker_code=(select agency_code from broker_company_master bc where q.broker_code =bc.rsa_broker_code) where q.reference_number='"+refNo+"'");
		
		String selectLoginId = "select distinct l.login_id from login_user_details l , webservice_marine_quote q where l.agency_code = q.broker_code and q.reference_number='"+refNo+"'";
		String loginID = runner.singleSelection(selectLoginId);
		 
		 String updateCustomerId = "  update webservice_marine_quote q set login_id='"+loginID+"' , customer_id=(select distinct p.customer_id from personal_info p where p.client_customer_id=q.client_customer_id and  login_id='"+loginID+"') where q.reference_number='"+refNo+"' and q.client_customer_id in(select distinct client_customer_id from personal_info where login_id='"+loginID+"')";
		 System.out.println("Update cust::>"+updateCustomerId);
		 runner.updation(updateCustomerId);
		 
		 String updateOpenCover = "update webservice_marine_quote set OPENCOVER_POLICY_NUMBER=(select OPENCOVER_POLICY_NUMBER from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=" +maxAmend+") where reference_number='"+refNo+"'";
		 System.out.println("Update OpenCover::"+updateOpenCover);
		 runner.updation(updateOpenCover);
		 String updateFrgile = "update webservice_marine_commodity set fragile='off' where fragile is null or upper(fragile)<>'YES' and  reference_number='"+refNo+"'";
		 System.out.println("Update updateFrgile::"+updateFrgile);
		 runner.updation(updateFrgile);
		 
		 String otherCities[][] = runner.multipleSelection("select ORIGINATING_COUNTRY_CITY,DESTINATION_COUNTRY_CITY from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=" +maxAmend);
		 
		 if((otherCities[0][0]==null?"":otherCities[0][0]).equalsIgnoreCase("999")){
		 String updateCityOthersOrg = "update webservice_marine_quote set ORIGINATING_COUNTRY_CITY='0'  where reference_number='"+refNo+"'";
		 System.out.println("Update updateCityOthersOrg::"+updateCityOthersOrg);
		 runner.updation(updateCityOthersOrg);
		 }
		 if((otherCities[0][1]==null?"":otherCities[0][1]).equalsIgnoreCase("999")){
		 String updateCityOtherDest = "update webservice_marine_quote set DESTINATION_COUNTRY_CITY='0' where  reference_number='"+refNo+"'";
		 System.out.println("Update updateCityOtherDest::"+updateCityOtherDest);
		 runner.updation(updateCityOtherDest);
		 }
		 try {
			runner.updation("UPDATE WEBSERVICE_MARINE_QUOTE WR SET " +
						"  WR.WSRCC=(SELECT (case when OCM.WRSC_YN ='Y' then 'Yes' else 'No' end) FROM OPEN_COVER_MASTER OCM"+
						"  WHERE OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
						"  AND OCM.MISSIPPI_OPENCOVER_NO=WR.OPENCOVER_POLICY_NUMBER) where WR.OPENCOVER_POLICY_NUMBER is not null and WR.reference_number='"+refNo+"'");
				System.out.println("Query wsrcc---->UPDATE WEBSERVICE_MARINE_QUOTE WR SET " +
				"  WR.WSRCC=(SELECT (case when OCM.WRSC_YN ='Y' then 'Yes' else 'No' end) FROM OPEN_COVER_MASTER OCM"+
				"  WHERE OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"  AND OCM.MISSIPPI_OPENCOVER_NO=WR.OPENCOVER_POLICY_NUMBER) where WR.OPENCOVER_POLICY_NUMBER is not null and wr.reference_number='"+refNo+"'");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	 //////////END INSERTION BLOCK	
		
	}
	public String quoteValidation(String refNo)
	{
		String inValid="";
		try {
			System.out.println("Length validation starts:::");
			//Length Validation
			String validLength = "";
			String selectFieldsLength = "select table_name ,field_name,tag_value,data_length from webservice_request_reader where validation_required='Y' and status='Y' and data_length is not null";
			String lengthFields[][] = runner.multipleSelection(selectFieldsLength);
			
			for(int i=0;i<lengthFields.length;i++){
				validLength =	"update "+lengthFields[i][0]+" w " +
				 		" set validation_detail=validation_detail||',"+lengthFields[i][2]+" exceeds length'" +
				 		" where  length("+lengthFields[i][1]+")>"+lengthFields[i][3]+"   " +
				  		" and reference_number='"+refNo+"' " +
				  		"and w.amend_id=(select max(amend_id) from "+lengthFields[i][0]+" where reference_number='"+refNo+"')";
				runner.updation(validLength);
					
			}
			
			//Email Validation
			String validEmail = "";
			String selectFieldsEmail = "select table_name ,field_name,tag_value from webservice_request_reader where validation_required='Y' and status='Y' and  type='EMAIL'";
			System.out.println("Email validatioin::"+ selectFieldsEmail);
			String email[][] = runner.multipleSelection(selectFieldsEmail);
			
			for(int i=0;i<email.length;i++){
								
				String selEmail = "select "+email[i][1]+" from "+email[i][0]+" w where  reference_number='"+refNo+"' " +
				  		"and w.amend_id=(select max(amend_id) from "+email[i][0]+" where reference_number='"+refNo+"')";
				String emailValue = runner.singleSelection(selEmail);
				System.out.println(emailValidate(emailValue)+"<==");
				if(!emailValidate(emailValue).equalsIgnoreCase("")){
				validEmail =	"update "+email[i][0]+" w " +
				 		" set validation_detail=validation_detail||',"+email[i][2]+" is invalid '" +
				 		" where  reference_number='"+refNo+"' " +
				  		"and w.amend_id=(select max(amend_id) from "+email[i][0]+" where reference_number='"+refNo+"')";
				
				
				System.out.println("Email qry::>"+validEmail);
				runner.updation(validEmail);
				}
					
			}
			
			String saleTerm =runner.singleSelection("select saleterm_value from webservice_marine_quote_raw w where w.reference_number='"+refNo+"' and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ")==null?"":runner.singleSelection("select saleterm_value from webservice_marine_quote_raw w where w.reference_number='"+refNo+"' and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
						
			//Mandatory Validation
			String validMandatory = "";
			String policycode  = "select policy from  webservice_marine_quote_raw w" +
				"  where  " +
				"  reference_number='"+refNo+"'" +
				" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			String policyType = runner.singleSelection(policycode);
			System.out.println("policycode::"+policyType);
			
			String selectFieldsMandatory = "select table_name ,field_name,tag_value from webservice_request_reader where validation_required='Y' and status='Y' and  mandatory='Y'";
			String mandatories[][] = runner.multipleSelection(selectFieldsMandatory);
			
			for(int i=0;i<mandatories.length;i++){
				validMandatory =	"update "+mandatories[i][0]+" w " +
				 		" set validation_detail=validation_detail||',"+mandatories[i][2]+" is mandatory'" +
				 		" where  "+mandatories[i][1]+" IS NULL  " +
				  		" and reference_number='"+refNo+"' " +
				  		"and w.amend_id=(select max(amend_id) from "+mandatories[i][0]+" where reference_number='"+refNo+"')";
				if(!( saleTerm.equalsIgnoreCase("13") && mandatories[i][1].equalsIgnoreCase("TOLERANCE"))
					&& !( policyType.equalsIgnoreCase("C02") && mandatories[i][1].equalsIgnoreCase("SEA_COVERAGES"))
					&& !( policyType.equalsIgnoreCase("C01") && mandatories[i][1].equalsIgnoreCase("LC_NUMBER"))){
				runner.updation(validMandatory);
				System.out.println("validMandatory::>"+validMandatory);
				}
					
			}
			//Number Validation
			String validNumbers = "";
			String selectFieldsNumber = "select table_name ,field_name,tag_value from webservice_request_reader where validation_required='Y' and  status='Y' and type='NUMBER'";
			String numberFields[][] = runner.multipleSelection(selectFieldsNumber);
			if(numberFields.length>0){
			for(int i=0;i<numberFields.length;i++){
				validNumbers =	"update "+numberFields[i][0]+" w " +
				 		" set validation_detail=validation_detail||',"+numberFields[i][2]+" should be number'" +
				 		" where  "+numberFields[i][1]+
				 		" in (select case when instr(translate( "+numberFields[i][1]+","+
						"'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
						"'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'),'X')=0 " +
						" then 'no' else "+numberFields[i][1]+"  end FROM "
						+numberFields[i][0]+") and reference_number='"+refNo+"' " +
						"and w.amend_id=(select max(amend_id) from "+numberFields[i][0]+" where reference_number='"+refNo+"')";
				System.out.println(validNumbers);
				runner.updation(validNumbers);
					
			}
			
			}
			/*Date Validation *///Format:: DD/MM/YYYY///
			String selectFieldsDate = "select table_name ,field_name,tag_value from webservice_request_reader where  validation_required='Y' and status='Y' and type='DATE'";
			String dateFields[][] = runner.multipleSelection(selectFieldsDate);
			if(dateFields.length>0){
		   
		    String lenValue  = "=4";
		    for(int i=0;i<dateFields.length;i++){
			    String DB_COLUMN_NAME = dateFields[i][1];
				String dateValid = "update  "+dateFields[i][0]+" w set " +
				"validation_detail=validation_detail||'," +
				" "+dateFields[i][2]+" is not DD/MM/YYYY Format' where  ("+DB_COLUMN_NAME+""
	            + " in (select case when (length(substr("
	            + DB_COLUMN_NAME
	            + ",1,"
	            + "instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,1)-1))=2  or length(substr("
	            + DB_COLUMN_NAME
	            + ",1,"
	            + "instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,1)-1))=1 )  and (length(substr("
	            + DB_COLUMN_NAME
	            + ","
	            + "instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,1)+1,(instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,2)-"
	            + "instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,1))-1))=2 or length(substr("
	            + DB_COLUMN_NAME
	            + ","
	            + "instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,1)+1,(instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,2)-"
	            + "instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,1))-1))=1 ) and (length(substr("
	            + DB_COLUMN_NAME + "," + "instr("
	            + DB_COLUMN_NAME
	            + ",'/',1,2)+1,length("
	            + DB_COLUMN_NAME + ")))" + lenValue
	            + " ) then 'no' else "
	            + DB_COLUMN_NAME + " end from "
	            + dateFields[i][0]
	            + " where  "
	            + DB_COLUMN_NAME
	            + " is not null)" +
        		" or  (SUBSTR (" +
        		""+ DB_COLUMN_NAME +"," +
        		"INSTR ("+ DB_COLUMN_NAME +"," +
        		"'/', 1,1 ) + 1,"+
                "(INSTR ("+ DB_COLUMN_NAME +",'/',  1,2 )-" +
                " INSTR ("+ DB_COLUMN_NAME +",'/',1, 1 ))- 1 ))>12) " +
        		" and reference_number='"+refNo+"' " +
	            "and w.amend_id=(select max(amend_id) from "+dateFields[i][0]+" where reference_number='"+refNo+"') " +
	            " and reference_number='"+refNo+"'  ";
				
				System.out.println("Date Validation query::>"+dateValid);
				runner.updation(dateValid);
		    }
			}
			
			//// Updating branch code and product id 
			 String validProduct = "update webservice_marine_quote_raw w" +
				" set validation_detail=validation_detail||' ,Not valid Policy code'" +
				" where  policy NOT IN  ('C01','C02')" +
				" and reference_number='"+refNo+"'" +
				" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			 runner.updation(validProduct);
			 
			 String validBroker = "update webservice_marine_quote_raw w" +
				" set validation_detail=validation_detail||' ,Brokercode not valid'" +
				" where  broker_code NOT IN  (select distinct rsa_broker_code from " +
				" webservice_marine_quote_raw,broker_company_master where broker_company_master.status='Y' and " +
				" broker_company_master.rsa_broker_code= webservice_marine_quote_raw.broker_code)" +
				" and reference_number='"+refNo+"' " +
				" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			 runner.updation(validBroker);
			 
	
			
			String selectBranch = "select b.branch_code from broker_company_master b , webservice_marine_quote_raw w where b.status='Y' and b.rsa_broker_code = w.broker_code and w.reference_number='"+refNo+"'" +
					"and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			String branchCode = runner.singleSelection(selectBranch);
			String selectBelongingBranch = "select belonging_branch from branch_master where branch_code='"+branchCode+"'";
			String belongingBranch = runner.singleSelection(selectBelongingBranch);
			System.out.println("Branch Code::>"+ branchCode);
			
			
			
			String selectCountry = "SELECT ORIGINATION_COUNTRY_ID from BRANCH_MASTER where BRANCH_CODE='"+branchCode+"'";
			String countryId = runner.singleSelection(selectCountry);
			
			String selCountry =  "select ORIGINATING_COUNTRY,DESTINATION_COUNTRY,ORIGINATING_COUNTRY_CITY, DESTINATION_COUNTRY_CITY,MODE_OF_TRANSPORT,POLICY_START_DATE from webservice_marine_quote_raw w where w.reference_number='"+refNo+"'" +
				"and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			String country[][] = runner.multipleSelection(selCountry);
			String originCountry = country[0][0]==null?"":country[0][0];
			String destCountry = country[0][1]==null?"":country[0][1];
			
			String originCountryId =runner.singleSelection("select country_id from country_master where rsacode='"+originCountry+"' and amend_id=(select max(amend_id) from country_master where rsacode='"+originCountry+"' ) and status in ('Y','R')");
			
			String destCountryId =runner.singleSelection("select country_id from country_master where rsacode='"+destCountry+"' and amend_id=(select max(amend_id) from country_master where rsacode='"+destCountry+"' ) and status in ('Y','R')");
			
			String originCountryCityId =  country[0][2]==null?"":country[0][2];
			String destCountryCityId =  country[0][3]==null?"":country[0][3];
			String modeOfTransRSA =  country[0][4]==null?"":country[0][4];
			String policyStartDate =  country[0][5]==null?"":country[0][5];
			
			//String modeOfTrans = runner.singleSelection("select mode_transport_id from mode_of_transport where branch_code='"+branchCode+"' and status='Y' and rsacode='"+modeOfTransRSA+"'");
			String modeOfTrans = runner.singleSelection("select mode_transport_id from mode_of_transport where branch_code=(select belonging_branch from branch_master where branch_code='"+branchCode+"') and status='Y' and rsacode='"+modeOfTransRSA+"'");
			System.out.println("MODE ::"+modeOfTrans);
			if(modeOfTrans.trim().equalsIgnoreCase("3")){
			// Origin Ware house validation for LAND
			runner.updation("update webservice_marine_quote_raw w" +
						" set validation_detail=validation_detail||' ,Origin Ware house Mandatory'" +
						" where  trim(upper(ORIGINATING_WAREHOUSE_COVERAGE)) ='NO' " +
						" and reference_number='"+refNo+"' " +
						" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			
			runner.updation("update webservice_marine_quote_raw w" +
					" set validation_detail=validation_detail||' ,Destination Ware house Mandatory'" +
					" where  trim(upper(DESTINATION_WAREHOUSE_COVERAGE)) ='NO' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
		    }
			
	         // Origin city name validation
			logger.info("Origin City Name Validation==> " + "update webservice_marine_quote_raw w" +
					" set validation_detail=validation_detail||' ,OriginCityName Mandatory'" +
					" where  origin_city_name is null and ORIGINATING_COUNTRY_CITY ='999' and trim(upper(ORIGINATING_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			runner.updation("update webservice_marine_quote_raw w" +
					" set validation_detail=validation_detail||' ,OriginCityName Mandatory'" +
					" where  origin_city_name is null and ORIGINATING_COUNTRY_CITY ='999' and trim(upper(ORIGINATING_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			
			logger.info("Origin City Name Validation==> " +"update webservice_marine_quote_raw w" +
					" set ORIGIN_CITY_NAME=(select CITY_NAME from CITY_MASTER WHERE COUNTRY_ID='"+originCountryId+"' and RSACODE='"+originCountryCityId+"') " +
					" where  origin_city_name is null and ORIGINATING_COUNTRY_CITY !='999' and trim(upper(ORIGINATING_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			runner.updation("update webservice_marine_quote_raw w" +
						" set ORIGIN_CITY_NAME=(select CITY_NAME from CITY_MASTER WHERE COUNTRY_ID='"+originCountryId+"' and RSACODE='"+originCountryCityId+"') " +
						" where  origin_city_name is null and ORIGINATING_COUNTRY_CITY !='999' and trim(upper(ORIGINATING_WAREHOUSE_COVERAGE)) ='YES' " +
						" and reference_number='"+refNo+"' " +
						" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			
			// Destination city name validation
			logger.info("Destination City Name Validation==> " + "update webservice_marine_quote_raw w" +
					" set validation_detail=validation_detail||' ,DestintionCityName Mandatory'" +
					" where  dest_city_name is null and DESTINATION_COUNTRY_CITY ='999' and trim(upper(DESTINATION_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			runner.updation("update webservice_marine_quote_raw w" +
						" set validation_detail=validation_detail||' ,DestintionCityName Mandatory'" +
						" where  dest_city_name is null and DESTINATION_COUNTRY_CITY ='999' and trim(upper(DESTINATION_WAREHOUSE_COVERAGE)) ='YES' " +
						" and reference_number='"+refNo+"' " +
						" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			logger.info("Destination City Name Validation==> " + "update webservice_marine_quote_raw w" +
					" set DEST_CITY_NAME=(select CITY_NAME from CITY_MASTER WHERE COUNTRY_ID='"+destCountryId+"' and RSACODE='"+destCountryCityId+"') " +
					" where  dest_city_name is null and DESTINATION_COUNTRY_CITY !='999' and trim(upper(DESTINATION_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			runner.updation("update webservice_marine_quote_raw w" +
					" set DEST_CITY_NAME=(select CITY_NAME from CITY_MASTER WHERE COUNTRY_ID='"+destCountryId+"' and RSACODE='"+destCountryCityId+"') " +
					" where  dest_city_name is null and DESTINATION_COUNTRY_CITY !='999' and trim(upper(DESTINATION_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			
			//Warehouse Origin validation
			runner.updation("update webservice_marine_quote_raw w" +
					" set validation_detail=validation_detail||' ,OriginatingCountryCity Mandatory'" +
					" where  policy='C01' and ORIGINATING_COUNTRY_CITY is null and trim(upper(ORIGINATING_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			System.out.println("update webservice_marine_quote_raw w" +
					" set validation_detail=validation_detail||' ,OriginatingCountryCity Mandatory'" +
					" where  policy='C01' and ORIGINATING_COUNTRY_CITY is null and trim(upper(ORIGINATING_WAREHOUSE_COVERAGE)) ='YES' " +
					" and reference_number='"+refNo+"' " +
					" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
	
			//Warehouse Destination validation -- if yes then city mandatory
			runner.updation("update webservice_marine_quote_raw w" +
						" set validation_detail=validation_detail||' ,DestinationCountryCity Mandatory'" +
						" where  policy='C01' and DESTINATION_COUNTRY_CITY is null and trim(upper(DESTINATION_WAREHOUSE_COVERAGE)) ='YES' " +
						" and reference_number='"+refNo+"' " +
						" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			System.out.println("Query check::>>update webservice_marine_quote_raw w" +
						" set validation_detail=validation_detail||' ,DestinationCountryCity Mandatory'" +
						" where  policy='C01' and DESTINATION_COUNTRY_CITY is null and trim(upper(DESTINATION_WAREHOUSE_COVERAGE)) ='YES' " +
						" and reference_number='"+refNo+"' " +
						" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			System.out.println("countryId::>"+ countryId);
			System.out.println("originCountryId::>"+ originCountryId);
			System.out.println("destCountryId::>"+ destCountryId);
			
			/*String selMode = "select mode_transport_id from mode_of_transport where status in('Y','R') and branch_code='"+branchCode+"' and " +
					" rsacode=(select mode_of_transport from webservice_marine_quote_raw where " +
					" reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"'))";
			String mode = runner.singleSelection(selMode);
			if(mode.equalsIgnoreCase("1"))
			{
				String selSeaCoverages = "select sea_coverages from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"')";
				String seaCoverages = runner.singleSelection(selSeaCoverages);
				String countValid = runner.singleSelection(" select count(*) from constant_detail where rsacode='"+seaCoverages+"' and category_id='2'");
				System.out.println("Valid sea::"+countValid);
				if(countValid.equalsIgnoreCase("0")){
					runner.updation("update webservice_marine_quote_raw set validation_detail= validation_detail||',SeaCoverages Not exists' where  policy= 'C01' and reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
				}
				
			}*/
			
			//Master Reference Validation
			String selectReferences = "select table_name ,field_name,tag_value,reference_table,reference_column,nvl(add_refer_column,'') ,type" +
					" from webservice_request_reader where  validation_required='Y' " +
					 " and status='Y' and reference='Y'  and request_id <>103";
			String referFields[][] = runner.multipleSelection(selectReferences);
			for(int i=0;i<referFields.length;i++){
					String referenceCode="";
					String reference = "";
					String amendment = "";
					if(referFields[i][5]!=null)
					{
						if(referFields[i][5].equalsIgnoreCase("BRANCH_CODE"))
						{
							//referenceCode = branchCode;
							referenceCode = belongingBranch;
						}
						if((referFields[i][5].equalsIgnoreCase("COUNTRY_ID") || referFields[i][5].equalsIgnoreCase("BELONGING_COUNTRY_ID") ) &&(!(referFields[i][1].equalsIgnoreCase("ORIGINATING_COUNTRY_CITY") ||referFields[i][1].equalsIgnoreCase("DESTINATION_COUNTRY_CITY")) ))
						{
							referenceCode = countryId;
						}
						if((referFields[i][5].equalsIgnoreCase("COUNTRY_ID") || referFields[i][5].equalsIgnoreCase("BELONGING_COUNTRY_ID")) &&(referFields[i][1].equalsIgnoreCase("ORIGINATING_COUNTRY_CITY") ))
						{
							referenceCode = originCountryId;
						}
						if((referFields[i][5].equalsIgnoreCase("COUNTRY_ID") || referFields[i][5].equalsIgnoreCase("BELONGING_COUNTRY_ID") )&&(referFields[i][1].equalsIgnoreCase("DESTINATION_COUNTRY_CITY") || referFields[i][1].equalsIgnoreCase("SETTLING_AGENT")))
						{
						
							referenceCode = destCountryId;
						}
						
						reference =  " and "+referFields[i][3]+"."+referFields[i][5]+"='"+referenceCode+"'";
					}
					if(referFields[i][6]!=null && referFields[i][6].equalsIgnoreCase("AMEND")){
						amendment = " and "+referFields[i][3]+".amend_id = (select max(amend_id) from "+referFields[i][3]+" where "+ (((referFields[i][5] == null ?"":referFields[i][5]).equalsIgnoreCase("BRANCH_CODE"))?(referFields[i][3]+".branch_code = '"+belongingBranch+"' and "):"")+referFields[i][3]+".RSACODE="+referFields[i][0]+"."+referFields[i][1]+((referFields[i][1].equalsIgnoreCase("CURRENCY") || referFields[i][1].equalsIgnoreCase("COMMODITY_CODE")|| referFields[i][1].equalsIgnoreCase("COUNTRY")|| referFields[i][1].equalsIgnoreCase("COVERAGES")|| referFields[i][3].equalsIgnoreCase("CONVEYAN_MASTER")|| referFields[i][1].equalsIgnoreCase("DESTINATION_COUNTRY")|| referFields[i][1].equalsIgnoreCase("ORIGINATING_COUNTRY"))?" AND TRUNC("+referFields[i][3]+".EFFECTIVE_DATE)<=TO_DATE('"+policyStartDate+"','DD/MM/YYYY')":"")+" )";
					}
					String coreAppId = ".RSACODE= ";
					String modeAppend = "";
					String validAppend = "";
					if(referFields[i][3].trim().equalsIgnoreCase("CONVEYAN_MASTER")){
						coreAppId = ".CORE_APP_ID= ";
					}
					if(referFields[i][3].trim().equalsIgnoreCase("CONVEYAN_MASTER")){
						modeAppend =" and CONVEYAN_MASTER.MODE_TRANSPORT_ID = " +
						"(select mode_transport_id from  MODE_OF_TRANSPORT where branch_code='" +belongingBranch+"' and "+
						referFields[i][0]+".mode_of_transport = MODE_OF_TRANSPORT.rsacode " +
								" and "+referFields[i][0]+".amend_id=(select max(amend_id) from "+referFields[i][0]+" m where m.reference_number='"+refNo+"')" +
										" and "+referFields[i][0]+".reference_number='"+refNo+"' ) ";
			
					}
					if(referFields[i][3].trim().equalsIgnoreCase("COVER_MASTER")){
						
						modeAppend =" and COVER_MASTER.MODE_TRANSPORT_ID = " +
						"(select mode_transport_id from  MODE_OF_TRANSPORT where branch_code='" +belongingBranch+"' and "+
						referFields[i][0]+".mode_of_transport = MODE_OF_TRANSPORT.rsacode " +
						" and "+referFields[i][0]+".amend_id=(select max(amend_id) from "+referFields[i][0]+" m where m.reference_number='"+refNo+"')" +
						" and "+referFields[i][0]+".reference_number='"+refNo+"' ) ";
					}
					if(referFields[i][1].equalsIgnoreCase("CURRENCY") || referFields[i][1].equalsIgnoreCase("COMMODITY_CODE")|| referFields[i][1].equalsIgnoreCase("COUNTRY")|| referFields[i][1].equalsIgnoreCase("COVERAGES")|| referFields[i][3].equalsIgnoreCase("CONVEYAN_MASTER")|| referFields[i][1].equalsIgnoreCase("DESTINATION_COUNTRY")|| referFields[i][1].equalsIgnoreCase("ORIGINATING_COUNTRY")){

						validAppend =" AND TRUNC("+referFields[i][3]+".EFFECTIVE_DATE)<=TO_DATE('"+policyStartDate+"','DD/MM/YYYY')";
					}
					String refValidUpdate = " update "+referFields[i][0]+" w set " +
							"validation_detail=validation_detail||',"+referFields[i][2]+" not " +
							"exists' where  "+referFields[i][1]+" is not  null " +((referFields[i][1].equalsIgnoreCase("SETTLING_AGENT"))?" and SETTLING_AGENT<>'0'":"")+
							" and  "+referFields[i][1]+" NOT IN " +
							"(select distinct "+referFields[i][0]+"."+referFields[i][1]+" from  "+referFields[i][0]+"," +
							referFields[i][3]+" where  "+referFields[i][3]+".status in('Y','R') "+modeAppend+" and "+referFields[i][3]+coreAppId +
							""+referFields[i][0]+"."+referFields[i][1]+validAppend+reference+amendment+" ) "+((referFields[i][3].equalsIgnoreCase("CONVEYAN_MASTER"))?" AND DECODE(policy,'C02',SEA_COVERAGES,1) !=0 ":"")+" and reference_number='"+refNo+"'" +
							"and w.amend_id=(select max(amend_id) from "+referFields[i][0]+" where reference_number='"+refNo+"') ";
					System.out.println("refValidUpdate Qry::>"+refValidUpdate);
					
					boolean cityOthers=(referFields[i][1].equalsIgnoreCase("ORIGINATING_COUNTRY_CITY")&& originCountryCityId.equalsIgnoreCase("999")) || (referFields[i][1].equalsIgnoreCase("DESTINATION_COUNTRY_CITY")&& destCountryCityId.equalsIgnoreCase("999"));
					if(!(cityOthers))
					{
				      runner.updation(refValidUpdate);
					}
					if(referFields[i][1].equalsIgnoreCase("SALETERM_VALUE")){
					String updatePercent = "update "+referFields[i][0]+" w set validation_detail=validation_detail||',Saleterm Percent not exists' where  SALETERM_VALUE is not  null and " +
							" SALETERM_VALUE NOT IN (select distinct "+referFields[i][0]+".SALETERM_VALUE " +
							"from  "+referFields[i][0]+",SALE_TERM_MASTER where  SALE_TERM_MASTER.status " +
							"in('Y','R')  and SALE_TERM_MASTER.SALE_TERM_VALUE= "+referFields[i][0]+".SALETERM_PERCENT " +
							" and SALE_TERM_MASTER.RSACODE= WEBSERVICE_MARINE_QUOTE_RAW.SALETERM_VALUE " +
							"and SALE_TERM_MASTER.BRANCH_CODE='"+belongingBranch+"' and reference_number='"+refNo+"' ) and reference_number='"+refNo+"' " +
							" and w.amend_id=(select max(amend_id) from "+referFields[i][0]+" where reference_number='"+refNo+"') ";
					System.out.println("Update Percent " +updatePercent);
					runner.updation(updatePercent);
							} 
			   }
			
			 
			 String givenTolerance = " select TOLERANCE from WEBSERVICE_MARINE_QUOTE_RAW W where   W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
				"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
			   String toleranceRSA = runner.singleSelection(givenTolerance)==null?"":runner.singleSelection(givenTolerance).toString();
			   
			   if(toleranceRSA.equalsIgnoreCase("")  ){
				 String upTolerance = "update WEBSERVICE_MARINE_QUOTE_RAW set TOLERANCE='4' where   REFERENCE_NUMBER='"+refNo+"'  AND AMEND_ID = " +
						"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
				  runner.updation(upTolerance);
			   }
			   String updateSettlingOthers = " update webservice_marine_quote_raw set validation_detail=validation_detail||',Settling Agent Others Mandatory' where settling_agent_name is null and settling_agent='0' and reference_number='"+refNo+"' " +
		 		"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			   runner.updation(updateSettlingOthers);
			   
			   /*  String updateSettling = " update webservice_marine_quote_raw set settling_agent='' where settling_agent='0' reference_number='"+refNo+"' " +
		 		"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			   runner.updation(updateSettling);
			    */
			   String updateProduct = " update webservice_marine_quote_raw set product_id=(select  (case policy when 'C01' then '3' when 'C02' then '11' else null end) product from webservice_marine_quote_raw where  amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') and reference_number='"+refNo+"') where reference_number='"+refNo+"' " +
		 		"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			   runner.updation(updateProduct);
			 				
			 String updateBranch = "update webservice_marine_quote_raw w set branch_code=(select branch_code from broker_company_master where rsa_broker_code=w.broker_code ) where reference_number='"+refNo+"' " +
			 		"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
			 runner.updation(updateBranch);
		        
			 String selBrokerCode = "select agency_code from broker_company_master where rsa_broker_code=(select broker_code from webservice_marine_quote_raw where reference_number='"+refNo+"' " +
			 		"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') )";
			 
			 String brokerCode = runner.singleSelection(selBrokerCode); 
			
			 String validProduc = runner.singleSelection("select count(*) from webservice_marine_quote_raw w, LOGIN_USER_DETAILS l " +
			 		" where '"+brokerCode+"'=l.oa_code and l.product_id = w.product_id and reference_number='"+refNo+"' and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			 
			 System.out.println("Valid Product::>"+"select count(*) from webservice_marine_quote_raw w, LOGIN_USER_DETAILS l " +
			 		" where '"+brokerCode+"'=l.oa_code and l.product_id = w.product_id and reference_number='"+refNo+"' and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			 
			 if(validProduc.equalsIgnoreCase("0")){	
				 runner.updation("update webservice_marine_quote_raw w set validation_detail=validation_detail||', Product Invalid for Broker' where reference_number='"+refNo+"' " +
			 		"and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
			 }
			 
			 String selectInValid = "select nvl(validation_detail,'') from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') "; 
			 inValid = runner.singleSelection(selectInValid)==null?"":runner.singleSelection(selectInValid);
			// System.out.println("Invalid detail::"+ inValid);
			 
			String selectInValid2 = "select nvl(validation_detail,'') from webservice_marine_commodity_rw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_commodity_rw where reference_number='"+refNo+"') ";
			System.out.println("selectInvalid 2 ::"+selectInValid2); 
			String inValid2="";
			String vals[][] = runner.multipleSelection(selectInValid2);
			for(int j=0;j<vals.length;j++){
				inValid2 = inValid2 +( vals[j][0]==null?"":vals[j][0]);
			}
			 inValid = inValid + inValid2;
			 
			 System.out.println("Invalid detail::"+ inValid);
			 if(inValid.equalsIgnoreCase("") ){	 
				 System.out.println("No Error Occurs in validation 1");
				 
			 }
			 else{
				 System.out.println("Validation error occurs in validation 1");
			 }
			 
			 //Open cover Validation
		   		System.out.println("Open cover validation:::>");
				String productSelect = "select product_id from webservice_marine_quote_raw where reference_number='"+refNo+"'";
				String product = runner.singleSelection(productSelect);
				if(product.equalsIgnoreCase("11") && inValid.equalsIgnoreCase("")){
				 opencoverValidation(refNo);
				 }
			 
				String selectInValidOpencover = "select nvl(validation_detail,'') from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
				System.out.println("selectInValidOpencover 2 ::"+selectInValidOpencover); 
				String inValid3="";
				String vals3[][] = runner.multipleSelection(selectInValidOpencover);
				for(int j=0;j<vals3.length;j++){
					inValid3 = inValid3 +( vals3[j][0]==null?"":vals3[j][0]);
				}
				String selectInValidOpencover2 = "select nvl(validation_detail,'') from webservice_marine_commodity_rw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_commodity_rw where reference_number='"+refNo+"') ";
				System.out.println("selectInValidOpencover 2 ::"+selectInValidOpencover2); 
				String inValid4="";
				String vals4[][] = runner.multipleSelection(selectInValidOpencover2);
				for(int j=0;j<vals4.length;j++){
					inValid4 = inValid4 +( vals4[j][0]==null?"":vals4[j][0]);
				}
				inValid = inValid3+inValid4;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return inValid;
	}
	public void opencoverValidation(String refNo)
	{
		System.out.println("<::Opencover Validation::>");
		String validOpenCover = "update webservice_marine_quote_raw w" +
			" set validation_detail=validation_detail||' ,OpenCover not valid'" +
			" where  ( MOC_NUMBER NOT IN  (select distinct MOC_NUMBER from " +
			" webservice_marine_quote_raw,open_cover_master where open_cover_master.status in('Y','R') and " +
			" open_cover_master .MISSIPPI_OPENCOVER_NO= webservice_marine_quote_raw.MOC_NUMBER) or MOC_NUMBER IS NULL )" +
			" and reference_number='"+refNo+"' " +
			" and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
		System.out.println("Open cover validation:"+validOpenCover);
		runner.updation(validOpenCover);
		String selectInValidOpencover = "select nvl(validation_detail,'') from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
		System.out.println("selectInValidOpencover 2 ::"+selectInValidOpencover); 
		String inValid=(String)this.mytemplate.queryForObject(selectInValidOpencover, String.class);
		 if(inValid ==null){
		 //Master Reference Validation
		 
		String selectBranch = "select b.branch_code from broker_company_master b , webservice_marine_quote_raw w where b.rsa_broker_code = w.broker_code and w.reference_number='"+refNo+"'" +
				"and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ";
		String branchCode = runner.singleSelection(selectBranch);
		System.out.println("Branch Code::>"+ branchCode);
		String selectCountry = "SELECT ORIGINATION_COUNTRY_ID from BRANCH_MASTER where BRANCH_CODE='"+branchCode+"' and status='Y'";
		String countryId = runner.singleSelection(selectCountry);
		System.out.println("countryId::>"+ countryId);
		String lcNumber="";
		String selMOC = runner.singleSelection("select MOC_NUMBER from webservice_marine_quote_raw where  reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"')");
		String selProposal = runner.singleSelection("Select PROPOSAL_NO from OPEN_COVER_MASTER where MISSIPPI_OPENCOVER_NO='"+selMOC+"' and amend_id=(select max(amend_id) from OPEN_COVER_MASTER where MISSIPPI_OPENCOVER_NO='"+selMOC+"')");
		String updateProposalNo = "UPDATE  webservice_marine_quote_raw set PROPOSAL_NO='"+selProposal+"' where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"')"; 
		runner.updation(updateProposalNo);
		
		String selectOpencover = "select PROPOSAL_NO from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"')";
		
		String proposalno = runner.singleSelection(selectOpencover);
		
		String openCoverLC = runner.singleSelection("select OPEN_COVER_NO from OPEN_COVER_POSITION_MASTER where proposal_no='"+proposalno+"' and amend_id=(select max(amend_id) from OPEN_COVER_POSITION_MASTER where proposal_no='"+proposalno+"')");
		
		runner.updation("update webservice_marine_quote_raw set opencover_policy_number = '"+openCoverLC+"' where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"')");
		
		String customer = runner.singleSelection("select CLIENT_CUSTOMER_ID from webservice_marine_quote_raw where reference_number='"+refNo+"' and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"')");
		String customerStatus = runner.singleSelection("SELECT DECODE (COUNT ( * ), 1, 'Y', 'N')  FROM   (SELECT   DISTINCT (REGEXP_SUBSTR (CUSTOMER,'[^,]+',1,LEVEL)) CUSTOMER FROM  (SELECT   CUSTOMER_ID || ',' || ADDITIONAL_INSURED  CUSTOMER  FROM   OPEN_COVER_MASTER WHERE   PROPOSAL_NO = '"+proposalno+"') B  CONNECT BY   LEVEL <= LENGTH (CUSTOMER) - LENGTH (REPLACE (CUSTOMER, ',', '')) + 1)  WHERE   CUSTOMER = '"+customer+"'");
		if(customerStatus.equalsIgnoreCase("N")){	
		runner.updation("update webservice_marine_quote_raw w set validation_detail=validation_detail||', Customer Invalid for the Opencover' where reference_number='"+refNo+"' " +
			 "and w.amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+refNo+"') ");
		}
			
		String selectReferences = "select table_name ,field_name,tag_value,reference_table,reference_column,nvl(add_refer_column,''),type " +
				"from webservice_request_reader where  validation_required='Y' " +
				"and status='Y' and reference='Y' and request_id=103";
		String referFields[][] = runner.multipleSelection(selectReferences);
		for(int i=0;i<referFields.length;i++){
			proposalno = runner.singleSelection(selectOpencover);
			
			String amendment="";
			String selectValue = "select reference_column ,reference_table,table_name,type from webservice_request_reader where field_name='"+referFields[i][1]+"' and (request_id=101 or request_id=102)";
			String values[][] = runner.multipleSelection(selectValue);

			if(values[0][3]!=null)
			{
				amendment = " and "+values[0][1]+".amend_id = (select max(amend_id) from "+values[0][1]+" where "+values[0][1]+".RSACODE=w."+referFields[i][1]+")";
			}
			String selectFieldValue = "select "+values[0][0]+" from " +values[0][1]+","+values[0][2]+" w where "+values[0][1]+".RSACODE=w."+referFields[i][1]+amendment+" and w.reference_number='"+refNo+"' and w.amend_id= (select max(amend_id) from "+values[0][2]+" where reference_number='"+refNo+"')";
			if(referFields[i][1].equalsIgnoreCase("SALETERM_VALUE")){
				selectFieldValue = "select "+values[0][0]+" from " +values[0][1]+","+values[0][2]+" w where SALE_TERM_MASTER.sale_term_value in(select SALETERM_PERCENT from "+values[0][2]+" wr  where wr.amend_id = (select max(amend_id) from "+values[0][2]+" where reference_number='"+refNo+"') and wr.reference_number='"+refNo+"') and "+values[0][1]+".RSACODE=w."+referFields[i][1]+amendment+" and w.reference_number='"+refNo+"' and w.amend_id= (select max(amend_id) from "+values[0][2]+" where reference_number='"+refNo+"') and SALE_TERM_MASTER.branch_code='"+branchCode+"'";
			}
			//select SALE_TERM_ID from SALE_TERM_MASTER,WEBSERVICE_MARINE_QUOTE_RAW w where SALE_TERM_MASTER.RSACODE=w.SALETERM_VALUE and w.reference_number='11h145129' and w.amend_id= (select max(amend_id) from WEBSERVICE_MARINE_QUOTE_RAW where reference_number='11h145129')
			System.out.println("Selecting fields values::>"+selectFieldValue);
			String fieldvalue =""; 
			if(referFields[i][1].equalsIgnoreCase("LC_NUMBER")){
				selectFieldValue = "select LC_NUMBER from "+referFields[i][0]+" w where  w.reference_number='"+refNo+"' and w.amend_id= (select max(amend_id) from "+referFields[i][0]+" where reference_number='"+refNo+"')";
				System.out.println("Selecting fields values::>"+selectFieldValue);
				fieldvalue = runner.singleSelection(selectFieldValue);
				lcNumber= fieldvalue;
				proposalno=openCoverLC;
				System.out.println("Open Cover LC::"+proposalno);
			}
			else
			{
				fieldvalue = runner.singleSelection(selectFieldValue);
			}
			
			System.out.println("Field vAlues:::"+fieldvalue);
			if(referFields[i][5]!=null)
			{
				 if(referFields[i][5].equalsIgnoreCase("PROPOSAL_NO") && referFields[i][1].equalsIgnoreCase("OPEN_COVER_DETAIL"))
				{
					
					String refValidUpdate = "update "+referFields[i][0]+" w " +
					                 " set w.validation_detail=validation_detail||"+
					                 " ',"+referFields[i][2]+" not exists for open cover'"+
								     " where  w."+referFields[i][1]+" NOT IN ("+
								      " select  "+referFields[i][3]+"."+referFields[i][4]+""+
								     " from  "+referFields[i][0]+"," +referFields[i][3]+""+
								     " where "+referFields[i][3]+".status in ('Y','R') and "+referFields[i][3]+".amend_id=("+
								     " select max(amend_id)"+
								      " from "+referFields[i][3]+""+
								      " where   "+referFields[i][3]+"."+referFields[i][5]+"='"+proposalno+"' )"+
								     " and "+referFields[i][0]+".PROPOSAL_NO =  "+referFields[i][3]+"."+referFields[i][5]+""+
								     " AND "+referFields[i][0]+".PROPOSAL_NO='"+proposalno+"'"+
								      " and "+referFields[i][0]+".reference_number='"+refNo+"'"+
								       "     and "+referFields[i][0]+".amend_id=("+
								      " select max(amend_id)"+
								      " from "+referFields[i][0]+""+
								      " where reference_number='"+refNo+"')) and  w.reference_number='"+refNo+"' and w.amend_id = ( select max(amend_id)"+
								      " from "+referFields[i][0]+""+
								      " where reference_number='"+refNo+"')";
			
					System.out.println("Open cover refValidUpdate Qry::>"+refValidUpdate);
					runner.updation(refValidUpdate);
					
					
				}
				 else {
					 
					String refValidUpdate=" UPDATE  "+referFields[i][0]+" W SET W.VALIDATION_DETAIL=VALIDATION_DETAIL||"+
				      " ',"+referFields[i][2]+" not exists in Open cover'"+
				      " WHERE W."+referFields[i][1]+" NOT IN " +
				      		" (select "+referFields[i][0]+"."+referFields[i][1]+" from "+referFields[i][0]+","+referFields[i][3]+" m where (m."+referFields[i][4]+" LIKE '%,"+fieldvalue+",%'"
				     +" OR m."+referFields[i][4]+" LIKE '"+fieldvalue+",%' OR m."+referFields[i][4]+" LIKE '%,"+fieldvalue+"' OR"+
				     " m."+referFields[i][4]+" LIKE '"+fieldvalue+"') AND  m."+referFields[i][5]+"='"+proposalno+"'"+
				      " AND  m.status in('Y','R') and m.AMEND_ID=(SELECT MAX(AMEND_ID) FROM "+referFields[i][3]+""+
				    " WHERE "+referFields[i][5]+"='"+proposalno+"'))  "+
				      " AND  W.REFERENCE_NUMBER='"+refNo+"' AND W.AMEND_ID = ( SELECT MAX(AMEND_ID)"+
				     " FROM "+referFields[i][0]+
				      " WHERE REFERENCE_NUMBER='"+refNo+"')";
					 System.out.println("Open cover refValidUpdate Qry::>"+refValidUpdate);
					 if(!(referFields[i][1].equalsIgnoreCase("LC_NUMBER") && fieldvalue.equalsIgnoreCase("0"))){
						runner.updation(refValidUpdate);
					 }
					 
				 }
				 if(referFields[i][1].equalsIgnoreCase("SALETERM_VALUE")){
						//fieldvalue = "select "
					 
					/* String modeQryUpdate = "update "+referFields[i][0]+" w set " +
						" W.VALIDATION_DETAIL=VALIDATION_DETAIL|| ',Conveyance Not Exists in Open cover' " +
						" where w.SEA_COVERAGES IS NOT NULL AND w.SEA_COVERAGES not in " +
						"(select core_app_id from conveyan_master where " +
						" core_app_id=w.SEA_COVERAGES and " +
						" conveyan_master.branch_code='"+branchCode+"'" +
						" and MODE_TRANSPORT_ID in (" +
						" select MODE_TRANSPORT_ID from OPEN_COVER_DETAIL where proposal_no='"+proposalno+"' and  amend_id =(select max(amend_id ) from OPEN_COVER_DETAIL where proposal_no='"+proposalno+"')" +
						" )) and REFERENCE_NUMBER='"+refNo+"' AND W.AMEND_ID = " +
						" ( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
						

						System.out.println("UPDATE CONVEYANCE::"+modeQryUpdate);
						runner.updation(modeQryUpdate);*/
					 
					 String conveyanQry = "select COVER_TYPE_ID from OPEN_COVER_SUB_DETAIL where proposal_no=? and COVER_TYPE_ID IS NOT NULL AND  amend_id =(select max(amend_id ) from OPEN_COVER_SUB_DETAIL where proposal_no=?)" ;
						SqlRowSet conveyanValuesRS= this.mytemplate.queryForRowSet(conveyanQry, new Object[]{proposalno,proposalno});
					    StringBuffer conveyanValues = new StringBuffer(); 
						while (conveyanValuesRS.next()){
							if(conveyanValuesRS.isLast()){
								conveyanValues.append(conveyanValuesRS.getString(1)); 
							}
							else{
								conveyanValues.append(conveyanValuesRS.getString(1)+",");
							}
					    }
						
						String selectCoverNames ="select distinct RSACODE from OPEN_COVER_SUB_DETAIL,COVER_MASTER where COVER_MASTER.amend_id=(select max(amend_id) from COVER_MASTER where branch_code=?) and OPEN_COVER_SUB_DETAIL.cover_type_id is not null  and OPEN_COVER_SUB_DETAIL.proposal_no=? and OPEN_COVER_SUB_DETAIL.COVER_ID= COVER_MASTER.COVER_ID and  OPEN_COVER_SUB_DETAIL.COVER_ID IS NOT NULL AND  OPEN_COVER_SUB_DETAIL.amend_id =(select max(amend_id ) from OPEN_COVER_SUB_DETAIL where proposal_no=?)";
						SqlRowSet selectCoverRS= this.mytemplate.queryForRowSet(selectCoverNames, new Object[]{branchCode, proposalno,proposalno});
					    StringBuffer coverNames = new StringBuffer(); 
						while (selectCoverRS.next()){
							if(selectCoverRS.isLast()){
								coverNames.append("'"+selectCoverRS.getString(1)+"'"); 
							}
							else{
								coverNames.append("'"+selectCoverRS.getString(1)+"',");
							}
					    }
				
						String updateConveyanDefault = "update "+referFields[i][0]+" w set W.SEA_COVERAGES ='0' where  W.SEA_COVERAGES IS NULL  AND  W.REFERENCE_NUMBER=? AND W.AMEND_ID = ( SELECT MAX(AMEND_ID)"+
								" FROM "+referFields[i][0]+
								" WHERE REFERENCE_NUMBER='"+refNo+"')";
						System.out.println("updateConveyanDefault"+updateConveyanDefault);
						this.mytemplate.update(updateConveyanDefault, new Object[]{refNo});
				 
						String updateInvalidConveyan = "update  "+referFields[i][0]+" w set  W.VALIDATION_DETAIL" +
								"=W.VALIDATION_DETAIL||', Conveyance Not Exists in Open Cover' where  (W.COVERAGES in ("+coverNames+") or W.SEA_COVERAGES<>'0' )AND SEA_COVERAGES NOT IN  "+
								" (select core_app_id from CONVEYAN_MASTER where core_app_id=" +
								"  w.SEA_COVERAGES AND " +
								" BRANCH_CODE=? and W.COVERAGES in ("+coverNames+") AND " +
								" CONVEYAN_MASTER.CONV_MEAN in("+conveyanValues+") and  " +
								"  MODE_TRANSPORT_ID=(select MODE_TRANSPORT_ID from MODE_OF_TRANSPORT" +
								" where RSACODE= W.MODE_OF_TRANSPORT AND BRANCH_CODE=? AND STATUS='Y'))  AND  W.REFERENCE_NUMBER=? AND W.AMEND_ID = ( SELECT MAX(AMEND_ID)"+
								" FROM "+referFields[i][0]+
								" WHERE REFERENCE_NUMBER='"+refNo+"')";
						
			            System.out.println("Update invalid conveyan qry::"+updateInvalidConveyan);
						//this.mytemplate.update(updateInvalidConveyan, new Object[]{branchCode,branchCode,refNo});
						 
					 String updateInvalidCover = "update  "+referFields[i][0]+" w set W.VALIDATION_DETAIL" +
						"=W.VALIDATION_DETAIL||',Coverages Not Exists in Open cover' where COVERAGES IS NOT NULL AND COVERAGES NOT IN  "+
						" (select RSACODE from COVER_MASTER where RSACODE=" +
						"  w.COVERAGES AND " +
						" BRANCH_CODE='"+branchCode+"' AND " +
						" COVER_MASTER.COVER_ID in("
						+" select COVER_ID from OPEN_COVER_SUB_DETAIL where proposal_no='"+proposalno+"' and  amend_id =(select max(amend_id ) from OPEN_COVER_SUB_DETAIL where proposal_no='"+proposalno+"')" +
						 ") and " +
						"  MODE_TRANSPORT_ID=(select MODE_TRANSPORT_ID from MODE_OF_TRANSPORT" +
						"	where RSACODE= w.MODE_OF_TRANSPORT AND BRANCH_CODE='"+branchCode+"' AND STATUS='Y')" +
						" ) and w.REFERENCE_NUMBER='"+refNo+"' AND W.AMEND_ID = " +
						" ( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
		
		            System.out.println("Update invalid cover qry::"+updateInvalidCover);
		            runner.updation(updateInvalidCover);
					   String selectTolerance = "select distinct m.TOLERANCE_ID " +
						"from WEBSERVICE_MARINE_QUOTE_RAW W,OPEN_COVER_SALE_TERM_MASTER m" +
						" where "+referFields[i][1]+"= W.SALETERM_VALUE and (m.SALE_TERM_ID LIKE '%,"+fieldvalue+",%' OR m.SALE_TERM_ID LIKE '"+fieldvalue+",%' " +
						"OR m.SALE_TERM_ID LIKE '%,"+fieldvalue+"' OR m.SALE_TERM_ID LIKE '"+fieldvalue+"') AND " +
						" m.PROPOSAL_NO='"+proposalno+"' AND  m.status in('Y','R') and" +
						" m.AMEND_ID=(SELECT MAX(AMEND_ID) FROM " +
						" OPEN_COVER_SALE_TERM_MASTER WHERE PROPOSAL_NO='"+proposalno+"' )  and branch_code='"+branchCode+"' "+ 
						"  and   W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
						"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
					   String tolernceDBValue = runner.singleSelection(selectTolerance)==null?"":runner.singleSelection(selectTolerance);
					   String givenTolerance = " select TOLERANCE from WEBSERVICE_MARINE_QUOTE_RAW W where   W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
						"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
					   String toleranceRSA = runner.singleSelection(givenTolerance);
					   String selSAle =  " select "+referFields[i][1]+" from WEBSERVICE_MARINE_QUOTE_RAW W where   W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
						"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
					   String saleterm = runner.singleSelection(selSAle);
					   String selSAlePer =  " select SALETERM_PERCENT from WEBSERVICE_MARINE_QUOTE_RAW W where   W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
						"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
					   String percent = runner.singleSelection(selSAlePer);
					   String saletermID = runner.singleSelection("select sale_term_id from sale_term_master where rsacode='"+saleterm+"' and sale_term_value='"+percent+"' and branch_code='"+branchCode+"'");
					   boolean none1 = ((","+tolernceDBValue+",").contains(","+saletermID+"~"));
					   boolean none2 = ((","+tolernceDBValue+",").contains(","+saletermID+"~4"));
					   System.out.println("none1:::"+none1+saletermID+"~");
					   System.out.println("none2:::"+none2);
					   
					   // if( !(toleranceRSA.equalsIgnoreCase("4") && (tolernceDBValue.equalsIgnoreCase("") )||( toleranceRSA.equalsIgnoreCase("4") && (!(","+tolernceDBValue+",").contains(","+referFields[i][1]+"~"))) )){
					   if( (!toleranceRSA.equalsIgnoreCase("4") && !tolernceDBValue.equalsIgnoreCase(""))
							   ||( toleranceRSA.equalsIgnoreCase("4")   && none1 && !none2)
							   ){
						String updateTolerance = "UPDATE  "+referFields[i][0]+" W " +
						" SET W.VALIDATION_DETAIL=VALIDATION_DETAIL|| ',Tolerance not exists in Open cover' WHERE  " +
						" nvl((select distinct ','||m.TOLERANCE_ID || ',' " +
						" from WEBSERVICE_MARINE_QUOTE_RAW,OPEN_COVER_SALE_TERM_MASTER m" +
						" where "+referFields[i][1]+"= WEBSERVICE_MARINE_QUOTE_RAW.SALETERM_VALUE and (m.SALE_TERM_ID LIKE '%,"+fieldvalue+",%' OR m.SALE_TERM_ID LIKE '"+fieldvalue+",%' " +
						" OR m.SALE_TERM_ID LIKE '%,"+fieldvalue+"' OR m.SALE_TERM_ID LIKE '"+fieldvalue+"') AND " +
						" m.PROPOSAL_NO='"+proposalno+"' AND  m.status in('Y','R') and" +
						" m.AMEND_ID=(SELECT MAX(AMEND_ID) FROM " +
						" OPEN_COVER_SALE_TERM_MASTER WHERE PROPOSAL_NO='"+proposalno+"' )  and branch_code='"+branchCode+"') ,' ') not like " +
						" '%,"+fieldvalue+"~' || (select tolerance_id from tolerance_master where rsacode=W.TOLERANCE and tolerance_master.branch_code='"+branchCode+"')|| ',%' "  +
						"  AND  W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
						" ( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
						System.out.println("UPDATE TOLERANCE::"+updateTolerance);
						runner.updation(updateTolerance);
					   }
						
					   String updateShipmentExposure = " update WEBSERVICE_MARINE_QUOTE_RAW W set validation_detail=validation_detail||',Shipment Exposure Mandatory' where  W.PARTIAL_SHIPMENT='Y' and SHIPMENT_EXPOSURE IS NULL and W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
						"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
					   runner.updation(updateShipmentExposure);
					 
						 String updateExposureCurrency = " update WEBSERVICE_MARINE_QUOTE_RAW W set validation_detail=validation_detail||',Exposure currency Mandatory' where  W.PARTIAL_SHIPMENT='Y' and EXPOSURE_CURRENCY IS NULL and W.REFERENCE_NUMBER='"+refNo+"'  AND W.AMEND_ID = " +
							"( SELECT MAX(AMEND_ID) FROM WEBSERVICE_MARINE_QUOTE_RAW WHERE REFERENCE_NUMBER='"+refNo+"')";
						 runner.updation(updateExposureCurrency);
						
						 
					}
				 
				//reference =  " and "+referFields[i][3]+"."+referFields[i][5]+"='"+referenceCode+"'";
			}
		   }
		   /*String updateBank="update webservice_marine_quote_raw w set issuing_bank=(select bank_id from OPEN_COVER_LC_MASTER where lc_id='"+lcNumber+"' and open_cover_no='"+openCoverLC+"' and amend_id=(select max(amend_id ) from OPEN_COVER_LC_MASTER where open_cover_no='"+openCoverLC+"' ))  where W.REFERENCE_NUMBER='"+refNo+"' AND W.AMEND_ID = ( SELECT MAX(AMEND_ID)"+
				     " FROM webservice_marine_quote_raw WHERE REFERENCE_NUMBER='"+refNo+"')";*/
		if(!lcNumber.trim().equalsIgnoreCase("99999")){
			String updateBank="update webservice_marine_quote_raw w set issuing_bank=(select bank_id from OPEN_COVER_LC_MASTER where LC_NUMBER='"+lcNumber+"' and open_cover_no='"+openCoverLC+"' and amend_id=(select max(amend_id ) from OPEN_COVER_LC_MASTER where open_cover_no='"+openCoverLC+"' ))  where W.REFERENCE_NUMBER='"+refNo+"' AND W.AMEND_ID = ( SELECT MAX(AMEND_ID)"+
					" FROM webservice_marine_quote_raw WHERE REFERENCE_NUMBER='"+refNo+"')";
		   runner.updation(updateBank);
		}
		}
		
	}
	private static String getTagValue(String sTag, Element eElement) {
		String result="";
		try{
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		    Node nValue = (Node) nlList.item(0);
		    if(nValue!=null){
		       result=nValue.getNodeValue()==null?"":nValue.getNodeValue();
		    }
		}catch(Exception e){
			System.out.println("Exception: Some Tages are missing in request");
		}
	    return result;
	  }
	public String generateResponse(String referenceNo, String remarks) {
        System.out.println("Response generation::");
		String response="";
		String commodityResponse="";
		String countQry = "select count(*) from webservice_marine_quote where reference_number='"+referenceNo+"' ";
		String count = runner.singleSelection(countQry);
		String countInvalid = "select count(*) from webservice_marine_quote_raw where reference_number='"+referenceNo+"' " +
				"and amend_id=(select max(amend_id) from webservice_marine_quote_raw where reference_number='"+referenceNo+"' ) and validation_detail is not null";
		String invalid = runner.singleSelection(countInvalid);
		if(!count.equalsIgnoreCase("0") && invalid.equalsIgnoreCase("0"))
		{
			String cmdtyQry = "select COMMODITY_CODE, COMMODITY_DESCRIPTION, SUMINSURED, COMMODITY_RATE, WSRCC_RATE from webservice_marine_commodity where reference_number ='"+referenceNo+"'";
			String commodities[][] = runner.multipleSelection(cmdtyQry);
			for(int i=0;i<commodities.length;i++)
			{
				commodityResponse=commodityResponse+"<commodity><CommodityCode>"+(commodities[i][0]==null?"":commodities[i][0])+"</CommodityCode>" +
								"<CommodityDescription>"+(commodities[i][1]==null?"":commodities[i][1])+"</CommodityDescription>" +
								"<SumInsured>"+(commodities[i][2]==null?"":commodities[i][2])+"</SumInsured>" +
								"<CommodityRate>"+(commodities[i][3]==null?"":commodities[i][3])+"</CommodityRate>" +
								"<WSRCCRate>"+(commodities[i][4]==null?"":commodities[i][4])+"</WSRCCRate></commodity>";
			}
			
			String quoteQry = "select to_char(POLICY_START_DATE,'dd/mm/yyyy'), QUOTE_NO, PREMIUM, STATUS, REMARKS,to_char(QUOTATION_DATE,'dd/mm/yyyy'),MARINE_PREMIUM,WAR_PREMIUM,ISSUANCE_FEE,ADDITIONAL_PREMIUM,nvl(PREMIUM,0)+nvl(ADDITIONAL_PREMIUM,0) PREMIUM, PROMOTIONAL_CODE  from webservice_marine_quote where reference_number ='"
					          + referenceNo + "'";
			String quoteValues[][] = runner.multipleSelection(quoteQry);
			
			String quoteQry1 = "SELECT (EQUIVALENT_USD + NVL (TOTAL_SALE_TERM_CHARGES, 0)+ NVL (TOTAL_TOLERANCE_CHARGES, 0)) EQUIVALENT,TOTAL_SUM_INSURED FROM MARINE_DATA WHERE APPLICATION_NO=(SELECT APPLICATION_NO FROM WEBSERVICE_MARINE_QUOTE WHERE REFERENCE_NUMBER='"+referenceNo+"')";
			String quoteValues1[][] = runner.multipleSelection(quoteQry1);
			
			response="<QuoteInformation>" +
					"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
					"<PolicyStartDate>"+quoteValues[0][0]+"</PolicyStartDate>" +
					"<QuotationDate>"+(quoteValues[0][5]==null?"":quoteValues[0][5])+"</QuotationDate>" +
					"<QuoteNumber>"+(quoteValues[0][1]==null?"":quoteValues[0][1])+"</QuoteNumber>" +
					"<MarinePremium>"+(quoteValues[0][6]==null?"":quoteValues[0][6])+"</MarinePremium>" +
					"<WarPremium>"+(quoteValues[0][7]==null?"":quoteValues[0][7])+"</WarPremium>" +
					"<AdditionalPremium>"+(quoteValues[0][9]==null?"":quoteValues[0][9])+"</AdditionalPremium>" +
					"<IssuanceFee>"+(quoteValues[0][8]==null?"":quoteValues[0][8])+"</IssuanceFee>" +
					"<TotalPremium>"+(quoteValues[0][10]==null?"":quoteValues[0][10])+"</TotalPremium>" +
					"<TotalSumInsured>"+(quoteValues1.length>0?(quoteValues1[0][0]==null?"":quoteValues1[0][0]):"")+"</TotalSumInsured>" +
					"<RefferalStatus>"+(quoteValues[0][3]==null?"":quoteValues[0][3])+"</RefferalStatus>" +
					"<RefferalRemarks>"+(quoteValues[0][4]==null?"":quoteValues[0][4])+remarks+"</RefferalRemarks>" +
					"<PromotionalCode>"+(quoteValues[0][11]==null?"":quoteValues[0][11])+"</PromotionalCode>" +commodityResponse+
					"</QuoteInformation>";
		}
		else
		{
			String status="E";
			commodityResponse="";
			String cmdtyQry = "select COMMODITY_CODE, COMMODITY_DESCRIPTION, SUMINSURED, COMMODITY_RATE, WSRCC_RATE from webservice_marine_commodity_rw where amend_id=(select max(amend_id) from webservice_marine_commodity_rw where reference_number ='"+referenceNo+"' ) and reference_number ='"+referenceNo+"'";
			String commodities[][] = runner.multipleSelection(cmdtyQry);
			for(int i=0;i<commodities.length;i++)
			{
				commodityResponse=commodityResponse+"<commodity><CommodityCode>"+(commodities[i][0]==null?"":commodities[i][0])+"</CommodityCode>" +
								"<CommodityDescription>"+(commodities[i][1]==null?"":commodities[i][1])+"</CommodityDescription>" +
								"<SumInsured>"+(commodities[i][2]==null?"":commodities[i][2])+"</SumInsured>" +
								"<CommodityRate>"+(commodities[i][3]==null?"":commodities[i][3])+"</CommodityRate>" +
								"<WSRCCRate>"+(commodities[i][4]==null?"":commodities[i][4])+"</WSRCCRate></commodity>";
			}
			
			response="<QuoteInformation>" +
			"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
			"<PolicyStartDate></PolicyStartDate>" +
			"<QuotationDate></QuotationDate>" +
			"<QuoteNumber></QuoteNumber>" +
			"<MarinePremium></MarinePremium>" +
			"<WarPremium></WarPremium>" +
			"<AdditionalPremium></AdditionalPremium>" +
			"<IssuanceFee></IssuanceFee>" +
			"<TotalPremium></TotalPremium>" +
			"<TotalSumInsured></TotalSumInsured>" +
			"<RefferalStatus>"+status+"</RefferalStatus>" +
			"<RefferalRemarks>"+remarks+"</RefferalRemarks>" +
			"<PromotionalCode></PromotionalCode>"
			+commodityResponse+
			"</QuoteInformation>";
			
		}
		String updateQuery = "update webservice_marine_quote set response_time=sysdate , response_remarks='"+remarks+"' where reference_number='"+referenceNo+"'";
		runner.updation(updateQuery);
		String updateQuery2 = "update webservice_marine_quote_raw set response_time=sysdate , response_remarks='"+remarks+"' where amend_id=(select max(amend_id)  from webservice_marine_quote_raw where reference_number='"+referenceNo+"') and reference_number='"+referenceNo+"'";
		runner.updation(updateQuery2);
		return response;
	}
	public String generatePremiumResponse(String requestInfo) {
		String response="";
		String commodityResponse="";
		String referenceNo = "";
		String quoteNo = "";
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			// To read XML
			String count = "";
			NodeList nList1 = doc.getElementsByTagName("PremiumInformation");
			Node nNode1 = nList1.item(0);
			if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode1;
				referenceNo =  getTagValue("ReferenceNumber", eElement);
				quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
			}
		
			String countQry = "select count(*) from webservice_marine_quote where reference_number='"+referenceNo+"' and quote_no='"+quoteNo+"'";
			count = runner.singleSelection(countQry);
			if(!count.equalsIgnoreCase("0"))
			{
				String selRefStatus = "select a.admin_referral_status, b.remarks, b.status, wmq.PROMOTIONAL_CODE from marine_data a, position_master b, webservice_marine_quote wmq where wmq.application_no=b.application_no and a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
				System.out.println(">>"+selRefStatus);
				String referral[][] = runner.multipleSelection(selRefStatus);
				String adminReferral = referral[0][0]==null?"":referral[0][0];
				String referralRemarks = referral[0][1]==null?"":referral[0][1];
				String quoteStatus = referral[0][2]==null?"":referral[0][2];
				String promoCode = referral[0][3]==null?"":referral[0][3];
			   
				String remarks="";
				if("Y".equalsIgnoreCase(adminReferral) || ("Referal".equalsIgnoreCase(referralRemarks) && "Y".equals(quoteStatus))){
					remarks=",Admin Referral";
				} else if("R".equalsIgnoreCase(quoteStatus)){
					//PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					//generate.updateQuoteInfo();
					remarks=",Admin Rejected";
					String update = "UPDATE webservice_marine_quote SET PREMIUM= '0',MARINE_PREMIUM='0',WAR_PREMIUM='0', ISSUANCE_FEE='0',ADDITIONAL_PREMIUM='0' where reference_number ='"+referenceNo+"'";
					runner.updation(update);
					
				} else if (referralRemarks.equalsIgnoreCase("Admin") && !"P".equalsIgnoreCase(quoteStatus)){
					PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					generate.updateQuoteInfo();
					remarks=",Admin Approved";
				}
				
				String cmdtyQry = "select COMMODITY_CODE, COMMODITY_DESCRIPTION, SUMINSURED, COMMODITY_RATE, WSRCC_RATE from webservice_marine_commodity where reference_number ='"+referenceNo+"'";
				String commodities[][] = runner.multipleSelection(cmdtyQry);
				for(int i=0;i<commodities.length;i++)
				{
					commodityResponse=commodityResponse+"<commodity><CommodityCode>"+(commodities[i][0]==null?"":commodities[i][0])+"</CommodityCode>" +
									"<CommodityDescription>"+(commodities[i][1]==null?"":commodities[i][1])+"</CommodityDescription>" +
									"<SumInsured>"+(commodities[i][2]==null?"":commodities[i][2])+"</SumInsured>" +
									"<CommodityRate>"+(commodities[i][3]==null?"":commodities[i][3])+"</CommodityRate>" +
									"<WSRCCRate>"+(commodities[i][4]==null?"":commodities[i][4])+"</WSRCCRate></commodity>";
				}
				
				String quoteQry = "select to_char(received_date,'dd/mm/yyyy' ), QUOTE_NO, PREMIUM, STATUS, REMARKS,to_char(QUOTATION_DATE ,'dd/mm/yyyy'),MARINE_PREMIUM,WAR_PREMIUM,ISSUANCE_FEE,ADDITIONAL_PREMIUM,(NVL (PREMIUM, 0) + NVL (ADDITIONAL_PREMIUM, 0)) TOTAL_PREMIUM from webservice_marine_quote where reference_number ='"
						          + referenceNo + "' and quote_no='"+quoteNo+"'";
				String quoteValues[][] = runner.multipleSelection(quoteQry);
				
				String quoteQry1 = "SELECT (EQUIVALENT_USD + NVL (TOTAL_SALE_TERM_CHARGES, 0)+ NVL (TOTAL_TOLERANCE_CHARGES, 0)) EQUIVALENT,TOTAL_SUM_INSURED FROM MARINE_DATA WHERE APPLICATION_NO=(SELECT APPLICATION_NO FROM WEBSERVICE_MARINE_QUOTE WHERE REFERENCE_NUMBER='"+referenceNo+"')";
				String quoteValues1[][] = runner.multipleSelection(quoteQry1);
				String referalStatus="P".equalsIgnoreCase(quoteStatus)?quoteStatus:((quoteValues[0][3]==null?"":quoteValues[0][3]));
					
				response="<PremiumInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<PolicyStartDate>"+quoteValues[0][0]+"</PolicyStartDate>" +
						"<QuotationDate>"+(quoteValues[0][5]==null?"":quoteValues[0][5])+"</QuotationDate>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<MarinePremium>"+(quoteValues[0][6]==null?"":quoteValues[0][6])+"</MarinePremium>" +
						"<WarPremium>"+(quoteValues[0][7]==null?"":quoteValues[0][7])+"</WarPremium>" +
						"<AdditionalPremium>"+(quoteValues[0][9]==null?"":quoteValues[0][9])+"</AdditionalPremium>" +
						"<IssuanceFee>"+(quoteValues[0][8]==null?"":quoteValues[0][8])+"</IssuanceFee>" +
						"<TotalPremium>"+(quoteValues[0][6]==null?"":(quoteValues[0][10]==null?"":quoteValues[0][10]))+"</TotalPremium>" +
						"<TotalSumInsured>"+(quoteValues1.length>0?(quoteValues1[0][0]==null?"":quoteValues1[0][0]):"")+"</TotalSumInsured>" +
						"<RefferalStatus>"+referalStatus+"</RefferalStatus>" +
						"<RefferalRemarks>"+(quoteValues[0][4]==null?"":quoteValues[0][4])+""+remarks+"</RefferalRemarks>" +
						"<PromotionalCode>"+promoCode+"</PromotionalCode>" +commodityResponse+
						"</PremiumInformation>";
			}
			else
			{
				commodityResponse="";
				System.out.println("test::");
				String cmdtyQry = "select COMMODITY_CODE, COMMODITY_DESCRIPTION, SUMINSURED, COMMODITY_RATE, WSRCC_RATE from webservice_marine_commodity_rw where amend_id=(select max(amend_id) from webservice_marine_commodity_rw where reference_number ='"+referenceNo+"' ) and reference_number ='"+referenceNo+"'";
				String commodities[][] = runner.multipleSelection(cmdtyQry);
				for(int i=0;i<commodities.length;i++)
				{
					commodityResponse=commodityResponse+"<commodity><CommodityCode>"+(commodities[i][0]==null?"":commodities[i][0])+"</CommodityCode>" +
									"<CommodityDescription>"+(commodities[i][1]==null?"":commodities[i][1])+"</CommodityDescription>" +
									"<SumInsured>"+(commodities[i][2]==null?"":commodities[i][2])+"</SumInsured>" +
									"<CommodityRate>"+(commodities[i][3]==null?"":commodities[i][3])+"</CommodityRate>" +
									"<WSRCCRate>"+(commodities[i][4]==null?"":commodities[i][4])+"</WSRCCRate></commodity>";
				}
				response="<PremiumInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<PolicyStartDate></PolicyStartDate>" +
						"<QuotationDate></QuotationDate>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<MarinePremium></WarPremium>" +
						"<WarPremium></WarPremium>" +
						"<AdditionalPremium></AdditionalPremium>" +
						"<IssuanceFee></IssuanceFee>" +
						"<TotalPremium></TotalPremium>" +
						"<TotalSumInsured></TotalSumInsured>" +
						"<RefferalStatus></RefferalStatus>" +
						"<RefferalRemarks>Reference number with quoteno requested not exists</RefferalRemarks>" 
						+commodityResponse+
						"</PremiumInformation>";
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println("exit");
		return response;
	}
	public String generateDraftResponse(String requestInfo) {
		String response="";
		String referenceNo = "";
		String quoteNo = "";
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			// To read XML
			String count = "";
			NodeList nList1 = doc.getElementsByTagName("DraftInformation");
			Node nNode1 = nList1.item(0);
			if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode1;
				referenceNo =  getTagValue("ReferenceNumber", eElement);
				quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
			}
		
			String countQry = "select count(*) from webservice_marine_quote where reference_number='"+referenceNo+"' and quote_no='"+quoteNo+"'";
			count = runner.singleSelection(countQry);
			if(!count.equalsIgnoreCase("0") )
			{
				String selcount = runner.singleSelection("select count(*) from webservice_marine_quote where status = 'P' and reference_number='"+referenceNo+"' and quote_no='"+quoteNo+"'");
				System.out.println("selcount..."+selcount+"..."+"select count(*) from webservice_marine_quote where status = 'P' and reference_number='"+referenceNo+"' and quote_no='"+quoteNo+"'");
				if(selcount.equalsIgnoreCase("0")){
				String quoteQry = "select DRAFT_FILE_PATH, STATUS, REMARKS from webservice_marine_quote where reference_number ='"
						          + referenceNo + "' and quote_no='"+quoteNo+"'";
				String quoteValues[][] = runner.multipleSelection(quoteQry);
				//String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				//String basePath=path.substring(0, path.indexOf("WEB-INF"));
				String fileName = quoteValues[0][0]==null?"":quoteValues[0][0];
				StringBuffer fileContent = new StringBuffer();
				String remarks = "";
				if(!fileName.equalsIgnoreCase("")){
					File file = new File(fileName);
					InputStream in = new FileInputStream(fileName);
					byte[] bytes = new byte[(int)file.length()];
					in.read(bytes);
					in.close();
					fileContent.append(Base64.encode(bytes)); 
					
					String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET DRAFT_FILE_PATH = '' WHERE QUOTE_NO='"+quoteNo+"'";
					runner.updation(update);
				}
				else
				{
					remarks = "File Not Generated";
				}
				
				String selRefStatus = "select a.admin_referral_status, b.remarks, b.status from marine_data a, position_master b where a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
				System.out.println(">>"+selRefStatus);
				String referral[][] = runner.multipleSelection(selRefStatus);
				String adminReferral = referral[0][0]==null?"":referral[0][0];
				String referralRemarks = referral[0][1]==null?"":referral[0][1];
				String quoteStatus = referral[0][2]==null?"":referral[0][2];
			    System.out.println("ref rem::"+referralRemarks);
				
			    String remarks2="";
				if("Y".equalsIgnoreCase(adminReferral) || ("Referal".equalsIgnoreCase(referralRemarks) && "Y".equals(quoteStatus))){
					remarks2=",Admin Referral";
				}else if("R".equalsIgnoreCase(quoteStatus)){
					//PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					//generate.updateQuoteInfo();
					remarks=",Admin Rejected";
					String update = "UPDATE webservice_marine_quote SET PREMIUM= '0',MARINE_PREMIUM='0',WAR_PREMIUM='0', ISSUANCE_FEE='0',ADDITIONAL_PREMIUM='0' where reference_number ='"+referenceNo+"'";
					runner.updation(update);
				}
				else if (referralRemarks.equalsIgnoreCase("Admin") && !"P".equalsIgnoreCase(quoteStatus)){
					remarks2=",Admin Approved";
				}
				
				fileName =fileName.substring(fileName.lastIndexOf("/")+1);
				response="<DraftInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<FileName>"+fileName+"</FileName>" +
						"<FileContent>"+fileContent+"</FileContent>" +
						"<ErrorStatus>"+(quoteValues[0][1]==null?"":quoteValues[0][1])+"</ErrorStatus>" +
						"<Remarks>"+(quoteValues[0][2]==null?"":quoteValues[0][2])+" "+remarks+""+remarks2+"</Remarks>" +
						"</DraftInformation>";
			}
				else{
					response="<DraftInformation>" +
					"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
					"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
					"<FileName></FileName>" +
					"<FileContent></FileContent>" +
					"<ErrorStatus></ErrorStatus>" 
					+"<Remarks>Draft Not Applicable</Remarks>"+
					"</DraftInformation>";
				}
				}
			else
			{
				
				response="<DraftInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<FileName></FileName>" +
						"<FileContent></FileContent>" +
						"<ErrorStatus></ErrorStatus>" 
						+"<Remarks>Reference Number with requested Quote Number not exists</Remarks>"+
						"</DraftInformation>";
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}
	public String generatePolicyResponse(String requestInfo,String remarks2) {
		String response="";
		String referenceNo = "";
		String quoteNo = "";
		String noteType = "";
		String basisVal = "";
		String paymentMode = "";
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			// To read XML
			String count = "";
			NodeList nList1 = doc.getElementsByTagName("PolicyInformation");
			Node nNode1 = nList1.item(0);
			if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode1;
				referenceNo =  getTagValue("ReferenceNumber", eElement);
				quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
			    noteType =  getTagValue("NoteType", eElement)==null?"":getTagValue("NoteType", eElement);
			    basisVal =  getTagValue("BasisVal", eElement)==null?"":getTagValue("BasisVal", eElement);
			    paymentMode = getTagValue("PaymentMode", eElement)==null?"":getTagValue("PaymentMode", eElement); 
			}
		
			String countQry = "select count(*) from webservice_marine_quote where reference_number='"+referenceNo+"' and quote_no='"+quoteNo+"'";
			count = runner.singleSelection(countQry);
			String validBasisOfVal = runner.singleSelection("select count(*) from CONSTANT_DETAIL where category_id='135' and remarks ='"+basisVal.toUpperCase()+"'");
			boolean validNote = noteType.equalsIgnoreCase("N") || noteType.equalsIgnoreCase("G");
			boolean validmode = paymentMode.equalsIgnoreCase("CR") || paymentMode.equalsIgnoreCase("CA");
			if(!count.equalsIgnoreCase("0")&& remarks2.equalsIgnoreCase("") && validNote && !validBasisOfVal.equalsIgnoreCase("0"))
			{
				String quoteQry = "select POLICY_FILE_PATH, STATUS, REMARKS,POLICY_NO from webservice_marine_quote where reference_number ='"
						          + referenceNo + "' and quote_no='"+quoteNo+"'";
				String quoteValues[][] = runner.multipleSelection(quoteQry);
				//String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				//String basePath=path.substring(0, path.indexOf("WEB-INF"));
				//String fileName = basePath+"/PDFFile/Schedule/Copy/"+( quoteValues[0][0]==null?"":quoteValues[0][0]);
				String fileName = quoteValues[0][0]==null?"":quoteValues[0][0];
				String policyNo = quoteValues[0][3]==null?"":quoteValues[0][3];
				System.out.println("FileName policy::>"+fileName);
				StringBuffer fileContentschedule =new StringBuffer();
				InputStream in, inScheduleReceipt = null, inReceipt = null;
				StringBuffer fileContent =new StringBuffer();
				String remarks = "";
				
				String selRefStatus = "select a.admin_referral_status, b.remarks,b.INTEGRATION_ERROR,b.status, nvl(b.RN_GEN_STS,0), b.INTEGRATION_STATUS from marine_data a, position_master b where a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
				System.out.println(">>"+selRefStatus);
				String referral[][] = runner.multipleSelection(selRefStatus);
				String adminReferral = referral[0][0]==null?"":referral[0][0];
				String referralRemarks = referral[0][1]==null?"":referral[0][1];
				String error = referral[0][2]==null?"":referral[0][2];
				String quoteStatus = referral[0][3]==null?"":referral[0][3];
				
				if(!fileName.equalsIgnoreCase("")){
					try {
						File file = new File(fileName);
						in = new FileInputStream(file);
						byte[] bytes = new byte[(int)file.length()];
						in.read(bytes);
						in.close();
						if(policyNo.length()>0 && Integer.parseInt(referral[0][4])>0 && "Y".equals(referral[0][5])){
							fileContent.append( Base64.encode(bytes));
							try{
								String update = "UPDATE position_master SET RN_GEN_STS= nvl(RN_GEN_STS,0)+1 where policy_no='"+policyNo+"'";
								runner.updation(update);
							}catch(Exception e){
								System.out.println("UPDATing position_master for RN_GEN_STS===>exception"+e.getMessage());
							}
						}else if(policyNo.length()>0 && "Y".equals(referral[0][5])){
						
							fileContentschedule.append( Base64.encode(bytes));
							PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
							String fileNameReceipt=generate.receiptGeneration();
							StringBuffer fileContentReceipt =new StringBuffer();
							try {
								File fileReceipt = new File(fileNameReceipt);
								inReceipt = new FileInputStream(fileReceipt);
								byte[] bytesReceipt = new byte[(int)fileReceipt.length()];
								inReceipt.read(bytesReceipt);
								inReceipt.close();
								fileContentReceipt.append( Base64.encode(bytesReceipt));
							} catch (RuntimeException e) {
								remarks = " File Not Generated";
								System.out.println("File not genrated");
								e.printStackTrace();
							}
							if(remarks==null || "".equals(remarks)){
								List<InputStream> listIS=new ArrayList<InputStream>();
								if(in!=null){
									listIS.add(new FileInputStream(new File(fileName)));
								}
								if(inReceipt!=null){
									listIS.add(new FileInputStream(new File(fileNameReceipt)));
								}
								if(listIS!=null && listIS.size()>0){
									fileName=fileName.substring(0,fileName.lastIndexOf("/")+1)+policyNo+"ScheduleReceipt.pdf";
									File fileScheduleReceipt = new File(fileName);
									OutputStream outputScheduleReceipt = new FileOutputStream(fileScheduleReceipt);
									MergePDF.doMerge(listIS, outputScheduleReceipt);
									inScheduleReceipt = new FileInputStream(fileScheduleReceipt);
									byte[] bytesScheduleReceipt = new byte[(int)fileScheduleReceipt.length()];
									inScheduleReceipt.read(bytesScheduleReceipt);
									inScheduleReceipt.close();
									fileContent.append( Base64.encode(bytesScheduleReceipt));
								}
							}
							try{
								String update = "UPDATE position_master SET RN_GEN_STS= nvl(RN_GEN_STS,0)+1 where policy_no='"+policyNo+"'";
								runner.updation(update);
							}catch(Exception e){
								System.out.println("UPDATing position_master for RN_GEN_STS===>exception"+e.getMessage());
							}
						}
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}else{
					remarks = " File Not Generated";
					System.out.println("File not genrated");
				}
				String remarksReferral="";
				if("Y".equalsIgnoreCase(adminReferral) || ("Referal".equalsIgnoreCase(referralRemarks) && "Y".equals(quoteStatus))){
					remarksReferral=",Admin Referral";
				} else if("R".equalsIgnoreCase(quoteStatus)){
					remarks=",Admin Rejected"; 
					String update = "UPDATE webservice_marine_quote SET PREMIUM= '0',MARINE_PREMIUM='0',WAR_PREMIUM='0', ISSUANCE_FEE='0',ADDITIONAL_PREMIUM='0' where reference_number ='"+referenceNo+"'";
					runner.updation(update);
				} else if (referralRemarks.equalsIgnoreCase("Admin") && !"P".equalsIgnoreCase(quoteStatus)){
					remarksReferral=",Admin Approved";
				}
				fileName =fileName.substring(fileName.lastIndexOf("/")+1);
				System.out.println("start res");
				response="<PolicyInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<PolicyNo>"+policyNo+"</PolicyNo>" +
						"<FileName>"+fileName+"</FileName>" +
						"<FileContent>"+fileContent+"</FileContent>" +
						"<ErrorStatus>"+(quoteValues[0][1]==null?"":quoteValues[0][1])+"</ErrorStatus>" +
						"<Remarks>"+(quoteValues[0][2]==null?"":quoteValues[0][2])+" "+remarks+""+remarksReferral+""+error+"</Remarks>" +
						"</PolicyInformation>";
				/*if(policyNo.length()>0 && Integer.parseInt(referral[0][4])<=0 && "Y".equals(referral[0][5])){
					PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					generate.receiptGeneration();
				}if(policyNo.length()>0 && "Y".equals(referral[0][5])){
					String update = "UPDATE position_master SET RN_GEN_STS= nvl(RN_GEN_STS,0)+1 where policy_no='"+policyNo+"'";
					runner.updation(update);
				}*/
			}
			else
			{
				String remarks="";
				if(count.equalsIgnoreCase("0")){
					remarks="Reference Number with requested Quote Number not exists";
				}
				else if(!remarks2.equalsIgnoreCase(""))
				{
				  remarks=remarks2;	
				}
				if(validBasisOfVal.equalsIgnoreCase("0") || !validNote || !validmode){
					remarks="";
					if(validBasisOfVal.equalsIgnoreCase("0"))
						remarks = "Invalid Basis of valuation";
					if(!validNote)
						remarks = remarks+" ,Invalid Note Type";
					if(!validmode)
						remarks = remarks+" ,Invalid Payment Mode";
				}
				response="<PolicyInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<PolicyNo></PolicyNo>" +
						"<FileName></FileName>" +
						"<FileContent></FileContent>" +
						"<ErrorStatus></ErrorStatus>" 
						+"<Remarks>"+remarks+"</Remarks>"+
						"</PolicyInformation>";
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//System.out.println(response);
		return response;
	}
	public String generateReferralResponse(String requestInfo,String remarks2) {
		String response="";
		String referenceNo = "";
		String quoteNo = "";
		String AdminReferralStatus = "";
		String AdminReferralRemarks = "";
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			// To read XML
			String count = "";
			NodeList nList1 = doc.getElementsByTagName("ReferralInformation");
			Node nNode1 = nList1.item(0);
			if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode1;
				referenceNo =  getTagValue("ReferenceNumber", eElement);
				quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
				AdminReferralStatus =  getTagValue("AdminReferralStatus", eElement)==null?"":getTagValue("AdminReferralStatus", eElement);
				AdminReferralRemarks =  getTagValue("AdminReferralRemarks", eElement)==null?"":getTagValue("AdminReferralRemarks", eElement);
			}
			String countQry = "select count(*) from webservice_marine_quote where reference_number='"+referenceNo+"' and quote_no='"+quoteNo+"'";
			count = runner.singleSelection(countQry);
			boolean validStatus = AdminReferralStatus.equalsIgnoreCase("Y") || AdminReferralStatus.equalsIgnoreCase("N");
			String selRefStatus = "select a.admin_referral_status, b.remarks from marine_data a, position_master b where a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
			System.out.println("APPROVED STATUS::"+selRefStatus);
			String referral[][] = runner.multipleSelection(selRefStatus);
			String referralRemarks = referral[0][1]==null?"":referral[0][1];
			
			if(!count.equalsIgnoreCase("0")&& remarks2.equalsIgnoreCase("") && validStatus && !AdminReferralRemarks.equalsIgnoreCase(""))
			{
				System.out.println("*INSIDE VALIDATION*");
				String quoteQry = "select QUOTE_NO, ADMIN_REFERRAL_YN, ADMIN_REFERRAL_REMARKS from webservice_marine_quote where reference_number ='"
			          + referenceNo + "'";
	            String quoteValues[][] = runner.multipleSelection(quoteQry);
	            //checking the given Quote is Approved
	            
				String remarksReferral="";
				if (!AdminReferralRemarks.equalsIgnoreCase("") && AdminReferralStatus.equalsIgnoreCase("N")){
					
					remarksReferral=",Remove Referral Remarks";
				}
                else if (referralRemarks.equalsIgnoreCase("Admin")){
					
					remarksReferral=",The Given Quote Already Approved By Underwriter";
				}else{
					remarksReferral="The Given Quote Accepted for Referral";
				}
	            response="<ReferralInformation>" +
			             "<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
			             "<QuoteNumber>"+(quoteValues[0][0]==null?"":quoteValues[0][0])+"</QuoteNumber>" +
			             "<AdminReferralStatus>"+(quoteValues[0][1]==null?"":quoteValues[0][1])+"</AdminReferralStatus>" +
			            // "<AdminReferralRemarks>"+(quoteValues[0][2]==null?"":quoteValues[0][2])+""+remarksReferral+"</AdminReferralRemarks>" +
			             "<AdminReferralRemarks>"+remarksReferral+"</AdminReferralRemarks>" +
			             "</ReferralInformation>";
			}	
			else
			{
				System.out.println("<INSIDE VALIDATION>");
				String remarks="";
				if(count.equalsIgnoreCase("0")){
					remarks="Reference Number with requested Quote Number not exists";
				}
				else if(!remarks2.equalsIgnoreCase(""))
				{
				  remarks=remarks2;	
				}
				if(!validStatus || validStatus){
					remarks="";
					if(count.equalsIgnoreCase("0"))
						remarks="Reference Number with requested Quote Number not exists";
					if(!validStatus)
						remarks = remarks+" ,Invalid Rferral Status";
					if(AdminReferralRemarks.equalsIgnoreCase("") && AdminReferralStatus.equalsIgnoreCase("Y"))
						remarks = remarks+" ,Enter AdminReferral Remarks";
					if(!AdminReferralRemarks.equalsIgnoreCase("") && AdminReferralStatus.equalsIgnoreCase("N"))
						remarks = remarks+" ,Remove AdminReferral Remarks";
					if(referralRemarks.equalsIgnoreCase("Admin"))
						remarks = remarks+" ,The Given Quote Already Approved By Admin";
					if(AdminReferralStatus.equalsIgnoreCase("N"))
						remarks = remarks+" ,The Given Quote Accepted for Normal";
				}
				
			   response="<ReferralInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<AdminReferralStatus></AdminReferralStatus>" +
						"<AdminReferralRemarks>"+remarks+"</AdminReferralRemarks>" +
						"</ReferralInformation>";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//System.out.println(response);
		return response;
	}
	public List getPolicyDetails(String requestInfo, String info) {
		System.out.println("::Enters getPolicyDetails::");
		String request="";
		String refNo="";
		String count="";
		String pdfPreShowStatus="";
		String pdfBankerStatus="";
		String pdfGenerateStatus="";
		String pdfBankerAssuredStatus="";
		String pdfCurrencyStatus="";
		String pdfStampStatus="";
		String pdfForeignExcessStatus="";
		String quoteNo = "";
		String referralStatus = "";
		String noteType ="";
		String paymentMode = "";
		String basisVal = "";
		String paymentReference = "";
		String countQuery2="";
		String count2="";
		
		List<String> list = new ArrayList<String>();
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName(info);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
			    request =  getTagValue("PolicyRequest", eElement);
			    refNo = getTagValue("ReferenceNumber", eElement);
			    pdfPreShowStatus =  getTagValue("PDFPreShowStatus", eElement);
			    pdfBankerStatus =  getTagValue("PDFBankerStatus", eElement);
			    pdfGenerateStatus =  getTagValue("PDFGenerateStatus", eElement);
			    pdfBankerAssuredStatus =  getTagValue("PDFBankerAssuredStatus", eElement);
			    pdfCurrencyStatus =  getTagValue("PDFCurrencyStatus", eElement);
			    pdfStampStatus =  getTagValue("PDFStampStatus", eElement);
			    pdfForeignExcessStatus =  getTagValue("PDFForeignExcessStatus", eElement);
			    noteType =  getTagValue("NoteType", eElement);
			    paymentMode =  getTagValue("PaymentMode", eElement);
			    basisVal =  getTagValue("BasisVal", eElement);
			    paymentReference =  getTagValue("PaymentReference", eElement);
				quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
			}
			if(pdfStampStatus.equalsIgnoreCase("Yes")){
				pdfStampStatus="Y";
			}else{
				pdfStampStatus="N";
			}
		
			String countQry="select count (*) from webservice_marine_quote where (policy_requested is not null and policy_file_path is not null) and reference_number='"+refNo+"'";
			System.out.println(countQry);
			
			count=runner.singleSelection(countQry);
			System.out.println("Count::>>>"+count);
			
			String selRefStatus = "select a.admin_referral_status, b.remarks,b.status from marine_data a, position_master b where a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
			System.out.println(">>"+selRefStatus);
			String referral[][] = runner.multipleSelection(selRefStatus);
			//REFERRAL
			String adminReferral="";
			String referralRemarks ="";
			String policyStatus ="";
			if(referral.length>0){
			adminReferral = referral[0][0]==null?"":referral[0][0];
			referralRemarks = referral[0][1]==null?"":referral[0][1];
			policyStatus = referral[0][2]==null?"":referral[0][2];
			}
			//REF NO MISMATCH
			countQuery2 = "select count(*) from webservice_marine_quote where reference_number='"+refNo+"' and quote_no='"+quoteNo+"'";
			count2 = runner.singleSelection(countQuery2);
			if(count2.equalsIgnoreCase("0")){
			String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET REMARKS = 'Reference Number with requested Quote Number not exists' WHERE QUOTE_NO='"+quoteNo+"'";
			runner.updation(update);
			}
			//PAYMENT REF VALIDATION
			String paymentQuery="";
			String paymentcount="1";
			if(!paymentReference.equalsIgnoreCase("") && !policyStatus.trim().equalsIgnoreCase("P")){
				paymentQuery = "select count(*) from POSITION_MASTER where PAYMENT_REFERENCE='"+paymentReference+"' and QUOTE_NO !='"+quoteNo+"'";
				paymentcount = runner.singleSelection(paymentQuery);
			}
			
			//POLICY ALREADY GENERATED
			if(policyStatus.trim().equalsIgnoreCase("P")){
				String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET REMARKS = 'Given Reference Number Already in Policy Status' WHERE QUOTE_NO='"+quoteNo+"'";
				runner.updation(update);
			}else{
				if(!paymentcount.equalsIgnoreCase("0") && !paymentcount.equalsIgnoreCase("")){
					String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET REMARKS = 'Payment Reference Number Already exists' WHERE reference_number='"+refNo+"'";
					runner.updation(update);
				}else if(!paymentReference.contains("QPREF") && !paymentcount.equalsIgnoreCase("")){
					String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET REMARKS = 'Payment Reference Number is Invalid' WHERE reference_number='"+refNo+"'";
					runner.updation(update);	
				}else if(paymentcount.equalsIgnoreCase("")){
					String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET REMARKS = 'Payment Reference Number Not Exists' WHERE reference_number='"+refNo+"'";
					runner.updation(update);	
				}
			}
			
			//String remarksReferral="";
			if(!(adminReferral.equalsIgnoreCase("Y") || referralRemarks.equalsIgnoreCase("Referal")) && !count2.equalsIgnoreCase("0") && !paymentReference.equalsIgnoreCase("") && paymentcount.equalsIgnoreCase("0") && paymentReference.contains("QPREF")){
				referralStatus="No";
				updateDetails(refNo);
				String update1 = "UPDATE POSITION_MASTER SET PAYMENT_REFERENCE = '"+paymentReference+"' WHERE QUOTE_NO='"+quoteNo+"'";
				runner.updation(update1);
			}
			if(!(referralRemarks.equalsIgnoreCase("Admin"))){
			boolean flag=updateReferralDetails(refNo);
			if(flag){
				referralStatus="Yes";
				String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET REMARKS = 'Policy Date Referral' WHERE QUOTE_NO='"+quoteNo+"'";
				runner.updation(update);
			}
			}
			
			String status="";
			status= runner.singleSelection("select count (*) from webservice_marine_quote where upper(status)='Q' and reference_number='"+refNo+"' ");
			String validBasisOfVal = runner.singleSelection("select count(*) from CONSTANT_DETAIL where category_id='135' and remarks ='"+basisVal.toUpperCase()+"'");
			boolean validNote = noteType.equalsIgnoreCase("N") || noteType.equalsIgnoreCase("G");
			boolean validPayment = paymentMode.equalsIgnoreCase("CA") || paymentMode.equalsIgnoreCase("CR");
			if(!validNote || !validPayment || validBasisOfVal.equalsIgnoreCase("0")){
				status = "0";
			}
			if(count.equalsIgnoreCase("0") && !status.equalsIgnoreCase("0") && !validBasisOfVal.equalsIgnoreCase("0") && validNote && validPayment)
			{
				try {
					String update = "update webservice_marine_quote set POLICY_REQUESTED= '"+request+"'," +
					" PDF_PRE_SHOW_STATUS= '"+pdfPreShowStatus+"', PDF_BANKER_STATUS= '"+pdfBankerStatus+"', PDF_GENERATE_STATUS= '"+pdfGenerateStatus+"', " +
					"PDF_BANKER_ASSURED_STATUS= '"+pdfBankerAssuredStatus+"', PDF_CURRENCY_STATUS= '"+pdfCurrencyStatus+"' , NOTE_TYPE = '"+noteType.toUpperCase()+"', PAYMENT_MODE = '"+paymentMode+"', BASIS_VAL = '"+basisVal.toUpperCase()+"', PAYMENT_REFERENCE = '"+paymentReference+"', PDF_STAMP_STATUS = '"+pdfStampStatus+"', PDF_EXCESS_STATUS = '"+pdfForeignExcessStatus+"' where reference_number='"+refNo+"'";
					
					runner.updation(update);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				}
			 
			 
			try {
				
				list.add(0,request);
				list.add(1,refNo);
				list.add(2,count);
				list.add(3,status);
				list.add(4,referralStatus);
			} catch (RuntimeException e) {
				
				e.printStackTrace();
			}
			
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	         
		return list;
	}
	public List getReferralDetails(String requestInfo, String info) {
		System.out.println("::Enters getReferralDetails::");
		String refNo="";
		String count="";
		String quoteNo = "";
		String AdminReferralStatus = "";
		String AdminReferralRemarks = "";
		
		
		List<String> list = new ArrayList<String>();
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName(info);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
			    refNo = getTagValue("ReferenceNumber", eElement);
			    AdminReferralStatus =  getTagValue("AdminReferralStatus", eElement);
			    AdminReferralRemarks =  getTagValue("AdminReferralRemarks", eElement);
				quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
			}
		
			String countQry = "select count(*) from webservice_marine_quote where reference_number='"+refNo+"' and quote_no='"+quoteNo+"'";
			count = runner.singleSelection(countQry);
			System.out.println("count::>>>"+count);
			
			String selRefStatus = "select b.remarks from marine_data a, position_master b where a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
			System.out.println("selRefStatus>>"+selRefStatus);
			String referral[][] = runner.multipleSelection(selRefStatus);
			String referralRemarks = referral[0][0]==null?"":referral[0][0];
			System.out.println("referralRemarks>>"+referralRemarks);
			
			if(!count.equalsIgnoreCase("0"))
			{
			try {
					String update = "update webservice_marine_quote set ADMIN_REFERRAL_REMARKS= '"+AdminReferralRemarks+"', ADMIN_REFERRAL_YN= '"+AdminReferralStatus+"' where reference_number='"+refNo+"'";
					runner.updation(update);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				}
			 
			 
			try {
				
				list.add(0,refNo);
				list.add(1,count);
				list.add(2,AdminReferralStatus);
				list.add(3,AdminReferralRemarks);
				list.add(4,referralRemarks);
			} catch (RuntimeException e) {
				
				e.printStackTrace();
			}
			
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	         
		return list;
	}
	public List getDraftDetails(String requestInfo, String info) {
	
		String refNo="";
		String count="";
		String referralStatus="";
		String quoteNo="";
		String pdfPreShowStatus="";
		String pdfBankerStatus="";
		String pdfBankerAssuredStatus="";
		String pdfCurrencyStatus="";
		String basisVal="";
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(info);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
			    refNo = getTagValue("ReferenceNumber", eElement);
			    quoteNo = getTagValue("QuoteNumber", eElement);
			    pdfPreShowStatus =  getTagValue("PDFPreShowStatus", eElement);
			    pdfBankerStatus =  getTagValue("PDFBankerStatus", eElement);
			    pdfBankerAssuredStatus =  getTagValue("PDFBankerAssuredStatus", eElement);
			    pdfCurrencyStatus =  getTagValue("PDFCurrencyStatus", eElement);
			    basisVal =  getTagValue("BasisVal", eElement);
			}
				String statusPolicy= runner.singleSelection("select status from webservice_marine_quote where  quote_no='"+quoteNo+"' ");
				
			    String selRefStatus = "select a.admin_referral_status, b.remarks from marine_data a, position_master b where a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
				System.out.println(">>"+selRefStatus);
				String referral[][] = runner.multipleSelection(selRefStatus);
				String adminReferral = referral[0][0]==null?"":referral[0][0];
				String referralRemarks = referral[0][1]==null?"":referral[0][1];
				System.out.println("ref rem::"+referralRemarks);
				
				if(!statusPolicy.equalsIgnoreCase("p") && (!(adminReferral.equalsIgnoreCase("Y") || referralRemarks.equalsIgnoreCase("Referal")))){
					referralStatus="No";
					updateDetails(refNo);
				}
				
				
			String countQry="select count (*) from webservice_marine_quote where draft_file_path is not null and reference_number='"+refNo+"'";
			System.out.println(countQry);
			count=runner.singleSelection(countQry);
			String status="";
			status= runner.singleSelection("select count (*) from webservice_marine_quote where upper(status)='Q' and reference_number='"+refNo+"' ");
			try{
			String update = "update webservice_marine_quote set PDF_PRE_SHOW_STATUS= '"+pdfPreShowStatus+"', PDF_BANKER_STATUS= '"+pdfBankerStatus+"', PDF_BANKER_ASSURED_STATUS= '"+pdfBankerAssuredStatus+"', PDF_CURRENCY_STATUS= '"+pdfCurrencyStatus+"', BASIS_VAL = '"+basisVal.toUpperCase()+"' where reference_number='"+refNo+"'";
			runner.updation(update);
			}catch (RuntimeException e) {
				e.printStackTrace();
			}
			
			try {
				list.add(refNo);
				list.add(count);
				list.add(status);
				list.add(referralStatus);
			} catch (RuntimeException e) {
				
				e.printStackTrace();
			}
			
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	
		return list;
	}
	public String getXML() {
		
		String xml = runner.singleSelection("select to_clob(xml_test) from dual") ;
		System.out.println("XML values:::"+xml);
		return xml;
	}
	public String emailValidate(String mailid)
	{
		String returnval="";
		
		try
		{mailid=mailid.trim();
		if(mailid.length()>0)
		{
			if(mailid.indexOf("@")==-1)
			{
				returnval="Invalid";
			}
			//else if((mailid.substring(0,mailid.indexOf("@"))).length()<2 || (mailid.substring(mailid.indexOf("@") + 1)).length()<5  || (mailid.substring(mailid.indexOf("@") + 1)).indexOf(".") == -1)
			else if((mailid.substring(0,mailid.indexOf("@"))).length()<1 || (mailid.substring(mailid.indexOf("@") + 1)).length()<3  || (mailid.substring(mailid.indexOf("@") + 1)).indexOf(".") == -1)
			{
				returnval="Invalid";
			}
    
		}
		else
				returnval="needed";
		}catch(Exception e)
		{
			returnval="needed";
		}
		return returnval;

	}
	public List getQuotePDFDetails(String requestInfo, String info) {
		
		String refNo="";
		String count="";
		String quoteNo="";
		String remarks = "";
		String  statusPolicy = "";
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(info);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
			    quoteNo = getTagValue("QuoteNumber", eElement);
			}
			String countQry="select count (*) from webservice_marine_quote where quote_no='"+quoteNo+"'";
			System.out.println(countQry);
			count=runner.singleSelection(countQry);
			if(!count.equalsIgnoreCase("0")){
			String vals[][]= runner.multipleSelection("select reference_number,status from webservice_marine_quote where  quote_no='"+quoteNo+"' ");
			refNo = vals[0][0];
			statusPolicy = vals[0][1];
			}
			 countQry="select count (*) from webservice_marine_quote where quote_file_path is not null and quote_no='"+quoteNo+"'";
			System.out.println(countQry);
			count=runner.singleSelection(countQry);
			String selRefStatus = "select a.admin_referral_status, b.remarks from marine_data a, position_master b where a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
			System.out.println(">>"+selRefStatus);
			String referral[][] = runner.multipleSelection(selRefStatus);
			String adminReferral = referral[0][0]==null?"":referral[0][0];
			String referralRemarks = referral[0][1]==null?"":referral[0][1];
			
			if(!statusPolicy.equalsIgnoreCase("P") && !(adminReferral.equalsIgnoreCase("Y") || referralRemarks.equalsIgnoreCase("Referal"))){
			updateDetails(refNo);
			}
			
			String status="";
			status= runner.singleSelection("select count (*) from webservice_marine_quote where (upper(status)='Q' or upper(status)='P') and quote_no='"+quoteNo+"' ");
			
			try {
				list.add(refNo);
				list.add(count);
				list.add(status);
				list.add(quoteNo);
			
			} catch (RuntimeException e) {
				
				e.printStackTrace();
			}
			
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	
		return list;
	}
	
	public String generatePDFResponse(String requestInfo,String type) {
		String response="";
		
		String quoteNo = "";
		String informationTag = "";
		String filePathField = "";
		if(type.equalsIgnoreCase("quote") ){
			informationTag = "QuoteInformation";
			filePathField ="QUOTE_FILE_PATH";
		}
		else if (type.equalsIgnoreCase("credit")){
			informationTag = "CreditInformation";
			filePathField ="CREDIT_FILE_PATH";
		}
		else if (type.equalsIgnoreCase("debit")){
			informationTag = "DebitInformation";
			filePathField ="DEBIT_FILE_PATH";
		}else if (type.equalsIgnoreCase("receipt")){
			informationTag = "ReceiptInformation";
			filePathField ="RECEIPT_FILE_PATH";
		}
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			// To read XML
			String count = "";
			NodeList nList1 = doc.getElementsByTagName(informationTag);
			Node nNode1 = nList1.item(0);
			if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode1;
				quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
			}
		
			String countQry = "select count(*) from webservice_marine_quote where quote_no='"+quoteNo+"'";
			count = runner.singleSelection(countQry);
			if(!count.equalsIgnoreCase("0") )
			{
				String quoteQry = "select "+filePathField+", STATUS, REMARKS from webservice_marine_quote where  quote_no='"+quoteNo+"'";
				String quoteValues[][] = runner.multipleSelection(quoteQry);
				//String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				//String basePath=path.substring(0, path.indexOf("WEB-INF"));
				String fileName = quoteValues[0][0]==null?"":quoteValues[0][0];
				StringBuffer fileContent = new StringBuffer();
				String remarks = "";
				if(!fileName.equalsIgnoreCase("")){
					File file = new File(fileName);
					InputStream in = new FileInputStream(fileName);
					byte[] bytes = new byte[(int)file.length()];
					in.read(bytes);
					in.close();
					fileContent.append(Base64.encode(bytes)); 
					
				if(type.equalsIgnoreCase("quote") ){
					String update = "UPDATE WEBSERVICE_MARINE_QUOTE SET QUOTE_FILE_PATH = '' WHERE QUOTE_NO='"+quoteNo+"'";
					runner.updation(update);
				}
				}
				else
				{
					remarks = "File Not Generated";
				}
				 if (type.equalsIgnoreCase("debit")){
					String debitCount = runner.singleSelection("select count(*) from position_master where quote_no='"+quoteNo+"' and debit_note_no is not null");
					System.out.println("select count(*) from position_master where quote_no='"+quoteNo+"' and debit_note_no is not null");
					if(debitCount.equalsIgnoreCase("0")){
					 remarks = 	"Debit Note Not Available for this Quote No";
					}
				 }
				 if (type.equalsIgnoreCase("credit")){
						String creditCount = runner.singleSelection("select count(*) from position_master where quote_no='"+quoteNo+"' and credit_note_no is not null");
						System.out.println("select count(*) from position_master where quote_no='"+quoteNo+"' and credit_note_no is not null");
						if(creditCount.equalsIgnoreCase("0")){
						 remarks = 	"Credit Note Not Available for this Quote No";
						}
					 }
				String status= runner.singleSelection("select count (*) from webservice_marine_quote where upper(status)='P' and quote_no='"+quoteNo+"' ");
			
				if(status.equalsIgnoreCase("0") && !type.equalsIgnoreCase("quote") ){
					remarks = "Policy Not Generated";
				}
				
				 status= runner.singleSelection("select count (*) from webservice_marine_quote where (upper(status)='Q' or  upper(status)='P') and quote_no='"+quoteNo+"' ");
				
				if(status.equalsIgnoreCase("0") && type.equalsIgnoreCase("quote") ){
					remarks = "Quote Not Generated";
				}
				fileName =fileName.substring(fileName.lastIndexOf("/")+1);
				response="<"+informationTag+">" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<FileName>"+fileName+"</FileName>" +
						"<FileContent>"+fileContent+"</FileContent>" +
						"<ErrorStatus>"+(quoteValues[0][1]==null?"":quoteValues[0][1])+"</ErrorStatus>" +
						"<Remarks>"+(quoteValues[0][2]==null?"":quoteValues[0][2])+" "+remarks+"</Remarks>"+
						"</"+informationTag+">";
				}
				else
				{
				response="<"+informationTag+">" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<FileName></FileName>" +
						"<FileContent></FileContent>" +
						"<ErrorStatus></ErrorStatus>" 
						+"<Remarks>Requested Quote Number not exists</Remarks>"+
						"</"+informationTag+">";
				}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return response;
	}
	
	public List getCreditPDFDetails(String requestInfo, String info) {
		
		String refNo="";
		String count="";
		String quoteNo="";
		String remarks = "";
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(info);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
			    quoteNo = getTagValue("QuoteNumber", eElement);
			}
			refNo= runner.singleSelection("select reference_number from webservice_marine_quote where  quote_no='"+quoteNo+"' ");
			
			String countQry="select count (*) from webservice_marine_quote where credit_file_path is not null and quote_no='"+quoteNo+"'";
			System.out.println("COUNT query ::"+countQry);
			count=runner.singleSelection(countQry); 
			if(count.equalsIgnoreCase("0")){
			countQry="select count(*) from position_master where quote_no='"+quoteNo+"' and credit_note_no is  null";
			System.out.println("COUNT query ::"+countQry);
			count=runner.singleSelection(countQry); 
			}
			
			String status="";
			status= runner.singleSelection("select count (*) from webservice_marine_quote where upper(status)='P' and quote_no='"+quoteNo+"' ");
			
			try {
				list.add(refNo);
				list.add(count);
				list.add(status);
				list.add(quoteNo);
			
			} catch (RuntimeException e) {
				
				e.printStackTrace();
			}
			
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	
		return list;
	}

	public List getDebitPDFDetails(String requestInfo, String info) {
	
		String refNo="";
		String count="";
		String quoteNo="";
		String remarks = "";
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(info);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
			    quoteNo = getTagValue("QuoteNumber", eElement);
			}
			refNo= runner.singleSelection("select reference_number from webservice_marine_quote where  quote_no='"+quoteNo+"' ");
			
			String countQry="select count (*) from webservice_marine_quote where debit_file_path is not null and quote_no='"+quoteNo+"'";
			System.out.println("Count Qry:"+countQry);
			count=runner.singleSelection(countQry);
			if(count.equalsIgnoreCase("0")){
				countQry="select count(*) from position_master where quote_no='"+quoteNo+"' and debit_note_no is  null";
				System.out.println("COUNT query ::"+countQry);
				count=runner.singleSelection(countQry); 
				}
			String status="";
			status= runner.singleSelection("select count (*) from webservice_marine_quote where upper(status)='P' and quote_no='"+quoteNo+"' ");
			
			try {
				list.add(refNo);
				list.add(count);
				list.add(status);
				list.add(quoteNo);
				
			} catch (RuntimeException e) {
				
				e.printStackTrace();
			}
			
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		return list;
	}
	void updateDetails(String refNo){
		PolicyGenerationAction generate = new PolicyGenerationAction(refNo);
		generate.updateQuoteInfo();
		
	}
	boolean updateReferralDetails(String refNo){
		PolicyGenerationAction generate = new PolicyGenerationAction(refNo);
		boolean flag=generate.updateReferralDetails();
		return flag;
	
	}
	public String getPolicyStatus(String refNo) {
		String count= runner.singleSelection("SELECT COUNT (*) FROM WEBSERVICE_MARINE_QUOTE WHERE UPPER(STATUS)='P' AND REFERENCE_NUMBER='"+refNo+"' ");
		System.out.println("XML values:::"+count);
		return count;
	}
	
	public String generateQuoteInfoResponse(String requestInfo) {
		String response="";
		String commodityResponse="";
		String referenceNo = "";
		String quoteNo = "";
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			// To read XML
			String count = "";
			NodeList nList1 = doc.getElementsByTagName("QuoteInformation");
			Node nNode1 = nList1.item(0);
			if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode1;
				referenceNo =  getTagValue("ReferenceNumber", eElement);
				//quoteNo =  getTagValue("QuoteNumber", eElement)==null?"":getTagValue("QuoteNumber", eElement);
			}
		
			String countQry = "select count(*) from webservice_marine_quote where reference_number='"+referenceNo+"'";// and quote_no='"+quoteNo+"'";
			count = runner.singleSelection(countQry);
			if(!count.equalsIgnoreCase("0"))
			{
				countQry = "select distinct quote_no from webservice_marine_quote where reference_number='"+referenceNo+"'";
				quoteNo = runner.singleSelection(countQry);
				String selRefStatus = "select a.admin_referral_status, b.remarks, b.status, wmq.PROMOTIONAL_CODE from marine_data a, position_master b, webservice_marine_quote wmq where wmq.application_no=b.application_no and a.application_no=b.application_no and b.quote_no='"+quoteNo+"'";
				System.out.println(">>"+selRefStatus);
				String referral[][] = runner.multipleSelection(selRefStatus);
				String adminReferral = referral[0][0]==null?"":referral[0][0];
				String referralRemarks = referral[0][1]==null?"":referral[0][1];
				String quoteStatus = referral[0][2]==null?"":referral[0][2];
				String promoCode = referral[0][3]==null?"":referral[0][3];
			   
				String remarks="";
				if("Y".equalsIgnoreCase(adminReferral) || ("Referal".equalsIgnoreCase(referralRemarks) && "Y".equals(quoteStatus))){
					remarks=",Admin Referral";
				} else if("R".equalsIgnoreCase(quoteStatus)){
					remarks=",Admin Rejected";
					String update = "UPDATE webservice_marine_quote SET PREMIUM= '0',MARINE_PREMIUM='0',WAR_PREMIUM='0', ISSUANCE_FEE='0',ADDITIONAL_PREMIUM='0' where reference_number ='"+referenceNo+"'";
					runner.updation(update);
				} else if (referralRemarks.equalsIgnoreCase("Admin") && !"P".equalsIgnoreCase(quoteStatus)){
					PolicyGenerationAction generate = new PolicyGenerationAction(referenceNo);
					generate.updateQuoteInfo();
					remarks=",Admin Approved";
				}
				
				String cmdtyQry = "select COMMODITY_CODE, COMMODITY_DESCRIPTION, SUMINSURED, COMMODITY_RATE, WSRCC_RATE from webservice_marine_commodity where reference_number ='"+referenceNo+"'";
				String commodities[][] = runner.multipleSelection(cmdtyQry);
				for(int i=0;i<commodities.length;i++)
				{
					commodityResponse=commodityResponse+"<commodity><CommodityCode>"+(commodities[i][0]==null?"":commodities[i][0])+"</CommodityCode>" +
									"<CommodityDescription>"+(commodities[i][1]==null?"":commodities[i][1])+"</CommodityDescription>" +
									"<SumInsured>"+(commodities[i][2]==null?"":commodities[i][2])+"</SumInsured>" +
									"<CommodityRate>"+(commodities[i][3]==null?"":commodities[i][3])+"</CommodityRate>" +
									"<WSRCCRate>"+(commodities[i][4]==null?"":commodities[i][4])+"</WSRCCRate></commodity>";
				}
				
				String quoteQry = "select to_char(received_date,'dd/mm/yyyy' ), QUOTE_NO, PREMIUM, STATUS, REMARKS,to_char(QUOTATION_DATE ,'dd/mm/yyyy'),MARINE_PREMIUM,WAR_PREMIUM,ISSUANCE_FEE,ADDITIONAL_PREMIUM,(NVL (PREMIUM, 0) + NVL (ADDITIONAL_PREMIUM, 0)) TOTAL_PREMIUM from webservice_marine_quote where reference_number ='"
						          + referenceNo + "' and quote_no='"+quoteNo+"'";
				String quoteValues[][] = runner.multipleSelection(quoteQry);
				
				String quoteQry1 = "SELECT (EQUIVALENT_USD + NVL (TOTAL_SALE_TERM_CHARGES, 0)+ NVL (TOTAL_TOLERANCE_CHARGES, 0)) EQUIVALENT,TOTAL_SUM_INSURED FROM MARINE_DATA WHERE APPLICATION_NO=(SELECT APPLICATION_NO FROM WEBSERVICE_MARINE_QUOTE WHERE REFERENCE_NUMBER='"+referenceNo+"')";
				String quoteValues1[][] = runner.multipleSelection(quoteQry1);
				String referalStatus="P".equalsIgnoreCase(quoteStatus)?quoteStatus:((quoteValues[0][3]==null?"":quoteValues[0][3]));
					
				response="<QuoteInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<PolicyStartDate>"+quoteValues[0][0]+"</PolicyStartDate>" +
						"<QuotationDate>"+(quoteValues[0][5]==null?"":quoteValues[0][5])+"</QuotationDate>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<MarinePremium>"+(quoteValues[0][6]==null?"":quoteValues[0][6])+"</MarinePremium>" +
						"<WarPremium>"+(quoteValues[0][7]==null?"":quoteValues[0][7])+"</WarPremium>" +
						"<AdditionalPremium>"+(quoteValues[0][9]==null?"":quoteValues[0][9])+"</AdditionalPremium>" +
						"<IssuanceFee>"+(quoteValues[0][8]==null?"":quoteValues[0][8])+"</IssuanceFee>" +
						"<TotalPremium>"+(quoteValues[0][6]==null?"":(quoteValues[0][10]==null?"":quoteValues[0][10]))+"</TotalPremium>" +
						"<TotalSumInsured>"+(quoteValues1.length>0?(quoteValues1[0][0]==null?"":quoteValues1[0][0]):"")+"</TotalSumInsured>" +
						"<RefferalStatus>"+referalStatus+"</RefferalStatus>" +
						"<RefferalRemarks>"+(quoteValues[0][4]==null?"":quoteValues[0][4])+""+remarks+"</RefferalRemarks>" +
						"<PromotionalCode>"+promoCode+"</PromotionalCode>" +commodityResponse+
						"</QuoteInformation>";
			}
			else {
				response="<QuoteInformation>" +
						"<ReferenceNumber>"+referenceNo+"</ReferenceNumber>" +
						"<QuoteNumber>"+quoteNo+"</QuoteNumber>" +
						"<TotalPremium></TotalPremium>" +
						"<RefferalStatus></RefferalStatus>" +
						"<RefferalRemarks>Reference number requested not exists</RefferalRemarks>"+
						"</QuoteInformation>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("exit");
		return response;
	}
	public List getReceiptPdfDetails(String requestInfo, String info) {
		
		String refNo="";
		String quoteNo="";
		String policyNo="";
		List<String> list = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(requestInfo));
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(info);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
			    quoteNo = getTagValue("QuoteNumber", eElement);
			}
			refNo= runner.singleSelection("select reference_number from webservice_marine_quote where  quote_no='"+quoteNo+"' ");
			if(refNo!=null && !"".equals(refNo))
				policyNo= runner.singleSelection("select policy_no from position_master where upper(status)='P' and quote_no='"+quoteNo+"' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			list.add(refNo);
			list.add(policyNo);
			list.add(quoteNo);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateDTMonitor(String response, String referenceNo, String sno) {
		try{
			String checkQry = "update webservice_DT_monitor set RESPONSE_TIME=sysdate, RESPONSE_XML=?, REMARKS=? where sno=?";
			response=(response.replaceAll("\t","")).replaceAll("\n","");
			if(response.length()>3810){
				response=response.substring(0,3800);
			}
			
			runner.multipleUpdationDT(checkQry, new String[]{response, referenceNo, sno});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String insertDTMonitor(String request, String referenceNo, String methodType){
		String sno ="", monitorDT="", dateStatus="";
		try{
			String checkQry = "select status from constant_detail where category_id='152'";
			monitorDT=runner.singleSelection(checkQry);
			if("N".equalsIgnoreCase(monitorDT)){
				checkQry = "select 'Y' date_status from dual where trim(to_char(sysdate,'DAY')) IN ('FRIDAY','SATURDAY') or trim(to_char(sysdate,'HH24')) NOT BETWEEN 8 AND 18";
				dateStatus=runner.singleSelection(checkQry);
			}
			if("Y".equalsIgnoreCase(monitorDT) || (dateStatus!=null && "Y".equals(dateStatus))){
				checkQry = "select nvl(max(sno)+1,1) from webservice_DT_monitor";
				sno = runner.singleSelection(checkQry);
				
				checkQry="INSERT INTO webservice_DT_monitor(sno, request_time, request_xml,request_type, remarks) values(?,sysdate,?,?,?)";
				request=(request.replaceAll("\t","")).replaceAll("\n","");
				if(request.length()>3810){
					request=request.substring(0,3800);
				}
				runner.multipleInsertionDT(checkQry, new String[]{sno, request,methodType,referenceNo});
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sno;
	}
	public String[][] getPolicyProcessStatus(String refNo, String status) {
		String[][] policyProcess= runner.multipleSelection("select nvl(pm.policy_process_status,''), nvl(pm.quote_no,0) policy_process_status from position_master pm, webservice_marine_quote wmq where pm.quote_no =wmq.quote_no and wmq.REFERENCE_NUMBER='"+refNo+"'");
		if(policyProcess[0][0]==null || "".equals(policyProcess[0][0]) || "".equals(status))
			runner.updation("update position_master set POLICY_PROCESS_STATUS='"+status+"' where QUOTE_NO='"+policyProcess[0][1]+"'");
		return policyProcess;
	}
}	
