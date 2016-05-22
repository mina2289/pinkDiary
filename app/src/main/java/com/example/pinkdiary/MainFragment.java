package com.example.pinkdiary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

/**
 * Created by pengsh on 5/15/16.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView tvInput;
    private FloatingActionButton submitButton;
    private Button selectButton;
    private EditText mEdit;
    private Controller con;
    private ImageView imageViewMain;
    private String imagePath;
    private Integer REQUEST_CAMERA = 0;
    private Integer SELECT_FILE = 1;
    public ImageHelper imHelper;
    public String diaryInput;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment,
                container, false);
        Log.d("mainFragment", "onCreateView");

        imageViewMain = (ImageView) view.findViewById(R.id.imageViewMain);
        tvInput = (TextView) view.findViewById(R.id.inputTextView);
        imHelper = new ImageHelper();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submitButton = (FloatingActionButton)view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        selectButton = (Button)view.findViewById(R.id.selectButton);
        selectButton.setOnClickListener(this);
        // test database
        con = new Controller(getActivity());
        Log.d("mainFragment", String.valueOf(con.getNumberOfRows()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitButton:
                Log.d("submitbutton", "click");
                diaryInput = tvInput.getText().toString();
                Log.d("EditText", diaryInput);
                con.insertDB(diaryInput, ImageHelper.imageURI);
                break;
            case R.id.selectButton:
                Log.d("selectbutton", "click");
                imHelper.selectImage(getActivity());
                break;
            default:
                Log.i("default button", "Unknown: " + view.getId());
                break;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 1) { //RESULT_OK) {
//            if (requestCode == REQUEST_CAMERA) {
//                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//                File destination = new File(Environment.getExternalStorageDirectory(),
//                        System.currentTimeMillis() + ".jpg");
//                FileOutputStream fo;
//                try {
//                    destination.createNewFile();
//                    fo = new FileOutputStream(destination);
//                    fo.write(bytes.toByteArray());
//                    fo.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                imageViewMain.setImageBitmap(thumbnail);
//            } else if (requestCode == SELECT_FILE) {
//                Uri imageUri = data.getData();
//                Log.d("onActivityResult", imageUri.toString());
//
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                Cursor cursor = getActivity().getContentResolver().query(imageUri,
//                        filePathColumn, null, null, null);
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imagePath = cursor.getString(columnIndex);
//                cursor.close();
//                Log.d("onActivityResult","debug choose photo from gallary");
//                imageViewMain.setImageBitmap(BitmapFactory.decodeFile(imagePath));
//
//                //				Uri selectedImageUri = data.getData();
//                //				Log.d("onActivityResult", selectedImageUri.toString());
//                //
//                //				String[] projection = { MediaColumns.DATA };
//                //				CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null, null);
//                //				Cursor cursor = cursorLoader.loadInBackground();
//                //				int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
//                //				cursor.moveToFirst();
//                //
//                //				String selectedImagePath = cursor.getString(column_index);
//                //				Bitmap bm;
//                //				BitmapFactory.Options options = new BitmapFactory.Options();
//                //				options.inJustDecodeBounds = true;
//                //
//                //				BitmapFactory.decodeFile(selectedImagePath, options);
//                //				final int REQUIRED_SIZE = 200;
//                //				int scale = 1;
//                //				while (options.outWidth / scale / 2 >= REQUIRED_SIZE
//                //						&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
//                //					scale *= 2;
//                //				options.inSampleSize = scale;
//                //				options.inJustDecodeBounds = false;
//                //				bm = BitmapFactory.decodeFile(selectedImagePath, options);
//                //				imageView.setImageBitmap(bm);
//            }
//        }
//        else {
//            Log.d("onActivityResult","request canceled");
//        }

    }
}
