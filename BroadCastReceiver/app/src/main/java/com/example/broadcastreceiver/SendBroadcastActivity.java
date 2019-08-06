package com.example.broadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class SendBroadcastActivity extends Activity implements View.OnClickListener {

    public static final String COM_EXAMPLE_BROADCASTRECEIVER_BROADCAST = "com.example.broadcastreceiver.broadcast";
    private TestBroadcastReceiver testBroadcastReceiver;
    private TestBroadcastReceiver localBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private Button sendBroadcastButton;
    private Button sendLocalBroadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);
        init();


    }

    @Override
    protected void onResume() {
        super.onResume();
//        /**
//         * 动态注册广播
//         */
//        //有一个intentFilter，过滤器
//        IntentFilter intentFilter = new IntentFilter();
//        //只接收名字为它的action
//        intentFilter.addAction(COM_EXAMPLE_BROADCASTRECEIVER_BROADCAST);
//        registerReceiver(testBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(testBroadcastReceiver);
        //取消注册应用内广播接收器
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

    public void init(){
        testBroadcastReceiver = new TestBroadcastReceiver();    //接收器，动态创建
        sendBroadcastButton = findViewById(R.id.send_broadcast_button);
        sendLocalBroadcastButton = findViewById(R.id.send_localbroadcast_button);
        sendBroadcastButton.setOnClickListener(this);
        sendLocalBroadcastButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.send_broadcast_button:
                //Intent发给谁
                intent.setAction(COM_EXAMPLE_BROADCASTRECEIVER_BROADCAST);
                intent.putExtra("toast", "This is my toast of Broadcast!");
                sendBroadcast(intent);
//                //有序广播，要给多个人发广播，可以设定顺序
//                sendOrderedBroadcast(intent);
//                LocalBroadcastManager     //本应用广播
                break;
            case R.id.send_localbroadcast_button:
                LocalBroadcast();
                break;
        }
    }

    public void LocalBroadcast(){
        //注册应用内广播接收器
        //步骤1：实例化BroadcastReceiver子类 & IntentFilter mBroadcastReceiver
        localBroadcastReceiver = new TestBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();

        //步骤2：实例化LocalBroadcastManager的实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        //步骤3：设置接收广播的类型
        intentFilter.addAction(COM_EXAMPLE_BROADCASTRECEIVER_BROADCAST);

        //步骤4：调用LocalBroadcastManager单一实例的registerReceiver（）方法进行动态注册
        localBroadcastManager.registerReceiver(localBroadcastReceiver, intentFilter);

        //步骤5： 发送应用内广播
        Intent intent = new Intent();
        intent.putExtra("toastLocal", "This is my toast of localBroadcast!");
        intent.setAction(COM_EXAMPLE_BROADCASTRECEIVER_BROADCAST);
        localBroadcastManager.sendBroadcast(intent);
    }
}
