package com.example.student.serviceactivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("log_MyService","onBind()");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("log_MyService","onCreate()");

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("log_MyService","onStartCommand()");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("log_MyService","onDestroy()");
    }
}
