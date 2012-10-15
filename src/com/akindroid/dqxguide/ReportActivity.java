package com.akindroid.dqxguide;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReportActivity extends Activity {
	private SharedPreferences pref;
	
	private static final String REPORT_EDIT = "report_edit";
	
	private EditText mReportText;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_report_menu);
        
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        
        TextView backButton = (TextView) findViewById(R.id.report_menu_back);
        backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
        
        Button sendButton = (Button) findViewById(R.id.report_menu_send);
        sendButton.setOnClickListener(new MySendButton());
	}
	
	@Override
	public void onResume() {
		super.onResume();
		        
        mReportText = (EditText) findViewById(R.id.report_menu_edit);
        mReportText.setText(pref.getString(REPORT_EDIT, ""));
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(REPORT_EDIT, mReportText.getText().toString());
		editor.commit();
	}
	
	private class MySendButton implements View.OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.setType("text/Gmail");
			intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
			
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "akindroid@gmail.com"} );
			intent.putExtra(Intent.EXTRA_SUBJECT, "DQXGuide - Report");
			intent.putExtra(Intent.EXTRA_TEXT, mReportText.getText().toString());
			
			mReportText.setText("");
			
			startActivity(intent);
		}
	}

}
