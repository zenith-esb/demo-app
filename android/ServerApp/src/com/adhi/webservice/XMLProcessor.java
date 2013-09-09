package com.adhi.webservice;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLProcessor {

	public static String getValue(String xml, String tag){
		String value = null;
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new InputSource(new StringReader(xml)));
		    //Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		    
		    System.out.println("Root element :" + document.getDocumentElement().getNodeName());
		    Element elem = document.getDocumentElement();
		    NodeList temp = elem.getElementsByTagName(tag);
		    
		    Element eElement = (Element) temp.item(0);
		    value = eElement.getNodeValue().toString();
		    //NodeList nList = document.getElementsByTagName(tag);
		    
		    //value = nList.item(0).getNodeValue();
		} catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	public static void main(){
		
		String xml = "<?xml version=\"1.0\"?>" +
				  "<root>" +
					"<title>CSE message</title>" +
					"<message> tst </message>" +
			   	   "</root>";
		System.out.println("message" + getValue(xml, "message"));
	}
}

