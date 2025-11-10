package com.maan.webservice.service;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class LoginAuthentication {
	
	public boolean getAuthenticateLogin(final String userName,final String password){
		boolean result = false;
		try{
			final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			  final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			  String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			  String basePath=path.substring(0, path.indexOf("WEB-INF"));
			  //System.out.println("Base Path"+basePath);
			  final Document doc = docBuilder.parse (new File(basePath+"/LoginInfo/LoginAuthentication.xml"));
			  DocumentTraversal traversal = (DocumentTraversal) doc;
			  NodeIterator iterator = traversal.createNodeIterator(
				  doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
			  for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {
				  String tagName = ((Element) n).getTagName();
				  if(tagName.indexOf("User-Name")!=-1){
					  NodeList ch = ((Element)n).getChildNodes();
					  String user = (String)ch.item(0).getNodeValue().trim();
					  if(userName.equals(user)){
						  String indx = tagName.replaceAll("User-Name", "");
						  final NodeList n2 =doc.getElementsByTagName("Password"+indx);
		          		  final Element e2=(Element) n2.item(0) ;
		          		  ch = e2.getChildNodes();
						  String pwd = (String)ch.item(0).getNodeValue().trim();
						  if(PasswordEnc.crypt(password.substring(0,3), password).equals(pwd)){
							  result = true;
						  }else{
							  result = false;
							  //throw new Exception("Authentication Failed due to Invalid Details");
						  }
						  break;
					  }
				  }
			  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(final String[] argss){
		LoginAuthentication auth = new LoginAuthentication();
	
	}
}
