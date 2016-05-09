package com.example.pinkdiary;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ButtonAction {
	
	public ButtonAction () {
		//TextView tvInput = super.getTxtViewInput();
	}
	
	
	public void actionSubmitButton(TextView txtView) {
		
		String input = txtView.getText().toString();
		Log.d("EditText", input);
		Log.d("EditText", "test log in actionsubmitbutton");
	}
	
	public void addListenerOnSubmitButton(Button button, EditText mEdit, TextView txtView) {
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//newValue = mEdit.getText().toString().trim(); 
				//Log.d("EditText", mEdit.getText().toString());
				//actionSubmitButton(txtView);
			}
		});
	}
}
