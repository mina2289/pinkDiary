package com.example.pinkdiary;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity { //extends Activity {
    private ImageView imageViewMain;
	private String imagePath;
	private Integer REQUEST_CAMERA = 0;
	private Integer SELECT_FILE = 1;
	private ImageHelper imHelper;
	private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
 
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
 
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
	}

	private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "HOME");
        adapter.addFragment(new HistoryFragment(), "HISTORY");
        viewPager.setAdapter(adapter);
    }
 
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
 
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
 
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
 
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
 
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
 
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    /*
    Get the result from select button. Perform image setting.
     */
    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageViewMain = (ImageView) findViewById(R.id.imageViewMain);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageViewMain.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri imageUri = data.getData();
                Log.d("onActivityResult", imageUri.toString());

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(imageUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                cursor.close();
                Log.d("onActivityResult","debug choose photo from gallary");
                imageViewMain.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                imHelper = new ImageHelper();
                imHelper.setImageURI(imagePath);
                //				Uri selectedImageUri = data.getData();
                //				Log.d("onActivityResult", selectedImageUri.toString());
                //
                //				String[] projection = { MediaColumns.DATA };
                //				CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null, null);
                //				Cursor cursor = cursorLoader.loadInBackground();
                //				int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
                //				cursor.moveToFirst();
                //
                //				String selectedImagePath = cursor.getString(column_index);
                //				Bitmap bm;
                //				BitmapFactory.Options options = new BitmapFactory.Options();
                //				options.inJustDecodeBounds = true;
                //
                //				BitmapFactory.decodeFile(selectedImagePath, options);
                //				final int REQUIRED_SIZE = 200;
                //				int scale = 1;
                //				while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                //						&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
                //					scale *= 2;
                //				options.inSampleSize = scale;
                //				options.inJustDecodeBounds = false;
                //				bm = BitmapFactory.decodeFile(selectedImagePath, options);
                //				imageView.setImageBitmap(bm);
            }
        }
        else {
            Log.d("onActivityResult","request canceled");
        }

    }
}
