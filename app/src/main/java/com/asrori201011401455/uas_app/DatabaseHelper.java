package com.asrori201011401455.uas_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databasename = "uas.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "uas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allUsers(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists allUsers");
    }

    public boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allUsers", null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allUsers where email = ?", new String[]{email});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allUsers where email = ? and password = ?", new String[]{email, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
