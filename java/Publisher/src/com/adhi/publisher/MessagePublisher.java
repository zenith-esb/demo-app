package com.adhi.publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;


public class MessagePublisher extends Observable implements Runnable {
	
	private String body = "";
	private String publishUrl = "http://192.168.0.2:8280/message";
	private int count = 0;


	private String data = null;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPublishUrl() {
		return publishUrl;

	}

	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}

	
	 @Override
	public void run() {
		
		try {
			
			  data = sendMessage(publishUrl, body);
			  System.out.println("Incomming: " + data);
			  
		} catch (Exception e) {
					setNotification(-1);
				  e.printStackTrace();
		}
	}
	
	 private String sendMessage(String pubUrl, String message) throws IOException{
		 	URL url = new URL(pubUrl);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			setNotification(10);
			
			connection.connect();
			setNotification(20);
			
			OutputStream os = connection.getOutputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
			String payload = message;
			pw.write(payload);
			pw.close();
			setNotification(70);
			
			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
			    sb.append(line);
			}
			is.close();
			String response = sb.toString();
			setNotification(100);
			
			return response;
		}


	 
	 private void setNotification(int num){
		
		 this.count = num;
		 setChanged();
		 notifyObservers();
	 }
	 public int getValue(){
		 return this.count;
	 }
	 
}
