package com.maan.webservice.service;

import java.io.StringReader;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.maan.common.*;
import com.maan.webservice.dao.*;

public class QuoteGeneration {
	private static final Logger logger = LogUtil.getLogger(QuoteGeneration.class);
	
	public String  quoteRequestXMLReader(String request) throws Exception
	{
		logger.info("Enters quoteRequestXMLReader - Service");
		String result = "";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		result = quoteGenerationDAO.quoteRequestXMLReader(request);
		logger.info("Exits quoteRequestXMLReader - Service");
		return result;
		
	}
	public void premiumRequestXMLReader(String request) throws Exception
	{
		logger.info("Enters premiumRequestXMLReader - Service");
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		quoteGenerationDAO.quoteRequestXMLReader(request);
		logger.info("Exits premiumRequestXMLReader - Service");
	}
	public void draftRequestXMLReader(String request) throws Exception
	{
		logger.info("Enters draftRequestXMLReader - Service");
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		quoteGenerationDAO.quoteRequestXMLReader(request);
		logger.info("Exits draftRequestXMLReader - Service");
	}
	public void policyRequestXMLReader(String request) throws Exception
	{
		logger.info("Enters policyRequestXMLReader - Service");
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		quoteGenerationDAO.quoteRequestXMLReader(request);
		logger.info("Exits policyRequestXMLReader - Service");
	}
	public boolean authentication(String requestInfo, String info) {
		
		boolean authenticated = false;
		String username = "";
		String password = "";
		//Start XML reading
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
		    username =  getTagValue("username", eElement);
		    password =  getTagValue("password", eElement);
		    
		}
		//ends XML reading
		LoginAuthentication auth = new LoginAuthentication();
		authenticated = auth.getAuthenticateLogin(username,password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return authenticated;
	}
	private static String getTagValue(String sTag, Element eElement) {
		
		String result="";
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 	Node nValue = (Node) nlList.item(0);
	 	if(nValue!=null)
	 	{
	 		result = nValue.getNodeValue();
	 	}
	 	return result;
	  }
	public String getReferenceNo(String requestInfo,String info) throws Exception{
		String refNo="";
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
			    refNo =  getTagValue("ReferenceNumber", eElement);
			}
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		return refNo;
	}
	public String getPolicyRequest(String requestInfo,String info) throws Exception{
		String request="";
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
			}
			//ends XML reading
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		return request;
	}
	public String generateResponse(String referenceNo, String remarks) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.generateResponse(referenceNo,remarks);
		return response;	
	}
	public String generatePremiumResponse(String requestInfo) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.generatePremiumResponse(requestInfo);
		return response;
	}
	public String generateDraftResponse(String requestInfo) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.generateDraftResponse(requestInfo);
		return response;
	}
	public String generatePolicyResponse(String requestInfo,String remarks) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.generatePolicyResponse(requestInfo,remarks);
		return response;
	}
	public String generateReferralResponse(String requestInfo,String remarks) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.generateReferralResponse(requestInfo,remarks);
		return response;
	}
	public List getPolicyDetails(String requestInfo, String remarks) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		List response = quoteGenerationDAO.getPolicyDetails(requestInfo,remarks);
		return response;
	}
	public List getReferralDetails(String requestInfo, String remarks) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		List response = quoteGenerationDAO.getReferralDetails(requestInfo,remarks);
		return response;
	}
	public List getDraftDetails(String requestInfo, String remarks) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		List response = quoteGenerationDAO.getDraftDetails(requestInfo,remarks);
		return response;
	}
	public String getXML() {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		String response = quoteGenerationDAO.getXML();
		return response;
		
	}
	public List getQuotePDFDetails(String requestInfo, String remarks) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		List response = quoteGenerationDAO.getQuotePDFDetails(requestInfo,remarks);
		return response;
	}
	public List getCreditPDFDetails(String requestInfo, String remarks) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		List response = quoteGenerationDAO.getCreditPDFDetails(requestInfo,remarks);
		return response;
	}
	public List getDebitPDFDetails(String requestInfo, String remarks) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		List response = quoteGenerationDAO.getDebitPDFDetails(requestInfo,remarks);
		return response;
	}
	public String generatePDFResponse(String requestInfo,String type) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.generatePDFResponse(requestInfo,type);
		return response;
	}
	public String getPolicyStatus(String referenceNo) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.getPolicyStatus(referenceNo);
		return response;	
	}
	public String generateQuoteInfoResponse(String requestInfo) {
		String response="";
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		response = quoteGenerationDAO.generateQuoteInfoResponse(requestInfo);
		return response;
	}
	public List getReceiptPdfDetails(String requestInfo, String remarks) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		List response = quoteGenerationDAO.getReceiptPdfDetails(requestInfo,remarks);
		return response;
	}
	public void updateDTMonitor(String response, String referenceNo, String sno) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		quoteGenerationDAO.updateDTMonitor(response, referenceNo, sno);
	}
	public String insertDTMonitor(String request, String referenceNo, String methodType) {
		QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
		return quoteGenerationDAO.insertDTMonitor(request, referenceNo, methodType);
	}
	public String[][] getPolicyProcessStatus(String referenceNo, String status) {
		String response[][]=null;
		try{
			QuoteGenerationDAO quoteGenerationDAO = new QuoteGenerationDAO();
			response = quoteGenerationDAO.getPolicyProcessStatus(referenceNo, status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
}
