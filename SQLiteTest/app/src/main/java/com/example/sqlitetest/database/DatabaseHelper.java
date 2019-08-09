package com.example.sqlitetest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "user1";
    public static final String USERNAME = "username1";
    public static final String PASSWORD = "password1";

    /**
     * 创建一个数据库
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, "test8.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_NAME + "(" + USERNAME + " varchar(20) not null, " + PASSWORD + " varchar(60) not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //检测到数据库升级时的操作
    }
}
