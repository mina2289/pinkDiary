package com.example.pinkdiary;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tvDisplay;
	TextView tvInput;
	Button getButton;
	Button submitButton;
	EditText mEdit;
	String newValue = "PinkDiary Yes!!";
	Controller con;
	public String diaryInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvDisplay = (TextView) findViewById(R.id.displayTextView);
		tvInput = (TextView) findViewById(R.id.inputTextView);
		//tvDisplay.setMovementMethod(new ScrollingMovementMethod());
		addListenerOnGetButton();
		addListenerOnSubmitButton();
		
		
		// test database
		con = new Controller(this);
		// Inserting Contacts
        //Log.d("Insert: ", "Inserting .."); 
        //con.insertDB("Keep going!!");
		//ArrayList array_list = mydb.getAllCotacts();
        //con.getData(1);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	
	public void actionGetButton() {
		String newTxt = new String();
		
		for (ArrayList<String> list: con.fetchDB()) {
			for (String str: list) {
				newTxt = newTxt + " " + str;
			}
			newTxt += "\n";
		}
		
		tvDisplay.setText(newTxt);
		Log.d("EditText", "test log in actiongetbutton");
	}
	
	public void addListenerOnGetButton() {
		final Context context = this;

		getButton = (Button) findViewById(R.id.getButton);
		getButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				actionGetButton();
				Intent intent = new Intent(context, Main2Activity.class);
                startActivity(intent);   
			}
		});
	}
	
	public void actionSubmitButton() {
		diaryInput = tvInput.getText().toString();
		Log.d("EditText", diaryInput);
		Log.d("Insert: ", "Inserting .."); 
        con.insertDB(diaryInput);
	}
	
	public String getDiaryInput() {
		return diaryInput;
	}
	
	public void addListenerOnSubmitButton() {
		submitButton = (Button) findViewById(R.id.submitButton);
		mEdit   = (EditText)findViewById(R.id.inputTextView);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//newValue = mEdit.getText().toString().trim(); 
				//Log.d("EditText", mEdit.getText().toString());
				actionSubmitButton();
			}
		});
	}
}
