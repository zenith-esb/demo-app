package com.adhi.webclient;
import com.adhi.webclient.util.ClientUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsActivity extends Activity{
	private Button btnBack;
	private Button btnUpdate;
	private TextView ip;
	private TextView port;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ip = (TextView) findViewById(R.id.serverAddress);
		port = (TextView) findViewById(R.id.serverPort);
		
		ip.setText(ClientUtil.getEsbAddress());
		port.setText(ClientUtil.getEsbPort());
		
		btnBack = (Button) findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			 finish();
			}
		});
		btnUpdate = (Button) findViewById(R.id.btn_updata);
		btnUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			ClientUtil.setEsbAddress(ip.getText().toString());	
			ClientUtil.setEsbPort(port.getText().toString());
			Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
			finish();	
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		ip.setText(ClientUtil.getEsbAddress());
		port.setText(ClientUtil.getEsbPort());
	}
}
