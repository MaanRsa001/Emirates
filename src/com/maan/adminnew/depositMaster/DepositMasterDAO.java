package com.maan.adminnew.depositMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class DepositMasterDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(DepositMasterDAO.class);
	private String query="";
	
	public List<DepositMasterBean>getCbcList(String branchCode1, String searchField, String searchString, String searchOper, String mode, DepositMasterBean bean){
		logger.info("Enter===>getCbcList");
		List<DepositMasterBean> depositList=new ArrayList<DepositMasterBean>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		Object[] obj=null;
		String branchCode=branchCode1;
		String str="like '%'||upper(?)||'%'";
		try{
			query=getQuery("GET_EXISTING_DEPOSIT_CBC_LIST");
			logger.info("Query===>" + query);
			if(branchCode!=null && branchCode.length()>0){
				branchCode=branchCode.replaceAll(",", "','");
			}
			branchCode="'"+branchCode+"'";
			if(StringUtils.isNotBlank(bean.getCbcNo()) && ("edit".equals(mode) || "chequeDetail".equals(mode) || "chequeDetail1".equals(mode))){
				query+=" where dcm.CBC_NO=?";
				obj=new Object[]{branchCode1, branchCode, branchCode, bean.getCbcNo()};
			}else
				obj=new Object[]{branchCode1, branchCode, branchCode};
			if(searchField!=null && searchString!=null && searchOper!=null && "view".equals(mode)){
				if("nc".equalsIgnoreCase(searchOper))
					str="not like '%'||upper(?)||'%'";
				else if("cn".equalsIgnoreCase(searchOper))
					str="like '%'||upper(?)||'%'";
				else if("eq".equalsIgnoreCase(searchOper))
					str=" = upper(?)";
				else if("nq".equalsIgnoreCase(searchOper))
					str=" != upper(?)";
				if("cbcNo".equalsIgnoreCase(searchField))
					query+=" where upper(dcm.CBC_NO) "+str;
				else if("brokerName".equalsIgnoreCase(searchField)){
					query+=" where upper((SELECT   DISTINCT (COMPANY_NAME) FROM   BROKER_COMPANY_MASTER WHERE   AGENCY_CODE = dcm.BROKER_ID)) "+str;
				}
				obj=new Object[]{branchCode1, branchCode, branchCode, searchString};
			}
			logger.info("args===>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(query,obj);
			
			if(list!=null && list.size()>0 && ("view".equals(mode) || "chequeDetail".equals(mode) || "chequeDetail1".equals(mode))){
				for(int i=0; i<list.size(); i++){
					Map map=(Map)list.get(i);
					DepositMasterBean dma=new DepositMasterBean();
					dma.setCbcNo(map.get("cbc_no")==null?"":map.get("cbc_no").toString());
					dma.setAgencyCode(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
					dma.setBrokerName(map.get("COMPANY_NAME")==null?"":map.get("COMPANY_NAME").toString());
					dma.setDepositType(map.get("DEPOSIT_TYPE")==null?"":map.get("DEPOSIT_TYPE").toString());
					dma.setProductName(map.get("PRODUCTNAME")==null?"":map.get("PRODUCTNAME").toString());
					dma.setCustomerOpt(map.get("customer_option")==null?"":map.get("customer_option").toString());
					depositList.add(dma);
				}
			}else if(list!=null && list.size()>0 && "edit".equals(mode)){
				Map map=(Map)list.get(0);
				bean.setCbcNo(map.get("cbc_no")==null?"":map.get("cbc_no").toString());
				bean.setDepositNo(map.get("deposit_no")==null?"":map.get("deposit_no").toString());
				bean.setAgencyCode(map.get("BROKER_ID")==null?"":map.get("BROKER_ID").toString());
				bean.setCustomerOpt(map.get("customer_option")==null?"":map.get("customer_option").toString());
				bean.setBrokerName(map.get("COMPANY_NAME")==null?"":map.get("COMPANY_NAME").toString());
				bean.setCustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString().substring(1, map.get("CUSTOMER_ID").toString().length()-1));
				bean.setDepositType(map.get("DEPOSIT_TYPE")==null?"":map.get("DEPOSIT_TYPE").toString());
				bean.setProductName(map.get("PRODUCTNAME")==null?"":map.get("PRODUCTNAME").toString());
				bean.setProductsId((map.get("PRODUCT_ID")==null?"":map.get("PRODUCT_ID").toString()).split(","));
				bean.setOcNo(map.get("OPENCOVER_NO")==null?"":map.get("OPENCOVER_NO").toString().substring(1, map.get("OPENCOVER_NO").toString().length()-1));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Exit===>getCbcList");
		return depositList;
	}
	
	public List<Map<String, Object>> getCustomerList(String branchCode, String agencyCode){
		logger.info("Enter===>getCustomerList");
		List <Map<String,Object>> customerList=null;
    	try{
    		query=getQuery("GET_DEPOSIT_CUSTOMER_LIST");
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{agencyCode};
			logger.info("args===>"+StringUtils.join(obj,","));
			customerList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit===>getCustomerList");
    	return customerList;
	}
	
	public List<Map<String,Object>> getBrokerList(String branchCode){
		logger.info("Enter===>getBrokerList");
		List <Map<String,Object>> brokerList=null;
    	try{
    		query=getQuery("GET_DEPOSIT_BROKER_LIST");
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode};
			logger.info("args===>"+StringUtils.join(obj,","));
			brokerList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit===>getBrokerList");
    	return brokerList;
	}
	
	public List<Map<String,Object>> getOpenCoverList(String customerId, String branchCode){
		logger.info("Enter===>getOpenCoverList");
		List <Map<String,Object>> ocList=null;
    	try{
			logger.info("Query===>" + query);
			
			if(customerId!=null && customerId.length()>0){
				customerId=customerId.replaceAll(",", "','");
			}
			customerId="'"+customerId+"'";
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			query=ltp.findDefaultText("GET_DEPOSIT_OC_LIST", Locale.ENGLISH, new Object[]{customerId});
			Object[] obj=new Object[]{branchCode};
			logger.info("args===>"+StringUtils.join(obj,","));
			ocList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit===>getOpenCoverList");
    	return ocList;
	}
	
	public List<DepositMasterBean>getDepositList(String branchCode, String searchField, String searchString, String searchOper, String mode, DepositMasterBean bean){
		logger.info("Enter===>getDepositList");
		List<DepositMasterBean> depositList=new ArrayList<DepositMasterBean>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		Object[] obj=null;
		String str="like '%'||upper(?)||'%'";
		try{
			
			query=getQuery("GET_EXISTING_DEPOSIT_CHEQUE_LIST");
			logger.info("Query===>" + query);
			
			if("editCheque".equals(bean.getOption())){
				query+=" and DEPOSIT_NO=?";
				obj=new Object[]{bean.getDepositNo()};
			}else if(StringUtils.isNotBlank(bean.getCbcNo())){
				query+=" and CBC_NO=?";
				obj=new Object[]{bean.getCbcNo()};
			}
			if(searchField!=null && searchString!=null && searchOper!=null && "chequeDetail".equals(mode)){
				if("nc".equalsIgnoreCase(searchOper))
					str="not like '%'||upper(?)||'%'";
				else if("cn".equalsIgnoreCase(searchOper))
					str="like '%'||upper(?)||'%'";
				else if("eq".equalsIgnoreCase(searchOper))
					str=" = upper(?)?";
				else if("nq".equalsIgnoreCase(searchOper))
					str=" != upper(?)";
				
				if("depositNo".equalsIgnoreCase(searchField))
					query+=" and upper(DEPOSIT_NO) "+str;
				else if("receiptNo".equalsIgnoreCase(searchField))
					query+=" and upper(RECEIPT_NO) "+str;
				else if("chequeNo".equalsIgnoreCase(searchField))
					query+=" and upper(CHEQUE_NO) "+str;
				
				obj=new Object[]{bean.getCbcNo(), searchString};
			}
			query+=" order by DEPOSIT_NO";
			logger.info("args===>"+StringUtils.join(obj,","));
			list=this.mytemplate.queryForList(query,obj);

			if(list!=null && list.size()>0 && "chequeDetail".equals(mode)){
				for(int i=0; i<list.size(); i++){
					Map map=(Map)list.get(i);
					DepositMasterBean dma=new DepositMasterBean();
					dma.setCbcNo(map.get("cbc_no")==null?"":map.get("cbc_no").toString());
					dma.setDepositNo(map.get("DEPOSIT_NO")==null?"":map.get("DEPOSIT_NO").toString());
					dma.setDepositAmt(map.get("DEPOSIT_AMOUNT")==null?"":map.get("DEPOSIT_AMOUNT").toString());
					dma.setDepositUtilsed(map.get("DEPOSIT_UTILISED")==null?"":map.get("DEPOSIT_UTILISED").toString());
					dma.setChequeNo(map.get("CHEQUE_NO")==null?"":map.get("CHEQUE_NO").toString());
					dma.setChequeAmt(map.get("CHEQUE_AMOUNT")==null?"":map.get("CHEQUE_AMOUNT").toString());
					dma.setChequeBank(map.get("CHEQUE_BANK")==null?"":map.get("CHEQUE_BANK").toString());
					dma.setChequeDate(map.get("CHEQUE_DATE1")==null?"":map.get("CHEQUE_DATE1").toString());
					dma.setReceiptNo(map.get("RECEIPT_NO")==null?"":map.get("RECEIPT_NO").toString());
					dma.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
					dma.setDepositCBCNo(""+map.get("DEPOSIT_NO")==null?"":map.get("DEPOSIT_NO").toString()+"&cbcNo="+dma.getCbcNo());
					depositList.add(dma);
				}
			}else if(list!=null && list.size()>0 && "editCheque".equals(bean.getOption())){
				Map map=(Map)list.get(0);
				bean.setCbcNo(map.get("cbc_no")==null?"":map.get("cbc_no").toString());
				bean.setDepositNo(map.get("DEPOSIT_NO")==null?"":map.get("DEPOSIT_NO").toString());
				bean.setDepositAmt(map.get("DEPOSIT_AMOUNT")==null?"":map.get("DEPOSIT_AMOUNT").toString());
				bean.setDepositUtilsed(map.get("DEPOSIT_UTILISED")==null?"":map.get("DEPOSIT_UTILISED").toString());
				bean.setChequeNo(map.get("CHEQUE_NO")==null?"":map.get("CHEQUE_NO").toString());
				bean.setChequeAmt(map.get("CHEQUE_AMOUNT")==null?"":map.get("CHEQUE_AMOUNT").toString());
				bean.setChequeBank(map.get("CHEQUE_BANK")==null?"":map.get("CHEQUE_BANK").toString());
				bean.setChequeDate(map.get("CHEQUE_DATE")==null?"":map.get("CHEQUE_DATE").toString());
				bean.setReceiptNo(map.get("RECEIPT_NO")==null?"":map.get("RECEIPT_NO").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Exit===>getDepositList");
		return depositList;
	}
	
	public List<Map<String, Object>> getChequeDetails(String depositNo, String branchCode){
		logger.info("Enter===>getChequeDetails");
		List <Map<String, Object>> detailList=null;
    	try{
			query=getQuery("GET_CHEQUE_TRANSACTION_DETAIL");
			Object[] obj=new Object[]{branchCode, branchCode, depositNo};
			logger.info("Query===>" + query);
			logger.info("args===>"+StringUtils.join(obj,","));
			detailList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit===>getChequeDetails");
    	return detailList;
	}
	
	public int saveCBC(DepositMasterBean bean, DepositMasterAction dma){
		logger.info("Enter===>saveCBC");
		int count=0;
		Object[] obj=null;
    	try{
    		String pid = StringUtils.join( bean.getProductsId(), ",");
    		if("new".equals(dma.getMode())){
    			query=getQuery("INS_CBC_DETAIL");
    			obj=new Object[]{bean.getCbcNo(), bean.getAgencyCode(), bean.getCustomerOpt(), ","+bean.getCustomerId()+",", bean.getDepositType(), ","+pid+",", StringUtils.isBlank(bean.getOcNo())?"":","+bean.getOcNo()+","};
    		}else if("edit".equals(dma.getMode())){
    			query=getQuery("UPD_CBC_DETAIL");
    			obj=new Object[]{bean.getAgencyCode(), bean.getCustomerOpt(), ","+bean.getCustomerId()+",", bean.getDepositType(),","+pid+",", StringUtils.isBlank(bean.getOcNo())?"":","+bean.getOcNo()+",", bean.getCbcNo()};
    		}
			logger.info("Query===>" + query);
			logger.info("args===>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
    	}catch (Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit==>saveCBC");
    	return count;
	}
	
	public String getCbcValid(DepositMasterAction dma, DepositMasterBean bean){
		logger.info("Enter===>getCbcValid");
		String cbc=null;
		Object[] obj=null;
    	try{
    		query=getQuery("CHK_CBC_DETAIL_EXIST");
    		obj=new Object[]{bean.getAgencyCode(), bean.getCustomerOpt(), StringUtils.isBlank(bean.getCustomerId())?"":","+bean.getCustomerId()+",", bean.getDepositType(),","+StringUtils.join( bean.getProductsId(), ",")+",", StringUtils.isBlank(bean.getOcNo())?"":","+bean.getOcNo()+","};
    		//CHK_CBC_DETAIL_EXIST=select nvl(CBC_NO,'') from DEPOSIT_CBC_MASTER where BROKER_ID=? and CUSTOMER_OPTION=? and CUSTOMER_ID=? and DEPOSIT_TYPE=? and PRODUCT_ID=? and OPENCOVER_NO=?
			logger.info("Query===>" + query);
			logger.info("args===>"+StringUtils.join(obj,","));
			List cbcList=this.mytemplate.queryForList(query,obj);
			if(cbcList!=null && cbcList.size()>0){
				Map map=(Map)cbcList.get(0);
				cbc=map.get("CBC_NO").toString();
			}else
				cbc="";
    	}catch (Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit==>getCbcValid");
    	return StringUtils.isBlank(cbc)?"":cbc;
	}
	
	public int saveCheque(DepositMasterBean bean, DepositMasterAction dma){
		logger.info("Enter===>saveCheque");
		int count=0;
		Object[] obj=null;
    	try{
    		if("newCheque".equals(bean.getOption())){
    			query=getQuery("INS_NEW_CHEQUE_DETAIL");
    			obj=new Object[]{bean.getCbcNo(), bean.getChequeAmt(), bean.getChequeNo(), bean.getChequeAmt(), bean.getChequeBank(), bean.getChequeDate(), bean.getReceiptNo(),"0"};
    		}else if("editCheque".equals(bean.getOption()) && StringUtils.isNotBlank(bean.getDepositUtilsed()) && Double.parseDouble(bean.getDepositUtilsed())>0){
    			query=getQuery("INS_AMEND_CHEQUE_DETAIL");
    			obj=new Object[]{bean.getCbcNo(), bean.getDepositNo(), bean.getChequeAmt(), bean.getChequeNo(), bean.getChequeAmt(), bean.getChequeBank(), bean.getChequeDate(),  bean.getReceiptNo(), bean.getDepositUtilsed(), bean.getDepositNo()};
    		}else{
    			query=getQuery("UPD_CHEQUE_DETAIL");
    			obj=new Object[]{bean.getChequeAmt(), bean.getChequeNo(), bean.getChequeAmt(), bean.getChequeBank(), bean.getChequeDate(), bean.getReceiptNo(), bean.getDepositNo()};
    		}
			logger.info("Query===>" + query);
			logger.info("args===>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
    	}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit==>saveCheque");
    	return count;
	}
}
