package com.example.pinkdiary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv1;
	Button getButton;
	Button submitButton;
	EditText mEdit;
	String newValue = "PinkDiary Yes!!";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.displayTextView);
		addListenerOnButton();
	}

	public void actionGetButton(View v) {
		tv1.setText(newValue);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	public void addListenerOnButton() {
		getButton = (Button) findViewById(R.id.getButton);
		getButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//				startActivity(browserIntent);
				actionGetButton(arg0);
			}
		});
	}
	
	public void addListenerOnSubmitButton() {
		submitButton = (Button) findViewById(R.id.submitButton);
		mEdit   = (EditText)findViewById(R.id.inputText);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				newValue = mEdit.getText().toString().trim(); 
				Log.v("EditText", mEdit.getText().toString());
			}
		});
	}
}
