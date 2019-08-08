package com.example.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;

public class SharedPreferenceUtils{
    private SharedPreferences sharedPreferences;
    private String preference_name;
    private int mode = Context.MODE_PRIVATE;       //模式，默认为PRIVATE
    private SharedPreferences.Editor editor;
    private ListViewDemoActivity listViewDemoActivity;      //不要自己继承Activity！！智障！


    public SharedPreferenceUtils(String preference_name, int mode, ListViewDemoActivity listViewDemoActivity) {
        this.listViewDemoActivity = listViewDemoActivity;
        this.preference_name = preference_name;
        this.mode = mode;
        System.out.println(this.preference_name);
        System.out.println(this.mode);
        sharedPreferences = this.listViewDemoActivity.getSharedPreferences(this.preference_name, this.mode);
        System.out.println(sharedPreferences);
        editor = sharedPreferences.edit();
    }

    public void putInt(String keyName, int value){
        //内部类Editor，用于存入数据。取出直接取
        editor.putInt(keyName, value);   //要存入的数据
        editor.apply();
    }

    public void remove(String keyName){
        //内部类Editor，用于存入数据。取出直接取
        editor.remove(keyName);   //要存入的数据
        editor.apply();
    }

    public int getInt(String keyName, int defvalue){
        //内部类Editor，用于存入数据。取出直接取
        return sharedPreferences.getInt(keyName, defvalue);
    }

}
