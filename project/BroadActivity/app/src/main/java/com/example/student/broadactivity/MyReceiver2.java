package com.example.student.broadactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"안녕 딩가딩가",Toast.LENGTH_SHORT).show();
    }
}
