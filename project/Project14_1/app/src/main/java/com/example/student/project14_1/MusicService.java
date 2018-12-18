package com.example.student.project14_1;


import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * Created by student on 2018-11-15.
 */

public class MusicService extends Service {

    MediaPlayer mp;

    public MusicService() {
        super();
    }

    @Override
    public void onCreate() {
        android.util.Log.i("서비스 테스트","onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("서비스 테스트","onStartCommand()");
        mp = MediaPlayer.create(this,R.raw.song1);
        mp.setLooping(true);
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("서비스 테스트","onDestroy()");
        mp.stop();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
