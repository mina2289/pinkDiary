package com.example.pinkdiary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Controller {
	// Database fields
	private SQLiteDatabase db;
	private DBhelper dbHelper;
	private String[] allColumns = { "_id" , "date", "week", "txt", "url" };

	public Controller(Context context) {
		dbHelper = new DBhelper(context);
		this.open();
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void insertDB(String diaryInput, String imagePath) {
		Cursor res =  db.rawQuery( "select * from " + DBhelper.DATABASE_TABLE, null );
		Log.d("controller", res.toString());

		Calendar c = Calendar.getInstance();
		SimpleDateFormat rawDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		SimpleDateFormat rawWeek = new SimpleDateFormat("E", Locale.US);
		String formatDate = rawDate.format(c.getTime());
		String formatWeek = rawWeek.format(c.getTime());

		ContentValues contentValues = new ContentValues();
		contentValues.put("date", formatDate);
		contentValues.put("week", formatWeek);
		contentValues.put("txt", diaryInput);	
		contentValues.put("url", imagePath);
		long insertId = db.insert(DBhelper.DATABASE_TABLE, null, contentValues);
		this.getData(insertId);

	}
	
	public ArrayList<ArrayList<String>> fetchDB() {
		ArrayList<ArrayList<String>> dbData = new ArrayList<ArrayList<String>>();
		int row = 1; 
		while (row <= getNumberOfRows()) {
			dbData.add(getData(row));
			row++;
		}
		return dbData;
	}
	
	public ArrayList<String> getData(long id){
		//Cursor res =  db.rawQuery( "select * from " + dbHelper.DATABASE_TABLE + " where id=" + id + "", null );
		ArrayList<String> ans = new ArrayList<String>();
		Cursor cursor = db.query(DBhelper.DATABASE_TABLE,
				allColumns, "_id" + " = " + id, null,
				null, null, null);
		cursor.moveToFirst();
		String dbId = String.valueOf(cursor.getLong(0));
		String dbDate = cursor.getString(1);
		String dbWeek = cursor.getString(2);
		String dbTxt = cursor.getString(3);
		String dbUrl = cursor.getString(4);
		ans.add(dbId);
		ans.add(dbDate);
		ans.add(dbWeek);
		ans.add(dbTxt);
		ans.add(dbUrl);
		
		Log.d("controller", dbId + dbDate + dbWeek + dbTxt + dbUrl);
		
		return ans;
	}

	
	public int getNumberOfRows(){
		int numRows = (int) DatabaseUtils.queryNumEntries(db, DBhelper.DATABASE_TABLE);
		return numRows;
	}
}
