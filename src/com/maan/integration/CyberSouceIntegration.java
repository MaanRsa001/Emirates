package com.maan.integration;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.quotation.service.PremiumService;
 

public class CyberSouceIntegration extends MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(CyberSouceIntegration.class);
	private static final String ALGO = "AES";
	private static final String	UNICODE_FORMAT= "UTF-8";
	private static final String AES_KEY = "1234567812345675";
	private static final String HMAC_SHA256 = "HmacSHA256";
	
	public TreeMap<String,String> createPay(String quoteNo, String branchCode, String productId, String applicationNo, String merchantRefno ) {
		 
		String signature;
		try {
			String sql= getQuery("SELECT_PAYMENTLIST_5");
			List<Map<String, Object>> list = this.mytemplate.queryForList(sql,new String[] {quoteNo,merchantRefno});
			
				if(list.size()>0) {
					sql=getQuery("SELECT_PAYMENTVENDORMASTER");
					List<Map<String, Object>> vendors = this.mytemplate.queryForList(sql);
					Map<String, Object> vendor = vendors.get(0);
					
					TreeMap<String,String> params = new TreeMap<String,String>();		 
					Map<String, Object> map = list.get(0);
					params.put("access_key", vendor.get("API_KEY").toString());
					params.put("profile_id", vendor.get("PROFILE_ID").toString());
					params.put("bill_to_address_line1", map.get("BILL_TO_ADDRESS_LINE1").toString());
					params.put("bill_to_address_city", map.get("BILL_TO_ADDRESS_CITY").toString() );
					params.put("bill_to_address_state", map.get("BILL_TO_ADDRESS_CITY").toString() );
					params.put("bill_to_address_country", map.get("BILL_TO_ADDRESS_COUNTRY").toString() );
					params.put("bill_to_email",  map.get("BILL_TO_EMAIL").toString()); 
					params.put("bill_to_forename", map.get("BILL_TO_FORENAME").toString() );
					params.put("bill_to_surname",map.get("BILL_TO_SURNAME")==null?" ":map.get("BILL_TO_SURNAME").toString());
					params.put("transaction_uuid", map.get("MERCHANT_REFERENCE").toString());
					params.put("signed_field_names", vendor.get("SIGNED_FIELDS").toString());
					params.put("unsigned_field_names",vendor.get("UNSIGNED_FIELDS")==null?"":vendor.get("UNSIGNED_FIELDS").toString());
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					format.setTimeZone(TimeZone.getTimeZone("UTC"));
					params.put("signed_date_time", format.format(new Date())); //2025-07-03T11:18:03Z
					params.put("locale", "en");
					params.put("transaction_type", "sale");
					params.put("reference_number", map.get("MERCHANT_REFERENCE").toString());
					params.put("amount", map.get("PREMIUM").toString());
					params.put("currency", map.get("RES_REQ_CURRENCY").toString());
					params.put("bill_to_address_postal_code",  map.get("BILL_TO_ADDRESS_POSTAL_CODE").toString());
			    	  
					
					params.put("override_custom_receipt_page", vendor.get("RETURN_URL_LINK").toString().replaceAll("<QuoteNo>", quoteNo) );//URLEncoder.encode(, StandardCharsets.UTF_8.toString()) );	
					params.put("override_custom_cancel_page", vendor.get("CANCEL_URL_LINK").toString().replaceAll("<QuoteNo>", quoteNo));
					//params.put("notificationUrl", vendor.get("WEBHOOK_URL_LINK").toString());
					signature = peachGenerateSignature(params, vendor.get("API_SECRET_KEY").toString());
					params.put("signature", signature);
					return params;					
				}			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
		public String decrypt(String encryptedData) throws Exception {
			SecretKeySpec keySpec = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8),ALGO);
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			return new String(decValue, StandardCharsets.UTF_8);
		}
		
		private String peachGenerateSignature(Map<String,String> params,String secretKey) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
	        return sign(buildDataToSign(params), secretKey);
	    }
		
		private String sign(String data, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
	        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
	        Mac mac = Mac.getInstance(HMAC_SHA256);
	        mac.init(secretKeySpec);
	        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
	        return DatatypeConverter.printBase64Binary(rawHmac).replace("\n", "");
	    }
		
		private String buildDataToSign(Map<String,String> params) {
	        String[] signedFieldNames = String.valueOf(params.get("signed_field_names")).split(",");
	        ArrayList<String> dataToSign = new ArrayList<String>();
	        for (String signedFieldName : signedFieldNames) {
	            dataToSign.add(signedFieldName + "=" + String.valueOf(params.get(signedFieldName)));
	        }
	        return commaSeparate(dataToSign);
	    }
		
		 private String commaSeparate(ArrayList<String> dataToSign) {
		        StringBuilder csv = new StringBuilder();
		        for (Iterator<String> it = dataToSign.iterator(); it.hasNext(); ) {
		            csv.append(it.next());
		            if (it.hasNext()) {
		                csv.append(",");
		            }
		        }
		        return csv.toString();
		    }

		public Map<String,String> checkStatus(Map<String, String> params) {
			Map<String,String> resultRet=new HashMap<String,String>(); 
			boolean containsKey = params.containsKey("req_transaction_uuid");
			
			if(containsKey) {
				

				String sql_2= getQuery("SELECT_PAYMENTLIST_4"); 
				String merchantReference = params.get("req_transaction_uuid");
				
				List<Map<String, Object>> list = this.mytemplate.queryForList(sql_2,new String[] {merchantReference});
				if(!list.isEmpty()) {
					Map<String, Object> paymentDetail = list.get(0);
				/*	premiumAction.setRefNo(paymentDetail.get("APPLICATION_NO").toString());
					premiumAction.setApplicationNo(paymentDetail.get("APPLICATION_NO").toString());
					*/
					String applicationNo=paymentDetail.get("APPLICATION_NO").toString();
					String quoteNo=paymentDetail.get("QUOTE_NO").toString();
					
					resultRet.put("applicationNo", applicationNo);
					resultRet.put("quoteNo", quoteNo);
					resultRet.put("merchantRefno", merchantReference);
					String sql=getQuery("SELECT_PAYMENTVENDORMASTER");
					List<Map<String, Object>> vendors = this.mytemplate.queryForList(sql);
					Map<String, Object> vendor = vendors.get(0);				
					
					Boolean isvalid=checkSignature(params,vendor);
					String responseMessage=isvalid?null:"Mismatch Signature";
					String responseStatus="PENDING";
					//Boolean checkStatus = onlineCheckStatus(merchantReference,vendor);
					String updateQuery="";
					String args[]=null;
					
					String auth_trans_ref_no=params.getOrDefault("auth_trans_ref_no","0");
					String auth_amount=params.getOrDefault("auth_amount","0");
					String auth_time=params.getOrDefault("auth_time","0");
					String transaction_id=params.getOrDefault("transaction_id","0");
					String auth_code=params.getOrDefault("auth_code","0");
					String req_card_number=params.getOrDefault("req_card_number","0");
					String payer_authentication_reason_code=params.getOrDefault("payer_authentication_reason_code","0");
					String decision=params.getOrDefault("decision","0");
					String decision_reason_code=params.getOrDefault("decision_reason_code","0");
					
					if(isvalid && params.get("decision").equals("ACCEPT") && params.get("decision_reason_code").equals("100")) {
						String paymentCheck=getQuery("SELECT_PAYMENTCHK");
						 List<Map<String, Object>> payment = this.mytemplate.queryForList(paymentCheck,new String[] {applicationNo});
						 if( new BigDecimal( payment.get(0).get("TOTAL_PREMIUM").toString()).compareTo(new BigDecimal(auth_amount))==0 ) {
							responseStatus="SUCCESS";
							responseMessage=params.get("message");
						}else {
							responseStatus="FAILED";
							responseMessage="Amount Missmatch";
						}
						

						
						
								
					}else if(isvalid && params.get("decision").equals("CANCEL")) {
						responseStatus="FAILED";
						responseMessage=params.get("message");
					 
					} 
					updateQuery=getQuery("UPD_PAYMENT_LIST_5");
					args=new String[]{responseMessage,responseStatus,auth_trans_ref_no , auth_amount,auth_time,transaction_id,auth_code,req_card_number
							,decision_reason_code,decision,auth_trans_ref_no,merchantReference};
					System.out.println(updateQuery);
					System.out.println(StringUtils.join(args));
					int update = this.mytemplate.update(updateQuery, args);
					
					if("ACCEPT".equalsIgnoreCase(params.get("decision") ) && params.get("decision_reason_code").equals("100") && "SUCCESS".equals(responseStatus)) {
						updateQuery= getQuery("UPD_PAYMENT_LIST_2"); 
						args=new String[]{quoteNo};
						update = this.mytemplate.update(updateQuery, args);
						updateQuery=getQuery("UPD_PAYMENT_LIST_6"); 
						args=new String[]{"EXPIRED",merchantReference,quoteNo};
						update = this.mytemplate.update(updateQuery, args);
						
						PremiumService service=new PremiumService();
						String result=service.policyGeneration( applicationNo);
						
						updateQuery="select * from WEBSERVICE_MARINE_QUOTE where QUOTE_NO=?";
						args=new String[]{quoteNo};
						 List<Map<String, Object>> quoteInfo = this.mytemplate.queryForList(updateQuery, args);
						service.sentPolicyMail(paymentDetail,result,quoteInfo.get(0));
						resultRet.put("result", result);
					}
				}
			}
			return resultRet;
		}
		private Boolean onlineCheckStatus(String merchantReferenceNo, Map<String, Object> vendor) {
			try {

				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				format.setTimeZone(TimeZone.getTimeZone("UTC"));
				String signDate=format.format(new Date()); //2025-07-03T11:18:03Z

				Map<String,String> params=new HashMap<String, String>();
				params.put("signed_field_names","v-c-date,v-c-merchant-id");
				params.put("v-c-date", signDate);
				params.put("v-c-merchant-id", merchantReferenceNo);
				String signature = peachGenerateSignature(params, vendor.get("API_SECRET_KEY").toString());
				System.out.println(signature);
				params.put("signature", signature);
				String url =vendor.get("CHECK_STATUS_URL").toString().replaceAll("<id>", merchantReferenceNo);

				HttpHeaders headers = new HttpHeaders();
				headers.setAll(params); 
				HttpEntity<String> entity = new HttpEntity<>(headers);

				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> response = restTemplate.exchange(
						url,
						HttpMethod.GET,
						entity,
						String.class
						);

				System.out.println(response.getBody());

			}catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		private Boolean checkSignature(Map<String, String> params, Map<String, Object> vendor) {
			String gsignature="";
			try {
				gsignature = peachGenerateSignature(params, vendor.get("API_SECRET_KEY").toString());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return gsignature.equals(params.get("signature"));
		}

		public Map<String, String> createPay(String merchantRefno) {
			 
			String signature;
			try {
				String sql= getQuery("SELECT_PAYMENTLIST_6");
				List<Map<String, Object>> list = this.mytemplate.queryForList(sql,new String[] {merchantRefno});
				
					if(list.size()>0) {
						sql=getQuery("SELECT_PAYMENTVENDORMASTER");
						List<Map<String, Object>> vendors = this.mytemplate.queryForList(sql);
						Map<String, Object> vendor = vendors.get(0);
						
						TreeMap<String,String> params = new TreeMap<String,String>();		 
						Map<String, Object> map = list.get(0);
						String quoteNo=map.get("QUOTE_NO").toString();
						params.put("access_key", vendor.get("API_KEY").toString());
						params.put("profile_id", vendor.get("PROFILE_ID").toString());
						params.put("bill_to_address_line1", map.get("BILL_TO_ADDRESS_LINE1").toString());
						params.put("bill_to_address_city", map.get("BILL_TO_ADDRESS_CITY").toString() );
						params.put("bill_to_address_state", map.get("BILL_TO_ADDRESS_CITY").toString() );

						params.put("bill_to_address_country", map.get("BILL_TO_ADDRESS_COUNTRY").toString() );
						params.put("bill_to_email",  map.get("BILL_TO_EMAIL").toString()); 
						params.put("bill_to_forename", map.get("BILL_TO_FORENAME").toString() );
						params.put("bill_to_surname",map.get("BILL_TO_SURNAME")==null?" ":map.get("BILL_TO_SURNAME").toString());
						params.put("transaction_uuid", map.get("MERCHANT_REFERENCE").toString());
						params.put("signed_field_names", vendor.get("SIGNED_FIELDS").toString());
						params.put("unsigned_field_names",vendor.get("UNSIGNED_FIELDS")==null?"":vendor.get("UNSIGNED_FIELDS").toString());
						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
						format.setTimeZone(TimeZone.getTimeZone("UTC"));
						params.put("signed_date_time", format.format(new Date())); //2025-07-03T11:18:03Z
						params.put("locale", "en");
						params.put("transaction_type", "sale");
						params.put("reference_number", map.get("MERCHANT_REFERENCE").toString());
						params.put("amount", map.get("PREMIUM").toString());
						params.put("currency", map.get("RES_REQ_CURRENCY").toString());
						params.put("bill_to_address_postal_code",  map.get("BILL_TO_ADDRESS_POSTAL_CODE").toString());
				    	  
						
						params.put("override_custom_receipt_page", vendor.get("RETURN_URL_LINK").toString().replaceAll("<QuoteNo>", quoteNo) );//URLEncoder.encode(, StandardCharsets.UTF_8.toString()) );	
						params.put("override_custom_cancel_page", vendor.get("CANCEL_URL_LINK").toString().replaceAll("<QuoteNo>", quoteNo));
						//params.put("notificationUrl", vendor.get("WEBHOOK_URL_LINK").toString());
						signature = peachGenerateSignature(params, vendor.get("API_SECRET_KEY").toString());
						params.put("signature", signature);
						return params;					
					}			 
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@SuppressWarnings("deprecation")
		public Boolean checkPayment(String quoteNo) {
			try {
				String sql= "select count(*) from payment_detail where  RESPONSE_STATUS='SUCCESS' and QUOTE_NO=?";
				 Long count = this.mytemplate.queryForObject(sql,new String[] {quoteNo},Long.class);
				return count>0;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		public List<Map<String, Object>> getPaymentList(String policyStartDate, String policyEndDate, String loginId) {
			try {
				String query=getQuery("PAYMENT_REPORT_LIST");
				String[] args=new String[2];
				args[0]=policyStartDate+" 00:00:00";
				args[1]=policyEndDate+" 23:59:59";
				if(!"ALL".equals(loginId)) {
					query+=" and upper(login_id)=upper('"+loginId+"') ";
				}
				query+=" order by request_time desc";
				
				
				List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
				result=this.mytemplate.queryForList(query,args);
				return result;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

}

	
