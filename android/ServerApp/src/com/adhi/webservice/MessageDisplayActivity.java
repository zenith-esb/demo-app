package com.adhi.webservice;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MessageDisplayActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		 TextView topic = (TextView) findViewById(R.id.topic);
		 EditText msgBox = (EditText) findViewById(R.id.msg_box);
		 
		 Bundle extras = getIntent().getExtras();
		 topic.setText(extras.getString("title"));
		 msgBox.setText(extras.getString("message"));
	}
}
