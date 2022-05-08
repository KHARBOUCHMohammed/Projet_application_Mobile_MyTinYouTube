package com.Ceri.youtube_api.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//This database is created to handel favourite list
public class FavouriteList extends SQLiteOpenHelper {
    public FavouriteList(Context context) {
        super(context, "favourite.db", null, 1);

    }
//logic to create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table favourite(url TEXT primary key , thumb TEXT , videoid TEXT , title TEXT , des TEXT , dop TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists favourite");
    }
//logic to insert data into database
    public Boolean insert(String url , String thumb , String videoid , String title ,String des, String dop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", url);
        contentValues.put("thumb", thumb);
        contentValues.put("videoid",videoid);
        contentValues.put("title",title);
        contentValues.put("des",des);
        contentValues.put("dop",dop);
        long result = db.insert("favourite", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//logic to delete data from database
    public Boolean delete(String url ) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from favourite where url = ? ", new String[]{url});
        if (cursor.getCount() > 0) {
            long result = db.delete("favourite", "url=?", new String[]{url});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }
//logics to fetch different type of values from databse
    public Cursor getdataurl() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select url from favourite ", null);
        return cursor;


    }

    public Cursor getdatathumb() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select thumb from favourite ", null);
        return cursor;


    }
    public Cursor getvideoid() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select videoid from favourite ", null);
        return cursor;


    }

    public Cursor title() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select title from favourite ", null);
        return cursor;


    }
    public Cursor discription() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select des from favourite ", null);
        return cursor;


    }

    public Cursor dop() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select dop from favourite ", null);
        return cursor;


    }


}
