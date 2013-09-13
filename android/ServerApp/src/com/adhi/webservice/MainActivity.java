package com.adhi.webservice;

import com.adhi.webservice.util.ServerUtil;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn_start;
	private Button btn_stop;
	private String TAG = ServerUtil.TAG;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_start = (Button) findViewById(R.id.button1);
		btn_stop = (Button) findViewById(R.id.button2);
		
		if(isServerRunning()){
			btn_start.setEnabled(false);
			btn_stop.setEnabled(true);
		} else {
			btn_start.setEnabled(true);
			btn_stop.setEnabled(false);
		}
	
		
		btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn_start.setEnabled(false);
				btn_stop.setEnabled(true);
				Log.i(TAG, "Starting server");
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
				Log.i(TAG, "Stopping server");
				//server.startThread();
				stopService(new Intent(MainActivity.this, ServerBackgroundService.class));
			}
		});
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(isServerRunning()){
			btn_start.setEnabled(false);
			btn_stop.setEnabled(true);
		} else {
			btn_start.setEnabled(true);
			btn_stop.setEnabled(false);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private boolean isServerRunning() {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (ServerBackgroundService.class.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}

}
