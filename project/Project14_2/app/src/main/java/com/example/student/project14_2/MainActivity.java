package com.example.student.project14_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    EditText et;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        et = (EditText) findViewById(R.id.et);


    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                et.setText("현재 충전량 : " +remain+ "\n");

                if(remain >=90)
                    iv.setImageResource(R.drawable.battery_100);
                else if(remain >=70)
                    iv.setImageResource(R.drawable.battery_80);
                else if(remain >=50)
                    iv.setImageResource(R.drawable.battery_60);
                else if(remain >=10)
                    iv.setImageResource(R.drawable.battery_20);
                else
                    iv.setImageResource(R.drawable.battery_0);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch (plug){
                    case 0 :
                        et.append("전원 연결 : 안됨");
                        Toast.makeText(getApplicationContext(),"전원 연결 안됨",Toast.LENGTH_LONG).show();
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC :
                        et.append("전원 연결 : 어댑터 연결됨");
                        Toast.makeText(getApplicationContext(),"전원 연결 ",Toast.LENGTH_LONG).show();
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB :
                        et.append("전원 연결 : USB 연결됨");
                        Toast.makeText(getApplicationContext(),"USB 연결 ",Toast.LENGTH_LONG).show();
                        break;
                }

                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);

                switch (status){
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        Toast.makeText(getApplicationContext(),"충전중",Toast.LENGTH_LONG).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        Toast.makeText(getApplicationContext(),"충전중아님",Toast.LENGTH_LONG).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        Toast.makeText(getApplicationContext(),"충전중",Toast.LENGTH_LONG).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        Toast.makeText(getApplicationContext(),"방전됨",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"알수없음",Toast.LENGTH_LONG).show();
                }
            }
        }


    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
        //android.util.Log.i();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,iFilter);

    }
}
