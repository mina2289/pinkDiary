package com.example.pinkdiary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "db";
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_TABLE = "diary3";
	private String[] allColumns = { "_id" , "date", "week", "txt", "url" };
	//private String[] allColumns = { "_id" , "txt" };
	
	public DBhelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//context.deleteDatabase(DATABASE_TABLE);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+ DATABASE_TABLE + " (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"date DATETIME," +
				"week CHAR(3)," +
				"txt varcar(255)," +
				"url varchar(255))"
				);
		//db.execSQL("CREATE TABLE "+ DATABASE_TABLE + " (_id integer primary key autoincrement, txt text not null);");		

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(db);
	}

	public void insertContact(String comment) {
		SQLiteDatabase db = this.getWritableDatabase();
		
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put("txt", comment);
//		long insertId = db.insert(DATABASE_TABLE, null, values);
//
//		Cursor cursor = db.query(DATABASE_TABLE,
//				allColumns, "_id" + " = " + insertId, null,
//				null, null, null);
//
//		cursor.moveToFirst();
//		String idmem = String.valueOf(cursor.getLong(0));
//		String str = cursor.getString(1);
//		//Comment newComment = cursorToComment(cursor);
//		Log.d("controller", idmem);
//		Log.d("controller", str);
//
//		cursor.close();
	}


	/*
	 public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("phone", phone);
	      contentValues.put("email", email);
	      contentValues.put("street", street);
	      contentValues.put("place", place);
	      db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
	      return true;
	   }

	   public Integer deleteContact (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("contacts", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }

	   public ArrayList<String> getAllCotacts()
	   {
	      ArrayList<String> array_list = new ArrayList<String>();

	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from contacts", null );
	      res.moveToFirst();

	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
	         res.moveToNext();
	      }
	   return array_list;
	   }
	 */
}
