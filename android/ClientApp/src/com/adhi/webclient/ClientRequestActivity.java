package com.adhi.webclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;


import com.adhi.webclient.util.ClientUtil;




import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ClientRequestActivity extends Activity{
	private Button requestBtn;
	private EditText messageTxtBx;
	private String TAG = ClientUtil.TAG;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request);
		messageTxtBx = (EditText) findViewById(R.id.req_reply_msg);
		
		requestBtn = (Button) findViewById(R.id.request_btn);
		requestBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				requestBtn.setEnabled(false);
				
				new ConnectBackground().execute();
				
			}
		});
	}
	private class ConnectBackground extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			String data = null;
			try {//http://localhost:9000/services/SimpleStockQuoteService
				  URL url = new URL(ClientUtil.getDataRequestServiceUrl()+ "?wsdl");
				  
				  /**
				   * HTTP GET thing
				   */
				 /*
				  HttpURLConnection con = (HttpURLConnection) url
				    .openConnection();
				  data = readStream(con.getInputStream());
				  */
				//  data = getKSoapMessage("A");
				  data = sendMessage(url, getMessageBody());
				  } catch (Exception e) {
				  e.printStackTrace();
				}
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			
		//	Log.i(TAG, result);
			
			messageTxtBx.setText(result);
			requestBtn.setEnabled(true);
			
		}
		private String readStream(InputStream in) {
			  BufferedReader reader = null;
			  String data = "";
			  try {
			    reader = new BufferedReader(new InputStreamReader(in));
			    String line = "";
			    while ((line = reader.readLine()) != null) {
			     // System.out.println(line);
			    	data = data + line;
			    }
			  } catch (IOException e) {
			    e.printStackTrace();
			  } finally {
			    if (reader != null) {
			      try {
			        reader.close();
			      } catch (IOException e) {
			        e.printStackTrace();
			        }
			    }
			  }
			return data;
		}
		
		private String sendMessage(URL url, String message) throws IOException{
			
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-Length", String.valueOf(message.length())); 
			connection.setRequestProperty("Content-Type", "text/xml"); 
			connection.setRequestProperty("Connection", "Close"); 
			connection.setRequestProperty("SoapAction", ""); 
			connection.setDoOutput(true);
			
			PrintWriter pw = new PrintWriter(connection.getOutputStream()); 
			pw.write(message); 
			pw.flush();
			
			connection.connect();

			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				
			    sb.append(line);
			}
			is.close();
			String response = sb.toString();
			Log.d("TEST", response);
			return response;
		}

		private String getMessageBody() {
			// TODO Auto-generated method stub
			String msg = "<ns1:getAvailableSeats xmlns:ns1=\"http://service.zenith.com\">" +
			"<ns1:compartment>A</ns1:compartment></ns1:getAvailableSeats>";
			
			String body = "<?xml version='1.0' encoding='UTF-8'?>" +
					"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
						"<soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">" +
							"<wsa:To>http://192.168.106.1:8080/axis2/services/SeatService</wsa:To>" +
							"<wsa:MessageID>urn:uuid:5d988edb-fd88-4f8c-b1bd-9071a65a73a6</wsa:MessageID>" +
							"<wsa:Action>urn:getAvailableSeats</wsa:Action>" +
						"</soapenv:Header>" +
						"<soapenv:Body>" +
							msg +
						"</soapenv:Body>" +
					"</soapenv:Envelope>";
			return body;
			
//			String body = "<?xml version='1.0' encoding='UTF-8'?>" +
//					"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
//						"<soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">" +
//							"<wsa:To>http://192.168.106.1:9000/services/SimpleStockQuoteService</wsa:To>" +
//							"<wsa:MessageID>urn:uuid:5d988edb-fd88-4f8c-b1bd-9071a65a73a6</wsa:MessageID>" +
//							"<wsa:Action>urn:getQuote</wsa:Action>" +
//						"</soapenv:Header>" +
//						"<soapenv:Body>" +
//							"<m0:getQuote xmlns:m0=\"http://services.samples\">" +
//								"<m0:request><m0:symbol>IBM</m0:symbol></m0:request>" +
//							"</m0:getQuote>" +
//						"</soapenv:Body>" +
//					"</soapenv:Envelope>";
//			return body;
		}
		
		public String getKSoapMessage(String compartment){
			String NAMESPACE = "http://service.zenith.com";
			String SOAP_METHOD = "getAvailableSeats";
			String SOAP_ACTION = "getAvailableSeats";
			String result = "None";
			String URL = "http://192.168.106.1:8080/axis2/services/SeatService?wsdl";
			SoapObject request = new SoapObject(NAMESPACE, SOAP_METHOD);    	
	    	request.addProperty("account", compartment);
	    	
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	envelope.dotNet = true;
	    	envelope.setOutputSoapObject(request);
	    	
	    	AndroidHttpTransport aht = new AndroidHttpTransport(URL);

	    	try
	    	{
	    		aht.call(SOAP_ACTION, envelope);

	    		
	    		SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;                	               
				result =resultsRequestSOAP.toString();

	    	//	String fname = player.getProperty("FirstName").toString();

	    	} catch (Exception ex) {
	    		result = null;
	    	}    
	    	
	    	return result;
		}
	}


}
