package com.example.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity implements  OnCheckedChangeListener, View.OnClickListener{

	//受信管理用AsyncTasc
	AsyncR syncr;

	//送信管理用AsyncTask
	AsyncS syncs;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//openswitchを変数に格納
		Switch s = (Switch) findViewById(R.id.Openswitch);

		s.setOnCheckedChangeListener(this);

		//sendButtonを変数に格納
		Button b = (Button)findViewById(R.id.sendButton);

		b.setOnClickListener(this);

		//受信開始
		syncr = new AsyncR((TextView)findViewById(R.id.textView3));
		syncr.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		

		
		

	}



	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		if(isChecked == true){
			
			//送信準備
			syncs = new AsyncS((EditText)findViewById(R.id.Ip),(EditText)findViewById(R.id.Port), (TextView)findViewById(R.id.textView3));
		}
		
	}

	

	@Override
	public void onClick(View v) {
		

	}



}



