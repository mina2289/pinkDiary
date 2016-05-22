package com.example.pinkdiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;

public class ImageHelper {
    private Integer REQUEST_CAMERA = 0;
    private Integer SELECT_FILE = 1;
    static public String imageURI;

    public void selectImage(final Context context) {
        final CharSequence[] items = {"Take Photo", "Choose from Gallary", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (items[i].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    ((Activity) context).startActivityForResult(cameraIntent, REQUEST_CAMERA);
                    Log.d("selectImage", "take Photo");
                } else if (items[i].equals("Choose from Gallary")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    ((Activity) context).startActivityForResult(
                            Intent.createChooser(intent, "Select File"), SELECT_FILE);
                    Log.d("selectImage", "debug choose photo from gallary");
                } else if (items[i].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

	public void setImageURI(String URI) {
		imageURI = URI;
	}
//
//	public String getImageURI(String URI) {
//		return imageURI;
//	}

//	public void activityAction(int requestCode, int resultCode, Intent data, ImageView imageView, Context context) {
//		if (resultCode == Activity.RESULT_OK) {
//			if (requestCode == REQUEST_CAMERA) {
//				Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//				thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//				File destination = new File(Environment.getExternalStorageDirectory(),
//						System.currentTimeMillis() + ".jpg");
//				FileOutputStream fo;
//				try {
//					destination.createNewFile();
//					fo = new FileOutputStream(destination);
//					fo.write(bytes.toByteArray());
//					fo.close();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				//imageView.setImageBitmap(thumbnail);
//			} else if (requestCode == SELECT_FILE) {
//				Uri selectedImageUri = data.getData();
//				Log.d("onActivityResult", selectedImageUri.toString());
//				String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//				Cursor cursor = context.getContentResolver().query(selectedImageUri,
//						filePathColumn, null, null, null);
//				cursor.moveToFirst();
//
//				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//				String picturePath = cursor.getString(columnIndex);
//				cursor.close();
//				Log.d("onActivityResult","debug choose photo from gallary");
//				imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//
//				//				Uri selectedImageUri = data.getData();
//				//				Log.d("onActivityResult", selectedImageUri.toString());
//				//				
//				//				String[] projection = { MediaColumns.DATA };
//				//				CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null, null);
//				//				Cursor cursor = cursorLoader.loadInBackground();
//				//				int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
//				//				cursor.moveToFirst();
//				//				
//				//				String selectedImagePath = cursor.getString(column_index);
//				//				Bitmap bm;
//				//				BitmapFactory.Options options = new BitmapFactory.Options();
//				//				options.inJustDecodeBounds = true;
//				//				
//				//				BitmapFactory.decodeFile(selectedImagePath, options);
//				//				final int REQUIRED_SIZE = 200;
//				//				int scale = 1;
//				//				while (options.outWidth / scale / 2 >= REQUIRED_SIZE
//				//						&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
//				//					scale *= 2;
//				//				options.inSampleSize = scale;
//				//				options.inJustDecodeBounds = false;
//				//				bm = BitmapFactory.decodeFile(selectedImagePath, options);
//				//				imageView.setImageBitmap(bm);
//			}
//		}
//		else {
//			Log.d("onActivityResult","request canceled");
//		}
//	}

}