package com.example.handlertest;

import android.app.Activity;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class HandlerButtonActivity extends Activity {

    public static final int MESSAGE_CODE = 888888;
//    Handler handler = new Handler(){        //在此处处理接收消息的函数！
//        @Override
//        /**
//         * 接收消息，此方法可能会有内存泄漏！！！更好的方法在下方
//         */
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){  //通过what来判断发送的是哪个人发的消息
//                case MESSAGE_CODE:
//                    int value = (int)msg.obj;
//                    textView.setText(String.valueOf(value));
//
//                    //处理完这条消息后，再发送新的消息。
//                    //注意：消息只能同时被一个使用，须构造新消息
//                    msg = Message.obtain();
//                    msg.arg1 = 0;
//                    msg.arg2 = 0;
//                    msg.what = MESSAGE_CODE;
//                    msg.obj = value - 1;
//                    if(value > 0){
//                        sendMessageDelayed(msg, 1200);
//                    }
//                    break;
//            }
//        }
//    };
    private TextView textView;
    private TestHandler testHandler = new TestHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        init();

        //发消息，延迟发，制造一个计时器
//        Message message = handler.obtainMessage();
        Message message1 = testHandler.obtainMessage();
//        message.arg1 = 0;
//        message.arg2 = 0;
//        message.what = MESSAGE_CODE;
//        message.obj = 10;
        message1.arg1 = 0;
        message1.arg2 = 0;
        message1.what = MESSAGE_CODE;
        message1.obj = 10;
        testHandler.sendMessageDelayed(message1, 1000);
//        handler.sendMessageDelayed(message, 1200);
    }

    public void init(){
        textView = findViewById(R.id.handler_text_view);
    }

    public TextView getTextView() {
        return textView;
    }

    /**
     * 使用静态类，弱引用，则TestHandler虽然是内部类，但与外部类无关，
     * 当外部类被销毁时，内部类对象也会被处理，不会有内存泄露
     */
    public static class TestHandler extends Handler{
        //弱引用
        public final WeakReference<HandlerButtonActivity> handlerButtonActivity;

        public TestHandler(HandlerButtonActivity activity){
            //使用外部activity初始化
            handlerButtonActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //获得外部activity，后续使用get方法获得对应成员
            HandlerButtonActivity activity = handlerButtonActivity.get();

            switch (msg.what){  //通过what来判断发送的是哪个人发的消息
                case MESSAGE_CODE:
                    int value = (int)msg.obj;
                    //使用get方法，而不是直接用textView。
                    // 当然，定义为静态类后也不能使用textView
                    activity.getTextView().setText(String.valueOf(value));
//                    textView.setText(String.valueOf(value));

                    //处理完这条消息后，再发送新的消息。
                    //注意：消息只能同时被一个使用，须构造新消息
                    msg = Message.obtain();
                    msg.arg1 = 0;
                    msg.arg2 = 0;
                    msg.what = MESSAGE_CODE;
                    msg.obj = value - 1;
                    if(value > 0){
                        sendMessageDelayed(msg, 1000);
                    }
                    break;
            }
        }
    }

}
