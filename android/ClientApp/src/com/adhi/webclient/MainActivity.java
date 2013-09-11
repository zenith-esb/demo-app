package com.adhi.webclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText txt;
	private Button btnPub;
	private Button btnReq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		btnPub = (Button) findViewById(R.id.main_publish_btn);
		btnPub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, EventPublisherActivity.class));
				
			}
		});
		btnReq = (Button) findViewById(R.id.main_send_btn);
		btnReq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, ClientRequestActivity.class));
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(new Intent(MainActivity.this, SettingsActivity.class));
		return true;
	    // Handle item selection
		/*
	    switch (item.getItemId()) {
	        case R.menu.main:
	        	startActivity(new Intent(MainActivity.this, SettingsActivity.class));
	            return true;
	       
	        default:
	            return super.onOptionsItemSelected(item);
	    }*/
	}
//
//	private class ConnectBackground extends AsyncTask<Void, Void, String>{
//
//		@Override
//		protected String doInBackground(Void... arg0) {
//			// TODO Auto-generated method stub
//			String data = null;
//			
//			try {
//				  URL url = new URL("http://192.168.105.1:8280/message");
//				 /*
//				  HttpURLConnection con = (HttpURLConnection) url
//				    .openConnection();
//				  data = readStream(con.getInputStream());
//				  */
//				  data = sendMessage(url, getMessageBody());
//				  } catch (Exception e) {
//				  e.printStackTrace();
//				}
//			return data;
//		}
//		
//		@Override
//		protected void onPostExecute(String result) {
//			// TODO Auto-generated method stub
//			
//			Log.i("WebClient", result);
//			txt.setText(result);
//			btn.setEnabled(true);
//			
//		}
//		private String readStream(InputStream in) {
//			  BufferedReader reader = null;
//			  String data = "";
//			  try {
//			    reader = new BufferedReader(new InputStreamReader(in));
//			    String line = "";
//			    while ((line = reader.readLine()) != null) {
//			     // System.out.println(line);
//			    	data = data + line;
//			    }
//			  } catch (IOException e) {
//			    e.printStackTrace();
//			  } finally {
//			    if (reader != null) {
//			      try {
//			        reader.close();
//			      } catch (IOException e) {
//			        e.printStackTrace();
//			        }
//			    }
//			  }
//			return data;
//		}
//		
//		private String sendMessage(URL url, String message) throws IOException{
//			URLConnection connection = url.openConnection();
//			connection.setDoInput(true);
//			connection.setDoOutput(true);
//
//			connection.connect();
//
//			OutputStream os = connection.getOutputStream();
//			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
//			String payload = message;
//			pw.write(payload);
//			pw.close();
//
//			InputStream is = connection.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//			String line = null;
//			StringBuffer sb = new StringBuffer();
//			while ((line = reader.readLine()) != null) {
//			    sb.append(line);
//			}
//			is.close();
//			String response = sb.toString();
//			return response;
//		}
//
//		private String getMessageBody() {
//			// TODO Auto-generated method stub
//			String body = "<root>" +
//							"<title>CSE message</title>" +
//							"<message>No lectures today</message>" +
//					   	   "</root>";
//			return body;
//		}
//	}
}
