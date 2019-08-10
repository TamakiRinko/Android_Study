package com.example.sqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlitetest.database.DatabaseHelper;

public class DatabaseButtonActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase sqLiteDatabase;
    private ContentValues contentValues;
    private DatabaseHelper databaseHelper;

    public static final String TAG = DatabaseButtonActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        init();
    }


    public void init(){
        Button addButton = findViewById(R.id.add_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button updataButton = findViewById(R.id.update_button);
        updataButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                Transaction();
                Query();
                break;
            case R.id.delete_button:
                Delete();
                Query();
                break;
            case R.id.update_button:
                Update();
                Query();
                break;
        }
    }

    public void Insert(){
        //UI操作，建议使用线程来完成
        contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USERNAME, "阿呆");
        contentValues.put(DatabaseHelper.AGE, "16岁");
        //往数据库中的特定表中插入一个值，返回表中这一行的id
        long rowNumber = sqLiteDatabase.insert(DatabaseHelper.USERTABLE_NAME, null, contentValues);
        if(rowNumber != -1){
            //提示插入成功
            Toast.makeText(DatabaseButtonActivity.this, "插入成功！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查询database中的值
     */
    private void Query() {
        //游标，对于database的结果集合进行随机读取
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.USERTABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst()){       //移动到第一行
            int count = cursor.getCount();
            int usernameIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.USERNAME);      //username列号
            int ageIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.AGE);                //age列号
            for (int i = 0; i < count; i++) {
                //获得该列每一行数据的名称
                String userName = cursor.getString(usernameIndex);
                String age = cursor.getString(ageIndex);
                Log.i(TAG, i + ": username = " + userName + ", age = " + age);
                cursor.moveToNext();    //移动到下一行
            }
            Log.i(TAG, "=====================================");
        }
    }

    /**
     * 删除特定筛选条件的数据
     */
    private void Delete(){
        String whereClauseString = "username=? and age=?";    //删除条件：username=? and age=?
        String[] whereArgsString = {"阿呆", "16岁"};           //username为阿呆，age为16岁的
        sqLiteDatabase.delete(DatabaseHelper.USERTABLE_NAME, whereClauseString, whereArgsString);
    }

    /**
     * 更新特定筛选条件的数据
     */
    private void Update(){
        contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.AGE, "20岁");
        String whereClauseString = "username=? and age=?";    //更新条件：username=? and age=?
        String[] whereArgsString = {"阿呆", "16岁"};           //username为阿呆，age为16岁的
        sqLiteDatabase.update(DatabaseHelper.USERTABLE_NAME, contentValues, whereClauseString, whereArgsString);
    }

    private void rawOperation(){
        sqLiteDatabase.execSQL("insert into user(username, age) values ('liu da ming', '5岁')");
    }

    /**
     * 大容量数据操作的优化：
     * 使用事务
     */
    private void Transaction(){
        //开始事务，此时数据库db会被锁定
        sqLiteDatabase.beginTransaction();
        try{
            //执行操作
            for (int i = 0; i < 5; i++) {
                sqLiteDatabase.execSQL("insert into user(username, age) values ('liu da ming', '5岁')");
            }
            //结束时设置成功，退出。不设置成功的话会自动回滚，不提交
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
        }
    }

}
