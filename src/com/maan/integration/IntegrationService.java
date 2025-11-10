package com.maan.integration;

import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.SOAPElement;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.logging.log4j.Logger;

import com.iii.premia.maansarovar.services.common.CustomerDataRequest;
import com.iii.premia.maansarovar.services.common.CustomerDataRequestInfo;
import com.iii.premia.maansarovar.services.common.CustomerDataResponseInfo;
import com.iii.premia.maansarovar.services.common.MaansarovarServicePortBindingStub;
import com.iii.premia.maansarovar.services.common.MaansarovarServicesServiceLocator;
import com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessExceptionBean;
import com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqServicePortBindingStub;
import com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessRequest;
import com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessRequestInfo;
import com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessResponseInfo;
import com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessServiceLocator;
import com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateReqServicePortBindingStub;
import com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateRequest;
import com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateRequestInfo;
import com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateResponseInfo;
import com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateServiceLocator;
import com.iii.premia.maansarovar.services.marinedirect.MarineDirectReqServicePortBindingStub;
import com.iii.premia.maansarovar.services.marinedirect.MarineDirectRequest;
import com.iii.premia.maansarovar.services.marinedirect.MarineDirectRequestInfo;
import com.iii.premia.maansarovar.services.marinedirect.MarineDirectResponseInfo;
import com.iii.premia.maansarovar.services.marinedirect.MarineDirectServiceLocator;
import com.iii.premia.maansarovar.services.marinedirect.WSExceptionBean;
import com.maan.common.LogUtil;


public class IntegrationService {
	private static final Logger logger = LogUtil.getLogger(IntegrationService.class);

	private static String UserName="maansarovar1";
	private static String Password="maansarovar1";
	private static String heading;
	private IntegrationDAO dao=new IntegrationDAOImpl(); 
	private final String properties="IntegrationXML"; 
	private ResourceBundle bundle=null;

	public IntegrationService(){
		try{
			bundle = ResourceBundle.getBundle(properties);
			heading=getXMLString("INTG_HEADER");
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private String getXML(String key) {
		String query=bundle.getString(key);
		return query;
	} 
	private String getXMLString(String key, Object... params) {
		return MessageFormat.format(getXML(key),params);
	}
	public String merineCusTag(String policyNo) {
		List<Map<String, Object>> records=dao.getUserInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("CUSTOMER",records.get(i));
			input+=getXMLString("MARINE_CUST_REQ_XML", params);
		}
		return input;
	}
	/** Marine Direct Request Service Start**/

	private String marineDirectPolicy(String policyNo){	
		List<Map<String, Object>> records=dao.getPolicyTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("POLICY",records.get(i));
			input+=getXMLString("MARINE_DIRECT_POLICY", params);
		}
		return input;
	}
	private String marineDirectRisk(String policyNo){
		List<Map<String, Object>> records=dao.getRiskTag(policyNo);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("RISK",records.get(i));
			input=getXMLString("MARINE_DIRECT_RISK", params);
		}

		return input;
	}
	private String marineDirectRiskVessels(String policyNo){
		List<Map<String, Object>> records=dao.getRiskVesselTag(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("VESSEL",records.get(i));
			input+=getXMLString("MARINE_DIRECT_RISK_VESSEL", params);
		}
		return input;
	}

	private String marineDirecRiskCoverInfo(String policyNo){
		List<Map<String, Object>> records=dao.getRiskCoverTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("RISKCOVER",records.get(i));
			params[0]=i+1;
			input+=getXMLString("MARINE_DIRECT_RISK_COVER", params);
		}
		return input;
	}
	private String marineDirectRiskDedtInfo(String policyNo){
		List<Map<String, Object>> records=dao.getRiskDedtTag(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("RISKDEDUCTIBLE",records.get(i));
			input+=getXMLString("MARINE_DIRECT_RISK_DEDUCTIBLE", params);
		}
		return input;
	}
	private String marineDirectRiskDisInfo(String policyNo){
		List<Map<String, Object>> records=dao.getRiskDisTag(policyNo);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("RISKDIS",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_DIRECT_RISK_DISCOUNTINFO", params);

		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("RISKDIS",new HashMap<String, Object>());
		}*/


		return input;
	}
	private String marineDirectRiskLoadInfo(String policyNo){
		List<Map<String, Object>> records=dao.getRiskLoadTag(policyNo);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("RISKLOAD",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_DIRECT_RISK_LOADINGINFO", params);
		}
		/*	if(records.size()<=0)
		{
			params=dao.constantList("RISKLOAD",new HashMap<String, Object>());

		}*/

		return input;
	} 
	private String marineDirecDeductibleInfo(String policyNo){
		List<Map<String, Object>> records=dao.getDeductibleTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("DEDUCTIBLEINFO",records.get(i));
			params[0]=i+1;
			input+=getXMLString("MARINE_DIRECT_DEDUCTIBLE", params);
		}
		return input;
	}
	private String marineDirectDisCountInfo(String policyNo){
		List<Map<String, Object>> records=dao.getDiscountTagInfo(policyNo);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("DISCOUNTINFO",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_DIRECT_DISCOUNTINFO", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("DISCOUNTINFO",new HashMap<String, Object>());

+
		}*/

		return input;
	}
	private String marineDirectLoadingInfo(String policyNo){
		List<Map<String, Object>> records=dao.getLoadingTagInfo(policyNo);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("LOADINGINFO",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_DIRECT_LOADINGINFO", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("LOADINGINFO",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineDirecRiskSmiInfo(String policyNo){
		List<Map<String, Object>> records=dao.getRiskSmiTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("SMIINFO",records.get(i));
			params[0]=i+1;
			input+=getXMLString("MARINE_DIRECT_RISK_SMIINFO", params);
		}

		return input;
	}
	private String marineDirectConditionInfo(String policyNo){
		List<Map<String, Object>> records=dao.getConditionTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("CONDITIONINFO", records.get(i));
			input+=getXMLString("MARINE_DIRECT_COND", params);
		}

		return input;
	}
	private String marineDirectChargesInfo(String policyNo){
		List<Map<String, Object>> records=dao.getChargesTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("CHARGESINFO",records.get(i));
			params[0]=i+1;
			input+=getXMLString("MARINE_DIRECT_CHARGESINFO", params);
		}
		return input;
	}
	private String marineDirectBrokersInfo(String policyNo){
		List<Map<String, Object>> records=dao.getBrokersTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("BROKERCOMM",records.get(i));
			input+=getXMLString("MARINE_DIRECT_BROKERCOM", params);
		}
		return input;
	}
	private String marineDirectFacsharedtl(String policyNo){
		List<Map<String, Object>> records=dao.getMarDirFacsharedtlTag(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("FACSHAREDTL",records.get(i));
			input+=getXMLString("MARINE_DIRECT_FACSHAREDTL", params);
		}
		return input;
	}
	private String marineDirectFacparDtls(String policyNo){
		List<Map<String, Object>> records=dao.getMarDirFacparDtlsTag(policyNo);

		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("FACPARTDTLS",records.get(i));
			input+=getXMLString("MARINE_DIRECT_FACPARTDTLS", params);
		}
		return input;
	}
	private String marineDirectFacpartComm(String policyNo){
		List<Map<String, Object>> records=dao.getMarDirFacpartCommTag(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("FACPARTCOMM",records.get(i));
			input+=getXMLString("MARINE_DIRECT_FACPARTCOMM", params);
		}
		return input;
	}
	private String marineAccInfo(String policyNo){
		List<Map<String, Object>> records=dao.getAccountTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("ACCOUNTSINFO", records.get(i));
			params[0]=i+1;
			input+=getXMLString("MARINE_DIRECT_ACCINFO", params);
		}
		return input;
	}
	
	private String marineVatInfo(String policyNo){
		List<Map<String, Object>> records=dao.getVatTagInfo(policyNo);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("VATINFO", records.get(i));
			input+=getXMLString("MARINE_DIRECT_VATINFO", params);
		}
		return input;
	}
	
	private String marineEndtVatInfo(String policyNo,String tranId){
		List<Map<String, Object>> records=dao.getEndtVatTagInfo(policyNo, tranId);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("VATINFO", records.get(i));
			input+=getXMLString("MARINE_DIRECT_VATINFO", params);
		}
		return input;
	}
	private void updateStatus(String policyNo,String status, String msg,String res){
		dao.updateStatus(policyNo,status, msg,res);
	}
	private void updateXmlRequest(String policyNo,String xml, String type,String policyType){
		dao.updateXmlRequest(policyNo,xml, type,policyType);
	}
	public MarineDirectResponseInfo marineDirectService(String policyNo){
		MarineDirectResponseInfo responseInfo = new MarineDirectResponseInfo();
		try{
			if(isLinkUp(getXMLString("WS_URL_DIRECT_SERVICE"))){
				MarineDirectServiceLocator service = new MarineDirectServiceLocator();
				MarineDirectReqServicePortBindingStub clientStub = new MarineDirectReqServicePortBindingStub(new URL(getXMLString("WS_URL_DIRECT_SERVICE")),service);
				SOAPHeaderElement soapHeader = new SOAPHeaderElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
				soapHeader.setPrefix("wsse");
				soapHeader.setMustUnderstand(true);
				SOAPElement oElement1 = soapHeader.addChildElement("UsernameToken");
				SOAPElement oElement = oElement1.addChildElement("Username");
				oElement.addTextNode(UserName);
				oElement = oElement1.addChildElement("Password");
				oElement.addTextNode(Password);
				clientStub.setHeader(soapHeader);
				clientStub.setUsername(UserName);
				clientStub.setPassword(Password);
				MarineDirectRequestInfo requestInfo = getMarineDirectRequest(policyNo);
				responseInfo=clientStub.marineDirectRequest(requestInfo);
				updateStatus(policyNo,responseInfo.getStatus(), responseInfo.getStatusMessage(),responseInfo.getResponsibility());
				System.out.println(responseInfo.getOutPut());
				System.out.println("Status: "+responseInfo.getStatus());
				System.out.println("StatusMessage: "+responseInfo.getStatusMessage());
				System.out.println("Responsibility: "+responseInfo.getResponsibility());
				//	System.out.println(responseInfo);
				String Responsibility=responseInfo.getResponsibility()==null?"":responseInfo.getResponsibility();
				String input="<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body> <ns2:MarineDirectRequestResponse xmlns:ns2=\"http://marinedirect.services.maansarovar.premia.iii.com/\"><MarineDirectInformation><Status>"+responseInfo.getStatus()+"</Status><StatusMessage>"+responseInfo.getStatusMessage()+"</StatusMessage><Responsibility>"+Responsibility+"</Responsibility></MarineDirectInformation></ns2:MarineDirectRequestResponse></S:Body></S:Envelope>";
				updateXmlRequest(policyNo,input,"Response","");
			}else{
				 getMarineDirectRequest(policyNo);
				 responseInfo.setStatus("FAILD");
				 responseInfo.setStatusMessage("DIRECT SERVICE DOWN");
				updateStatus(policyNo,"FAILD", "DIRECT SERVICE DOWN","3I");
			}
			//System.out.println(responseInfo.getStatusMessage());
		}catch (WSExceptionBean ex) {
			
			System.out.println(ex.getErrorDetails().getErrorDescription() + ex.getErrorDetails().getErrorCode());
			String exMsg=ex.getErrorDetails().getErrorDescription()==null?"": ex.getErrorDetails().getErrorDescription()
					+ ex.getErrorDetails().getErrorCode()==null?"":"and error code "+ ex.getErrorDetails().getErrorCode() ;
			 responseInfo.setStatus("FAILD");
			 responseInfo.setStatusMessage(exMsg);
			updateStatus(policyNo,"Error", exMsg,"3I");
		}
		catch(Exception e){
			 responseInfo.setStatus("FAILD");
			 responseInfo.setStatusMessage("Exception Happened. Please contact System Administrator.");
			updateStatus(policyNo,"Error", "Exception Happened. Please contact System Administrator.","MAANSAROVAR");
			e.printStackTrace();
		}
		return responseInfo;
	}

	public MarineDirectRequestInfo getMarineDirectRequest(String policyNo){
		MarineDirectRequestInfo marineDirectReq = new MarineDirectRequestInfo();
		try{
			MarineDirectRequest directReq = new MarineDirectRequest();
			Object param[]=new Object[20];
			param[0]=merineCusTag(policyNo);
			param[1]=marineDirectPolicy(policyNo);
			param[2]=marineDirectRisk(policyNo);
			param[3]=marineDirectRiskVessels(policyNo);
			param[4]=marineDirecRiskSmiInfo(policyNo);
			param[5]=marineDirecRiskCoverInfo(policyNo);
			param[6]=marineDirectRiskDedtInfo(policyNo);
			//param[6]="";
			param[7]=marineDirectRiskDisInfo(policyNo);
			param[8]=marineDirectRiskLoadInfo(policyNo);
			param[9]=marineDirecDeductibleInfo(policyNo);
			//param[9]="";
			param[10]=marineDirectDisCountInfo(policyNo);
			param[11]=marineDirectLoadingInfo(policyNo);
			param[12]=marineDirectConditionInfo(policyNo);
			param[13]=marineDirectChargesInfo(policyNo);
			param[14]=marineDirectBrokersInfo(policyNo);
			param[15]=marineDirectFacsharedtl(policyNo);
			param[16]=marineDirectFacparDtls(policyNo);
			param[17]=marineDirectFacpartComm(policyNo);
			param[18]=marineVatInfo(policyNo);
			param[19]=marineAccInfo(policyNo);
			String input=getXMLString("MARINE_DIRECT_OVERALL_TAG", param);
			input=heading + replceSplChar(input);
			logger.info("Request XML String => "+input);
			updateXmlRequest(policyNo,input,"Request","DIRECTPOLICY");
			directReq.setInput(input);
			marineDirectReq.setWSInformation(directReq);
		}catch(Exception e){
			e.printStackTrace();
		}
		return marineDirectReq;
	}
	/** Marine Direct Request Service End**/
	/** Marine Certificate Request Service Start**/
	public MarineCertificateResponseInfo marineCertificateService(String policyNo){
		MarineCertificateResponseInfo responseInfo = new MarineCertificateResponseInfo();
		try{
			if(isLinkUp(getXMLString("WS_URL_CERTIFICATE_SERVICE"))){
				MarineCertificateServiceLocator service = new MarineCertificateServiceLocator();
				MarineCertificateReqServicePortBindingStub clientStub = new MarineCertificateReqServicePortBindingStub(new URL(getXMLString("WS_URL_CERTIFICATE_SERVICE")),service);
				SOAPHeaderElement soapHeader = new SOAPHeaderElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
				soapHeader.setPrefix("wsse");
				soapHeader.setMustUnderstand(true);
				SOAPElement oElement1 = soapHeader.addChildElement("UsernameToken");
				SOAPElement oElement = oElement1.addChildElement("Username");
				oElement.addTextNode(UserName);
				oElement = oElement1.addChildElement("Password");
				oElement.addTextNode(Password);
				clientStub.setHeader(soapHeader);
				clientStub.setUsername(UserName);
				clientStub.setPassword(Password);
				MarineCertificateRequestInfo requestInfo = getMarineCertificateRequest(policyNo);
				responseInfo=clientStub.marineCertificateRequest(requestInfo);
				updateStatus(policyNo,responseInfo.getStatus(), responseInfo.getStatusMessage(),responseInfo.getResponsibility());
				System.out.println(responseInfo.getOutPut());
				System.out.println("Status: "+responseInfo.getStatus());
				System.out.println("StatusMessage: "+responseInfo.getStatusMessage());
				System.out.println("Responsibility: "+responseInfo.getResponsibility());
				String Responsibility=responseInfo.getResponsibility()==null?"":responseInfo.getResponsibility();
				String input="<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:MarineCertificateRequestResponse xmlns:ns2=\"http://marinecertificate.services.maansarovar.premia.iii.com/\"><MarineCertificateInformation><Status>"+responseInfo.getStatus()+"</Status><StatusMessage>"+responseInfo.getStatusMessage()+"</StatusMessage><Responsibility>"+Responsibility+"</Responsibility></MarineCertificateInformation></ns2:MarineCertificateRequestResponse></S:Body></S:Envelope>";
				updateXmlRequest(policyNo,input,"Response","");

			}else{
				getMarineCertificateRequest(policyNo);
				 responseInfo.setStatus("FAILD");
				 responseInfo.setStatusMessage("CERTIFICATE SERVICE DOWN");
				updateStatus(policyNo,"FAILD", "CERTIFICATE SERVICE DOWN","3I");
			}

		}catch (com.iii.premia.maansarovar.services.marinecertificate.WSExceptionBean ex) {
			System.out.println(ex.getErrorDetails().getErrorDescription() + ex.getErrorDetails().getErrorCode());
			String exMsg=ex.getErrorDetails().getErrorDescription()==null?"": ex.getErrorDetails().getErrorDescription()
					+ ex.getErrorDetails().getErrorCode()==null?"":"and error code "+ ex.getErrorDetails().getErrorCode() ;
			getMarineCertificateRequest(policyNo);
			 responseInfo.setStatus("FAILD");
			 responseInfo.setStatusMessage(exMsg);
			updateStatus(policyNo,"Error", exMsg,"3I");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return responseInfo;
	}
	private MarineCertificateRequestInfo getMarineCertificateRequest(String policyNo) {
		MarineCertificateRequestInfo marineReq = new MarineCertificateRequestInfo();
		try{
			MarineCertificateRequest certificateReq = new MarineCertificateRequest();
			Object param[]=new Object[20];
			param[0]=merineCusTag(policyNo);
			param[1]=marineDirectPolicy(policyNo);
			param[2]=marineDirectRisk(policyNo);
			param[3]=marineDirectRiskVessels(policyNo);
			param[4]=marineDirecRiskSmiInfo(policyNo);
			param[5]=marineDirecRiskCoverInfo(policyNo);
			//param[6]=marineDirectRiskDedtInfo(policyNo);
			param[6]="";
			param[7]=marineDirectRiskDisInfo(policyNo);
			param[8]=marineDirectRiskLoadInfo(policyNo);
			param[9]=marineDirecDeductibleInfo(policyNo);
			//param[9]="";
			param[10]=marineDirectDisCountInfo(policyNo);
			param[11]=marineDirectLoadingInfo(policyNo);
			param[12]=marineDirectConditionInfo(policyNo);
			param[13]=marineDirectChargesInfo(policyNo);
			param[14]=marineDirectBrokersInfo(policyNo);
			param[15]=marineDirectFacsharedtl(policyNo);
			param[16]=marineDirectFacparDtls(policyNo);
			param[17]=marineDirectFacpartComm(policyNo);
			param[18]=marineVatInfo(policyNo);
			param[19]=marineAccInfo(policyNo);
			String input=getXMLString("MARINE_CERTIFICATE_OVERALL_TAG", param);
			input=heading + replceSplChar(input);
			logger.info("Request XML String => "+input);
			updateXmlRequest(policyNo,input,"Request","CERTIFICATE");
			certificateReq.setInput(input);
			marineReq.setWSInformation(certificateReq);
		}catch(Exception e){
			e.printStackTrace();
		}
		return marineReq;
	}
	/** Marine Certificate Request Service End**/

	/** Endorsement Request Service Start
	 * @param tranId ***/
	public String marineEndtPolicyTag(String policyNo, String tranId) {
		List<Map<String, Object>> records=dao.getEndorsePolicyTag(policyNo,tranId);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("ENDTPOLICY",records.get(i));
			input+=getXMLString("MARINE_ENDT_POLICY", params);
		}
		return input;
	}
	private String marineEndtRisk(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtRiskTag(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTRISK",records.get(i));
			input=getXMLString("MARINE_ENDT_RISK", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTRISK",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineEndtRiskVessels(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtRiskVesselTag(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTVESSEL",records.get(i));
			input=getXMLString("MARINE_ENDT_RISK_VESSEL", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTVESSEL",new HashMap<String, Object>());
		}*/

		return input;
	}

	private String marineEndtRiskCoverInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtRiskCoverTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTRISKCVR",records.get(i));
			input+=getXMLString("MARINE_ENDT_RISK_COVER", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTRISKCVR",new HashMap<String, Object>());
		}*/
		
		return input;
	}
	private String marineEndtRiskDedtInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtRiskDedtTag(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTRISKDED",records.get(i));
			params[0]=i+1;
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTRISKDED",new HashMap<String, Object>());
		}*/
		input=getXMLString("MARINE_ENDT_RISK_DEDUCTIBLE", params);
		return input;
	}
	private String marineEndtRiskDisInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtRiskDisTag(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTRISKDIS",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_ENDT_RISK_DISCOUNTINFO", params);
		}
		/*if(records.size()<=0)
		{

			params=dao.constantList("ENDTRISKDIS",new HashMap<String, Object>());
		}*/
		return input;
	}
	private String marineEndtRiskLoadInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtRiskLoadTag(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTRISKLOAD",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_ENDT_RISK_LOADINGINFO", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTRISKLOAD",new HashMap<String, Object>());
		}*/

		return input;
	} 
	private String marineEndtDeductibleInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtDeductibleTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTDEDUCTIBLE",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_ENDT_DEDUCTIBLE", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTDEDUCTIBLE",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineEndtDisCountInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtDiscountTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTDISCOUNT",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_ENDT_DISCOUNTINFO", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTDISCOUNT",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineEndtLoadingInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtLoadingTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTLOADINFO",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_ENDT_LOADINGINFO", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTLOADINFO",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineEndtRiskSmiInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtRiskSmiTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTSMIINFO",records.get(i));
			params[0]=i+1;
			input+=getXMLString("MARINE_ENDT_RISK_SMIINFO", params);

		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTSMIINFO",new HashMap<String, Object>());
			input=getXMLString("MARINE_ENDT_RISK_SMIINFO", params);
		}*/
		return input;
	}
	private String marineEndtConditionInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtConditionTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTCONDINFO",records.get(i));
			input+=getXMLString("MARINE_ENDT_COND", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTCONDINFO",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineEndtChargesInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtChargesTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ENDTCHARGES",records.get(i));
			params[0]=i+1;
			input=getXMLString("MARINE_ENDT_CHARGESINFO", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTCHARGES",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineEndtBrokersInfo(String policyNo, String tranId){
		List<Map<String, Object>> records=dao.getEndtBrokersTagInfo(policyNo,tranId);
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("ENDTBROKER",records.get(i));
			input=getXMLString("MARINE_ENDT_BROKERCOM", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ENDTBROKER",new HashMap<String, Object>());
		}*/

		return input;
	}
	private String marineEndtAccInfo(String policyNo, String tranId) {
		List<Map<String, Object>> records=dao.getEndtAccTagInfo(policyNo,tranId);
		String input="";
		Object[] params=null;
		for(int i=0;i<records.size();i++)
		{
			params=dao.constantList("ACCOUNTSINFO", records.get(i));
			params[0]=i+1;
			input+=getXMLString("MARINE_DIRECT_ACCINFO", params);
		}
		/*if(records.size()<=0)
		{
			params=dao.constantList("ACCOUNTSINFO",new HashMap<String, Object>());
			input=getXMLString("MARINE_ENDT_RISK_SMIINFO", params);
		}*/
		return input;
	}


	public EndorsementProcessResponseInfo endorsementProcessService(String policyNo,String EndtPolicyNo){
		EndorsementProcessResponseInfo responseInfo = new EndorsementProcessResponseInfo();
		try{
			EndorsementProcessServiceLocator service = new EndorsementProcessServiceLocator();
			if(isLinkUp(getXMLString("WS_URL_ENDORSMENT_SERVICE"))){
				EndorsementProcessReqServicePortBindingStub clientStub = new EndorsementProcessReqServicePortBindingStub(new URL(getXMLString("WS_URL_ENDORSMENT_SERVICE")),service);
				SOAPHeaderElement soapHeader = new SOAPHeaderElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
				soapHeader.setPrefix("wsse");
				soapHeader.setMustUnderstand(true);
				SOAPElement oElement1 = soapHeader.addChildElement("UsernameToken");
				SOAPElement oElement = oElement1.addChildElement("Username");
				oElement.addTextNode(UserName);
				oElement = oElement1.addChildElement("Password");
				oElement.addTextNode(Password);
				clientStub.setHeader(soapHeader);
				clientStub.setUsername(UserName);
				clientStub.setPassword(Password);
				EndorsementProcessRequestInfo requestInfo = getEndorsementProcessRequest(policyNo);
				responseInfo=clientStub.endorsementProcessRequest(requestInfo);
				updateStatus(EndtPolicyNo,responseInfo.getStatus(), responseInfo.getStatusMessage(),responseInfo.getResponsibility());
				System.out.println(responseInfo.getOutPut());
				System.out.println("Status: "+responseInfo.getStatus());
				System.out.println("StatusMessage: "+responseInfo.getStatusMessage());
				System.out.println("Responsibility: "+responseInfo.getResponsibility());
				String Responsibility=responseInfo.getResponsibility()==null?"":responseInfo.getResponsibility();
				String input="<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body> <ns2:EndorsementProcessRequestResponse xmlns:ns2=\"http://endorsementprocess.services.maansarovar.premia.iii.com/\"><EndorsementProcessInformation><Status>"+responseInfo.getStatus()+"</Status><StatusMessage>"+responseInfo.getStatusMessage()+"</StatusMessage><Responsibility>"+Responsibility+"</Responsibility></EndorsementProcessInformation></ns2:EndorsementProcessRequestResponse></S:Body></S:Envelope>";
				updateXmlRequest(policyNo,input,"Response","");

			}else{
				getEndorsementProcessRequest(policyNo);
				responseInfo.setStatus("FAILD");
				responseInfo.setStatusMessage("ENDORSMENT SERVICE DOWN");
				updateStatus(EndtPolicyNo,"FAILD", "ENDORSMENT SERVICE DOWN","3I");
			}
		}catch (EndorsementProcessExceptionBean ex) {
			System.out.println(ex.getErrorDetails().getErrorDescription() + ex.getErrorDetails().getErrorCode());
			String exMsg=ex.getErrorDetails().getErrorDescription()+"and error code "+ ex.getErrorDetails().getErrorCode() ;
			exMsg="Exception in Premia : "+exMsg==null?"Please contact System Administrator":exMsg;
			responseInfo.setStatus("FAILD");
			responseInfo.setStatusMessage(exMsg);
			updateStatus(EndtPolicyNo,"Error", exMsg,"3I");
		}
		catch(Exception e){
			updateStatus(EndtPolicyNo,"Error", "DataBase Exception Happened. Please contact System Administrator.","3I/MAANSAROVAR");
			e.printStackTrace();
		}
		return responseInfo;
	}
	public String getMaxTranId(String policyNo)
	{
		String result=null;
		try{
			result=dao.getMaxTranId(policyNo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private EndorsementProcessRequestInfo getEndorsementProcessRequest(String policyNo) {
		EndorsementProcessRequestInfo marineReq = new EndorsementProcessRequestInfo();
		try{
			EndorsementProcessRequest certificateReq = new EndorsementProcessRequest();
			Object param[]=new Object[17];
			String tranId=getMaxTranId(policyNo);
			param[0]=merineCusTag(policyNo);
			param[1]=marineEndtPolicyTag(policyNo,tranId);
			param[2]=marineEndtRisk(policyNo,tranId);
			param[3]=marineEndtRiskVessels(policyNo,tranId);
			param[4]=marineEndtRiskSmiInfo(policyNo,tranId);
			param[5]=marineEndtRiskCoverInfo(policyNo,tranId);
			param[6]="";
			//param[6]=marineEndtRiskDedtInfo(policyNo,tranId);
			param[7]=marineEndtRiskDisInfo(policyNo,tranId);
			param[8]=marineEndtRiskLoadInfo(policyNo,tranId);
			//param[9]="";
			param[9]=marineEndtDeductibleInfo(policyNo,tranId);
			param[10]=marineEndtDisCountInfo(policyNo,tranId);
			param[11]=marineEndtLoadingInfo(policyNo,tranId);
			param[12]=marineEndtConditionInfo(policyNo,tranId);
			param[13]=marineEndtChargesInfo(policyNo,tranId);
			param[14]=marineEndtBrokersInfo(policyNo,tranId);
			param[15]=marineEndtVatInfo(policyNo, tranId);
			param[16]=marineEndtAccInfo(policyNo,tranId);
			String input=getXMLString("MARINE_ENDT_OVERALL_TAG", param);
			input=heading + replceSplChar(input);
			logger.info("Request XML String => "+input);
			updateXmlRequest(policyNo,input,"Request","ENDORSEMENT");
			certificateReq.setInput(input);
			marineReq.setWSInformation(certificateReq);
		}catch(Exception e){
			e.printStackTrace();
		}
		return marineReq;
	}
	/** Endorsement Request Service End*****/
	@SuppressWarnings("unchecked") 	
	public String cusTag(String customerId,String type) {
		List<Map<String, Object>> records=new ArrayList<Map<String, Object>>();
		if("search".equalsIgnoreCase(type)){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("MISSIPPI_CUSTOMER_CODE", customerId);//For customer search
			records.add(map);
		}
		else
			records=dao.getCustomerInfoCust(customerId);// customerId Quote no when hitting quotaion cus vaildatation;
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("CUSTOMER",records.get(i));
			input=getXMLString("CUST_REQ_XML", params);
		}
		return input;
	}
	
	public CustomerDataRequestInfo getCustomerDataRequest(String customerId,String type){
		CustomerDataRequestInfo mrinecusReq = new CustomerDataRequestInfo();
		try{
			CustomerDataRequest cusReq = new CustomerDataRequest();
			Object param[]=new Object[1];
			param[0]=cusTag(customerId,type);

			String input=(String) param[0];
			input=heading + replceSplChar(input);
			logger.info("Request XML String => "+input);
			cusReq.setInput(input);
			mrinecusReq.setWSInformation(cusReq);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mrinecusReq;
	}
	
	
	
	
	public CustomerDataResponseInfo customerDataService(String customerId,String type){
		CustomerDataResponseInfo responseInfo = new CustomerDataResponseInfo();
		try{
			MaansarovarServicesServiceLocator service = new MaansarovarServicesServiceLocator();
			MaansarovarServicePortBindingStub clientStub = new MaansarovarServicePortBindingStub(new URL(getXMLString("WS_URL_COMMON_SERVICE")),service);
			SOAPHeaderElement soapHeader = new SOAPHeaderElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
			soapHeader.setPrefix("wsse");
			soapHeader.setMustUnderstand(true);
			SOAPElement oElement1 = soapHeader.addChildElement("UsernameToken");
			SOAPElement oElement = oElement1.addChildElement("Username");
			oElement.addTextNode(UserName);
			oElement = oElement1.addChildElement("Password");
			oElement.addTextNode(Password);
			clientStub.setHeader(soapHeader);
			clientStub.setUsername(UserName);
			clientStub.setPassword(Password);
			CustomerDataRequestInfo requestInfo = getCustomerDataRequest(customerId,type);
			responseInfo=clientStub.customerDataRequest(requestInfo);
			if(responseInfo.getOutPut() != null)
				setCustXmlBean(responseInfo.getOutPut());
			System.out.println(responseInfo.getOutPut());
			System.out.println("Status: "+responseInfo.getStatus());
			System.out.println("StatusMessage: "+responseInfo.getStatusMessage());
			//System.out.println("Responsibility: "+responseInfo.getResponsibility());

		}catch(Exception e){
			e.printStackTrace();
		}
		return responseInfo;
	}
	public CustXmlBean setCustXmlBean (String obj)  {
		CustXmlBean xml = null;
		try {
			StringReader reader = new StringReader(obj);
			JAXBContext jaxbContext = JAXBContext.newInstance(CustXmlBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			//InputStream in = IOUtils.toInputStream(obj, "UTF-8");
			 xml = (CustXmlBean) unmarshaller.unmarshal(reader);
			System.out.println("xml.getCustCreditChkYN()====>"+xml.getCustQueryDtls().getCustomer().getCustCreditChkYN());
			System.out.println("xml.getCustCreditLimit()======>"+xml.getCustQueryDtls().getCustomer().getCustCreditLimit());
			System.out.println("xml.getCustCreditBalance()=====>"+xml.getCustQueryDtls().getCustomer().getCustRateRI());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	public boolean isLinkUp(String urlStr) {
		logger.info(urlStr);
		URL url = null;
		boolean result = false;
		int responsCode = 0;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpConn =  (HttpURLConnection)url.openConnection();
			httpConn.setInstanceFollowRedirects( false );
			try{
				httpConn.connect();
				logger.info( "Up : " + httpConn.getResponseCode());
				responsCode = httpConn.getResponseCode();
			}catch(java.net.ConnectException e){
				result = false;
				System.out.println( " Down ");
			}
			if((responsCode>=100) && (responsCode<300)) {
				logger.info("GOOD URL");
				result = true;
			} else {
				logger.info("BAD URL");
				result = false;
			}
		} catch (MalformedURLException ex) {
			logger.info("bad URL");
			result = false;
			ex.printStackTrace();
		} catch (IOException ex) {
			logger.info("Failed opening connection. Perhaps WS is not up?");
			result = false;
			ex.printStackTrace();
		}
		return result;
	}
	private String replceSplChar(String input){
		String result="";
		try {
		result=input.replaceAll("&" , "&#38;").replaceAll("'" , "&#39;").replaceAll("\"" , "&#34;").replaceAll("[\\\t|\\\n|\\\r]"," ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public CustomerDataResponseInfo brokerDataService(String customerId,String type) {
		CustomerDataResponseInfo responseInfo = new CustomerDataResponseInfo();
		try{
			MaansarovarServicesServiceLocator service = new MaansarovarServicesServiceLocator();
			MaansarovarServicePortBindingStub clientStub = new MaansarovarServicePortBindingStub(new URL(getXMLString("WS_URL_COMMON_SERVICE")),service);
			SOAPHeaderElement soapHeader = new SOAPHeaderElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
			soapHeader.setPrefix("wsse");
			soapHeader.setMustUnderstand(true);
			SOAPElement oElement1 = soapHeader.addChildElement("UsernameToken");
			SOAPElement oElement = oElement1.addChildElement("Username");
			oElement.addTextNode(UserName);
			oElement = oElement1.addChildElement("Password");
			oElement.addTextNode(Password);
			clientStub.setHeader(soapHeader);
			clientStub.setUsername(UserName);
			clientStub.setPassword(Password);
			CustomerDataRequestInfo requestInfo = getBrokerDataRequest(customerId,type);
			responseInfo=clientStub.customerDataRequest(requestInfo);
			if(responseInfo.getOutPut() != null)
				setCustXmlBean(responseInfo.getOutPut());
			System.out.println(responseInfo.getOutPut());
			System.out.println("Broker Status: "+responseInfo.getStatus());
			System.out.println("Broker StatusMessage: "+responseInfo.getStatusMessage());
			//System.out.println("Responsibility: "+responseInfo.getResponsibility());

		}catch(Exception e){
			e.printStackTrace();
		}
		return responseInfo;
	
	}
	public CustomerDataRequestInfo getBrokerDataRequest(String customerId,String type){
		CustomerDataRequestInfo mrinecusReq = new CustomerDataRequestInfo();
		try{
			CustomerDataRequest cusReq = new CustomerDataRequest();
			Object param[]=new Object[1];
			param[0]=brokerTag(customerId,type);

			String input=(String) param[0];
			input=heading + replceSplChar(input);
			logger.info("Request XML String => "+input);
			cusReq.setInput(input);
			mrinecusReq.setWSInformation(cusReq);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mrinecusReq;
	}
	/** Endorsement Request Service End*****/
	@SuppressWarnings("unchecked") 	
	public String brokerTag(String customerId,String type) {
		List<Map<String, Object>> records=new ArrayList<Map<String, Object>>();
		if("search".equalsIgnoreCase(type)){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("MISSIPPI_CUSTOMER_CODE", customerId);//For customer search
			records.add(map);
		}
		else
			records=dao.getCustomerInfoBroker(customerId);// customerId Quote no when hitting quotaion cus vaildatation;
		String input="";
		for(int i=0;i<records.size();i++)
		{
			Object[] params=dao.constantList("CUSTOMER",records.get(i));
			input=getXMLString("CUST_REQ_XML", params);
		}
		return input;
	}
	
}
