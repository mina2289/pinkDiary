package com.example.pinkdiary;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends Activity {
	private TextView tvDisplay;
	private Controller con;
    private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		
		imageView = (ImageView)findViewById(R.id.imageView1);
		tvDisplay = (TextView) findViewById(R.id.displayTextView);
		con = new Controller(this);

		String newTxt = new String();

		for (ArrayList<String> list: con.fetchDB()) {
			for (String str: list) {
				newTxt = newTxt + " " + str;
			}
			newTxt += "\n";
		}

		tvDisplay.setText(newTxt);
	
		// Set image
		ArrayList<String> latestDiary = con.getData(con.getNumberOfRows());
		imageView.setImageBitmap(BitmapFactory.decodeFile(latestDiary.get(4)));
		Log.d("next page", "test log for image");
	}
}
