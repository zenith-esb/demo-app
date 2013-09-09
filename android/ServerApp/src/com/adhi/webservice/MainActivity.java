package com.adhi.webservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn_start;
	private Button btn_stop;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_start = (Button) findViewById(R.id.button1);
		btn_stop = (Button) findViewById(R.id.button2);
		btn_stop.setEnabled(false);
	
		
		btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn_start.setEnabled(false);
				btn_stop.setEnabled(true);
				Log.i("WebService", "Starting server");
				//server.startThread();
				startService(new Intent(MainActivity.this, ServerBackgroundService.class));
			}
		});
		
		btn_stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn_start.setEnabled(true);
				btn_stop.setEnabled(false);
				Log.i("WebService", "Stopping server");
				//server.startThread();
				stopService(new Intent(MainActivity.this, ServerBackgroundService.class));
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
