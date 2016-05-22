package com.example.pinkdiary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView tvDisplay;
    private Button getButton;
    private Controller con;
    private ImageView imageViewHistory;
    public String diaryInput;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_fragment,
                container, false);
        Log.d("historyFragment", "onCreateView");

        imageViewHistory = (ImageView) view.findViewById(R.id.imageViewHistory);
        tvDisplay = (TextView) view.findViewById(R.id.displayTextView);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getButton = (Button)view.findViewById(R.id.getButton);
        getButton.setOnClickListener(this);

        // test database
        con = new Controller(getActivity());
        Log.d("mainFragment", String.valueOf(con.getNumberOfRows()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getButton:
                Log.d("getbutton", "click");
                String newTxt = new String();
                for (ArrayList<String> list: con.fetchDB()) {
                    for (String str: list) {
                        newTxt = newTxt + " " + str;
                    }
                    newTxt += "\n";
                }
                tvDisplay.setText(newTxt);
                imageViewHistory.setImageBitmap(BitmapFactory.decodeFile(ImageHelper.imageURI));
                break;
            default:
                Log.i("default button", "Unknown: " + view.getId());
                break;
        }
    }

}
//public class HistoryFragment extends Fragment implements View.OnClickListener {
//    private View view;
//    private TextView tvDisplay;
//    private Controller con;
//    private ImageView imageView;
//    private Button getButton;
//
//    public HistoryFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.history_fragment,
//                container, false);
//
//        con = new Controller(getActivity());
//        imageView = (ImageView) view.findViewById(R.id.imageViewHistory);
//        tvDisplay = (TextView) view.findViewById(R.id.displayTextView);
//
//        ArrayList<String> latestDiary = con.getData(con.getNumberOfRows());
//        //imageView.setImageBitmap(BitmapFactory.decodeFile(latestDiary.get(4)));
//        Log.d("historyFragment", String.valueOf(con.getNumberOfRows()));
//        return inflater.inflate(R.layout.history_fragment, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        getButton = (Button) view.findViewById(R.id.getButton);
//        getButton.setOnClickListener(this);
//        Log.d("historyFragment", "onActivityCreated");
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.getButton:
//                Log.d("getbutton", "click");
//
//                String newTxt = new String();
//                for (ArrayList<String> list: con.fetchDB()) {
//                    for (String str: list) {
//                        newTxt = newTxt + " " + str;
//                    }
//                    newTxt += "\n";
//                }
//                tvDisplay.setText(newTxt);
//
//                // Set image
//                //ArrayList<String> latestDiary = con.getData(con.getNumberOfRows());
//                //imageView.setImageBitmap(BitmapFactory.decodeFile(latestDiary.get(4)));
//
//                break;
//            default:
//                Log.i("default button", "Unknown: " + view.getId());
//                break;
//        }
//    }
//}
