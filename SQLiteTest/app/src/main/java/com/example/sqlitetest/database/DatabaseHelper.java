package com.example.sqlitetest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.PluralRules;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String USERTABLE_NAME = "user";
    public static final String USERNAME = "username";
    public static final String AGE = "age";
    public static final String DATABASE_NAME = "mytest.db";
    public static final int VERSION = 1;

    /**
     * 创建一个数据库
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USERTABLE_NAME + "(" + USERNAME + " varchar(20) not null, " + AGE + " varchar(60) not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //检测到数据库升级时的操作
    }
}
