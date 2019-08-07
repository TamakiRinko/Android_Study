package com.example.widget;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

public class TestWidget extends AppWidgetProvider {

    public static final String WIDGET_BUTTON_ACTION = "widget_button_action";

    /**
     * 收到广播后做的事
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        //处理点击事件！
        if(intent != null && TextUtils.equals(intent.getAction(), WIDGET_BUTTON_ACTION)){
            //接收到了点击，设置文字和颜色
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            remoteViews.setTextViewText(R.id.widget_textview, "be clicked");
            remoteViews.setTextColor(R.id.widget_textview, Color.RED);

            //更新widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, TestWidget.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    /**
     * 程序初始化时会调用onUpdate，在此时注册一个点击事件
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //将布局文件读出到远程View中
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

        //Intent发给TestWidget广播接收器
        Intent intent = new Intent();
        intent.setClass(context, TestWidget.class);
        intent.setAction(WIDGET_BUTTON_ACTION);
        //PendingIntent是未来要执行的事，过一段时间后再做
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        //设置点击事件，点击widget_button时，发出pendingIntent给TestWidget
        remoteViews.setOnClickPendingIntent(R.id.widget_button, pendingIntent);
        //更新widget
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }
}
