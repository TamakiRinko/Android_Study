package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.Toast;

public class TestBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //receive broadcast，handle data
        if(intent != null){
            //其实没必要判断，因为已经过滤过了，只会收到这类消息
            if(TextUtils.equals(intent.getAction(),
                    SendBroadcastActivity.COM_EXAMPLE_BROADCASTRECEIVER_BROADCAST)){
                String toastString = intent.getStringExtra("toast");
                if(toastString == null){
                    toastString = intent.getStringExtra("toastLocal");
                }
                //context就是发送者传入的activity的context
                Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
            }
        }
    }
}
