package com.Ceri.youtube_api.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    //This SQL Lite database is created to store configuration details

    private static final String dbname = "configuration.db";
    private static final String tablename = "score";
    private static final int version = 1;
    public static final String col_1 = "ID";
    public static final String col_2 = "result";
    public static final String col_3 = "min";
    public static final String col_4 = "max";


    public Database(Context context) {
        super(context, dbname, null, version);
    }

    //Logic to create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + tablename + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,result TEXT , min TEXT , max TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);

        onCreate(db);

    }
//logic to insert data into database
    public boolean insertdata(String result,String min , String max) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, result);
        contentValues.put(col_3, min);
        contentValues.put(col_4, max);
        Long sucess = db.insert(tablename, null, contentValues);

        if (sucess == -1) {
            return false;

        } else {
            return true;
        }

    }
//logic to fetch all data from database
    public Cursor getAlldata() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tablename + " where ID = 1 ", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;



    }
//logic to update data into database
    public boolean updataData(String ID,String result , String min , String max)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,ID);
        contentValues.put(col_2, result);
        contentValues.put(col_3, min);
        contentValues.put(col_4, max);
        db.update(tablename,contentValues,"ID = ?",new String[]{ID});
        return true;

    }
}
