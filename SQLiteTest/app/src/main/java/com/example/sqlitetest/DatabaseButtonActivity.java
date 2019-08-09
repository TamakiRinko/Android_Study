package com.example.sqlitetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlitetest.database.DatabaseHelper;

public class DatabaseButtonActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置点击Add事件
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.USERNAME, "极客班");
                contentValues.put(DatabaseHelper.PASSWORD, "wasd");
                //往数据库中的特定表中插入一个值，返回表中这一行的id
                long rowNumber = sqLiteDatabase.insert(DatabaseHelper.DATABASE_NAME, null, contentValues);
                if(rowNumber != -1){
                    //提示插入成功
                    Toast.makeText(DatabaseButtonActivity.this, "插入成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
