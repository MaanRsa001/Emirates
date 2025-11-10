package com.maan.common;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.HashMap;
import java.io.File;
import org.w3c.dom.*;

public class MailServerInfo
{
	public HashMap getMailServerInfo(final String branch,final String path)
	{
		HashMap serverInfo = new HashMap();
		try
		{
			  DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			  Document doc = docBuilder.parse (new File(path));
			  String branchCode = "Branch-";
			  branchCode = branchCode+branch;
			  NodeList n = doc.getElementsByTagName(branchCode);
			  NodeList ch = null;
			  NodeList ch1 = null;
			  NodeList ch2 = null;
			  NodeList ch3 = null;
			  NodeList ch4 = null;
			  String host = "";
			  String user = "";
			  String pass = "";
			  String remarks = "";
			  String frmAdd = "";
			 for(int i=0;i<n.getLength();i++)
             {
	          	   Node node = n.item(i);
	          	   if(node.getNodeType()==Node.ELEMENT_NODE)
	          	   {
	          		   Element doc1=(Element)node;
	          		   NodeList n1 =doc1.getElementsByTagName("Host-Name");
	          		   Element e=(Element) n1.item(0) ;
	          		   ch=e.getChildNodes();
	          		   host = (String)ch.item(0).getNodeValue().trim();
	
	          		   NodeList n2 =doc1.getElementsByTagName("User-name");
	          		   Element e1=(Element) n2.item(0) ;
	          		   ch1=e1.getChildNodes();
	          		   user =  (String) ch1.item(0).getNodeValue().trim();
	          		  
	          		   NodeList n3 =doc1.getElementsByTagName("Password");
	          		   Element e2=(Element) n3.item(0) ;
	          		   ch2=e2.getChildNodes();
	          		   pass = (String) ch2.item(0).getNodeValue().trim();
	          		  
	          		   NodeList n4 =doc1.getElementsByTagName("Remarks");
	          		   Element e3=(Element) n4.item(0) ;
	          		   ch3=e3.getChildNodes();
	          		   remarks = (String) ch3.item(0).getNodeValue().trim();
	          		  
	          		   NodeList n5 =doc1.getElementsByTagName("From-Address");
	          		   Element e4=(Element) n5.item(0) ;
	          		   ch4=e4.getChildNodes();
	          		   frmAdd = ch4.item(0).getNodeValue().trim();
	          	   }
             }
			 
			 serverInfo.put("hostname", host);
          	 serverInfo.put("username", user);
          	 serverInfo.put("password", pass);
          	 serverInfo.put("webaddress", remarks);
          	 serverInfo.put("Address", frmAdd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/*if(serverInfo.size() > 0)
		{
			String s = (String)serverInfo.get("hostname");
          	String s1 = (String)serverInfo.get("username");
          	String s2 = (String)serverInfo.get("password");
          	String s3 = (String)serverInfo.get("webaddress");
          	String s4 = (String)serverInfo.get("Address");
			System.out.println("Host Name ...."+s);
			System.out.println("User Name ...."+s1);
			System.out.println("Password  ...."+s2);
			System.out.println("Web address ...."+s3);
			System.out.println("From Address ...."+s4);
		}*/
		return serverInfo;
	}
}// Class