package com.example.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MusicButtonActivity extends Activity implements View.OnClickListener {

    private TextView textView;
    private Button startButton;
    private Button stopButton;
    private Button messageButton;
    private MusicService musicService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        /**
         * 服务链接和断开连接时通知
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获得MusicService传来的Ibinder对象
            MusicService.LocalBinder localBinder = (MusicService.LocalBinder)service;
            //由Ibinder对象获得MusicService对象引用，建立连接
            musicService = localBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_button);
        init();

    }

    public void init(){
        textView = findViewById(R.id.textMessage);
        startButton = findViewById(R.id.starts);
        stopButton = findViewById(R.id.stops);
        messageButton = findViewById(R.id.messages);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        messageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MusicButtonActivity.this, MusicService.class);;
        switch (v.getId()){
            /**
             * 播放服务，播放音乐，startService，两种形式：
             * Started：启动服务
             * Bound：绑定服务，在解绑之前无法stop！与Service进行沟通
             */
            case R.id.starts:
                startService(intent);       //Started模式不要下一句
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);       //Bound模式
                break;

            /**
             * 结束服务，stopService
             */
            case R.id.stops:
                unbindService(serviceConnection);   //Bound，关闭连接
                stopService(intent);      //Started模式不要上一句
                break;

            /**
             * 获取信息，显示在text上
             */
            case R.id.messages:
                if(musicService != null){
                    String message = "当前播放到：" + musicService.getMusicPlayProgress() / 1000.0 + "秒";
                    textView.setText(message);
                }
                break;
        }
    }
}
