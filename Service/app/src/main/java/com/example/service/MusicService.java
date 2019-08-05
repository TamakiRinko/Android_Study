package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    private static final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mediaPlayer;
    private IBinder iBinder = new LocalBinder();

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        mediaPlayer.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public void init(){
        mediaPlayer = MediaPlayer.create(this, R.raw.count_on_me);
    }

    public class LocalBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

    public int getMusicPlayProgress(){
        return mediaPlayer.getCurrentPosition();
    }
}
