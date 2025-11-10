package com.maan.proj.xml;

import java.io.BufferedInputStream;import java.io.File;import java.io.FileNotFoundException;import java.io.PrintWriter;import java.util.Hashtable;import java.util.Properties;import java.util.Vector;import javax.xml.parsers.SAXParser;import javax.xml.parsers.SAXParserFactory;import org.xml.sax.Attributes;import org.xml.sax.SAXException;import org.xml.sax.helpers.DefaultHandler;
 
public class SaxReader extends DefaultHandler
{
	StringBuffer textBuffer;	// To store value or character in between the tags.
	Vector table;				// To store entire data in table format.
	Hashtable record;			// To store temperory record before inserting into table.

	boolean rootFound;			// Flag to check the root of the document identified.
	String root;				// To store the name of the root.
	String fileName;			// File to be read.
 	BufferedInputStream buffer;

	int maxElements;			// Maximum elements in the document excluding root.
	int counter;				// Temperory counter for looping.

	PrintWriter out;
	
	
	/**
		Default Constructor to initialize the fields.
	*/
	public SaxReader()
	{
		table = new Vector();
		record = new Hashtable();
		rootFound = false;
		root = "";
		maxElements = 5;
		counter = 0;
		fileName = "";
		buffer = null;
		setProperties();
	}

	/**
		Constructor to initialize file name, maximum elements with passed value. 
	*/
	public SaxReader(String file, int maxElements, PrintWriter out)
	{
		table = new Vector();
		record = new Hashtable();
		rootFound = false;
		root = "";
		this.maxElements = maxElements;
		counter = 0;
		fileName = file;
		buffer = null;
		this.out = out;
		setProperties();
	}


	/**
		Constructor to initialize file name, maximum elements with passed value. 
	*/
	public SaxReader(BufferedInputStream buffer, int maxElements, PrintWriter out)
	{
		table = new Vector();
		record = new Hashtable();
		rootFound = false;
		root = "";
		this.maxElements = maxElements;
		counter = 0;
		fileName = "";
		this.buffer = buffer;
		this.out = out;
		setProperties();
	}

	public void startDocument() throws SAXException
	{
	}

	public void endDocument() throws SAXException
	{
	}

	public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException
	{
		String eName = sName; // element name
		
		if ("".equals(eName)) 
			eName = qName; // not namespaceAware
		
		
		if(rootFound == false)
		{
			rootFound = true;
			root = eName;
			counter = 0;
		}
	}
 
	public void endElement(String namespaceURI, String sName, String qName) throws SAXException
	{
		
		String eName = sName; // element name
		
		if ("".equals(eName)) 
			eName = qName; // not namespaceAware

		if(rootFound)
		{
			counter++;
			record.put(eName,textBuffer);
			textBuffer = null;
		}

		if(counter == maxElements)
		{
			table.addElement(record);
			record = new Hashtable();
			counter = 0;
		}
	}


	public void characters(char buf[], int offset, int len) throws SAXException
	{
		String s = new String(buf, offset, len);
		if (textBuffer == null)
			textBuffer = new StringBuffer(s);
		else
			textBuffer.append(s);
	}

	public Vector getData()
	{
		return table;
	}

	public void setFile(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFile()
	{
		return fileName;
	}

	public void setMaxElements(int max)
	{
		maxElements = max;
	}

	public int getMaxElements()
	{
		return maxElements;
	}

	public Vector read() throws Exception
	{
		//out.println("Filename : "+fileName+" Buffer : "+buffer);
		
		if((fileName == null || fileName.equals("")) && buffer == null)
		{
			throw new FileNotFoundException("File is Empty");
		}
		
		// Use an instance of ourselves as the SAX event handler
		//DefaultHandler handler = new SaxReader();
		DefaultHandler handler = this;
 
        // Use the default (non-validating) parser
	    SAXParserFactory factory = SAXParserFactory.newInstance();
	    try
		{
			// Parse the input 
			SAXParser saxParser = factory.newSAXParser();
			
			if(fileName.equals("") && buffer != null)
				saxParser.parse( buffer, handler );
			else
				saxParser.parse( new File(fileName), handler );
				
			return getData();
        }
		catch (Throwable t)
		{
			throw new Exception("Throwable Exception : "+t);
		}
	}

	public void setProperties()
	{
		Properties saxParserFactoryProperties = System.getProperties();
        saxParserFactoryProperties.put("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
		saxParserFactoryProperties.put("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		saxParserFactoryProperties.put("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.StandardParserConfiguration");
		saxParserFactoryProperties.put("org.xml.sax.driver", "org.apache.xerces.parsers.SAXParser");
	    System.setProperties(saxParserFactoryProperties);
	}

	/*public static void main(String args[]) throws Exception
	{
		SaxReader sax = new SaxReader();
		sax.setFile(args[0]);
		sax.setMaxElements(5);
		Vector v = sax.read();

		System.out.println("V Size : "+v.size());
		for(int i=0; i < v.size(); i++)
		{
			Hashtable h = (Hashtable) v.get(i);
			System.out.println("ID : "+h.get("id"));
			System.out.println("Email : "+h.get("email"));
			System.out.println("Day : "+h.get("day"));
			System.out.println("type : "+h.get("type"));
		}
	}*/
}