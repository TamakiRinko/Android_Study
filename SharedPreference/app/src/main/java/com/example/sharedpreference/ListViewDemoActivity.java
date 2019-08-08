package com.example.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewDemoActivity extends Activity implements View.OnClickListener {

    public static final String PREFERENCE_NAME = "preference_name";
    public static final String LIST_VIEW_DATA_COUNTS = "list_view_data_counts";
    public static final int DEF_VALUE = 10;
    private ListView phoneBookListView;
    private List<UserInfo> userInfos;
    private int DataCounts;        //需要将DataCounts存储起来
    private PhoneBookAdapter phoneBookAdapter;
    private EditText dataCountEditText;
    private Button confirmButton;
    SharedPreferenceUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_listview_demo);

        utils = new SharedPreferenceUtils(PREFERENCE_NAME, Context.MODE_PRIVATE,ListViewDemoActivity.this);

        findViews();
        setData();

        //进行视图和数据的绑定
        phoneBookAdapter = new PhoneBookAdapter(ListViewDemoActivity.this, userInfos);
        phoneBookListView.setAdapter(phoneBookAdapter);

        //设置监听操作
        setListeners();

    }

    private void findViews() {
        phoneBookListView = findViewById(R.id.list_view);
        dataCountEditText = findViewById(R.id.data_counts_edit_text);
        confirmButton = findViewById(R.id.confirm_button);
    }

    private void setData() {
//        //取出数据
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
//        DataCounts = sharedPreferences.getInt(LIST_VIEW_DATA_COUNTS, DEF_VALUE);   //默认值为10！
//        dataCountEditText.setText(String.valueOf(DataCounts));

        DataCounts = utils.getInt(LIST_VIEW_DATA_COUNTS, DEF_VALUE);
        dataCountEditText.setText(String.valueOf(DataCounts));

        //设置数据
        userInfos = new ArrayList<>();
        for (int index = 0; index < DataCounts; index++) {
            userInfos.add(new UserInfo("阿妈", 45));
        }
    }

    private void setListeners() {
        phoneBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //view为Adapter中getView返回的视图
                //i：Item的位置，下标
                Toast.makeText(ListViewDemoActivity.this, userInfos.get(i).getmUserName() + "被点击了", Toast.LENGTH_SHORT).show();

                //点击第一项时修改
                if(i == 0){
                    //新建另外一批数据
                    userInfos.clear();
                    userInfos.add(new UserInfo("臭屁呆", 1));
                    userInfos.add(new UserInfo("臭屁泰", 2));
                    userInfos.add(new UserInfo("臭屁屎", 3));
                    //替换老的数据并刷新视图，这次是修改的userInfos，故直接更新即可
                    //phoneBookAdapter.refreshData(userInfos);
                    phoneBookAdapter.notifyDataSetChanged();
                }
            }
        });

        phoneBookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewDemoActivity.this, userInfos.get(i).getmUserName() + "被长按了", Toast.LENGTH_SHORT).show();
                //return false则长按后还会点击
                return true;
            }
        });

        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_button:
                String countString = dataCountEditText.getText().toString();
                DataCounts = Integer.valueOf(countString);
                refreshListView();
                saveDataToPreference(DataCounts);
                break;
        }
    }

    private void saveDataToPreference(int dataCounts) {
//        //系统会自动帮我们创建一个xml文件，名字为函数提供的名字preference_name"
//        //MODE_PRIVATE：默认：the created file can only be accessed by the calling application
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);    //名字叫preference_name
//        SharedPreferences.Editor editor = sharedPreferences.edit(); //内部类Editor，用于存入数据。取出直接取
//        editor.putInt(LIST_VIEW_DATA_COUNTS, dataCounts);   //要存入的数据
////        editor.remove(LIST_VIEW_DATA_COUNTS);       //删除
//
//        /**
//         * 	commit：立即写
//         * 	apply：后台写数据，另开线程，不占用UI线程
//         * 	和网络相关，和IO操作相关的建议使用apply！
//         * 	此情况下也建议使用apply！
//         */
//        editor.apply();         //执行
////        editor.commit();    //执行


        utils.putInt(LIST_VIEW_DATA_COUNTS, dataCounts);


//        /**
//         * Registers a callback to be invoked when a change happens to a preference.
//         * 当sharedPreferences改变时，可以监听到
//         */
//        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
//            @Override
//            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//
//            }
//        });

    }

    private void refreshListView() {
        userInfos.clear();
        for (int index = 0; index < DataCounts; index++) {
            userInfos.add(new UserInfo("阿妈", 45));
        }
        phoneBookAdapter.notifyDataSetChanged();
    }
}
