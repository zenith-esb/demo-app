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


public class MessagePublisher {
	public static void main(String[] args) {
		

		String body = "test";
		String pubUrl = "http://192.168.0.1:8080/Message" ;
		URL url;
		try {
			url = new URL(pubUrl);
			WorkerThread worker = new WorkerThread(url, body);
			worker.run();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	 static class WorkerThread extends Thread {
		 
		private URL url;
		private String body;
		private String data = null;
		public WorkerThread(URL url, String body) {
			// TODO Auto-generated constructor stub
			 this.url = url;
			 this.body = body;
		}
		 @Override
		public void run() {
			
			try {
				
				  data = sendMessage(url, body);
				  System.out.println("Incomming: " + data);
				  
			} catch (Exception e) {
					  e.printStackTrace();
			}
		}
		
		 private String sendMessage(URL url, String message) throws IOException{
				URLConnection connection = url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);

				connection.connect();

				OutputStream os = connection.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
				String payload = message;
				pw.write(payload);
				pw.close();

				InputStream is = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = null;
				StringBuffer sb = new StringBuffer();
				while ((line = reader.readLine()) != null) {
				    sb.append(line);
				}
				is.close();
				String response = sb.toString();
				return response;
			}
	 }
}
