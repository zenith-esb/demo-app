package com.adhi.webclient.util;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLProcessor
{

    public static String processXML (String xml)
        throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(new StringReader (xml));
        int eventType = xpp.getEventType();
        boolean dataFound = false;
        String data = " ";
        while (eventType != XmlPullParser.END_DOCUMENT) {
           if(eventType == XmlPullParser.START_TAG) {
        	 dataFound = true;
        	 
         } else if(eventType == XmlPullParser.END_TAG) {
             
         } else if(eventType == XmlPullParser.TEXT) {
        	 if(dataFound == true)
              data = xpp.getText();
         }
         eventType = xpp.next();
        }
        
        return data;
    }
} 
