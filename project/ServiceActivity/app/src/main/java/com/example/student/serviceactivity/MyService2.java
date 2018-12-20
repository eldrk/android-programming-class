package com.example.student.serviceactivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService2 extends Service {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");
            Intent intent_to = new Intent("com.example.student.serviceactivity");
            if(mode !=null){
                if(mode.equals("send1")){
                    Log.d("myservice_receiver","send1");
                    intent_to.putExtra("res","response from send1");
                }
                else if(mode.equals("send2")){
                    Log.d("myservice_receiver","send2");
                    intent_to.putExtra("res","response from send2");
                }
                sendBroadcast(intent_to);
            }
        }
    };
    public MyService2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myservice_receiver","onCreate()");
        registerReceiver(receiver,new IntentFilter("com.example.student.serviceactivity"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myservice_receiver","onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("myservice_receiver","onDestroy()");
        unregisterReceiver(receiver);
        super.onDestroy();
    }


}
