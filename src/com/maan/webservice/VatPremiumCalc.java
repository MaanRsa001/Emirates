package com.maan.webservice;

import com.maan.quotation.dao.QuotationDAO;
import com.maan.webservice.dao.VatPremiumDAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
public class VatPremiumCalc {
	VatPremiumDAO dao=new VatPremiumDAO();
	public String vatApplicableYn(String orgCountry,String destCountry,String policyType){
		String result="N";
		try {
			
			String countryId="E".equalsIgnoreCase(policyType)?destCountry:orgCountry;
			
			if("C".equalsIgnoreCase(policyType)){
				result="N";
			}else if("L".equalsIgnoreCase(policyType)){
				result="Y";
			}else if(dao.getVatApplicableForGCC(countryId)){
				result="Y";
			}else{
				result="N";
			}
		} catch (Exception e) {
			
		}
		return result;
		
	}
	public void calculateVatTax(String quoteNo,String productId,String orgCountry,String destCountry,String branchCode,String premium,String commision, String vatApplicableYN, String policyStartDt){
		
		String type="";
		String typeString="";
		String vatApplicable="";
		String vatPercentage="";
		String vatBroPercentage="0";
		double vatInputAmt=0.0;
		double vatOutputAmt=0.0;
		try {
			if("1".equals(orgCountry)&& "1".equals(destCountry)){
				type="L";
				typeString="In Land";
			}else if("1".equals(orgCountry)&& !"1".equals(destCountry)){
				type="E";
				typeString="Export";
			}else if(!"1".equals(orgCountry)&& "1".equals(destCountry)){
				type="I";
				typeString="Import";
			}else{
				type="C";
				typeString="Cross Voyage";
			}
			if("Y".equalsIgnoreCase(vatApplicableYN)||"".equalsIgnoreCase(vatApplicableYN)){
				vatApplicable=vatApplicableYn(orgCountry, destCountry, type);
			}else{
				vatApplicable="N";
			}
			
			
			if("Y".equalsIgnoreCase(vatApplicable)){
				vatPercentage=dao.getVatPercentage(policyStartDt);
				vatBroPercentage=vatPercentage;
				
			}else{
				vatPercentage="0"; 
				if("C".equalsIgnoreCase(type)){
					vatBroPercentage=dao.getVatPercentage(policyStartDt);
				}
			}
			
			
			
			vatInputAmt=(Double.parseDouble((StringUtils.isBlank(premium))?"0":premium) * Double.parseDouble(vatPercentage))/100;
			
			vatOutputAmt=(Double.parseDouble((StringUtils.isBlank(commision))?"0":commision) * Double.parseDouble(vatBroPercentage) )/100;
			
			insertVatInfo(quoteNo, productId, vatInputAmt, vatOutputAmt, typeString,vatPercentage,vatBroPercentage);
			String totalPremium=String.valueOf(Double.parseDouble(premium)+vatInputAmt);
			updateMarinedata(quoteNo, vatInputAmt, vatOutputAmt,vatPercentage,totalPremium);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMarinedata(String quoteNo, double vatInputAmt,double vatOutputAmt,String vatPercentage, String premium){
		try {
			dao.insertVatInfo("UPDATE_VAT_MD",  new Object[]{vatPercentage,vatInputAmt,vatOutputAmt,quoteNo});
			dao.insertVatInfo("UPDATE_VAT_PM",  new Object[]{premium,quoteNo});
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void insertVatInfo(String quoteNo,String productId,double vatInputAmt,double vatOutputAmt,String policyType,String vatPercentage, String vatBroPercentage){
	
		try {
			Map<String,Object>getVatInfo =new HashMap<String, Object>();
			getVatInfo=dao.getResultMap("GET_VAT_INFO", new String[]{quoteNo});
			dao.insertVatInfo("DEL_VAT_INFO",  new Object[]{quoteNo});
			
			boolean  isRefund=vatOutputAmt<0 || vatInputAmt<0 ;
			Object[] obj =new Object[11];

			obj[0]=quoteNo;//getVatInfo.get("")
			obj[1]=productId;
			obj[2]=getVatInfo.get("MISSIPPI_CUSTOMER_CODE")==null?"":getVatInfo.get("MISSIPPI_CUSTOMER_CODE").toString();
			obj[3]=getVatInfo.get("CUST_VAT_REG_NO")==null?"":getVatInfo.get("CUST_VAT_REG_NO").toString();
			obj[4]=vatPercentage;//getVatInfo.get("") 
			obj[5]=vatInputAmt;
			obj[6]=policyType;
			obj[7]=getVatInfo.get("ORG_COUNTRY")==null?"":getVatInfo.get("ORG_COUNTRY").toString();
			obj[8]=getVatInfo.get("DEST_COUNTRY")==null?"":getVatInfo.get("DEST_COUNTRY").toString();
			obj[9]=isRefund?"IP":"OP";
			obj[10]="C";
			//Insert vat input data
			dao.insertVatInfo("INSERT_VAT_INFO", obj);
			String bdm=getVatInfo.get("AC_EXECUTIVE_ID")==null?"":getVatInfo.get("AC_EXECUTIVE_ID").toString();
			if("5".equalsIgnoreCase(bdm)){
				obj[2]=getVatInfo.get("RSA_BROKER_CODE")==null?"":getVatInfo.get("RSA_BROKER_CODE").toString();
				obj[3]=getVatInfo.get("BROKER_VAT_REG_NO")==null?"":getVatInfo.get("BROKER_VAT_REG_NO").toString();
			}else{
				obj[2]=getVatInfo.get("BDM_CODE")==null?"":getVatInfo.get("BDM_CODE").toString();
				obj[3]=getVatInfo.get("BDM_VAT_REG_NO")==null?"":getVatInfo.get("BDM_VAT_REG_NO").toString();
			}
			obj[4]=vatBroPercentage;
			obj[5]=vatOutputAmt;
			obj[9]=isRefund?"OP":"IP";
			obj[10]="B";
			//Insert vat output  data
			dao.insertVatInfo("INSERT_VAT_INFO", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void insertVatServiceInfo(String quoteNo, String branchCode,String productId){
		
		
		
	}
	public void updateMarineVatinfo(String quoteNo, String branchCode,String productId) {
		try {
			Map<String,Object>getVatInfo =new HashMap<String, Object>();
			getVatInfo=dao.getResultMap("GET_VAT_INFO", new String[]{quoteNo});
			String bdm=getVatInfo.get("AC_EXECUTIVE_ID")==null?"":getVatInfo.get("AC_EXECUTIVE_ID").toString();
			String orgCountry=getVatInfo.get("TRANSIT_FROM_COUNTRY_ID")==null?"":getVatInfo.get("TRANSIT_FROM_COUNTRY_ID").toString();
			String destCountry=getVatInfo.get("FINAL_DESTINATION_COUNTRY_ID")==null?"":getVatInfo.get("FINAL_DESTINATION_COUNTRY_ID").toString();
			String totalPremium=getVatInfo.get("NET_PREMIUM")==null?"":getVatInfo.get("NET_PREMIUM").toString();
			String commission=getVatInfo.get("COMMISSION")==null?"":getVatInfo.get("COMMISSION").toString();
			String vatApplicable=getVatInfo.get("VAT_APPLICABLE")==null?"":getVatInfo.get("VAT_APPLICABLE").toString();
			String policyStartDt=getVatInfo.get("POL_START_DATE")==null?"":getVatInfo.get("POL_START_DATE").toString();
			/*String loginId=getVatInfo.get("MISSIPPI_OPENCOVER_NO")==null?"":getVatInfo.get("MISSIPPI_OPENCOVER_NO").toString();
			String openCoverNo=getVatInfo.get("LOGIN_ID")==null?"":getVatInfo.get("LOGIN_ID").toString();
			String issuer=getVatInfo.get("APPLICATION_ID")==null?"":getVatInfo.get("APPLICATION_ID").toString();
			String applicationNo=getVatInfo.get("APPLICATION_NO")==null?"":getVatInfo.get("APPLICATION_NO").toString();
			
			if(StringUtils.isEmpty(commission)){
				//commission=getCommissionPercent(info.getLoginId(), info.getProductId(), info.getOpenCoverPolicyNo(),info.getIssuer());
				commission = getCommissionPercent(loginId, productId, openCoverNo,issuer,applicationNo,branchCode);
			}
			*/
			if(!"5".equalsIgnoreCase(bdm)){
				commission="0";
			}
			calculateVatTax(quoteNo, productId, orgCountry, destCountry, branchCode, totalPremium, commission,vatApplicable,policyStartDt);

		} catch (Exception e) {

		}


	}
	private String getCommissionPercent(String loginId, String productId,
			String openCoverNo, String issuer, String applicationNo,
			String branchCode) {
		String  commission="";
		String isEndt = dao.getValue("GET_ENDT_COMMISSION_PERCENT", new String[]{applicationNo});
		if(Integer.parseInt(isEndt)>0) {
			commission = dao.getValue("GET_ENDT_COMMISSION_PERCENT", new String[]{applicationNo});
		}
		else {
			String directStatus = new QuotationDAO().getDirectStatus(applicationNo,branchCode);
			String executiveId = dao.getValue("GET_EXECUTIVE_ID", new String[]{applicationNo});
			if("3".equals(productId)){
				if(!"Y".equalsIgnoreCase(directStatus)) {
					commission=dao.getValue("GET_COMMISSION_PERCENT", new String[]{loginId, productId, loginId});
				}
				else if("Y".equalsIgnoreCase(directStatus) && !"5".equals(executiveId)) {
					commission = dao.getValue("GET_EXECUTIVE_PERCENT",new String[]{executiveId});
				}
			} else if("11".equals(productId)){
				if(!"Y".equalsIgnoreCase(directStatus)) {
					commission=dao.getValue("GET_OC_COMMISSION_PERCENT", new String[]{openCoverNo});
				}
				else if("Y".equalsIgnoreCase(directStatus) && !"5".equals(executiveId)) {
					commission = dao.getValue("GET_OPENCOVER_EXECUTIVE_PERCENT",new String[]{openCoverNo,openCoverNo});
				}
			}
		}
		return commission;
	}
	
}


